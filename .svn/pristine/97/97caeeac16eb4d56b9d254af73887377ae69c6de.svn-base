<%@ taglib prefix="s" uri="/struts-tags"%>

<%@ page import="com.projectwork.constants.TestProjectConstantsIfc"%>

<s:form action="/UnitsUpdateAction" method="post" theme="simple">
	<table width="60%" border="0" cellspacing="3" cellpadding="3" height="100%">
		<tr height="65" />
		<tr>
			<td align="right" class="fieldname"><s:text
					name="selectCompany.label" />:</td>
			<td><s:textfield property="profileName" size="20"
					maxlength="20" value="" /></td>

			<td align="right" class="fieldname"><s:text
					name="unitsAlloted.label" />:</td>
			<td><s:textfield property="profileDesignation"
					size="20" maxlength="20" value="" /></td>

			<td align="right" class="fieldname"><s:text
					name="meter.label" />:</td>
			<td><%-- <s:select property="feeder" value="Select"
					multiple="true">
					<html:optionsCollection name="LoginForm" property="feederList"
						label="label" value="value" />
				</s:select> --%></td>

		</tr>
		<tr height="35" />

		<%
		    if (request.getSession().getAttribute(
								TestProjectConstantsIfc.USER_TYPE) == TestProjectConstantsIfc.ADMINISTRATOR) {
		%>
		<tr>
			<td align="right">
							<button type="submit">
					<s:text name="button.addUnit" />
				</button>
				
				</td>
			<td align="left">	<button type="submit">
					<s:text name="button.removeUnit" />
				</button></td>
		</tr>
		<tr height="15" />
		<tr>
			<td align="center" colspan="2">	<button type="submit">
					<s:text name="button.editUnit" />
				</button></td>
		</tr>
		<tr height="15" />
		<%
		    }
		%>
	</table>
</s:form>
<script>
	document.getElementById("settingsTab").style.backgroundColor = "#A9A9F5";
	document.getElementById("unitsTab").style.backgroundColor = "#A9A9F5";
</script>
