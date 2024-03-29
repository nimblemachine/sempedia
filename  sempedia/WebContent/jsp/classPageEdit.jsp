<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="java.util.Iterator"%>
<%@ page import="dao.PredicateDao"%>
<%@ page import="dao.ObjectDao"%>
<%@ page import="dao.ClassDao"%>
<%@ page import="model.TriplePOJO;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sempedia | Making computers think about data the way we
do</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script>
	$(document).ready( function() {
		$(".button-table").hover( function() {
			$(this).addClass("button-table-over");
		}, function() {
			$(this).removeClass("button-table-over");
		});
		$(".top-button").hover( function() {
			$(this).addClass("top-button-active");
		}, function() {
			$(this).removeClass("top-button-active");
		});
		$(".tag-edit").hover( function() {
			$(this).addClass("tag-edit-over");
		}, function() {
			$(this).removeClass("tag-edit-over");
		});
	});
</script>
</head>
<body>
<table width="80%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td rowspan="2" valign="bottom" class="Header1">
			<a href="index.jsp"> 
			<img src="images/sempedia.jpg" alt="" width="299" height="60" border="0" /> </a>
		</td>
		<td width="90%" rowspan="2" valign="bottom" class="normal-small">
		<table border="0" cellspacing="8" cellpadding="6">
			<tr>
				<td width="52" class="normal-medium">&nbsp;</td>
				<td class="top-button"><a href="jsp/addClass.jsp" class="normal-medium">Add Class</a></td>
				<td class="top-button"><a href="jap/addObject.jsp" class="normal-medium">Add Object </a></td>
			</tr>
		</table>
		</td>
		<td align="right" class="Header1">
		<table border="0" cellspacing="0" cellpadding="8">
			<tr>
				<td align="right" bgcolor="#dedede" class="normal-small">
					<a href="faq.html" class="normal-small-bold-link">faq</a> | 
					<a href="about.html" class="normal-small-bold-link">about</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="right" nowrap="nowrap" class="Header1">
		<form method="post" name="seedSearch" action="SeedSearch"><label> <input
			type="text" class="normal" id="searchString" name="searchString" size="32" /> <input
			name="submit2" type="submit" class="normal" id="submit2"
			value="Search" /> </label></form>
		</td>
	</tr>
</table>
<hr width="80%" size="1" />
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td colspan="3" valign="top" nowrap="nowrap" class="normal-small">Return
		to <a href="ClassPage?classId=<% out.print(request.getAttribute("claId"));%>" class="normal-small-bold-link"> <% out.print(request.getAttribute("className")); %>
		</a></td>
	</tr>
	<tr id="class-header">
		<td valign="top">
		<form method="get" action="EditClassName">
		<div>
			<input type="hidden" name="classId" id="classId" value="<%out.print(request.getAttribute("claId"));%>" />
			<input type="text" size="32" name="className" id="className" value="<%out.print(request.getAttribute("className"));%>" />
			<br />
			<input type="submit" value="Save Class" />
		</div>
		</form>
		</td>
		<td width="50%" valign="top" nowrap="nowrap" align="right"><span class="normal-small"><br />is a </span></td>
		<td valign="top" nowrap="nowrap">
		<div id="edit-class-div">
		<form method="get" action="EditSuper">
			<input type="hidden" name="classId" id="classId" value="<%out.print(request.getAttribute("claId"));%>" />
			<label>
				<input class="small-link" type="text" id="superClass" name="superClass" size="32" value="<%out.print(request.getAttribute("superClass"));%>" />
			</label>
			<label><br /><input type="submit" value="Save Super Class" /> </label>
		</form>
		</div>
		</td>
	</tr>
</table>
<form method="post" action="EditClassText">
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0" id="object-panel">
			<input type="hidden" name="classId" id="classId" value="<%out.print(request.getAttribute("claId"));%>" />
	<tr>
		<td colspan="2" align="left" valign="top">
		<textarea name="text" rows="6" id="text" class="description-content-textarea" style="width: 100%">
			<% out.print(request.getAttribute("text"));%>
		</textarea>
		<input type="submit" name="save" id="save" value="Save" />
		<input type="submit" name="cancel" id="cancel" value="Cancel" /></td>
	</tr>
</table>
</form>
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr id="predicate-header">
		<td><span class="Header2-black">Semantic Properties</span></td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr id="predicates">
		<td width="40%" height="100%" colspan="2" valign="top">
		<table border="0" cellspacing="0" cellpadding="6">
			<tr>
				<td nowrap="nowrap"><label> <input name="addSemTag" type="submit" id="add-property-btn" value="+ Add a Semantic Property" /> </label></td>
			</tr>
			<tr id="add-property">
				<td valign="top" nowrap="nowrap">
				<p><label class="normal-small">Property<br />
				<input type="text" name="textfield" id="textfield" class="normal" />
				</label> <label> <input type="submit" name="save" id="save"
					value="Save" /> </label> <input type="submit" name="cancel" id="cancel"
					value="Cancel" /> <label class="normal-small"><br />
				</label> <br />
				</p>
				</td>
			</tr>
		</table>
		<table border="0" cellspacing="0" cellpadding="6">
			<tr>
				<td nowrap="nowrap" class="predicate"><span class="normal">
				<%  HashMap<Integer, String> properties = new HashMap<Integer, String>();
					properties = (HashMap<Integer, String>) request.getAttribute("classProps");
				%>
			</span></td>
			</tr>
			<%
				Set<Integer> ids = new HashSet<Integer>();
				ids = properties.keySet();
				Iterator<Integer> itr = ids.iterator();
				PredicateDao pdao = new PredicateDao();
				while (itr.hasNext()) {
					int id = itr.next();
					String predicate = pdao.getPredName(id);
			%>
			<tr>
				<td nowrap="nowrap" class="predicate"><span class="normal">
				<%
					out.print(predicate);
				%> </span></td>
				<td nowrap="nowrap">&nbsp;</td>
				<td colspan="2" nowrap="nowrap">&nbsp;</td>
			</tr>
			<%
				}
			%>
		</table>
		</td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td bgcolor="#4C88BE"><img src="images/1px-transparent.gif"
			width="1" height="1" alt="1px" /></td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr>
		<td class="normal-small">
			Design, development and maintenance by - iVEC &quot;The hub of Advanced Computing in Western Australia&quot; | Creative Commons License
		</td>
	</tr>
</table>
</body>
</html>