package com.blog;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.blog.config.AppConstants;
import com.blog.entities.Role;
import com.blog.repository.RoleRepo;

import java.util.List;

@SpringBootApplication

public class BlogAppApisApplication implements CommandLineRunner {
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	public static void main(String[] args) {
		SpringApplication.run(BlogAppApisApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(this.passwordEncoder.encode("admin"));
		try {
			Role role = new Role();
			role.setId(AppConstants.ADMIN);
			role.setName("ROLE_ADMIN");
			Role role1 = new Role();
			role1.setId(AppConstants.NORMAL);
			role1.setName("ROLE_EMP");
			// List<Role> roles=List.of(role,role1);
			this.roleRepo.saveAll(List.of(role, role1));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
