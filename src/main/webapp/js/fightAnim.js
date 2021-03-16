function buttonRedirect(monster) {
    location.replace("monster/fight/"+monster);
}

function buttonAttack() {
    document.getElementById("normalAttackButton").style.display = "none";
    document.getElementById("skillAttackButton").style.display = "none";
    document.getElementById("heroImg").classList.add("w3-animate-right");
    document.getElementById("monsterImg").classList.add("w3-animate-left");
}
