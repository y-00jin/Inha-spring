<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="java.util.List" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="java.util.Arrays" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <!-- 부트스트랩 CSS 추가 -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <script>
        $(document).ready(function() {
            var token = localStorage.getItem("authToken");
            if (!token) {
                window.location.href = "/login";
                return;
            }

            $.ajax({
                url: '/user/list',
                type: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token
                },
                success: function(users) {
                    var tableBody = $('#uesrTable tbody');
                    tableBody.empty();
                    users.forEach(function(user) {
                        var row = $('<tr></tr>');
                        row.append($('<td></td>').html('<a href="/getUser?username=' + user.username + '">' + user.username + '</a>'));
                        row.append($('<td></td>').text( user.email));
                        row.append($('<td></td>').text( user.role));
                        tableBody.append(row);
                    });
                },
                error: function(xhr, status, error) {
                    $('#groupTable').after("<p class='text-danger'>모임목록 불러오기 오류 : " + error + "</p>");
                }
            });
        });
    </script>



</head>
<body>
<div class="container mt-4">
    <h1>사용자 목록</h1>
    <table id="uesrTable" class='table table-striped'>
        <thead class='thead-dark'>
            <tr>
                <th>Username</th>
                <th>Email</th>
                <th>Role</th>
            </tr>
        </thead>
        <tbody>

        </tbody>
    </table>


<%--    <%--%>
<%--        String apiUrl = "http://localhost:8080/user/list";--%>
<%--        RestTemplate restTemplate = new RestTemplate();--%>
<%--        try {--%>
<%--            // RestTemplate을 사용하여 사용자 목록 데이터를 가져옴--%>
<%--            User[] users = restTemplate.getForObject(apiUrl, User[].class);--%>
<%--            List<User> userList = Arrays.asList(users); // 배열을 리스트로 변환--%>

<%--            out.print("<table class='table table-striped'>");--%>
<%--            out.print("<thead class='thead-dark'>");--%>
<%--            out.print("<tr>");--%>
<%--            out.print("<th>Username</th>");--%>
<%--            out.print("<th>Email</th>");--%>
<%--            out.print("<th>Role</th>");--%>
<%--            out.print("</tr>");--%>
<%--            out.print("</thead>");--%>
<%--            out.print("<tbody>");--%>
<%--            for(User user : userList) {--%>
<%--                out.print("<tr>");--%>
<%--                out.print("<td><a href='getUser?username=" + user.getUsername() + "'>" + user.getUsername() + "</a></td>");--%>
<%--                out.print("<td>" + user.getEmail() + "</td>");--%>
<%--                out.print("<td>" + user.getRole() + "</td>");--%>
<%--                out.print("</tr>");--%>
<%--            }--%>
<%--            out.print("</tbody>");--%>
<%--            out.print("</table>");--%>
<%--        } catch (Exception e) {--%>
<%--            out.println("<p class='text-danger'>Error fetching user list</p>");--%>
<%--        }--%>
<%--    %>--%>
</div>
</body>
</html>

