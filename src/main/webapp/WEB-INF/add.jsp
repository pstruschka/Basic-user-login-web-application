<!DOCTYPE html>
<html>
<head>
    <meta http-equiv='Content-Type' content='text/html; charset=UTF-8'>
    <title>User Input Form</title>
</head>

<body>
<h2>Add:</h2>
<p>${error}</p>
<form method="post" action="/add">
    <fieldset>
        <legend>NewUser</legend>
        Username:<label>
            <input type="text" name="username"/>
        </label><br /><br />
        Password:<label>
            <input type="password" name="password"/>
        </label><br /><br />
        First name:<label>
        <input type="text" name="firstname"/>
            </label><br /><br />
        Last name:<label>
            <input type="text" name="lastname"/>
        </label><br /><br />
    </fieldset>
    <input type="submit" name="action" value="Submit" />
    <input type="submit" name="action" value="Cancel"/>
</form>
</body>
</html>