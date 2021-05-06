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
<script src='<c:url value="/js/showDetails.js"/>'></script>


<body class="w3-black">
<%@include file="/WEB-INF/jspf/navBarLoginChar.jspf"%>
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
                    <span class="w3-xxlarge">DEFENCE: <c:out value="${hero.defence}"/></span>
                </div>
            </div>
        </div>
        <div class="w3-row w3-center w3-section">
            <div class="">
                <ul class="w3-ul w3-white">
                    <li class="w3-dark-grey w3-xlarge w3-padding-24">EQUIPMENT</li>
                    <li class="w3-row w3-center w3-xlarge">
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('necklace').imageUrl}"/>' alt="necklace" class="w3-image grade<c:out value="${itemList.get('necklace').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('necklace').id}"/>"
                                 data-name="<c:out value="${itemList.get('necklace').name}"/>"
                                 data-url="<c:out value="${itemList.get('necklace').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('necklace').description}"/>"
                                 data-type="<c:out value="${itemList.get('necklace').type}"/>"
                                 data-grade="<c:out value="${itemList.get('necklace').grade}"/>"
                                 data-power="<c:out value="${itemList.get('necklace').attack}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('helmet').imageUrl}"/>' alt="helmet" class="w3-image grade<c:out value="${itemList.get('helmet').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('helmet').id}"/>"
                                 data-name="<c:out value="${itemList.get('helmet').name}"/>"
                                 data-url="<c:out value="${itemList.get('helmet').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('helmet').description}"/>"
                                 data-type="<c:out value="${itemList.get('helmet').type}"/>"
                                 data-grade="<c:out value="${itemList.get('helmet').grade}"/>"
                                 data-power="<c:out value="${itemList.get('helmet').defence}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('ring').imageUrl}"/>' alt="ring" class="w3-image grade<c:out value="${itemList.get('ring').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('ring').id}"/>"
                                 data-name="<c:out value="${itemList.get('ring').name}"/>"
                                 data-url="<c:out value="${itemList.get('ring').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('ring').description}"/>"
                                 data-type="<c:out value="${itemList.get('ring').type}"/>"
                                 data-grade="<c:out value="${itemList.get('ring').grade}"/>"
                                 data-power="<c:out value="${itemList.get('ring').attack}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                    </li>
                    <li class="w3-row w3-center w3-xlarge">
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('weapon').imageUrl}"/>' alt="weapon" class="w3-image grade<c:out value="${itemList.get('weapon').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('weapon').id}"/>"
                                 data-name="<c:out value="${itemList.get('weapon').name}"/>"
                                 data-url="<c:out value="${itemList.get('weapon').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('weapon').description}"/>"
                                 data-type="<c:out value="${itemList.get('weapon').type}"/>"
                                 data-grade="<c:out value="${itemList.get('weapon').grade}"/>"
                                 data-power="<c:out value="${itemList.get('weapon').attack}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('armor').imageUrl}"/>' alt="armor" class="w3-image grade<c:out value="${itemList.get('armor').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('armor').id}"/>"
                                 data-name="<c:out value="${itemList.get('armor').name}"/>"
                                 data-url="<c:out value="${itemList.get('armor').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('armor').description}"/>"
                                 data-type="<c:out value="${itemList.get('armor').type}"/>"
                                 data-grade="<c:out value="${itemList.get('armor').grade}"/>"
                                 data-power="<c:out value="${itemList.get('armor').defence}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                        <div class="w3-third">
                            <img src='<c:url value="${itemList.get('gloves').imageUrl}"/>' alt="gloves" class="w3-image grade<c:out value="${itemList.get('gloves').grade}"/>" width="128" height="128"
                                 data-id="<c:out value="${itemList.get('gloves').id}"/>"
                                 data-name="<c:out value="${itemList.get('gloves').name}"/>"
                                 data-url="<c:out value="${itemList.get('gloves').imageUrl}"/>"
                                 data-description="<c:out value="${itemList.get('gloves').description}"/>"
                                 data-type="<c:out value="${itemList.get('gloves').type}"/>"
                                 data-grade="<c:out value="${itemList.get('gloves').grade}"/>"
                                 data-power="<c:out value="${itemList.get('gloves').defence}"/>"
                                 onclick="showItemDetails(this, event)">
                        </div>
                    </li>
                    <li class="w3-row w3-center w3-xlarge">
                        <img src='<c:url value="${itemList.get('pants').imageUrl}"/>' alt="pants" class="w3-image grade<c:out value="${itemList.get('pants').grade}"/>" width="128" height="128"
                             data-id="<c:out value="${itemList.get('pants').id}"/>"
                             data-name="<c:out value="${itemList.get('pants').name}"/>"
                             data-url="<c:out value="${itemList.get('pants').imageUrl}"/>"
                             data-description="<c:out value="${itemList.get('pants').description}"/>"
                             data-type="<c:out value="${itemList.get('pants').type}"/>"
                             data-grade="<c:out value="${itemList.get('pants').grade}"/>"
                             data-power="<c:out value="${itemList.get('pants').defence}"/>"
                             onclick="showItemDetails(this, event)">
                    </li>
                    <li class="w3-row w3-center w3-xlarge">
                        <img src='<c:url value="${itemList.get('boots').imageUrl}"/>' alt="boots" class="w3-image grade<c:out value="${itemList.get('boots').grade}"/>" width="128" height="128"
                             data-id="<c:out value="${itemList.get('boots').id}"/>"
                             data-name="<c:out value="${itemList.get('boots').name}"/>"
                             data-url="<c:out value="${itemList.get('boots').imageUrl}"/>"
                             data-description="<c:out value="${itemList.get('boots').description}"/>"
                             data-type="<c:out value="${itemList.get('boots').type}"/>"
                             data-grade="<c:out value="${itemList.get('boots').grade}"/>"
                             data-power="<c:out value="${itemList.get('boots').defence}"/>"
                             onclick="showItemDetails(this, event)">
                    </li>
                </ul>
            </div>
        </div>
        <div class="w3-row">
            <ul class="w3-ul w3-white w3-opacity w3-hover-opacity-off">
                <li class="w3-dark-grey w3-xlarge w3-padding-24 w3-center">POTIONS</li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/items/potion/healthPotion.png"/>' alt="potion" class="w3-image" width="64" height="64" style="border: 5px solid green">
                    </div>
                    <div class="w3-quarter w3-section">
                        <div class="w3-dark-gray">
                            <div class="w3-green" style="height:36px;width:<c:out value="${(hero.healthPointsCurrent/hero.healthPointsMax)*100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.healthPointsCurrent}"/>/<c:out value="${hero.healthPointsMax}"/></p></div>
                        </div>
                    </div>
                    <div class="w3-quarter w3-section">HEALTH POTION<br>x <c:out value="${hero.potionHealth}"/></div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/items/add/health"/>' class="w3-button w3-dark-grey w3-hover-black">USE</a>
                        <a href='<c:url value="/items/add/health5"/>' class="w3-button w3-dark-grey w3-hover-black">USE x 5</a>
                    </div>
                </li>
                <li class="w3-row w3-center w3-xlarge">
                    <div class="w3-quarter w3-section">
                        <img src='<c:url value="/images/items/potion/staminaPotion.png"/>' alt="potion" class="w3-image" width="64" height="64" style="border: 5px solid pink">
                    </div>
                    <div class="w3-quarter w3-section">
                        <div class="w3-dark-gray">
                            <div class="w3-pink" style="height:36px;width:<c:out value="${(hero.staminaCurrent/hero.staminaMax)*100}"/>%"><p class="w3-center w3-text-white"><c:out value="${hero.staminaCurrent}"/>/<c:out value="${hero.staminaMax}"/></p></div>
                        </div>
                    </div>
                    <div class="w3-quarter w3-section">STAMINA POTION<br>x <c:out value="${hero.potionStamina}"/></div>
                    <div class="w3-quarter w3-section">
                        <a href='<c:url value="/items/add/stamina"/>' class="w3-button w3-dark-grey w3-hover-black">USE</a>
                        <a href='<c:url value="/items/add/stamina5"/>' class="w3-button w3-dark-grey w3-hover-black">USE x 5</a>
                    </div>
                </li>
            </ul>
        </div>
        <div class="w3-white w3-opacity w3-hover-opacity-off w3-section">
            <div class="w3-row w3-center w3-light-grey w3-xxlarge w3-margin">
                <a href='<c:url value="/items/list/change/item"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-margin w3-border">ALL SUITABLE ITEMS IN BAG</a>
            </div>
            <div class="w3-row w3-center w3-light-grey w3-xxlarge w3-margin">
                <a href='<c:url value="/items/list/item"/>' class="w3-button w3-dark-grey w3-padding-small w3-hover-black w3-margin w3-border">ALL ITEMS IN BAG</a>
            </div>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
<%@include file="/WEB-INF/jspf/itemDetails.jspf"%>
</body>
<script>navBarBlack('navItems')</script>
</html>