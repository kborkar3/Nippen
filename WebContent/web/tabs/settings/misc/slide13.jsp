<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="com.projectwork.constants.TestProjectConstantsIfc"%>

<s:form action="/Slide13SearchAction">
	<table width="60%" border="0" cellspacing="3" cellpadding="3" height="100%">
		<tr height="65" />
		<tr>
			<td align="right" class="fieldname"><s:text
					name="addCompanyName.label" />:</td>
			<td align="left"><s:textfield property="companyName" size="20"
					maxlength="20" value="" /></td>
		</tr>
		<tr height="15" />
		<tr>
			<td align="right" class="fieldname"><s:text
					name="addCompanyAddress.label" />:</td>
			<td align="left"><s:textarea property="companyAddress"
					cols="50" rows="3" /></td>
		</tr>
		<tr height="35" />

		<tr>
			<td align="right"><s:submit>
					<s:text name="button.submit" />
				</s:submit></td>
			<td><s:reset>
					<s:text name="button.clear" />
				</s:reset></td>	
		</tr>
		<tr height="15" />
	</table>
</s:form>	
<script>
	document.getElementById("settingsTab").style.backgroundColor = "#A9A9F5";
	document.getElementById("slide13Tab").style.backgroundColor = "#A9A9F5";
</script>	