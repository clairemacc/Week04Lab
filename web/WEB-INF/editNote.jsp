<%-- 
    Document   : editNote
    Created on : 1-Oct-2020, 1:22:03 PM
    Author     : 822408
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Edit Note</title>
    </head>
    <body>
        <h1>Simple Note Keeper</h1>
        <h2>Edit Note</h2>
        
        <form method="post" action="note">
            <table>
                <tr>
                    <td>Title: </td>
                    <td><input type="text" name="editTitle" value="${note.title}"></td>
                </tr>
                <tr>
                    <td>Contents: </td>
                    <td><textarea>${note.contents}</textarea></td>
                </tr>
            </table>
            <input type="submit" value="Save">
        </form>
        
    </body>
</html>
