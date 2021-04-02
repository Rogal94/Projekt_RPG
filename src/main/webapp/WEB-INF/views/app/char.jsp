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
                <h1 class="w3-padding-small w3-text-light-grey"><c:out value="${hero.name}"/></h1>
                <h1 class="w3-padding-small w3-text-light-grey"><c:out value="${fn:toUpperCase(hero.race)}"/></h1>
                <h1 class="w3-padding-small w3-text-light-grey">LEVEL : <c:out value="${hero.level}"/></h1>
                    <div class="w3-margin w3-center w3-dark-grey ">
                        <a href='<c:url value="/character/char"/>' class="w3-button w3-dark-grey w3-padding-large w3-hover-light-grey w3-xxlarge">STATS AND SKILLS</a>
                    </div><br>
                    <div class="w3-margin w3-center w3-dark-grey">
                        <a href='<c:url value="/items"/>' class="w3-button w3-dark-grey w3-padding-large w3-hover-light-grey w3-xxlarge">ITEMS</a>
                    </div><br>
                    <div class="w3-margin w3-center w3-dark-grey">
                        <a href='<c:url value="/quests"/>' class="w3-button w3-dark-grey w3-padding-large w3-hover-light-grey w3-xxlarge">QUESTS</a>
                    </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navCharacter')</script>
</html>


