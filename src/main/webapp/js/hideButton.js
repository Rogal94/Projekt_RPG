window.addEventListener('DOMContentLoaded', (event) => {
    hideButton();
});

function hideButton() {
    const button = document.querySelectorAll('.adds');
    button.forEach(b=>{
        if(b.dataset.rank === '5') {
            b.classList.add('w3-hide');
        }
    });
}