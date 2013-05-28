<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<HTML>
<HEAD>
<TITLE>Submit Entry</TITLE>
<META http-equiv="Content-Type" content="text/html;">



<LINK REL=StyleSheet HREF="css/qiStyle.css" TYPE="text/css">
 <%response.setDateHeader("Expires",System.currentTimeMillis());%>
</HEAD>

<BODY>
   
 <%@ page import="gov.nih.nci.qi.*, gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*"%>
   <%@ page import="java.net.*,java.util.*"%>     
   
  <% 
    Qi_sample sample = new Qi_sample();
	
	
	 String userId = null;
	 userId =  (String) request.getParameter("submitterId");
	 Long submitterKey = null;
	
     QISession mySession = null;
	 String submitterId = null; 
   
	
		response.setHeader("Cache-Control","no-cache");
	    response.setHeader("Pragma","no-cache");
	    response.setDateHeader("Expires",System.currentTimeMillis());
		if((QISession) session.getAttribute("nci.mmhcc.mmhccSession") != null){		
	   	    mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");
		    Enumeration submitters = mySession.getObjectID("SUBMITTER");
	        while(submitters.hasMoreElements()){
	           //as there is only one submitter for now
		        submitterKey = (Long) ((Label)submitters.nextElement()).getId();
		        
		      }
   
            if(submitterKey != null){   
                submitterId = submitterKey.toString();
	           
                  }				 
		   }   
	 
	 
	 
	 
	 
    Long sampleId = null;
	String  labtrak_id = null;
	if( request.getParameter("sampleID") != null){	   
	   sampleId = new Long((String) request.getParameter("sampleID"));
	}
	if(request.getParameter("core_sampeId") != null){	
	   labtrak_id =(String) request.getParameter("core_sampeId");
	}
	if(sampleId != null && mySession != null){		
	
		sample.retrieveByKey(sampleId);		
		mySession.setSampleId(sampleId);
		mySession.setLabtrak_id(sample.getLabtrak_id());
		mySession.clearAll();	
		mySession.populateRecords(sampleId);
		
		
	}
	if(sampleId == null && mySession != null){	 
	   
		//qi
		sampleId = mySession.getSampleId();
		labtrak_id = mySession.getLabtrak_id();
		submitterId = mySession.getSubmitterUID().toString();
		//end of qi
	   
		mySession.clearAll();	
		mySession.populateRecords(sampleId);	
		}
	
%>  
<%	
	
	 if( submitterId == null){
	     submitterId = (String)session.getAttribute("nci.mmhcc.resubmit_key");
	   }
	
	 
	%>
	<A name="#top"></a>	
<div class="page">


<%@ include file="header.html"%> 

<div class="main">
<h2 class="title">
   	  Continuing Submission Process</h2>
   	

	<A name="# top">	
	<%if(userId == null && submitterId != null){%>	
   <FORM method="POST" action="exit.jsp?sampleId=<%=sampleId%>&submitterId=<%=submitterId%>">
   <%}
   else if( userId != null ){%>
   <FORM method="POST" action="exit.jsp?sampleId=<%=sampleId%>&submitterId=<%=userId%>">
   <%}
   else {%>
    <FORM method="POST" action="exit.jsp">
   <%}%>

   <input type="hidden" name="labtrak_id" value="<%=labtrak_id%>">
					<table width="95%" class="data" border="0"  align="center" cellpadding="3" cellspacing="3">
					<tr>
					<td align="left" valign="top">
				<b>This page displays an overview of the data entered for this sample</b>.<br>
				<p><ul>
				<li>To enter sub-sample data select a category and click on the category's hyperlink e.g. <b>&quot;Enter DNA Quality Indicator&quot;</b>.<br>
				Upon completion and saving the data you will return to this page and a new link identifying the entered data will appear under the category link.</li><br><br>
				<li>To modify existing data, click on the hyperlink indentifying the submitted data.</li><br><br>
<li>If you would like to enter two or more items per category, select the appropriate &quot;Enter ....&quot; link. This will open a new blank submission page e.g. DNA quality indicator submission page.</li><br><br>
				<li>To end this session, click on the <b>&quot;Exit&quot;</b> button.&nbsp;&nbsp;All entered information will be saved.&nbsp;You may return later to view or modify the data.</li>
				</ul></p>
				</td>
				<td valign="top">
				<input type="submit" class="buttons" name="submit" value="Exit">
				</td>
				</tr>
				</table>



		
				
					<table width="95%" border="1" cellspacing="0" cellpadding="5" align="center">
					<tr>
   						<td width="30%" class="label">	Category
   						</td>
   						<td class="label">	Description
   						</td>
   					</tr>
   					<tr>
   						<td class="user" colspan="2">Sample General Information
   						</td>
   						
   					</tr>
   					<tr>
   						<td><ul><li> <a href="DispatcherServlet?url=General_info.jsp&<%="id=" + sampleId%>"><%=labtrak_id%></a></ul>
   						</td>
   						<td> Click on the LabTrak ID on the left side to revise the sample information.
						</td>
   					</tr>
   					
   					
   				
					<tr>
   						<td class="user" colspan="2">Quality Indicators
   						</td>
   						
   					</tr>
   					<tr>
   						<td>	
						
						
						
						<li><a href="dna.jsp?mode=Submit">Enter DNA Quality Indicator</a>						
						<ul><%=DatabaseSetup.pupulateList(mySession, "DNA", "dna.jsp")%>
						</ul>
						<br>
						
						<li> <a href="rna.jsp?mode=Submit">Enter RNA Quality Indicator</a>
						<ul><%=DatabaseSetup.pupulateList(mySession, "RNA", "rna.jsp")%>
						</ul>	
						<br>	
						<li> <a href="protein.jsp?mode=Submit">Enter Protein Quality Indicator</a>
						<ul><%=DatabaseSetup.pupulateList(mySession, "PROTEIN", "protein.jsp")%>
						</ul>					
						
   						</td>
   						<td>	Enter quality indicators for sub-samples, e.g. DNA or RNA. <br>
If the sub-sample has more than one quality indicator to enter, submit the entries individually by selecting the <b>&quot;Enter DNA/RNA Quality Indicator&quot;</b> hyperlink twice. </td>
   					</tr>
   					
   					
   					
   					
   					
					
				
   				</table>
				<br />
				
				<table align="center" class="tbuttons" border="0" width="95%">
				<tr>
				<td><input type="submit" class="buttons" name="submit" value="Exit">   				</td></tr>
				</table>			
				<!--end wrap table-->
	</FORM>
	
	
	

	
	</div>
<p class="note"><a href="select.jsp">Home</a> | <a href="#top">Top of the page</a></p>
</div>

<%@ include file="footer.jsp"%>

  
