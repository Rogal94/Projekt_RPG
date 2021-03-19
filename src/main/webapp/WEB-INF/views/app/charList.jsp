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
<%@include file="/WEB-INF/jspf/navBarLoginL.jspf"%>
<div class="w3-padding-large" id="main">
    <header class="w3-container w3-padding-16 w3-center w3-black" id="home">
        <h1 class="w3-jumbo w3-hide-small">ONE MAN ARMY RPG</h1>
        <img src='<c:url value="/images/home/homeImg.jpg"/>' alt="logo" class="w3-image w3-animate-zoom" width="300" height="200">
    </header>
    <div class="w3-padding-large">
        <div class="w3-content w3-padding-64 w3-text-grey w3-xlarge">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">CHARACTER LIST</li>
                <c:forEach items="${heroList}" var="hero">
                    <li class="w3-padding-16"><c:out value="${hero.name}"/><a href='<c:url value="/panel/${hero.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right">PLAY</a></li>
                </c:forEach>
                <li class="w3-light-grey w3-padding-16 w3-center">
                    <a href='<c:url value="/character/create"/>' class="w3-button w3-dark-grey w3-padding-large w3-hover-black">CREATE CHARACTER</a>
                </li>
            </ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navCharList')</script>
</html>
