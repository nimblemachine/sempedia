<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="/WEB-INF/taglibs/c.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sempedia | Making computers think about data the way we do-Object Description</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery.jeditable.mini.js"></script>
<script type="text/javascript" language="javascript">
$(document).ready(function() {

		 var propertySpanID ;
            	$(".propertySuperId").click(function(){
                    propertySpanID = $(this).attr('id');
                    //alert('proerty id '+propertySpanID);
               	});
		
			$(".top-button").hover(
        	function() { $(this).addClass("top-button-active"); },
        	function() { $(this).removeClass("top-button-active"); }
    		);			
			$("#add-tag").hide();						
			$("#add-tag-btn").click(
        		function() { $("#add-tag").toggle("fast"); }
			);
			$('#object-name').editable(
					function(value,settings){	
						value = value.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
						if(value.length != 0){
							document.updateSubject.objectName.value = value;
							document.updateSubject.submit();
						}
						else{
							alert("Invalid edit, please enter a value.");
						}
					
					}
					, {
         			cancel    : 'Cancel',
         			submit    : 'OK',
         			indicator : '<img src="img/indicator.gif">',
         			tooltip   : 'Click to edit...'
			});
				
			$("#cancel-add").click(function () {
				$("#add-tag").toggle("");
			});
			
			$("#attach-class").click(
        		function() { 
					$("#class-row").prepend("<td nowrap='nowrap'>&nbsp;</td><td><span class='normal'><input name='class-type2' type='text' class='input-field' id='class-type2' size='24' /></span></td>"); }
			);
			  
			$("#newPropertySaving").click(
				function(){
        			saveNewProperty();
        			}
			);
			
			var predicateCount = document.getElementById("predicateCount").value;
			var num =0;	
				
			for(num ; num<predicateCount; num++){
				var temp = 0;	
				var prValueCount = document.getElementById("predicateValueCount"+num).value;
				for(temp; temp<prValueCount;temp++){
			 		$('#predicate'+temp+'Value'+num).editable(
						function(value,settings){
						value = value.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
							if(value.length != 0){
								document.updatePredicateValue.predicateValue.value = value;
								document.updatePredicateValue.tripleId.value = document.getElementById("triple"+propertySpanID).value;
								document.updatePredicateValue.submit();
							}
							else{
								alert("Invalid edit, please enter a value");
								return false;
							}
			 			},{
		         			cancel    : 'Cancel',
		         			submit    : 'OK',
		         			tooltip   : 'Click to edit...'
						});
					}
			}
	});				   
</script>

<script type="text/javascript" language="javascript">
function addPropertyTextBox(divName,divId)
{
	var i=1;
	var commingDiv = divName+i;
	var commingDivId = divId+i;
	while(document.getElementById(commingDiv) != null) 
	{
		var div = document.getElementById(commingDiv);
		i++;
		commingDiv = divName+i;
		commingDivId = divId+i;
	}
	
	var newdiv = document.createElement(commingDiv);
	newdiv.setAttribute("id",commingDiv);
	newdiv.innerHTML = "<input type=\'text\' id=\'"+commingDivId+"\' name=\'property\' size=\'40\'>&nbsp;&nbsp;";
	var parentDiv = document.getElementById(divName);
	parentDiv.appendChild(newdiv);
}
function removeEvent(id){
		var removingId = document.getElementById(id);	
		removingId.remove();
}
function editObjectPage()
{
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
	var newProperty = document.predicateForm.newPropertyAddingToSuperClass.value;
	var newValue = document.predicateForm.newPropertyAddingToSuperClassValue.value;
	
	newProperty = newProperty.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	newValue = newValue.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');
	
	if(newProperty.length != 0 && newValue.length != 0 ){
		document.saveNewPropertyForm.newPredicate.value = newProperty;
		document.saveNewPropertyForm.newPredicateValue.value = newValue;
		document.saveNewPropertyForm.objectName.value = document.predicateForm.objectName.value;
		document.saveNewPropertyForm.isOfType.value = document.predicateForm.isOfType.value;
		if(document.predicateForm.isOfType.value == ''){
			alert("Class does't exists for '"+document.predicateForm.objectName.value+"', you can't add Property and it's Value");
			return false;
		}else{
			document.saveNewPropertyForm.submit();
		}
	}
	else if(newProperty.length == 0){
		alert("Please enter Property name");
		return false;
	}
	else if(newValue.length == 0){
		alert("Please enter Property value");
		return false;
	}
	else{
		alert("Please enter Property details");
		return false;
	}
}
function editObject(objectName)
{
	document.editObjectForm.objectName.value = objectName;
	document.editObjectForm.submit();
}
var counting = 0;
var toDiv;
var addButton;
var removeButton;
var textFieldCounter = 0;
var textFieldId;
var newTextBoxName;
function assignNewTextBoxName(assignName)
{
	newTextBoxName = assignName;
}
</script>
<script type="text/javascript">
function goToImagePage(objectName){
	document.editObjectDescription.objectName.value = objectName;
	document.editObjectDescription.submit();
}
function goToEditClassPage(className){
document.editClassForm.className.value = className;
document.editClassForm.submit();

}
</script>
</head>

<body>
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
<%@ include file="/jsp/sempediaHeader.jsp" %>
  
</table> 
<form method="post" name="updateSubject" action="addObject.htm?action=updateSubjectName"> 
	<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>	
	 <input type="hidden" name="hiddenObjectName" value="<c:out value="${objectNameFromObjectPage}" />"/> 
	<input type="hidden" name="objectName" />
</form>

<form method="post" name="updatePredicateValue" action="addObject.htm?action=saveTripleValues"> 
	<input type="hidden" name="tripleId" />
	<input type="hidden" name="predicateValue" />
	<input type="hidden" name="objectName" value="<c:out value='${objectNameFromObjectPage}'/>"/>
	<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>	
</form>

<form name="editClassForm" method="post" action="createClass.htm?action=editClass">
	<input type="hidden" name="className" />
</form>
<form name="editObjectDescription" method="post" action="addObject.htm?action=readDescription">
	<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>	
	<input type="hidden" name="objectName" />
</form>
<form name="editObjectForm" method="post" action="addObject.htm?action=editObject">
	<input type="hidden" name="objectName" />
</form>
<form name="saveNewPropertyForm" method="post" action="addObject.htm?action=saveNewProperty">
	<input type="hidden" name="objectName" />
	<input type="hidden" name="isOfType" />
	<input type="hidden" name="newPredicate" />
	<input type="hidden" name="newPredicateValue" />	
</form>
 <form id="form5" name="objectForm" enctype="multipart/form-data" method="post" action="addObject.htm?action=saveCroppedImage">

<hr width="80%" size="1" />
<table width="100%" border="0" align="center">
	<tr>
		<td align="center"><font color="red"><b><c:out value="${exceptionMessage}"/> </b></font></td>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
   <td width="40%" height="100%" valign="top"><strong>
     <label><span class="subject-link"></span></label>
   </strong>
   <strong>
  	 <span class="Header2-black" id="object-name">
	   	 <c:out value='${objectNameFromObjectPage}'/>
	 </span>
   </strong>
   <span class="normal-small">is a</span>
   <strong>
  	<span id="class-name">
   		<a href="#" class="small-link" onclick="goToEditClassPage('<c:out value='${superClass}'/>')"><c:out value="${superClass}"/></a>
   	</span>
   </strong>
  </td>
  </tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0" id="object-panel">
  <tr>
    <td valign="top" width="25%"><p class="normal-small"><img src="addObject.htm?action=readImage&objectName=<c:out value='${objectNameFromObjectPage}'/>" width="200" height="300" alt="Insert Image" id="userImage"/></p>
     
        <label> 
        <a href="#" onclick="goToImagePage('<c:out value='${objectNameFromObjectPage}'/>')"><spring:message code="editimage"/></a>        
          <input type="hidden" name="objectDescriptionId" value="<c:out value="${objectDescriptionId}" />"/>    
           <input type="hidden" name="hiddenObjectName" value="<c:out value="${objectNameFromObjectPage}" />"/>           
        </label>

    <p class="normal-small">&nbsp;</p></td>
    <td colspan="2" valign="top" width="75%">
      <label>
        <c:out value="${objectDescription}" escapeXml="false"/>
      </label>
    <p>&nbsp;</p></td>
  </tr>
</table>
</form>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td><span class="Header2-black"><spring:message code="semanticbrowse"/> <span class="normal"><spring:message code="for"/>  &quot; <c:out value='${objectNameFromObjectPage}'/>&quot;&nbsp;
          <input type="button" id="add-tag-btn" value="+ Add a Semantic Tag" class="ie6-button-button"/>
    </span></span></td>
  </tr>
</table>
 
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">

 	<tr>
    <td width="40%" height="100%" colspan="2" valign="top">
    <form method="post" name="predicateForm" action="addObject.htm?action=updateObjectDetails"> 
    	<table border="0" cellspacing="0" cellpadding="6" id="add-tag"> 
    	<tr>
    		<td colspan="2">
    			<input type="hidden" name="objectName" value='<c:out value="${objectNameFromObjectPage}" />' /> 
				<input type="hidden" name="isOfType" value='<c:out value="${superClass}"/>'/>
								
    		</td>
    	</tr>    
 		<tr>
			<td valign="top" nowrap="nowrap" ><span class="normal-small"><spring:message code="property"/></span></td>
       		<td valign="top" nowrap="nowrap" ><span class="normal-small"><spring:message code="value"/></span></td>
		</tr>
    	<tr id="add-property">
            <td valign="top" nowrap="nowrap" >
            	<input type="text" name="newPropertyAddingToSuperClass" id="newPropertyForAjax" class="ie6-input-text"/>           
            </td>
            <td valign="top" nowrap="nowrap" ><span class="normal-small">
              <input type="text" name="newPropertyAddingToSuperClassValue" id="newPropertyForAjaxValue" class="ie6-input-text"/>
              </span>
              <input type="button" name="saveNewProperty" id="newPropertySaving" value="Save" class="ie6-submit-button"/>
              <input type="button" name="cancel-add" id="cancel-add" value="Cancel" class="ie6-submit-button"/>
             </td>
    	</tr>
    </table>
    </form>
    </td>
  </tr> 
  
  <tr>
  	<td colspan="3">
  	
  		 <table border="0" cellspacing="2" cellpadding="4" id="tags">
				        <tr>
				          <td nowrap="nowrap"><span class="normal"><strong><u><spring:message code="property"/></u></strong></span></td>
				          <td nowrap="nowrap" class="normal"></td>
				          <td nowrap="nowrap"><span class="normal"><strong><u><spring:message code="value"/></u></strong></span></td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				        </tr>
				        
        <c:set var="num" value="0"/>
	 	<c:forEach var="predicatesList" items="${predicatesList}">
		<c:set var="count" value="0"/>
			
			<c:forEach var="predicateValueArray" items="${predicatesList.value}" >
						<tr>
							<c:if test="${count == 0}">
							          <td nowrap="nowrap" class="predicate" id="predicate<c:out value='${num}'/>" ><span class="normal"><c:out value="${predicatesList.key}"/></span></td>
							          <td nowrap="nowrap" class="normal"><img src="images/right.jpg" width="16" height="10" alt="k" /></td>
				          	</c:if>
				          	<c:if test="${count != 0}">
							          <td nowrap="nowrap">&nbsp;</td>
			          				  <td nowrap="nowrap" class="normal">&nbsp;</td>
          					</c:if>							
				          <td nowrap="nowrap">
				          <span class="propertySuperId" id="predicate<c:out value='${count}'/>Value<c:out value='${num}'/>">
				          	<c:out value="${predicateValueArray.predicateValue}"/>
				          </span>
				          <input type="hidden" value='<c:out value="${predicateValueArray.tripleId}"/>' id="triplepredicate<c:out value='${count}'/>Value<c:out value='${num}'/>" name="trippleId" />	
				          </td>
				          <td nowrap="nowrap"  class="normal">
				          		&nbsp;
				          </td>
				          
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				        </tr>
				 <c:set var="count" value="${count + 1}"/>
			</c:forEach>
			<input type="hidden" id="predicateValueCount<c:out value='${num}'/>" value="<c:out value='${count}'/>"/>
			 <c:set var="num" value="${num + 1}"/>		
		</c:forEach>			
      </table> 
      
      <input type="hidden" value='<c:out value="${objectNameFromObjectPage}" />' id="objectName" name="subject" />
      <input type="hidden" name="predicateCount" id="predicateCount"  value="<c:out value='${num}'/>"/>
    	
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
    <td class="normal-small"> <spring:message code="copyright"> </spring:message> </td>
  </tr>
</table>
<p>&nbsp;</p>
<script type="text/javascript">
function findValue(li) {
//alert("findValue function...");
	if( li == null ) return alert("No match!");

	// if coming from an AJAX call, let's use the CityId as the value
	if( !!li.extra ) var sValue = li.extra[0];

	// otherwise, let's just display the value in the text box
	else var sValue = li.selectValue;

	var status = confirm("Do you want to edit the Object : " + sValue);
	if(status)
	{
		//alert("yes");
		editObject(sValue);
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
			}// if for charAt == 'p'
		}//if for text box
		
	}
});
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

--></script>
</body>
</html>