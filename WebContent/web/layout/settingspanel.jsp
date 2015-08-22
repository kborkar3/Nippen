<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.projectwork.constants.TestProjectConstantsIfc" %>

<style> 
.settingsPanelDiv {
    border: 1px solid;
    padding: 1px;
    background: #F5C1AF;
    border-top-right-radius: 1em;
    border-top-left-radius: 1em;
    border-bottom-right-radius: 1em;
    border-bottom-left-radius: 1em;
}

a:hover {
	text-decoration: none;
	color: #1963a9;
	text-decoration: underline;
}
</style>

<table id="tabbedDiv" cellspacing="3" cellpadding="0" width="38%">
	<tr>
		<td id="showCompanyTab" width="12%" align="center" class="settingsPanelDiv" class="settingsPanelDiv">
				<a href="<s:url value="ShowCompany.action"/>"><s:text name="settingsTab.showCompanyTab.label" /></a>
		</td>
		
		<td id="addCompanyTab" width="10%" align="center" class="settingsPanelDiv">
				<a href="<s:url value="AddCompany.action"/>"><s:text name="settingsTab.addCompanyTab.label" /></a>
		</td>

		<td id="employeeTab" width="10%" align="center" class="settingsPanelDiv">
			<a href="<s:url value="Employee.action"/>"><s:text name="settingsTab.employeeTab.label" /></a>
			</td>

		<td id="unitsTab" width="10%" align="center" class="settingsPanelDiv">
			<a href="<s:url value="Units.action"/>"><s:text name="settingsTab.unitsTab.label" /></a>
			</td>
			
		<td id="configureTab" width="10%" align="center" class="settingsPanelDiv">
			<a href="<s:url value="Configure.action"/>"><s:text name="settingsTab.configTab.label" /></a>
			</td>	

<%-- 		<% if(request.getSession().getAttribute(TestProjectConstantsIfc.USER_TYPE)==TestProjectConstantsIfc.ADMINISTRATOR){ %>				
		<td class="divformatsub" id="miscTab" width="10%"><s:url value="/Misc.do">
				<s:text name="settingsTab.miscTab.label" />
			</s:url></td>			
			
		<td class="divformatsub" id="slide12Tab" width="10%"><s:url value="/Slide12.do">
				<s:text name="settingsTab.slide12Tab.label" />
			</s:url></td>	
		<td class="divformatsub" id="slide13Tab" width="10%"><s:url value="/Slide13.do">
				<s:text name="settingsTab.slide13Tab.label" />
			</s:url></td>
		<td class="divformatsub" id="slide14Tab" width="10%"><s:url value="/Slide14.do">
				<s:text name="settingsTab.slide14Tab.label" />
			</s:url></td>						
			
	<%} %> 
 --%>	</tr>
</table>
<script>
setTabbedPanelWidth();
</script> 