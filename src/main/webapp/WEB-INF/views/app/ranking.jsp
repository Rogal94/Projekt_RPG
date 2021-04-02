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
            <div class="w3-row w3-padding w3-dark-grey">
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/level"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">LEVEL</a>
                </div>
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/attack"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">ATTACK</a>
                </div>
                <div class="w3-third w3-center">
                    <a href='<c:url value="/ranking/defense"/>' class="w3-button w3-white w3-padding-large w3-hover-black w3-xlarge">DEFENSE</a>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navRanking')</script>
</html>


