<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import = "java.io.File" %>

<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
response.setDateHeader("Expires", 0); // Proxies.
%>

<table id="TableHeader" border="0" width="100%" cellspacing="0"
	cellpadding="0" bgcolor="#CED8F6">
	<tr>
		<td colspan="3">
			<table>
				<tr>
					<td width="50" height="70"></td>
				</tr>
			</table>
		</td>

		<td class="globaltitle" width="80%">
			<%
			    String imageLocation = (String)request.getSession().getAttribute("headerImagePath"); 
			    
			    %>
			
			    <% if (imageLocation != null)
			    {
			%> 
			   <img src="<%=imageLocation%>" WIDTH=200 HEIGHT="90" BORDER="0">   
			<%
			    }
			%>
			
		</td>

		<td width="20%" style="text-align: right;" height="0">
			<%
			    if (request.getSession().getAttribute("userStatus") != null)
			    {
			%> <a
			href="<s:url value="LogoutAction.action"/>"><s:text
					name="header.logout" /></a> <%
     }
 %>&nbsp;&nbsp;&nbsp;
		</td>
	</tr>
</table>