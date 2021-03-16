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
        <div class="w3-row w3-center w3-section" style="padding-bottom: 50px">
            <img src='<c:url value="/images/hero/orcWarrior.jpg"/>' alt="hero" class="w3-image w3-animate-zoom w3-half w3-section" width="600" height="800">
            <div class="w3-half w3-section">
                <h3 class="w3-padding-small w3-text-light-grey">CHARACTER NAME</h3>
                <h3 class="w3-padding-small w3-text-light-grey">CHARACTER CLASS</h3>
                <h1 class="w3-padding-small w3-text-light-grey">LEVEL : 50</h1>
                <p class="w3-wide">HP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-red" style="height:36px;width:60%"><p class="w3-center w3-text-white w3-xlarge">1200/2400</p></div>
                </div>
                <p class="w3-wide">MP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-blue" style="height:36px;width:85%"><p class="w3-center w3-text-white w3-xlarge">1200/2400</p></div>
                </div>
                <p class="w3-wide">STAMINA</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-orange" style="height:36px;width:80%"><p class="w3-center w3-text-white w3-xlarge">1200/2400</p></div>
                </div>
                <p class="w3-wide">EXP</p>
                <div class="w3-dark-gray w3-margin">
                    <div class="w3-green" style="height:36px;width:80%"><p class="w3-center w3-text-white w3-xlarge">1200/2400</p></div>
                </div>
            </div>
        </div>
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <span class="w3-xlarge">ATTACK :</span>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">100</span>
            </div>
        </div><br/>
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <span class="w3-xlarge">DEFENCE :</span>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">100</span>
            </div>
        </div>
        <div class="w3-row-padding" style="margin:50px -16px">
            <div class="w3-half w3-margin-bottom">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">STATISTICS</li>
                    <li class="w3-padding-16">
                        STRENGTH
                        <a href='<c:url value="/app/statAdd"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-left">ADD</a>
                        <a href='<c:url value="/app/statDetails"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-right">DETAILS</a>
                    </li>
                    <li class="w3-padding-16">
                        VITALITY
                        <a href='<c:url value="/app/statAdd"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-left">ADD</a>
                        <a href='<c:url value="/app/statDetails"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-right">DETAILS</a>
                    </li>
                    <li class="w3-dark-grey w3-padding-16 w3-center">POINTS : 6</li>
                </ul>
            </div>
            <div class="w3-half w3-margin-bottom">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">SKILLS</li>
                    <li class="w3-padding-16">
                        FIRE BALL
                        <a href='<c:url value="/app/skillAdd"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-left">ADD</a>
                        <a href='<c:url value="/app/skillDetails"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-right">DETAILS</a>
                    </li>
                    <li class="w3-padding-16">
                        LIFESTEAL
                        <a href='<c:url value="/app/skillAdd"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-left">ADD</a>
                        <a href='<c:url value="/app/skillDetails"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-right">DETAILS</a>
                    </li>
                    <li class="w3-light-grey w3-padding-16 w3-center">
                        <a href='<c:url value="/app/skillAddNew"/>' class="w3-button w3-dark-grey w3-padding-large w3-hover-black">LEARN NEW SKILL</a>
                    </li>
                    <li class="w3-dark-grey w3-padding-16 w3-center">POINTS : 6</li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navCharacter')</script>
</html>

