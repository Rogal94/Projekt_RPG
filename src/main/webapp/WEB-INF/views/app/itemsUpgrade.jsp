<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<title>ONE MAN ARMY RPG</title>
<link rel="shortcut icon" href='<c:url value="/images/home/favicon.ico"/>'/>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href='<c:url value="/css/w3.css"/>' rel="stylesheet" type="text/css">
<link href='<c:url value="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>' rel="stylesheet" type="text/css">
<script src='<c:url value="/js/showDetails.js"/>'></script>
<script src='<c:url value="/js/itemUpgrade.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLoginChar.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row-padding" style="margin:50px -16px">
            <div class="w3-row w3-margin-bottom w3-dark-grey">
                <h1 class="w3-xxlarge w3-padding-24 w3-center">UPGRADE</h1>
            </div>
            <div class="w3-row w3-margin-bottom">
                <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                    <li class="w3-padding-16  w3-center"
                        data-id="<c:out value="${item.id}"/>"
                        data-name="<c:out value="${item.name}"/>"
                        data-url="<c:out value="${item.imageUrl}"/>"
                        data-description="<c:out value="${item.description}"/>"
                        data-type="<c:out value="${item.type}"/>"
                            <c:if test="${item.type.equals('weapon') || item.type.equals('ring') || item.type.equals('necklace')}">
                                data-power="<c:out value="${item.attack}"/>"
                            </c:if>
                            <c:if test="${!(item.type.equals('weapon') || item.type.equals('ring') || item.type.equals('necklace'))}">
                                data-power="<c:out value="${item.defence}"/>"
                            </c:if>
                        onclick="setTimeout(showItemDetailsShop, 100 , this , event)">
                        <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image grade<c:out value="${item.grade}"/>" width="128" height="128" id="zoom">
                    </li>
                    <li class="w3-padding-16 w3-xlarge w3-center">
                        <button  class="w3-button w3-dark-grey w3-hover-black" onclick="setTimeout(buttonRedirect, 2000, ${item.id}, ${equipped});itemUpgrade()">UPGRADE</button>
                    </li>
                    <li class="w3-row w3-section w3-xlarge w3-center">
                        <div class="w3-quarter">
                            <img src='<c:url value="/images/shop/gold.png"/>' alt="icon" class="w3-image" width="64" height="64" style="border: 5px solid yellow">
                            <c:out value="${hero.goldAmount}"/>
                        </div>
                        <div class="w3-half w3-circle w3-dark-grey w3-border w3-border-black w3-padding-small w3-margin-top">
                            <c:out value="${chance}"/> %
                        </div>
                        <div class="w3-quarter">
                            <img src='<c:url value="/images/shop/gold.png"/>' alt="icon" class="w3-image" width="64" height="64" style="border: 5px solid yellow">
                            <fmt:formatNumber type="number" value="${item.price/2}"/>
                        </div>
                    </li>
                    <li class="w3-padding-16 w3-xlarge w3-center">
                        <a href='<c:url value="/items"/>' class="w3-button w3-dark-grey w3-hover-black">BACK</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
<%@include file="/WEB-INF/jspf/itemDetails.jspf"%>
</body>
</html>



