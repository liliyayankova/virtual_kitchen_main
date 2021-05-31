<?php
    //Start session
    session_start();
    include("config.php");

    //Variables to hold username and password from login form
    $username = $_POST['username'];
    $password = $_POST['password'];

    if(!empty($_POST)){
        //Checks if username and password are set (i.e. not empty)
        if (isset($username) && isset($password)) {
            $sql = $link->prepare("SELECT * FROM user WHERE username = ?");
            $sql->bind_param('s', $username);
            $sql->execute();
            $result = $sql->get_result();
            $user = $result->fetch_object();
                
            //Checks the password against the one in the database
            if(password_verify($password, $user->password) ) {
                $_SESSION['ID'] = $user->ID;
                header("Location: http://localhost:8080/{$username}");
            } else{
                echo '<script>
                        alert("Wrong password");
                        window.location.href = "index.html";
                    </script>';
            }
        }
    }
?>