<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="http://localhost:8080/user/save" method="post">
    ID : <input type="text" name="id" value=""/> <p/>
    Username : <input type="text" name="username" value=""/> <p/>
    Password : <input type="text" name="password" value=""/> <p/>
    eMail : <input type="text" name="email" value=""/> <p/>
    Role : <input type="text" name="role" value=""/> <p/>

    <input type="submit" value="save"/>
</form>

</body>
</html>

