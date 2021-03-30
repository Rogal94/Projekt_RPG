function rewardButtonShow() {
    const elem = document.getElementById("rewardButton");
    const progress = document.getElementById("progress").innerText.split("/");
    if(parseInt(progress[0]) >= parseInt(progress[1])) elem.classList.remove("w3-hide");
}