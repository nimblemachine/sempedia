<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sempedia | Making computers think about data the way we
do</title>
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
<link href="../css/jquery.autocomplete.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="../js/jquery.js"></script>
<script type="text/javascript" src="../js/jquery.autocomplete.js"></script>
<script>
	$(document).ready( function() {

			    $.ajax({
			        type: "GET",
			        url: "../ClassAutoComplete",
			        success: function(data) {
			            var dataArray = data.split("|");
			          // alert(dataArray);
			            $("#class-name").autocomplete(dataArray);
			            $("#super-class").autocomplete(dataArray);
			        }
			    });

			    
				
				$(".top-button").hover( function() {
					$(this).addClass("top-button-active");
				}, function() {
					$(this).removeClass("top-button-active");
				});
				/////////////////////////											
					$("#object-created-panel").hide();
					$("#create-class-btn").click( function() {
						$("#object-created-panel").show("slow");
					}, function() {
						$("#new-object-panel").hide();
					}
					//	return: false;
							);

					$("#browse").hide();
					//$("#object-browse").click(
					//	function() { $("#browse").toggle("slow"); }
					//);

					$("#object-page-editor").hide();
					$("#edit_object_page_btn").click( function() {
						$("#object-page-editor").toggle("slow");
					}
					//        		function() { $("#new-object-panel").hide(); } 				
							//	return: false;
							);

					$("#add_property")
							.click(
									function() {
										$("#property-row")
												.append(
														"<tr><td nowrap='nowrap'>&nbsp;</td><td nowrap='nowrap'>&nbsp;</td></tr>");
									});
				});
</script>
<style type="text/css">
<!--
body {
	margin-top: 0px;
}
-->
</style>
</head>

<body>
<table width="80%" border="0" align="center" cellpadding="0"
	cellspacing="0">
	<tr>
		<td rowspan="2" valign="bottom" class="Header1"><a href="../index.jsp"><img src="../images/sempedia.jpg" alt="" width="299" height="60" border="0" /></a></td>
		<td width="90%" rowspan="2" valign="bottom" class="normal-small">
		<table border="0" cellspacing="8" cellpadding="6">
			<tr>
				<td width="52" class="normal-medium">&nbsp;</td>
				<td class="top-button-active"><a href="addClass.jsp" class="normal-medium">Add Class</a></td>
				<td class="top-button"><a href="addObject.jsp" class="normal-medium">Add Object </a></td>
			</tr>
		</table>
		</td>
		<td align="right" class="Header1">
		<table border="0" cellspacing="0" cellpadding="8">
			<tr>
				<td align="right" bgcolor="#dedede" class="normal-small">
				<a class="normal-small-bold-link" href="">logout </a>| 
				<a href="faq.html" class="normal-small-bold-link">faq</a> | 
				<a href="about.html" class="normal-small-bold-link">about</a></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td align="right" nowrap="nowrap" class="Header1">
		<form id="form1" name="form1" method="post" action=""><label>
		<input type="text" class="normal" id="search_box" size="32" /> <input name="submit" type="submit" class="normal" id="submit" value="Search" />
		</label></form>
		</td>
	</tr>
</table>
<hr width="80%" size="1" />
<form method="post" name="addClass" action="../AddClass">
<table width="80%" border="0" align="center" cellpadding="6" cellspacing="0">
	<tr>
		<td colspan="2"><span class="header3">Details of New Class</span></td>
	</tr>
	<tr>
		<td colspan="2">
		<hr size="1" />
		</td>
	</tr>
	<tr>
		<td><span class="normal">Class Name</span></td>
		<td><span class="normal"> <input name="class-name" type="text" id="class-name" size="40" class="input-field" /> </span></td>
	</tr>
	<tr>
		<td colspan="2"><span class="comment-text"><strong>Newbie Help:</strong> add as many classes you can think of. For example a basketballer
		is a sportsperson which is a human. So if your class was &quot;basketballer&quot;, then you would add the classes sportsperson and human below.</span></td>
	</tr>
	<tr>
		<td><span class="normal">Is a</span></td>
		<td><span class="normal"> <input name="super-class"
			type="text" class="input-field" id="super-class" size="24" /> </span></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td><input type="submit" name="create-class-btn" id="create-class-btn" value="Create Class" /></td>
	</tr>
	<tr>
		<td>&nbsp;</td>
		<td>&nbsp;</td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	<tr>
		<td bgcolor="#4C88BE"><img src="../images/1px-transparent.gif" width="1" height="1" alt="1px" /></td>
	</tr>
</table>
</form>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
	<tr>
		<td class="normal-small">&copy; Sempedia 2009 | All rights reserved</td>
	</tr>
</table>
</body>
</html>
