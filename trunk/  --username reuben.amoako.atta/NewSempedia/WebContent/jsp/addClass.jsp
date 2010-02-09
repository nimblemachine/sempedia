<%@ taglib prefix="c" uri="/WEB-INF/taglibs/c.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sempedia | Making computers think about data the way we
do</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="css/jquery.autocomplete.css"
	type="text/css" />
<script type="text/javascript" src="js/jquery.jeditable.mini.js">
        </script>
<script>
            $(document).ready(function(){
            	 
            	$(".removeButton").click(function(){
                  	var propertySpanID = $(this).attr('id');
                   // alert('propertySpanID '+propertySpanID);
               	});
               	
            	$("#object-created-panel").hide();
            	if(document.getElementById('method').value=="created"){
            		$("#object-created-panel").show("slow");
            	}
            
                $(".top-button").hover(function(){
                    $(this).addClass("top-button-active");
                }, function(){
                    $(this).removeClass("top-button-active");
                });
                						
                $("#create-object-btn").click(function(){
                	if(document.getElementById('className').value!=''){
                    	$("#object-created-panel").show("slow");
                   	}else{
                   		$("#object-created-panel").hide();
                   	}
                }, function(){
                    $("#new-object-panel").hide();
                }    //	return: false;
                );
                
                $("#browse").hide();
                //$("#object-browse").click(
                //	function() { $("#browse").toggle("slow"); }
                //);
                
                $("#object-page-editor").hide();
                $("#edit_object_page_btn").click(function(){
                    $("#object-page-editor").toggle("slow");
                }    //        		function() { $("#new-object-panel").hide(); } 				
                //	return: false;
                );
                
 			 $("#add_propertyNew").click(
 			 		function() {	
        				var divIdName;
        				var butId;
      					var ni = document.getElementById('myDiv');
						var numi = document.getElementById('theValue');
						var num = (document.getElementById('theValue').value -1)+ 2;
						numi.value = num;
						divIdName = "my"+num+"Div";
						butId = "removeBut"+num;
						var newdiv = document.createElement('div');
						newdiv.setAttribute("id",divIdName);
						newdiv.innerHTML = "<div id='"+divIdName+"'><table border='0' width='100%'> <tr valign='bottom'><td valign='bottom'><input type='text' class='ie6-input-text' id='property' name='property' size='24'></td><td valign='bottom'><input type='button' id='"+butId+"' class='ie6-button-button'  value='Remove' ></td></tr></table></div>";
						ni.appendChild(newdiv);
						$('#'+butId).bind('click',function(event) {
							// document.getElementById(butId).parentNode.parentNode.parentNode.parentNode.parentNode.innerHTML="";
							
							var d = document.getElementById('myDiv');
							var olddiv = document.getElementById(divIdName);
							d.removeChild(olddiv);
							
							
						});
				});
				
				$("#save").click(
        			function() {
        				var finalList = '';
						var propertyNameValue; 
						var propertiesArray = new Array();
						var countProperties = document.getElementById('theValue').value;
						var len = document.getElementsByName('property').length;
						var tempList = new Array();
						var firstname = document.getElementById('property').value;
						for(var j = 0 ;j<len;j++){
							propertiesArray[j] = document.getElementsByName('property')[j].value;
							propertyNameValue = propertiesArray[j];
							propertyNameValue = propertyNameValue.replace(/(\s+)+/g," ");
							propertyNameValue = propertyNameValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
							if(propertyNameValue != ''){
								finalList = finalList + propertyNameValue;
								finalList = finalList + '::';
							}else{
								alert('Please enter property name');
								return false;
							} 
						}
						tempList = finalList.split("::");
						if(document.getElementById('superClassPropertyList')!=null){
							var superClassPropetyLength = document.getElementById('superClassPropertyList').length;
							superClassPropetyLength = superClassPropetyLength - 1;
							var superProperty;
							var superPropertyTemp;
							var tempListTemp;
							for(var k = 0; k<(tempList.length)-1; k++){
								for(var i = 1; i<=superClassPropetyLength; i++){
									superProperty = document.getElementById('superClassPropertyList').options[i].value;
									superPropertyTemp = superProperty.toUpperCase();
									tempListTemp = tempList[k].toUpperCase();
									superPropertyTemp = superPropertyTemp.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
									tempListTemp = tempListTemp.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
									if(tempListTemp == superPropertyTemp){
										alert('Given property '+superProperty+' exists in one of its super class. Please select another property');
										return false;
									}else{}
								}
							}
						}else{
							document.getElementById('subClassId').value = document.getElementById('superClassId').value;
						}
						for(var count = 0; count<(tempList.length)-1; count++){
							for(var j = count+1; j<=(tempList.length)-1; j++){
								//alert('tempList[count] '+tempList[count].toUpperCase()+' tempList[j] '+tempList[j].toUpperCase());
								if(tempList[count].toUpperCase()==tempList[j].toUpperCase()){
									alert('Duplicate class property '+tempList[j]+' exists');
									return false;
								}else{}
							}
						}
						if(document.getElementById('superClassPropertyList')!=null){
							document.getElementById('allProperties').value = finalList;
							var classNameValue = document.getElementById('classNameHidden').value;
							classNameValue = classNameValue.replace(/(\s+)+/g," ");
							classNameValue = classNameValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
							document.classForm.className.value = classNameValue;
							document.classForm.action="/NewSempedia/createClass.htm?action=saveClass";
							document.classForm.submit();
						}
				});
          });
        </script>
<script type="text/javascript">
        function showCreatedClassDiv(){
			var classNameValue = document.getElementById('className').value;
			classNameValue = classNameValue.replace(/(\s+)+/g," ");
			classNameValue = classNameValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
			if(classNameValue != ''){
				document.classForm.className.value = classNameValue;
				document.classForm.action="/NewSempedia/createClass.htm?action=addClass";
				document.classForm.submit();
			}else{
				alert('Please enter class name');
				return false;
			}
		}
		
		function createSelectBox(box,selectBoxValue){
			var propertySelectBox = document.getElementById(box);
			var optionNew = document.createElement('option');
			optionNew.text = selectBoxValue;
			optionNew.value = selectBoxValue;
			propertySelectBox.options.add(optionNew);
		}
		
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
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
	<%@ include file="/jsp/sempediaHeader.jsp" %>
</table>
<hr width="80%" size="1" />
<form method="post" name="classForm">
<table width="80%" border="0" align="center" cellpadding="8"
	cellspacing="0">

	<tr>
		<td>
		<table width="100%" border="0" cellpadding="6" cellspacing="0"
			id="new-object-panel">
			<tr>
				<td colspan="2" nowrap="nowrap" class="header3" id="object-details">
				Details of New Class</td>
			</tr>

			<tr>
				<td width="12%" nowrap="nowrap"><img
					src="images/1px-transparent.gif" alt="d" width="1" height="1" /></td>
				<td><img src="images/1px-transparent.gif" alt="d" width="1"
					height="1" /></td>
			</tr>
			<tr>
				<td colspan="6" align="center">
				<input type="hidden" name="method"
						id="method" value='<c:out value="${classValue.operation}" />' /> 
				<c:if
					test="${messageColor == 'red'}">
					<font class="messages" color="red" style="text-align: center">
					<font color="red"><b><c:out value="${exceptionMessage}" />
					</b></font> <b><c:out value="${classMessage}" /></b> <b><c:out
						value="${message}" /></b> </font>
				</c:if> <c:if test="${messageColor == 'blue'}">
					<font class="messages" color="blue" style="text-align: center">
					<b><c:out value="${classMessage}" /></b> <b><c:out
						value="${message}" /></b></font>
				</c:if></td>
			</tr>
			<tr id="object-name">
				<td width="12%" nowrap="nowrap"><span class="normal"><spring:message
					code="classname"></spring:message> <font color="red">*</font></span></td>
				<td><span class="normal"><input name="className"
					class="ie6-input-text" type="text" id="className" size="40"
					value="" /></span></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap"><span class="normal"><spring:message
					code="isa"></spring:message></span></td>
				<td><span class="normal"><input name="isA" type="text"
					class="ie6-input-text" id="isA" size="24" /></span></td>
			</tr>
			<tr id="class-row">
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap"><input type="hidden"
					name="method" id="method" size="5" /> <input type="hidden"
					name="allProperties" id="allProperties" size="5" /> <input
					type="hidden" id="subClassId"
					value='<c:out value="${classValue.subClassId}" />' size="5"
					name="subClassId" /> <input type="hidden" id="superClassId"
					value='<c:out value="${classValue.superClassId}" />' size="5"
					name="superClassId" /> <input type="hidden" id="propertyName"
					name="propertyName" /> &nbsp;</td>
				<td><input type="button" class="ie6-button-button"
					id="create-object-btn"
					value="<spring:message code="createClassButton"></spring:message>"
					onclick="showCreatedClassDiv()" /></td>
			</tr>
			<tr>
				<td width="12%" nowrap="nowrap">&nbsp;</td>
				<td id="object-create-link">&nbsp;</td>
			</tr>
		</table>
		<table width="100%" border="0" cellpadding="6" cellspacing="0"
			id="object-created-panel">
			<tr>
				<td colspan="2" nowrap="nowrap"><span class="comment-text"><strong>Newbie
				Help:</strong> add properties to the class by entering them below. Use the
				&quot;+ Add Property&quot; button to add more properties</span></td>
			</tr>
			<tr>
				<td colspan="2"><spring:message code="class"></spring:message><b><c:out
					value="${classValue.subClassName}" /> <input type="hidden"
					id="classNameHidden" name="classNameHidden"
					value='<c:out value="${classValue.subClassName}" />' /> </b></td>
			</tr>
			<tr>
				<td colspan="5"><spring:message code="inherits"></spring:message><b><c:out
					value="${classValue.superClassName}" /></b></td>
			</tr>
			<tr>
				<td colspan="5"><b style="font-size: 0.5cm"><spring:message
					code="inheritsProperties(Predicates)"></spring:message></b></td>
			</tr>
			<tr>
				<td colspan="2"><c:forEach var="predicateValues"
					items="${classValue.superClassPredicates}">
					<font style="background-color: #DCDCDC"><c:out
						value="${predicateValues}" /></font>&nbsp;&nbsp;&nbsp;
								</c:forEach></td>
			</tr>
			<tr id="top_row">
				<td nowrap="nowrap">
					<table>
						<tr>
							<td>				
								<span class="normal">
									<input name="property" type="text" id="property" size="40" class="ie6-input-text" />
								</span>
									 <input type="hidden" value="0"	id="theValue" name="theValue" />
								<div id="myDiv"></div>
								</td>
								<td valign="bottom">
									<input type="button" name="add_propertyNew" id="add_propertyNew" class="ie6-button-button" value="<spring:message code='addNewProperty'></spring:message>" />
									<input type="hidden" size="4" name="addNewPropertyCount" id="addNewPropertyCount" value="1" />
								</td>
							</tr>		
					</table>		
				</td>
			</tr>
			<tr id="class-row-row">
			</tr>
			<tr>
				<td nowrap="nowrap"><input type="button" name="save" id="save"
					value="Save Class" class="ie6-button-button" /></td>
				<td nowrap="nowrap">&nbsp;</td>
			</tr>
			<tr>
				<td nowrap="nowrap">&nbsp;</td>
				<td nowrap="nowrap">&nbsp;</td>
			</tr>
			<tr>
				<td colspan="2"><select style="visibility: hidden;"
					id="superClassPropertyList" name="superClassPropertyList"
					onchange="">
					<option value="">---Select Property---</option>
				</select> <c:forEach var="superPredicateValues"
					items="${classValue.superClassPredicates}">
					<script type="text/javascript">
										createSelectBox('superClassPropertyList','<c:out value="${superPredicateValues}" />');
									</script>
				</c:forEach></td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</form>
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
		<td class="normal-small">Sempedia 2009 | Creative Commons License</td>
	</tr>
</table>
<script type="text/javascript">
function findValue(li) {
//alert("findValue function...");
	if( li == null ) return alert("No match!");

	// if coming from an AJAX call, let's use the CityId as the value
	if( !!li.extra ) var sValue = li.extra[0];

	// otherwise, let's just display the value in the text box
	else var sValue = li.selectValue;
	//alert("The value you selected was: " + sValue);
	
	//var status = confirm("Do you want to edit the Class : " + li);
	//if(status)
	//{
		//alert("yes");
		openEdit(li);
	//}
	//else
	//{
		//alert("no");
	//}
}

function selectItem(li) {
//alert("selectItem function...");
	findValue(li);
}

function formatItem(row) {
//alert("formatItem .....");

	return row[0] + "&nbsp;&nbsp;<font color='red'><-- Edit </font>";
	//return row[0] + " (id: " + row[1] + ")";
	//return row[0];
}

function lookupAjax(){
//alert("lookupAjax....");
	var oSuggest = $("#objectIdForAjax")[0].autocompleter;

	oSuggest.findValue();

	return false;
}

function lookupLocal(){
//alert("lookupLoacl...");
	var oSuggest = $("#CityLocal")[0].autocompleter;

	oSuggest.findValue();

	return false;
}
//gurupavan
function openEdit(li){
	var con;
	con = confirm('Selected class '+li+' already exists do you want to edit ');
	if(con){
		document.getElementById('className').value = li;
		document.getElementById('method').value = 'editClass';
		document.classForm.action="/NewSempedia/createClass.htm?action=editClass";
		document.classForm.submit();
	}else{
		alert('Please give another class name as '+li+' class already exists');
		document.getElementById('className').value='';
	}
}

//gurupavan
$(document).ready(function() {
//alert("ready....");
	
   	$("#className").autocomplete(
		"jsp/getClasses.jsp",
		{
			delay:1,
			minChars:1,
			matchSubset:1,
			matchContains:1,
			cacheLength:10,
			onItemSelect:selectItem,
			onFindValue:findValue,
			formatItem:formatItem,
			autoFill:false,
			maxItemsToShow:10
		}
	);
	
	
	$("#isA").autocomplete("jsp/getClasses.jsp",
		{
		 	delay:1,
			minChars:1,
			matchSubset:1,
			matchContains:1,
			cacheLength:10,
			autoFill:true,
			maxItemsToShow:10
		}
	);
	

});
</script>

</body>
</html>
