function generateObjectToArray(map){
	data=[]
	for(let key in map){
		data=[...data,[key,map[key]]]
	}
	return data
}
fetch(`http://localhost:8080/api/piechart`)
.then(response => response.json())
.then(dataobject => 
	{
		google.charts.load("current", {packages:["corechart"]});
		google.charts.setOnLoadCallback(drawChart);
		function drawChart() {
			var data = google.visualization.arrayToDataTable([
				['Task','Statistics on the number of products sold by category'],
			    	...generateObjectToArray(dataobject)
				]);
			var options = {
				title: 'Thống kê số lượng sản phẩm đã bán theo danh mục',
				is3D: true,
				width:900,
                height:500
			};

			var chart = new google.visualization.PieChart(document.getElementById('piechart_3d'));
			chart.draw(data, options);
		}
	}
)  

fetch(`http://localhost:8080/api/divchart`)
.then(response => response.json())
.then(dataobject =>{
		google.charts.load('current', {packages: ['corechart', 'bar']});
		google.charts.setOnLoadCallback(drawBasic);

		function drawBasic() {

      		var data = google.visualization.arrayToDataTable([
	        ['Tuần', 'Doanh thu',],
	        	...generateObjectToArray(dataobject)
      		]);

			var options = {
		        title: 'Thống kê doanh thu theo tuần',
		        chartArea: {width: '50%'},
		        width:900,
                height:500,
		        hAxis: {
		          title: 'VND',
		          minValue: 0
		        },
		        vAxis: {
		          title: 'Tuần'
		        }
			};

      		var chart = new google.visualization.BarChart(document.getElementById('chart_div'));

      	chart.draw(data, options);
   		}	
	}
)