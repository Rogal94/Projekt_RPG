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
        <div class="w3-container w3-padding-small w3-center w3-black">
            <img src='<c:url value="/images/shop/shop.jpg"/>' alt="shop" class="w3-image w3-animate-zoom" width="800" height="480">
            <h3 class="w3-padding-16 w3-text-light-grey">Welcome adventurer. What do u need?</h3>
        </div>
        <div class="w3-row w3-center w3-section">
            <div class="w3-third w3-section w3-dark-grey">
                <div class="w3-row w3-section">
                    <span class="w3-xxxlarge">
                        <a href='<c:url value="/shop/list"/>' class="w3-button w3-dark-grey w3-hover-black">SELL</a>
                    </span>
                </div>
            </div>
            <div class="w3-third w3-section w3-dark-grey w3-right">
                <div class="w3-row w3-section">
                    <span class="w3-xxxlarge">
                        <a href='<c:url value="/shop/list"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </span>
                </div>
            </div>
        </div>
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <span class="w3-xlarge">GOLD AMOUNT :</span>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">5400</span>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navShop')</script>
</html>

