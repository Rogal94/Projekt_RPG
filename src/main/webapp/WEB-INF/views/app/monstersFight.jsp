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
<script src='<c:url value="/js/fightAnim.js"/>'></script>

<body class="w3-black">

<nav class="w3-sidebar w3-bar-block w3-small w3-hide-small w3-center">
    <img src='<c:url value="/images/home/homeImg.jpg"/>' style="width:100%">
</nav>

<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <p class="w3-wide">HP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-red" style="height:28px;width:<c:out value="${(hero.healthPointsCurrent/hero.healthPointsMax) * 100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.healthPointsCurrent}"/>/<c:out value="${hero.healthPointsMax}"/></p></div>
                </div>
                <p class="w3-wide">MP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-blue" style="height:28px;width:<c:out value="${(hero.manaPointsCurrent/hero.manaPointsMax) * 100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.manaPointsCurrent}"/>/<c:out value="${hero.manaPointsMax}"/></p></div>
                </div>
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/hero/${hero.race}.jpg"/>' alt="warrior" class="w3-image" id = "heroImg" width="300" height="400">
                </span>
            </div>
            <div class="w3-half w3-section">
                <p class="w3-wide">HP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-red" style="height:28px;width:<c:out value="${(monsterSession.healthPointsCurrent/monster.healthPointsMax) * 100}"/>%"><p class="w3-center w3-text-white"><c:out value="${monsterSession.healthPointsCurrent}"/>/<c:out value="${monster.healthPointsMax}"/></p></div>
                </div>
                <p class="w3-wide">MP</p>
                <div class="w3-dark-gray w3-margin">
                    <div style="height:28px;width:100%"></div>
                </div>
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/monster/${fn:toLowerCase(monster.name)}.jpg"/>' alt="vampire" class="w3-image" id= "monsterImg" width="300" height="400">
                </span>
            </div>
        </div>
        <div class="w3-padding-large w3-center">
            <button onclick="setTimeout(buttonRedirectAttack, 2000, '${monster.id}'); buttonAttack()" id="normalAttackButton" class="w3-button w3-light-grey w3-hover-red w3-xxlarge">NORMAL ATTACK!</button>
        </div>
        <c:forEach items="${hero.skill}" var="skill">
        <div class="w3-padding-large w3-center">
            <button onclick="setTimeout(buttonRedirectSkill, 2000, '${monster.id}' ,'${skill.id}'); buttonAttack()" id="skillAttackButton" class="w3-button w3-light-grey w3-hover-red w3-xxlarge">${fn:toUpperCase(skill.name)} ATTACK!</button>
        </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

