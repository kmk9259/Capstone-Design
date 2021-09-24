<?php
$con = mysqli_connect("localhost","root","1234","attendance");
$u_id = $_POST["u_id"];

$statement = mysqli_prepare($con,"select st_id from student where st_id='$u_id'");
mysqli_stmt_bind_param($statement, "s", $u_id);
mysqli_stmt_execute($statement);
   
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $u_id);
$response = array();
$response["success"]=true;
while(mysqli_stmt_fetch($statement))
{
	$response["success"]=true;
	$response["u_id^^"]=$u_id;
}
echo json_encode($response);
?>