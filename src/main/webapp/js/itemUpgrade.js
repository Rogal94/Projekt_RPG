function buttonRedirect(item, equipped) {
    if(equipped) {
        location.replace("/items/upgrade/try/equipped/"+item);
    }else{
        location.replace("/items/upgrade/try/unequipped/"+item);
    }
}

function itemUpgrade() {
    document.getElementById("zoom").classList.add("upgrade-animate");
}