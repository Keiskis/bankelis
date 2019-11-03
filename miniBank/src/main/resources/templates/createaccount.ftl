<html>
<head>
<title>Mini BANK create user account</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
</head>
<body>
	<h2>Create new user account</h2>
	<table>
		<tr>
			<td>Hello </td>
			<td id="name"></td>
		</tr>
		<tr>
			<td>You account number is: </td>
			<td id="accountNo"></td>
		</tr>
		<tr>
			<td>Please, select the account currency</td>
			<td><select id="cur">
                <option value="EUR">EUR</option>
                <option value="USD">USD</option>
                <option value="GBP">GBP</option>
                <option value="RUR">RUR</option>
                </select></td>
		</tr>
		<tr>
			<td colspan="2">
			<input type="hidden" id = "userId">
			<input type="button" id="continue" value="CONTINUE" /></td>
		</tr>
	</table>
	
	<hr/>
	<div id="displayDiv" style="display:none"><h3>JSON Data returned from Server after processing</h3>
		<div id="processedData"></div>
	</div>
<script>

$(document).ready(function() {
			var createData = {};
			createData["userdata"] = "createaccount";
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getuserdata",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
					document.getElementById("name").innerHTML = value(JSON.stringify(data.name));
					document.getElementById("accountNo").innerHTML = value(JSON.stringify(data.accountNo));
					document.getElementById("userId").value = value(JSON.stringify(data.userId));
				}
			});
});
							
	jQuery(document).ready(function($) {
		$("#continue").click(function(){
			var createData = {};
			createData["userId"] = document.getElementById("userId").value;
			createData["accountNo"] = document.getElementById("accountNo").innerHTML;
			createData["balance"] = 0;
			createData["currency"] = document.getElementById("cur").value;
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "createaccount",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
					//$('#processedData').html(JSON.stringify(data));
					//$('#displayDiv').show();
					if (data.id != null) { 
					alert("User account created. Please login.");
					  location.replace("http://localhost:8080/login");
					  }
				}
			});
		});
	});
	function value(string){
	return string.substr(1, string.length - 2);
	}
</script>	
</body>
</html>