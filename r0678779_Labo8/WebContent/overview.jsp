<%@ page language="java" contentType="text/html; charset=UTF-8"
import="domain.db.StudentDB, domain.model.Student, java.util.ArrayList"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Studenten overzicht</title>
</head>
<body>
<h1>Overzicht studenten</h1>
<%
StudentDB studentenDB;
if (request.getAttribute("studenten") == null){
RequestDispatcher view = request.getRequestDispatcher("index.html");
view.forward(request, response);
}
%>

<table>
<tr>
<th> Naam </th>
<th> Voornaam</th>
<th> Leeftijd</th>
<th> Studierichting</th>
</tr>
<% for (Student student : (ArrayList<Student>) request.getAttribute("studenten") ){ %>
<tr id="<%= student.getNaam() %>"> 
<td> <%= student.getNaam() %></td>
<td> <%= student.getVoornaam() %></td>
<td> <%= student.getLeeftijd() %></td>
<td> <%= student.getStudierichting() %></td>
<td> <a href="StudentInfo?action=delete&naam=<%= student.getNaam() %>&voornaam=<%= student.getVoornaam() %>">Delete</a></td>
<td> <a href="StudentInfo?action=update&naam=<%= student.getNaam() %>&voornaam=<%= student.getVoornaam() %>">Update</a></td>
</tr>
<% } %>


</table>

<p><a href="index.html">Homepagina</a></p>
</body>
</html>