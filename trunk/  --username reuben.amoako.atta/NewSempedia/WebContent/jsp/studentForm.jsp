<%@ taglib prefix="core" uri="/WEB-INF/taglibs/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/taglibs/fmt.tld" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>
<%@ taglib prefix="spring" uri="/spring" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1><center>CHANGE TO YOUR FORM....</center></h1>
<form method="post">
		<table width="25%" border="1">
			<tr>
				<td align="center" bgcolor="lightblue">Student Information</td>
			</tr>
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="33%" align="right">Student name: </td>
							<td width="66%" align="left">
								
								<input type="text" 
								       name="name" 
								       value="<core:out value="${status.value}"/>"/>		
								
							</td>
							
						</tr>
						<tr>
							<td colspan="2" align="center">
								<spring:hasBindErrors name="studentBean">
								<font color="red"><core:out value="${status.errorMessage}"/></font>
								</spring:hasBindErrors>
							</td>
						</tr>
						<tr>
							<td width="33%" align="right">Roll no: </td>
							<td width="66%" align="left">
								
								<input type="password" name="roll" />
								
							</td>
						</tr>
						
						<tr>
							<td colspan="2" align="center">
								<spring:hasBindErrors name="studentBean">
								<font color="red"><core:out value="${status.errorMessage}"/></font>
								</spring:hasBindErrors>
							</td>
						</tr>
						<tr>
							<td width="33%" align="right">Marks : </td>
							<td width="66%" align="left">
								
								<input type="text" 
								       name="marks" 
								       value="<core:out value="${status.value}"/>"/>		
								
							</td>
							
						</tr>
						<tr>
							<td colspan="2" align="center">
								<spring:hasBindErrors name="studentBean">
								<font color="red"><core:out value="${status.errorMessage}"/></font>
								</spring:hasBindErrors>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" alignment="center" value="Submit">
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>
		
	</form>
</body>
</html>