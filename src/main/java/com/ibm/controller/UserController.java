package com.ibm.controller;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.dto.MentorSkillDto;
import com.ibm.dto.UserDto;
import com.ibm.service.impl.UserServiceImpl;

import javassist.NotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/api")
@RestController
public class UserController {

	private final UserServiceImpl userServiceImpl;
	
	public UserController(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}
	
	@PostMapping("/user")
	public ResponseEntity<Boolean> createUser(@Valid @RequestBody UserDto userDto) throws Exception {
		log.info("UserController:"+userDto);
		return ResponseEntity.ok(userServiceImpl.register(userDto));
	}
	
	@PostMapping("/user/mentorSkill")
	public ResponseEntity<MentorSkillDto> createMentorSkill(@Valid @RequestBody MentorSkillDto mentorSkillDto) throws Exception {
		log.info("createMentorSkill:"+mentorSkillDto);
		return ResponseEntity.ok(userServiceImpl.createMentorSkill(mentorSkillDto));
	}
	
	@GetMapping("/user/{username}")
	ResponseEntity<UserDto> findByUsername(@PathVariable String username) throws NotFoundException {
		UserDto user = userServiceImpl.findByUsername(username);
		return ResponseEntity.ok(user);
	}
}
