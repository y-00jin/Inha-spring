<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Responsive Navbar with Iframe</title>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="#">메뉴</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link" href="/getUserList" target="contentFrame">사용자 리스트</a>
            <a class="nav-item nav-link" href="/saveUser" target="contentFrame">사용자 등록</a>
            <a class="nav-item nav-link" href="/getGroupList" target="contentFrame">모임 리스트</a>
            <a class="nav-item nav-link" href="/saveGroup" target="contentFrame">모임 등록</a>
<%--            <a class="nav-item nav-link" href="/logout" target="_top">로그아웃</a>--%>
            <form action="/logout" method="post" style="display: inline">
                <input type="hidden" name="_csrf" value="${_csrf.token}" >
                <a class="nav-item nav-link" href="#" onclick="this.parentNode.submit(); return false" >로그아웃</a>
            </form>
        </div>
    </div>
</nav>

<!-- 메인 콘텐츠를 로드할 iframe 정의 -->
<div style="padding: 20px; height: 90vh;">
    <iframe name="contentFrame" style="width: 100%; height: 100%; border: 0;"></iframe>
</div>

<!-- 부트스트랩 JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

