const addButton = document.querySelector('.btn-add');
const btnAdd = document.querySelector('.btn-add');
const sideBar = document.querySelector('.side-bar');
const closeButton = document.querySelector('.btn-close');

addButton.addEventListener('click', function(){
    console.log(addButton.classList);
    if(sideBar.classList.contains('show-sidebar')){
        sideBar.classList.remove('show-sidebar')
    }
    else{
        sideBar.classList.add('show-sidebar');
    }
});

closeButton.addEventListener('click', function(){
    sideBar.classList.remove('show-sidebar');
});