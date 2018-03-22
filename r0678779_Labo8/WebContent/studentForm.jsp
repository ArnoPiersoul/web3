<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Voeg student toe</title>
<link rel="stylesheet" href="css/stylesheet.css">
</head>
<body>
<h1>New Student</h1>
<form method="post" action="StudentInfo?action=add" novalidate>
	<% 
	ArrayList<String> result = (ArrayList<String>) request.getAttribute("result");
	if (result != null){
		
	%>
	<ul>
		<% for (String message : result){ %>
		<li><%=message%></li>
		<%} %>
	</ul>
	<%} %>
	<p class="<%=request.getAttribute("naamClass") == null ? "" : request.getAttribute("naamClass")%>"><label for="naam">Naam: <input type="text" name="naam" id="naam" title="Voer de naam in" value="<%=request.getAttribute("naamPreviousValue") == null ? "" : request.getAttribute("naamPreviousValue")%>" required></label></p>
	<p class="<%=request.getAttribute("voornaamClass") == null ? "" : request.getAttribute("voornaamClass")%>"><label for="voornaam">Voornaam: <input type="text" name="voornaam" id="voornaam" title="voer de voornaam in" value="<%=request.getAttribute("voornaamPreviousValue") == null ? "" : request.getAttribute("voornaamPreviousValue")%>" required></label></p>
	<p class="<%=request.getAttribute("leeftijdClass") == null ? "" : request.getAttribute("leeftijdClass")%>"><label for="leeftijd">Leeftijd: <input type="number" name="leeftijd" id="leeftijd" title="voer de leeftijd in" value="<%=request.getAttribute("leeftijdPreviousValue") == null ? "" : request.getAttribute("leeftijdPreviousValue")%>" required></label></p>
	<p class="<%=request.getAttribute("studierichtingClass") == null ? "" : request.getAttribute("studierichtingClass")%>"><label for="studierichting">Studierichting: <input type="text" name="studierichting" id="studierichting" title="voer de studierichting in" value="<%=request.getAttribute("studierichtingPreviousValue") == null ? "" : request.getAttribute("studierichtingPreviousValue")%>" required></label></p>
	<input type="submit" value="Verstuur" id="bewaar">
</form>
</body>
</html>