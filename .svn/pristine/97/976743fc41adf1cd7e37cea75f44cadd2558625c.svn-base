<%@ taglib prefix="s" uri="/struts-tags"%>

<script src="js/jquery-1.8.2.js"></script>
<script src="js/jquery-ui.js"></script>

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.11.3/themes/smoothness/jquery-ui.css">
<link rel="stylesheet" href="js/jquery-1.8.2.js" />
<link rel="stylesheet" href="js/jquery-ui.js" />
<link rel="stylesheet" href="/resources/demos/style.css">

<script>
	$(function() {
		$("#HistoricalReportSearchAction_startDate").datepicker({
			dateFormat : '<s:text name="jquery.dateFormat" />'
		});
		$("#HistoricalReportSearchAction_endDate").datepicker({
			dateFormat : '<s:text name="jquery.dateFormat" />'
		});

	});
</script>


<script type="text/javascript">
	function validate()
	{
		var startDate = document.reportForm.startDate.value;
		var endDate = document.reportForm.endDate.value;
		var parameterMap = document.reportForm.parameterMap.value;
		
		if (startDate == "") {
			alert("Please Enter Start Date");
			return false;
		}

		if (endDate == "") {
			alert("Please Enter End Date");
			return false;
		}

		if (new Date(endDate).getTime() < new Date(startDate).getTime()) {
			alert("End date can not be prior to Start date");
			return false;
		}

		return (true);
	}
</script>


<body onload="clearForm();">

<table align="center">
	<tr height="25" />
	<tr>
		<td><font color="red"><b> 
 <%
 if(request.getSession().getAttribute("nohistoricdatafound") !=null) 
 {%> 
     No data available 
 <% } %>

<% request.getSession().removeAttribute("nohistoricdatafound");%>

</b></font></td>
		</tr>
	</table>


	<s:form action="/HistoricalReportSearchAction.action" method="post"
		theme="simple" onsubmit="return(validate());" name="reportForm">
		<table width="70%">

			<tr height="65" />

			<tr>
				<td align="right" width="50%" class="fieldname"><s:text
						name="selectParameter.label" />:</td>
				<td width="50%"><s:doubleselect label="Feeder Parameter"
						name="feederMap" list="feederParametereMap.keySet()"
						doubleName="parameterMap"
						doubleList="feederParametereMap.get(top)" doubleMultiple="true"
						doubleCssStyle="height: 100px" /></td>
			</tr>
			<tr height="35" />

			<tr>
				<td width="50%" align="right" class="fieldname"><s:text
						name="startDate.label" />:</td>
				<td width="50%"><s:textfield styleClass="data" name="startDate"
						size="10" maxLength="18" tabindex="5" /></td>
			</tr>
			<tr height="35" />
			<tr>
				<td width="50%" align="right" class="fieldname"><s:text
						name="endDate.label" />:</td>
				<td width="50%"><s:textfield styleClass="data" name="endDate"
						size="10" maxLength="18" tabindex="5" /></td>
			</tr>
			<tr height="35" />
			<tr>
				<td width="50%" align="right">
					<button type="submit">
						<s:text name="button.show" />
					</button>
				</td>
				<%-- <td width="50%"><s:reset /></td> --%>
			</tr>
			<tr height="15" />


		</table>


	</s:form>

</body>
<script>
	document.getElementById("histReportTab").style.textDecoration = "underline";
	document.getElementById("histReportTab").style.backgroundColor = "#9DB1ED";
</script>
