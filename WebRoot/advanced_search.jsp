<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*" %>
<!DOCTYPE html public "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/Transitional.dtd">
<HTML>
<HEAD>
<title>Search</title>
<META http-equiv="Content-Type" content="text/html;">
<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">
 <%response.setDateHeader("Expires",System.currentTimeMillis());%>
</HEAD>
  
<body>
    
<div class="page">

<table width="100%" border="0" cellspacing="0" cellpadding="0" bgcolor="#A90101">
		<tr bgcolor="#A90101">
				<td width="211" height="24" align="left"><a href="http://www.cancer.gov"><img src="images/brandtype.gif" width="211" height="24" border="0"></a></td>
				<td>&nbsp;</td>
				<td width="307" height="24" align="right"><a href="http://www.cancer.gov"><img src="images/tagline_nologo.gif" width="307" height="24" border="0"></a></td>

		</tr>
</table><div style="margin-bottom:0px;border-left:1px solid #000000;border-right:0px;border-top:1px solid #000000;border-bottom:0px solid #000000; height:45px;"><img src="images/ispyHead.jpg" width="749px" height="45px" /></div>



<div class="main">

 <SCRIPT language="JavaScript" src="images/calendar.js" >

</script>
<SCRIPT>
function goThere(the_date)
{  
  setDateField(document.main.process_date_start); 
  top.newWin = window.open('calendar.html', 'cal', 'dependent=yes, width=210, height=230, screenX=200, screenY=300, titlebar=yes');
}
function goThere2(the_date)
{  
  setDateField(document.main.process_date_end); 
  top.newWin = window.open('calendar.html', 'cal', 'dependent=yes, width=210, height=230, screenX=200, screenY=300, titlebar=yes');
}
</SCRIPT>




    <FORM Name="main" method="POST" action="Search">

	
		<div id="navcontainer">
<ul id="navlist">
<li><a href="search.jsp">Simple Search Mode</a></li>
<li><a href="advanced_search.jsp" id="current">Advanced Search Mode</a></li>
</ul>
</div> 
			
		<TABLE class="data" width="75%" cellpadding="5" cellspacing="0" border="0">
		
		 <tr>
						<td colspan="5" class="formLabel"><b>Sample</b>
						</td>
						</tr>
						<tr>
						<td colspan="5">
						</td>
						</tr>

			<TR>
			<TD class="blbo">			LabTrak ID
			</TD>
			<TD>
				<INPUT name="core_sample_Id" class="inps" type="TEXT" size="30" maxlength="255" value="All">
			</TD>
		</TR>

  	  	<TR>
			<TD class="blbo">Institution(Core Collected)<br><br><font size=-2>(<I>Press the Ctrl Key to select more than one option</I>)</font></Td>
			<td>
				<%= Lookup.getMultiSelectInstitute("required_institution")%>
			</td>
		</TR>

		<TR>
			<TD class="blbo">TimePoints<br><br><font size=-2>(<I>Press the Ctrl Key to select more than one option</I>)</font>
			</TD>
			<td>
				<%= Lookup.getMultiSelectTimePoints("timePoint")%>
			</td>
		</TR>

		<TR>
			<TD colspan="2">I-SPY patient ID <br>(range 1001-1244): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;From&nbsp;&nbsp;&nbsp;<INPUT NAME="accrualfrom" class="inps" TYPE=TEXT SIZE=4 MAXLENGTH=10 value="">
			&nbsp;&nbsp;To&nbsp;&nbsp;&nbsp;<INPUT NAME="accrualto" class="inps" TYPE=TEXT SIZE=4 MAXLENGTH=10 value="">
			</TD>
		</TR>
 <tr> 
						<td>Process Date Range:</td>
						<td>From <INPUT NAME="process_date_start" class="inps" TYPE=TEXT SIZE=10 MAXLENGTH=15 readonly="true" value="">&nbsp;&nbsp;
					
						 <A href="javascript:goThere(document.main.process_date_start)">
							   <IMG src="images/calendar2.gif" border="0"></A>
						
						<br>
						To     &nbsp;&nbsp;&nbsp;&nbsp;<INPUT NAME="process_date_end" class="inps" TYPE=TEXT SIZE=10 MAXLENGTH=15 readonly="true" value="">&nbsp;&nbsp;
					
						 <A href="javascript:goThere2(document.main.process_date_end)">
							   <IMG src="images/calendar2.gif" border="0"></A>
						
						</td>
						
		</tr>
		
		<tr>
					<td>Core Type:</td>
					<td>					
						<%= Lookup.getCoreTypes("coreType",1,null, null)%>
					</td>
						</tr>
		<tr>
						<td colspan="5"><hr noshade>
						</td>
						</tr>
						<tr>
						<td colspan="5" class="formLabel"><b>Frozen Core</b>
						</td>
						</tr>
						<tr>
						<td colspan="5">
						</td>
						</tr>
						<tr>
					<td>Touch Prep:</td>
						<td>						
						  <SELECT name="frozen_Touch_Prep" size="1" >
<OPTION value="">
<OPTION value="+">+ (Malignant Cells Present)
<OPTION value="+/-">+/- (Sugestive of Malignant Cells)
<OPTION value="-" >- (No Malignant Cells Present)
</SELECT>
</tr> 
						<tr>
					<td>H&E Tumor Review:</td>
					
					       <td>
							<SELECT name="frozen_H_E" size="1" >
<OPTION value="">
<OPTION value="+">+ (Cancer Present)
<OPTION value="-" >- (No Cancer Present)
</SELECT>

						   </td>
						
  
</tr>

		<TR>
			<TD class="blbo">				Tumor Density
			</TD>
			<TD><SELECT class="inps" name="Tpchoice1">
				  <OPTION selected></OPTION> 
				  <OPTION>&gt;</OPTION> <OPTION>&gt;=</OPTION> 
				  <OPTION>&lt;</OPTION> <OPTION>&lt;=</OPTION> 
				  <OPTION>=</OPTION></SELECT>
				<INPUT name="tumorPresence" type="TEXT" size="30" maxlength="255">%
			</TD>
		</TR>
		
		<tr>
						<td>Non-Tumor Nucleated Cells</td>
						<td>
						<SELECT class="inps" name="Tpchoice2">
						  <OPTION selected></OPTION> 
						  <OPTION>&gt;</OPTION> <OPTION>&gt;=</OPTION> 
						  <OPTION>&lt;</OPTION> <OPTION>&lt;=</OPTION> 
						  <OPTION>=</OPTION></SELECT>
								  <INPUT NAME="nontumor" class="inps" TYPE=TEXT SIZE=30 MAXLENGTH=50 >%</td>
						</tr> 	
				  
		         <tr>
						
					<td>Lab-based Search:</td>
					
					       <td>
							<SELECT name="lab" size="1">
							<OPTION value="">
							<OPTION value="2"> CGH Lab
							<OPTION value="3"> P53 PCR Lab
							<OPTION value="4" >Gene Expression Lab
							<OPTION value="5" >Proteomics Lab
							</SELECT>

						   </td>			
  
                   </tr>				
				  
						<tr>
						<td colspan="5"><hr noshade>
						</td>
						</tr>
						 <tr>  
						 <tr>
						<td colspan="5" class="formLabel"><b>Paraffin Core</b>
						</td>
						</tr>
						
						<tr>
					    <td>H&E Review (Top/Bottom):</td>
							<td>
							<SELECT name="H_E" size="1" >
							<OPTION value="">
							<OPTION value="1">(+/+) =Inv. Cancer Present on Top & Bottom
							<OPTION value="2">(-/-) =Inv.Cancer not present on Top or Bottom
							<OPTION value="3">(+/-) =Inv.Cancer Present on Top but not Bottom
							<OPTION value="4">(-/+) =Inv.Cancer not present on Top, but present on Bottom
							<OPTION value="6">(+,+/-) = Inv.Cancer present on Top, minimal on Bottom
							<OPTION value="7">(+/-,+) = Inv.Cancer minimal on Top, present on Bottom
							<OPTION value="8">(+/-,-) = Inv.Cancer minimal on Top, absent on Bottom
							<OPTION value="9">(-,+/-) = Inv.Cancer absent on Top, minimal on Bottom
							<OPTION value="10">(+/-, +/-) = Inv.Cancer minimal on Top and on Bottom
							</SELECT>

							</td>
					  </tr>		
						
						<!---  <tr>
						 
					    <td>H&E Review (Top/Bottom):</td>
							<td>
							<SELECT name="H_E" size="1" >
							<OPTION value="">
							<OPTION value="1">+ (Top) / + (Bottom)
							<OPTION value="2">- (Top) / - (Bottom)
							<OPTION value="3">+ (Top) / - (Bottom)
							<OPTION value="4">- (Top) / + (Bottom)
							<OPTION value="5">Blank
							</SELECT>

							</td>
					  </tr>		 --->
					  
					   <tr>
					     <td>H&E Usability:</td>
						 <td>
						 <SELECT name="usability" size="1" >
						<OPTION value="">
						<OPTION value="1">Yes
						<OPTION value="2">No
						<OPTION value="3">Blank
						</SELECT>

						</td>
						  </tr>	 
						  
						  <tr>
					     <td>Touch Preps Received:</td>
						 <td>
						 <SELECT name="touchPreps" size="1" >
							<OPTION value="">
							<OPTION value="1">Yes
							<OPTION value="2">No
							<OPTION value="3">Blank
							</SELECT>

						</td>
						  </tr>	 				

						</table> 
						
					
						
						<hr size="1" width="100%" color="black" />
		
	<TABLE class="data"  width="75%" cellpadding="5" cellspacing="0" border="0">
	
	<tr>
		
						 <tr>
						<td colspan="5" class="formLabel"><b>DNA or RNA Quality <br><br><font size=-2>(<I>Press the Ctrl Key to select more than one option</I>)</font></b>
						</td>
						</tr>
						<tr>
						<td colspan="5">
						</td>
						</tr>
						
						<tr>
					<td>Select DNA Quality(260/280 ratio) </td>
					<td>
				          <SELECT class="inps"  name="dnaQuality" MULTIPLE>
						        <OPTION value="-1" >
								<OPTION value="0" >All
								<OPTION value="1">Good (ratio of 1.7 - 2.0 for 260/280 absorbance)
								<OPTION value="2">Fair (ratio of 1.6 - 1.7)
								<OPTION value="3">Poor (ratio that is either < 1.6 or > 2.0)															
							</SELECT>
							</td>
						</tr>
						<tr>
					<td>Select Multiplex PCR Rating</td>					
					<td>
				          <SELECT class="inps"  name="pcr_rating" MULTIPLE >
						        <OPTION value="-1" >
								<OPTION value="0" >All
								<OPTION value="Worst">0 (Worst)
								<OPTION value="Poor">1 (Poor)
								<OPTION value="Good">2 (Good)
								<OPTION value="Best">3 (Best)
							</SELECT>
							</td>
						</tr>
						<tr>
						<tr>
					<td>Select RNA Quality(260/280 ratio)</td>
					<td>
				          <SELECT class="inps"  name="rnaProcessQuality" MULTIPLE>
						       <OPTION value="-1" >
								<OPTION value="0" >All
								<OPTION value="1">Good (ratio of 1.7 - 2.0 for 260/280 absorbance)
								<OPTION value="2">Fair (ratio of 1.6 - 1.7)
								<OPTION value="3">Poor (ratio that is either < 1.6 or > 2.0)															
							</SELECT>
							</td>
						</tr>
					<td>Select RNA Analysis Quality  </td>
					<td>
				            <SELECT class="inps" name="rnaAnalysisQuality" MULTIPLE>
							 <OPTION value="-1" >
								<OPTION value="0" >All
								<OPTION value="Good">Good (Discernable 18s and 28s peaks and no degradation of RNA sample)
								<OPTION value="Fair">Fair (Discernable peaks with substantial degradation)
								<OPTION value="Poor">Poor (No discernable peaks)
															
							</SELECT>
							</td>
						</tr>
						
						<tr>
						<td colspan="5"><hr noshade>
						</td>
						</tr>	
						 <tr>
						<td colspan="5" class="formLabel"><b>DNA Labeling Intensity and CGH Quality <br><br><font size=-2>(<I>Press the Ctrl Key to select more than one option</I>)</font></b>
						</td>
						</tr>
						<tr>
						<td colspan="5">
						</td>
						</tr>
						
						<TR>
			<TD>Select Intensity for Labeling DNA</Td>
			<td>
				<SELECT name="intensity" MULTIPLE >
				 <OPTION value="-1" >
                 <OPTION value="0" >All
									<OPTION value="1">High
									<OPTION value="2">Medium
									<OPTION value="3">Low
									</SELECT>

			</td>
		</TR>
						<!--- <tr>
					          <td>Select Intensity for Labeling DNA:</td>
					          <td>
						            <SELECT name="intensity" size="1" >
									
									<OPTION value="0" selected>All
									<OPTION value="1">High
									<OPTION value="2">Medium
									<OPTION value="3">Low
									</SELECT>
								</td>
						</tr> --->
						
						<TR>
			<TD>Select CGH Hybridization Quality</Td>
			<td>
				<SELECT name="cgh_quality" MULTIPLE >
				 <OPTION value="-1" >
<OPTION value="0" >All
									 
                                    <OPTION value="1">Good, Acceptable( score >=3)
                                    <OPTION value="2">Poor (score <3)
									</SELECT>

			</td>
		</TR>
					<!--- 	<tr>
					          <td>Select CGH Hybridization Quality:</td>
					          <td>
						           <SELECT name="cgh_quality" size="1" >
                                    <OPTION value="0" selected>All
                                    <OPTION value="1">Good, Acceptable( score >=3)
                                    <OPTION value="2">Poor (score <3)
                                   </SELECT>
								</td>
						</tr> --->
					<tr>
						<td colspan="5"><hr noshade>
						</td>
						</tr>	
		<tr>
		
						 <tr>
						<td colspan="5" class="formLabel"><b>Protein Quality</b>
						</td>
						</tr>
						<tr>
						<td colspan="5">
						</td>
						</tr>
		<tr>
			<td>Enter Microdissection Efficiency of Capture:	</td>		
			 <TD>
			 <SELECT class="inps" name="Tpchoice3">
				  <OPTION selected></OPTION> 
				  <OPTION>&gt;</OPTION> <OPTION>&gt;=</OPTION> 
				  <OPTION>&lt;</OPTION> <OPTION>&lt;=</OPTION> 
				  <OPTION>=</OPTION></SELECT>
			    <INPUT name="efficiency" type="TEXT" size="34" maxlength="50" value="" >%
			 </TD>
		</tr>
						
		
		<tr>
					<td>Tumor Presence:</td>
					<td><SELECT class="inps" name="Tpchoice4">
					  <OPTION selected></OPTION> 
					  <OPTION>&gt;</OPTION> <OPTION>&gt;=</OPTION> 
					  <OPTION>&lt;</OPTION> <OPTION>&lt;=</OPTION> 
					  <OPTION>=</OPTION></SELECT>	
					<INPUT name="tumor_presence" type="TEXT" size="34" maxlength="50" value="">%	</td>
		</tr>
</TABLE>
			<table class="tbuttons" width="75%">		
			<tr><td>&nbsp;</td></tr>	
		<TR>
			
			<TD>
			    <INPUT type="submit" class="buttons" name="search" value="   Search   ">				
			   
				<INPUT type="RESET" class="buttons" value="Clear">
			</TD>
		</TR>
	</TABLE>
	  
	 
	
</FORM>


</div>
<p class="note"><a href="select.jsp">Home</a> | <a href="#top">Top of the page</a></p>
</div>

<%@ include file="footer.jsp"%>

