<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ taglib prefix="c" uri="/WEB-INF/taglibs/c.tld"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Sempedia | Making computers think about data the way we do-Edit Object Description</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />

<script type="text/javascript" src="js/jquery-1.3.2.js"></script>
<script type="text/javascript" src="js/jquery.autocomplete.js"></script>
<script type="text/javascript" src="js/jquery.jeditable.mini.js"></script>
<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />
<script language="JavaScript" type="text/javascript" src="js/wyzz.js"></script>
<script type="text/javascript">
$(document).ready(function() {
			$(".top-button").hover(
        	function() { $(this).addClass("top-button-active"); },
        	function() { $(this).removeClass("top-button-active"); }
    		);
			
			$("#add-tag").hide();						
			$("#AddSemanticTagId").click(
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
	});

$(document).ready(function() {
			$("#leo-smart").hover(
        	function() { $("#007").prepend("<img src='images/hand.gif' width='22' height='27' id='just-added' />"); },
        	function() { $("#just-added").remove(); }
    		);
			
			$("#hand-arrow").hide();
			$("#leo-smart-name").hover(
        	function() { $("#hand-arrow").show(); },
        	function() { $("#hand-arrow").hide(); }
    		);
			$("#leo-smart-arrow").hover(
        	function() { $("#hand-arrow").show(); },
        	function() { $("#hand-arrow").hide(); }
    		);
			
			
			
			$("#leo-smart-arrow").hover(
        	function() { $("#hand").show(); },
        	function() { $("#hand").hide(); }
    		);
			
			$("#options").hide();
			$("#leo-smart-name").click(
        	function() { $("#options").toggle(""); } 
			);
			
			$(".predicate").hover(
        	function() { $(this).addClass("predicate-over"); },
        	function() { $(this).removeClass("predicate-over"); }
    		);
			
			$(".tag-show").hover(
        	function() { $(this).addClass("tag-show-over"); },
        	function() { $(this).removeClass("tag-show-over"); }
    		);
			
			$("#hand-arrow2").hide();
			$("#is-a").click(
        	function() { $("#hand-arrow2").toggle(""); } 
			);
			
			$("#tags").hide();
			$("#show-tags").click(
        	function() { $("#tags").toggle("slow"); } 
			);
			

			$("#options2").hide();
				$("#is-a").click(
				function() { $("#options2").toggle(""); }
				);
				
		$("#is-a-another").hide();
			$("#is-a").click(
				function() { $("#is-a-another").toggle(""); }
				);
			$("#is-a").click(
				function() { $("#options").toggle(""); }
				);
			
			$("#add-tag1, #add-tag2, #add-tag3, #add-tag4, #add-tag5").hide();
				$("#add-tag-btn").click(
				function() { $("#add-tag1, #add-tag2, #add-tag3, #add-tag4, #add-tag5").toggle(""); }
				);
			
			$("#is-a").click(
				function() { $("#options").toggle(""); }
				);
			
		$("#small-arrow").hide();
		$("#small-arrow2").hide();
			$("#is-a").click(
				function() { $("#small-arrow").toggle(""); }
				);
			$("#is-a").click(
				function() { $("#small-arrow2").toggle(""); }
				);
			

			$("#object-name").click(function () {
				$("#object-panel").toggle("");
													});
													
			$("#semanticBrowse").click(function () {
				$("#semanticBrowseTable").toggle("");
													});
													
			$("#browse-panel").hide();
			$("#sem-browse").click(function () {
				$("#browse-panel").toggle("");
				});
			});
						   

</script>
<script type="text/javascript">


function showLocalImage(field)
	 {
	 	var browserName=navigator.appName; 
		var browserVersion=navigator.appVersion; 
				
		if(browserName == 'Netscape' && parseInt(browserVersion) >3){
			var userImage = document.getElementById('userImage');	
			try {
				userImage.src=document.getElementById('imageFile').files[0].getAsDataURL();
				netscape.security.PrivilegeManager.enablePrivilege("UniversalXPConnect");				
			}catch (e) {			
				//alert('Unable to read full file path due to browser security settings. '
				//		+'This is a known issue if you are using Firefox 3. To overcome this, '
				//		+'follow these steps: (1) Enter "about:config" in the address bar of Firefox,'
				//		+' and click the confirmation button, to allow you to change your browser settings; (2) When the settings page appears, right-click anywhere on the page and select New->Boolean; (3) Enter "signed.applets.codebase_principal_support" (without the quotes) as a new preference name; (4) Click OK and try uploading the file again.');
			} 
		}
		else{
			var filePath = document.getElementById('imageFile').value;
			document.getElementById("userImage").src = convertToAbsolute(filePath);
		}
	}
	function convertToAbsolute(url)
	{	
		url = url.replace(new RegExp(/\\/g),"/");
		return url;
	}
	function getPath()
	{
 		return path;
	 }
	 
	 function goToAddObjectPage(objectName,objectId){
		 	
		document.editObjectForm.objectName.value = objectName;
		document.editObjectForm.objectId.value = objectId;
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
			//alert("assigning text Box name");
			newTextBoxName = assignName;
			//alert("assigned name is : "+newTextBoxName);
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
	function changeLabelOnClick(idName){		
		if(idName.innerHTML == '[Hide]'){
			idName.innerHTML = '[Show]';
		}
		else{
			idName.innerHTML = '[Hide]';
		}
	}
	function imageFiledValidation(){
	
		var fileTypes=[".jpg",".jpeg",".png",".bmp"];
		var fileTypes1=["jpg","jpeg","png","bmp"];
		var flag = false;
		var objectName = document.objectForm.objectName.value;
		objectName = objectName.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');	
		if(objectName.length == 0){
			alert("Invalid Object Name");
			return false;
		}
		var filePath = document.getElementById('imageFile').value;
		filePath = filePath.replace(/^[\s]+/,'').replace(/[\s]+$/,'').replace(/[\s]{2,}/,' ');		
		var ext=filePath.substring(filePath.lastIndexOf(".")).toLowerCase();		
		if(filePath.length != 0){
			for (var i=0; i<fileTypes.length; i++){
					if (fileTypes[i]==ext)
					{
						flag=true;
				  	}
			  }
		  	 if(flag != true){
		  		alert("Please load an image with the extention of "+fileTypes1.join(", "));				
				return false;
			}else{
				return true;
			}	
		}
	}
</script>
</head>

<body>
<table width="80%" border="0" align="center" cellpadding="4" cellspacing="0">
  <%@ include file="/jsp/sempediaHeader.jsp" %>
</table>
<form name="editObjectForm" method="post" action="addObject.htm?action=getObjectDetailsFromSearch">
	<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>
	<input type="hidden" name="objectName" />
</form>
<form name="saveNewPropertyForm" method="post" action="addObject.htm?action=saveNewProperty">
	<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>
	<input type="hidden" name="objectName" />
	<input type="hidden" name="isOfType" />
	<input type="hidden" name="newPredicate" />
	<input type="hidden" name="newPredicateValue" />
</form>
 <form id="form5" name="objectForm" enctype="multipart/form-data" method="post" action="addObject.htm?action=saveCroppedImage" onsubmit="return imageFiledValidation();">
<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>
<hr width="80%" size="1" />
<table width="100%" border="0" align="center">
	<tr>
		<td align="center"><font color="red"><b><c:out value="${exceptionMessage}"/></b></font></td>
		<c:if test="${exceptionMessage == null && userMessage != null}">
			<td align="center"><font color="blue"><b><c:out value="${userMessage}"/> </b></font></td>
		</c:if>
	</tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr id="">
   <td width="40%" height="100%" valign="top"><strong>
     <label><span class="subject-link"><spring:message code="objectname"/></span></label>
   </strong><span class="Header2-black"><strong>
   <label>
  <input type="text" name="objectName" id="objectName" value="<c:out value='${objectNameFromObjectPage}'/>" class="ie6-input-text"/>
  <input type="hidden" name="hiddenObjectName" id="hiddenObjectName" value="<c:out value='${objectNameFromObjectPage}'/>"/>
  <input type="hidden" name="isOfType" id="isOfType" value="<c:out value='${className}'/>"/>
   </label>
   	  </strong>
    </span>
    </td>
   <td valign="top"><table border="0" align="right" cellpadding="4" cellspacing="0">
  <tr>
  <td class="normal-small" id="object-name" onclick="changeLabelOnClick(this)">[Hide]</td>
  	</tr>
  </table></td>
  </tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0" id="object-panel">
  <tr>
    <td valign="top"><p class="normal-small"><img src="addObject.htm?action=readImage&objectName=<c:out value='${objectNameFromObjectPage}'/>" width="150" height="188" alt="Insert Image" id="userImage"/></p>
     
        <label>
          <input type="file" name="imageFile" id="imageFile" onchange="showLocalImage(this)" class="ie6-file"/>
          <input type="hidden" name="objectDescriptionId" value="<c:out value="${objectDescriptionId}" />"/>
          <input type="submit" name="" value="Save" class="ie6-submit-button"/> <input type="button" name="" class="ie6-submit-button" value="Close" onclick="goToAddObjectPage('<c:out value='${objectNameFromObjectPage}'/>', '<c:out value='${objectIdFromObjectPage}'/>')"/>
        </label>

    <p class="normal-small">&nbsp;</p></td>
    <td colspan="2" valign="top"><c:out value="${objectDescriptionHeading}" /><br/>
      <label>
        <textarea name="objectDescription" id="objectDescription" cols="80" rows="32"><c:out value="${objectDescription}" /></textarea>
         <!-- Once you comment the Script code, above text area wil act as a normal text area........... -->
         <script language="javascript1.2">
		    make_wyzz('objectDescription');
		 </script>  
      </label>
    <p>&nbsp;</p></td>
  </tr>
</table>
</form>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td><span class="Header2-black"><spring:message code="semanticbrowse"/></span></td>
    <td><table border="0" align="right" cellpadding="4" cellspacing="0" id="browse-panel-show">
      <tr>
        <td class="normal-small" id="semanticBrowse" onclick="changeLabelOnClick(this)" >[Hide]</td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0" id="semanticBrowseTable">
  <tr>
    <td width="40%" height="100%" colspan="2" valign="top"><br />
      <table border="0" cellspacing="0" cellpadding="6">
        <tr>
          <td nowrap="nowrap" id="AddSemanticTagId"><span class="medium-grey"><spring:message code="addasemantictag"/></span></td>
        </tr>
        <tr>
          <td class="normal-small" ><spring:message code="usernoteineditobjectfordescription" /></td>
          
          </tr>
      </table>
        <form method="post" name="predicateForm" action="addObject.htm?action=updateObjectDetails"> 
		      <table border="0" cellspacing="0" cellpadding="6" id="add-tag">
		      	<tr>
		      		<td colspan="7">
		      			<input type="hidden" name="objectName" value='<c:out value="${objectNameFromObjectPage}" />' /> 
						<input type="hidden" name="isOfType" value='<c:out value="${superClass}"/>'/>
						<input type="hidden" name="objectId" value="<c:out value='${objectIdFromObjectPage}'/>"/>
					</td>
				</tr>
										
		        <tr>
		          <td bgcolor="" class="normal"><span class="normal-small-bold-link"><spring:message code="predicateorproperty" /></span></td>
		          <td bgcolor=""><input type="text" name="newPropertyAddingToSuperClass" id="newPropertyForAjax" class="ie6-input-text"/></td>
		          <td>&nbsp;</td>
		          <td bgcolor=""><span class="normal-small-bold-link"><spring:message code="objectorvalue" /></span></td>
		          <td bgcolor="">&nbsp;</td>
		          <td bgcolor=""><input type="text" name="newPropertyAddingToSuperClassValue" id="newPropertyForAjaxValue" class="ie6-input-text"/></td>
		          <td>
		            <label>
		              <input type="button" name="saveNewProperty" id="newPropertySaving" value="Add Tag" class="ie6-submit-button"/>
		            </label>
		          </td>
		        </tr>
		      </table>
		 </form>
      <p>&nbsp;</p>
      <p class="normal"><span class="tag-show" id="show-tags"><spring:message code="viewtagsfor" /> <strong><c:out value='${objectNameFromObjectPage}'/></strong></span></p>
      <table border="0" cellspacing="2" cellpadding="4" id="tags">
				        <tr>
				          <td nowrap="nowrap"><span class="normal"><strong><u><spring:message code="property" /></u></strong></span></td>
				          <td nowrap="nowrap" class="normal"></td>
				          <td nowrap="nowrap"><span class="normal"><strong><u><spring:message code="value" /></u></strong></span></td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				        </tr>
        
	 	<c:forEach var="predicatesList" items="${predicatesList}">
		<c:set var="count" value="0"/>
			<c:forEach var="predicateValueArray" items="${predicatesList.value}" >
				
						<tr>
							<c:if test="${count == 0}">
				          <td nowrap="nowrap"><span class="normal"><c:out value="${predicatesList.key}"/></span></td>
				          <td nowrap="nowrap" class="normal"><img src="images/right.jpg" width="16" height="10" alt="k" /></td>
				          	</c:if>
				          	<c:if test="${count != 0}">
				          <td nowrap="nowrap">&nbsp;</td>
          				  <td nowrap="nowrap" class="normal">&nbsp;</td>
          					</c:if>
				          <td nowrap="nowrap" id="#an-object"><span class="normal"><c:out value="${predicateValueArray.predicateValue}"/></span></td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				          <td nowrap="nowrap" class="normal">&nbsp;</td>
				        </tr>
				 <c:set var="count" value="${count + 1}"/>
			</c:forEach>
		</c:forEach>
      </table></td>
  </tr>
</table>
    
<table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td bgcolor="#4C88BE"><img src="images/1px-transparent.gif" width="1" height="1" alt="1px" /></td>
  </tr>
</table>
<table width="80%" border="0" align="center" cellpadding="8" cellspacing="0">
  <tr>
    <td class="normal-small"> <spring:message code="copyright" /> </td>
  </tr>
</table>
<p>&nbsp;</p>

<script type="text/javascript"><!--

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