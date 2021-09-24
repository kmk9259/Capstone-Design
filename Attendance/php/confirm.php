
<?php

$con=mysqli_connect("localhost","root","1234", "attendance" );
mysqli_set_charset($con,"utf8");

$st_id=$_POST["u_id"];

$statement = mysqli_prepare($con, "SELECT * FROM student WHERE st_id = ?");
mysqli_stmt_bind_param($statement, "s", $st_id);
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $st_id);

$response = array();
$response["success"] = true;

while(mysqli_stmt_fetch($statement)){
  $response["success"] = false;
  $response["st_id"] = $st_id;
}

echo json_encode($response);

 ?>