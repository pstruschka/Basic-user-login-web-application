<%@ page import="io.muic.ooc.webapp.User" %>
<%@ page import="io.muic.ooc.webapp.servlet.EditServlet" %>
<%@ page import="io.muic.ooc.webapp.servlet.HomeServlet" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>User Input Form</title>
</head>
<% int id = HomeServlet.targetId;%>
<% User user = User.userFromID(id);%>

<body>
<h2>Delete:</h2>
<fieldset>
    <legend>Target</legend>
    <p>Username: <%=user.getUsername()%></p>
    <p>Firstname: <%=user.getFirstName()%></p>
    <p>Lastname: <%=user.getLastName()%></p>
</fieldset>
<p>${error}</p>
<p>Confirm?</p>
<form method="post" action="/confirm">
    <input type="hidden" name="id" value="<%=id%>"/>
    <input type="submit" name="action" value="Yes"/>
    <input type="submit" name="action" value="No"/>
</form>
</body>
</html>