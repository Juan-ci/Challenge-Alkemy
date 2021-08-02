package com.example.DisneyAPI;

import com.example.DisneyAPI.dto.UserDto;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DisneyApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(DisneyApiApplication.class, args);
	}
        
        @Bean
        UserDto beanUserDto() {
            return new UserDto();
        }
}
