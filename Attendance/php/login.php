<?php
   $con = mysqli_connect("localhost", "root","1234","attendance");
   mysqli_set_charset($con,"utf8");
   
   $u_id = $_POST["u_id"];
   $u_pw = $_POST["u_pw"];
   $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE u_id = ? AND u_pw = ?");
   
   mysqli_stmt_bind_param($statement, "ss", $u_id, $u_pw);
   mysqli_stmt_execute($statement);
   
   mysqli_stmt_store_result($statement);
   mysqli_stmt_bind_result($statement, $u_id, $u_pw, $major, $job, $u_name);
   
   $response = array();
   $response["success"] = false;
   
   while(mysqli_stmt_fetch($statement))
   {
      $response["success"] = true;
      $response["u_id"] = $u_id;
      $response["u_pw"] = $u_pw;
      $response["major"] = $major ;
      $response["job"] = $job;
      $response["u_name"] = $u_name;
   }
   echo json_encode($response);
?>
      