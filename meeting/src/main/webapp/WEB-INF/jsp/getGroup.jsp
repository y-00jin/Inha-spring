
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="com.obj.meeting.dto.Group" %>
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
        String id = request.getParameter("id");
        String apiUrl = "http://localhost:8080/group/get/" + id;
        RestTemplate restTemplate = new RestTemplate();

        try {
            Group group = restTemplate.getForObject(apiUrl, Group.class);

            // form 태그로
            out.println("<h1 class='mb-3'>Group Information</h1>");
            out.println("<form action='http://localhost:8080/group/update' method='post' class='mt-3' >");

            out.println("<div class='card'>");
            out.println("<div class='card-body'>");
            out.println("<p class='card-text'>groupId:  <input type='text' class='form-control' id='groupId' name='groupId' value='"+ group.getGroupId()+"' required readonly></p>");
            out.println("<p class='card-text'>groupName: <input type='text' class='form-control' id='groupName' name='groupName' value='"+ group.getGroupName()+"' ></p>");
            out.println("<p class='card-text'>groupManagerId: <input type='text' class='form-control' id='groupManagerId' name='groupManagerId' value='"+ group.getGroupManagerId()+"' ></p>");
            out.println("<p class='card-text'>groupDescription: <input type='text' class='form-control' id='groupDescription' name='groupDescription' value='"+ group.getGroupDescription()+"' ></p>");
            out.println("<p class='card-text'>groupImage: <input type='text' class='form-control' id='groupImage' name='groupImage' value='"+ group.getGroupImage()+"' ></p>");
            out.println("<p class='card-text'>meetingDate: <input type='text' class='form-control' id='meetingDate' name='meetingDate' value='"+ group.getMeetingDate()+"' ></p>");
            out.println("<p class='card-text'>meetingAddress: <input type='text' class='form-control' id='meetingAddress' name='meetingAddress' value='"+ group.getMeetingAddress()+"' ></p>");
            out.println("<p class='card-text'>participantCount: <input type='text' class='form-control' id='participantCount' name='participantCount' value='"+ group.getParticipantCount()+"' ></p>");
            out.println("</div>");
            out.println("</div>");
            out.println("<button type='submit' class='btn btn-primary mt-3' >수정</button>");
            out.println("</form>");

            out.println("<form action='http://localhost:8080/group/delete/"+group.getGroupId() +"' method='post' class='mt-3' >");
            out.println("<button type='submit' class='btn btn-primary'>삭제</button>");
            out.println("</form>");

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

