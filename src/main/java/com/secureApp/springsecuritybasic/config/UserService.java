package com.secureApp.springsecuritybasic.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.secureApp.springsecuritybasic.model.User;
import com.secureApp.springsecuritybasic.model.dto.UserDTO;
import com.secureApp.springsecuritybasic.model.dto.UserResponseDTO;
import com.secureApp.springsecuritybasic.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final ObjectMapper objectMapper;

    public UserService(UserRepository userRepository, ObjectMapper objectMapper) {
        this.userRepository = userRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<User> user = userRepository.findByEmail(username);
        String email = null;
        String password = null;
        List<GrantedAuthority> authorities = null;
        if(!user.isEmpty()){
            email = user.get(0).getEmail();
            password = user.get(0).getPassword();
            authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(user.get(0).getRole()));
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }

    public UserResponseDTO registerUser(UserDTO userDTO){
        User user = objectMapper.convertValue(userDTO, User.class);
        user = userRepository.save(user);
        return objectMapper.convertValue(user, UserResponseDTO.class);
    }

}
