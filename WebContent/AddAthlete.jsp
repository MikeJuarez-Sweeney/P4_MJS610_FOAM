<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
	<h2>Enter A New Athlete</h2>
	<span style="color: red"> ${errors} </span>
	<form action="addAthlete" method="post">
		<table>
			<tr>
				<td>National ID: <br> <input type="text" name="nationalID"
					placeholder="Enter National ID" autofocus>
				</td>
			</tr>
			<tr>
				<td>First Name: <br> <input type="text" name="firstName"
					placeholder="Enter First Name">
				</td>
			</tr>
			<tr>
				<td>Last Name: <br> <input type="text" name="lastName"
					placeholder="Enter Last Name">
				</td>
			</tr>
			<tr>
				<td>Date Of Birth: <br> <input type="text"
					name="dateOfBirth" placeholder="yyyy-mm-dd">
				</td>
			</tr>
		</table>

		<br> <br> <input class="button" type="submit"
			value="Add Athlete" id="addAthlete">
		<a class="button" style="background-color: red;" href="viewRoster">Cancel</a> 

	</form>

</body>
</html>