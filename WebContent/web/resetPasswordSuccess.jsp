<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<center>
	<font color="blue">Password reset is successful. Please login with new password.</font>
</center>
<br />
<br />

<s:form action="login.action" method="post" theme="simple">
Click on this button to go back to previous page..
<s:submit method="execute" align="center" />

</s:form>