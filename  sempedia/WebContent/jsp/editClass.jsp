<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
 <!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>Sempedia | Making computers think about data the way we do</title>
        <link href="css/styles.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/jquery.jeditable.mini.js"></script>
        <script type="text/javascript" src="js/jquery.autocomplete.js"></script>
		<link rel="stylesheet" href="css/jquery.autocomplete.css" type="text/css" />
        <script>
            $(document).ready(function(){
            	 var propertySpanID ;
            	$(".propertySuperID").click(function(){
                    propertySpanID = $(this).attr('id');
               	});
               
            	 var paragraphSpanID ;
            	$(".paragraphSuperId").click(function(){
                    paragraphSpanID = $(this).attr('id');
                   //  alert('paragraphSpanID '+paragraphSpanID);
               	});
            		
            	$(".top-button").hover(function(){
                    $(this).addClass("top-button-active");
                }, function(){
                    $(this).removeClass("top-button-active");
                });
                
                //$("#class-header").click(function () {
                //	$("#class-desc").toggle("");
                //	});
                
                $("#predicate-header").click(function(){
                    $("#predicates").toggle("");
                });
                
                $("#add-property").hide();
                $("#add-property-btn").click(function(){
                    $("#add-property").toggle("");
                });
                
                $("#edit-paragraph").hide();
                $("#add-paragraph-btn").click(function(){
                    $("#edit-paragraph").toggle("");
                });
                
                
                $("#edit-paragraph").hide();
                $("#addClassProperty").click(function(){
                    alert('Cannot add properties as objects exists for this class');
                    return false;
                });
                
                $("#cancel-add").click(function(){
                    $("#add-property").toggle("");
                });
                
                $("#cancel").click(function(){
                    $("#edit-paragraph").toggle("");
                });
                
                
                $(".predicate").hover(function(){
                    $(this).addClass("predicate-over");
                }, function(){
                    $(this).removeClass("predicate-over");
                });
		});
        </script>
    </head>
    <body>
        <table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
            	<%@ include file="/jsp/sempediaHeader.jsp" %>
        </table><hr width="80%" size="1" />
        <form method="post" name="classForm" enctype="text/html">
        <table width="80%" border="0" align="center" cellpadding="4" cellspacing="0">
            <tr>
                <td height="100%" valign="top">
                    <span class="grey-small"><strong>Newbie Help:</strong> If you double click any of the elements - class name, description or properties - they will become editable</span>
                    <span class="comment-text">.</span>
                </td>
            </tr>
            <tr>
        		<td valign="top" align="left">
        			<c:if test="${messageColor == 'red'}">
        				<font class="messages" color="red"><b><c:out value="${editClassMessage}" /></b></font>
        			</c:if>
        			<c:if test="${messageColor == 'blue'}">
        				<font class="messages" color="blue"><b><c:out value="${editClassMessage}" /></b></font>
        			</c:if>
        		</td>
        	</tr>
            <tr id="class-header">
                <td width="40%" height="100%" valign="top">
                    
                    <strong>
                    	<span class="Header2-black" id="class-name">
                            <c:out value="${classValue.subClassName}" />
                        </span>
                    </strong>
                        
                        <input type="hidden" name="propertyName" id="propertyName" size="5"/>
                        <input type="hidden" name="allProperties" id="allProperties" size="5"/>
                        <input type="hidden" id="subClassId" value='<c:out value="${classValue.subClassId}" />' size="5" name="subClassId"/>
						<input type="hidden" id="superClassId" value='<c:out value="${classValue.superClassId}" />' size="5" name="superClassId"/>
						<input type="hidden" size="5" id="classNameHidden" name="classNameHidden" value='<c:out value="${classValue.subClassName}" />'/> 
						<input type="hidden"  name="className" id="className" size="5" />
						<input type="hidden" size="5" name="classParagraph" id="classParagraph"  />
						<input type="hidden" size="5" name="paragraphKey" id="paragraphKey"  />
						<input type="hidden" size="5" name="paragraphStyle" id="paragraphStyle"  />
						
                </td>
            </tr>
        </table>
        <table width="80%" border="0" align="center" cellpadding="4" cellspacing="0" id="object-panel">
        	<tr>
        		<td colspan="2" valign="top" align="left">
        		<c:if test="${messageColor == 'red'}">
        			<font class="messages" color="red"><b><c:out value="${editParagraphMessage}" /></b></font>
        		</c:if>
        		<c:if test="${messageColor == 'blue'}">
        			<font class="messages" color="blue"><b><c:out value="${editParagraphMessage}" /></b></font>
        		</c:if>
        		</td>
        	</tr>
        	<c:set var="paragraphKey" value="${1}"/>
        	<c:forEach var="classParagraphDetails" items="${classValue.classParagraph}">
        		<c:forEach var="classParagraphStyleDetails" items="${classValue.classParagraphStyle}">
            		<c:if test="${classParagraphDetails.key == classParagraphStyleDetails.key}">
            			<tr> 
                			<td colspan="2" valign="top">
                				<div id="paragraphSuperId">
                					<c:if test="${classParagraphStyleDetails.value == 'main'}">
                    					<span class="Header1">
                    						<span class="paragraphSuperId" style="text-align: left" id="description<c:out value='${paragraphKey}'/>">
                    							<c:out value="${classParagraphDetails.value}"/>
                    						</span>
                    					</span>
                    					<input type="hidden" size="5" name="classParagraphHidden" id="description<c:out value='${paragraphKey}'/>hidden" value='<c:out value="${classParagraphDetails.value}"/>' />
                    				</c:if>
                    					
                					<c:if test="${classParagraphStyleDetails.value == 'sub'}">
                    					<span class="header3">
                    						<span class="paragraphSuperId" style="text-align: left" id="description<c:out value='${paragraphKey}'/>">
                    							<c:out value="${classParagraphDetails.value}"/>
                    						</span>
                    					</span>
                    					<input type="hidden" size="5" name="classParagraphHidden" id="description<c:out value='${paragraphKey}'/>hidden" value='<c:out value="${classParagraphDetails.value}"/>'  />
                    				</c:if>
                					
                					
                					<c:if test="${classParagraphStyleDetails.value == 'para'}">
                    					<span class="paragraph">
                    						<span class="paragraphSuperId" style="text-align: left" id="description<c:out value='${paragraphKey}'/>">
                    							<c:out value="${classParagraphDetails.value}"/>
                    						</span>
                    					</span>
                    					<input type="hidden" size="5" name="classParagraphHidden" id="description<c:out value='${paragraphKey}'/>hidden" value='<c:out value="${classParagraphDetails.value}"/>' />
                    				</c:if>
                	  				
                    			</div>
                    			<input type="hidden" size="5" id="paragraphKeyValuedescription<c:out value='${paragraphKey}'/>" name="paragraphKeyValue" value='<c:out value="${classParagraphDetails.key}"/>'/>
                    			<input type="hidden" size="5" id="paragraphStyleValuedescription<c:out value='${paragraphKey}'/>" name="paragraphStyleValue" value='<c:out value="${classParagraphStyleDetails.value}"/>'/>
	                		</td>
    	        		</tr>
    	        	</c:if>
    	        </c:forEach>
    	        <c:set var="paragraphKey" value="${paragraphKey+1}"/>
    	     </c:forEach>
             <tr>
             	<td colspan="2" align="left">
             		<input type="hidden" size="5" id="paragraphKeyCount" name="paragraphKeyCount" value='<c:out value="${paragraphKey}"/>'/>		
             	</td>
             </tr>
             
            <tr>
                <td width="50%" align="left" valign="top">
               	 <table border="0"  id="edit-paragraph">
                	<tr>
                		<td>
	                    	<label>
   	                    	<span class="normal">Style</span>
		                    </label>
		                    <select name="selectStyle" id="selectStyle">
		                        <option value="para">Paragraph</option>
		                        <option value="sub">Sub Heading</option>
		                        <option value="main">Main Heading</option>
		                    </select>
		                    <label>
		                        <textarea name="paragraphTextArea" cols="50" rows="3" id="paragraphTextArea" style="width: 100%;vertical-align: text-top;  ">
		                        </textarea>
		                        <input type="button"  class="ie6-button-button" name="paragraphSave" id="paragraphSave" value="Save" />
		                    </label><input type="button"  class="ie6-button-button" name="cancel" id="cancel" value="Cancel" />
                    	</td>
                   	</tr>
                  </table>  
                    
                </td>
                <td></td>
            </tr>
            <tr id="class-desc">
                <td colspan="2" valign="top">
                    	<label>
                        	<input type="button"  class="ie6-button-button" id="add-paragraph-btn" value="+ Add Paragraph" />
                    	</label>
                </td>
            </tr>
        </table>
        <table width="80%" border="0" align="center" cellpadding="4" cellspacing="0">
            <tr id="predicate-header">
                <td>
                    <span class="Header2-black">Semantic Properties</span>
                </td>
            </tr>
            
        </table>
       
	        
		
        <table width="80%" border="0" align="center" cellpadding="4" cellspacing="0">
            <tr id="predicates">
                <td  colspan="2" valign="top">
                    <p class="normal">
                        The following is a list of semantic properties that are specified for the <c:out value="${classValue.subClassName}" /> class.
                    </p>
                    	<c:if test="${messageColor == 'red'}">
                    		<font class="messages" color="red"><b><c:out value="${editMessage}" /></b></font>
                    	</c:if>
                    	<c:if test="${messageColor == 'blue'}">
                    		<font class="messages" color="blue"><b><c:out value="${editMessage}" /></b></font>
                    	</c:if>
                     <table border="0" cellspacing="0" cellpadding="6">
                        <tr>
                        	<td nowrap="nowrap">
                      			<c:if test="${classValue.operation != 'objectExists'}">
                                	<label>
                                    	<input type="button"  class="ie6-button-button" id="add-property-btn" value="+ Add a Semantic Property" />
                                	</label>
                                </c:if>
                      			<c:if test="${classValue.operation == 'objectExists'}">
                    				<label>
                        				<input type="button"  class="ie6-button-button" id="addClassProperty" value="+ Add a Semantic Property" />
                    				</label>
                  				</c:if>
                  				
                            </td>
                        </tr>
                        <tr>
                            <td valign="top" nowrap="nowrap">
		                                <table border="0" id="add-property">
		                                 <tr>
		                                  <td>
		                               		 <p>
		                                    <label class="normal-small">
		                                        Property
		                                        <br/>
		                                        <input type="text" name="property" id="property" class="ie6-input-text"/>
		                                        <input type="hidden" value="0" id="theValue" name="theValue"/>
		                                    </label>
		                                    <label>
		                                        <input  class="ie6-button-button" type="button" id="savePropertyButton" value="<spring:message code='save'></spring:message>"/>
		                                    </label><input  class="ie6-button-button" type="button" name="cancel-add" id="cancel-add" value="Cancel" />
		                                    <label class="normal-small">
		                                        <br/>
		                                    </label>
		                                    <br/>
		                                </p>
		                            </td>
		                          </tr>
		                        </table>
		                     </td>
                        </tr>
                    </table>
                    <table border="0" id="predicateTable">
                    	<c:set var="predicateKey" value="${1}"/>
                        <c:forEach var="predicateValues" items="${classValue.subClassPredicates}">
                         <c:if test="${classValue.operation != 'objectExists'}">
                            <tr id="rowCount">
                                <td nowrap="nowrap"  class="predicate" id="predicateID" >
                                    <span class="normal" >
                                        <font style="background-color:#DCDCDC" style="display: inline">
                                            <span class="propertySuperID" id="propertyId<c:out value='${predicateKey}'/>"><c:out value="${predicateValues}" /></span>
                                        </font>&nbsp;&nbsp;&nbsp;
                                    </span>
                                    <input type="hidden" size="10" name="originalPredicateValue" id="originalpropertyId<c:out value='${predicateKey}'/>" value='<c:out value="${predicateValues}" />'/>
                                </td>
                            </tr>
                            </c:if>
                            <c:if test="${classValue.operation == 'objectExists'}">
                            <tr id="rowCount">
                                <td nowrap="nowrap" id="predicateIDDup" >
                                    <span class="normal" >
                                        <font style="background-color:#DCDCDC" style="display: inline">
                                            <span class="propertySuperIDDup" id="propertyIdDup<c:out value='${predicateKey}'/>"><c:out value="${predicateValues}" /></span>
                                        </font>&nbsp;&nbsp;&nbsp;
                                    </span>
                                    <input type="hidden" size="10" name="originalPredicateValue" id="originalpropertyIdDup<c:out value='${predicateKey}'/>" value='<c:out value="${predicateValues}" />'/>
                                </td>
                            </tr>
                            </c:if>
                            <c:set var="predicateKey" value="${predicateKey+1}"/>
                        </c:forEach>
                   </table>
                   <input type="hidden" size="5" id="predicateKeyCount" name="predicateKeyCount" value='<c:out value="${predicateKey}"/>'/>
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
		<td class="normal-small">Sempedia 2009 | Creative Commons License</td>
	</tr>
</table>
        <select id="propertySelect" style="visibility: hidden;" name="propertySelect" onchange="">
			<option value="">---Select Property---</option>
		</select>
		<input type="button"  class="ie6-button-button" style="visibility: hidden;" name="deleteProperty" id="deleteProperty" value="<spring:message code="deleteProperty"></spring:message>" onclick="checkValueToDelete()"/>
		<select style="visibility: hidden;" id="superClassPropertySelect" name="superClassPropertySelect" onchange="">
			<option value="">---Select Property---</option>
		</select>
		<c:forEach var="predicateValues" items="${classValue.subClassPredicates}">
			<script type="text/javascript">
				createSelectBox('propertySelect','<c:out value="${predicateValues}" />');
			</script>
		</c:forEach>
		<c:forEach var="superPredicateValues" items="${classValue.superClassPredicates}">
			<script type="text/javascript">
				createSelectBox('superClassPropertySelect','<c:out value="${superPredicateValues}" />');
			</script>
		</c:forEach>
       </form>
    </body>
</html>
