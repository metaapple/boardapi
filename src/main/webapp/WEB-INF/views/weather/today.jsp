<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="../layouts/header.jsp"%>
<h1>OpenAPI 이용한 날씨정보</h1>
<div>
${city} <br>
<img src="${iconUrl}" width="200" height="200"> <br>
${weather} <br>
온도 ${weather.main.temp} , 습도 ${weather.main.humidity} <br>





    <hr>
    <%
        String c = (String)request.getAttribute("city");
        out.print(c);
    %>
</div>
<%@ include file="../layouts/footer.jsp" %>