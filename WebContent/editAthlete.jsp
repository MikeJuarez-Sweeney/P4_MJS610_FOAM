<!DOCTYPE html>
<html>
<head>

<meta charset="ISO-8859-1">
<title>FOAM</title>
<style type="text/css">
body {
	font-size: 12pt;
	margin-left: 2em;
}

#id {
	color: red;
	background-color: red;
}

h2, tr {
	color: blue;
}

span {
	color: red;
}

.button {
	background-color: blue;
	border-radius: 6px;
	display: inline-block;
	cursor: pointer;
	color: #ffffff;
	font-family: Arial;
	font-size: 12px;
	padding: 3px 11px;
	text-decoration: none;
	text-shadow: 0px 0px 0px #0d00ff;
}

.button:hover {
	background-color: SkyBlue;
}

.button:active {
	position: relative;
	top: 1px;
}
</style>
</head>
<body>

	<h2>Edit Athlete</h2>
	<span style="color: red"> ${errors} </span>

	<form action="saveChanges" method="post">

		<table>

			<tr>
				<td>National ID: <span>${athlete.nationalID}</span><br> <input
					hidden="" type="text" name="nationalID" readonly
					value="${athlete.nationalID}">
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td>First Name: <br> <input type="text" name="firstName"
					value="${athlete.firstName}">
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td>Last Name: <br> <input type="text" name="lastName"
					value="${athlete.lastName}">
				</td>
			</tr>
			<tr>
			</tr>
			<tr>
				<td>Date Of Birth: <br> <input type="DATE" pattern="([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))"
					name="dateOfBirth" value="${athlete.dateOfBirth}">
				</td>
			</tr>
		</table>
		<br> <a class="button" style="background-color: red;" href="viewRoster">Cancel</a> 
			<input class="button" type="submit" value="Save Changes" id="saveChanges">
	</form>
	<br>

</body>
</html>