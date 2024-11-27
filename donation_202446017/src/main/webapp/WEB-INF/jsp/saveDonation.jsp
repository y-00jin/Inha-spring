<%@ page import="com.obj.meeting.dto.User" %>
<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기부 등록</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        let map;
        let marker;
        let geocoder;

        

        $(document).ready(function() {
            
            $('#donationForm').on('submit', function(e) {
                e.preventDefault();
                var formData = new FormData(this);
                $.ajax({
                    url: '/donation/save',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    headers: {
                        'Authorization': 'Bearer ' + localStorage.getItem('authToken')
                    },
                    success: function(response) {
                        alert('기부 등록에 성공했습니다.');
                    },
                    error: function(xhr, status, error) {
                        alert('기부 등록에 실패했습니다: ' + error);
                    }
                });
            });
        });
    </script>
</head>
<body>
<div class="container mt-4">
    <h2>기부 등록</h2>
    <form id="donationForm" method="post" class="mt-3" enctype="multipart/form-data">
        <div class="form-group">
            <label for="name">기부 명:</label>
            <input type="text" class="form-control" id="name" name="name" required>
        </div>
        <div class="form-group">
            <%
                Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                String username = ((UserDetails)principal).getUsername();
            %>
            <label for="creator">개설자:</label>
            <input type="text" class="form-control" id="creator" name="creator" value="<%=username%>" required readonly>
        </div>
        <div class="form-group">
            <label for="description">기부 설명:</label>
            <input type="text" class="form-control" id="description" name="description" required>
        </div>
        <div class="form-group">
            <label for="donationAmount">기부 금액(1회):</label>
            <input type="text" class="form-control" id="donationAmount" name="donationAmount" required>
        </div>
        <div class="form-group">
            <label for="dueDate">기부 마감일자:</label>
            <input type="date" class="form-control" id="dueDate" name="dueDate" required>
        </div>
        <div id="map" style="height: 400px;"></div>
        <button type="submit" class="btn btn-primary">기부 등록</button>
    </form>
</div>
</body>
</html>
