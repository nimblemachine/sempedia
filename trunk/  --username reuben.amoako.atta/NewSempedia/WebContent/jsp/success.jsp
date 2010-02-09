<%@ taglib prefix="core" uri="/WEB-INF/taglibs/c.tld" %>
<%@ taglib prefix="fmt" uri="/WEB-INF/taglibs/fmt.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<h2>Logon Success</h2>
<table border="1" width="100" height="100">

<c:forEach items="${userProfile}" var="userP">
<tr>
<td><c:out value="${userP.get(1)}"/></td>
<td><c:out value="${userP.get(2)}"/></td>
</tr>
</c:forEach>
</table>
</body>
</html>