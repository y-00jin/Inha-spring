<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    RestTemplate restTemplate = new RestTemplate();
    try {
        User[] users = restTemplate.getForObject("http://localhost:8080/user/list", User[].class);
        List<User> userList = Arrays.asList(users);
        for (User user : userList) {
            out.println("<a href='getUser.jsp?id=" + user.getId() + "' >" + user.getId() + "</a>");
            out.println(user.getUsername() + " " + user.getEmail() + "<p>");
        }

    } catch (Exception e) {
        out.println("Error getUserList()");
    }

%>
</body>
</html>
