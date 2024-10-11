<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="org.obj.test20240911.entity.User" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR" pageEncoding="EUC-KR" %>


<%
    URL url = new URL("http://localhost:8080/user/get");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));    // Con을 통해 읽어옴
    String line;

    StringBuffer result = new StringBuffer();
    while ((line = in.readLine()) != null) {
        result.append(line);    // 한줄씩 읽어옴
    }
    in.close();

    Gson gson = new Gson();
    User user = gson.fromJson(result.toString(), User.class);   // fromJson (결과값, 결과값타입)
    out.println("ID : " + user.getId() + "<p>");
    out.println("Username : " + user.getUsername() + "<p>");
    out.println("Password : " + user.getPassword() + "<p>");
    out.println("eMail : " + user.getEmail() + "<p>");
    out.println("Role : " + user.getRole() + "<p>");


%>

