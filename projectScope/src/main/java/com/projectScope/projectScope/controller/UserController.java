package com.projectScope.projectScope.controller;

import com.projectScope.projectScope.dto.AuthRequestDto;
import com.projectScope.projectScope.dto.AuthResponseDto;
import com.projectScope.projectScope.dto.UserRegisterDto;
import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.model.enums.Type;
import com.projectScope.projectScope.security.CurrentUser;
import com.projectScope.projectScope.security.JwtTokenUtil;
import com.projectScope.projectScope.service.EmailService;
import com.projectScope.projectScope.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil tokenUtil;
    private final ModelMapper modelMapper;
    private final EmailService emailService;


    @PostMapping("/user/auth")
    public ResponseEntity<Object> auth(@RequestBody AuthRequestDto authRequest) {
        Optional<User> byEmail = userService.findByEmail(authRequest.getEmail());
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (passwordEncoder.matches(authRequest.getPassword(), user.getPassword())) {
                AuthResponseDto auth = new AuthResponseDto(tokenUtil.generateToken(user.getEmail()), user.getType());
                return ResponseEntity.ok(auth);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED");
    }

    @PostMapping("/user")
    public ResponseEntity<Integer> register(@Valid @RequestBody UserRegisterDto userRegister,
                                            BindingResult result,
                                            @RequestParam(required = false) MultipartFile file) {
        if (!result.hasErrors()) {

            Optional<User> byEmail = userService.findByEmail(userRegister.getEmail());
            if (!byEmail.isPresent()) {
                User user = modelMapper.map(userRegister, User.class);
                user.setPassword(passwordEncoder.encode(userRegister.getPassword()));
                user.setOtp(UUID.randomUUID().toString());
                userService.saveUserAndImg(user, file);
                String link = "http://localhost:8080/user/activate?email=" + user.getEmail() + "&otp=" + user.getOtp();
                emailService.send(user.getEmail(), "Welcome", "Dear " + user.getName() + ' ' + user.getSurname() + " You have successfully registered.Please " +
                        "activate your account by clicking on: " + link);
                return ResponseEntity.ok(0);
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(result.getFieldErrorCount());
    }

    @GetMapping("/user/activate")
    public ResponseEntity<String> activate(@RequestParam("email") String email,
                                           @RequestParam("otp") String otp) {
        Optional<User> byEmail = userService.findByEmail(email);
        if (byEmail.isPresent()) {
            User user = byEmail.get();
            if (user.getOtp().equals(otp)) {
                user.setActive(true);
                user.setOtp("");
                userService.save(user);
                return ResponseEntity.ok("User was activate,please login");
            }
        }
        return ResponseEntity.badRequest().body("Something went wrong.Please try again");
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> users() {
        return ResponseEntity.ok(userService.findAllByType(Type.TEAM_MEMBER));
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal CurrentUser currentUser) {
        return ResponseEntity.ok(currentUser.getUser());
    }

}
