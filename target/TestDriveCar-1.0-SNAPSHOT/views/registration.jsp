<%--
  Created by IntelliJ IDEA.
  User: Asus
  Date: 10.11.2018
  Time: 0:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello!</title>
</head>
<body>
<div align="center">
    <form action="RegistrationServlet" method="post">
        First name: <input type="text" id="first_name" name="first_name" required="required">
        Last name: <input type="text" id="last_name" name="last_name" required="required">
        E-mail: <input id="email" type="email" name="email" required="required">
        Password: <input id="password" name="password" type="password" required="required">
        <input type="submit" value="signup">
    </form>
</div>
</body>
</html>
