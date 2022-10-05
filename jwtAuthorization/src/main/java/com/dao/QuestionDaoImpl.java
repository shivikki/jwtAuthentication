package com.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mapper.MasterMapper;
import com.modal.Question;
import com.modal.Quiz;
import com.modal.ResultResponse;
import com.queryConst.QueryConstant;

import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoImpl implements QuestionDao {

	private static final Logger LOG = LoggerFactory.getLogger(QuizDaoImpl.class);
	@Autowired
	private JdbcTemplate jdbcTemplate;
	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplateNamed;

	@Override
	public Question addQuestion(Question ques) {
		LOG.info("inside addQuestion m/d start");

		// add quiz then add to category
		List<Question> quesExist = quesExist(ques);
		if (quesExist.size() > 0) {
			ques.setContent("Question already exists.");
			ques.setAnswer("EXIST");
			ques.setOpt1(null);
			ques.setOpt2(null);
			ques.setOpt3(null);
			ques.setOpt4(null);
			return ques;
		} else {
			try {
				int result = jdbcTemplate.update(QueryConstant.INSERT_NEW_QUES, ques.getContent(), ques.getOpt1(),
						ques.getOpt2(), ques.getOpt3(), ques.getOpt4(), ques.getAnswer());
				// jdbcTemplate.update(QueryConstant.INSERT_NEW_QUES, ques.getContent(),
				// ques.getOpt1(),ques.getOpt2(), ques.getOpt2(), ques.getOpt3(),
				// ques.getOpt4(),ques.getAnswer());
				if (result > 0) {
					quesExist = quesExist(ques);
					ques.setContent("Ques added successfully");
					ques.setAnswer("SUCCESS");
					ques.setOpt1(null);
					ques.setOpt2(null);
					ques.setOpt3(null);
					ques.setOpt4(null);
					// ques.setQuizId(quesExist.get(0).getQuizId());
					ques.setQuesId(quesExist.get(0).getQuesId());
					ResultResponse response = mapQuesToQuiz(ques);
					if (response.getValidationStatus() == "SUCCESS") {
						ques.setContent("Ques mappeed successfully to quiz");
						ques.setAnswer("SUCCESS");
					} else {
						ques.setContent("Ques not mapped to quiz");
						ques.setAnswer("ERROR");
					}

					return ques;
				}

			} catch (Exception e) {
				LOG.error("error in addQuestion m/d" + e);
			}
			return ques;
		}
	}

	@Override
	public ResultResponse updateQuestion(Question question) {
		LOG.info("Inside updateQuestion starts");
		ResultResponse result = new ResultResponse();
		try {
			Question quesList = getQuesById(question.getQuesId());
			if (null != quesList) {
				int row = jdbcTemplate.update(QueryConstant.UPDATE_QUES_BY_ID, question.getContent(),
						question.getOpt1(), question.getOpt2(), question.getOpt3(), question.getOpt4(),
						question.getAnswer(),question.getQuesId());
				if (row > 0) {
					result.setValidationStatus("SUCCESS");
					return result;
				}

			}

		} catch (Exception e) {
			LOG.error("Error in updateQuestion m/d " + e);
		}
		result.setValidationStatus("ERROR");
		return result;
	}

	@Override
	public List<Question> getQuestionOfQuiz(int quizId) {
		List<Integer> quesId = new ArrayList<>();
		List<Question> quesList = new ArrayList<>();
		LOG.info("inside getQuestion() m/d start");
		try {
			// get question id that belongs to quiz GET_QUES_MAP_TO_QUZ
			List<Map<String, Object>> quesData = jdbcTemplate.queryForList(QueryConstant.GET_QUES_MAP_TO_QUZ, quizId);
			if (!quesData.isEmpty()) {

				quesData.forEach(ques -> {
					quesId.add((Integer) new MasterMapper().mapQuizQues(ques));

				});
				Map<String, Object> params = new HashMap<>();
				params.put("quesId", quesId);
				List<Map<String, Object>> allQues = jdbcTemplateNamed.queryForList(QueryConstant.GET_ALL_QUES, params);
				if (!allQues.isEmpty()) {
					allQues.forEach(ques -> {
						quesList.add((Question) new MasterMapper().mapQuestion(ques));
					});
				}
			}

		} catch (Exception e) {
			LOG.error("error in getQuestion() m/d" + e);
		}
		return quesList;
	}

	@Override
	public Question getQuesById(int id) {
		Question ques = new Question();
		LOG.info("inside getQuesById m/d start");
		try {

			List<Map<String, Object>> quesData = jdbcTemplate.queryForList(QueryConstant.GET_QUES_BY_ID2, id);
			if (!quesData.isEmpty()) {
				for (Map<String, Object> u : quesData) {
					ques = (Question) new MasterMapper().mapQuestion(u);
				}
				return ques;
			}

		} 
		catch (Exception e) {
			LOG.error("getQuesById m/d " + e);
		}
		return ques;
	}

	@Override
	public ResultResponse deleteQuestion(int id) {
		LOG.info("inside deleteQuestion m/d start");
		ResultResponse result = new ResultResponse();
		try {

			int row = jdbcTemplate.update(QueryConstant.DELETE_QUES_BY_ID, id);
			if (row > 0) {
				result.setValidationStatus("SUCCESS");
			} else {
				result.setValidationStatus("ERROR");
			}

		} catch (Exception e) {
			LOG.error("getQuesById m/d " + e);
		}
		return result;
	}

	@Override
	public ResultResponse addQuestionToQuiz(Question quiz) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Question> quesExist(Question ques) {
		LOG.info("inside quesExist m/d start");
		List<Question> quesList = new ArrayList<>();
		try {

			List<Map<String, Object>> quesData = jdbcTemplate.queryForList(QueryConstant.CHECK_QUES_EXIST,
					ques.getContent());
			if (!quesData.isEmpty()) {
				for (Map<String, Object> u : quesData) {
					ques = (Question) new MasterMapper().mapQuestion(u);
					quesList.add(ques);
					return quesList;
				}

			}

		} catch (Exception e) {
			LOG.error("quizExist m/d " + e);
		}
		return quesList;
	}

	public ResultResponse mapQuesToQuiz(Question ques) {
		LOG.info("inside mapQuesToQuiz m/d start");
		ResultResponse response = new ResultResponse();
		List<Question> quesList = new ArrayList<>();
		try {
			List<Map<String, Object>> checkMap = jdbcTemplate.queryForList(QueryConstant.CHECK_QUES_QUIZ,
					ques.getQuesId(), ques.getQuizId());

			if (checkMap.size() > 0) {
				System.out.println("Ques ID" + ques.getQuesId() + "---" + "quiz ID" + ques.getQuizId());
				int result = jdbcTemplate.update(QueryConstant.INSERT_QUES_QUIZ, ques.getQuesId(), ques.getQuizId());
				if (result > 0) {
					response.setValidationStatus("SUCCESS");
				}
				return response;

			}
		} catch (Exception e) {
			LOG.error("MapQuesToQuiz m/d" + e);
		}
		response.setValidationStatus("ERROR");
		return response;
	}

}
