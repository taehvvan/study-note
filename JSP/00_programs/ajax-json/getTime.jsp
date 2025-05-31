<%@ page contentType="application/json; charset=UTF-8" %>
<%@ page import="java.util.*" %>
<%
    String time = new java.text.SimpleDateFormat("HH:mm:ss").format(new Date());
%>
{
    "time": "<%= time %>"
}

