package com.ibm.service.impl;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.ibm.dto.TrainingDto;
import com.ibm.entity.Training;
import com.ibm.repository.TrainingRepository;
import com.ibm.service.TrainingService;

import javassist.NotFoundException;

@Service
public class TrainingServiceImpl implements TrainingService{
	
private final TrainingRepository trainingRepository;
	
	private final ModelMapper modelMapper;
	
	public TrainingServiceImpl(TrainingRepository trainingRepository,ModelMapper modelMapper) {
		this.trainingRepository = trainingRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public TrainingDto save(TrainingDto trainingDto) {
		Training training = modelMapper.map(trainingDto, Training.class);
		trainingRepository.save(training);
		trainingDto.setId(training.getId());
		return trainingDto;
	}

	@Override
	public TrainingDto update(Integer id, TrainingDto trainingDto) throws NotFoundException{
		Optional<Training> trainingOpt = trainingRepository.findById(id);
		
		if (!trainingOpt.isPresent()) {
			throw new NotFoundException("User dosen't exist : " + id);
		}
		
		Training training = modelMapper.map(trainingDto, Training.class);
		training.setId(id);
		trainingRepository.save(training);
		trainingDto.setId(training.getId());
		return trainingDto;
	}

}
