<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <span class="w3-xlarge">GOLD AMOUNT :</span>
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge">5400</span>
            </div>
        </div>
        <div class="w3-row w3-margin-bottom">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">POTIONS</li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">HEALTH POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br>10</div>
                    <div class="w3-quarter w3-section">PRICE:<br>10</div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/shop/buyPotion"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">MANA POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br>10</div>
                    <div class="w3-quarter w3-section">PRICE:<br>10</div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/shop/buyPotion"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">STAMINA POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br>10</div>
                    <div class="w3-quarter w3-section">PRICE:<br>10</div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/shop/buyPotion"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="w3-row w3-center w3-section w3-dark-grey w3-padding-large">
            <span class="w3-xxlarge">ITEM LIST</span>
        </div>
        <div class="w3-row w3-center w3-section w3-light-grey">
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/item/weapon.jpg"/>' alt="weapon" class="w3-image" width="68" height="76">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/item/exampleItemIcon.png"/>' alt="icon" class="w3-image" width="64" height="64">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">PRICE: <br>10</span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xxlarge">
                        <a href='<c:url value="/shop/buyItem"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">BUY</a>
                    </span>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

