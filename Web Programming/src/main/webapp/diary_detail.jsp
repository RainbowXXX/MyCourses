<%--
  Created by IntelliJ IDEA.
  User: yan32
  Date: 2024/4/30
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@
        page
        contentType="text/html;charset=UTF-8"
        language="java"
        import="java.sql.*"
        import="site.rainbowx.demo.SqlHandler"
%>

<%
    int diary_id = Integer.parseInt(request.getParameter("id"));
    PreparedStatement preparedStatement = SqlHandler.connection.prepareStatement(" SELECT title, content, write_data\nFROM diary\nWHERE id = ?");
    preparedStatement.setInt(1, diary_id);
    ResultSet resultSet = preparedStatement.executeQuery();

    String title = "untitled", content = "", write_data = "";
    if(resultSet.next()) {
        title = resultSet.getString("title");
        content = resultSet.getString("content");
        write_data = resultSet.getString("write_data");
    }
    else {
        response.sendRedirect("/");
    }
%>
<html>
<head>
    <title><%=title%></title>
</head>
<body>
<%=title%>
<br>
<%=content%>
<br>
<%=write_data%>
</body>
</html>
