<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-22
  Time: 08:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Newsletter</title>
</head>
<body>
<p>
    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus ac sodales eros, in lobortis purus. Orci varius
    natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Cras aliquet nisi ex, ut facilisis eros
    ullamcorper semper. Integer pulvinar sapien quis dolor bibendum, quis rhoncus turpis venenatis. In tincidunt lorem a
    feugiat blandit. Donec non ex est. Aliquam finibus consequat ligula, ac malesuada arcu.
</p>
<p>
    Quisque non nibh sit amet dolor bibendum sollicitudin non ultrices purus. Sed turpis est, efficitur sit amet orci
    vel, dapibus congue nisi. Nullam convallis mattis lacus, et feugiat arcu efficitur a. Etiam in eros nec mauris
    tincidunt convallis sed a eros. Aliquam lacus ligula, pretium a erat ut, dapibus placerat arcu. Vestibulum massa
    erat, posuere nec congue quis, consequat nec leo. Phasellus congue orci tellus, sit amet pulvinar ex molestie
    lobortis. Phasellus orci sem, fermentum auctor augue at, interdum finibus eros.
</p>
<p>
    Etiam malesuada efficitur suscipit. Morbi rutrum tempus elit ut pulvinar. Sed molestie molestie ipsum, non euismod
    enim varius quis. In ullamcorper posuere tincidunt. Curabitur eget neque eget nisl pharetra maximus vitae vitae
    quam. Phasellus eros massa, tempor vitae ante eget, feugiat lacinia turpis. Sed sit amet pulvinar elit. Praesent at
    odio nec nisl aliquam vulputate in vitae sem. Integer vehicula tortor auctor, iaculis augue in, aliquet augue. Nulla
    id volutpat mauris. Suspendisse sed placerat arcu, sed volutpat orci. Ut sed auctor eros. Ut aliquet, tellus ut
    aliquet congue, quam ex eleifend purus, nec viverra ligula nulla ac ante. Cras ultricies purus et molestie tempus.
    Pellentesque ut ligula interdum, cursus tortor eget, mattis diam.
</p>
<p>
    Etiam pharetra egestas efficitur. Nullam id velit gravida, mollis neque nec, laoreet ligula. Maecenas vitae
    convallis erat. Fusce pulvinar odio ut mauris interdum vestibulum. Vestibulum dolor arcu, convallis ut mattis id,
    tincidunt vitae tellus. Praesent quis finibus orci. In non aliquet magna. In vitae tristique lectus. Nulla facilisi.
</p>
<p>
    Donec et hendrerit massa, a ultrices ipsum. Fusce quis tellus aliquet, luctus eros in, ultricies ligula. Sed
    ultricies sit amet orci eget dictum. Donec non aliquet massa, at facilisis leo. In tincidunt tempus risus, vel
    luctus justo sollicitudin vitae. Fusce vehicula nibh odio, quis pellentesque arcu malesuada nec. Ut lobortis sem sed
    ex porta, vitae blandit nulla rhoncus. Vivamus bibendum sit amet ipsum eget aliquam. Praesent lectus nisi, volutpat
    nec scelerisque sollicitudin, accumsan in arcu. Phasellus sit amet lectus ut velit convallis euismod sed eu nisl.
    Sed tincidunt convallis luctus. Donec venenatis neque dictum nunc porta, in tincidunt justo feugiat. Mauris
    elementum finibus diam auctor tristique. Ut ullamcorper augue vitae hendrerit cursus.
</p>
<c:if test="${not subscribed}">
<form action="newsletter" method="post">
    <p>
        <label for="email">Mail</label> <input id="email" name="email" type="email">
    </p>
    <p>
        <label for="name">Imię Nazwisko</label> <input id="name" name="name" type="text">
    </p>
    <p>
        <button type="submit">Submit</button>
    </p>
</form>
</c:if>
</body>
</html>
