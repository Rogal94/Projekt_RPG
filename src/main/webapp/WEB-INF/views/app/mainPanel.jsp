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
        <div class="w3-row w3-center w3-section" style="padding-bottom: 50px">
            <img src='<c:url value="/images/hero/${hero.race}.jpg"/>' alt="hero" class="w3-image w3-animate-zoom w3-half w3-section" width="600" height="800">
            <div class="w3-half w3-section">
                <h3 class="w3-padding-small w3-text-light-grey"><c:out value="${hero.name}"/></h3>
                <h3 class="w3-padding-small w3-text-light-grey"><c:out value="${fn:toUpperCase(hero.race)}"/></h3>
                <h1 class="w3-padding-small w3-text-light-grey">LEVEL : <c:out value="${hero.level}"/></h1>
                <p class="w3-wide">HP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-red" style="height:36px;width:<c:out value="${(hero.healthPointsCurrent/hero.healthPointsMax)*100}"/>%"><p class="w3-center w3-text-white w3-xlarge"><c:out value="${hero.healthPointsCurrent}"/>/<c:out value="${hero.healthPointsMax}"/></p></div>
                </div>
                <p class="w3-wide">MP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-blue" style="height:36px;width:<c:out value="${(hero.manaPointsCurrent/hero.manaPointsMax)*100}"/>%"><p class="w3-center w3-text-white w3-xlarge"><c:out value="${hero.manaPointsCurrent}"/>/<c:out value="${hero.manaPointsMax}"/></p></div>
                </div>
                <p class="w3-wide">STAMINA</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-orange" style="height:36px;width:<c:out value="${(hero.staminaCurrent/hero.staminaMax)*100}"/>%"><p class="w3-center w3-text-white w3-xlarge"><c:out value="${hero.staminaCurrent}"/>/<c:out value="${hero.staminaMax}"/></p></div>
                </div>
                <p class="w3-wide">EXP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-green" style="height:36px;width:<c:out value="${(hero.experienceCurrent/hero.experienceMax)*100}"/>%"><p class="w3-center w3-text-white w3-xlarge"><c:out value="${hero.experienceCurrent}"/>/<c:out value="${hero.experienceMax}"/></p></div>
                </div>
            </div>
        </div>
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <span class="w3-xlarge">GOLD AMOUNT :</span>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge"><c:out value="${hero.goldAmount}"/></span>
            </div>
        </div><br/>
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">QUEST:</span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge"><c:out value="${hero.quest.monsterName}"/></span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge"><c:out value="${hero.monsterKilled}"/>/5</span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">
                    <a href='<c:url value="/quests"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-right">DETAILS</a>
                </span>
            </div>
        </div>
        <div class="w3-row-padding" style="margin:50px -16px">
            <div class="w3-half w3-margin-bottom">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">STATISTICS</li>
                    <li class="w3-white w3-padding-16 w3-center">NEW POINTS : <c:out value="${hero.statisticPoints}"/></li>
                    <li class="w3-dark-grey w3-padding-16 w3-center">
                        <a href='<c:url value="/character"/>' class="w3-button w3-white w3-padding-large w3-hover-black">SPEND</a>
                    </li>
                </ul>
            </div>
            <div class="w3-half w3-margin-bottom">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">SKILLS</li>
                    <li class="w3-white w3-padding-16 w3-center">NEW POINTS : <c:out value="${hero.skillPoints}"/></li>
                    <li class="w3-dark-grey w3-padding-16 w3-center">
                        <a href='<c:url value="/character"/>' class="w3-button w3-white w3-padding-large w3-hover-black">SPEND</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navMainPanel')</script>
</html>

