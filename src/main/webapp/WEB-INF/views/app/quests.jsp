<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
<script src='<c:url value="/js/navBar.js"/>'></script>
<script src='<c:url value="/js/rewardBut.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-margin">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xxlarge w3-padding-24 w3-center">QUEST</li>
                <li class="w3-padding-16 w3-xlarge"><c:out value="${quest.name}"/></li>
                <li class="w3-padding-16 w3-xlarge">Kill 5 <c:out value="${quest.monsterName}"/></li>
                <li class="w3-padding-16"><c:out value="${quest.description}"/></li>
                <li class="w3-dark-grey w3-padding-16 w3-center w3-xlarge">PROGRESS : <span id="progress"><c:out value="${hero.monsterKilled}"/>/5</span></li>
                <li class="w3-light-grey w3-padding-16 w3-center">
                    <a href='<c:url value="/reward/quest/${quest.id}"/>' class="w3-button w3-green w3-padding-large w3-hover-black w3-xxlarge w3-hide" id="rewardButton">GET REWARD</a>
                </li>
            </ul>
        </div></br>
        <div class="w3-row w3-margin">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xxlarge w3-padding-24 w3-center">NEXT QUEST</li>
                <li class="w3-padding-16 w3-xlarge"><c:out value="${nextQuest.name}"/></li>
                <li class="w3-padding-16 w3-xlarge">Kill 5 <c:out value="${nextQuest.monsterName}"/></li>
                <li class="w3-padding-16"><c:out value="${nextQuest.description}"/></li>
            </ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navQuests'); rewardButtonShow()</script>
</html>