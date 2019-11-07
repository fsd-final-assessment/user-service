package com.ibm.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ibm.dto.SkillDto;
import com.ibm.entity.Skill;
import com.ibm.repository.SkillRepository;
import com.ibm.service.SkillService;

@Service
public class SkillServiceImpl implements SkillService{
	
	private final SkillRepository skillRepository;
	
	private final ModelMapper modelMapper;
	
	public SkillServiceImpl(SkillRepository skillRepository,ModelMapper modelMapper) {
		this.skillRepository = skillRepository;
		this.modelMapper = modelMapper;
	}
	@Override
	public SkillDto save(SkillDto skillDto) {
		Skill skill = modelMapper.map(skillDto, Skill.class);
		skillRepository.save(skill);
		skillDto.setId(skill.getId());
		return skillDto;
	}

}
