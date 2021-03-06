<%@ taglib prefix="s" uri="/struts-tags"%>

<script type="text/javascript">
	// Form validation code will come here.
	function validate() {

		if (document.unitForm.unitID.value == "") {
			alert("Please Enter Unit ID!");
			document.unitForm.unitID.focus();
			return false;
		}

		if (document.unitForm.unitName.value == "") {
			alert("Please Enter Unit Name!");
			document.unitForm.unitName.focus();
			return false;
		}

		if (isNaN(document.unitForm.unitID.value)) {
			alert("Unit ID should be a number");
			document.unitForm.unitID.focus();
			return false;
		}

		return (true);
	}
</script>

<s:if test="hasActionErrors()">
	<table align="center">
		<tr>
			<td><font color="red"><s:actionerror /></font></td>
		</tr>
	</table>
</s:if>


<s:form action="/AddNewUnitAction.action" method="post" theme="simple"
	onsubmit="return(validate());" name="unitForm">

	<table width="100%" border="0" cellspacing="3" cellpadding="3"
		align="center" height="100%">
		<tr height="35" />

		<tr>
			<td width="45%" align="right" class="fieldname"><s:text
					name="units.unitID" /> : <s:textfield
					name="unitID" size="20" maxlength="20" value=""
					onkeypress="return RestrictSpace(event);" /></td>
			<td width="10%" />
			<td width="45%" class="fieldname"> <s:text
					name="units.unitName" /> : <s:textfield
					name="unitName" size="20" maxlength="20" value="" /></td>
		</tr>
		<tr height="15" />

		<tr>
			<td width="45%" align="right" class="fieldname"> <s:text
					name="units.assignCompany" /> : <s:select list="companyList"
					name="companyID" id="companyID" listKey="companyID"
					listValue="companyID" cssStyle="WIDTH:120px"></s:select></td>
			<td width="10%" />
			<td width="45%" class="fieldname"></td>
		</tr>

		<tr>
			<td colspan="4" align="center"><button type="submit"
					onclick="alphanumeric(document.form1.text1)">
					<s:text name="button.addUnit" />
				</button></td>
		</tr>
		<tr height="15" />
	</table>
</s:form>

<script>
	document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("unitsTab").style.backgroundColor = "#A9A9F5";
</script>


