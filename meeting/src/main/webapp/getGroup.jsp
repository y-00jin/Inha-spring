<%--
  User: y00jin
  Date: 9/17/24
--%>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    URL url = new URL("http://localhost:8080/group/get");
    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    con.setRequestMethod("GET");

    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    StringBuffer result = new StringBuffer();
    String line;

    while((line=in.readLine()) !=null){
        result.append(line);
    }
    in.close();

    out.println("Result : " +result.toString());
%>
</body>
</html>
