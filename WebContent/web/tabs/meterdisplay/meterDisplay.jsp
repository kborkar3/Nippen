<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>

<%@page import="java.util.List"%>

<sx:head />
<style>
</style>

<script type="text/javascript">

function submitTheForm(clickedButton) 
{ 
	var newAction = 'SelectMeterAction.action?feeder='+ clickedButton.name;
	callAction(newAction);
}

function validate() 
{
	var feederName = document.meterForm.feederName.value;
	
	if (feederName == "") 
	{
		alert("Please select atleast one search criteria");
		return false;
	}
}


</script>

<center>
	<font color="red">
<% 
String isRedirected = (String)request.getAttribute("isRedirected");
        if(isRedirected!=null)
        {%>
			No feeders are assigned            
       <% }
        %>
 </font>
</center>       
       
        
<s:form action="SelectMeterAction.action" method="post" theme="simple" onsubmit="return(validate());" name="meterForm">
	<table width="100%" border="0" height="100%">
		<tr>
			<td><hr style="border: 1px solid #000;"><font color="red"><s:actionerror /></font></td>
		</tr>
		<tr>
			<%
			    java.util.List<com.projectwork.bean.FeederBean> feederList = (java.util.List<com.projectwork.bean.FeederBean>)request.getSession().getAttribute("feederList");
			%>
			<td>
				 <table >
					<tr>
						<td bgcolor="#E5F1E0" height="70px">&nbsp;&nbsp;
							<div
								style="float: left; height: auto; overflow-x: auto; overflow-y: hidden; white-space: nowrap; width: 1300px">
								<s:iterator value="feederList">
									 <input type="button" name = "<s:property value="feeder" />" value="<s:property value="feeder" />" style="height: 40px; width: 95px"
										class="feederBar" onclick="submitTheForm(this);"/> 
									<%-- <a onclick="submitTheForm(this);" name = "<s:property value="feeder" />"><s:property value="feeder" /></a>| --%>	
									
								</s:iterator>
							</div>
						</td>
					</tr>
				</table> 

			</td>
		</tr>
		<tr>
			<td ><hr style="border: 1px solid #000;"></td>
		</tr>
		<tr height="45" />

		<tr>
			<td width="100%" align="center" colspan=3>


				<table border="0" height="100%" width="100%">
					<tr>
						<td width="30%" align="left" class="fieldname" >Select Search
							Feeder : <sx:autocompleter size="1" list="feederListSearch"
								name="feederName" dropdownHeight="450" id="feederName"
								cssStyle="WIDTH:120px; border-top-right-radius: 0em;"></sx:autocompleter>
							<button type="submit">
								<s:text name="button.go" />
							</button>
						</td>

						<td width="30%" align="left" /></td>

						<td width="40%"></td>
					</tr>

					<tr height="45" />
					<%
					java.util.List<com.projectwork.dto.ParameterDTO> parameterdtoLeftList = (java.util.List<com.projectwork.dto.ParameterDTO>)request.getAttribute("parameterdtoLeftList");
					java.util.List<com.projectwork.dto.ParameterDTO> parameterdtoRightList = (java.util.List<com.projectwork.dto.ParameterDTO>)request.getAttribute("parameterdtoRightList");
					String lastUpdatedTime = (String)request.getAttribute("lastUpdatedTime");
					String selectedFeeder = (String)request.getAttribute("selectedFeeder");
					%>
					
					<tr><td class="fieldname"> 
								<%
							 if(selectedFeeder != null)
							{
							%>
								Selected Feeder is <%= selectedFeeder%>
								<%} %>
									</td></tr>
								
								<tr height="15" />
									
									<tr><td class="fieldname"> 
									<% 
									 if(request.getAttribute("nodata")!=null)
								 {
									     
									     String message = (String) request.getAttribute("nodata");
								%>
								<font color="red"><b>
								<script type="text/javascript">
            						alert('<%=message%>');
        						</script>
								
								</b></font>
								<% } %>
								</td></tr>	

					<tr>
						<td width="45%">
							<%
								 if(request.getAttribute("parameterdtoLeftList") !=null)
								{
							%>
							<table width="100%" border=1>
								<tr>
									<td height="20" width="50%" class="tableheadrow" align="center">Parameter
										Name</td>
									<td height="20" width="50%" class="tableheadrow" align="center">Values</td>
								</tr>

								<%
								    }
								%>
								
								
									
								<s:iterator value="parameterdtoLeftList" var="paramdto">
									<tr>
										<td height="20" width="50%" align="center"><s:property
												value="parameterName" /></td>

										<s:if test="#paramdto.parameterRYGB == 0">
											<td height="20" width="50%" class="redFontTable"
												align="center"><b><s:property
														value="parameterValue" /></b></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 1">
											<td height="20" width="50%" class="yellowFontTable"
												align="center"><b><s:property
														value="parameterValue" /></b></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 2">
											<td height="20" width="50%" class="greenFontTable"
												align="center"><b><s:property
														value="parameterValue" /></b></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 3">
											<td height="20" width="50%" class="blueFontTable"
												align="center"><b><s:property
														value="parameterValue" /></b></td>
										</s:if>
									</tr>
								</s:iterator>
							</table>
						</td>

						<td width="45%">
							<%
							 if(parameterdtoRightList != null)
							{
							%>
							<table width="100%" border=1>
								<tr>
									<td height="20" width="50%" class="tableheadrow" align="center">Parameter
										Name</td>
									<td height="20" width="50%" class="tableheadrow" align="center">Values</td>
								</tr>

								<%
								    }
								%>
								<s:iterator value="parameterdtoRightList" var="paramdto">
									<tr>
										<td height="20" width="50%" align="center"><s:property
												value="parameterName" /></td>

										<s:if test="#paramdto.parameterRYGB == 0">
											<td height="20" width="50%" class="redFontTable"
												align="center"><s:property value="parameterValue" /></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 1">
											<td height="20" width="50%" class="yellowFontTable"
												align="center"><s:property value="parameterValue" /></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 2">
											<td height="20" width="50%" class="greenFontTable"
												align="center"><s:property value="parameterValue" /></td>
										</s:if>

										<s:if test="#paramdto.parameterRYGB == 3">
											<td height="20" width="50%" class="blueFontTable"
												align="center"><s:property value="parameterValue" /></td>
										</s:if>
									</tr>
								</s:iterator>
							</table>
						</td>

					</tr>

					<tr height="15" />
					<%
					    if(lastUpdatedTime!=null)
					{
					%>
					<tr>
						<td>Last updated on: <%=lastUpdatedTime%>
						</td>
					</tr>
				</table>
				
			</td>
		</tr>
	</table>
	<%
	    }
	%>
</s:form>


<script>
	document.getElementById("meterTab").style.textDecoration = "underline";
	document.getElementById("meterTab").style.backgroundColor = "#9DB1ED";
</script>

