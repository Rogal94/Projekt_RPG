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
<script src='<c:url value="/js/showDetails.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLoginChar.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row-padding" style="margin:50px -16px">
            <div class="w3-row w3-margin-bottom">
                <h1 class="w3-light-grey w3-xxlarge w3-padding-24 w3-center"><c:out value="${fn:toUpperCase(hero.race.name)}"/> <c:out value="${fn:toUpperCase(type)}"/> LIST</h1>
            </div>
            <div class="w3-row w3-padding w3-dark-grey">
                <div class="w3-center w3-margin">
                    <a href='<c:url value="/items/list/change/item"/>' class="w3-button w3-white w3-hover-black w3-xlarge w3-padding-small">ALL <c:out value="${fn:toUpperCase(hero.race.name)}"/> ITEMS</a>
                </div>
                <div class="w3-quarter">
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/weapon"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/weapon/weapon.png"/>' alt="weapon" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/necklace"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/accessory/necklace.png"/>' alt="helmet" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/ring"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/accessory/ring.png"/>' alt="weapon" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/helmet"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/armor/helmet.png"/>' alt="helmet" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/armor"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/armor/armor.png"/>' alt="armor" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/pants"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/armor/pants.png"/>' alt="pants" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                </div>
                <div class="w3-quarter">
                    <div class="w3-center w3-half">
                        <a href='<c:url value="/items/list/change/gloves"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/armor/gloves.png"/>' alt="gloves" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                    <div class="w3-center w-half">
                        <a href='<c:url value="/items/list/change/boots"/>' class="w3-white w3-hover-black w3-button w3-padding-small">
                            <img src='<c:url value="/images/items/armor/boots.png"/>' alt="boots" class="w3-image" width="64" height="64">
                        </a>
                    </div>
                </div>
            </div>
            <c:forEach items="${itemList}" var="item">
                <div class="w3-row w3-center w3-section w3-light-grey"
                     data-id="<c:out value="${item.id}"/>"
                     data-name="<c:out value="${item.name}"/>"
                     data-url="<c:out value="${item.imageUrl}"/>"
                     data-description="<c:out value="${item.description}"/>"
                     data-grade="<c:out value="${item.grade}"/>"
                     data-type="<c:out value="${item.type}"/>"
                        <c:if test="${item.type.equals('weapon') || item.type.equals('ring') || item.type.equals('necklace')}">
                            data-power="<c:out value="${item.attack}"/>"
                        </c:if>
                        <c:if test="${!(item.type.equals('weapon') || item.type.equals('ring') || item.type.equals('necklace'))}">
                            data-power="<c:out value="${item.defence}"/>"
                        </c:if>
                     onclick="setTimeout(showItemDetailsList, 100 , this , event)">
                    <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/items/${fn:toLowerCase(item.category)}/${item.type}.png"/>' alt="${item.type}" class="w3-image" width="64" height="64">
                    </span>
                    </div>
                    <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image grade<c:out value="${item.grade}"/>" width="64" height="64">
                    </span>
                    </div>
                    <div class="w3-quarter w3-section">
                        <c:forEach items="${item.race}" var="race">
                            <c:url value="${fn:toUpperCase(race.name)}"/><br>
                        </c:forEach>
                    </div>
                    <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <a href='<c:url value="/items/change/${item.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">EQUIP<br/><c:out value="${fn:toUpperCase(item.type)}"/></a>
                    </span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
<%@include file="/WEB-INF/jspf/itemDetails.jspf"%>
</body>
</html>

