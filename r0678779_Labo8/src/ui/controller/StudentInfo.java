package ui.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.db.StudentDB;
import domain.model.Student;

/**
 * Servlet implementation class StudentInfo
 */
@WebServlet("/StudentInfo")
public class StudentInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentDB studentenDB = new StudentDB();
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String voornaam = request.getParameter("voornaam");
		String naam = request.getParameter("naam");
		Student student = studentenDB.vindStudent(voornaam, naam);
		if (student != null){
			request.setAttribute("leeftijd", student.getLeeftijd());
			request.setAttribute("studierichting", student.getStudierichting());
			RequestDispatcher view = request.getRequestDispatcher("gevonden.jsp");
			view.forward(request, response);
		}
		else{
			RequestDispatcher view = request.getRequestDispatcher("nietgevonden.jsp");
			view.forward(request, response);
		}*/
		/*RequestDispatcher view = request.getRequestDispatcher(vindStudent(request, response));
		view.forward(request, response);*/
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*String voornaam = request.getParameter("voornaam");
		String naam = request.getParameter("naam");
		int leeftijd = Integer.parseInt(request.getParameter("leeftijd"));
		String studierichting = request.getParameter("studierichting");
		if (voornaam != null && !voornaam.isEmpty() && naam != null && !naam.isEmpty() && leeftijd > 0 && studierichting != null && !studierichting.isEmpty()){
		studentenDB.voegStudentToe(naam, voornaam, leeftijd, studierichting);
		request.setAttribute("studentenDB", studentenDB);
		RequestDispatcher view = request.getRequestDispatcher("overview.jsp");
		view.forward(request, response);
		}
		else{
			RequestDispatcher view = request.getRequestDispatcher("studentForm.jsp");
			view.forward(request, response);
		}*/
		/*RequestDispatcher view = request.getRequestDispatcher(createStudent(request, response));
		view.forward(request, response);*/
		processRequest(request, response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String action = request.getParameter("action");
		if (action == null){
			action = "index";
		}
		String destination;
		switch(action){
			case "find":
				destination = vindStudent(request, response);
				break;
			case "add":
				destination = createStudent(request, response);
				break;
			case "read":
				request.setAttribute("studenten", studentenDB.getAlleStudenten());
				destination = "overview.jsp";
				break;
			case "delete":
				destination = deleteStudent(request, response);
				break;
			case "verwijderBevestiging":
				destination = confirmDelete(request, response);
				break;
			case "update":
				destination = update(request, response);
				break;
			case "confirmUpdate":
				destination = confirmUpdate(request, response);
				break;
			default:
				destination = "index.html";
		}
		RequestDispatcher view = request.getRequestDispatcher(destination);
		view.forward(request, response);
	}
	
	
	private String update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student student = studentenDB.vindStudent(request.getParameter("naam"));
		if (student == null){
			return "StudentInfo?action=read";
		}
		request.setAttribute("voornaamPreviousValue", student.getVoornaam());
		request.setAttribute("leeftijdPreviousValue", student.getLeeftijd());
		request.setAttribute("studierichtingPreviousValue", student.getStudierichting());
		return "update.jsp";
	}
	private String confirmUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<String> result = new ArrayList<String>();
		Student student = new Student();
		getNaam(student, request, result);
		getVoornaam(student, request, result);
		getLeeftijd(student, request, result);
		getStudierichting(student, request, result);
		if (!result.isEmpty()){
			request.setAttribute("result", result);
			return "update.jsp";
		}
		else{
			studentenDB.verwijderStudent(request.getParameter("naam"));
			studentenDB.voegStudentToe(student);
			return "StudentInfo?action=read";
		}
	}
	private String deleteStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String naam = request.getParameter("naam");
		String voornaam = request.getParameter("voornaam");
		if (studentenDB.vindStudent(naam) != null){
			request.setAttribute("delete", "ok");
			return "deleteConfirmation.jsp";
		}
		return "nietgevonden.jsp";
	}
	private String confirmDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		if (studentenDB.verwijderStudent(request.getParameter("naam"))){
			return "StudentInfo?action=read";
		}
		else{
			return "nietgevonden.jsp";
		}
	}
	private String createStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		Student student = new Student();
		ArrayList<String> result = new ArrayList<String>();
		isUniek(request, result);
		getNaam(student, request, result);
		getVoornaam(student, request, result);
		getLeeftijd(student, request, result);
		getStudierichting(student, request, result);
		if (! result.isEmpty()){
			request.setAttribute("result", result);
			return "studentForm.jsp";
		}
		else{
			studentenDB.voegStudentToe(student);
			return showStudents(request, response);
		}
	}
	private String vindStudent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<String> result = new ArrayList<String>();
		String voorNaam = request.getParameter("voornaam");
		String naam = request.getParameter("naam");
		
		request.setAttribute("voorNaamPreviousValue", voorNaam);
		request.setAttribute("naamPreviousValue", naam);
		try{
			studentenDB.naamOk(voorNaam);
		}
		catch (Exception E){
			request.setAttribute("voorNaamClass", "error");
			result.add(E.getMessage());
		}
		try{
			studentenDB.naamOk(naam);
		}
		catch (Exception E){
			request.setAttribute("naamClass", "error");
			result.add(E.getMessage());
		}
		if (!result.isEmpty()){
			request.setAttribute("result", result);
			return "vindStudent.jsp";
		}
		else{
			Student student = studentenDB.vindStudent(naam);
			if (student != null){
			request.setAttribute("leeftijd", student.getLeeftijd());
			request.setAttribute("studierichting", student.getStudierichting());
			return "gevonden.jsp";}
			else{
				return "nietgevonden.jsp";
				}
		}
	}
	private void isUniek(HttpServletRequest request, ArrayList<String> result){
		String naam = request.getParameter("naam");
		if (!studentenDB.uniek(naam)){
			result.add("Persoon bestaat al");
		}
	}
	private void getNaam(Student student, HttpServletRequest request, ArrayList<String> result){
		String naam = request.getParameter("naam");
		request.setAttribute("naamPreviousValue", naam);
		try{
			student.setNaam(naam);
		}
		catch (Exception exc){
			request.setAttribute("naamClass", "error");
			result.add(exc.getMessage());
		}
	}
	private void getVoornaam(Student student, HttpServletRequest request, ArrayList<String> result){
		String voornaam = request.getParameter("voornaam");
		request.setAttribute("voornaamPreviousValue", voornaam);
		try{
			student.setVoornaam(voornaam);
		}
		catch (Exception exc){
			request.setAttribute("voornaamClass", "error");
			result.add(exc.getMessage());
		}
	}
	private void getLeeftijd(Student student, HttpServletRequest request, ArrayList<String> result){
		String leeftijd = request.getParameter("leeftijd");
		request.setAttribute("leeftijdPreviousValue", leeftijd);
		try{
			student.setLeeftijd(leeftijd);
		}
		catch (Exception exc){
			request.setAttribute("leeftijdClass", "error");
			result.add(exc.getMessage());
		}
	}
	private void getStudierichting(Student student, HttpServletRequest request, ArrayList<String> result){
		String studierichting = request.getParameter("studierichting");
		request.setAttribute("studierichtingPreviousValue", studierichting);
		try{
			student.setStudierichting(studierichting);
		}
		catch (Exception exc){
			request.setAttribute("studierichtingClass", "error");
			result.add(exc.getMessage());
		}
	}
	private String showStudents(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		ArrayList<Student> studenten = studentenDB.getAlleStudenten();
		request.setAttribute("studenten", studenten);
		return "overview.jsp";
	}
}