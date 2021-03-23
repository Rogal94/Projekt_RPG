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
<script src='<c:url value="/js/navBar.js"/>'></script>

<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLogin.jspf"%>
<div class="w3-padding-large" id="main">
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
        <div class="w3-row w3-center w3-section">
            <div class="w3-third w3-section w3-dark-grey">
                <div class="w3-row w3-section">
                    <span class="w3-xxlarge">ATTACK: <c:out value="${hero.attack}"/></span>
                </div>
            </div>
            <div class="w3-third w3-section w3-dark-grey w3-right">
                <div class="w3-row w3-section">
                    <span class="w3-xxlarge">DEFENCE: <c:out value="${hero.defense}"/></span>
                </div>
            </div>
        </div>
        <div class="w3-row w3-margin-bottom">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">POTIONS</li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">HEALTH POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br><c:out value="${hero.potionHealth}"/></div>
                    <div class="w3-quarter w3-section">
                        <div class="w3-dark-gray">
                            <div class="w3-red" style="height:36px;width:<c:out value="${(hero.healthPointsCurrent/hero.healthPointsMax)*100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.healthPointsCurrent}"/>/<c:out value="${hero.healthPointsMax}"/></p></div>
                        </div>
                    </div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/items/add/health"/>' class="w3-button w3-dark-grey w3-hover-black">RECOVER<br>100 POINTS</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">MANA POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br><c:out value="${hero.potionMana}"/></div>
                    <div class="w3-quarter w3-section">
                        <div class="w3-dark-gray">
                            <div class="w3-blue" style="height:36px;width:<c:out value="${(hero.manaPointsCurrent/hero.manaPointsMax)*100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.manaPointsCurrent}"/>/<c:out value="${hero.manaPointsMax}"/></p></div>
                        </div>
                    </div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/items/add/mana"/>' class="w3-button w3-dark-grey w3-hover-black">RECOVER<br>100 POINTS</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">STAMINA POTION</div>
                    <div class="w3-quarter w3-section">AMOUNT:<br><c:out value="${hero.potionStamina}"/></div>
                    <div class="w3-quarter w3-section">
                        <div class="w3-dark-gray">
                            <div class="w3-orange" style="height:36px;width:<c:out value="${(hero.staminaCurrent/hero.staminaMax)*100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.staminaCurrent}"/>/<c:out value="${hero.staminaMax}"/></p></div>
                        </div>
                    </div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/items/add/stamina"/>' class="w3-button w3-dark-grey w3-hover-black">RECOVER<br>100 POINTS</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="w3-row w3-center w3-section w3-light-grey">
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/item/weapon.jpg"/>' alt="weapon" class="w3-image" width="68" height="76">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="${weapon.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">Attack: <c:out value="${weapon.attack}"/></span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <a href='<c:url value="/items/list/weapon"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">CHANGE<br>WEAPON</a>
                    </span>
            </div>
        </div>
        <c:forEach items="${armorList}" var="armor">
        <div class="w3-row w3-center w3-section w3-light-grey">
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="/images/item/${armor.type}.jpg"/>' alt="helmet" class="w3-image" width="68" height="76">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <img src='<c:url value="${armor.imageUrl}"/>' alt="icon" class="w3-image" width="64" height="64">
                    </span>
            </div>
            <div class="w3-quarter w3-section">
                <span class="w3-xlarge">Defence: <c:out value="${armor.defense}"/></span>
            </div>
            <div class="w3-quarter w3-section">
                    <span class="w3-xlarge">
                        <a href='<c:url value="/items/list/${armor.type}"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black">CHANGE<br><c:out value="${fn:toUpperCase(armor.type)}"/></a>
                    </span>
            </div>
        </div>
        </c:forEach>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navItems')</script>
</html>