<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="com.obj.meeting.dto.MeetingUserDetails" %>
<%@ page import="java.util.Collection" %>
<%@ page import="org.springframework.security.core.GrantedAuthority" %>
<%@ page import="java.util.Iterator" %>
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
            <label for="groupName">모임 명:</label>
            <input type="text" class="form-control" id="groupName" name="groupName" required>
        </div>
        <div class="form-group">
            <label for="groupManagerId">모임 방장:</label>
            <%
                Object principal =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();    // 사용자 정보 가져와서
                String username = ((MeetingUserDetails)principal).getUsername();    // MEetingUserDetails로 파싱

                // 컬렉션 형태로 권한 조회
//                Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
//                Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
//                GrantedAuthority grantedAuthority = iterator.next();    // 하나만 조회
//                String role = grantedAuthority.getAuthority();  // 권한 조회

                String role = SecurityContextHolder.getContext().getAuthentication().getAuthorities().iterator().next().getAuthority();
            %>
            <input type="text" class="form-control" id="groupManagerId" name="groupManagerId" value="<%=username%> ( <%= role.replace("ROLE_", "")%> )" readonly >
        </div>
        <div class="form-group">
            <label for="groupDescription">모임 설명:</label>
            <input type="text" class="form-control" id="groupImage" name="groupImage" >
        </div>
        <div class="form-group">
            <label for="groupImage">모임 이미지:</label>
            <input type="text" class="form-control" id="groupDescription" name="groupDescription" >
        </div>
        <div class="form-group">
            <label for="meetingDate">모임 날짜:</label>
            <input type="text" class="form-control" id="meetingDate" name="meetingDate" >
        </div>
        <div class="form-group">
            <label for="meetingAddress">모임 장소:</label>
            <input type="text" class="form-control" id="meetingAddress" name="meetingAddress" >
        </div>
        <div class="form-group">
            <label for="participantCount">참여자 수:</label>
            <input type="text" class="form-control" id="participantCount" name="participantCount" value="0">
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
