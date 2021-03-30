
function attackClick(monsterId) {
    document.querySelectorAll('.color-change').forEach((element)=>{element.classList.add('expanded')});
    setTimeout(()=>{document.querySelectorAll('.color-change').forEach((element)=>{element.classList.remove('expanded')})},1500);
    setTimeout(()=>{window.location.href = '/monsters/fight/'+ monsterId +'/start'},1500);
}