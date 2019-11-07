package com.ibm.service.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ibm.dto.MentorSkillDto;
import com.ibm.dto.UserDto;
import com.ibm.entity.MentorSkill;
import com.ibm.entity.User;
import com.ibm.repository.MentorSkillRepository;
import com.ibm.repository.UserRepository;
import com.ibm.service.UserService;

import javassist.NotFoundException;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	private final MentorSkillRepository mentorSkillRepository;

	private final ModelMapper modelMapper;

	public UserServiceImpl(UserRepository userRepository, MentorSkillRepository mentorSkillRepository,
			ModelMapper modelMapper) {
		this.userRepository = userRepository;
		this.mentorSkillRepository = mentorSkillRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Boolean register(UserDto userDto) throws Exception {

		List<User> userList = userRepository.getByUsername(userDto.getUsername());

		if (userList.size() > 0) {
			throw new Exception("User exist with this : " + userDto.getUsername());
		}

		User user = new User();
		user = modelMapper.map(userDto, User.class);

		userRepository.save(user);
		return true;

	}

	@Override
	public UserDto findByUsername(String username) throws NotFoundException {
		try {

			User user = userRepository.findByUsername(username);
			UserDto userDto = modelMapper.map(user, UserDto.class);
			userDto.setPassword(null);
			return userDto;

		} catch (Exception e) {

			throw new NotFoundException("User dosen't exist with this name called : " + username);
		}
	}

	@Override
	public MentorSkillDto createMentorSkill(MentorSkillDto mentorSkillDto) {
		MentorSkill mentorSkill = modelMapper.map(mentorSkillDto, MentorSkill.class);
		mentorSkillRepository.save(mentorSkill);
		mentorSkillDto.setId(mentorSkill.getId());
		return mentorSkillDto;
	}

}
