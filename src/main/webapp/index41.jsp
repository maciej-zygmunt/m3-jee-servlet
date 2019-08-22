<%--
  Created by IntelliJ IDEA.
  User: agh
  Date: 2019-08-21
  Time: 16:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>index41</title>
</head>
<%--
W projekcie stwórz stronę jsp index41.jsp. Następnie:

    Pobierz wartość parametru GET o nazwie author.
    Sprawdź czy parametr istnieje oraz czy nie jest pusty.
    Wyświetl informacje w postaci:

    <p>Wybrany autor <Pobrana wartość></p>


--%>
<body>
<p>Wybrany autor ${empty param.author? "empty ":param.author}</p>
</body>
</html>
