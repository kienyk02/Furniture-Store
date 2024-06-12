// color mode
var light_mode=document.querySelector(".light-mode")
var dark_mode=document.querySelector(".dark-mode")
light_mode.addEventListener("click",function(){
    light_mode.classList.add("mode--active")
    dark_mode.classList.remove("mode--active")
    document.querySelector("body").classList.remove("dark-mode")
})
dark_mode.addEventListener("click",function(){
    light_mode.classList.remove("mode--active")
    dark_mode.classList.add("mode--active")
    document.querySelector("body").classList.add("dark-mode")
})
function openLoginForm(){
	Swal.fire({
		title: 'Xin chào!',
		text:'Bạn cần đăng nhập để sử dụng dịch vụ này!',
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#4ac35a',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Đồng ý',
		cancelButtonText: 'Trở lại'
	}).then((result) => {
		if (result.isConfirmed) {
			document.querySelector(".modal").classList.add("open")
			document.querySelector(".auth-form--login").classList.add("open")
		}
	})
}
function addCart(id,soluong){
	//get api thêm cart mới
	fetch(`http://localhost:8080/add-cart?id=${id}&soluong=${soluong}`,
        {
            method:'put',
            mode:'cors',
        }
     ).then(()=>{
		Swal.fire({
	  		position: 'center',
	  		width:450,
	  		icon: 'success',
	  		title: 'Thành công',
	  		text:'Sản phẩm đã được thêm vào giỏ hàng!',
	  		showConfirmButton: false,
	  		timer: 700
	  	}).then(()=>location.reload())
	 })
	//window.location="add-cart?id="+id+"&soluong="+soluong
}
// mở box dki và đăng nhập
if(document.querySelector(".header__navbar-item-register")){
    document.querySelector(".header__navbar-item-register").onclick=function(){
        document.querySelector(".modal").classList.toggle("open")
        if(document.querySelector(".auth-form--register"))
        document.querySelector(".auth-form--register").classList.toggle("open")
    }
    document.querySelector(".header__navbar-item-login").onclick=function(){
        document.querySelector(".modal").classList.toggle("open")
        if(document.querySelector(".auth-form--login"))
        document.querySelector(".auth-form--login").classList.toggle("open")
    }
}
// đóng dki và đăng nhập
var comeback=document.querySelectorAll(".btn--comeback")
for(var i in comeback){
    comeback[i].onclick=function(){
        document.querySelector(".modal").classList.remove("open")
        if(document.querySelector(".auth-form--edit-delivery")){
            document.querySelector(".auth-form--edit-delivery").classList.remove("open")
        }
        document.querySelector(".auth-form--login").classList.remove("open")
        document.querySelector(".auth-form--register").classList.remove("open")
    }
}
// chuyển giữa dki và dnhap
var switch_authform=document.querySelectorAll(".auth-form__switch-btn")
for(var i in switch_authform){
    switch_authform[i].onclick=function(){
        document.querySelector(".auth-form--login").classList.toggle("open")
        document.querySelector(".auth-form--register").classList.toggle("open")
    }
}
function checkRegister(){
	let data=document.querySelectorAll(".auth-form--register input")
	if(data[2].value==data[3].value){
		document.querySelector('.auth-form--register form').submit()
	}else{
		document.querySelector(".errorregister").innerText="Xác nhận mật khẩu không chính xác!"
	}
}

var history_list=document.querySelector(".header__search-history-list")
history_list.addEventListener("mousedown",function(e){
    e.preventDefault()
})

// hiển thị nút clear nội dung thanh input
var content_search=document.querySelector(".header__search-input")
content_search.addEventListener("input",function(){
    if(content_search.value!=""){
        document.querySelector(".header__search-clear").style.display="block"
    }
    else{
        document.querySelector(".header__search-clear").style.display="none"
    }
})
// clear nội dung thanh input
document.querySelector(".header__search-clear").onclick=function(e){
    content_search.value=""
    document.querySelector(".header__search-clear").style.display="none"
}
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
//khi bấm tìm kiếm
var btn_search=document.querySelector(".header__search-btn")
btn_search.addEventListener("click",function(e){
    var content_search=document.querySelector(".header__search-input")
    if(content_search.value!=""){
        const search_item=document.createElement("li")
        search_item.classList.add("header__search-history-item")
        search_item.innerHTML=`<i class="fa-solid fa-clock-rotate-left"></i><a href="">${content_search.value}</a>`
        history_list.insertBefore(search_item,history_list.children[0])
    }
    window.location="home?key="+content_search.value;
})

function handleClearCart(cartid){
	fetch(`http://localhost:8080/delete-cart?cartid=${cartid}`,
        {
            method:'delete',
            mode:'cors',
        }
     ).then(()=>location.reload())
    //reload để cập nhật dữ liệu mới
	//window.location="delete-cart?cartid="+cartid
}
document.querySelectorAll(".fa-circle-xmark").forEach((clearCartBtn)=>{
	clearCartBtn.addEventListener("click",(e)=>{
		e.preventDefault()
	})
})

// App container
// mở danh mục sản phẩm
if(document.querySelector(".category-heading")){
    document.querySelector(".category-heading").onclick=function(){
        document.querySelector(".category-list").classList.toggle("open-category-list")
        document.querySelector(".category-heading__humber").classList.toggle("active")
    }
}// mở danh mục sản phẩm android
if(document.querySelector(".category-android-switch")){
    document.querySelector(".category-android-switch").onclick=function(e){
        document.querySelector(".category-android-switch").classList.toggle("active")
        document.querySelector(".category-wrapper").classList.toggle("active")
        // ngăn chặn scroll khi mở category
        document.querySelector("body").classList.toggle("active")
    }
}

// thêm category-extension vào category-item
document.querySelectorAll(".category-item").forEach( function(it,stt){
    var category_extension=document.createElement("div")
    category_extension.classList.add("category-extension")
    category_extension.innerHTML=`
        <ul class="category-extension__list category-extension__list--cost">
            <span>Giá</span>
            <li class="category-extension__item">Dưới 2 Triệu</li>
            <li class="category-extension__item"> 2 Triệu - 3 Triệu</li>
            <li class="category-extension__item"> 3 Triệu - 5 Triệu</li>
            <li class="category-extension__item">Trên 5 Triệu</li>
        </ul>
        <ul class="category-extension__list category-extension__list--trending">
            <span>Bán chạy</span>
            <li class="category-extension__item">Ghế Lamboghini hồng</li>
            <li class="category-extension__item">Ghế Lamboghini đế thép</li>
        </ul>
    `
    it.appendChild(category_extension)
})

// chuyến giá về dạng chuẩn
function convertPrice(priceInt){
    var priceStr=priceInt+""
    var res=""
    for(var i=priceStr.length;i>0;i-=3){
        res="."+priceStr.substring(i-3,i)+res
    }
    return res.substring(1)
}
//danh sách sản phẩm
products=[{
    link:"product_view.html",
    img:"http://edravn.com/media/product/377_gm3.jpg",
    name:"Bàn điện nâng hạ công thái học E-Dra ELT1460",
    price_old:"",
    price_cur:3990000,
    favorite: false,
    sales: 10,
    type:"Bàn",
    info:"siu"
},
{
    link:"",
    img:"https://hanvika.vn/wp-content/uploads/2022/11/2.png",
    name:"Bàn ikea 1 hộc gỗ MDF 1600*600*750",
    price_old:2900000,
    price_cur:2300000,
    favorite: false,
    sales: 15,
    type:"Bàn",
    info:"siu"
},
{   
    link:"",
    img:"https://noithatfufutech.com/upload/hinhthem/ban-ikea-trang-2-tu9-5570.jpg",
    name:"Bàn Ikea 2 hộc gỗ MDF 180*60*75",
    price_old:3500000,
    price_cur:2700000,
    favorite: false,
    sales: 5,
    type:"Bàn",
    info:"siu"
},
{
    link:"",
    img:"https://hanvika.vn/wp-content/uploads/2022/11/z3918202618786_d90d07ecef59743b9aefb404a57870a1-220x220.jpg",
    name:"Bàn ikea 2 hộc gỗ MDF 1800*600*750 màu trắng viền đen",
    price_old:3500000,
    price_cur:2800000,
    favorite: false,
    sales: 7,
    type:"Bàn",
    info:"siu"
},
{
    link:"",
    img:"https://owlgaming.vn/wp-content/uploads/2022/01/ghe-gaming-e-dra-hunter-egc206-sierra-blue-1.jpg",
    name:"Ghế chơi game E-dra Hunter EGC206 Sierra Blue",
    price_old:5590000,
    price_cur:4590000,
    favorite: false,
    sales: 20,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://thuthuatnhanh.com/wp-content/uploads/2022/04/Hinh-nen-co-Dang-cho-dien-thoai.jpg",
    name:"Siuuu",
    price_old:"",
    price_cur:9999999,
    favorite: false,
    sales: 13,
    type:"Cờ",
    info:"siu"
},
{
    link:"",
    img:"https://product.hstatic.net/200000637319/product/433_4_e1d858cb254e4430a0cf594254a819c0.jpg",
    name:"Ghế chơi game E-dra Fresh EGC230 Plus xám",
    price_old:4990000,
    price_cur:4390000,
    favorite: false,
    sales: 4,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://product.hstatic.net/1000129940/product/ghe-e-dra-iris-egc228---hang-chinh-hang-black_1655581899d04dc08ce9b3bfb6bec630_master.jpg",
    name:"ghế chơi game e-dra iris egc228 – black",
    price_old:4790000,
    price_cur:4490000,
    favorite: false,
    sales: 16,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://owlgaming.vn/wp-content/uploads/2020/06/e-dra-midnight-gaming-chair-egc205-01.jpg",
    name:"Ghế chơi game E-dra Midnight EGC205 – Midnight",
    price_old:2990000,
    price_cur:2590000,
    favorite: false,
    sales: 14,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://product.hstatic.net/200000637319/product/365_s1_a0f1a97c6d6e48c9aaa9cfb4fd191835.jpg",
    name:"ghế chơi game e-dra spider egc226",
    price_old:4690000,
    price_cur:4390000,
    favorite: false,
    sales: 21,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://phukienmaytinh.vn/wp-content/uploads/2020/05/e_dra_aresegc207_black_white__7_-800x800.png",
    name:"ghế e-dra ares egc207 – black white",
    price_old:3890000,
    price_cur:3690000,
    favorite: false,
    sales: 17,
    type:"Ghế",
    info:"siu"
},
{
    link:"",
    img:"https://product.hstatic.net/1000037809/product/thegioigear_ghe_choi_game_warrior_wgc206plus_white_pink_110630720ac24ee2bb3c0f5346b9ffd7_master.jpg",
    name:"Ghế game Warrior Raider Series – WGC206 Plus White/Pink",
    price_old:3690000,
    price_cur:3390000,
    favorite: false,
    sales: 6,
    type:"Ghế",
    info:"siu"
}
]


window.addEventListener("scroll",()=>{
    window.scrollY>700 ? document.querySelector(".comeback_navbar").style="display:block":
    document.querySelector(".comeback_navbar").style="display:none; "
})
