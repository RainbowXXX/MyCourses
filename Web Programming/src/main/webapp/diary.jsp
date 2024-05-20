<%--
  Created by IntelliJ IDEA.
  User: yan32
  Date: 2024/4/30
  Time: 10:55
  To change this template use File | Settings | File Templates.
--%>
<%@
        page
        contentType="text/html;charset=UTF-8"
        language="java"
        import="java.sql.*"
        import="java.util.List"
        import="java.util.ArrayList"
        import="site.rainbowx.demo.Diary"
        import="site.rainbowx.demo.SqlHandler"
%>

<%
    List<Diary> diaryList = new ArrayList<>();
    PreparedStatement preparedStatement = SqlHandler.connection.prepareStatement("SELECT id, title, content, write_data\nFROM diary");
    ResultSet resultSet = preparedStatement.executeQuery();

    while(resultSet.next()) {
        diaryList.add(new Diary(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("content"), resultSet.getString("write_data")));
    }
%>

<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    for(Diary diary : diaryList) {
        out.println("<a href=\"./diary_detail.jsp?id=%s\">%s</a>".formatted(diary.getId(), diary.getTitle()));
        out.println("<br>");
    }
%>
</body>
</html>
