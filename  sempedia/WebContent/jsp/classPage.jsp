<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Set"%>
<%@ page import="java.util.HashSet"%>
<%@ page import="dao.PredicateDao"%>
<%@ page import="java.util.Iterator"%>
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

		$(".top-button").hover( function() {
			$(this).addClass("top-button-active");
		}, function() {
			$(this).removeClass("top-button-active");
		});
		
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
	});
</script>
</head>

<body>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td rowspan="2" valign="bottom" class="Header1"><a href="index.jsp"><img src="images/sempedia.jpg" alt="" width="299" height="60" border="0" /></a></td>
		<td width="90%" rowspan="2" valign="bottom" class="normal-small">
		<table border="0" cellspacing="8" cellpadding="6">
			<tr>
				<td width="52" class="normal-medium">&nbsp;</td>
				<td class="top-button"><a href="jsp/addClass.html" class="normal-medium">Add Class</a></td>
				<td class="top-button"><a href="jsp/addObject.html" class="normal-medium">Add Object </a></td>
			</tr>
		</table>
		</td>
		<td align="right" class="Header1">
		<table border="0" cellspacing="0" cellpadding="8">
			<tr>
				<td align="right" bgcolor="#dedede" class="normal-small">
				  <a href="faq.html" class="normal-small-bold-link">faq</a>
				  | <a href="about.html" class="normal-small-bold-link">about</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
	  <td align="right" nowrap="nowrap" class="Header1">
		<form method="post" name="seedSearch" action="SeedSearch">
		  <label>
		    <input type="text" class="normal" id="searchString" name="searchString" size="32" />
		    <input name="submit2" type="submit" class="normal" id="submit2" value="Search" />
          </label>
        </form>
	  </td>
	</tr>
</table>
<hr width="80%" size="1" />
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">
	<tr id="class-header">
	  <td height="100%" valign="top">
		<strong>
			<span class="Header2-black" id="class-name">
			<% out.print((String) request.getAttribute("className")); %>
			</span>
		</strong> 
		<% String superClass = (String) request.getAttribute("superClass");
			if(!superClass.equals("")) {
			%>
		<span class="Header2-black">
			<span class="normal-small">
			is a </span></span>
			<strong>
				<span id="class-name2">
				<a href="ClassPage?classId=
					<% out.print((Integer) (request.getAttribute("superClassId")));%>"
					 class="small-link">
					<% out.print((String) request.getAttribute("superClass")); %>
				</a>
				</span>
			</strong>
		<%
			}
		%>	
		</td>
		<td align="right" valign="top">
		<table border="0" cellspacing="0" cellpadding="4">
			<tr>
				<td class="normal-small">
					<a href="EditClass?id=<% out.print(request.getAttribute("classId")); %>"><img src="images/edit.gif" alt="" width="19" height="19" /></a>
				</td>
				<td class="normal-small">
					<a href="EditClass?id=<% out.print(request.getAttribute("classId")); %>">Edit this Page</a>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0" id="object-panel">
	<tr>
		<td valign="top">
		<p><span class="normal-small" id="description">	<% out.print(request.getAttribute("text"));%></span></p>
		<p>&nbsp;</p>
		</td>
	</tr>
</table>
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
				<td nowrap="nowrap"><label><input type="submit" id="add-property-btn" value="+ Add a Semantic Property" /> </label></td>
			</tr>
			<tr id="add-property">
				<td valign="top" nowrap="nowrap">
				<form method="get" action="AddProperty">
					<p><label class="normal-small">Property<br />
					<input type="hidden" id="classId" name="classId" value="<% out.print(request.getAttribute("classId")); %>" />
					<input type="text" name="property" id="property" class="normal" />
					</label><label><input type="submit" name="button" id="button" value="Save" /> </label> <input type="submit" name="cancel-add" id="cancel-add" value="Cancel" /> <label class="normal-small"><br />
					</label><br />
					</p>
				</form>
				</td>
			</tr>
		</table>

		<table border="0" cellspacing="0" cellpadding="6">
			<tr>
				<td nowrap="nowrap" class="predicate">
					<span class="normal">
					<% 
						HashMap<Integer,String> properties = new HashMap<Integer,String>();
						properties = (HashMap<Integer,String>)request.getAttribute("properties");
					%>
				</span></td>
			</tr>
			<%
				Set<Integer> ids = new HashSet<Integer>();
					ids = properties.keySet();
				Iterator<Integer> itr = ids.iterator();
				PredicateDao pdao = new PredicateDao();
				while(itr.hasNext()){
					
					int id = itr.next();
					String predicate = pdao.getPredName(id);
					%>
			<tr>
				<td nowrap="nowrap" class="predicate">
				<span class="normal"><% out.print(predicate); %></span></td>
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
		<td class="normal-small">&copy; Sempedia 2009 | Creative Commons License</td>
	</tr>
</table>
<p>&nbsp;</p>
</body>
</html>