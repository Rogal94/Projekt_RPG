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
<%@include file="/WEB-INF/jspf/navBar.jspf"%>
<div class="w3-padding-large" id="main">
    <header class="w3-container w3-padding-16 w3-center w3-black" id="home">
        <h1 class="w3-jumbo w3-hide-small">ONE MAN ARMY RPG</h1>
        <h3 class="w3-padding-16 w3-text-light-grey">WELCOME ADVENTURER</h3>
        <img src='<c:url value="/images/home/homeImg.jpg"/>' alt="logo" class="w3-image w3-animate-zoom" width="810" height="540">
        <h3 class="w3-padding-16 w3-text-light-grey">
            <a href='<c:url value="/register"/>' class="w3-button w3-light-grey w3-padding-large w3-section w3-animate-fading">
                START YOUR ADVENTURE
            </a>
        </h3>
    </header>
    <div class="w3-content w3-justify w3-text-grey w3-padding-64" id="about">
        <h2 class="w3-text-light-grey">DESCRIPTION</h2>
        <hr style="width:200px" class="w3-opacity">
        <p>One Man Army is a new RPG in fantasy world. Choose your hero and save the world from monsters. There are many tasks ahead of you. Get new items, learn new skills and become the savior of this world!
        </p>
    </div>
    <div class="w3-padding-64 w3-content w3-text-grey" id="contact">
        <h2 class="w3-text-light-grey">Contact Me</h2>
        <hr style="width:200px" class="w3-opacity">

        <div class="w3-section">
            <p><i class="fa fa-map-marker fa-fw w3-text-white w3-xxlarge w3-margin-right"></i> Warsaw, Poland</p>
            <p><i class="fa fa-envelope fa-fw w3-text-white w3-xxlarge w3-margin-right"> </i> Email: arogal@op.pl</p>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navHome')</script>
<audio loop="loop" id="music">
    <source src='<c:url value="/music/start.ogg"/>' type="audio/ogg">
    <source src='<c:url value="/music/start.mp3"/>' type="audio/mpeg">
</audio>
<script src='<c:url value="/js/music.js"/>'></script>
</html>

