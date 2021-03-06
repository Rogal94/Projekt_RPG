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

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLoginChar.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-section w3-dark-grey w3-padding-large">
            <span class="w3-xxlarge"><c:out value="${fn:toUpperCase(type)}"/> LIST</span>
        </div>
        <c:forEach items="${itemList}" var="item">
        <div class="w3-row w3-center w3-section w3-light-grey">
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/item/${type}.jpg"/>' alt="weapon" class="w3-image" width="68" height="76">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                <c:if test="${type.equals('weapon')}">
                <span class="w3-xlarge">Attack: <c:out value="${item.attack}"/></span>
                </c:if>
                <c:if test="${!type.equals('weapon')}">
                    <span class="w3-xlarge">Defense: <c:out value="${item.defense}"/></span>
                </c:if>
                <br><a href='<c:url value="/items/details/${type}/${item.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">DETAILS</a>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <a href='<c:url value="/items/change/${type}/${item.id}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">EQUIP<br/><c:out value="${fn:toUpperCase(type)}"/></a>
                    </span>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

