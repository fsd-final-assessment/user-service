package com.ibm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.entity.Skill;

public interface SkillRepository extends JpaRepository<Skill, Integer> {

}
