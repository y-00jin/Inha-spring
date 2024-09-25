
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>사용자 등록</h1>
<form action="/user/save" method="post">
    Username : <input type="text" name="username"><p/>
    Password : <input type="text" name="password"><p/>
    Email : <input type="text" name="email"><p/>
    Role : <input type="text" name="role"><p/>
    <input type="submit" value="save"/>
</form>

</body>
</html>
