package com.ss.services;

import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ss.entity.User;
import com.ss.repo.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;
	
	public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
		
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(),
				user.getPassword(),
				user.getRole().stream().map(role->new SimpleGrantedAuthority("ROLE_" + role.getName())).collect(Collectors.toList())
		);
	}

}
