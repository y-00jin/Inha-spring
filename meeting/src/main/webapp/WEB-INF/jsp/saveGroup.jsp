<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.obj.meeting.dto.MeetingUserDetails" %>
<%@ page import="org.springframework.security.core.userdetails.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save New User</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>


    <script type="text/javascript">
        $(document).ready(function () {

            var token = localStorage.getItem("authToken");
            if (!token) {
                window.location.href = "/login";
                return;
            }

            // save 버튼 클릭
            $("#btn-save").off("click").on("click", function () {
                var formData = $('#saveForm').serialize();  // 폼 데이터 가져오기
                $.ajax({
                    url: $('#saveForm').attr("action"),
                    type: "POST",
                    data: formData,
                    headers: {
                        "Authorization": "Bearer " + token
                    },
                    success: function (response) {
                        alert("SAVE SUCCESS");
                        window.location.href = "/menu";
                    },
                    error: function (xhr, status, error) {
                        alert("SAVE FAIL");
                    }
                });
            });


        });
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>Save New Group</h2>
    <form action="http://localhost:8080/group/save" method="post" class="mt-3" id="saveForm">
        <div class="form-group">
            <label for="groupName">모임 명:</label>
            <input type="text" class="form-control" id="groupName" name="groupName" required>
        </div>
        <div class="form-group">
            <label for="groupManagerId">모임 방장:</label>
                <%
                    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                    User user = (User) principal;
                    String username = user.getUsername();
                    String role = user.getAuthorities().iterator().next().getAuthority().replace("ROLE_", "");

                %>
                <input type="text" class="form-control" id="groupManagerId" name="groupManagerId" value="<%=username%> ( <%= role %> )" readonly >

        </div>
        <div class="form-group">
            <label for="groupDescription">모임 설명:</label>
            <input type="text" class="form-control" id="groupImage" name="groupImage">
        </div>
        <div class="form-group">
            <label for="groupImage">모임 이미지:</label>
            <input type="text" class="form-control" id="groupDescription" name="groupDescription">
        </div>
        <div class="form-group">
            <label for="meetingDate">모임 날짜:</label>
            <input type="text" class="form-control" id="meetingDate" name="meetingDate">
        </div>
        <div class="form-group">
            <label for="meetingAddress">모임 장소:</label>
            <input type="text" class="form-control" id="meetingAddress" name="meetingAddress">
        </div>
        <div class="form-group">
            <label for="participantCount">참여자 수:</label>
            <input type="text" class="form-control" id="participantCount" name="participantCount" value="0">
        </div>
        <button type="button" id="btn-save" class="btn btn-primary">Save Group</button>
    </form>
</div>
</body>
</html>
