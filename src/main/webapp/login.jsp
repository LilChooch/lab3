<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.Date" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
    <h2>Вход</h2>
    <form action="login" method="post">
        <label for="username">Логин:</label>
        <input type="text" id="username" name="username" required><br>

        <label for="password">Пароль:</label>
        <input type="password" id="password" name="password" required><br>

        <button type="submit">Войти</button>
    </form>
    <p>Нет аккаунта? <a href="register.jsp">Зарегистрируйтесь здесь</a></p>
</body>
</html>