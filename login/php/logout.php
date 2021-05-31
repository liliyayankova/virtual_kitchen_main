<?php
   session_start();
   unset($_SESSION["username"]);
   unset($_SESSION["password"]);
   
   header('Location: http://localhost:8000/index.html');
?>