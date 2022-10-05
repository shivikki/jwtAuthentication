package com.mapper;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dao.QuizDaoImpl;
import com.modal.Category;
import com.modal.Question;
import com.modal.Role;
import com.modal.User;
import com.modal.Quiz;
public class MasterMapper {

	private static final Logger LOG = LoggerFactory.getLogger(MasterMapper.class);
	
	public User mapUser(Map<String,Object> userMap) {
		LOG.info("inside m/d  mapUser");
		User user=new User();
		user.setId((int) userMap.get("id"));
		user.setUsername((String) userMap.get("username"));
		user.setPassword((String) userMap.get("password"));
		user.setFirstName((String) userMap.get("first_name"));
		user.setLastName((String) userMap.get("last_name"));
		user.setEmail((String) userMap.get("email"));
		user.setPhone((String) userMap.get("phone"));
		user.setEnabled((boolean) userMap.get("enabled"));
		
		return user;
	}
	
	public Role mapRole(Map<String,Object> roleMap) {
		LOG.info("inside m/d  mapRole");
		Role role=new Role();
		role.setRoleId((int) roleMap.get("role_id"));
		role.setRoleName((String) roleMap.get("role_name"));
		return role;
	}
	
	
	
	public Category mapCategory(Map<String,Object> categoryMap) {
		LOG.info("inside m/d  mapCategory");
		Category category=new Category();
		category.setCid((int) categoryMap.get("cid"));
		category.setDescription((String) categoryMap.get("description"));
		category.setTitle((String) categoryMap.get("title"));
		return category;
	}
	
	public Quiz mapQuiz(Map<String,Object> quizMap) {
		LOG.info("inside m/d  mapQuiz");
		Quiz quiz=new Quiz();
		quiz.setQuizId((int) quizMap.get("quiz_id"));
		quiz.setTitle((String) quizMap.get("title"));
		quiz.setDescription((String) quizMap.get("description"));
		quiz.setMax_marks((int) quizMap.get("max_marks"));
		quiz.setNoOfQues((int) quizMap.get("no_of_ques"));
		quiz.setEnabled((boolean) quizMap.get("enable"));
		return quiz;
	}
	
	public int mapQuizQues(Map<String,Object> quesQuizMap) {
		LOG.info("inside m/d  mapQuizQues");
		int id=(int) quesQuizMap.get("ques_id");
		return id;
	}
	public Question mapQuestion(Map<String,Object> quesMap) {
		LOG.info("inside m/d  mapQuiz");
		Question ques=new Question();
		ques.setQuesId((int) quesMap.get("ques_id"));
		ques.setContent((String) quesMap.get("content"));
		ques.setOpt1((String) quesMap.get("opt1"));
		ques.setOpt2((String) quesMap.get("opt2"));
		ques.setOpt3((String) quesMap.get("opt3"));
		ques.setOpt4((String) quesMap.get("opt4"));
		ques.setAnswer((String) quesMap.get("answer"));
		return ques;
	}
	
}
