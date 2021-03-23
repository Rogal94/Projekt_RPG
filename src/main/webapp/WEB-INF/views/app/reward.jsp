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
        <div class="w3-row w3-center w3-section">
            <div class="w3-half w3-section">
                <div class="w3-row w3-section w3-margin w3-dark-grey">
                    <span class="w3-xxlarge">REWARD</span>
                </div>
                <div class="w3-row w3-center w3-section w3-light-grey w3-margin">
                    <div class="w3-half w3-section">
                        <span class="w3-xlarge">EXP:</span>
                    </div>
                    <div class="w3-half w3-section">
                        <span class="w3-xlarge"><c:out value="${exp}"/></span>
                    </div>
                </div>
                <div class="w3-row w3-center w3-section w3-light-grey w3-margin">
                    <div class="w3-half w3-section">
                        <span class="w3-xlarge">GOLD:</span>
                    </div>
                    <div class="w3-half w3-section">
                        <span class="w3-xlarge"><c:out value="${gold}"/></span>
                    </div>
                </div>
            </div>
            <div class="w3-half w3-section">
                <div class="w3-row w3-section w3-margin w3-dark-grey">
                    <span class="w3-xxlarge">NEW ITEMS</span>
                </div>
                <div class="w3-row w3-center w3-section w3-light-grey w3-margin">
                    <div class="w3-half w3-section">
                        <span class="w3-xlarge">${item.name}</span>
                    </div>
                    <div class="w3-half w3-section">
                        <img src='<c:url value="${item.imageUrl}"/>' alt="icon" class="w3-image" width="32" height="32">
                    </div>
                </div>
            </div>
            <div class="w3-row w3-section">
                    <span class="w3-xxlarge">
                        <br>
                        <a href='<c:url value="/panel"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-lightgray">MAIN PANEL</a>
                    </span>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

