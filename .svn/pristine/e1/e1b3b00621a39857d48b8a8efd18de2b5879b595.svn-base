<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<s:form action="forgotPasswordUserAction.action" method="post"
	theme="simple">

<%
String userName = (String)request.getAttribute("forgottenUser");
String temporaryPassword = (String)request.getAttribute("temporaryPassword");

%>

	<table width="30%" align="center">
	
		<tr>
			<td width="100%" align="center"><font color="blue">
			Temporary password is <%=temporaryPassword %>. 
			
			Please note down and use it for resetting the password.  
			
			</font></td>
		</tr>
	
		<tr>
			<s:if test="hasActionErrors()">
			<td width="100%" align="center"><font color="red"><s:actionerror /></font></td>
		</s:if>
		</tr>
		<tr height="35" />
		<tr>
			<td width="100%" align="center"><s:submit method="execute" /></td>
		</tr>
	</table>

</s:form>