package domain.db;

import java.util.ArrayList;

import domain.model.Student;

public class StudentDB {
	private ArrayList<Student> studenten = new ArrayList<Student>();
	
public StudentDB(){
	studenten.add(new Student("Piersoul","Arno","22","Toegepaste Informatica"));
	studenten.add(new Student("Hellemans","Maarten","23","Dierenzorg"));
	studenten.add(new Student("Debouwer","Bob","42","Bouwkunde"));
	studenten.add(new Student("Pickering","Ronnie","54","Pickeringkunde"));
	studenten.add(new Student("Melaerts","Kristien","21","Chemie"));
	studenten.add(new Student("Jongen","Greetje","23","Toegepaste Informatica"));
	studenten.add(new Student("Steegmans","Elke","16","Vroedkunde"));
	studenten.add(new Student("Van Hee","Jan","18","Verpleegkunde"));
}
public Student vindStudent(String naam){
	for (Student student : studenten){
		if (student.getNaam().equals(naam)){
			return student;
		}
	}
	return null;
}
public boolean verwijderStudent(String naam){
	try{
		Student student = vindStudent(naam);
		studenten.remove(student);
		return true;
	}
	catch (Exception E){
		return false;
	}
}
public ArrayList<Student> getAlleStudenten(){
	return studenten;
}
public void voegStudentToe(String naam, String voornaam, String leeftijd, String studierichting){
	if (uniek(naam)){
		studenten.add(new Student(naam, voornaam, leeftijd, studierichting));
	}
	else{
		
	}
}
public void voegStudentToe(Student student){
	studenten.add(student);
}
public String naamOk(String naam){
	if (naam.trim().isEmpty()){
		throw new IllegalArgumentException("Veld mag niet leeg zijn");
	}
	return naam;
}
public boolean uniek(String naam){
	for (Student student : studenten){
		if (student.getNaam().equals(naam)){
			return false;
		}
	}
	return true;
}
}

