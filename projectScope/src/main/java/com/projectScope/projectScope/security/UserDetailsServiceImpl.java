package com.projectScope.projectScope.security;



import com.projectScope.projectScope.model.User;
import com.projectScope.projectScope.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UserRepo userRepo;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user= userRepo.findByEmail(s).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CurrentUser(user);
    }
}
