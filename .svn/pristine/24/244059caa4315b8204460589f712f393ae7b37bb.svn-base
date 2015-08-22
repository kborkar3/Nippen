<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>
<center>
	<font color="red"><s:actionerror /></font>
</center>
<br />
<br />

<s:form action="login.action" method="post" theme="simple">
	<div align="center"
		height="100%">

		<table width="100%" border="0" cellspacing="3" cellpadding="3"
			height="100%">
			<tr>
				<td class="pageheadercat" colspan="2" align="center"><s:text
						name="loginPage.title" /></td>
			</tr>
			<tr height="15" />
			<tr>

				<td width="50%" align="right" class="fieldname"><s:text
						name="loginPage.username.label" />:</td>
				<td width="50%"><input class="data" type="text" id="Textbox1"
					name="userName" size="25" MAXLENGTH="15" autocomplete="off"
					onkeypress="return RestrictSpace(event);"></td>
			</tr>
			<tr height="15" />
			<tr>
				<td width="50%" class="fieldname" align="right"><s:text
						name="loginPage.password.label" />:</td>
				<td width="50%"><input class="passworddata" type="password"
					name="password" size="25" MAXLENGTH="15"
					autocomplete="off" onkeypress="return RestrictSpace(event);"></td>
			</tr>
			<tr height="15" />
			<tr>
				<td align="right"><s:submit method="execute" align="center" /></td>
				<td align="left">&nbsp;&nbsp;&nbsp;<input type="button"
					value="<s:text name="button.clear" />" onclick="clearForm();" />
					<input type="button"
					value="<s:text name="button.forgot" />" onclick="callAction('forgotPassword.action');" />
					
					
					</td>
			</tr>
			<tr height="15" />
			<tr>

				<%-- <td colspan="2" align="center"><input type="button"
								value="<bean:message key="button.forgot" />"
								onclick="callAction('forgotPassword.do');"  /></td>
					 --%>
			</tr>
		</table>
	</div>
</s:form>
</BODY>
