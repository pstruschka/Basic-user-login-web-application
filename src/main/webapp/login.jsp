<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>User Input Form</title>
</head>

<body>
<h2>Login:</h2>
<form method="post" action="login">
    <fieldset>
        <legend>Login</legend>
        Login:
        <label>
            <input type="text" name="username"/>
        </label><br /><br />
        Password:<label>
            <input type="password" name="password"/>
        </label><br /><br />
    </fieldset>
    <% %>
    <!--<input type="hidden" name="secret" value="888" />-->
    <input type="submit" value="SEND" />
</form>
</body>
</html>