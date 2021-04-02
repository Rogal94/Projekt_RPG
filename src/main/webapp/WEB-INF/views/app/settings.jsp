<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
<script src='<c:url value="/js/settings.js"/>'></script>
<script src='<c:url value="/js/navBar.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <form:form method="post" modelAttribute="user">
            <div class="w3-row w3-center w3-section w3-xlarge">
                <div class="w3-row w3-section w3-dark-grey">
                    <div class="w3-row w3-section">
                        <span class="w3-xxlarge">COMBAT</span>
                    </div>
                </div>
                <div class="w3-half w3-section">
                    <div class="">
                        <form:radiobutton class="checkbox-budget" path="combatStyle" id="click" value="1" required="true"/>
                        <label for="click">
                            CLICK
                        </label>
                    </div>
                </div>
                <div class="w3-half w3-section w3-xlarge">
                    <div>
                        <form:radiobutton class="checkbox-budget" path="combatStyle" id="move" value="2" required="true"/>
                        <label for="move">
                            MOVE
                        </label>
                    </div>
                </div>
            </div>
            <div class="w3-row w3-center w3-section w3-xlarge">
                <div class="w3-row w3-section w3-dark-grey">
                    <div class="w3-row w3-section">
                        <span class="w3-xxlarge">MAP SIZE</span>
                    </div>
                </div>
                <div class="w3-row w3-section w3-light-grey">
                    <div class="w3-third w3-section">
                        <div class="w3-button w3-dark-gray w3-hover-black w3-padding-large w3-circle" onclick="minusMap()">-</div>
                    </div>
                    <div class="w3-third w3-section w3-xxlarge">
                        <span id="map">${user.mapSize}</span> %
                    </div>
                    <div class="w3-third w3-section">
                        <div class="w3-button w3-dark-gray w3-hover-black w3-padding-large w3-circle" onclick="plusMap()">+</div>
                    </div>
                </div>
            </div>
            <div class="w3-row w3-center w3-section w3-xlarge">
                <div class="w3-row w3-section w3-dark-grey">
                    <div class="w3-row w3-section">
                        <span class="w3-xxlarge">MUSIC VOLUME</span>
                    </div>
                </div>
                <div class="w3-row w3-section w3-light-grey w3-center">
                    <div class="w3-third w3-section">
                        <div class="w3-button w3-dark-gray w3-hover-black w3-padding-large w3-circle" onclick="minusVolume()">-</div>
                    </div>
                    <div class="w3-third w3-section w3-xxlarge">
                        <span id="volume"><fmt:formatNumber type="number" value="${user.musicVolume * 100}"/></span> %
                    </div>
                    <div class="w3-third w3-section">
                        <div class="w3-button w3-dark-gray w3-hover-black w3-padding-large w3-circle" onclick="plusVolume()">+</div>
                    </div>
                </div>
            </div>
            <p><form:hidden value="${user.mapSize}" path="mapSize" required="true" id="formMap"/></p>
            <p><form:errors class="w3-text-red" path="mapSize"/></p>
            <p><form:hidden value="${user.musicVolume}" path="musicVolume" required="true" id="formVolume"/></p>
            <p><form:errors class="w3-text-red" path="musicVolume"/></p>
            <div style="height:150px">
                <button type="submit" class="w3-button w3-light-gray w3-padding-large w3-left w3-circle" style="width:250px; font-size:20px">
                    <i class="fa fa-paper-plane w3-xxlarge"></i>SAVE
                </button>
            </div>
        </form:form>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navSettings')</script>
</html>

