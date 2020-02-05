<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>FOAM</title>
<style type="text/css">
table {
	border: 1px solid black;
	padding: 5px;
	text-align: left;
	border-radius: 5px;
}

tr, td, h2 {
	padding: 5px;
	text-align: left;
}

tr:nth-child(odd) {
	background-color: #f2f2f2;
}

th {
	color: blue;
}

body {
	font-family: Arial, Helvetica, sans-serif;
	font-size: 11pt;
	margin-left: 2em;
	margin-right: 2em color: white;
	background-color: Snow;
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

a:link {
	color: blue;
}

a:visited {
	color: blue;
}

a:hover {
	color: skyBlue;
}

a:active {
	color: red;
}
</style>
</head>
<body>

	<h2 style="color: blue">Roster</h2>
	<div style="color: red">${confirmation}</div>
	
	<form action="viewRoster" method="post" autocomplete="on">
		<table id="athTable">
			<tr>
				<th id="nationalID" >National ID</th>
				<th id="firstName">First Name</th>
				<th id="lastName">Last Name</th>
				<th id="dateOfBirth">Date of Birth</th>
				<th id="age">Age</th>
				<th id="eligible">Eligible</th>
			</tr>
			<c:forEach items="${athletes}" var="athlete">
				<tr>
					<td>${athlete.nationalID}</td>
					<td>${athlete.firstName}</td>
					<td>${athlete.lastName}</td>
					<td>${athlete.dateOfBirth}</td>
					<td style="text-align: center">
						<c:choose>
							<c:when test="${athlete.age  > 0}">${athlete.age}
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose></td>
					<td style="text-align: center"><c:choose>
							<c:when test="${athlete.eligible == true}">
								<font color="blue">&#10004;</font>
							</c:when>
							<c:otherwise>
								<font color="red">&#10006;</font>
							</c:otherwise>
						</c:choose></td>
					<th><a href="editAthlete?nationalID=${athlete.nationalID}">Edit</a></th>
					<th><a href="deleteAthlete?nationalID=${athlete.nationalID}">Delete</a></th>
				</tr>
			</c:forEach>
			<tr>
				<td><a style="color: white" class="button"
					href="AddAthlete.jsp">Add New Athlete</a><br></td>
			</tr>
		</table>
		

	</form>

</body>

</html>