<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기부 목록</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            var token = localStorage.getItem("authToken");
            if (!token) {
                alert("No token found. Redirecting to login page.");
                window.location.href = "/login";
                return;
            }

            var apiUrl = '/donation/list';
            $.ajax({
                url: apiUrl,
                type: 'GET',
                headers: {
                    'Authorization': 'Bearer ' + token
                },
                success: function(donations) {
                    var tableBody = $('#donationTable tbody');
                    tableBody.empty();
                    donations.forEach(function(donation) {
                        var row = $('<tr></tr>');
                        row.append($('<td></td>').html('<a href="#" onclick="loadPage(\'/getDonation?id=' + encodeURIComponent(donation.id) + '\'); return false;">' + donation.id + '</a>'));
                        row.append($('<td></td>').text(donation.name));
                        row.append($('<td></td>').text(donation.dueDate));
                        row.append($('<td></td>').text(donation.donorCount));
                        tableBody.append(row);
                    });
                },

                error: function(xhr, status, error) {
                    $('#donationTable').after("<p class='text-danger'>Error fetching donation list: " + error + "</p>");
                }
            });
        });
    </script>
</head>
<body>
<div class="container mt-4">
    <h1>기부목록</h1>
    <table id="donationTable" class="table table-striped">
        <thead class='thead-dark'>
        <tr>
            <th>기부 ID</th>
            <th>기부 명</th>
            <th>기부 마감일자</th>
            <th>기부자 수</th>
        </tr>
        </thead>
        <tbody>
        <!-- 기부 목록 자동 삽입 -->
        </tbody>
    </table>
</div>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>