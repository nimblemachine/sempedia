<%@ taglib prefix="core" uri="/WEB-INF/taglibs/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/taglibs/fmt.tld" %>
<%@ taglib prefix="str" uri="http://jakarta.apache.org/taglibs/string-1.1" %>
<%@ taglib prefix="spring" uri="/spring" %>


<html>
<head><title>Spring with hibernate application</title></head>
<body>

<center>

	<h1>Spring MVC-Hibernate</h1>
	<br/>
	<form method="post" >
	<table border="1" width="500px" bordercolor="lavender">
	<tr align="center">
	<td>
		<table width="100%" border="0">
			<tr>
				<td align="center" bgcolor="lavender"> <font face="verdana"><b>POC</b></font></td>
			</tr>
			<tr>
				<td>
					<table border="0" width="100%">
						<tr>
							<td width="33%" align="right">Entry field 1 </td>
							<td width="66%" align="left">
								<spring:bind path="userBean.username">
								<input type="text" 
								       name="username" 
								       value="<core:out value="${status.value}"/>"/>		
								</spring:bind>
							</td>
							
						</tr>
						<tr>
							<td colspan="2" align="center">
								<spring:hasBindErrors name="userBean.username">
								<font color="red"><core:out value="${status.errorMessage}"/></font>
								</spring:hasBindErrors>
							</td>
						</tr>
						<tr>
							<td width="33%" align="right">Entry Field 2</td>
							<td width="66%" align="left">
								<spring:bind path="userBean.password">
								<input type="password" name="password" />
								</spring:bind>
							</td>
						</tr>
						<tr>
							<td colspan="2" align="center">
								<spring:hasBindErrors name="userBean.password">
								<font color="red"><core:out value="${status.errorMessage}"/></font>
								</spring:hasBindErrors>
							</td>
						</tr>
						<tr>
							<td align="center" colspan="2">
								<input type="submit" alignment="center" value="Click to insert into DB">
							</td>
						</tr>
					</table>
					
				</td>
			</tr>
		</table>
	</td>
	</tr>
	</table>	
	</form>

</center>

</body>
</html>