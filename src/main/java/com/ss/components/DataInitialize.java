package com.ss.components;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.ss.entity.Role;
import com.ss.entity.User;
import com.ss.repo.RoleRepository;
import com.ss.repo.UserRepository;

import jakarta.annotation.PostConstruct;

@Component
public class DataInitialize {
	
	@Autowired
	private RoleRepository roleRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostConstruct
    public void init() {
        // Buat role jika belum ada
        Role userRole = roleRepo.findByName("User").orElseGet(() -> {
            Role role = new Role();
            role.setName("User");
            return roleRepo.save(role);
        });

        Role adminRole = roleRepo.findByName("Administrator").orElseGet(() -> {
            Role role = new Role();
            role.setName("Administrator");
            return roleRepo.save(role);
        });

        // Buat user dengan role User jika belum ada
        if (userRepo.findByUsername("user").isEmpty()) {
            User user = new User();
            user.setUsername("user");
            user.setPassword(passwordEncoder.encode("password"));
            Set<Role> roles = new HashSet<>();
            roles.add(userRole);
            user.setRole(roles);
            userRepo.save(user);
        }

        // Buat admin dengan role Administrator jika belum ada
        if (userRepo.findByUsername("admin").isEmpty()) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("password"));
            Set<Role> roles = new HashSet<>();
            roles.add(adminRole);
            admin.setRole(roles);
            userRepo.save(admin);
        }
    }
	
}
