package com.queryConst;

public class QueryConstant {
	public static final String FIND_USER_BY_ID = "select * from exam.user where id=?";

	public static final String FIND_ROLE_BY_ID = "select * from exam.role where role_id=?";

	public static final String INSERT_IN_USER_ROLE = "insert into exam.user_role (user_id,role_id)\r\n"
			+ "values (?,?);";

	public static final String CREATE_NEW_USER = "INSERT INTO exam.user\r\n" + "(email,first_name,last_name,\r\n"
			+ "password,phone,username,enabled)\r\n" + "VALUES\r\n" + "(?,?,?,?,?,?,?);";

	public static final String FIND_USER_BY_EMAIL = "select * from exam.user where email=?;";

	public static final String FIND_USER_BY_USERNAME = "select * from exam.user where username=?;";

	public static final String DELETE_USER_BY_ID = "DELETE FROM exam.user WHERE id=?;";

	public static final String FIND_ROLE_TAGGED_TO_USER ="select * from exam.role where role_id in (select role_id from exam.user_role where user_id=:id );";
	
	public static final String DELETE_FROM_USER_ROLE="delete from user_role where user_id=?;";
	
	public static final String GET_CATEGORY_BY_ID="select * from exam.category where cid=?;";
	
	public static final String GET_ALL_CATEGORY="select * from exam.category";
	
	public static final String ADD_NEW_CATEGORY="INSERT INTO exam.category (title,description) VALUES (?,?);";
	
	public static final String CHECK_CATEGORY_EXIST="select * from exam.category where title=?;";
	
	public static final String UPDATE_CATEGORY="update exam.category set title=?, description=? where title=?;";
	
	public static final String DELETE_CATEGORY="delete from exam.category where cid=?;";
	
	public static final String GET_QUIZ_BY_ID="select * from exam.quiz where quiz_id=?;";
	
	public static final String GET_ALL_QUIZ="select * from exam.quiz;";
	
	public static final String CHECK_QUIZ_EXIST="select * from exam.quiz where title=?;";
	
	public static final String INSERT_NEW_QUIZ="INSERT INTO exam.quiz (title,description,max_marks,no_of_ques,enable) VALUES (?,?,?,?,?);";
	
	public static final String MAP_QUIZ_CATEGORY="INSERT INTO exam.quiz_category (cid,quiz_id) VALUES (?,?);";
	
	public static final String CHECK_QUIZ_CAT_MAP="select * from exam.quiz_category where cid=? and quiz_id=?;";
	
	public static final String UPDATE_QUIZ="update exam.quiz set title=?, description=?, max_marks=?, no_of_ques=?, enable=? where quiz_id=?";
	
	//queries for question
	public static final String GET_QUES_BY_ID="select * from exam.question where ques_id in (?);";
	
	public static final String CHECK_QUES_EXIST="select * from exam.question where content=?;";
	
	public static final String INSERT_NEW_QUES="INSERT INTO exam.question (content,opt1,opt2,opt3,opt4,answer) VALUES (?,?,?,?,?,?);";
	
	public static final String INSERT_QUES_QUIZ="INSERT INTO exam.quiz_question (ques_id,quizz_id) VALUES (?,?);";
	
	public static final String CHECK_QUES_QUIZ="select count(*) from  exam.quiz_question where ques_id = ? and quizz_id=?";
	
	public static final String GET_ALL_QUES="select * from exam.question where ques_id in (:quesId);";
	
	public static final String GET_QUES_MAP_TO_QUZ="select * from exam.quiz_question where quizz_id=?;";
	
	public static final String DELETE_QUES_BY_ID="delete from exam.quiz_question where ques_id=?;";
	
	public static final String DELETE_QUES_QUIZ_MAP="delete from exam.quiz_question where ques_id=?;";
	
	public static final String UPDATE_QUES_BY_ID="UPDATE exam.question SET  content = ?,opt1 = ?,opt2 = ?,opt3 = ?,opt4 = ?,answer =?\r\n"
			+ "WHERE ques_id =?;";
	
	public static final String GET_QUES_BY_ID2="select * from exam.question where ques_id = ?;";
		
	
	
	
}
