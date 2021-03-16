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

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">WEREWOLF</li>
                    <li class="w3-padding-16">Attack : 90</li>
                    <li class="w3-padding-16">Defence : 90</li>
                    <li class="w3-padding-16">HP : 1800</li>
                    <li class="w3-padding-16">Recommended Level : 18</li>
                    <li class="w3-dark-grey w3-padding-small w3-center">
                        <a href='<c:url value="/monsters/fight"/>' class="w3-button w3-light-grey w3-hover-red w3-xlarge">ATTACK!</a>
                    </li>
                </ul>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/monster/werewolf.jpg"/>' alt="werewolf" class="w3-image w3-animate-zoom" width="300" height="400">
                </span>
            </div>
        </div>
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">VAMPIRE</li>
                    <li class="w3-padding-16">Attack : 100</li>
                    <li class="w3-padding-16">Defence : 100</li>
                    <li class="w3-padding-16">HP : 2000</li>
                    <li class="w3-padding-16">Recommended Level : 20</li>
                    <li class="w3-dark-grey w3-padding-small w3-center">
                        <a href='<c:url value="/monsters/fight"/>' class="w3-button w3-light-grey w3-hover-red w3-xlarge">ATTACK!</a>
                    </li>
                </ul>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/monster/vampire.jpg"/>' alt="vampire" class="w3-image w3-animate-zoom" width="300" height="400">
                </span>
            </div>
        </div>
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">HYDRA</li>
                    <li class="w3-padding-16">Attack : 200</li>
                    <li class="w3-padding-16">Defence : 150</li>
                    <li class="w3-padding-16">HP : 4000</li>
                    <li class="w3-padding-16">Recommended Level : 40</li>
                    <li class="w3-dark-grey w3-padding-small w3-center">
                        <a href='<c:url value="/monsters/fight"/>' class="w3-button w3-light-grey w3-hover-red w3-xlarge">ATTACK!</a>
                    </li>
                </ul>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/monster/hydra.jpg"/>' alt="hydra" class="w3-image w3-animate-zoom" width="300" height="400">
                </span>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navMonsters')</script>
</html>

