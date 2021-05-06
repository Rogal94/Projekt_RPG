function buttonRedirectAttack(monster) {
    location.replace("/monsters/fight/"+monster+"/attack");
}
function buttonRedirectSkill(monster, skillId) {
    location.replace("/monsters/fight/"+monster+"/skill?skillId=" +skillId);
}

function buttonAttack() {
    document.getElementById("heroImg").classList.add("w3-animate-right");
    document.getElementById("monsterImg").classList.add("w3-animate-left");
    document.getElementById("normalAttackButton").style.display = "none";
    document.querySelectorAll("#skillAttackButton").forEach(s=>{s.style.display = "none"});
}
