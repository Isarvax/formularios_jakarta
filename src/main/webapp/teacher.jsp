<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    <link rel="stylesheet" href="css/bootstrap.css">
    <link rel="stylesheet" href="css/login.css">
    <title>Crear profesor</title>

</head>
<body>
    <div id="information-space">
        <% HashMap<String,String> errores=(HashMap<String, String>) request.getSession().getAttribute("errores");
            if (errores!=null&&errores.size()>0){
        %>
        <h3>Whoops!</h3>
        <%
            for(String error:errores.values()){
        %>
        <p>* <%=error%></p>
            <%}%>
        <%}%>
        <h1>Crear profesor</h1>
        <form action="${pageContext.request.contextPath}/teacher-form" method="post">
            <label for="name">Nombre:</label>
            <input type="text" id="name" name="name" required><br><br>
            <label for="email">Email:</label>
            <input type="text" id="email" name="email" required><br><br>
            <input type="submit" value="Guardar">
        </form>
    </div>
</body>
</html>