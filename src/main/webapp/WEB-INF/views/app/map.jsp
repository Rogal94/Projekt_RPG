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
    <div class="container-img w3-margin-top" style="width: <c:out value="${user.mapSize}"/>%">
        <img src='<c:url value="/images/map/worldMap.jpg"/>' alt="hero" class="w3-image w3-animate-zoom" width="100%">
        <a href='<c:url value="/map/CityOfHope/city"/>' class="button-img w3-light-grey w3-hover-black w3-xlarge" style="left:42%; top:12%">CityOfHope</a>
        <a href='<c:url value="/map/PumpkinFarm/monster/1"/>' class="button-img w3-dark-grey w3-hover-black" style="left:78%; top:39%">PumpkinFarm(LVL:1)</a>
        <a href='<c:url value="/map/AbandonedVillage/monster/2"/>' class="button-img w3-dark-grey w3-hover-black" style="left:76%; top:30%">AbandonedVillage(LVL:3)</a>
        <a href='<c:url value="/map/CityStreets/monster/3"/>' class="button-img w3-dark-grey w3-hover-black" style="left:60%; top:36%">CityStreets(LVL:5)</a>
        <a href='<c:url value="/map/OverlookGraveyard/monster/4"/>' class="button-img w3-dark-grey w3-hover-black" style="left:94%; top:42%">OverlookGraveyard(LVL:7)</a>
        <a href='<c:url value="/map/ForestCamp/monster/5"/>' class="button-img w3-dark-grey w3-hover-black" style="left:85%; top:12%">ForestCamp(LVL:9)</a>
        <a href='<c:url value="/map/WinterRockyPath/monster/6"/>' class="button-img w3-dark-grey w3-hover-black" style="left:88%; top:90%">WinterRockyPath(LVL:11)</a>
        <a href='<c:url value="/map/LavaPools/monster/7"/>' class="button-img w3-dark-grey w3-hover-black" style="left:14%; top:86%">LavaPools(LVL:13)</a>
        <a href='<c:url value="/map/ForestRiverConfluence/monster/8"/>' class="button-img w3-dark-grey w3-hover-black" style="left:26%; top:55%">ForestRiverConfluence(LVL:15)</a>
        <a href='<c:url value="/map/ForestPond/monster/9"/>' class="button-img w3-dark-grey w3-hover-black" style="left:15%; top:40%">ForestPond(LVL:17)</a>
        <a href='<c:url value="/map/RockyRoad/monster/10"/>' class="button-img w3-dark-grey w3-hover-black" style="left:13%; top:50%">RockyRoad(LVL:19)</a>
        <a href='<c:url value="/map/IcyOreDeposits/monster/11"/>' class="button-img w3-dark-grey w3-hover-black" style="left:82%; top:94%">IcyOreDeposits(LVL:21)</a>
        <a href='<c:url value="/map/NecromancersLair/monster/12"/>' class="button-img w3-dark-grey w3-hover-black" style="left:22%; top:68%">NecromancersLair(LVL:23)</a>
        <a href='<c:url value="/map/ForestEncounter/monster/13"/>' class="button-img w3-dark-grey w3-hover-black" style="left:96%; top:20%">ForestEncounter(LVL:25)</a>
        <a href='<c:url value="/map/Chapel/monster/14"/>' class="button-img w3-dark-grey w3-hover-black" style="left:94%; top:60%">Chapel(LVL:27)</a>
        <a href='<c:url value="/map/Waterfalls/monster/15"/>' class="button-img w3-dark-grey w3-hover-black" style="left:82%; top:80%">Waterfalls(LVL:29)</a>
        <a href='<c:url value="/map/LavaPit/monster/16"/>' class="button-img w3-dark-grey w3-hover-black" style="left:30%; top:86%">LavaPit(LVL:30)</a>
        <a href='<c:url value="/map/Altar/monster/1"/>' class="button-img w3-dark-grey w3-hover-black" style="left:13%; top:4%">Altar(Will be dungeon)</a>
        <a href='<c:url value="/map/ShadowCave/monster/1"/>' class="button-img w3-dark-grey w3-hover-black" style="left:53%; top:88%">ShadowCave(Will be dungeon)</a>
    </div>
    <div class="w3-padding-64 w3-content w3-text-grey" id="body">
    </div>
</div>
<%@include file="/WEB-INF/jspf/footer.jspf"%>
</body>
<script>navBarBlack('navMap')</script>
</html>