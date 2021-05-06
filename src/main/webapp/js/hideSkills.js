function hideSkills(points) {
    const skills = document.querySelectorAll('.skills');
    skills.forEach(s => {
       if(points < s.dataset.cost) {
           s.classList.add('w3-hide');
       }
    });
}