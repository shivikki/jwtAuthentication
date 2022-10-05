package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.modal.ResultResponse;
import com.modal.Question;
import com.service.QuestionService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuestionController {

	@Autowired
	private QuestionService quesService;

	@PostMapping("/addNewQuestion")
	public Question addQuestion(@RequestBody Question ques) {
		ques = quesService.addQuestion(ques);
		return ques;
	}

	@PostMapping("/getQuestionByQuizId/{quizId}")
	public List<Question> getQuestionOfQuiz(@PathVariable int quizId) {
		List<Question> quesList = quesService.getQuestionOfQuiz(quizId);
		return quesList;

	}

	@DeleteMapping("/deleteQues/{quesId}")
	public ResultResponse deleteQuestion(@PathVariable int quesId) {
		return quesService.deleteQuestion(quesId);

	}

	@PostMapping("/updateQues")
	public ResultResponse updateQuestion(@RequestBody Question ques) {
		return quesService.updateQuestion(ques);

	}
}
