package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.dao.QuestionDao;
import com.modal.Question;
import com.modal.Quiz;
import com.modal.ResultResponse;
import org.springframework.stereotype.Service;

@Service
public class QuestionServiceImpl implements QuestionService{

	@Autowired
	private QuestionDao quesDao;
	
	@Override
	public Question addQuestion(Question ques) {
		return quesDao.addQuestion(ques);
	}

	@Override
	public ResultResponse updateQuestion(Question ques) {
		return quesDao.updateQuestion(ques);
	}

	@Override
	public List<Question>  getQuestionOfQuiz(int quizId) {
		return quesDao.getQuestionOfQuiz(quizId);
	}

	@Override
	public Question getQuesById(int id) {
		return quesDao.getQuesById(id);
	}

	@Override
	public ResultResponse deleteQuestion(int id) {
		return quesDao.deleteQuestion(id);
	}

	@Override
	public ResultResponse addQuestionToQuiz(Question quiz) {
		return quesDao.addQuestionToQuiz(quiz);
	}

	
}
