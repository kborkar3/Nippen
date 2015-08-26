<%@ taglib prefix="s" uri="/struts-tags"%>					

<head>
<s:head />
</head>

<%java.util.List<com.projectwork.dto.FeederDTO> feederDetailsList = (java.util.List<com.projectwork.dto.FeederDTO>)request.getAttribute("feederDetailsList");%>


<s:form action="/UpdateFeederConfigAction.action" method="post" theme="simple" name="updateFeederForm">

<table border="0" height="50%" width="30%" align="center">

<tr height="25" />

<s:iterator value="feederDetailsList" >

<tr><td align="right" class="fieldname" >Feeder Name : </td><td><s:textfield name="feeder"
					size="20" maxlength="20" value ="%{feeder}" readonly="true" onkeypress="return RestrictSpace(event);" /></td></tr>

<tr height="15" />

<tr><td align="right" class="fieldname" >Feeder ID : </td><td><s:property value="meterID" /></td></tr>
<s:hidden name="meterID" value="%{meterID}" />
<tr height="15" />

<tr><td align="right" class="fieldname" >Feeder Index : </td><td><s:property value="meterIndex" /></td></tr>
<s:hidden name="meterIndex" value="%{meterIndex}" />
<tr height="15" />

<tr><td align="right" class="fieldname" >Company Name : </td><td><s:property value="companyName" /></td></tr>
<s:hidden name="companyName" value="%{companyName}" />
<tr height="15" />

</s:iterator>

<tr><td align="right" class="fieldname" colspan="2">

<s:optiontransferselect
     label="Assigned Units"
     name="leftUnit"
     leftTitle="Assigned Units"
     rightTitle="Available Units"
     list="leftUnitList"
     headerKey="-1"
     size="5"
     allowAddAllToLeft="false"
     allowAddAllToRight="false"
     allowSelectAll="false"
     allowUpDownOnLeft="false"
     allowUpDownOnRight="false"
     labelposition="left"
     headerValue="--- Please Select ---"
     doubleList="rightUnitList"
     doubleName="rightUnit"
     doubleHeaderKey="-1"
     doubleHeaderValue="--- Please Select ---"
     doubleSize="5"/>
 
 </td></tr>

<tr height="15" />

<tr>
	<td width="50%" align="right">
		<s:submit value="Submit" />
	</td>
	<td width="50%"></td>
</tr>

</table>
</s:form>

<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("configureTab").style.backgroundColor = "#A9A9F5";
</script>