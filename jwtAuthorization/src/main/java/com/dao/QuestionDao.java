package com.dao;

import java.util.List;

import com.modal.Question;
import com.modal.ResultResponse;

public interface QuestionDao {
	public Question addQuestion(Question quiz);

	public ResultResponse updateQuestion(Question quiz);

	public List<Question>  getQuestionOfQuiz(int quizId);

	public Question getQuesById(int id);

	public ResultResponse deleteQuestion(int id);
	
	public ResultResponse addQuestionToQuiz(Question quiz);
}
