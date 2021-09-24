<?php
	$con = mysqli_connect("localhost", "root","1234","attendance");
	mysqli_set_charset($con,"utf8");
	
	$u_id = $_GET["u_id"];
	
	$result = mysqli_query($con, "SELECT course.courseID, course.courseTitle, course.courseProfessor, 
	course.courseTime, course.courseRoom FROM user, course, schedule WHERE user.u_id = '$u_id'
	AND user.u_id = schedule.u_id AND schedule.courseID = course.courseID");
	
	$response = array();
	while($row = mysqli_fetch_array($result)){
		array_push($response, array("courseID" => $row[0], "courseTitle" => $row[1], "courseProfessor" => $row[2], 
		"courseTime" => $row[3], "courseRoom" => $row[4]));
	}
	
    echo json_encode(array("response" => $response), JSON_UNESCAPED_UNICODE);
	mysqli_close($con);

?>