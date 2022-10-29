package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import com.modal.Quiz;
import com.modal.ResultResponse;
import com.service.QuizService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class QuizController {
	@Autowired
	private QuizService quizService;

	@PostMapping("/getQuizById/{id}")
	public Quiz getQuizById(@PathVariable int id) {
		Quiz quiz = new Quiz();
		quiz = quizService.getQuizById(id);
		return quiz;
	}

	@GetMapping("/getAllQuiz")
	public List<Quiz> getQuiz() {
		List<Quiz> quizList = new ArrayList<>();
		quizList = quizService.getQuiz();
		return quizList;
	}

	@PostMapping("/addNewQuiz")
	public ResultResponse addQuiz(@RequestBody Quiz quiz) {
		return quizService.addQuiz(quiz);
	}

	@PostMapping("/addQuizToCat")
	ResultResponse addQuizToCategory(@RequestBody Quiz quiz) {
		ResultResponse result = new ResultResponse();
		result = quizService.addQuizToCategory(quiz);
		return result;
	}

	@PostMapping("/updateQuiz")
	public Quiz updateQuiz(@RequestBody Quiz quiz) {

		return quizService.updateQuiz(quiz);
	}

	@PostMapping("/deleteQuiz")
	public ResultResponse deleteQuiz(@RequestBody Quiz quiz) {

		return quizService.deleteQuiz(quiz);
	}
	
	

}
