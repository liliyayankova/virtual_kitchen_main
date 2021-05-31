<?php
//Database credentials
define('DB_SERVER', 'userdb');
define('DB_USERNAME', 'service_user');
define('DB_PASSWORD', 'service_user');
define('DB_NAME', 'userdb');

//Connection to database
$link = mysqli_connect(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);

//Check connection
if($link === false){
    die("ERROR: Could not connect. " . mysqli_connect_error());
}
?>