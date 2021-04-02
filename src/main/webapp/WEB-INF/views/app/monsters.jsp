<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
<script src='<c:url value="/js/navBar.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <c:forEach items="${monsterList}" var="monster">
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center"><c:out value="${fn:toUpperCase(monster.name)}"/></li>
                    <li class="w3-padding-16">Attack : <c:out value="${monster.attack}"/></li>
                    <li class="w3-padding-16">Defence : <c:out value="${monster.defense}"/></li>
                    <li class="w3-padding-16">HP : <c:out value="${monster.healthPointsMax}"/></li>
                    <li class="w3-padding-16">Recommended Level : <c:out value="${monster.levelPreferred}"/></li>
                    <li class="w3-dark-grey w3-padding-small w3-center">
                        <button class="w3-button w3-light-grey w3-hover-red w3-xlarge" onclick="attackClick('${monster.id}')">ATTACK!</button>
                    </li>
                </ul>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/monster/${fn:toLowerCase(monster.name)}.jpg"/>' alt="${monster.name}" class="w3-image w3-animate-zoom" width="300" height="400">
                </span>
            </div>
        </div>
        </c:forEach>
        <span class="color-change color--1" ></span>
        <span class="color-change color--2" ></span>
        <span class="color-change color--3" ></span>
        <span class="color-change color--4" ></span>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<%--<script>navBarBlack('navMonsters')</script>--%>
<script src='<c:url value="/js/attackButton.js"/>'></script>
</html>

