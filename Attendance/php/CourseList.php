<?php

   header('Content-Type: text/html; charset=utf-8');
   $con = mysqli_connect("localhost", "root","1234","attendance");
   mysqli_set_charset($con,"utf8");
   
   $u_id = $_POST["u_id"];
   $u_pw = $_POST["u_pw"];
   
   $statement = mysqli_prepare($con, "SELECT * FROM USER WHERE u_id = ? AND u_pw = ?");
   
   
   $courseUniversity = $_GET["courseUniversity"];
   $courseYear = $_GET["courseYear"];
   $courseTerm = $_GET["courseTerm"];
   $courseArea= $_GET["courseArea"];
   $courseMajor = $_GET["courseMajor"];
   
   $result = mysqli_query($con, "SELECT * FROM course WHERE courseUniversity = '$courseUniversity' 
   AND courseYear = '$courseYear' AND courseTerm = '$courseTerm' 
   AND courseArea = '$courseArea' AND courseMajor = '$courseMajor'");

   $response = array();

   while ($row = mysqli_fetch_array($result)) {
      array_push($response, array("courseID"=>$row[0], "courseUniversity"=>$row[1], "courseYear"=>$row[2],
     "courseTerm"=>$row[3], "courseArea"=>$row[4], "courseMajor"=>$row[5], "courseTitle"=>$row[6],
     "courseProfessor"=>$row[7], "courseTime"=>$row[8], "courseRoom"=>$row[9]));
   }

   echo json_encode(array("response" => $response), JSON_UNESCAPED_UNICODE);
   mysqli_close($con);
?>