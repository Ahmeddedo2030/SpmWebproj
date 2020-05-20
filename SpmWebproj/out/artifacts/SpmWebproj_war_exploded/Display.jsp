<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Baudas/Ergebnisse-Analyse</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="keywords" content="Glance Design Dashboard Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template,
SmartPhone Compatible web template, free WebDesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design"/>
    <script type="application/x-javascript"> addEventListener("load", function () {
        setTimeout(hideURLbar, 0);
    }, false);

    function hideURLbar() {
        window.scrollTo(0, 1);
    } </script>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.css" rel='stylesheet' type='text/css'/>

    <!-- Custom CSS -->
    <link href="css/style.css" rel='stylesheet' type='text/css'/>

	<!-- side nav css file -->
	<link href='css/SidebarNav.min.css' media='all' rel='stylesheet' type='text/css'/>
	<!-- //side nav css file -->

    <!-- font-awesome icons CSS -->
    <link href="css/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons CSS-->

    <!-- ===========================
    FAVICONS
    =========================== -->
    <link rel="icon" href="img/LogoBauDas.gif">




    <!-- js-->
    <script src="js/jquery-1.11.1.min.js"></script>
    <script src="js/modernizr.custom.js"></script>

    <!--webfonts-->
    <link href="//fonts.googleapis.com/css?family=PT+Sans:400,400i,700,700i&amp;subset=cyrillic,cyrillic-ext,latin-ext"
          rel="stylesheet">
    <!--//webfonts-->

    <!-- chart -->
    <script src="js/Chart.js"></script>
    <!-- //chart -->

    <!-- Metis Menu -->
    <script src="js/metisMenu.min.js"></script>
    <script src="js/custom.js"></script>
    <link href="css/custom.css" rel="stylesheet">
    <!--//Metis Menu -->
    <style>
        #chartdiv {
            width: 100%;
            height: 295px;
        }
    </style>
    <!--pie-chart --><!-- index page sales reviews visitors pie chart -->
    <script src="js/pie-chart.js" type="text/javascript"></script>
    <script type="text/javascript">

        $(document).ready(function () {
            $('#demo-pie-1').pieChart({
                barColor: '#2dde98',
                trackColor: '#eee',
                lineCap: 'round',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-2').pieChart({
                barColor: '#8e43e7',
                trackColor: '#eee',
                lineCap: 'butt',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });

            $('#demo-pie-3').pieChart({
                barColor: '#ffc168',
                trackColor: '#eee',
                lineCap: 'square',
                lineWidth: 8,
                onStep: function (from, to, percent) {
                    $(this.element).find('.pie-value').text(Math.round(percent) + '%');
                }
            });


        });

    </script>
    <!-- //pie-chart --><!-- index page sales reviews visitors pie chart -->

    <!-- requried-jsfiles-for owl -->
    <link href="css/owl.carousel.css" rel="stylesheet">
    <script src="js/owl.carousel.js"></script>
    <script>
        $(document).ready(function () {
            $("#owl-demo").owlCarousel({
                items: 3,
                lazyLoad: true,
                autoPlay: true,
                pagination: true,
                nav: true,
            });
        });
    </script>
    <!-- //requried-jsfiles-for owl -->

    <link rel="stylesheet" type="text/css" href="css/style.css">

    <link rel="stylesheet" href="http://lee.dkfirst.cn/lee_loading.css">
    <script src="http://lee.dkfirst.cn/lee_loading.js"></script>

</head>
<!--等待信号的css-->
<style>
    spinner {
        width: 60px;
        height: 60px;
        background-color: #67CF22;

        margin: 100px auto;
        -webkit-animation: rotateplane 1.2s infinite ease-in-out;
        animation: rotateplane 1.2s infinite ease-in-out;

    }

    @-webkit-keyframes rotateplane {
        0% {
            -webkit-transform: perspective(120px)
        }
        50% {
            -webkit-transform: perspective(120px) rotateY(180deg)
        }
        100% {
            -webkit-transform: perspective(120px) rotateY(180deg) rotateX(180deg)
        }
    }

    @keyframes rotateplane {
        0% {
            transform: perspective(120px) rotateX(0deg) rotateY(0deg);
            -webkit-transform: perspective(120px) rotateX(0deg) rotateY(0deg)
        }
        50% {
            transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg);
            -webkit-transform: perspective(120px) rotateX(-180.1deg) rotateY(0deg)
        }
        100% {
            transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
            -webkit-transform: perspective(120px) rotateX(-180deg) rotateY(-179.9deg);
        }
    }


</style>
<body class="cbp-spmenu-push">

<!--main Header-->
<div class="main-content">

    <div class="cbp-spmenu cbp-spmenu-vertical cbp-spmenu-left" id="cbp-spmenu-s1">
        <!--left-fixed -navigation-->
        <aside class="sidebar-left">
            <nav class="navbar navbar-inverse">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed"
                            data-toggle="collapse" data-target=".collapse"
                            aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span> <span
                            class="icon-bar"></span> <span class="icon-bar"></span> <span
                            class="icon-bar"></span>
                    </button>
                    <h1>
                        <a class="navbar-brand" href="index.jsp"><span
                                class="fa fa-area-chart"></span> Baudas</a>
                    </h1>
                </div>
                <div class="collapse navbar-collapse"  id="bs-example-navbar-collapse-1">
                    <ul class="sidebar-menu">
                        <li class="header">MAIN NAVIGATION</li>
                        <li class="treeview"><a href="startseite.jsp"> <i
                                class="fa fa-dashboard"></i> <span>Home</span>
                        </a></li>

                        <li class="treeview"><a name="bar1" href="#"> <i
                                class="fa fa-shopping-cart"></i> <span>ArtikelNachUmsatz</span>
                        </a></li>
                        <li class="treeview"><a name="bar2" href="#"> <i
                                class="fa fa-bar-chart-o"></i> <span>ArtikelNachDBeitrag</span>
                        </a></li>
                        <li class="treeview"><a name="pie1" href="#"> <i
                                class="fa fa-calendar"></i> <span>BestZeitundTag</span>
                        </a></li>





                        <li class="treeview"><a href="#">
                            <i class="fa fa-info"></i> <span>KundenGrundInfo</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                            <ul class="treeview-menu">
                                <li><a name="pie2_1" href="#"><i class="fa fa-male"></i> Geschlecht</a></li>
                                <li><a name="pie2_2" href="#"><i class="fa fa-smile-o"></i> Kinder</a></li>
                                <li><a name="pie2_3" href="#"><i class="fa fa-briefcase"></i> Berufstaetig</a></li>
                                <li><a name="pie2_4" href="#"><i class="fa fa-group"></i> FamilienStand</a></li>
                                <li><a name="pie2_5" href="#"><i class="fa fa-eur"></i> Haushalt</a></li>
                                <li><a name="pie2_6" href="#"><i class="fa fa-user"></i> Stammkunde</a></li>
                                <li><a name="pie2_7" href="#"><i class="fa fa-blind"></i> Alter</a></li>
                                <li><a name="pie2_8" href="#"><i class="fa fa-map-marker"></i> Wohnort</a></li>

                            </ul>
                        </li>

                        <li class="treeview"><a href="#">
                            <i class="fa fa-bars "></i> <span>History</span>
                            <i class="fa fa-angle-left pull-right"></i>
                        </a>
                            <ul class="treeview-menu">
                                <li><a href="validation2_1?change=1"><i class="fa fa-angle-right"></i> 1. Record</a></li>
                                <li><a href="validation2_1?change=2"><i class="fa fa-angle-right"></i> 2. Record</a></li>
                                <li><a href="validation2_1?change=3"><i class="fa fa-angle-right"></i> 3. Record</a></li>
                                <li><a href="validation2_1?change=4"><i class="fa fa-angle-right"></i> 4. Record</a></li>
                                <li><a href="validation2_1?change=5"><i class="fa fa-angle-right"></i> 5. Record</a></li>
                            </ul>
                        </li>

                        <li class="treeview"><a href="login.jsp"> <i
                                class="fa fa-sign-out"></i> <span>logout</span>
                        </a></li>
                    </ul>
                </div>

                <!-- /.navbar-collapse -->
            </nav>
        </aside>
    </div>
    <!--left-fixed -navigation-->

    <!-- header-starts -->
    <div class="sticky-header header-section ">
        <div class="header-left">
            <!--toggle button start-->
            <button id="showLeftPush"><i class="fa fa-bars"></i></button>
            <!--toggle button end-->
            <div class="clearfix"></div>
        </div>


        <div class="header-right">
            <!--search-box-->
            <div class="search-box">
                <form class="input">
                    <input class="sb-search-input input__field--madoka" placeholder="Search..." type="search"
                           id="input-31"/>
                    <label class="input__label" for="input-31">
                        <svg class="graphic" width="100%" height="100%" viewBox="0 0 404 77" preserveAspectRatio="none">
                            <path d="m0,0l404,0l0,77l-404,0l0,-77z"/>
                        </svg>
                    </label>
                </form>
            </div><!--//end-search-box-->

            <div class="profile_details">
                <ul>
                    <li class="dropdown profile_details_drop">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                            <div class="profile_img">
                                <span class="prfil-img"><img src="images/2.jpg" alt=""> </span>
                                <div class="user-name">
                                    <p>Admin Name</p>
                                    <span>Administrator</span>
                                </div>
                                <i class="fa fa-angle-down lnr"></i>
                                <i class="fa fa-angle-up lnr"></i>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                        <ul class="dropdown-menu drp-mnu">
                            <li><a href="login.jsp"><i class="fa fa-sign-out"></i> Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
            <div class="clearfix"></div>
        </div>
        <div class="clearfix"></div>
    </div>
    <!-- //header-ends -->

    <!-- main content start-->
    <div id="page-wrapper">
        <div class="main-page">
            <div class="col_3">


                <!-- 为 ECharts 准备四个具备大小（宽高）的显示区域 -->
                <div id="main1" style="position: relative; top: 60px; width: 1600px;height:750px;  text-align: center">
                </div>
                <div id="main2" style="position: relative; top: 60px; width: 1600px;height:750px;  text-align: center">
                </div>
                <div id="main3"
                     style="position: absoulte; transform:translate(10%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>
                <div id="main4"
                     style="position: absoulte; transform:translate(130%, -90.5%);  width: 40%;height:600px;   text-align: center">
                </div>

                <div id="main5"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;   text-align: center">
                </div>
                <div id="main6"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width:40%;height:600px;  text-align: center">
                </div>
                <div id="main7"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>
                <div id="main8"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>

                <div id="main9"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px; text-align: center">
                </div>
                <div id="main10"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>
                <div id="main11"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>
                <div id="main12"
                     style="position: absoulte; transform:translate(80%, 0%); top: 60px; width: 40%;height:600px;  text-align: center">
                </div>

                <!--scrolling js-->
                <script src="js/jquery.nicescroll.js"></script>
                <script src="js/scripts.js"></script>
                <!--//scrolling js-->

                <!-- Bootstrap Core JavaScript -->
                <script src="js/bootstrap.js"></script>
                <!-- //Bootstrap Core JavaScript -->

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
                    < div
                class
                = "spinner"
                style = "position: top: 60px; margin:' + _LoadingTop + 'px auto ;" > < /div></
                div > ';

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
                    $(document).ready(function (e) {
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

                        var Artikel_U = [];
                        var Umsatz = [];
                        var Artikel_D = [];
                        var DBeitrag = [];
                        var Best_Z = [];//pieData
                        var Best_T = [];//pieData
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
                            name: "${c.key}",
                            value: "${c.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Best_T}" var="d">
                        Tag.push("${d.key}");
                        Best_T.push({
                            name: "${d.key}",
                            value: "${d.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Sex}" var="e">
                        Sex.push("${e.key}");
                        S_num.push({
                            name: "${e.key}",
                            value: "${e.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Kinder}" var="f">
                        Kinder.push("${f.key}");
                        K_num.push({
                            name: "${f.key}",
                            value: "${f.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Beruf}" var="g">
                        Beruf.push("${g.key}");
                        B_num.push({
                            name: "${g.key}",
                            value: "${g.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Family}" var="h">
                        Family.push("${h.key}");
                        F_num.push({
                            name: "${h.key}",
                            value: "${h.value}"
                        });
                        </c:forEach>


                        <c:forEach items="${Haus}" var="i">
                        Haus.push("${i.key}");
                        H_num.push({
                            name: "${i.key}",
                            value: "${i.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Stammkunde}" var="j">
                        Stammkunde.push("${j.key}");
                        SK_num.push({
                            name: "${j.key}",
                            value: "${j.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Age}" var="k">
                        Age.push("${k.key}");
                        A_num.push({
                            name: "${k.key}",
                            value: "${k.value}"
                        });
                        </c:forEach>

                        <c:forEach items="${Wohnort}" var="l">
                        Wohnort.push("${l.key}");
                        W_num.push({
                            name: "${l.key}",
                            value: "${l.value}"
                        });
                        </c:forEach>

                        /*一开始的时候就展示给顾客一个图*/
                        $("#main1").show();
                        if (myChart1 != null) {
                            myChart1.dispose();//销毁实例后可以再次在该容器上创建 ECharts实例
                        }
                        //创建一个 ECharts 实例,但不能在单个容器上初始化多个 ECharts 实例
                        myChart1 = echarts.init(document.getElementById('main1'));
                        // 指定图表的配置项和数据
                        var optionBar1 = {
                            title: {text: 'Top und Flop_Artikel_Nach_Umsatz'},
                            tooltip: {show: true, trigger: 'item'},
                            legend: {data: ['Umsatz'], show: true},
                            xAxis: {
                                position: 'bottom',
                                data: Artikel_U,
                                axisLabel: {rotate: 30, textStyle: {fontStyle: 'italic', fontSize: '12'}}
                            },
                            yAxis: {show: true, position: 'left',},
                            series: [{
                                name: 'Umsatz', type: 'bar', data: Umsatz, label: {
                                    normal: {
                                        show: true,
                                        position: 'top'
                                    }
                                }
                            }]
                        };
                        myChart1.setOption(optionBar1, true);


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
                        $("[name=submit]").click(function (e) {
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
                        $("[name=bar1]").click(function (e) {
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
                            if (myChart1 != null) {
                                myChart1.dispose();//销毁实例后可以再次在该容器上创建 ECharts实例
                            }
                            //创建一个 ECharts 实例,但不能在单个容器上初始化多个 ECharts 实例
                            myChart1 = echarts.init(document.getElementById('main1'));
                            // 指定图表的配置项和数据
                            var optionBar1 = {
                                title: {text: 'Top und Flop_Artikel_Nach_Umsatz'},
                                tooltip: {show: true, trigger: 'item'},
                                legend: {data: ['Umsatz'], show: true},
                                xAxis: {
                                    position: 'bottom',
                                    data: Artikel_U,
                                    axisLabel: {rotate: 30, textStyle: {fontStyle: 'italic', fontSize: '12'}}
                                },
                                yAxis: {show: true, position: 'left',},
                                series: [{
                                    name: 'Umsatz', type: 'bar', data: Umsatz, label: {
                                        normal: {
                                            show: true,
                                            position: 'top'
                                        }
                                    }
                                }]
                            };
                            myChart1.setOption(optionBar1, true);
                        });

                        //显示简单柱状图
                        $("[name=bar2]").click(function (e) {
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
                            if (myChart2 != null) {
                                myChart2.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart2 = echarts.init(document.getElementById('main2'));
                            var optionBar2 = {
                                title: {text: 'Top und Flop_Artikel_Nach_DBeitrag'},
                                tooltip: {show: true, trigger: 'item'},
                                legend: {data: ['DBeitrag'], show: true},
                                xAxis: {
                                    position: 'bottom',
                                    data: Artikel_U,
                                    axisLabel: {rotate: 30, textStyle: {fontStyle: 'italic', fontSize: '12'}}
                                },
                                yAxis: {show: true, position: 'left',},
                                series: [{
                                    name: 'DBeitrag', type: 'bar', data: DBeitrag, label: {
                                        normal: {
                                            show: true,
                                            position: 'top'
                                        }
                                    }
                                }]
                            };
                            myChart2.setOption(optionBar2, true);
                        });
                        //显示饼图
                        $("[name=pie1]").click(function (e) {
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

                            if (myChart3 != null && myChart4 != null) {
                                myChart3.dispose();//销毁实例，否则无法再次显示图表
                                myChart4.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart3 = echarts.init(document.getElementById('main3'));
                            var optionPie1 = {
                                title: {text: 'Best_Zeit', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Zeit},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: Best_Z
                                }]
                            };
                            myChart3.setOption(optionPie1, true);

                            myChart4 = echarts.init(document.getElementById('main4'));
                            var optionPie2 = {
                                title: {text: 'Best_Tag', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Tag},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: Best_T
                                }]
                            };
                            myChart4.setOption(optionPie2, true);

                        });
                        $("[name=pie2_1]").click(function (e) {
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
                            if (myChart5 != null) {
                                myChart5.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart5 = echarts.init(document.getElementById('main5'));
                            var optionPie1 = {

                                title: {text: 'Sex', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Sex},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: S_num
                                }]

                            };
                            myChart5.setOption(optionPie1, true);//eg:饼图通过配置center来确定中心位置，radius确定饼图的大小
                        });

                        $("[name=pie2_2]").click(function (e) {
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
                            $("#main6").show();
                            if (myChart6 != null) {
                                myChart6.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart6 = echarts.init(document.getElementById('main6'));
                            var pie2 = {
                                title: {text: 'Kinder', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Kinder},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: K_num
                                }]
                            };

                            myChart6.setOption(pie2, true);
                        });

                        $("[name=pie2_3]").click(function (e) {
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
                            $("#main7").show();
                            if (myChart7 != null) {
                                myChart7.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart7 = echarts.init(document.getElementById('main7'));
                            var optionPie3 = {
                                title: {text: 'Beruf', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Beruf},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: B_num
                                }]
                            };
                            myChart7.setOption(optionPie3, true);
                        });
                        $("[name=pie2_4]").click(function (e) {
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
                            $("#main8").show();
                            if (myChart8 != null) {
                                myChart8.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart8 = echarts.init(document.getElementById('main8'));
                            var optionPie4 = {
                                title: {text: 'Family Status', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Family},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: F_num
                                }]
                            };
                            myChart8.setOption(optionPie4, true);
                        });

                        $("[name=pie2_5]").click(function (e) {
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
                            $("#main9").show();
                            if (myChart9 != null) {
                                myChart9.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart9 = echarts.init(document.getElementById('main9'));
                            var optionPie5 = {
                                title: {text: 'Haus', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Haus},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: H_num
                                }]
                            };
                            myChart9.setOption(optionPie5, true);
                        });
                        $("[name=pie2_6]").click(function (e) {
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
                            $("#main10").show();
                            if (myChart10 != null) {
                                myChart10.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart10 = echarts.init(document.getElementById('main10'));
                            var optionPie6 = {
                                title: {text: 'Stammkunde', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Stammkunde},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: SK_num
                                }]
                            };
                            myChart10.setOption(optionPie6, true);
                        });
                        $("[name=pie2_7]").click(function (e) {
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
                            $("#main11").show();
                            if (myChart11 != null) {
                                myChart11.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart11 = echarts.init(document.getElementById('main11'));
                            var optionPie7 = {
                                title: {text: 'Age', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Age},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'
                                    , center: ['50%', '60%'], data: A_num
                                }]
                            };
                            myChart11.setOption(optionPie7, true);
                        });
                        $("[name=pie2_8]").click(function (e) {
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
                            $("#main12").show();
                            if (myChart12 != null) {
                                myChart12.dispose();//销毁实例，否则无法再次显示图表
                            }
                            myChart12 = echarts.init(document.getElementById('main12'));
                            var optionPie8 = {
                                title: {text: 'Wohnort', subtext: 'Subtitle', x: 'center'},
                                tooltip: {trigger: 'item', formatter: "{a} <br/>{b} : {c} ({d}%)"},
                                legend: {orient: 'vertical', left: 'left', data: Wohnort},
                                series: [{
                                    name: 'total', type: 'pie', radius: '55%'  //eg:饼图通过配置center来确定中心位置，radius确定饼图的大小
                                    , center: ['50%', '60%'], data: W_num
                                }]
                            };
                            myChart12.setOption(optionPie8, true);
                        });

                    });
                </script>
                <!-- Classie --><!-- for toggle left push menu script -->
                <script src="js/classie.js"></script>
                <script>
                    var menuLeft = document.getElementById('cbp-spmenu-s1'),
                        showLeftPush = document.getElementById('showLeftPush'),
                        body = document.body;

                    showLeftPush.onclick = function () {
                        classie.toggle(this, 'active');
                        classie.toggle(body, 'cbp-spmenu-push-toright');
                        classie.toggle(menuLeft, 'cbp-spmenu-open');
                        disableOther('showLeftPush');
                    };


                    function disableOther(button) {
                        if (button !== 'showLeftPush') {
                            classie.toggle(showLeftPush, 'disabled');
                        }
                    }
                </script>
                <!-- //Classie --><!-- //for toggle left push menu script -->

				<!--scrolling js-->
				<script src="js/jquery.nicescroll.js"></script>
				<script src="js/scripts.js"></script>
				<!--//scrolling js-->

				<!-- side nav js -->
				<script src='js/SidebarNav.min.js' type='text/javascript'></script>
				<script>
					$('.sidebar-menu').SidebarNav()
				</script>
				<!-- //side nav js -->

</body>
</html>