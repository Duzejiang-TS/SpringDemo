<%--
  Created by IntelliJ IDEA.
  User: 旗云百里
  Date: 2018/7/27
  Time: 21:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>测试</title>
</head>
<body>
<h1>上传附件</h1>
<form method="post" action="/doUpload.do" enctype="multipart/form-data">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>
</body>
</html>
