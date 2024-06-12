imgs=["https://cdn.mos.cms.futurecdn.net/Dig8shFu2YRqrYQjq7gBJn-1200-80.jpg.webp",
"https://cdn.mos.cms.futurecdn.net/r8tSF5eFjkKBKmjB5Cm7dX-1200-80.jpg",
"https://cdn.mos.cms.futurecdn.net/vALMDgjf6vucD6z26PcQYH-1200-80.png",
"https://cdn.mos.cms.futurecdn.net/hCcn9LQdxRChRoS8MMieSR-1200-80.jpg",
"https://cdn.mos.cms.futurecdn.net/r8tSF5eFjkKBKmjB5Cm7dX-1200-80.jpg",
"https://www.gamersnexus.net/media/k2/items/cache/54b4716a3dc0cd733b26e61ebb10e28e_XL.jpg",
"https://www.everracer.com.au/images/everracer-og-image.jpg",
"https://m.media-amazon.com/images/S/aplus-media-library-service-media/00fcf50b-f994-449b-bb77-42d418013d1c.__CR0,0,970,600_PT0_SX970_V1___.png",
"https://storage-asset.msi.com/global/picture/image/feature/multimeda/Chairs/ch120i/kv.jpg",
"https://petapixel.com/assets/uploads/2021/10/Razer-Launched-an-Office-Chair-and-Its-Supposedly-Pretty-Great.jpg",
]
function createSlideImg(img){
    var slideImg=document.createElement("div")
    slideImg.innerHTML=`<img src="${img}" alt="">`
    return slideImg
}
imgs.forEach(function(item){
    document.querySelector(".slideshow-list-img").appendChild(createSlideImg(item))
    document.querySelector(".img-feature").appendChild(createSlideImg(item))
})
var imgFeature=document.querySelector(".img-feature")
var listImg=document.querySelectorAll(".slideshow-list-img img")
var prevBtn=document.querySelector(".slideshow-main .prev")
var nextBtn=document.querySelector(".slideshow-main .next")

listImg[0].parentElement.classList.add("slide-show-img--active")
imgFeature.style.width=`${listImg.length}00%`
var currentIndex=0;
function updateImgByIndex(index){
    document.querySelectorAll(".slideshow-list-img div").forEach(function(item){
        item.classList.remove("slide-show-img--active")
    })
    currentIndex=index
    imgFeature.style.marginLeft=`-${index}00%`
    listImg[index].parentElement.classList.add("slide-show-img--active")
}
listImg.forEach(function(img,index){
    img.addEventListener("click",function(e){
        updateImgByIndex(index)
    })
})
prevBtn.addEventListener("click",function(){
    currentIndex--
    if(currentIndex==-1) {
        currentIndex=listImg.length-1
        setTimeout(function(){
            imgFeature.style.transition="0s"
            updateImgByIndex(currentIndex)
        },0)
        setTimeout(function(){
            imgFeature.style.transition="1s"
        },100)
    }
    else 
    updateImgByIndex(currentIndex) 
})
nextBtn.addEventListener("click",function(){
    currentIndex++
    if(currentIndex==listImg.length){
        currentIndex=0
        setTimeout(function(){
            imgFeature.style.transition="0s"
            updateImgByIndex(currentIndex)
        },0)
        setTimeout(function(){
            imgFeature.style.transition="1s"
        },100)
    }
    else
    updateImgByIndex(currentIndex)
})
setInterval(function(){
    currentIndex++
    if(currentIndex==listImg.length){
        currentIndex=0
        setTimeout(function(){
            imgFeature.style.transition="0s"
            updateImgByIndex(currentIndex)
        },0)
        setTimeout(function(){
            imgFeature.style.transition="1s"
        },100)
    }
    else
    updateImgByIndex(currentIndex)
},5000)


