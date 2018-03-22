<%@ page language="java" contentType="text/html; charset=UTF-8" import="domain.model.Student"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="nl">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gevonden</title>
</head>
<body>
<p id="boodschap">Je vroeg naar volgende gegevens: <%= request.getParameter("naam") + " " + request.getParameter("voornaam") + " (" + request.getAttribute("leeftijd") + " jaar): " + request.getAttribute("studierichting")%> </p>
</body>
</html>