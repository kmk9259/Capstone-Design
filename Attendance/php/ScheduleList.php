<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_GET["u_id"];
	
	$result = mysqli_query($con, "SELECT course.courseID, course.courseTime, 
	course.courseProfessor, course.courseTitle FROM user, course, schedule WHERE user.u_id = '$u_id'
	AND user.u_id = schedule.u_id AND schedule.courseID = course.courseID");
	
	$response = array();
	while($row = mysqli_fetch_array($result)){
		array_push($response, array("courseID" => $row[0], "courseTime" => $row[1], 
		"courseProfessor" => $row[2], "courseTitle" => $row[3]));
	}
	
    echo json_encode(array("response" => $response), JSON_UNESCAPED_UNICODE);
	mysqli_close($con);

?>