<!DOCTYPE html>
<html lang="en">

<head>
    <title>Login</title>
    <!-- Meta Tags -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta charset="utf-8">
    <meta name="keywords" content="BauDaS Perfessional Data Analyse" />
    <!-- //Meta Tags -->

    <!-- Style-sheets -->
    <!-- Bootstrap Css -->
    <link href="css/bootstrap1.css" rel="stylesheet" type="text/css" media="all" />
    <!-- Bootstrap Css -->
    <!-- Common Css -->
    <link href="css/style3.css" rel="stylesheet" type="text/css" media="all" />
    <!--// Common Css -->
    <!-- Fontawesome Css -->
    <link href="css/fontawesome-all.css" rel="stylesheet">
    <!--// Fontawesome Css -->
    <!--// Style-sheets -->

    <!--web-fonts-->
    <link href="//fonts.googleapis.com/css?family=Poiret+One" rel="stylesheet">
    <link href="//fonts.googleapis.com/css?family=Open+Sans:300,400,600,700" rel="stylesheet">
    <!--//web-fonts-->
</head>

<body>

    <div class="bg-page py-5">
        <div class="container">
            <!-- main-heading -->
            <h2 class = "text-center" style="color:#FACC2E;">${message}</h2>
            <h1 class="main-title-w3layouts mb-2 text-center text-white">Login</h1>
            <!--// main-heading -->
            <div class="form-body-w3-agile text-center w-lg-50 w-sm-75 w-100 mx-auto mt-5">
                <form action="validation" method="post">
                      <div class="form-group">
                        <label>BenutzerName</label>
                        <input type="text" class="form-control" placeholder="Benutzer Name" required=""  name="username">
                    </div>
                    <div class="form-group">
                        <label>Password</label>
                        <input type="password" class="form-control" placeholder="Password" required=""  name="password">
                    </div>
                    
                    <div class="d-sm-flex justify-content-between">
                        <div class="form-check col-md-6 text-sm-left text-center">
                            <input type="checkbox" class="form-check-input" id="exampleCheck1">
                            <label class="form-check-label" for="exampleCheck1">Remember me</label>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary error-w3l-btn mt-sm-5 mt-3 px-4" value="login" name="submit">Login</button>
                </form>
                <h1 class="paragraph-agileits mt-2">
                    <a href="index.jsp">Back to Home</a>
                </h1>
            </div>

            <!-- Copyright -->
            <div class="copyright py-xl-3 py-2 mt-xl-5 mt-4 text-center">
                <p>� 2020 BauDas Gesellschaft . All Rights Reserved | Design by
                    <a href=#> PandesHSEmden </a>
                </p>
            </div>
            <!--// Copyright -->
        </div>
    </div>


    <!-- Required common Js -->
    <script src='js/jquery-3.2.1.min.js'></script>
    <!-- //Required common Js -->

    <!-- Js for bootstrap working-->
    <script src="js/bootstrap.min.js"></script>
    <!-- //Js for bootstrap working -->
</body>
</html>