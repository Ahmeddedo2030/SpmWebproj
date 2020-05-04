<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Form</title>

<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>

	<div id="wrapper">
		<div id="header">
			<div id="subheader">
				<div class="container">

					<li><a href="login.jsp">Logout</a></li>
					<li><a href="startseite.jsp">Home</a></li>


				</div>
			</div>

		</div>
	</div>
	<!--main Header-->
	<div id="main-header">

		<div id="logo">

			<span id="sp1">MeinBuchGeschaeft.de</span>

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

	<div id=container2 style="margin-top: 200px; height: 250px;">

	


     <div>"TF_Artikel_Umsatz":<%=request.getAttribute("TF_Artikel_Umsatz") %></div>
     <div>"TF_Artikel_DBeitrag":<%=request.getAttribute("TF_Artikel_DBeitrag") %></div>
     <div>"Best_und_Worst_Zeit":<%=request.getAttribute("Best_Z") %></div> 
    
     <div>"Best_und_Worst_Tag":<%=request.getAttribute("Best_T") %></div> 





	</div>

</body>

</html>