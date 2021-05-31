<?php 
    session_start();

    if (isset($_SESSION['ID'])) {
       echo 'Session set';
?>
<!DOCTYPE html>
<html>
 <head>
  <title>Virtual kitchen</title>
    <style type="text/css">
        body{ font: 14px sans-serif; }
        .wrapper{ width: 350px; padding: 20px; }
    </style>
 </head>
 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 <body>
   <ul>
      <li><a href="logout.php">Logout</a></li>
   </ul>
 </body>
<?php 
   } else {
      echo '<script>
               alert("Session not set");
               window.location.href = "index.html";
            </script>';
   }
  ?>
</html>