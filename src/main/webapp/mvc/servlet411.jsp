<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-21
  Time: 17:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Servlet 411 view</title>
</head>
<body>
<h1>${greeting}</h1>
<form method="post" action="servlet412">
    <label for="language-select">Select language</label>
    <select id="language-select" name="language">
        <c:forEach var="langI" items="${lang}">
            <option>${langI}</option>
        </c:forEach>

    </select>
    <button type="submit">Submit</button>

</form>

</body>
</html>
