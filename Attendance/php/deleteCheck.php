<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_POST["u_id"];
	
	$statement = mysqli_prepare($con, "delete from attend where u_id = '$u_id'");
	mysqli_stmt_bind_param($statement, 's', $u_id);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"] = true;
	
	
    echo json_encode($response);
?>