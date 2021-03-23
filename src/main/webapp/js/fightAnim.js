function buttonRedirectAttack(monster) {
    location.replace("/monsters/fight/"+monster+"/attack");
}
function buttonRedirectSkill(monster) {
    location.replace("/monsters/fight/"+monster+"/skill");
}

function buttonAttack() {
    document.getElementById("heroImg").classList.add("w3-animate-right");
    document.getElementById("monsterImg").classList.add("w3-animate-left");
    document.getElementById("normalAttackButton").style.display = "none";
    document.getElementById("skillAttackButton").style.display = "none";
}
