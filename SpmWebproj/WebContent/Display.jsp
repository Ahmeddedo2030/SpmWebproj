<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Form</title>

<link rel="stylesheet" type="text/css" href="css/style.css">

<link rel="stylesheet" href="http://lee.dkfirst.cn/lee_loading.css">
 <script src="http://lee.dkfirst.cn/lee_loading.js"></script>

</head>
<style> //等待信号的css
spinner {
 width: 60px;
 height: 60px;
 background-color: #67CF22;
 
 margin: 100px auto;
 -webkit-animation: rotateplane 1.2s infinite ease-in-out;
 animation: rotateplane 1.2s infinite ease-in-out;
 
}
 
@-webkit-keyframes rotateplane {
 0% { -webkit-transform: perspective(120px) }
 50% { -webkit-transform: perspective(120px) rotateY(180deg) }
 100% { -webkit-transform: perspective(120px) rotateY(180deg) rotateX(180deg) }
}
 
@keyframes rotateplane {
 0% {
 transform: perspective(120px) rotateX(0deg) rotateY(0deg);
 -webkit-transform: perspective(120px) rotateX(0deg) rotateY(0deg)
 } 50% {
 transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg);
 -webkit-transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg)
 } 100% {
 transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
 -webkit-transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
 }
}


</style>
<script type="text/javascript">
	function test() {
		document.getElementById('1').style.display = 'none';
		document.getElementById('2').style.display = 'none';
		document.getElementById('3').style.display = 'none';
		document.getElementById('4').style.display = 'none';
		document.getElementById('5').style.display = 'none';
		document.getElementById('6').style.display = 'none';
		var value = document.getElementById('select').value;
		document.getElementById(value).style.display = 'block';
	}
	function test1() {
		document.getElementById('7').style.display = 'none';
		document.getElementById('8').style.display = 'none';
		document.getElementById('9').style.display = 'none';
		document.getElementById('10').style.display = 'none';
		document.getElementById('11').style.display = 'none';
		document.getElementById('12').style.display = 'none';
		var value = document.getElementById('select1').value;
		document.getElementById(value).style.display = 'block';
	}
</script>
<body>

	<div id="wrapper">
		<div id="header">
			<div id="subheader">
				<div class="container">

					<li><a href="login.jsp">Logout</a></li>
					<li><a href="startseite.jsp?signal=0">Home</a></li>


				</div>
			</div>

		</div>
	</div>
	<!--main Header-->
	<div id="main-header">

		<div id="logo">

			<span id="sp1">Baudas.de</span>

		</div>
		<!--Suchen-->
		<div id="search">
			<form action="">

				<input class="searchfeld" type="text" name="text"
					placeholder="Hier ihre Suche bitte Eingeben... "> <input
					class="searchsubmit" type="submit" name="submit" value="Suchen">

			</form>

		</div>
	</div>

	<!-- <h2><input type="button" value="TF_Artikel_Umsatz" name="bar1">&nbsp;
    <input type="button" value="TF_Artikel_DBeitrag" name="bar2">&nbsp;
    <input type="button" value="Best_Z_T" name="pie">&nbsp;
    </h2>-->
    <div style="position: relative; top: 10px;  text-align: center">
			<select id="select" onchange="test()">
				<option value="1" selected>bitte ausweahlen</option>
				<option value="2" >Top und Flop Artikel nach Umsatz</option>
				<option value="3" >Top und Flop Artikel nach Deckungsbeitrag</option>
				<option value="4" >Beste- und Schlechteste Kaufszeit und Kaufstag</option>
				<option value="5">Kunde_Basic_Information</option>
				<option value="6">Andere</option>
			</select>			
	
			<div style="position: relative; top: 10px; text-align: center">
			    <div id="1" style="display: block">
			    </div>
				<div id="2" style="display: none">
				<input type="button" value="TF_Artikel_Umsatz" name="bar1" ></div>
				<div id="3" style="display: none">
				<input type="button" value="TF_Artikel_DBeitrag" name="bar2"></div>
				<div id="4" style="display: none">
				<input type="button" value="Best_Z_T" name="pie1"></div>
				<div id="5" style="display: none">
				<input type="button" value="Basic_Information" name="pie2"></div></div>
				<div id="6" style="display: none">
				    Input:<input id='newmassnahme' type='text' >
					<button type="submit" name="submit" class="button" value="submit">submit</button>
				</div>
            </div>
         <div style="position: relative; top: 50px; text-align: center">
			<select id="select1" onchange="test1()">
				<option value="7" selected>choose record</option>
				<option value="8" >1. record</option>
				<option value="9" >2. record</option>
				<option value="10" >3. record</option>
				<option value="11">4. record</option>
				<option value="12">5. record</option>
			</select>			
	
			<div style="position: relative; top: 10px;text-align: center">
			    <div id="7" style="display: block">
			    </div>
				<div id="8" style="display: none">
				<input type="button" value="choose"  onclick="check1()"></div>
				<div id="9" style="display: none">
				<input type="button" value="choose" onclick="check2()"></div>
				<div id="10" style="display: none">
				<input type="button" value="choose" onclick="check3()"></div>
				<div id="11" style="display: none">
				<input type="button" value="choose" onclick="check4()"></div>
				<div id="12" style="display: none">
				<input type="button" value="choose" onclick="check5()">
				</div>
            </div>
     </div>
<script>

function check1(){
	  location.href = "/SpmWebproj/validation2_1?change=1";
}
function check2(){
	location.href = "/SpmWebproj/validation2_1?change=2";
}
function check3(){
	location.href = "/SpmWebproj/validation2_1?change=3";
}
function check4(){
	location.href = "/SpmWebproj/validation2_1?change=4";
}
function check5(){
	location.href = "/SpmWebproj/validation2_1?change=5";
}
</script>


<!-- 为 ECharts 准备四个具备大小（宽高）的显示区域 -->
<div id="main1" style="position: relative; top: 60px; width: 1800px;height:1200px; border:2px solid red; text-align: center"  >
</div>
<div id="main2" style="position: relative; top: 60px; width: 1800px;height:1200px; border:2px solid white; text-align: center" >
</div>
<div id="main3" style="position: absoulte; transform:translate(10%, 0%); top: 60px; width: 40%;height:600px;  border:2px solid white; text-align: center" >
</div>
<div id="main4" style="position: absoulte; transform:translate(130%, -89%);  width: 40%;height:600px;  border:2px solid white; text-align: center" >
</div>

<div id="main5" style="position: absoulte; transform:translate(10%, 0%); top: 60px; width: 40%;height:600px;  border:2px solid white; text-align: center" >
</div>
<div id="main6" style="position: absoulte; transform:translate(130%, -100%); top: 60px; width:40%;height:600px;  border:2px solid white; text-align: center" >
</div>
<div id="main7" style="position: absoulte; transform:translate(10%, -100%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>
<div id="main8" style="position: absoulte; transform:translate(130%, -200%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>

<div id="main9" style="position: absoulte; transform:translate(10%, -200%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>
<div id="main10" style="position: absoulte; transform:translate(130%, -300%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>
<div id="main11" style="position: absoulte; transform:translate(10%, -300%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>
<div id="main12" style="position: absoulte; transform:translate(130%, -400%); top: 60px; width: 40%;height:600px; border:2px solid white; text-align: center" >
</div>


</body>

<script src="js/jquery-3.2.1.min.js"></script>
<script src="js/echarts.min.js"></script>  
<script type="text/javascript">//等待信号

//获取浏览器页面可见高度和宽度  
var _PageHeight = document.documentElement.clientHeight,
    _PageWidth = document.documentElement.clientWidth;
//计算loading框距离顶部和左部的距离（loading框小部件的宽度为90px，高度为90px）  
var _LoadingTop = _PageHeight > 90 ? (_PageHeight - 90) / 2 : 0,
    _LoadingLeft = _PageWidth > 90 ? (_PageWidth - 90) / 2 : 0;
//在页面未加载完毕之前显示的loading Html自定义内容  
var _LoadingHtml = '<div id="loadingDiv" style="position:absolute;left:0;width:100%;height:' + _PageHeight +
'px;top:0;background:#FFFFFF;opacity:1.0; filter:alpha(opacity=80);z-index:10000;">
                         <div class="spinner" style="position: top: 60px; margin:' + _LoadingTop + 'px auto ;"></div></div>';

//呈现loading效果  
document.write(_LoadingHtml);

//监听加载状态改变  
document.onreadystatechange = completeLoading;

//加载状态为complete时移除loading效果  
function completeLoading() {
    if (document.readyState == "complete") {
        //此引用了Jquery，页面没有导jq包的，需要自行加入
        $("#loadingDiv").fadeOut(1);
    }
}

</script>
<script type="text/javascript">
$(document).ready(function(e){
	$("#main1").hide();
	$("#main2").hide();
	$("#main3").hide();
	$("#main4").hide();
	$("#main5").hide();
	$("#main6").hide();
	$("#main7").hide();
	$("#main8").hide();
	$("#main9").hide();
	$("#main10").hide();
	$("#main11").hide();
	$("#main12").hide();
	
    var Artikel_U=[];
    var Umsatz=[];
    var Artikel_D=[];
    var DBeitrag=[];
    var Best_Z=[];//pieData
    var Best_T=[];//pieData
    var Zeit = [];
    var Tag = [];
    var Sex = [];
    var S_num = [];
    var Kinder = [];
    var K_num = [];
    var Beruf = [];
    var B_num = [];
    var Family = [];
    var F_num = [];
    
    var Haus = [];
    var H_num = [];
    var Stammkunde = [];
    var SK_num = [];
    var Age = [];
    var A_num = [];
    var Wohnort = [];
    var W_num = [];

    
    //初始化数据
    <c:forEach items="${TF_Artikel_Umsatz}" var="a">
        Artikel_U.push("${a.key}");
        Umsatz.push("${a.value}");
		//注意这里添加的是一个json对象
	</c:forEach>
	
	   <c:forEach items="${TF_Artikel_DBeitrag}" var="b">
	   Artikel_D.push("${b.key}");
	   DBeitrag.push("${b.value}");
	</c:forEach>
	
	   <c:forEach items="${Best_Z}" var="c">
		//注意这里添加的是一个json对象
		Zeit.push("${c.key}");
		Best_Z.push({
			name:"${c.key}",
			value:"${c.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Best_T}" var="d">
	   Tag.push("${d.key}");
	   Best_T.push({
			name:"${d.key}",
			value:"${d.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Sex}" var="e">
	   Sex.push("${e.key}");
	   S_num.push({
			name:"${e.key}",
			value:"${e.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Kinder}" var="f">
	   Kinder.push("${f.key}");
	   K_num.push({
			name:"${f.key}",
			value:"${f.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Beruf}" var="g">
	   Beruf.push("${g.key}");
	   B_num.push({
			name:"${g.key}",
			value:"${g.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Family}" var="h">
	   Family.push("${h.key}");
	   F_num.push({
			name:"${h.key}",
			value:"${h.value}"
		});
	</c:forEach>
	

	   <c:forEach items="${Haus}" var="i">
	   Haus.push("${i.key}");
	   H_num.push({
			name:"${i.key}",
			value:"${i.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Stammkunde}" var="j">
	   Stammkunde.push("${j.key}");
	   SK_num.push({
			name:"${j.key}",
			value:"${j.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Age}" var="k">
	   Age.push("${k.key}");
	   A_num.push({
			name:"${k.key}",
			value:"${k.value}"
		});
	</c:forEach>
	
	   <c:forEach items="${Wohnort}" var="l">
	   Wohnort.push("${l.key}");
	   W_num.push({
			name:"${l.key}",
			value:"${l.value}"
		});
	</c:forEach>
	
	
	
	//定义echarts实例对象
    var myChart1;
	var myChart2;
	var myChart3;
	var myChart4;
	var myChart5;
	var myChart6;
	var myChart7;
	var myChart8;
	var myChart9;
	var myChart10;
	var myChart11;
	var myChart12;
	//显示简单柱状图
	 $("[name=submit]").click(function(e){
    	$("#main1").hide();
		$("#main2").hide();
		$("#main3").hide();
		$("#main4").hide();
		$("#main5").hide();
		$("#main6").hide();
		$("#main7").hide();
		$("#main8").hide();
		$("#main9").hide();
		$("#main10").hide();
		$("#main11").hide();
		$("#main12").hide();
	 });
    $("[name=bar1]").click(function(e){
    	$("#main1").hide();
		$("#main2").hide();
		$("#main3").hide();
		$("#main4").hide();
		$("#main5").hide();
		$("#main6").hide();
		$("#main7").hide();
		$("#main8").hide();
		$("#main9").hide();
		$("#main10").hide();
		$("#main11").hide();
		$("#main12").hide();
    	$("#main1").show();
    	if(myChart1!=null){
    		myChart1.dispose();//销毁实例后可以再次在该容器上创建 ECharts实例
    	}
    	//创建一个 ECharts 实例,但不能在单个容器上初始化多个 ECharts 实例
    	myChart1 = echarts.init(document.getElementById('main1'));
    	// 指定图表的配置项和数据
	    var optionBar1 = {
	        title: {text: 'Top und Flop_Artikel_Nach_Umsatz'},
	        tooltip: {show:true,trigger:'item'},
	        legend: {data:['Umsatz'],show:true},
	        xAxis: {position:'bottom',data: Artikel_U,axisLabel:{textStyle:{fontStyle:'italic',fontSize: '8'}}},
	        yAxis: {show:true,position:'left' ,},
	        series: [{name: 'Umsatz',type: 'bar',data: Umsatz,label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            }}]
	    };
    	myChart1.setOption(optionBar1,true);
    });

    //显示简单柱状图
      $("[name=bar2]").click(function(e){
       	$("#main1").hide();
   		$("#main2").hide();
   		$("#main3").hide();
   		$("#main4").hide();
   		$("#main5").hide();
   		$("#main6").hide();
   		$("#main7").hide();
   		$("#main8").hide();
   		$("#main9").hide();
   		$("#main10").hide();
   		$("#main11").hide();
   		$("#main12").hide();
       	$("#main2").show();
       	if(myChart2!=null){
       		myChart2.dispose();//销毁实例，否则无法再次显示图表
       	}
       	myChart2 = echarts.init(document.getElementById('main2'));
       	var optionBar2 = {
       		title: {text: 'Top und Flop_Artikel_Nach_DBeitrag'},
 	        tooltip: {show:true,trigger:'item'},
 	        legend: {data:['DBeitrag'],show:true},
 	        xAxis: {position:'bottom',data: Artikel_D,axisLabel:{textStyle:{fontStyle:'italic',fontSize: '8'}}},
 	        yAxis: {show:true,position:'left' ,},
 	        series: [{name: 'DBeitrag',type: 'bar',data: DBeitrag,label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            }}]
   	    };
       	myChart2.setOption(optionBar2,true);
       });
    //显示饼图
    $("[name=pie1]").click(function(e){
    	$("#main1").hide();
		$("#main2").hide();
		$("#main3").hide();
		$("#main4").hide();
		$("#main5").hide();
		$("#main6").hide();
		$("#main7").hide();
		$("#main8").hide();
		$("#main9").hide();
		$("#main10").hide();
		$("#main11").hide();
		$("#main12").hide();
    	$("#main3").show();
    	$("#main4").show();
    	
    	if(myChart3!=null&&myChart4!=null){
    		myChart3.dispose();//销毁实例，否则无法再次显示图表
    		myChart4.dispose();//销毁实例，否则无法再次显示图表
    	}
    	myChart3 = echarts.init(document.getElementById('main3'));
    	var optionPie1 = {
	        title : {text: 'Best_Zeit',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Zeit},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:Best_Z}]
	    }; 
    	myChart3.setOption(optionPie1,true);
    	
    	myChart4 = echarts.init(document.getElementById('main4'));
    	var optionPie2 = {
	        title : {text: 'Best_Tag',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Tag},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:Best_T}]
	    }; 
    	myChart4.setOption(optionPie2,true);
    	
    });
    $("[name=pie2]").click(function(e){
    	$("#main1").hide();
		$("#main2").hide();
		$("#main3").hide();
		$("#main4").hide();
		$("#main5").hide();
		$("#main6").hide();
		$("#main7").hide();
		$("#main8").hide();
		$("#main9").hide();
		$("#main10").hide();
		$("#main11").hide();
		$("#main12").hide();
		$("#main5").show();
		$("#main6").show();
		$("#main7").show();
		$("#main8").show();
		$("#main9").show();
		$("#main10").show();
		$("#main11").show();
		$("#main12").show();
    	if(myChart5!=null&&myChart6!=null&&myChart7!=null&&myChart8!=null&&myChart9!=null&&myChart10!=null&&myChart11!=null&&myChart12!=null){
    		myChart5.dispose();//销毁实例，否则无法再次显示图表
    		myChart6.dispose();//销毁实例，否则无法再次显示图表
    		myChart7.dispose();//销毁实例，否则无法再次显示图表
    		myChart8.dispose();//销毁实例，否则无法再次显示图表
    		myChart9.dispose();//销毁实例，否则无法再次显示图表
    		myChart10.dispose();//销毁实例，否则无法再次显示图表
    		myChart11.dispose();//销毁实例，否则无法再次显示图表
    		myChart12.dispose();//销毁实例，否则无法再次显示图表
    	}
    	myChart5 = echarts.init(document.getElementById('main5'));
    	var optionPie1 = {
    		
	        title : {text: 'Sex',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Sex},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:S_num}]
    	
	    }; 
    	myChart5.setOption(optionPie1,true);//eg:饼图通过配置center来确定中心位置，radius确定饼图的大小
    	
    	myChart6 = echarts.init(document.getElementById('main6'));
    	var pie2 = {
    	        title : {text: 'Kinder',subtext: 'Subtitle',x:'center'},
    	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
    	    	legend: {orient: 'vertical',left: 'left',data: Kinder},
    	    	series : [{name: 'total',type: 'pie',radius : '55%'  
    	    		       ,center: ['50%', '60%'],data:K_num}]
    	    }; 
 
    	myChart6.setOption(pie2,true);
    	
    	myChart7 = echarts.init(document.getElementById('main7'));
    	var optionPie3 = {
	        title : {text: 'Beruf',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Beruf},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:B_num}]
	    }; 
    	myChart7.setOption(optionPie3,true);
    	
    	myChart8 = echarts.init(document.getElementById('main8'));
    	var optionPie4 = {
	        title : {text: 'Family Status',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Family},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:F_num}]
	    }; 
    	myChart8.setOption(optionPie4,true);
    	
    	
    	myChart9 = echarts.init(document.getElementById('main9'));
    	var optionPie5 = {
	        title : {text: 'Haus',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Haus},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:H_num}]
	    }; 
    	myChart9.setOption(optionPie5,true);
    	
    	myChart10 = echarts.init(document.getElementById('main10'));
    	var optionPie6 = {
	        title : {text: 'Stammkunde',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Stammkunde},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:SK_num}]
	    }; 
    	myChart10.setOption(optionPie6,true);
    	
    	myChart11 = echarts.init(document.getElementById('main11'));
    	var optionPie7 = {
	        title : {text: 'Age',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Age},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  
	    		       ,center: ['50%', '60%'],data:A_num}]
	    }; 
    	myChart11.setOption(optionPie7,true);
    	
    	myChart12 = echarts.init(document.getElementById('main12'));
    	var optionPie8 = {
	        title : {text: 'Wohnort',subtext: 'Subtitle',x:'center'},
	    	tooltip : {trigger: 'item',formatter: "{a} <br/>{b} : {c} ({d}%)"},
	    	legend: {orient: 'vertical',left: 'left',data: Wohnort},
	    	series : [{name: 'total',type: 'pie',radius : '55%'  //eg:饼图通过配置center来确定中心位置，radius确定饼图的大小
	    		       ,center: ['50%', '60%'],data: W_num}]
	    };  
    	myChart12.setOption(optionPie8,true);
    });
    
});
</script>
</html>