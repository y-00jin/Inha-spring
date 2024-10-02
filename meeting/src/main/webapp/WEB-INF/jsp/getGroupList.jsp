<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="java.util.Arrays" %>
<%@ page import="com.obj.meeting.dto.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h1>모임 목록</h1>
    <%
        String apiUrl = "http://localhost:8080/group/list";
        RestTemplate restTemplate = new RestTemplate();
        try {
            // RestTemplate을 사용하여 사용자 목록 데이터를 가져옴
            Group[] groups = restTemplate.getForObject(apiUrl, Group[].class);
            List<Group> groupList = Arrays.asList(groups); // 배열을 리스트로 변환

            out.print("<table class='table table-striped'>");
            out.print("<thead class='thead-dark'>");
            out.print("<tr>");
            out.print("<th>모임 ID</th>");
            out.print("<th>모잉 명</th>");
            out.print("<th>모임 날짜</th>");
            out.print("<th>모임 장소</th>");
            out.print("<th>참여자 수</th>");
            out.print("</tr>");
            out.print("</thead>");
            out.print("<tbody>");
            for(Group group : groupList) {
                out.print("<tr>");
                out.print("<td><a href='getGroup?id=" + group.getGroupId() + "'>" + group.getGroupId() + "</a></td>");
                out.print("<td>" + group.getGroupName() + "</td>");
                out.print("<td>" + group.getMeetingDate() + "</td>");
                out.print("<td>" + group.getMeetingAddress() + "</td>");
                out.print("<td>" + group.getParticipantCount() + "</td>");
                out.print("</tr>");
            }
            out.print("</tbody>");
            out.print("</table>");
        } catch (Exception e) {
            out.println("<p class='text-danger'>Error fetching user list</p>");
        }
    %>
</div>
<!-- 부트스트랩 JS 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

