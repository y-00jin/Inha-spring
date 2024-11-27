
<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Information</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

    <script type="text/javascript">
        $(document).ready(function() {

            var token = localStorage.getItem("authToken");
            if (!token) {
                window.location.href = "/login";
                return;
            }

            var id = new URLSearchParams(window.location.search).get('username');
            var apiUrl = "/user/get/" + id

            // 정보 조회
            $.ajax({
                url: apiUrl,
                type: 'get',
                dataType: 'json',
                headers: {
                    'Authorization': 'Bearer ' + token
                }, success: function (user) {
                    $('#username').text(user.username);
                    $('#email').text(user.email);
                    $('#role').text(user.role);
                },
                error: function (xhr, status, error) {
                    $('#userInfo').after("<p class='text-danger'>사용자 정보 불러오기 오류 : " + error + "</p>");
                }
            });
        });

    </script>

</head>
<body>
<div class="container mt-5">
    <h1 class='mb-3'>User Information</h1>
    <div class='card'>
        <div class='card-body' id="userInfo">
            <p class='card-text'>Username : <span id="username"></span></p>
            <p class='card-text'>Email : <span id="email"></span></p>
            <p class='card-text'>Role : <span id="role"></span></p>
        </div>
    </div>
    <div>
        <button type="button" class="btn btn-primary mt-3" onclick="window.location.href='/menu'">목록</button>
    </div>
</div>
</body>
</html>

