<style>
#conteiner {
  height: 80%;
  width: 100%;
  background-color: powderblue;
  padding-top: 50px;
  padding-right: 30px;
  padding-bottom: 50px;
  padding-left: 80px;
}
</style>

<html>
<head>
<title>Mini BANK</title>
<script src="https://code.jquery.com/jquery-1.12.3.min.js"></script>
</head>
<body>
	<div>
	<table>
		<tr>
			<td align = "right">Hello</td><td id="name"></td><td></td><td></td><td></td>
		</tr>
		<tr>	
			<td>Account No:&nbsp;&nbsp;</td><td id="accountNo"></td><td>&nbsp;&nbsp;&nbsp;Balance:&nbsp;&nbsp;</td><td id ="balance"></td><td id="currency"></td>
		</tr>
	</table>
		<input type="hidden" id = "userId">
	</div>
	<hr/>
	
	<div>
	<table>
	<tr>
	   <td><input type="button" id="details" value="DETAILS" /></td>
	   <td><input type="button" id="deposit" value="DEPOSIT" /></td>
	   <td><input type="button" id="withdrawal" value="CASH OUT" /></td>
	   <td><input type="button" id="transaction" value="TRANSACTION" /></td>
	   <td width = "70%"></td>
	   <td><input type="button" id="logout" value="LOGOUT" /></td>
	</tr>
	</table>
	</div>
	
	<div id="conteiner">
	<div id="detailsDiv" style="display:none">
	<b>Account details</b><br> 
		<div id = "tbldata"></div>
		<br>
		<table>
		<tr>
		<td>Records per page</td><td>
				<select id="recordsPerPage">
                <option value="3">3</option>
                <option value="5">5</option>
                <option value="10">10</option>
                <option value="15">15</option>
                </select>
		</td><td><input type = "submit" id  = "minus" value = "<" ></td>
		<td><input type = "submit" id  = "plus" value = ">" ></td>
		<td>Page number:</td><td id = "pageNo">1</td>
		</tr>
		</table>
	</div>
	
	<div id="depositDiv" style="display:none">
		<b>Deposit</b><br>
		Short description<br>
		<textarea rows="2" cols="40" id = "depositTextarea">
        </textarea><br>
		<input type = "text" id = "depositInput">
		<select id="curDeposit">
                <option value="EUR">EUR</option>
                <option value="USD">USD</option>
                <option value="GBP">GBP</option>
                <option value="RUR">RUR</option>
                </select>
        <input type = "submit" id = "depositSubmit" value = "CONTINUE">
	</div>
	
	
	
	<div id="withdrawalDiv" style="display:none">
		<b>Cash out</b><br>
		Short description<br>
		<textarea rows="2" cols="40" id = "withdrawalTextarea">
        </textarea><br>
		<input type = "text" id = "withdrawalInput">
		<select id="curWithdrawal">
                <option value="EUR">EUR</option>
                <option value="USD">USD</option>
                <option value="GBP">GBP</option>
                <option value="RUR">RUR</option>
                </select>
        <input type = "submit" id = "withdrawalSubmit" value = "CONTINUE">
	</div>
	
	<div id="transactionDiv" style="display:none">
				<b>Transaction</b><br>
		Short description<br>
		<textarea rows="2" cols="40" id = "transactionTextarea">
        </textarea><br>
		Transaction sum:<br> <input type = "text" id = "transactionInput"><br>
        Beneficiary account number:<br> <input type = "text" id = "transactionAccount"><br>
        <input type = "submit" id = "transactionSubmit" value = "CONTINUE">
	</div>
	
	</div>
	
		<div id="displayDiv" style="display:none"><h3>JSON Data returned from Server after processing</h3>
		<div id="processedData"></div>
	</div>
<script>

$(document).ready(function() {
			var createData = {};
			createData["userdata"] = "getdata";
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getuserdata",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
				if (data.userId == null) { 
					  location.replace("http://localhost:8080/login");
					  }else{
					document.getElementById("name").innerHTML = value(JSON.stringify(data.name));
					document.getElementById("accountNo").innerHTML = value(JSON.stringify(data.accountNo));
					document.getElementById("userId").value = value(JSON.stringify(data.userId));
					document.getElementById("currency").innerHTML = value(JSON.stringify(data.accountCur));
					document.getElementById("balance").innerHTML = value(JSON.stringify(data.balance));
					}
				}
			});
});
							
	jQuery(document).ready(function($) {
		$("#logout").click(function(){
			var createData = {};
			createData["userdata"] = "logout";
			$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "getuserdata",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
					if (data.id == null) { 
					  location.replace("http://localhost:8080/login");
					  }
				}
			});
		});

		$("#transactionSubmit").click(function(){
		var createData = {};
		createData["userId"] = document.getElementById("userId").value;
		createData["doUserAccountNo"] = document.getElementById("transactionAccount").value;
		createData["curDoUser"] = "";
		createData["curUser"] =  document.getElementById("currency").innerHTML;
		createData["deposit"] =  document.getElementById("transactionInput").value; 
		createData["withdrawal"] = document.getElementById("transactionInput").value;   
	    createData["description"] = document.getElementById("transactionTextarea").value;
	    createData["date"] = "";
		$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "transaction",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
                document.getElementById("balance").innerHTML = value(JSON.stringify(data.balance));
                document.getElementById("depositInput").value = "";
                document.getElementById("depositTextarea").value = "";
                alert("Operation complete");
				}
			});
		});

		$("#depositSubmit").click(function(){
		var createData = {};
		createData["userId"] = document.getElementById("userId").value;
		createData["doUserAccountNo"] = "";
		createData["curDoUser"] = document.getElementById("curDeposit").value;
		createData["curUser"] =  document.getElementById("currency").innerHTML;
		createData["deposit"] = document.getElementById("depositInput").value;
		createData["withdrawal"] = 0;   
	    createData["description"] = document.getElementById("depositTextarea").value;
	    createData["date"] = "";
		$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "operation",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
                document.getElementById("balance").innerHTML = value(JSON.stringify(data.balance));
                document.getElementById("depositInput").value = "";
                document.getElementById("depositTextarea").value = "";
                alert("Operation complete");
				}
			});
		});
		
		$("#withdrawalSubmit").click(function(){
		var createData = {};
		createData["userId"] = document.getElementById("userId").value;
		createData["doUserAccountNo"] = ""; 
		createData["curDoUser"] = document.getElementById("curWithdrawal").value;
		createData["curUser"] = document.getElementById("currency").innerHTML;
		createData["deposit"] = 0;
		createData["withdrawal"] = document.getElementById("withdrawalInput").value;
		createData["description"] = document.getElementById("withdrawalTextarea").value;
		createData["date"] = "";
		$.ajax({
				type : "POST",
				contentType : "application/json",
				url : "operation",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
                document.getElementById("balance").innerHTML = value(JSON.stringify(data.balance));
                document.getElementById("withdrawalInput").value = "";
                document.getElementById("withdrawalTextarea").value = "";
                alert("Operation complete");
				}
			});
		});
		
		$("#details").click(function(){
		document.getElementById("detailsDiv").style.display = "block";
		document.getElementById("depositDiv").style.display = "none";
		document.getElementById("withdrawalDiv").style.display = "none";
		document.getElementById("transactionDiv").style.display = "none";
		paginator();
		});
		
		$("#plus").click(function(){
			var plus = document.getElementById("pageNo").innerHTML;
			plus = parseInt(plus) + 1;
			document.getElementById("pageNo").innerHTML = plus;
		    paginator();
			
		});
		
	    $("#minus").click(function(){
           var minus = document.getElementById("pageNo").innerHTML;
           minus = minus - 1;
           if (minus <= 0) minus = 1;
           document.getElementById("pageNo").innerHTML = minus;
		paginator();
		});	
		
		$("#deposit").click(function(){
		document.getElementById("detailsDiv").style.display = "none";
		document.getElementById("depositDiv").style.display = "block";
		document.getElementById("withdrawalDiv").style.display = "none";
		document.getElementById("transactionDiv").style.display = "none";
		});
		
		$("#withdrawal").click(function(){
		document.getElementById("detailsDiv").style.display = "none";
		document.getElementById("depositDiv").style.display = "none";
		document.getElementById("withdrawalDiv").style.display = "block";
		document.getElementById("transactionDiv").style.display = "none";
		});
		
		$("#transaction").click(function(){
		document.getElementById("detailsDiv").style.display = "none";
		document.getElementById("depositDiv").style.display = "none";
		document.getElementById("withdrawalDiv").style.display = "none";
		document.getElementById("transactionDiv").style.display = "block";
		});
		
	});
	
	function value(string){
	return string.substr(1, string.length - 2);
	}

    function paginator (){
        var pgnr = document.getElementById("pageNo").innerHTML;
        var createData = {};
		createData["userId"] = document.getElementById("userId").value;
		createData["pageNumber"] = document.getElementById("pageNo").innerHTML;
		createData["recordsPerPage"] = document.getElementById("recordsPerPage").value;
    		   $.ajax({
				type : "POST",
				contentType : "application/json",
				url : "details",
				data : JSON.stringify(createData),
				dataType : 'json',				
				success : function(data) {
					//$('#processedData').html(JSON.stringify(data));
					//$('#displayDiv').show();
					if (data == ""){
					  pgnr = pgnr - 1;
					  document.getElementById("pageNo").innerHTML = pgnr;
					 }
					generateDynamicTable(data);				
				}
			});
     }

	function generateDynamicTable(tbldata){
	
		var tblC = tbldata.length;
		if(tblC>0){
			var table = document.createElement("table");
			table.style.width = '50%';
			table.setAttribute('border', '1');
			table.setAttribute('cellspacing', '0');
			table.setAttribute('cellpadding', '5');
			var col = []; 
			for (var i = 0; i < tblC; i++) {
				for (var key in tbldata[i]) {
					if (col.indexOf(key) === -1) {
						col.push(key);
					}
				}
			}
			var tHead = document.createElement("thead");	
			var hRow = document.createElement("tr");
			for (var i = 0; i < col.length; i++) {
					var th = document.createElement("th");
					th.innerHTML = col[i];
					hRow.appendChild(th);
			}
			tHead.appendChild(hRow);
			table.appendChild(tHead);
			var tBody = document.createElement("tbody");	
			for (var i = 0; i < tblC; i++) {
			
					var bRow = document.createElement("tr");
					
					for (var j = 0; j < col.length; j++) {
						var td = document.createElement("td");
						td.innerHTML = tbldata[i][col[j]];
						bRow.appendChild(td);
					}
					tBody.appendChild(bRow)
			}
			table.appendChild(tBody);	
			var divContainer = document.getElementById("tbldata");
			divContainer.innerHTML = "";
			divContainer.appendChild(table);
		}	
	}
	
	
</script>	
</body>
</html>