<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_POST["u_id"];
	$courseTitle = $_POST["courseTitle"];
	$three = $_POST["three"];
	$Check_time = $_POST["Check_time"];
	
	
	$statement = mysqli_prepare($con, "insert into attend values(?,?,?,?)");
	mysqli_stmt_bind_param($statement, "ssss", $u_id, $courseTitle, $three, $Check_time);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"]=true;
	
	echo json_encode($response);
?>