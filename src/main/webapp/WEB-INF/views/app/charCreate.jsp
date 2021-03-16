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
<%@include file="/WEB-INF/jspf/navBarLoginL.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <form method="post">
            <div class="w3-padding-large w3-center">
                <p class="w3-xlarge">SELECT CLASS:</p>
            </div>
            <div class="w3-row w3-center w3-section w3-xlarge">
                <div class="w3-half w3-section">
                    <div class="">
                        <input class="checkbox-budget" type="radio" name="clas" id="warrior" required>
						<label for="warrior">
                            <img src='<c:url value="/images/hero/orcWarrior.jpg"/>' alt="warrior" class="w3-image" width="300" height="400">
                            WARRIOR
                        </label>
                    </div>
                </div>
                <div class="w3-half w3-section w3-xlarge">
                    <div>
                        <input class="checkbox-budget" type="radio" name="clas" id="mage" required>
						<label for="mage">
                            <img src='<c:url value="/images/hero/elfMage.jpg"/>' alt="mage" class="w3-image" width="300" height="400">
                            MAGE
                        </label>
                    </div>
                </div>
            </div>
            <div class="w3-padding-large w3-center">
                <p class="w3-xlarge">SELECT NAME:</p>
            </div>
            <div class="w3-padding-large w3-center">
                <input type="text" id="name" name="name" placeholder="Name" class="w3-dark-grey w3-hover-black w3-xxlarge" required/>
            </div>
            <div class="w3-padding-large w3-center">
                <button type="submit" class="w3-button w3-light-grey w3-hover-green w3-xxlarge">START</button>
            </div>
        </form>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>

