<%-- 
    Document   : pleaseWait
    Created on : Jan 16, 2015, 5:16:58 PM
    Author     : xnikos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" media="all" href="css/styles.css">

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Please Wait!</title>
    </head>
    <body>
    <center>
        <br><br><br><br>
        <h1>Please wait while results are loading...</h1>
        <h3>Server is parsing Car.gr. Depending to car popularity, this could take a while</h3>
        <br>
        <img src="img/loading.gif">
    </center>
    <form name="auto" method="POST" action="CarCharts">  
        <input type="hidden" name="manuf" value="<%=request.getParameter("manuf")%>"> 
        <input type="hidden" name="model" value="<%=request.getParameter("model")%>">  
        <input type="hidden" name="fromYear" value="<%=request.getParameter("fromYear")%>">  
        <input type="hidden" name="toYear" value="<%=request.getParameter("toYear")%>">  
        <input type="hidden" name="fromCC" value="<%=request.getParameter("fromCC")%>">  
        <input type="hidden" name="toCC" value="<%=request.getParameter("toCC")%>">  
        <input type="hidden" name="fromKlm" value="<%=request.getParameter("fromKlm")%>">  
        <input type="hidden" name="toKlm" value="<%=request.getParameter("toKlm")%>">  
        <input type="hidden" name="fromPrice" value="<%=request.getParameter("fromPrice")%>">  
        <input type="hidden" name="toPrice" value="<%=request.getParameter("toPrice")%>">  
    </form>  
    <!-- this script submits the form AFTER it has been completely loaded -->  
    <script>
        document.auto.submit();
    </script>      </body>
</html>
