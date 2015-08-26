<%@ taglib prefix="s" uri="/struts-tags"%>


<s:form action="/ConfigureFeederAction.action" method="post" theme="simple"
	name="graphForm">
	<table width="70%">

		<tr height="65" />
		<tr>
			<td align="right" width="50%" class="fieldname"><s:text
					name="selectMeter.label" />:</td>
			<td width="50%"><s:select list="feederList" name="feeder"
					id="feeder" listKey="feeder" listValue="feeder"
					cssStyle="WIDTH:120px"></s:select></td>
		</tr>
		<tr height="35" />
		<tr>
			<td width="50%" align="right">
				<button type="submit">
					<s:text name="button.show" />
				</button>
			</td>
			<td width="50%"><s:reset /></td>


		</tr>
		<tr height="15" />

	</table>
</s:form>


<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("configureTab").style.backgroundColor = "#A9A9F5";
</script>