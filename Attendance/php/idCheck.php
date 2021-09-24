
<?php

$con=mysqli_connect("localhost","root","1234", "attendance" );
mysqli_set_charset($con,"utf8");

$u_id=$_POST["u_id"];

$statement = mysqli_prepare($con, "SELECT * FROM user WHERE u_id = ?");
mysqli_stmt_bind_param($statement, "s", $u_id);
mysqli_stmt_execute($statement);
mysqli_stmt_store_result($statement);
mysqli_stmt_bind_result($statement, $u_id);

$response = array();
$response["success"] = true;

while(mysqli_stmt_fetch($statement)){
  $response["success"] = false;
  $response["u_id"] = $u_id;
}

echo json_encode($response);

 ?>