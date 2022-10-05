package com.service;

import java.util.List;

import com.modal.Quiz;
import com.modal.ResultResponse;

public interface QuizService {
	public Quiz addQuiz(Quiz quiz);

	public Quiz updateQuiz(Quiz quiz);

	public List<Quiz> getQuiz();

	public Quiz getQuizById(int id);

	public ResultResponse deleteQuiz(int id);
	
	public ResultResponse addQuizToCategory(Quiz quiz);
}
