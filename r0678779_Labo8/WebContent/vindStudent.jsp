<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.ArrayList"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/stylesheet.css">
<title>Vind student</title>
</head>
<body>
<h1>Vind student</h1>
<form method="GET" action="StudentInfo?action=find">
	<p class="<%= request.getAttribute("naamClass") == null ? "" : request.getAttribute("naamClass") %>"><label for="naam">Naam: <input type="text" name="naam" id="naam" title="Voer de naam in" value="<%= request.getAttribute("naamPreviousValue") == null ? "" : request.getAttribute("naamPreviousValue")%>"></label></p>
	<p class="<%= request.getAttribute("voorNaamClass") == null ? "" : request.getAttribute("voorNaamClass")%>"><label for="voornaam">Voornaam: <input type="text" name="voornaam" id="voornaam" title="Voer de voornaam in" value="<%= request.getAttribute("voorNaamPreviousValue") == null ? "" : request.getAttribute("voorNaamPreviousValue")%>"></label></p>
	<input type="submit" value="Verstuur" id="verstuur">
</form>
<% ArrayList<String> result = (ArrayList<String>) request.getAttribute("result");
if (result != null){
%>
<ul>
	<% for (String error : result){ %>
		<li><%=error%></li>
	<%} %>	
</ul>
<% } %>
</body>
</html>