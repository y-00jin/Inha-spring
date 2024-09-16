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
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="java.lang.reflect.Type" %>
<%@ page import="com.google.gson.reflect.TypeToken" %>
<%@ page import="com.google.gson.GsonBuilder" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    URL url = new URL("http://localhost:8080/group/list");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    StringBuffer result = new StringBuffer();
    String line;
    while((line = in.readLine()) != null){
        result.append(line);
    }

    //Gson gson = new Gson();
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create(); // 날짜 형태 지정
    Type groupListType = new TypeToken<ArrayList<Group>>(){}.getType(); // ArrayList 객체 안에 있는 Group 타입을 얻어옴
    List<Group> groupList = gson.fromJson(result.toString(), groupListType);

    out.println("<ul>");
    for(Group group : groupList){
        out.println("<li>");
            out.println("모임명 : " + group.getGroupName() +"<br>");
            out.println("모임설명 : " + group.getGroupDescription() +"<br>");
            out.println("모임이미지 : " + group.getGroupImage() +"<br>");
            out.println("모임날짜 : " + group.getMeetingDate() +"<br>");
            out.println("모임장소 : " + group.getMeetingAddress() +"<br>");
        out.println("</li>");
    }
    out.println("</ul>");

%>
</body>
</html>
