package com.ibm;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.jasypt.encryption.StringEncryptor;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import com.ibm.dto.SearchTrainingResultDto;
import com.ibm.entity.MentorSkill;
import com.ibm.entity.Skill;
import com.ibm.entity.Training;
import com.ibm.entity.User;
import com.ibm.repository.MentorSkillRepository;
import com.ibm.repository.SkillRepository;
import com.ibm.repository.TrainingRepository;
import com.ibm.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceApplicationTests {
	
	private static final Logger logger = LoggerFactory.getLogger(UserServiceApplicationTests.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private SkillRepository skillRepository;
	
	@Autowired
	private MentorSkillRepository mentorSkillRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
    StringEncryptor encryptor;
	
	@Before
    public void init() {
		this.userRepository.deleteAll();
		this.skillRepository.deleteAll();
		this.mentorSkillRepository.deleteAll();
		this.trainingRepository.deleteAll();
		
		Skill s1 = new Skill();
		s1.setName("Spring boot");
		s1.setToc("test");
		s1.setStatus("Active");
		s1.setCreateDate(LocalDateTime.now());
		Skill s2 = new Skill();
		s2.setName("angular");
		s2.setToc("test");
		s2.setStatus("Active");
		s2.setCreateDate(LocalDateTime.now());
		Skill[] ss = {s1,s2};
		List<Skill> skills = this.skillRepository.saveAll(Arrays.asList(ss));
		
		User u1 = new User();
		u1.setFirstName("danny");
		u1.setLastName("xx");
		u1.setUsername("danny@test.com");
		u1.setPassword("123");
		u1.setCreateDate(LocalDateTime.now());
		u1.setRoles("MENTOR");
		u1.setStatus("Active");
		User u2 = new User();
		u2.setFirstName("test");
		u2.setLastName("xx");
		u2.setUsername("test@test.com");
		u2.setPassword("123");
		u2.setStatus("Active");
		u2.setCreateDate(LocalDateTime.now());
		u2.setRoles("USER");
		this.userRepository.save(u1);
		this.userRepository.save(u2);
		
		MentorSkill ms1 = new MentorSkill();
		ms1.setPrice(new BigDecimal(100));
		ms1.setSelfRating(4.5f);
		ms1.setMentor(u1);
		ms1.setStatus("Active");
		ms1.setCreateDate(LocalDateTime.now());
		ms1.setSkill(skills.get(0));
		MentorSkill ms2 = new MentorSkill();
		ms2.setPrice(new BigDecimal(150));
		ms2.setSelfRating(5.5f);
		ms2.setMentor(u1);
		ms2.setStatus("Active");
		ms2.setCreateDate(LocalDateTime.now());
		ms2.setSkill(skills.get(1));
		MentorSkill[] ms = {ms1,ms2};
		this.mentorSkillRepository.saveAll(Arrays.asList(ms));
		
		Training t1 = new Training();
		t1.setCreateDate(LocalDateTime.now());
		User u = new User();
		u.setId(u2.getId());
		User m = new User();
		m.setId(u1.getId());
		Skill s = new Skill();
		s.setId(s1.getId());
		t1.setUser(u2);
		t1.setMentor(m);
		t1.setSkill(s);
		t1.setStatus("Active");
		t1.setStartDate(LocalDateTime.now());
		this.trainingRepository.save(t1);
    }
	
	@Test
	public void findByUsername() {
		User user = this.userRepository.findByUsername("danny@test.com");
		Assert.assertTrue("danny".equals(user.getFirstName()));
	}
	
	@Test
	public void getMentorSkills() {
		User user = this.userRepository.findByUsername("danny@test.com");
		List<MentorSkill> mentorSkills = user.getMentorSkills();
		System.out.println("--------------:"+mentorSkills.get(0).getPrice());
		Assert.assertTrue(user.getMentorSkills().size() == 2);
	}
	
	@Test
	public void getUserTrainings() {
		List<SearchTrainingResultDto> list = this.trainingRepository.getUserTrainings("test@test.com");
		Assert.assertTrue(list.size() == 1);
	}
	
	@Test
	public void getMentorTrainings() {
		List<SearchTrainingResultDto> list = this.trainingRepository.getMentorTrainings("danny@test.com");
		Assert.assertTrue(list.size() == 1);
	}
	
	@Test
	public void getTrainingsBySkillName() {
		Pageable pageable = PageRequest.of(0, 10);
		List<SearchTrainingResultDto> list = this.trainingRepository.searchTrainingsBySkillName("spring",pageable);
		Assert.assertTrue(list.size() == 1);
	}
	
	@Test
	public void findBySkillNameIgnoreCaseLikeOrderBySelfRatingDesc() {
		List<MentorSkill> list = this.mentorSkillRepository.findBySkillNameIgnoreCaseLikeOrderBySelfRatingDesc("%%");
		System.out.println(list.size());
		Assert.assertTrue(list.size() ==2);
//		Assert.assertTrue(list.get(0).getSelfRating() == 5.5f);
//		Assert.assertTrue("danny@test.com".equals(list.get(0).getMentor().getUsername()));
	}
	
	@Test
    public void getPass(){
        String password = encryptor.encrypt("fsd");//hello world
        System.out.println(password); //XBEEjr52//G95oPnW9ZglDUQQ6xraQSQ
        
        System.out.println(encryptor.decrypt("XBEEjr52//G95oPnW9ZglDUQQ6xraQSQ"));
	}
	
}
