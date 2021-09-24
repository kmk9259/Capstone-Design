<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_POST["u_id"];
	$courseID = $_POST["courseID"];
	
	
	$statement = mysqli_prepare($con, "insert into schedule values(?,?)");
	mysqli_stmt_bind_param($statement, "ss", $u_id, $courseID);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"]=true;
	
	echo json_encode($response);
?>