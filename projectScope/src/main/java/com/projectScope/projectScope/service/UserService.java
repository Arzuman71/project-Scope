package com.projectScope.projectScope.service;

import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.model.enums.Type;
import com.projectScope.projectScope.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    @Value("${file.upload.dir}")
    private String uploadDir;

    private final UserRepo userRepo;

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public void saveUserAndImg(User user, MultipartFile file) {
        if (file != null) {
            try {
                String name = UUID.randomUUID().toString().replace("-", "") + file.getOriginalFilename();
                File picUrl = new File(uploadDir, name);
                file.transferTo(picUrl);
                user.setProfilePicture(name);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        userRepo.save(user);
    }

    public User save(User user) {
        return userRepo.save(user);
    }


    public List<User> findAllByType(Type type) {
        return userRepo.findAllByType(type);
    }

    public User getOne(long id) {
        return userRepo.getOne(id);
    }
}
