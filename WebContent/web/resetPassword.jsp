<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<sx:head />

<script type="text/javascript">

// Form validation code will come here.
function validate()
{
	//	Empty Values check starts
	
   if( document.employeeForm.currentPassword.value == "" )
   {
     alert( "Please Enter Current Password" );
     document.employeeForm.currentPassword.focus() ;
     return false;
   } 
   
   if( document.employeeForm.newpassword.value == "" )
   {
     alert( "Please Enter New Password" );
     document.employeeForm.newpassword.focus() ;
     return false;
   }
   
   if( document.employeeForm.reenterNewpassword.value == "" )
   {
     alert( "Please Confirm New Password" );
     document.employeeForm.reenterNewpassword.focus() ;
     return false;
   }
   
   if( !(document.employeeForm.newpassword.value == document.employeeForm.reenterNewpassword.value) )
   {
     alert( "Passwords do not match. Please reenter the passwords" );
     document.employeeForm.newpassword.value="";
     document.employeeForm.reenterNewpassword.value = "";
     document.employeeForm.newpassword.focus() ;
     return false;
   }
   
      return( true );
}

</script>


<table align="center" class="errortableoutline">
<tr><td><font color="red"><s:actionerror /></font></td></tr></table>


<s:form action="/ResetPasswordAction.action" method="post"
	theme="simple" onsubmit="return(validate());" name="employeeForm" >
	
<table width="50%" border="0" cellspacing="3" cellpadding="3" align="center"
	height="100%">
	<tr height="35" />
	
	<tr>
		<td width="50%" align="right" class="fieldname"><s:text name="employee.currentpassword.label"/> : </td>
		<td width="50%"><s:password name="currentPassword"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>					
	</tr>
	<tr height="35" />
	
	<tr><td width="50%" align="right" class="fieldname"><s:text name="employee.newpassword.label"/> : </td>
		<td width="50%"><s:password name="newpassword"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>					
	</tr>
	
	<tr height="15" />
			
	<tr><td width="50%" align="right" class="fieldname"><s:text name="employee.reenterNewpassword.label"/> : </td>
		<td width="50%"><s:password name="reenterNewpassword"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>					
	</tr>

	<tr height="35" />

	<tr >
		<td align="center"><button type="submit">
				<s:text name="button.resetPassword" />
			</button></td>
	</tr>
</table>
</s:form>

<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("addCompanyTab").style.backgroundColor = "#A9A9F5";
</script>


