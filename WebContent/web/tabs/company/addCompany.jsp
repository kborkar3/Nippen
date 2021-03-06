<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<sx:head />

<script type="text/javascript">

// Form validation code will come here.
function validate()
{
	//	Empty Values check starts
	
   if( document.companyForm.companyID.value == "" ||
   isNaN( document.companyForm.companyID.value ) )
   {
     alert( "Please Enter Company ID" );
     document.companyForm.companyID.focus() ;
     return false;
   } 
   
   if( document.companyForm.companyName.value == "" )
   {
     alert( "Please Enter Company Name" );
     document.companyForm.companyName.focus() ;
     return false;
   }
   
   if( document.companyForm.companyAddress.value == "" )
   {
     alert( "Please Enter Company Address" );
     document.companyForm.companyAddress.focus() ;
     return false;
   }
   
   if( document.companyForm.companyCity.value == "" )
   {
     alert( "Please Enter Company City" );
     document.companyForm.companyCity.focus() ;
     return false;
   }
   
   if( document.companyForm.geoID.value == "" )
   {
     alert( "Please Enter Geo ID" );
     document.companyForm.geoID.focus() ;
     return false;
   }
   
   if( document.companyForm.pinCode.value == "" ||
           isNaN( document.companyForm.pinCode.value ) ||
           document.companyForm.pinCode.value.length != 6 )
	   {
	   alert( "Please Enter pin code in numeric format ######." );
	      document.companyForm.pinCode.focus() ;
	      return false;
	   }
   
   if( document.companyForm.contactNo1.value == "" ||
	   isNaN( document.companyForm.contactNo1.value ))	   
   {
     alert( "Please Enter Contact No 1 in numeric format" );
     document.companyForm.contactNo1.focus() ;
     return false;
   }
   
   if( document.companyForm.contactNo2.value == "" ||
	   isNaN( document.companyForm.contactNo2.value ))
   {
     alert( "Please Enter Contact No 2 in numeric format" );
     document.companyForm.contactNo2.focus() ;
     return false;
   }
   return( true );
}

</script>


<table align="center" class="errortableoutline">
<tr><td><font color="red"><s:actionerror /></font></td></tr></table>


<s:form action="/AddNewCompanyAction.action" method="post"
	theme="simple" onsubmit="return(validate());" name="companyForm" enctype="multipart/form-data">
	
<table width="100%" border="0" cellspacing="3" cellpadding="3" align="center"
	height="100%">
	<tr height="35" />
	
	<tr>
		<td width="50%" align="right" class="fieldname" class="fieldname"><s:text name="companyID.label"/> : <s:textfield name="companyID"
					size="20" maxlength="20" value="%{companyID}" readonly="true" onkeypress="return RestrictSpace(event);" /></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="companyName.label"/> : </td>
		<td width="31%"><s:textfield name="companyName"
					size="20" maxlength="20" value=""/></td>					
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="50%" align="right" class="fieldname"><s:text name="companyAddress1.label"/> : <s:textarea name="companyAddress"
					cols="50" rows="3" /></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="companyCity.label"/> : </td>
		<td width="31%"><s:textfield name="companyCity"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>					
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="50%" align="right" class="fieldname"><s:text name="geoId.label"/> : <s:textfield name="geoID"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="pinCode.label"/> : </td>
		<td width="31%"><s:textfield name="pinCode"
					size="20" maxlength="6" value="" onkeypress="return RestrictSpace(event);"/></td>
										
	</tr>
	<tr height="15" />
	
	<tr>
		<td width="50%" align="right" class="fieldname"><s:text name="contactNumber1.label"/> : <s:textfield name="contactNo1"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"><s:text name="contactNumber2.label"/> : </td>
		<td width="31%"><s:textfield name="contactNo2"
					size="20" maxlength="20" value="" onkeypress="return RestrictSpace(event);"/></td>
	</tr>
	
	<tr>
		<td width="50%" align="right" class="fieldname"><s:text name="companyLogo.label"/> : <s:file name="companyLogo" label="Select a File to upload" size="40" /></td>
					<td width="10%"/>
		<td width="9%" align="right" class="fieldname"></td>
		<td width="31%"></td>
										
	</tr>
	<tr height="15" />
	
	
	<tr height="35" />
	
	<tr >
		<td colspan="4" align="center"><button type="submit">
				<s:text name="button.addCompany" />
			</button></td>
	</tr>
	<tr height="15" />
</table>
</s:form>

<script>
    document.getElementById("settingsTab").style.textDecoration = "underline";
	document.getElementById("settingsTab").style.backgroundColor = "#9DB1ED";
	document.getElementById("addCompanyTab").style.backgroundColor = "#A9A9F5";
</script>


