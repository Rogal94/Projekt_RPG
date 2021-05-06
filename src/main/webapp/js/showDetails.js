function closeItemDetails () {
    const details = document.getElementById('details');
    details.classList.add("w3-hide");
}

function closeSkillDetails () {
    const details = document.getElementById('detailsSkill');
    details.classList.add("w3-hide");
}

function closeStatDetails () {
    const details = document.getElementById('detailsStat');
    details.classList.add("w3-hide");
}

function showItemDetails (obj, e) {
    const details = document.getElementById('details');
    details.classList.remove("w3-hide");
    details.style.left = e.pageX + 'px';
    details.style.top = e.pageY + 'px';
    const lines = details.querySelectorAll('li');
    lines[1].firstElementChild.src = obj.dataset.url;
    lines[2].innerHTML = 'Name : ' + obj.dataset.name;
    if(obj.dataset.type === "weapon" || obj.dataset.type === "necklace" || obj.dataset.type === "ring") {
        lines[3].innerHTML = 'Attack : ' + obj.dataset.power;
    }else{
        lines[3].innerHTML = 'Defence : ' + obj.dataset.power;
    }
    if(obj.dataset.power > 0) {
        lines[4].innerHTML = 'Description: ' + obj.dataset.description;
        lines[5].children[0].classList.remove("w3-hide");
        lines[5].children[0].href = "/items/upgrade/equipped/" + obj.dataset.id;
        lines[5].children[1].innerHTML = "CHANGE";
        if(obj.dataset.grade === '5') {
            lines[5].children[0].classList.add("w3-hide");
        }
    }else{
        lines[2].innerHTML = 'Name : ' + obj.dataset.name.charAt(0).toUpperCase() + obj.dataset.name.slice(1);
        lines[4].innerHTML = 'Description : ' + 'You need ' + obj.dataset.name;
        lines[5].children[0].classList.add("w3-hide");
        lines[5].children[1].innerHTML = "EQUIP";
    }
    lines[5].children[1].href = "/items/list/change/" + obj.dataset.type;
}

function showItemDetailsList (obj, e) {
    const details = document.getElementById('details');
    details.classList.remove("w3-hide");
    details.style.left = e.pageX + 'px';
    details.style.top = e.pageY + 'px';
    const lines = details.querySelectorAll('li');
    lines[1].firstElementChild.src = obj.dataset.url;
    lines[2].innerHTML = 'Name : ' + obj.dataset.name;
    if(obj.dataset.type === "weapon" || obj.dataset.type === "necklace" || obj.dataset.type === "ring") {
        lines[3].innerHTML = 'Attack : ' + obj.dataset.power;
    }else{
        lines[3].innerHTML = 'Defence : ' + obj.dataset.power;
    }
    lines[4].innerHTML = 'Description : ' + obj.dataset.description;
    if(obj.dataset.grade === '5') {
        lines[5].children[0].classList.add("w3-hide");
    }
    lines[5].children[0].href = "/items/upgrade/unequipped/" + obj.dataset.id;
    lines[5].children[1].classList.add("w3-hide");
}

 function showItemDetailsShop (obj, e) {
     const details = document.getElementById('details');
     details.classList.remove("w3-hide");
     details.style.left = e.pageX + 'px';
     details.style.top = e.pageY + 'px';
     const lines = details.querySelectorAll('li');
     lines[1].firstElementChild.src = obj.dataset.url;
     lines[2].innerHTML = 'Name : ' + obj.dataset.name;
     if(obj.dataset.type === "weapon" || obj.dataset.type === "necklace" || obj.dataset.type === "ring") {
         lines[3].innerHTML = 'Attack : ' + obj.dataset.power;
     }else{
         lines[3].innerHTML = 'Defence : ' + obj.dataset.power;
     }
     lines[4].innerHTML = 'Description : ' + obj.dataset.description;
     lines[5].children[0].classList.add("w3-hide");
     lines[5].children[1].classList.add("w3-hide");
 }

function showSkillDetails (obj, e) {
    const details = document.getElementById('detailsSkill');
    details.classList.remove("w3-hide");
    details.style.left = e.pageX + 'px';
    details.style.top = e.pageY + 'px';
    const lines = details.querySelectorAll('li');
    lines[1].innerHTML = 'Name : ' + obj.dataset.name;
    lines[2].innerHTML = 'Cost : ' + obj.dataset.cost;
    lines[3].innerHTML = 'Description : ' + obj.dataset.description;
    lines[4].innerHTML = 'Rank 1 : ' + obj.dataset.rank1;
    lines[5].innerHTML = 'Rank 2 : ' + obj.dataset.rank2;
    lines[6].innerHTML = 'Rank 3 : ' + obj.dataset.rank3;
    lines[7].innerHTML = 'Rank 4 : ' + obj.dataset.rank4;
    lines[8].innerHTML = 'Rank 5 : ' + obj.dataset.rank5;
}

function showStatDetails (obj, e) {
    const details = document.getElementById('detailsStat');
    details.classList.remove("w3-hide");
    details.style.left = e.pageX + 'px';
    details.style.top = e.pageY + 'px';
    const lines = details.querySelectorAll('li');
    lines[1].innerHTML = obj.dataset.name.toUpperCase() + ' : ' + obj.dataset.description;
    lines[3].innerHTML = obj.dataset.nameSec.toUpperCase() + ' : ' + obj.dataset.descriptionSec;

}

