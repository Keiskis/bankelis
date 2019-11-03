<html>
<head>
<title>Mini BANK create user</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
</head>
<body>
	<h2>Create new user</h2>
	<table>
		<tr>
			<td>First name</td>
			<td><input type="text" id="name"></td>
		</tr>
		<tr>
			<td>Last name</td>
			<td><input type="text" id="lastName"></td>
		</tr>
		<tr>
			<td>User name</td>
			<td><input type="text" id="userName"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" id="password"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="create" value="CREATE" /></td>
		</tr>
	</table>
	
	<hr/>
	<div id="displayDiv" style="display:none"><h3>JSON Data returned from Server after processing</h3>
		<div id="processedData"></div>
	</div>
<script>
	jQuery(document).ready(function($) {
 
		$("#create").click(function(){
			var createData = {};
			createData["name"] = $("#name").val();
			createData["lastName"] = $("#lastName").val();
			createData["userName"] = $("#userName").val();
			createData["password"] = $("#password").val();
			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "newuser",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
				if (data.id != null) { 
					  location.replace("http://localhost:8080/createaccount");
					  }else{
					  alert ("Error creating user. All fields required!");
					  }
				}
			});
		});
 
	});
</script>	
</body>
</html>