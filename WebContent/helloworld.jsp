<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
</head>
<body>
<label> UserName:<input id='username' type='text' /></label><br />
<label> First Name:<input id='fname' type='text' /></label><br />
<label> Last Name:<input id='lname' type='text' /></label><br />
<label> Password:<input id='pass' type='text' /></label><br />
<label> Cuisine Preferences
<select id='cuisine' multiple></select></label>
<button id='add' >add</button>
<script>


$(document).ready(function() {

	$.ajax({
		
		url:"http://localhost:8080/mydb/rest/test/get/cuisines",
		type:"get",
		success: function(response) {
			var stringResponse = JSON.stringify(response);
			console.log(stringResponse);
			
			$.each(response, function(key, value)  {
				console.log(key);
				console.log(value);
				$("#cuisine").append("<option>" + value.cuisineName + "</option>");
			});
			
		}
	});
});





$("#add").click( function() {

var username = $("#username").val();
var fname = $("#fname").val();
var lname = $("#lname").val();
var pass = $("#pass").val();

var newUser = {"userName":username,"cuisines":null,"orders":null,"payment":null,"restrictions":null,"person":{"userName":username,"dtype":"user","firstName":fname,"lastName":lname,"password":pass}};
alert(newUser);


	$.ajax({
		url:"http://localhost:8080/mydb/rest/test/createUser",
		type:"post",
		data:JSON.stringify(newUser),
		dataType:"json",
		contentType:"application/json",
		success: function(response) {
			var stringResponse = JSON.stringify(response);
			$('here').innerHTML = stringResponse;
		}
	});


});
</script>
<h1 id='here'>
hello world
</h1>
</body>
</html>