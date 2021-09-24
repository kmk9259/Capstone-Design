<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$proname = $_GET["proname"]; 
	
	$result = mysqli_query($con, "SELECT course.courseID, course.courseTitle, user.u_name, 
	course.courseTime, course.courseRoom, attend.three, attend.Check_time FROM user, course, schedule, attend 
	WHERE course.courseProfessor = '$proname' AND user.job='Student' AND course.courseTitle=attend.courseTitle 
	AND user.u_id = attend.u_id AND schedule.courseID = course.courseID AND schedule.u_id = attend.u_id");
	
	$response = array();
	while($row = mysqli_fetch_array($result)){
		array_push($response, array("courseID" => $row[0], "courseTitle" => $row[1], "u_name" => $row[2], 
		"courseTime" => $row[3], "courseRoom" => $row[4], "three" => $row[5], "Check_time" => $row[6]));
	}
	
    echo json_encode(array("response" => $response), JSON_UNESCAPED_UNICODE);
	mysqli_close($con);

?>