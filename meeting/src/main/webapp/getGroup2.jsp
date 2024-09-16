<%--
  User: y00jin
  Date: 9/17/24
--%>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.obj.meeting.entity.Group" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    URL url = new URL("http://localhost:8080/group/get?groupName=seminar");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");    // 전송방식
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    StringBuffer result = new StringBuffer();
    String line;

    while((line = in.readLine()) != null){
        result.append(line);
    }
    in.close();

    Gson gson = new Gson();
    Group group = gson.fromJson(result.toString(), Group.class);  // 변환할 값, 변환할 타입
    out.println(group.getGroupName() + "<p/>");
    out.println(group.getGroupDescription() + "<p/>");
    out.println(group.getGroupImage() + "<p/>");
    out.println(group.getGroupImage() + "<p/>");
    out.println(group.getMeetingAddress() + "<p/>");

%>

</body>
</html>
