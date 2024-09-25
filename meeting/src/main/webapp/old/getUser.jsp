<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="com.obj.meeting.dto.User" %><%--
  User: y00jin
  Date: 9/25/24
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    String id = request.getParameter("id");
    RestTemplate restTemplate = new RestTemplate();
    User user = restTemplate.getForObject("http://localhost:8080/user/get/" + id , User.class);
%>

<%= user.getId()%> <p/>
<%= user.getEmail()%> <p/>
<%= user.getEmail()%> <p/>
<%= user.getRole()%> <p/>

</body>
</html>
