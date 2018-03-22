<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<% if ((String) request.getAttribute("delete") == null){
	RequestDispatcher view = request.getRequestDispatcher("index.html");
	view.forward(request, response);
}
	%>

<title>Verwijder bevestiging</title>
</head>
<h1>Bevestig verwijdering</h1>
<body>
<h1>Bevestig verwijdering</h1>
<form method="POST" action="StudentInfo?action=verwijderBevestiging&naam=<%= request.getParameter("naam")%>&voornaam=<%= request.getParameter("voornaam")%>">
	<p id ="bevestiging">Bent u zeker dat u <%= request.getParameter("voornaam") + " " + request.getParameter("naam") %> wilt verwijderen?</p>
	<input type="submit" name="delete" value="Ja">
</form>
<a href="StudentInfo?action=read">Neen, breng mij terug naar overzicht</a>
</body>
</html>