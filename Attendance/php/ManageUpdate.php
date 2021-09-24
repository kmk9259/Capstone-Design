<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_name = $_POST["u_name"];
	$courseTitle = $_POST["courseTitle"];
	$three = $_POST["three"];
	$temp = $_POST["temp"];
	
	$statement = mysqli_prepare($con, "UPDATE attend A INNER JOIN user B ON A.u_id = B.u_id SET A.three='$temp'
WHERE B.u_id=A.u_id AND A.three='$three' AND B.u_name='$u_name' AND A.courseTitle = '$courseTitle'");
	
	
	mysqli_stmt_bind_param($statement, "ssss", $temp, $u_name, $courseTitle, $three);
	mysqli_stmt_execute($statement);
	
	$response = array();
	$response["success"]=true;
	
	echo json_encode($response);
	echo json_encode("강의명"+$courseTitle);
	echo json_encode("결과"+$three);
	echo json_encode("temp"+$temp);
	echo json_encode("조건문 : "+$statement);
?>