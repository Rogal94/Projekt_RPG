function minusMap() {
    let map = document.getElementById('map');
    let formMap = document.getElementById('formMap');
    if(map.innerHTML > 20) {
        map.innerHTML = parseInt(map.innerHTML) - 5;
        formMap.value = map.innerHTML;
    }
}

function plusMap() {
    let map = document.getElementById('map');
    let formMap = document.getElementById('formMap');
    if(map.innerHTML < 100) {
        map.innerHTML = parseInt(map.innerHTML) + 5;
        formMap.value = map.innerHTML;
    }
}

function minusVolume() {
    let volume = document.getElementById('volume');
    let formVolume = document.getElementById('formVolume');
    if(volume.innerHTML > 0) {
        volume.innerHTML = parseInt(volume.innerHTML) - 5;
        formVolume.value = volume.innerHTML / 100;
    }
}

function plusVolume() {
    let volume = document.getElementById('volume');
    let formVolume = document.getElementById('formVolume');
    if(volume.innerHTML < 100) {
        volume.innerHTML = parseInt(volume.innerHTML) + 5;
        formVolume.value = volume.innerHTML /100;
    }
}