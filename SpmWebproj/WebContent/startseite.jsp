<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html lang="en">
<head>
<title>Login Form</title>

<link rel="stylesheet" type="text/css" href="css/style.css">

<script type="text/javascript">
    
    function getMessage(){
    	
    	var msg = '${message}';
    	
    	if(msg != ""){
    		
    	alert(msg);
    	
    	
    	}
    }
    
    </script>
    
     <script>
    
        window.onload = function(){
 
            getMessage();
            
              };
    
    </script>

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
         <div id = "search">
         <form action="">
         
             <input class="searchfeld" type = "text" name="text" placeholder="Hier ihre Suche bitte Eingeben... ">
             <input class="searchsubmit" type = "submit" name="submit" value="Suchen">
         
         </form>
     
     </div>
     </div>

     <div id=container2 style="margin-top:200px; height:250px;">
     
     <h2 style="color:#FACC2E;">${file}</h2>
     
     <form action="UploadValidation" enctype='multipart/form-data' method="post">     
     <span style="color:#FACC2E; font-size:30px;">Datei.CSV Hochladen</span><br>
     <input  style="margin-left:130px; margin-top:30px;" type="file" accept=".csv" name="datei" /><br>
     <button style="margin-top:30px;" type="submit" name="submit" class="button" value="fileupload">Hochladen</button><br>
     </form>	 
     </div>
     
    
    
</body>
</html>