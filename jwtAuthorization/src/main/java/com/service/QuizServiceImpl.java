package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dao.QuizDao;
import com.modal.Quiz;
import com.modal.ResultResponse;
@Service
public class QuizServiceImpl implements QuizService{
	@Autowired
	private QuizDao quizDao;
	@Override
	public ResultResponse addQuiz(Quiz quiz) {
		return quizDao.addQuiz(quiz);
	}

	@Override
	public Quiz updateQuiz(Quiz quiz) {
		return quizDao.updateQuiz(quiz);
	}

	@Override
	public List<Quiz> getQuiz() {
		return quizDao.getQuiz();
	}

	@Override
	public Quiz getQuizById(int id) {
		return quizDao.getQuizById(id);
	}

	

	@Override
	public ResultResponse addQuizToCategory(Quiz quiz) {
		return quizDao.addQuizToCategory(quiz);
	}
	@Override
	public ResultResponse deleteQuiz(Quiz quiz) {
		return quizDao.deleteQuiz(quiz);
	}

}
