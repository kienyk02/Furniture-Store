if(document.querySelector(".delivery-informatio__edit")){
    document.querySelector(".delivery-informatio__edit").addEventListener("click",function(){
        document.querySelector(".modal").classList.toggle("open")
        document.querySelector(".auth-form--edit-delivery").classList.toggle("open")
    })
}