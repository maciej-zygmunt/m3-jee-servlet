<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-21
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%--
W projekcie stwórz stronę jsp index42.jsp. Za pomocą pętli forEach wyświetl na stronie liczby w taki sposób by uzyskać poniższy wynik. Wykorzystaj dodatkowe atrybuty pętli begin oraz step
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Index 42</title>
</head>
<body>
<ul>
<c:forEach begin="2" end="10" step="2" var="num" >
    <li>${num}</li>
</c:forEach>
</ul>
</body>
</html>
