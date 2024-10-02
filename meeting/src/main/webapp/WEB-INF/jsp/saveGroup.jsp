<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save New User</title>
    <!-- 부트스트랩 CSS 추가 -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <h2>Save New Group</h2>
    <form action="http://localhost:8080/group/save" method="post" class="mt-3" >
        <div class="form-group">
            <label for="groupName">groupName:</label>
            <input type="text" class="form-control" id="groupName" name="groupName" required>
        </div>
        <div class="form-group">
            <label for="groupManagerId">groupManagerId:</label>
            <input type="text" class="form-control" id="groupManagerId" name="groupManagerId" >
        </div>
        <div class="form-group">
            <label for="groupImage">groupImage:</label>
            <input type="text" class="form-control" id="groupDescription" name="groupDescription" >
        </div>
        <div class="form-group">
            <label for="groupDescription">groupDescription:</label>
            <input type="text" class="form-control" id="groupImage" name="groupImage" >
        </div>
        <div class="form-group">
            <label for="meetingDate">meetingDate:</label>
            <input type="text" class="form-control" id="meetingDate" name="meetingDate" >
        </div>
        <div class="form-group">
            <label for="meetingAddress">meetingAddress:</label>
            <input type="text" class="form-control" id="meetingAddress" name="meetingAddress" >
        </div>
        <div class="form-group">
            <label for="participantCount">participantCount:</label>
            <input type="text" class="form-control" id="participantCount" name="participantCount" >
        </div>
        <button type="submit" class="btn btn-primary">Save User</button>
    </form>
</div>
<!-- 부트스트랩 JS 추가 -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
