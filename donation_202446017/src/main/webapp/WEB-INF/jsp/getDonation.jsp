<%@ page import="org.springframework.security.core.userdetails.UserDetails" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>기부 정보</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {

            var donationId = new URLSearchParams(window.location.search).get('id');
            var donationListUrl = "/donation/get/" + donationId;
            var participantsUrl = "/donation/get/" + donationId + "/donors";
            var joinUrl = "/donation/join/" + donationId;
            var token = localStorage.getItem("authToken");

            var loginUsername = localStorage.getItem("username");


            var isCreator = false;

            console.log('login' + loginUsername);

            function fetchDonationDetails() {
                $.ajax({
                    url: donationListUrl,
                    type: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + token
                    },
                    success: function(donation) {
                        var donationHtml = "<h1 class='mb-3'>기부정보</h1>" +
                            "<div class='card'>" +
                            "<div class='card-body'>" +
                            "<h5 class='card-title'>기부 ID: " + donation.id + "</h5>" +
                            "<p class='card-text'>기부 명: " + donation.name + "</p>" +
                            "<p class='card-text'>기부 개설자: " + donation.creator + "</p>" +
                            "<p class='card-text'>기부 설명: " + donation.description + "</p>" +
                            "<p class='card-text'>기부 금액: " + donation.donationAmount + "</p>" +
                            "<p class='card-text'>기부 총액: " + donation.totalAmount + "</p>" +
                            "<p class='card-text'>기부 마감일자: " + donation.dueDate + "</p>" +
                            "</div></div>";


                        $('.container').html(donationHtml);
                        joinDonation();           // 기부참여
                        if(loginUsername == donation.creator){  // 기부개설자가 본인
                            isCreator = true;
                            fetchDonors();   // 기부참여자 리스트
                        }
                    },
                    error: function() {
                        $('.container').html("<p class='text-danger'>기부정보 불러오기 오류</p>");
                    }
                });
            }

            // 기부 참여자 리스트
            function fetchDonors() {
                $.ajax({
                    url: participantsUrl,
                    type: 'GET',
                    headers: {
                        'Authorization': 'Bearer ' + token
                    },
                    success: function(participants) {
                        var donorsDiv = $('#donorsDiv');
                        if (donorsDiv.length === 0) {
                            donorsDiv = $("<div id='donorsDiv'><h2 class='mt-4'>기부 참여자</h2><ul class='list-group'></ul></div>");
                            $('.container').append(donorsDiv);
                        } else {
                            donorsDiv.find('.list-group').empty();
                        }

                        var participantsList = donorsDiv.find('.list-group');
                        participants.forEach(function(participant) {
                            participantsList.append("<li class='list-group-item'>" + participant.username + " (" + participant.email + ")</li>");
                        });
                    },
                    error: function() {
                        var donorsDiv = $('#donorsDiv');
                        if (donorsDiv.length === 0) {
                            donorsDiv = $("<div id='donorsDiv'><p class='text-danger'>기부참여자 불러오기 오류</p></div>");
                            $('.container').append(donorsDiv);
                        } else {
                            donorsDiv.html("<p class='text-danger'>기부참여자 불러오기 오류</p>");
                        }
                    }
                });
            }

            // 기부 참여
            function joinDonation() {
                var joinFormHtml = "<br><form id='joinDonationForm'>" +
                    "<button type='submit' class='btn btn-primary'>기부참여하기</button></form>";
                $('.container').append(joinFormHtml);

                $('#joinDonationForm').on('submit', function(e) {
                    e.preventDefault();

                    $.ajax({
                        url: joinUrl,
                        type: 'POST',
                        headers: {
                            'Authorization': 'Bearer ' + token
                        },
                        data: { username: localStorage.getItem('username') },
                        success: function() {
                            alert('성공적으로 기부에 참여했습니다.');
                            fetchDonationDetails();

                        },
                        error: function(xhr, status, error) {
                            alert('기부 참여에 실패했습니다: ' + xhr.responseText);
                        }
                    });
                });
            }

            fetchDonationDetails();
        });
    </script>
</head>
<body>
<div class="container mt-5">
</div>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

