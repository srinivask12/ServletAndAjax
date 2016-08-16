<%-- 
    Document   : one
    Created on : 12 Aug, 2016, 7:58:46 PM
    Author     : a10441
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello JSP!</h1>
        <h2><%=(String)request.getAttribute("todo")%></h2>
    </body>
</html>
