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
<h2>Edit:</h2>
<fieldset>
    <legend>Target</legend>
    <p>Username: <%=user.getUsername()%></p>
    <p>Firstname: <%=user.getFirstName()%></p>
    <p>Lastname: <%=user.getLastName()%></p>
</fieldset>
<p>${error}</p>
<form method="post" action="/edit">
    <fieldset>
        <legend>Edit</legend>
        Username:<label>
        <input type="text" name="username"/>
    </label><br /><br />
        First name:<label>
        <input type="text" name="firstname"/>
    </label><br /><br />
        Last name:<label>
        <input type="text" name="lastname"/>
    </label><br /><br />
    </fieldset>
    <input type="hidden" name="id" value="<%=id%>"/>
    <input type="submit" name="action" value="Submit" />
    <input type="submit" name="action" value="Cancel">
</form>
</body>
</html>