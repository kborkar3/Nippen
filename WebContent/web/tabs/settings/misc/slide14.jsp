<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="com.projectwork.constants.TestProjectConstantsIfc"%>

<s:form action="/Slide14SearchAction">
	<table width="40%" border="0" cellspacing="3" cellpadding="3"
		height="100%">
		<tr height="65" />
		<tr>
			<td align="right" class="fieldname"><s:text
					name="searchCompanyDetails.label" />:</td>
			<td align="left"><s:textfield property="companyId" size="20"
					maxlength="20" value="" /></td>
			<td><s:submit>
					<s:text name="button.go" />
				</s:submit></td>
		</tr>
		<tr height="75" />


		<tr height="75" />

		<tr>
			<td align="center" colspan="3"><s:submit property="button">
					<s:text name="button.change" />
				</s:submit>&nbsp;&nbsp;
			<s:submit property="button">
					<s:text name="button.addUser" />
				</s:submit>&nbsp;&nbsp;
			<s:submit property="button">
					<s:text name="button.disable" />
				</s:submit></td>
			
		</tr>
		<tr height="15" />

	</table>
</s:form>
<script>
	document.getElementById("settingsTab").style.backgroundColor = "#A9A9F5";
	document.getElementById("slide14Tab").style.backgroundColor = "#A9A9F5";
</script>
