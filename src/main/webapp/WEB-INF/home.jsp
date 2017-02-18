<%@ page import="io.muic.ooc.webapp.User" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>
<% int id = (int)request.getSession().getAttribute("session");%>
<% User user = User.userFromID(id);%>
<h2>All Users</h2>
<fieldset>
    <legend>Your Info</legend>
    <p>Username: <%=user.getUsername()%></p>
    <p>Firstname: <%=user.getFirstName()%></p>
    <p>Lastname: <%=user.getLastName()%></p>
</fieldset>
<p>${error}</p>
<% HashMap<Integer, User> userHashMap = User.getUserMap(); %>
<fieldset>
    <legend>USERS</legend>
<table border="1">
    <tr>
        <th>Username</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Actions</th>
    </tr>
<% for (Map.Entry<Integer, User> entry: userHashMap.entrySet()) {%>
    <tr>
        <td><%=entry.getValue().getUsername() %></td>
        <td><%=entry.getValue().getFirstName() %></td>
        <td><%=entry.getValue().getLastName() %></td>
        <td>
            <form method="post" action="/">
                <input type="hidden" name="id" value="<%=entry.getKey()%>"/>
                <input type="submit" name="action" value="Edit"/>
                <% if (id != entry.getKey()){%>
                <input type="submit" name="action" value="Remove"/>
                <%}%>
            </form>
        </td>
    </tr>
<%}%>
</table>
</fieldset>
<form method="post" action="/">
    <input type="submit" name="action" value="Add"/>
    <input type="submit" name="action" value="Logout" />
</form>
</body>
</html>
