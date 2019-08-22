<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-21
  Time: 17:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index 43</title>
</head>
<body>
<c:set var="someName" value="Witaj w coderslab."/>
<c:if test="${someName.contains(\"coderslab\")}" >
    <p>OK</p>
</c:if>
</body>
</html>
