<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery.js"></script>
<link href="css/bootstrap.css" rel="stylesheet"/>
</head>
<body>

<div class="container">
<h1 id='here'> Bon Appetit!</h1>
<p><input id="username" placeholder="Username" class="form-control"/></p>
<p><input id="fname" placeholder="First Name"  class="form-control"/></p>
<p><input id="lname" placeholder="Last Name" class="form-control"/></p>
<p><input id="pass" placeholder="Password" class="form-control"/></p>
<p> <label> Cuisine Preferences </p>
<select id='cuisine' multiple></select></label>
<p> <label> Restrictions </p>
<select id='restrictions' multiple></select></label>
<button id='add' class="btn-primary">Add</button> 
<script>

$(document).ready(function() {

	$.ajax({
		
		url:"http://localhost:8080/mydb/rest/getPreferences",
		type:"get",
		success: function(response) {
			var stringResponse = JSON.stringify(response);
			console.log(stringResponse);
			
			$.each(response, function(key, value)  {
				console.log(key);
				console.log(value);
				$("#cuisine").append("<option value='cuisineName'>" + value.cuisineName + "</option>");
			});
			
		}
	});
	
	$.ajax({
		
		url:"http://localhost:8080/mydb/rest/getRestrictions",
		type:"get",
		success: function(response) {
			var stringResponse = JSON.stringify(response);
			console.log(stringResponse);
			
			$.each(response, function(key, value)  {
				console.log(key);
				console.log(value);
				$("#restrictions").append("<option value='restriction'>" + value.restriction + "</option>");
			});
			
		}
	});
	
});

$("#add").click( function() {

var username = $("#username").val();
var fname = $("#fname").val();
var lname = $("#lname").val();
var pass = $("#pass").val();
var prefs = [];
var prefSelections = $("#cuisine > option:selected");
var restrictions = [];
var prefRestrictions = $("#restrictions > option:selected");


for( var i = 0; i < prefSelections.length; i++) {
	prefs.push({cuisineName:prefSelections[i].innerHTML});
}

for( var i = 0; i < prefRestrictions.length; i++) {
	restrictions.push({restriction:prefRestrictions[i].innerHTML});
}

//alert(JSON.stringify(prefs));

var newUser = {"userName":username,"cuisines":prefs,"orders":null,"payment":null,"restrictions":restrictions,"person":{"userName":username,"dtype":"user","firstName":fname,"lastName":lname,"password":pass}};
console.log(newUser);


	$.ajax({
		url:"http://localhost:8080/mydb/rest/createUser",
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
</div>
</body>
</html>
