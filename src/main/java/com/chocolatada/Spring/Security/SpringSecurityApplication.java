package com.chocolatada.Spring.Security;

import com.chocolatada.Spring.Security.entity.RoleEntity;
import com.chocolatada.Spring.Security.entity.RoleEnum;
import com.chocolatada.Spring.Security.entity.UserEntity;
import com.chocolatada.Spring.Security.repository.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Set;

@SpringBootApplication
public class SpringSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityApplication.class, args);
	}

	@Bean
	public CommandLineRunner runner(IUserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				RoleEntity userRole = new RoleEntity();
				userRole.setName(RoleEnum.USER);
				RoleEntity adminRole = new RoleEntity();
				adminRole.setName(RoleEnum.ADMIN);

				UserEntity firstUser = new UserEntity();
				firstUser.setUsername("Pablo");
				firstUser.setPassword(passwordEncoder.encode("password"));
				firstUser.setRoles(Set.of(userRole));

				UserEntity secondUser = new UserEntity();
				secondUser.setUsername("Max");
				secondUser.setPassword(passwordEncoder.encode("password"));
				secondUser.setRoles(Set.of(adminRole));

				userRepository.saveAll(List.of(firstUser, secondUser));
			}
		};
	}
}
