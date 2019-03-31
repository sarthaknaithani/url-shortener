<%-- 
    Document   : index
    Created on : Mar 31, 2019, 8:06:49 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>URL-SHORTENER</title>
    </head>
    <body>
        Enter Long URL:-><input type="text" placeholder="Paste your long url" name="longurl">
    </body>
    <form action="get_request">
        <Input type="submit" value="Generate Short URL...">
    </form>
</html>
