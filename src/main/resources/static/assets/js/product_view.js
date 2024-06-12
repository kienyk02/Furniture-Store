let amountElement=document.getElementById("amount")
function renderAmount(amount){
    amountElement.value=amount
}
function handlePlus(){
    amountElement.value= parseInt(amountElement.value)+1
}
function handleMinus(){
    amountElement.value=Math.max(1,parseInt(amountElement.value)-1)
}
if(amountElement!=null){
	amountElement.addEventListener("input",()=>{
	    amountElement.value=isNaN(parseInt(amountElement.value))? 1:parseInt(amountElement.value)
	})	
}
function addCarts(id){
	addCart(id,amountElement.value)
}
// zoom img product
const result = document.querySelector('.result')
imgZoom=document.querySelector('.product-view__img')
var size=2
imgZoom.addEventListener('mousemove', function (e) {
	result.classList.remove('hide')
    const img = imgZoom.querySelector('img')
	topRs =e.clientY
	leftRs = e.clientX
    // offsetX : vị trí con trỏ so với border ảnh
    // offsetWidth: kích thước ảnh
    let x=(e.offsetX / img.width) * 100
    let y = (e.offsetY / img.height) * 100
    result.style.cssText=`
        background-image: url(${img.src});
        background-size: ${img.width * size}px ${img.height * size}px;
        background-position : ${x}% ${y}%;
        top: ${topRs}px;
        left: ${leftRs}px;
    `
})

imgZoom.addEventListener('mouseleave', function (e) {
	result.classList.add('hide')
})

//handle comment
        const pid=window.location.href.split("?id=")[1]
        console.log(pid)
    	let numbStar=5
    	voteStars=document.querySelectorAll(".vote-star")
    	voteStars.forEach((star,i)=>{
    		star.addEventListener('click',()=>{
    			numbStar=i+1
    			voteStars.forEach((star1,i1)=>{
    				if(i1<numbStar){
    					star1.classList.add('active')
    				}else{
    					star1.classList.remove('active')
    				}
    			})
    		})
    	})

        function getApi(url){
            fetch(url)
            .then(response => response.json())
            .then(data => showComment(data))
        }
        function showComment(comments){
            let commentString=``;
            for(let comment of comments){              
                commentString+=`
                <li class="comment-item">
                    <div class="comment-heading">
                        <div>
                        <span class="comment-username">${comment.user.ten!='' ? comment.user.ten:comment.user.username}</span>
                        <sup>
                        <span style=${comment.user.role!=1 ? "display:none":""} class="product-item__stars">
                            <i style="font-size:12px" class=${comment.vote >0 ? '"fa-solid fa-star active"':'"fa-solid fa-star"' }></i>
                            <i style="font-size:12px" class=${comment.vote >1 ? '"fa-solid fa-star active"':'"fa-solid fa-star"' }></i>
                            <i style="font-size:12px" class=${comment.vote >2 ? '"fa-solid fa-star active"':'"fa-solid fa-star"' }></i>
                            <i style="font-size:12px" class=${comment.vote >3 ? '"fa-solid fa-star active"':'"fa-solid fa-star"' }></i>
                            <i style="font-size:12px" class=${comment.vote >4 ? '"fa-solid fa-star active"':'"fa-solid fa-star"' }></i>
                        </span>
                        <span style=${comment.user.role==2 ? "color:red":"display:none"}>admin</span>
                        </sup>
                        </div>
                        <span class="comment-time">${comment.date}</span>
                    </div>
                    <div class="comment-content">
                        ${comment.content}
                    </div>
                </li>
                `
            }
            document.querySelector(".comment-list").innerHTML=commentString
            if(commentString=='') document.querySelector(".comment-list").innerHTML="Bạn hãy là người nhận xét đầu tiên..."
        }
        getApi(`http://localhost:8080/api/comment/${pid}`)
        function handleAddComment(){
            fetch(`http://localhost:8080/api/comment/add/${pid}?num=${numbStar}`,{
                method:"POST",
                mode:"cors",
                body: document.querySelector("textarea").value!="" ? document.querySelector("textarea").value:" ",
                headers:{
                    'Content-Type':'application/json; charset=UTF-8',
                }
            })
            .then((response)=>{
            	getApi(`http://localhost:8080/api/comment/${pid}`)
            	document.querySelector("textarea").value=''
            	return response.json()
            	}).then(data=>{
					if(!data){
						Swal.fire({
			  			  position: 'center',
			  			  width:450,
			  			  icon: 'warning',
			  			  title: 'Thất bại',
			  			  text:'Bạn chưa mua sản phẩm này!',
			  			  showConfirmButton: false,
			  			  timer: 1500
			  			})
					}
				})
            	
        }