<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<center>
	<font color="blue">Feeder modification is successful..</font>
</center>


<s:property value="meterID" />

<s:form action="Settings.action" method="post" theme="simple">
Click on this button to go back to previous page..
<s:submit method="execute" align="center" />

</s:form>