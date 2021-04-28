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
<%@include file="/WEB-INF/jspf/navBarLoginChar.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row-padding" style="margin:50px -16px">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">ITEM DETAILS</li>
                <li class="w3-padding-16  w3-center">
                    <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">
                </li>
                <li class="w3-padding-16">
                    Name: <c:out value="${item.name}"/>
                </li>
                <li class="w3-padding-16">
                    <c:if test="${type.equals('weapon')}">
                        Attack: <c:out value="${item.attack}"/>
                    </c:if>
                    <c:if test="${!type.equals('weapon')}">
                        Defense: <c:out value="${item.defence}"/>
                    </c:if>
                </li>
                <li class="w3-padding-16">
                    Description : <c:out value="${item.description}"/>
                </li>
            </ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
