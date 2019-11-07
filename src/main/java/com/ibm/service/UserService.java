package com.ibm.service;

import com.ibm.dto.MentorSkillDto;
import com.ibm.dto.UserDto;

import javassist.NotFoundException;

public interface UserService {

	public Boolean register(UserDto userDto) throws Exception;
	
	public MentorSkillDto createMentorSkill(MentorSkillDto mentorSkillDto);
	
	public UserDto findByUsername(String username) throws NotFoundException;
}
