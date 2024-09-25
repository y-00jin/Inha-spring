<%--
  User: y00jin
  Date: 9/17/24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form action="http://localhost:8080/group/save" method="post">

    모임명 : <input type="text" name="groupName" /><br>
    모임설명 : <input type="text" name="groupDescription" /><br>
    모임이미지 : <input type="text" name="groupImage" /><br>
    모임장소 : <input type="text" name="meetingAddress" /><br>
    <input type="submit" value="Save Group" />
</form>
</body>
</html>
