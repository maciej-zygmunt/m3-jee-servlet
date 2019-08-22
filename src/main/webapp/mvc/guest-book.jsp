<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-22
  Time: 15:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Guest Book</title>
</head>
<body>
<h1>Guest book</h1>
<h2>Subscribe to the guest book</h2>
<form action="guest-book" method="post">
    <table border="2">
        <thead>
        <td>
            <label for="name">Name</label>
        </td>
        <td>
            <label for="description">description</label>
        </td>
        </thead>
        <tr>
            <td>
                <input type="text" name="name" id="name">
            </td>
            <td>
                <input type="text" name="description" id="description">
            </td>

        </tr>
    </table>
    <button type="submit">Submit</button>
</form>
<h2>Guest list</h2>
<table border="1">
    <thead>
    <td>Name</td>
    <td>Description</td>
    </thead>
    <c:forEach items="${guests}" var="guest">
        <tr>
            <td>${guest.name}</td>
            <td>${guest.description}</td>
        </tr>
    </c:forEach>
    <tr></tr>
</table>

</body>
</html>
