<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
<script src='<c:url value="/js/showDetails.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-padding-large w3-section w3-light-grey">
            <div class="w3-half w3-section">
                <img src='<c:url value="/images/shop/gold.png"/>' alt="gold" class="w3-image" width="64" height="64" style="border: 5px solid yellow">
            </div>
            <div class="w3-half w3-section">
                <span class="w3-xlarge"><c:out value="${hero.goldAmount}"/></span>
            </div>
        </div>
        <c:if test="${transaction.equals('buy')}">
        <div class="w3-row w3-margin-bottom">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">POTIONS</li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/items/potion/healthPotion.png"/>' alt="potion" class="w3-image" width="64" height="64" style="border: 5px solid green">
                    </div>
                    <div class="w3-quarter w3-section">HEALTH POTION<br>x <c:out value="${hero.potionHealth}"/></div>
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/shop/gold.png"/>' alt="icon" class="w3-image" width="64" height="64">
                        100
                    </div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/shop/buy/potion/health"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/items/potion/staminaPotion.png"/>' alt="potion" class="w3-image" width="64" height="64" style="border: 5px solid pink">
                    </div>
                    <div class="w3-quarter w3-section">STAMINA POTION<br>x <c:out value="${hero.potionStamina}"/></div>
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/shop/gold.png"/>' alt="icon" class="w3-image" width="64" height="64">
                        2000
                    </div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/shop/buy/potion/stamina"/>' class="w3-button w3-dark-grey w3-hover-black">BUY</a>
                    </div>
                </li>
            </ul>
        </div>
        </c:if>
        <div class="w3-row w3-center w3-section w3-dark-grey w3-padding-large">
            <span class="w3-xxlarge">ITEM LIST</span>
        </div>
        <c:forEach items="${itemList}" var="item">
        <div class="w3-row w3-center w3-section w3-light-grey"
             data-id="<c:out value="${item.id}"/>"
             data-name="<c:out value="${item.name}"/>"
             data-url="<c:out value="${item.imageUrl}"/>"
             data-description="<c:out value="${item.description}"/>"
             data-type="<c:out value="${item.type}"/>"
                <c:if test="${item.type.equals('weapon')}">
                    data-power="<c:out value="${item.attack}"/>"
                </c:if>
                <c:if test="${!item.type.equals('weapon')}">
                    data-power="<c:out value="${item.defence}"/>"
                </c:if>
             onclick="setTimeout(showItemDetailsShop, 100 , this , event)">
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="/images/items/${fn:toLowerCase(item.category)}/${item.type}.png"/>' alt="<c:url value="${item.type}"/>" class="w3-image" width="64" height="64">
                </span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">
                    <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">
                </span>
            </div>
            <div class="w3-quarter w3-section">
                <img src='<c:url value="/images/shop/gold.png"/>' alt="icon" class="w3-image" width="64" height="64">
                <c:if test="${transaction.equals('buy')}">
                    <span class="w3-xlarge"><c:out value="${item.price}"/></span>
                </c:if>
                <c:if test="${transaction.equals('sell')}">
                    <span class="w3-xlarge"><fmt:formatNumber type="number" value="${item.price/5}"/></span>
                </c:if>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xxlarge">
                        <a href='<c:url value="/shop/${transaction}/${item.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black"><c:out value="${fn:toUpperCase(transaction)}"/></a>
                    </span>
            </div>
        </div>
        </c:forEach>
<%--        <c:forEach items="${armorList}" var="armor">--%>
<%--            <div class="w3-row w3-center w3-section w3-light-grey">--%>
<%--                <div class="w3-quarter w3-section">--%>
<%--                    <span class="w3-xlarge">--%>
<%--                        <img src='<c:url value="/images/items/armor/${armor.type}.png"/>' alt="weapon" class="w3-image" width="68" height="76">--%>
<%--                    </span>--%>
<%--                </div>--%>
<%--                <div class="w3-quarter w3-section">--%>
<%--                    <span class="w3-xlarge">--%>
<%--                        <img src='<c:url value="${armor.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">--%>
<%--                    </span>--%>
<%--                </div>--%>
<%--                <div class="w3-quarter w3-section">--%>
<%--                    <c:if test="${transaction.equals('buy')}">--%>
<%--                        <span class="w3-xlarge">PRICE: <c:out value="${armor.price}"/><br>DEFENSE: <c:out value="${armor.defense}"/></span>--%>
<%--                    </c:if>--%>
<%--                    <c:if test="${transaction.equals('sell')}">--%>
<%--                        <span class="w3-xlarge">PRICE: <fmt:formatNumber type="number" value="${armor.price/5}"/><br>DEFENSE: <c:out value="${armor.defense}"/></span>--%>
<%--                    </c:if>--%>
<%--                </div>--%>
<%--                <div class="w3-quarter w3-section">--%>
<%--                    <span class="w3-xxlarge">--%>
<%--                        <a href='<c:url value="/shop/${transaction}/${armor.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black"><c:out value="${fn:toUpperCase(transaction)}"/></a>--%>
<%--                    </span>--%>
<%--                </div>--%>
<%--            </div>--%>
<%--        </c:forEach>--%>
    </div>
</div>
<%@include file="/WEB-INF/jspf/itemDetails.jspf"%>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

