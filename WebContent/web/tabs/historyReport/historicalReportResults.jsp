
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

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


<%@page import="java.util.List"%>
<s:form action="ExportReportAction.action" method="post">

	<table width="100%" border="0" cellspacing="3" cellpadding="3">
		<tr>
			<td width="53%" class="fieldname">Results Page</td>

		</tr>
		<tr bgcolor="#CCCCCC">
			<td colspan="2"></td>
		</tr>
		<tr>
			<td colspan="2">
				<table width="100%" class="tableoutline">
					<tr>
						<td align="center"><s:actionerror /></td>
					</tr>
					<tr>
						<td>
							<table width="96%" border=1 frame=HSIDES rules=ROWS
								bordercolorlight=WHITE bordercolor=#CCCCCC cellspacing=1
								align="center">

								<tr>
									<td height="20" width="20%" class="tableheadrow">
										<div align="center" class="tab">Date</div>
									</td>
									<td height="20" width="15%" class="tableheadrow">
										<div align="center" class="tab">Time</div>
									</td>
									<td height="20" width="15%" class="tableheadrow">
										<div align="center" class="tab">Feeder Name</div>
									</td>
									<td height="20" width="15%" class="tableheadrow">
										<div align="center" class="tab">Parameter Name</div>
									</td>
									<td height="20" width="15%" class="tableheadrow">
										<div align="center" class="tab">Value</div>
									</td>
								</tr>

								<s:iterator value="historicalReportdtoList">

									<tr>
										<td height="20" width="15%" class="normal" align="center">
											<s:property value="dateStamp" />
										</td>

										<td height="20" width="15%" class="normal" align="center">
											<s:property value="timeStamp" />
										</td>

										<td height="20" width="15%" class="normal" align="center">
											<s:property value="feederName" />
										</td>

										<td height="20" width="15%" class="normal" align="center">
											<s:property value="parameterName" />
										</td>

										<td height="20" width="20%" class="normal" align="center">
											<s:property value="value" />
										</td>
									</tr>
								</s:iterator>
							</table>
							<p>&nbsp;</p>
						</td>
					</tr>
					</table>
			</td>
		</tr>

		<tr height="15" />
		<tr>
			<td width="50%" align="center"><input type="button"
				value="<s:text name="button.export" />"
				onclick="callAction('ExportReportAction.action');" /></td>
		</tr>
	</table>
</s:form>


<script>
	document.getElementById("histReportTab").style.textDecoration = "underline";
	document.getElementById("histReportTab").style.backgroundColor = "#9DB1ED";
</script>
