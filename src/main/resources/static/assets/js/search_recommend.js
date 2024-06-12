let input_search=document.querySelector(".header__search-input")
input_search.addEventListener("input",(e)=>{
    		let content=e.target.value
    		console.log(content)
	    	fetch(`http://localhost:8080/api/search?s=${content}`)
	        .then(response => response.json())
	        .then(data => {
	        	showSearchRecommend(data)
	        })
})
function showSearchRecommend(products){
    		let listSR=document.querySelector(".header__search-history-list")
    		let item=""
    		for(let product of products){
    			item+=`
    				<li class="header__search-history-item">
	                    <a href=${"http://localhost:8080/product-view?id="+product.id}>
	                    	<img style="padding-right:5px" width="40px"  src=${"./assets/img/"+product.anh} >
	                    	<span>${product.ten}</span>
	                    </a>
                	</li>
    			`
    		}
    		listSR.innerHTML=item
}