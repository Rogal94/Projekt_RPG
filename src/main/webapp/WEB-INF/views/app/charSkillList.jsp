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
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row-padding" style="margin:50px -16px">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">SKILLS</li>
                <c:forEach items="${skillList}" var="skill">
                    <li class="w3-padding-16"
                        data-name="<c:out value="${skill.name}"/>"
                        data-description="<c:out value="${skill.description}"/>"
                        data-cost="<c:out value="${skill.cost}"/>"
                            <c:if test="${skill.type.equals('damage')}">
                                <c:forEach items="${damageSkill}" var="skillD">
                                    data-rank${skillD.skillRank}="<c:out value="${skillD.damage}"/>"
                                </c:forEach>
                            </c:if>
                            <c:if test="${skill.type.equals('buff')}">
                                <c:forEach items="${buffSkill}" var="skillB">
                                    data-rank${skillB.skillRank}="<c:out value="${skillB.effect}"/>"
                                </c:forEach>
                            </c:if>
                        onclick="setTimeout(showSkillDetails, 100 , this , event)">
                        <c:out value="${fn:toUpperCase(skill.name)}"/>
                        <a href='<c:url value="/character/skill/add/${skill.id}/new"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-right w3-margin-left">ADD</a>
                    </li>
                </c:forEach>
                <li class="w3-dark-grey w3-padding-16 w3-center">POINTS : <c:out value="${hero.skillPoints}"/></li>
            </ul>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/skillDetails.jspf"%>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
</html>
