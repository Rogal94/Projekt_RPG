<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLoginL.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey w3-row w3-section w3-center" id="body">
        <img src='<c:url value="/images/home/homeImg.jpg"/>' alt="logo" class="w3-image w3-animate-zoom w3-margin" width="300" height="200">
        <form method="post" action="/add/all">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:400px; font-size:30px">ADD ALL</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/races">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD RACES</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/weapons">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD WEAPONS</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/armors">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD ARMORS</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/accessories">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD ACCESSORIES</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/skills">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD SKILLS</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/quests">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD QUESTS</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
        <form method="post" action="/add/monsters">
            <button type="submit" class="w3-button w3-light-grey w3-padding-large w3-circle w3-margin" style="width:250px; font-size:20px">ADD MONSTERS</button>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

