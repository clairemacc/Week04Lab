<%-- 
    Document   : viewNote
    Created on : 1-Oct-2020, 1:21:54 PM
    Author     : 822408
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>View Note</h2>
        <p><b>Title:</b> ${note.title}</p>
        <p><b>Contents:</b><br>
            ${note.contents}</p>
        
        <a href="note?edit">Edit</a>
    </body>
</html>
