<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Form</title>

<link rel="stylesheet" type="text/css" href="css/style.css">

</head>

<body>

     <!--main Header-->
    <div id="main-header">
         
         <div id="logo">
         
             <span id="sp1">Baudas.de</span> 
         
         </div>
         
         <!--Suchen-->
         <div id = "search">
         
             <input class="searchfeld" type = "text" name="text" placeholder="Hier ihre Suche bitte Eingeben... ">
             <input class="searchsubmit" type = "submit" name="submit" value="Suchen">
         
     
     </div>
        </div>

   <div id="container">
   <h2 style="color:#FACC2E;">${message}</h2>
  <h1>Login</h1>
 <form action = "validation" method="post">
  <label for="Benutzername"><b>Benutzername</b></label><br>
  <input type="text" name="username" placeholder="Benutzername"><br>
  <label for="psw"><b>Passwort</b></label><br>
  <input type="password" name="password" placeholder="passwort"><br>
  <button type="submit" name="submit" class="button" value="login">Login</button><br>
  </form>
    
</div>
    
     <footer>
        
        <div id="footer">
            
             <p>Copyright © 2020 meinbuchgeschaeft.de, Inc.</p>
           
        </div>
    </footer>
    
</body>
</html>