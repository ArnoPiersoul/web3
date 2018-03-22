package domain.model;

public class Student {
	private String naam;
	private String voornaam;
	private int leeftijd;
	private String studierichting;
	
public Student(){
}
public Student(String naam, String voornaam, String leeftijd, String studierichting){
	setNaam(naam);
	setVoornaam(voornaam);
	setLeeftijd(leeftijd);
	setStudierichting(studierichting);
}
public void setNaam(String naam){
	if (naam == null || naam.trim().isEmpty()){
		throw new IllegalArgumentException("De naam mag niet leeg zijn");
	}
	else if (!naam.matches("[a-zA-Z ]+")){
		throw new IllegalArgumentException("De naam mag enkel letters bevatten");
	}
	else{
		this.naam = naam;
	}
}
public void setVoornaam(String voornaam){
	if (voornaam == null || voornaam.trim().isEmpty()){
		throw new IllegalArgumentException("De voornaam mag niet leeg zijn");
	}
	else if (!voornaam.matches("[a-zA-Z]+")){
		throw new IllegalArgumentException("De voornaam mag enkel letters bevatten");
	}
	else{
		this.voornaam = voornaam;
	}
}
public void setLeeftijd(String leeftijd){
	int leeftijdd;
	if (leeftijd.trim().isEmpty()){
		throw new IllegalArgumentException("De leeftijd mag niet leeg zijn");
	}
	try{
		leeftijdd = Integer.parseInt(leeftijd);
	}
	catch (NumberFormatException exc){
		throw new IllegalArgumentException("De leeftijd moet uit cijfers bestaan");
	}
	if (leeftijdd < 0){
		throw new IllegalArgumentException("De leeftijd moet groter dan 0 zijn");
	}else{
	this.leeftijd = leeftijdd;
	}
}
public void setStudierichting(String studierichting){
	if (studierichting == null || studierichting.trim().isEmpty()){
		throw new IllegalArgumentException("De studierichting mag niet leeg zijn");
	}else{
		this.studierichting = studierichting;
	}
}
public String getNaam(){
	return this.naam;
}
public String getVoornaam(){
	return this.voornaam;
}
public int getLeeftijd(){
	return this.leeftijd;
}
public String getStudierichting(){
	return this.studierichting;
}
}
