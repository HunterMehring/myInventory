package org.launchcode.myInventory.service;

import org.launchcode.myInventory.models.CustomUserDetails;
import org.launchcode.myInventory.models.Role;
import org.launchcode.myInventory.models.User;
import org.launchcode.myInventory.models.data.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUsers = userDao.findByName(username);
        if(optionalUsers.isPresent()){
            return optionalUsers.map(CustomUserDetails::new).get();
        }else {
            throw new UsernameNotFoundException("Username not found");
        }
    }

    public void registerNewUserAccount(User user){
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        //newUser.setRoles(new Role(Integer.valueOf(1), user));
        userDao.save(newUser);
    }
}

