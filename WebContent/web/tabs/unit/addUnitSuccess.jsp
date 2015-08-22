<%@ taglib prefix="s" uri="/struts-tags"%>

<br />
<br />
<br />
<br />

<center>
	<font color="blue">Unit Added successfully..</font>
<br />
<br />
<br />
<br />

<s:form action="Units.action" method="post" theme="simple">
Click on this button to go back to previous page..

<br />
<br />
<br />
<br />

<s:submit method="execute" align="center" />
</s:form>

</center>
<script>
	document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("unitsTab").style.backgroundColor = "#A9A9F5";
</script>
