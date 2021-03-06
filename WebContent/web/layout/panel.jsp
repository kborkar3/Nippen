<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>

<style> 
.panelDiv {
    border: 1px solid;
    padding: 1px;
    background: #F5C2C2;
    border-top-right-radius: 1em;
    border-top-left-radius: 1em;
}

a:hover {
	text-decoration: none;
	color: #1963a9;
	text-decoration: underline;
}
</style>

<table cellspacing="3" cellpadding="0" width="30%">
	<tr>
		<td id="meterTab" width="10%" align="center" class="panelDiv"><div>
			<a href="<s:url value="/MeterDisplay.action"/>"><s:text name="meterTab.label" /></a></div>
		</td>
		
		<td id="histReportTab" width="13%" align="center" class="panelDiv"><div>
			<a href="<s:url value="/HistoricalReport.action"/>"><s:text name="histReportTab.label" /></a></div>
		</td>
		
		<td id="graphTab" width="5%" align="center" class="panelDiv"><div>
			<a href="<s:url value="/Graph.action"/>"><s:text name="graphTab.label" /></a></div>
		</td>
		
		<% if(request.getSession().getAttribute("userType")=="ADMINISTRATOR"){ %>
		 <td id="settingsTab" width="5%" align="center" class="panelDiv"><div>
			<a href="<s:url value="/Settings.action"/>"><s:text name="settingsTab.label" /></a></div>
		</td>
		<%} %> 	 
				
	</tr>
</table>
<script>
setTabbedPanelWidth();
</script>