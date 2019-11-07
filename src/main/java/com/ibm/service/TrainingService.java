package com.ibm.service;

import com.ibm.dto.TrainingDto;

import javassist.NotFoundException;

public interface TrainingService {

	public TrainingDto save(TrainingDto trainingDto);
	
	public TrainingDto update(Integer id, TrainingDto trainingDto) throws NotFoundException;
	
}
