package com.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.mapper.MasterMapper;
import com.modal.Category;
import com.modal.Quiz;
import com.modal.ResultResponse;
import com.queryConst.QueryConstant;
import org.springframework.stereotype.Repository;

@Repository
public class QuizDaoImpl implements QuizDao {

	private static final Logger LOG = LoggerFactory.getLogger(QuizDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	@Autowired
	private CategoryDaoImpl categoryDao;

	@Override
	public ResultResponse addQuiz(Quiz quiz) {
		LOG.info("inside addQuiz m/d start");
		ResultResponse output=new ResultResponse();
		// add quiz then add to category
		List<Quiz> quizExist = quizExist(quiz);
		if (quizExist.size() > 0) {
			output.setValidationFlag(true);
			output.setValidationStatus("Question alredy exist");
			return output;
		} else {
			try {
				int result = jdbcTemplate.update(QueryConstant.INSERT_NEW_QUIZ, quiz.getTitle(), quiz.getDescription(),
						quiz.getMax_marks(), quiz.getNoOfQues(), quiz.isEnabled());
				if (result > 0) {
					
					output=addQuizToCategory(quiz);
					return output;
				}

			} catch (Exception e) {
				LOG.error("error in addQuiz m/d" + e);
			}
			return output;
		}

	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {

		LOG.info("inside update Quiz m/d start");
		try {

			int result = jdbcTemplate.update(QueryConstant.UPDATE_QUIZ, quiz.getTitle(), quiz.getDescription(),
					quiz.getMax_marks(), quiz.getNoOfQues(), quiz.isEnabled(),quiz.getQuizId());
			if (result > 0) {
				quiz.setDescription("Quiz updated successfully.");
				quiz.setTitle("SUCCESS");
				quiz.setMax_marks(0);
				quiz.setNoOfQues(0);
				return quiz;
			} else {
				quiz.setDescription("Quiz not exist.");
				quiz.setTitle("ERROR");
				quiz.setMax_marks(0);
				quiz.setNoOfQues(0);
				return quiz;
			}

		} catch (Exception e) {
			LOG.error("getQuiz m/d " + e);
		}
		
		return quiz;

	}

	@Override
	public List<Quiz> getQuiz() {
		Quiz quiz = new Quiz();
		List<Quiz> quizList = new ArrayList<>();
		LOG.info("inside getQuiz m/d start");
		try {

			List<Map<String, Object>> quizData = jdbcTemplate.queryForList(QueryConstant.GET_ALL_QUIZ);
			if (!quizData.isEmpty()) {
				for (Map<String, Object> u : quizData) {
					quiz = (Quiz) new MasterMapper().mapQuiz(u);
					quizList.add(quiz);
				}
				return quizList;
			}

		} catch (Exception e) {
			LOG.error("getQuiz m/d " + e);
		}
		return quizList;
	}

	@Override
	public Quiz getQuizById(int id) {
		Quiz quiz = new Quiz();
		LOG.info("inside getQuizById m/d start");
		try {

			List<Map<String, Object>> quizData = jdbcTemplate.queryForList(QueryConstant.GET_QUIZ_BY_ID, id);
			if (!quizData.isEmpty()) {
				for (Map<String, Object> u : quizData) {
					quiz = (Quiz) new MasterMapper().mapQuiz(u);
				}
				return quiz;
			}

		} catch (Exception e) {
			LOG.error("getQuizById m/d " + e);
		}
		return quiz;
	}

	@Override
	public ResultResponse deleteQuiz(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Quiz> quizExist(Quiz quiz) {
		LOG.info("inside quizExist m/d start");
		List<Quiz> quizList = new ArrayList<>();
		try {

			List<Map<String, Object>> quizData = jdbcTemplate.queryForList(QueryConstant.CHECK_QUIZ_EXIST,
					quiz.getTitle());
			if (!quizData.isEmpty()) {
				for (Map<String, Object> u : quizData) {
					quiz = (Quiz) new MasterMapper().mapQuiz(u);
					quizList.add(quiz);
				}
				return quizList;
			}

		} catch (Exception e) {
			LOG.error("getQuizById m/d " + e);
		}
		return quizList;
	}

	@Override
	public ResultResponse addQuizToCategory(Quiz quiz) {
		ResultResponse response = new ResultResponse();
		LOG.info("inside addQuizToCategory m/d start");
		try {
			List<Quiz> getQuiz = quizExist(quiz);
			Category category = categoryDao.getCategoryById(quiz.getCatId());

			if (getQuiz.size()>0 && category != null) {
				List<Map<String, Object>> quiz_cat = jdbcTemplate.queryForList(QueryConstant.CHECK_QUIZ_CAT_MAP,
						quiz.getCatId(), getQuiz.get(0).getQuizId());
				if (quiz_cat.isEmpty()) {
					int result = jdbcTemplate.update(QueryConstant.MAP_QUIZ_CATEGORY, quiz.getCatId(),
							 getQuiz.get(0).getQuizId());

					if (result > 0) {
						response.setValidationStatus("Quiz added to selected category");
						response.setResponseCode(1);
					} else {
						response.setValidationStatus("Something went wrong. Please try again after sometime.");
						response.setResponseCode(0);
					}
				} else {
					response.setValidationStatus("Quiz already added in selected category.");
					response.setResponseCode(0);
				}

			} else {
				response.setValidationStatus("Selected quiz or category does not exist");
				response.setResponseCode(0);
			}

		} catch (Exception e) {
			LOG.error("getQuizById m/d " + e);
		}
		return response;
	}

}
