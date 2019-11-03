<html>
<head>
<title>Mini BANK user login</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
</head>
<body>
	<h2>User login</h2>
	<table>
		<tr>
			<td>User name</td>
			<td><input type="text" id="userName"></td>
		</tr>
		<tr>
			<td>Password</td>
			<td><input type="password" id="password"></td>
		</tr>
		<tr>
			<td colspan="2"><input type="button" id="enter" value="ENTER" /></td>
		</tr>
	</table>
	<hr/>
	
	Do not hawe account? <button onclick="createNewAccount()">Create new account</button>
	
	<div id="displayDiv" style="display:none"><h3>JSON Data returned from Server after processing</h3>
		<div id="processedData"></div>
	</div>
<script>
	jQuery(document).ready(function($) {
		$("#enter").click(function(){
			var userdata = {};
			userdata["userName"] = $("#userName").val();
			userdata["password"] = $("#password").val();			
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "login",
				data : JSON.stringify(userdata),
				dataType : 'json',				
				success : function(data) {
				if (data != null && data.userId > 0)
				{
				location.replace("http://localhost:8080/mainpage");
				}else{
				alert("Wrong user name or password!");
				location.replace("http://localhost:8080/login");
				}
				}
			});
		});
	});
	
	function createNewAccount() {
    location.replace("http://localhost:8080/newuser");
   }
	
</script>	
</body>
</html>