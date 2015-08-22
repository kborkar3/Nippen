<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<br />
<br />
<br />

<center>
	<font color="blue">Company Added successfully..</font>
</center>
<br />
<br />
<br />
<br />

<s:form action="Settings.action" method="post" theme="simple">
Click on this button to go back to previous page..

<br />
<br />
<br />
<br />

<s:submit method="execute" align="center" />

</s:form>

<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("addCompanyTab").style.backgroundColor = "#A9A9F5";
</script>
