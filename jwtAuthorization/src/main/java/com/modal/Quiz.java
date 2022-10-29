package com.modal;

public class Quiz {

	private int quizId;
	private String title;
	private String description;
	private int max_marks;
	private int noOfQues;
	private boolean enabled = false; // by default fALSE ..admin has to publish quiz
	private int catId;
	private String category;

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Quiz() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getQuizId() {
		return quizId;
	}

	public void setQuizId(int quizId) {
		this.quizId = quizId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getMax_marks() {
		return max_marks;
	}

	public void setMax_marks(int max_marks) {
		this.max_marks = max_marks;
	}

	public int getNoOfQues() {
		return noOfQues;
	}

	public void setNoOfQues(int noOfQues) {
		this.noOfQues = noOfQues;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public Quiz(int quizId, String title, String description, int max_marks, int noOfQues, boolean enabled, int catId) {
		super();
		this.quizId = quizId;
		this.title = title;
		this.description = description;
		this.max_marks = max_marks;
		this.noOfQues = noOfQues;
		this.enabled = enabled;
		this.catId = catId;
	}

}
