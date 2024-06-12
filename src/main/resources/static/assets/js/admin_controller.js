// chọn category item
document.querySelectorAll(".category__item").forEach(function(item,i){
    item.addEventListener("click",function(){
        document.querySelectorAll(".category__item").forEach(function(it){
            it.classList.remove("category__item--select")
        })
        item.classList.add("category__item--select")
        if(i==0){
            document.querySelector(".products").style.display="flex"
            document.querySelector(".orders").style.display="none"
            document.querySelector(".category_manager").style.display="none"
            document.querySelector(".pie-chart").style.display="none"
        }else if(i==1){
            document.querySelector(".products").style.display="none"
            document.querySelector(".orders").style.display="block"
            document.querySelector(".category_manager").style.display="none"
            document.querySelector(".pie-chart").style.display="none"
        }else if(i==2){
			document.querySelector(".products").style.display="none"
            document.querySelector(".orders").style.display="none"
            document.querySelector(".category_manager").style.display="flex"
            document.querySelector(".pie-chart").style.display="none"
		}else{
			document.querySelector(".products").style.display="none"
            document.querySelector(".orders").style.display="none"
            document.querySelector(".category_manager").style.display="none"
            document.querySelector(".pie-chart").style.display="block"
		}
    })
})
 function confirmDeleteProduct(id){
    	Swal.fire({
    		title: 'Bạn muốn xóa sản phẩm này?',
    		icon: 'question',
    		showCancelButton: true,
    		confirmButtonColor: '#4ac35a',
    		cancelButtonColor: '#d33',
    		confirmButtonText: 'Đồng ý',
    		cancelButtonText: 'Hủy bỏ'
    	}).then((result) => {
    		if (result.isConfirmed) {
        	  	fetch(`http://localhost:8080/controlmode/product/delete/${id}`,
        	  		{
        	  			method:'delete',
        	  			mode:'cors',
        	  		}
        		).then(()=>{
					Swal.fire({
				  		position: 'center',
				  		width:450,
				  		icon: 'success',
				  		title: 'Thành công',
				  		text:'Xóa sản phẩm thành công!',
				  		showConfirmButton: false,
				  		timer: 700
				  	}).then(()=>location.reload())//reload để cập nhật dữ liệu mới
				 })
        		
        				
    		}
    	})
    
    }
 function confirmDeleteCategory(id){
    	Swal.fire({
    		title: 'Chú ý!',
    		text:'Các sản phẩm cũng bị xóa theo danh mục',
    		icon: 'warning',
    		showCancelButton: true,
    		confirmButtonColor: '#4ac35a',
    		cancelButtonColor: '#d33',
    		confirmButtonText: 'Đồng ý',
    		cancelButtonText: 'Hủy bỏ'
    	}).then((result) => {
    		if (result.isConfirmed) {
        	  	fetch(`http://localhost:8080/controlmode/category/delete/${id}`,
        	  		{
        	  			method:'delete',
        	  			mode:'cors',
        	  		}
        		).then(()=>{
					Swal.fire({
				  		position: 'center',
				  		width:450,
				  		icon: 'success',
				  		title: 'Thành công',
				  		text:'Xóa Danh mục thành công!',
				  		showConfirmButton: false,
				  		timer: 700
				  	}).then(()=>location.reload())//reload để cập nhật dữ liệu mới
				 })
        		
        				
    		}
    	})
    
    }
document.querySelectorAll(".orders__view").forEach((orderview)=>{
	orderview.addEventListener("click",(e)=>{
	document.querySelectorAll(".orders__view").forEach((orderview1)=>{
		if(orderview1!=orderview){
			orderview1.querySelector(".orders__view-detail").classList.remove("open")                			
		}
	})
	orderview.querySelector(".orders__view-detail").classList.toggle("open")        
	})
})
document.querySelectorAll(".orders__edit.orders__edit-active").forEach((orderedit)=>{
	orderedit.addEventListener("click",(e)=>{
		document.querySelectorAll(".orders__edit.orders__edit-active").forEach((orderedit1)=>{
			if(orderedit1!=orderedit){
            	orderedit1.querySelector(".orders__edit-options").classList.remove("open")                			
        	}
    	})
    	orderedit.querySelector(".orders__edit-options").classList.toggle("open")        
	})
})
function handleDeleteOrder(idorder){
    		Swal.fire({
    			title: 'Bạn muốn hủy Order này?',
    			icon: 'question',
    			showCancelButton: true,
    			confirmButtonColor: '#4ac35a',
    			cancelButtonColor: '#d33',
    			confirmButtonText: 'Đồng ý',
    			cancelButtonText: 'Hủy bỏ'
    		}).then((result) => {
    	  		if (result.isConfirmed) {
					fetch(`http://localhost:8080/deleteOrder?id=${idorder}`,
        	  		{
        	  			method:'delete',
        	  			mode:'cors',
        	  		}
        			).then(()=>{
						Swal.fire({
					  		position: 'center',
					  		width:450,
					  		icon: 'success',
					  		title: 'Thành công',
					  		text:'Xóa Order thành công!',
					  		showConfirmButton: false,
					  		timer: 700
				  		}).then(()=>location.reload())//reload để cập nhật dữ liệu mới  
				 	})
        			
    	  		}
    		})
    	}
