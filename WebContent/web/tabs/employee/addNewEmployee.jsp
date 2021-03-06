<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<sx:head />

<script type="text/javascript">

function validate()
{
	//	Empty Values check starts
	
   if( document.employeeForm.employeeID.value == "" )
   {
     alert( "Please Enter Employee ID" );
     document.employeeForm.employeeID.focus() ;
     return false;
   } 
   
   if( document.employeeForm.employeePassword.value == "" )
   {
     alert( "Please Enter Employee Password" );
     document.employeeForm.employeePassword.focus() ;
     return false;
   }
   
   if( document.employeeForm.employeeFirstName.value == "" )
   {
     alert( "Please Enter Employee First Name" );
     document.employeeForm.employeeFirstName.focus() ;
     return false;
   }
   
   if( document.employeeForm.employeeLastName.value == "" )
   {
     alert( "Please Enter Employee Last Name" );
     document.employeeForm.employeeLastName.focus() ;
     return false;
   }
   
   if( document.employeeForm.geoID.value == "" )
   {
     alert( "Please Enter Geo ID" );
     document.employeeForm.geoID.focus() ;
     return false;
   }
   
   if( document.employeeForm.pinCode.value == "" ||
           isNaN( document.employeeForm.pinCode.value ) ||
           document.employeeForm.pinCode.value.length != 6 )
	   {
	   alert( "Please Enter pin code in numeric format ######." );
	      document.employeeForm.pinCode.focus() ;
	      return false;
	   }
   
   if( document.employeeForm.contactNo1.value == "" ||
	   isNaN( document.employeeForm.contactNo1.value ))	   
   {
     alert( "Please Enter Contact No 1 in numeric format" );
     document.employeeForm.contactNo1.focus() ;
     return false;
   }
   
   if( document.employeeForm.contactNo2.value == "" ||
	   isNaN( document.employeeForm.contactNo2.value ))
   {
     alert( "Please Enter Contact No 2 in numeric format" );
     document.employeeForm.contactNo2.focus() ;
     return false;
   }
   
	//	Empty Values check ends
   
   return( true );
}

</script>

<s:form action="/AddNewEmployeeAction.action" method="post"
	theme="simple" onsubmit="return(validate());" name="employeeForm" >
	
<table width="100%" border="0" cellspacing="3" cellpadding="3" align="center"
	height="100%">
	<tr height="35" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.employeeID.label"/> : <s:textfield name="employeeID"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.password.label"/> : </td>
		<td width="31%"><s:password name="employeePassword"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.firstName.label"/> : <s:textfield name="employeeFirstName"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.lastName.label"/> : </td>
		<td width="31%"><s:textfield name="employeeLastName"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.address.label"/> : <s:textarea name="employeeAddress"
					cols="50" rows="3" /></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.city.label"/> : </td>
		<td width="31%"><s:textfield name="employeeCity"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.country.label"/> : <s:textfield name="employeeCountry"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.pinCode.label"/> : </td>
		<td width="31%"><s:textfield name="pinCode"
					size="20" maxlength="6" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.contactNumber.label"/> : <s:textfield name="contactNo"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.roleIndex.label"/> : </td>
		<td width="31%"><s:textfield name="roleIndex"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="45%" align="right" class="fieldname"><s:text name="employee.companyID.label"/> : <s:textfield name="companyID"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="employee.locale.label"/> : </td>
		<td width="31%"><s:textfield name="employeeLocale"
					size="20" maxlength="20" value="" /></td>
	</tr>
	<tr height="35" />
	
	<tr >
		<td colspan="4" align="center"><button type="submit">
				<s:text name="button.addEmployee"/>
			</button></td>
	</tr>
	<tr height="15" />
</table>
</s:form>

<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("employeeTab").style.backgroundColor = "#A9A9F5";
</script>



