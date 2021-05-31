<?php
    include("config.php");

    //Variables to hold all information from register form
    $forename = $_POST['forename'];
    $surname = $_POST['surname'];
    $username = $_POST['username'];
    $password1 = $_POST['password'];
    $password2 = $_POST['pass_repeat'];
    $hashed_password = password_hash($password1, PASSWORD_DEFAULT);
    $email = $_POST['email'];

    //Checks if the 2 passwords match
    if($password1 != $password2){
        echo "The passwords do not match. Please try again!";
        exit;
    }

    $sql = "SELECT * FROM user WHERE username = '$username'";
    $user_result = mysqli_query($link, $sql);
    if($user_result->num_rows > 0){
        echo "The username " . $username . " already exists in the database!";
        exit;
    }

    //Adds the new user to the database with all relevant information
    $sql = "INSERT INTO user (ID, username, forename, surname, password, email) VALUES (null, ?, ?, ?, ?, ?)";
    $statement = mysqli_prepare($link, $sql);
    $statement->bind_param("sssss", $username, $forename, $surname, $hashed_password, $email);
    $result = $statement->execute();

    //Checks if the add was successful and if the 2 passwords match
    if($result && password_verify($password1, $hashed_password)){
        echo '<script>
                    alert("You have successfully registered!");
                    window.location.href = "index.html";
                </script>';
    } else{
        echo '<script>
                    alert("Failed to register.");
                    window.location.href = "index.html";
                </script>';
    }
?>