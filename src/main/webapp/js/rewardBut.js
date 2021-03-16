function rewardButtonShow() {
    const elem = document.getElementById("rewardButton");
    const progress = document.getElementById("progress").innerText.split("/");
    if(progress[0]>=progress[1]) elem.classList.remove("w3-hide");
}