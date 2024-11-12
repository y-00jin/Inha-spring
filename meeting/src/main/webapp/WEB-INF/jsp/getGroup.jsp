
<%@ page import="org.springframework.web.client.RestTemplate" %>
<%@ page import="com.obj.meeting.dto.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Grouop Information</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">

    <script type="text/javascript">
        $(document).ready(function() {

            var token = localStorage.getItem("authToken");
            if (!token) {
                window.location.href = "/login";
                return;
            }

            var id = new URLSearchParams(window.location.search).get('groupId');
            var apiUrl = "/group/get/" + id
            $("#deleteForm").attr("action", "/group/delete/" + id);

            // 정보 조회
            $.ajax({
                url: apiUrl,
                type: 'get',
                dataType: 'json',
                headers: {
                    'Authorization': 'Bearer ' + token
                },success: function(group) {
                    $('#groupId').val(group.groupId);
                    $('#groupName').val(group.groupName);
                    $('#groupManagerId').val(group.groupManagerId);
                    $('#groupDescription').val(group.groupDescription);
                    $('#groupImage').val(group.groupImage);
                    $('#meetingDate').val(group.meetingDate);
                    $('#meetingAddress').val(group.meetingAddress);
                    $('#participantCount').val(group.participantCount);
                },
                error: function(xhr, status, error) {
                    $('#groupInfo').after("<p class='text-danger'>모임정보 불러오기 오류 : " + error + "</p>");
                }
            });


            // 목록버튼
            $("#btn-list").on("click", function() {
                window.location.href = "/menu";
            });

            // update 버튼 클릭
            $("#btn-update").off("click").on("click", function() {
                var formData = $('#updateForm').serialize();  // 폼 데이터 가져오기
                $.ajax({
                    url: $('#updateForm').attr("action"),
                    type: "POST",
                    data: formData,
                    headers: {
                        "Authorization": "Bearer " + token
                    },
                    success: function(response) {
                        alert("UPDATE SUCCESS");
                    },
                    error: function(xhr, status, error) {
                        alert("UPDATE FAIL");
                    }
                });
            });

            // delete 버튼 클릭
            $("#btn-delete").off("click").on("click", function() {

                $.ajax({
                    url: $('#deleteForm').attr("action"),
                    type: "POST",
                    headers: {
                        "Authorization": "Bearer " + token
                    },
                    success: function(response) {
                        alert("DELETE SUCCESS");
                        window.location.href = "/menu";
                    },
                    error: function(xhr, status, error) {
                        alert("DELETE FAIL");
                    }
                });
            });
        });

    </script>
</head>
<body>
<div class="container mt-5">
    <h1 class='mb-3'>Group Information</h1>

    <form action='http://localhost:8080/group/update' method='post' class='mt-3' id="updateForm" >

        <div class='card'>
            <div class='card-body' id="groupInfo">
                <p class='card-text'>groupId:  <input type='text' class='form-control' id='groupId' name='groupId' value='' required readonly></p>
                <p class='card-text'>groupName: <input type='text' class='form-control' id='groupName' name='groupName' value='' ></p>
                <p class='card-text'>groupManagerId: <input type='text' class='form-control' id='groupManagerId' name='groupManagerId' value='' ></p>
                <p class='card-text'>groupDescription: <input type='text' class='form-control' id='groupDescription' name='groupDescription' value='' ></p>
                <p class='card-text'>groupImage: <input type='text' class='form-control' id='groupImage' name='groupImage' value='' ></p>
                <p class='card-text'>meetingDate: <input type='text' class='form-control' id='meetingDate' name='meetingDate' value='' ></p>
                <p class='card-text'>meetingAddress: <input type='text' class='form-control' id='meetingAddress' name='meetingAddress' value='' ></p>
                <p class='card-text'>participantCount: <input type='text' class='form-control' id='participantCount' name='participantCount' value='' ></p>
            </div>
        </div>

    </form>

    <form id="deleteForm" action="" method='post' class='mt-3' >

    </form>

    <div style="display: flex;gap:20px; margin:20px 0;">
        <button type="button" class="btn btn-primary" id="btn-list">목록</button>
        <button type='button' class='btn btn-primary' id="btn-update">수정</button>
        <button type='button' class='btn btn-primary' id="btn-delete">삭제</button>
    </div>

</div>
</body>
</html>

