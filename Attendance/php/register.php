<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_POST["u_id"];
	$u_pw = $_POST["u_pw"];
	$job = $_POST["job"];
	$major = $_POST["major"];
	$u_name = $_POST["u_name"];
	
	$statement = mysqli_prepare($con, "INSERT INTO user VALUES(?,?,?,?,?)");
	mysqli_stmt_bind_param($statement, "sssss", $u_id, $u_pw, $major, $job, $u_name);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"]=true;
	
	echo json_encode($response);
?>