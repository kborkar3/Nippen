<table width="100%" border="0"  height="100%">
		<tr>
			<td align="center" class="fieldname">
			
			<%
			    String graphImagePath = (String)request.getSession().getAttribute("graphImagePath"); %>
			
			<%    if (graphImagePath != null)
			    {
			%> 
			  <img src="<%=graphImagePath%>" WIDTH=1000 HEIGHT="500" BORDER="0"> 
			<%
			    }
			%>
			</td>
			
		</tr>
</table>


  </body>
  
<script>
    document.getElementById("graphTab").style.textDecoration = "underline";
	document.getElementById("graphTab").style.backgroundColor = "#9DB1ED";
</script>