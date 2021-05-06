<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
        <div class="w3-row-padding" style="margin:50px -16px">
            <div class="w3-row w3-margin-bottom">
                <h1 class="w3-light-grey w3-xxlarge w3-padding-24 w3-center">RANKING</h1>
            </div>
            <div class="w3-row w3-padding w3-dark-grey w3-margin-bottom">
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/level"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">LEVEL</a>
                </div>
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/attack"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">ATTACK</a>
                </div>
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/defence"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">DEFENCE</a>
                </div>
            </div>
            <div class="w3-row w3-padding w3-dark-grey">
                <div class="w3-quarter w3-center w3-xxlarge">
                    RANKING
                </div>
                <div class="w3-quarter w3-center w3-xxlarge">
                    PLAYER
                </div>
                <div class="w3-quarter w3-center w3-xxlarge">
                    RACE
                </div>
                <div class="w3-quarter w3-center w3-xxlarge">
                    ${fn:toUpperCase(rankingBy)}
                </div>
            </div>
            <ul class="w3-ul w3-margin">
            <c:forEach items="${rankingList}" var="hero">
                <li class="w3-row w3-padding w3-dark-grey">
                    <div class="w3-quarter w3-center w3-xlarge">
                        ${rankingList.indexOf(hero) + 1}
                    </div>
                    <div class="w3-quarter w3-center w3-xlarge">
                        ${hero.name}
                    </div>
                    <div class="w3-quarter w3-center w3-xlarge">
                        ${fn:toUpperCase(hero.race.name)}
                    </div>
                    <c:choose>
                        <c:when test="${rankingBy.equals('level')}"><div class="w3-quarter w3-center w3-xlarge">${hero.level}</div></c:when>
                        <c:when test="${rankingBy.equals('attack')}"><div class="w3-quarter w3-center w3-xlarge">${hero.attack}</div></c:when>
                        <c:when test="${rankingBy.equals('defense')}"><div class="w3-quarter w3-center w3-xlarge">${hero.defense}</div></c:when>
                    </c:choose>
                </li>
            </c:forEach>
            </ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>



