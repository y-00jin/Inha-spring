
<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Information</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container mt-5">
    <%
        String id = request.getParameter("username");
        String apiUrl = "http://localhost:8080/user/get/" + id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            User user = restTemplate.getForObject(apiUrl, User.class);

            out.println("<h1 class='mb-3'>User Information</h1>");
            out.println("<div class='card'>");
            out.println("<div class='card-body'>");
            out.println("<p class='card-text'>Username: " + user.getUsername() + "</p>");
            out.println("<p class='card-text'>Email: " + user.getEmail() + "</p>");
            out.println("<p class='card-text'>Role: " + user.getRole() + "</p>");
            out.println("</div>");
            out.println("</div>");
        } catch (Exception e) {
            out.println("<p class='text-danger'>Error fetching user information</p>");
        }
    %>
</div>
<!-- 부트스트랩 JS 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

