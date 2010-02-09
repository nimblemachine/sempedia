<%@ taglib prefix="core" uri="/WEB-INF/taglibs/c.tld" %>
<%@ taglib prefix="spring" uri="/spring" %>
<%@ taglib uri="http://displaytag.sourceforge.net/" prefix="display" %>
<%@ page import="com.beans.ObjectDetailsBean;" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<!-- <title>Sempedia | Making computers think about data the way we do</title> -->
<title>THIS IS CREATE OBJECT .....</title>
<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<style type="text/css">
<!--
body {
	margin-top: 0px;
}
-->
</style>
<script type="text/javascript">
function goToImagePage(objectName){
document.editObjectDescription.objectName.value = objectName;
document.editObjectDescription.submit();
}
</script>
<script type="text/javascript" language="javascript">
$(document).ready(function() {
			$(".top-button").hover(
        	function() { $(this).addClass("top-button-active"); },
        	function() { $(this).removeClass("top-button-active"); }
    		);
			
				$("#cancel-add").click(function(){
                    $("#add-tag").toggle("fast");
            });
			
			$("#add-tag").hide();						
			$("#add-tag-btn").click(
        		function() { $("#add-tag").toggle("fast"); }
			);
			
			$("#attach-class").click(
        		function() { 
					$("#class-row").prepend("<td nowrap='nowrap'>&nbsp;</td><td><span class='normal'><input name='class-type2' type='text' class='input-field' id='class-type2' size='24' /></span></td>"); }
			);
			  
			$("#newPropertySaving").click(
				function(){
        			//alert("this is frm jquery");
        			saveNewProperty();
        			}
			);
			
			$("#save").click(
				function(){
					var finalPropertyName;
					var propertyArray = new Array();
					var predicatesCountValue = document.getElementById('propertiesCountHidden').value;
					var len = document.getElementsByName('propertyHidden').length;
					for(var count = 0; count<len; count++){
						propertyArray[count] =  document.getElementsByName('propertyHidden')[count].value;
							if(document.getElementById(propertyArray[count]) != null){
								finalPropertyName = document.getElementById(propertyArray[count]).value;
								finalPropertyName = finalPropertyName.replace(/(\s+)+/g," ");
								finalPropertyName = finalPropertyName.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
								if(finalPropertyName != ''){
									document.getElementById(propertyArray[count]).value = finalPropertyName;
								}else{
									alert('Please enter properties values');
									return false;
								}
							}
					}	
						var defaultPropertiesValue;
						for(var countValue = 1; countValue < predicatesCountValue; countValue++){
								if(document.getElementById('predicateIdForAjax'+countValue) != null){
									defaultPropertiesValue = document.getElementById('predicateIdForAjax'+countValue).value;
									defaultPropertiesValue = defaultPropertiesValue.replace(/(\s+)+/g," ");
									defaultPropertiesValue = defaultPropertiesValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
									if(defaultPropertiesValue != ''){
										document.getElementById('predicateIdForAjax'+countValue).value = defaultPropertiesValue;
									}else{
										alert('Please enter properties values');
										return false;
									}
								 }
						}
						document.predicateForm.action = "addObject.htm?action=updateObjectDetails";
						document.predicateForm.submit();
				}
			);	
	});

function addPropertyTextBox(divName,divId)
{
	
	//alert(divName);
	var i=1;
	var commingDiv = divName+i;
	var commingDivId = divId+i;
	//alert("modified : "+commingDiv);
	while(document.getElementById(commingDiv) != null) 
	{
		//alert("modified : "+commingDiv);
		var div = document.getElementById(commingDiv);
		i++;
		commingDiv = divName+i;
		commingDivId = divId+i;
	}
	//alert("adding div is : "+i+commingDiv);
	
	var newdiv = document.createElement(commingDiv);
	newdiv.setAttribute("id",commingDiv);
	newdiv.innerHTML = "<input type=\'text\' id=\'"+commingDivId+"\' name=\'property\' size=\'40\'>&nbsp;&nbsp;";
	var parentDiv = document.getElementById(divName);
	parentDiv.appendChild(newdiv);
}
function removeEvent(id){
	//alert("hi.........");
		var removingId = document.getElementById(id);
		//alert("hi...... "+removingId);
		
		removingId.remove();
}
function editObjectPage()
{
	//alert("-->"+document.getElementById('EditObjectPage').style.display);
	if(document.getElementById('EditObjectPage').style.display == 'none')
	{
		document.getElementById('EditObjectPage').style.display = '';
	}
	else
	{
		document.getElementById('EditObjectPage').style.display = 'none';
	}
}
function addProperty()
{
	//alert("hi");
	if(document.getElementById('AddPropertyPage').style.display == 'none')
	{
		document.getElementById('AddPropertyPage').style.display = '';
	}
	else
	{
		document.getElementById('AddPropertyPage').style.display = 'none';
	}
}
function saveNewProperty()
{
	//alert("hureeeee");
	var newpredicate = document.predicateForm.newPropertyAddingToSuperClass.value;
	var newPredicateValue = document.predicateForm.newPropertyAddingToSuperClassValue.value;
	var objectName = document.predicateForm.objectName.value;
	var isoftype = document.predicateForm.isOfType.value;
	newpredicate = newpredicate.replace(/(\s+)+/g," ");
	newpredicate = newpredicate.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	newPredicateValue = newPredicateValue.replace(/(\s+)+/g," ");
	newPredicateValue = newPredicateValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	objectName = objectName.replace(/(\s+)+/g," ");
	objectName = objectName.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	isoftype = isoftype.replace(/(\s+)+/g," ");
	isoftype = isoftype.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	
	if(newpredicate != ''){
		if(newPredicateValue != ''){
			document.saveNewPropertyForm.newPredicate.value = newpredicate;
			document.saveNewPropertyForm.newPredicateValue.value = newPredicateValue;
			document.saveNewPropertyForm.objectName.value = objectName;
			document.saveNewPropertyForm.isOfType.value = isoftype;
			if(isoftype == ''){
				alert("Class does't exists for '"+objectName+"', you can't add property and it's value");
				return false;
			}else{
				document.saveNewPropertyForm.submit();
			}
		}else{
			alert('Please enter value details');
			return false;
		}	
	}else{
		alert('Please enter property details'); 
		return false;
	}	
}
function doObjectValidation(){
	var objectName = document.getElementById('objectIdForAjax').value;
	var isAName = document.getElementById('classIdForAjax').value;
	objectName = objectName.replace(/(\s+)+/g," ");
	objectName = objectName.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,'');
	isAName = isAName.replace(/(\s+)+/g," ");
	isAName = isAName.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,'');
	if(objectName == '')
	{
		alert("Please enter Object Name");
		return false;
	}
	else if(isAName == '')
	{
		alert("Please enter Is of type(class) for Object");
		return false;
	}else{
			document.objectSubmitionForm.objectName.value = objectName;
			document.objectSubmitionForm.isOfType.value = isAName;
		}
}
function saveObjectValidation(){
	//alert('hihihihihi');
	return false;
}
function editObject(objectName)
{
	//alert("hi how r u"+objectName);
	document.editObjectForm.objectName.value = objectName;
	document.editObjectForm.submit();
}
var counting = 0;
var toDiv;
var removeDiv;
var addButton;
var removeButton;
var textFieldCounter = 0;
var textFieldId;
var newTextBoxName;

function assignNewTextBoxName(assignName)
{
	//alert("assigning text Box name");
	newTextBoxName = assignName;
	//alert("assigned name is : "+newTextBoxName);
}
</script>
</head>
<body > 
<form name="editObjectDescription" method="post" action="addObject.htm?action=readDescription">
	<input type="hidden" name="objectName">
</form>
<form name="editObjectForm" method="post" action="addObject.htm?action=editObject">
	<input type="hidden" name="objectName">
</form>
<form name="saveNewPropertyForm" method="post" action="addObject.htm?action=saveNewProperty">
	<input type="hidden" name="objectName">
	<input type="hidden" name="isOfType">
	<input type="hidden" name="newPredicate">
	<input type="hidden" name="newPredicateValue">
	
</form>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <%@ include file="/jsp/sempediaHeader.jsp" %>
</table>
<hr width="80%" size="1" />

<table width="100%" border="0" align="center" height="10">
	<tr>
		<td align="center"><font color="red"><b><core:out value="${exceptionMessage}"/> </b></font></td>
	</tr>
</table>

<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td>
    	<form method="post" name="objectSubmitionForm" action="addObject.htm?action=addObject" onsubmit="return doObjectValidation()">
        <table width="100%" border="0" cellpadding="6" cellspacing="0" id="new-object-panel">
		          <tr>
		            <td colspan="2" nowrap="nowrap" class="header3" id="object-details">Details of New Object</td>
		          </tr>
		          <tr>
		            <td width="8%" nowrap="nowrap"><img src="images/1px-transparent.gif" alt="d" width="1" height="1" /></td>
		            <td><img src="images/1px-transparent.gif" alt="d" width="1" height="1" /></td>
		          </tr>
		          <tr>
						<td colspan="2" nowrap="nowrap" align="center"><font color="<core:out value="${color}"/>"><b><core:out value="${message}"/> </b></font></td>
					</tr>
		          <tr id="object-name">
		            <td width="8%" nowrap="nowrap"><span class="normal">Object Name :</span></td>
		            <td><span class="normal">
		            	<input type="text" class="ie6-input-text" name="objectName" size="40" class="input-field" id="objectIdForAjax"/>
		            </span></td>
		          </tr>
		          <tr >
		            <td colspan="2"><span class="comment-text"><strong>Newbie Help:</strong> If an appropriate class doesn't exist, write it's name here. It will then be created for you when you create the object. You can later navigate to the page you created for the class and add properties to it.</span></td>
		          </tr>
		          <tr >
		            <td width="8%" nowrap="nowrap"><span class="normal">Is of type (class) :</span></td>
		            <td><span class="normal">
		              <input type="text" class="ie6-input-text" name="isOfType" size="40" class="input-field"  id="classIdForAjax"/>
		            <input type="button" value="+ Attach another class" class="ie6-button-button"/>
		            </span><span class="class-def"><a href="add-class.html"></a></span></td>
		          </tr>
		          <tr id="class-row"></tr>
		          <tr>
		            <td width="8%" nowrap="nowrap">&nbsp;</td>
		            <td id="object-create-link"><label>
		              <input type="submit" class="ie6-submit-button" name="create-object-btn" id="create-object-btn" value="Create Object" class="ie6button"/>
		            </label></td>
		          </tr>
		         
		          <tr>
		            <td width="8%" nowrap="nowrap">&nbsp;</td>
		            <td>&nbsp;</td>
		          </tr>
        </table>
        </form>
       <form method="post" name="predicateForm" action="addObject.htm?action=updateObjectDetails" > 
        	<table width="100%" border="0" cellpadding="0" cellspacing="0" id="object-created-panel">
        	<core:forEach var="objectsList" items="${objectsList}">
          		<tr id="object-name2">
            		<td colspan="3" nowrap="nowrap"><span class="normal">Your object named: <strong><core:out value="${objectsList.key}"/></strong> has been created</span></td>
          		</tr>
          		<tr>
            		<td colspan="3" nowrap="nowrap"><span class="normal">New Object is of type (class)<strong><core:out value="${objectsList.value}"/></strong></span></td>
         	 	</tr>
          		<tr>
            		<td colspan="3"><p>
		              <span class="comment-text"><strong>Newbie Help:</strong> To edit the object page, once you have finished adding properties, you should visit the object's page and edit the descriptive content.</span></p>
		              <p>
		              <input type="button" name="add_property" id="add-tag-btn" value="+ Add Semantic Tag" class="ie6-button-button"/>
		              &nbsp;&nbsp;&nbsp;&nbsp;</p>
		              <table border="0" cellspacing="0" id="add-tag">
			                <tr>
				                  <td valign="top" nowrap="nowrap" ><span class="normal-small">Property</span></td>
				                  <td valign="top" nowrap="nowrap" >&nbsp;</td>
				                  <td valign="top" nowrap="nowrap" ><span class="normal-small">Value</span></td>
			                </tr>
			                <tr>
								<td colspan="3" nowrap="nowrap">
									<input type="hidden" name="objectName" value='<core:out value="${objectsList.key}"/>' /> 
									<input type="hidden" name="isOfType" value='<core:out value="${objectsList.value}"/>'/>
								</td>
							</tr>
			                <tr id="add-property">
				                  <td valign="top" nowrap="nowrap" >
				                  	<input type="text" class="ie6-input-text" name="newPropertyAddingToSuperClass" id="newPropertyForAjax" class="normal"/>
				                    <br />
				                  </td>
				                  <td valign="top" nowrap="nowrap" >&nbsp;</td>
				                  <td valign="top" nowrap="nowrap" ><span class="normal-small">
				                    <input type="text" class="ie6-input-text" name="newPropertyAddingToSuperClassValue" id="newPropertyForAjaxValue" class="normal"/>
				                    </span>
				                    <input type="button" name="saveNewProperty" id="newPropertySaving" value="Save" class="ie6-button-button" />
				                    <input type="button" name="cancel-add" id="cancel-add" value="Cancel" class="ie6-button-button"/>
				                  </td>
			                </tr>
			                <tr>
								<td colspan="3">
									<font color="red">Note : New Property will add to super class : <core:out value="${objectsList.value}"/></font>
								</td>
							</tr>
		            </table>
		           </td>
		      	</tr>
		      	<tr>
		            <td><input type="button" value="Edit Object Page" class="ie6-button-button" onclick="goToImagePage('<core:out value='${objectsList.key}'/>')"/></td>
		        </tr>
		      	<tr>
					<td colspan="3" nowrap="nowrap">
							<span class="normal"><strong>Inherits properties (predicates)</strong></span>
					</td>
				</tr>
			</core:forEach>
			
			<tr>
				<td colspan="3">
				<core:set var="propertiesCount" value="${1}"/>
				<core:forEach var="predicatesList" items="${predicatesList}">
						<font style="background-color:#DCDCDC">&nbsp;<core:out value="${predicatesList.key}"/>&nbsp;</font>&nbsp;
							<core:set var="propertiesCount" value="${propertiesCount+1}"/>
				</core:forEach>
				<input type="hidden" id="propertiesCountHidden" name="propertiesCountHidden" value='<core:out value="${propertiesCount}"/>'/>
				</td>
			</tr>
				<core:set var="count" value="0"/>
				<core:set var="countAlreadyBoxes" value="0"/>
				<core:forEach var="predicatesList" items="${predicatesList}">
				<core:set var="count" value="${count + 1}"/>
					<tr id="top_row" valign="top" align="left" >
						<td align="left" valign="top" width="20%">
							<span class="normal"><core:out value="${predicatesList.key}"/> 
							</span>
						</td>
						<td align="left" width="2%">
							<img src="images/right.jpg" width="16" height="10" alt="k" />
						</td>
						<td align="left" width="53%" >
							<table >
									<tr>
										<td valign="bottom">
											<core:if test="${predicatesList.value ==null }">
												<label>
												<input type="text" size="40" class="input-field" name="<core:out value="${predicatesList.key}"/>" id="predicateIdForAjax<core:out value="${count}"/>" />
												<input type="button" name="removeValue" class="ie6-button-button"id="predicateIdForAjax<core:out value="${count}"/>r" value="Remove" onClick="removeTextAndRemForEmpty('predicateIdForAjax<core:out value="${count}"/>','predicateIdForAjax<core:out value="${count}"/>r','extraTextBoxForProperty<core:out value="${count}"/>');"/>
											</core:if>
											<core:forEach var="predicateValueArray" items="${predicatesList.value}" >
											<core:set var="countAlreadyBoxes" value="${countAlreadyBoxes + 1}"/>
												<span class="normal">
													<input type="text" size="40" class="input-field" name="<core:out value="${predicateValueArray.tripleId}"/>" id="predicateIdForAjax<core:out value="${countAlreadyBoxes}"/>" value="<core:out value="${predicateValueArray.predicateValue}"/>"/>
													<input type="button" class="ie6-button-button" name="removeValue" id="predicateIdForAjax<core:out value="${countAlreadyBoxes}"/>r" value="Remove" onClick="myRemoveForAlreadyData('predicateIdForAjax<core:out value="${countAlreadyBoxes}"/>','predicateIdForAjax<core:out value="${countAlreadyBoxes}"/>r','extraTextBoxForProperty<core:out value="${count}"/>','<core:out value="${predicateValueArray.tripleId}"/>');"/>
												</span>
											</core:forEach>
											<div style="width:390px;" id="extraTextBoxForProperty<core:out value="${count}"/>">
											</div>
										</td>
										<td align="center" valign="bottom">
											<input type="button" class="ie6-button-button" name="addMoreValues" id="newTextBox<core:out value="${count}"/>" value="Add More Values" onclick="assignNewTextBoxName('<core:out value="${predicatesList.key}"/>');"/>
											<div style="width:1px;" id="extraTextBoxForProperty<core:out value="${count}"/><core:out value="${count}"/>">
											</div>
										</td>
									</tr>
							</table>
						</td>
					</tr>
					<tr height="6">	
						<td height="6" colspan="3"></td>
					</tr>
					<script type="text/javascript">
									$(document).ready(function() {
										//alert("ready2");
										counting++;
										toDiv = 'extraTextBoxForProperty'+counting;
										removeDiv = 'extraTextBoxForProperty'+counting+counting;
										var toDivTemp = document.getElementById(toDiv);
										var removeDivTemp = document.getElementById(removeDiv);
										addButton = 'newTextBox'+counting;
										var addButtonTemp = document.getElementById(addButton);
									
										var addRemoveTemp = document.getElementById(removeButton);
											$(addButtonTemp).click(
										        		function() { 
										        			//alert("this is adding... new textBox:"+newTextBoxName +","+counting++);
										        			textFieldCounter++;
									        				textFieldId = 'extraTextBoxForProperty'+counting+textFieldCounter;
															removeButton = 'removeTextBox'+counting+textFieldCounter;
															$(toDivTemp).prepend("<span class='normal'><input name='propertyHidden' type='hidden' size='5' id='propertyHidden' value='"+textFieldId+"' /><input name='"+newTextBoxName+"' type='text' class='input-field' id='"+textFieldId+"' size='40' /> </span><input type='button' class='ie6-button-button' name='removeValue' id='"+removeButton+"' value='Remove' />");
															 
															if(removeDivTemp != null)
															{
																$(removeDivTemp).remove();
															}
															
															myFunctionForAutoComplete(textFieldId,removeButton);
															}
													);																																																																																													 
											
											
										});
								</script>
				</core:forEach>
				
				<core:if test="${predicatesList != null }" >
					<tr>
						<td colspan="3" align="center" nowrap="nowrap">
						<label><input type="button" class="ie6-submit-button" name="save" id="save" value="Save Object Details"/></label>
						</td>
					</tr>
					
				</core:if>
				</table>
		</form>
	</td>
</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#4C88BE"><img src="images/1px-transparent.gif" width="1" height="1" alt="1px" /></td>
  </tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td class="normal-small">&copy; Sempedia 2009 | All rights reserved</td>
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

	var status = confirm("Do you want to edit the Object : " + li);
	if(status)
	{
		//alert("yes");
		editObject(li);
	}
	else
	{
		//alert("no");
	}
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
	var oSuggest = $("#classIdForAjax")[0].autocompleter;

	oSuggest.findValue();

	return false;
}

$(document).ready(function() {
//alert("ready....");
	$("#objectIdForAjax").autocomplete(
		"jsp/getObjects.jsp",
		{
			delay:1,
			minChars:1,
			matchSubset:1,
			matchContains:1,
			cacheLength:10,
			onItemSelect:selectItem,
			onFindValue:findValue,
			formatItem:formatItem,
			autoFill:true,
			maxItemsToShow:10
		}
	);
	$("#classIdForAjax").autocomplete(
		"jsp/getClasses.jsp",
		{
			delay:1,
			minChars:1,
			matchSubset:1,
			matchContains:1,
			cacheLength:10,
			onFindValue:findValue,
			autoFill:true,
			maxItemsToShow:10
		}
	);

	var form = document.predicateForm;
	for (var i=0; i<form.elements.length; i++) 
	{
		//alert("id : "+form.elements[i].id); 
		var fixedId = form.elements[i].id;
		//fixedId = "#"+fixedId;
		
		if(form.elements[i].type == "text")
		{
		if(fixedId.charAt(0) == 'p')
		{
		//alert(fixedId);
	$("#"+fixedId).autocomplete(
		"jsp/getObjects.jsp",
		{
			delay:1,
			minChars:1,
			matchSubset:1,
			matchContains:1,
			cacheLength:10,
			onFindValue:findValue,
			autoFill:true,
			maxItemsToShow:10
		}
	);
		}
		}
	}

});

function removeTextAndRemForEmpty(textFieldId,removeButtonId,divId,newTextBoxName)
{


$("#"+textFieldId).fadeOut(300, function() 
						{$("#"+textFieldId).remove(); 
						}
						);
$("#"+removeButtonId).fadeOut(300, function() 
						{$("#"+removeButtonId).remove(); 
						}
						);

}

function myRemoveForAlreadyData(textFieldId,removeButtonId,divId,newTextBoxName)
{
//alert("yeah removing");
//alert("the name of the textbox is : "+newTextBoxName);


$("#"+textFieldId).fadeOut(300, function() 
						{$("#"+textFieldId).remove(); 
						}
						);
$("#"+removeButtonId).fadeOut(300, function() 
						{$("#"+removeButtonId).remove(); 
						}
						);
$("#"+divId).prepend("<input name='"+newTextBoxName+"' type='hidden' size='40' /> ");

//$('.display_reply').click(function(){ // if a button is clicked
                // empty a text area & all text boxes
    //});
	
}

function myFunctionForAutoComplete(textFieldId,removeButton)
{
$(document).ready(function() {
//alert("this is my function");
var form = document.predicateForm;
	for (var i=0; i<form.elements.length; i++) 
	{
		//alert("id : "+form.elements[i].id); 
		var fixedId = form.elements[i].id;
		//fixedId = "#"+fixedId;
		
		if(form.elements[i].type == "text")
		{
			//alert("text Box :"+form.elements[i].id);
			if(fixedId.charAt(0) == 'e')
			{
			 
				$("#"+fixedId).autocomplete(
					"jsp/getObjects.jsp",
					{
						delay:1,
						minChars:1,
						matchSubset:1,
						matchContains:1,
						cacheLength:10,
						onFindValue:findValue,
						autoFill:false,
						maxItemsToShow:10
						
					}
				);
			}
		}
		if(form.elements[i].type == "button")
		{
			if(fixedId.charAt(0) == 'r')
			{
				$("#"+removeButton).click(function()
					{$("#"+textFieldId).fadeOut(300, function() 
						{$("#"+textFieldId).remove(); 
						}
						);
					 $("#"+removeButton).fadeOut(300, function()
					 	{
					 		$("#"+removeButton).remove(); 
					 	}
					 	);
					});
			}
		}
	}//for
	
});
}

</script>

</body>
</html>
			