package org.example.security;

import org.example.entity.UserEntity;
import org.example.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    private UserEntity userEntity;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByEmail(s).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email = %s does not exist ", s))
        );

        this.userEntity = userEntity;

        return SecurityUser.fromUser(userEntity);
    }

    public UserEntity getUserEntity() {
        return userEntity;
    }
}
