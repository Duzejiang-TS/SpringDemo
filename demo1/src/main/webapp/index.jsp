<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <style type="text/css">
        form{
            margin:0px auto;

            width:500px;
            padding:20px;
        }
    </style>
    <title></title>
</head>

<body>
<h1>SSM整合</h1>
<form action="${pageContext.request.contextPath }/userAction.do" method="post">
    用户名：<input name="name"/> <br/>
    用户年龄<input name="age"/><br/>
    <input type="submit" value="save"/>
</form>
</body>
</html>
