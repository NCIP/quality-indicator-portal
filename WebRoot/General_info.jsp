<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*,gov.nih.nci.qi.constants.*" %>
<%@ page info="this page is the password validation page"%> 

<%  
    gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
    SessionManager.verifySession(request,response,"Error.jsp");  
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");
 %>   

<%@ page import="gov.nih.nci.qi.*, java.lang.*,java.text.*,java.sql.*"%>
<%@ page buffer="32kb"%>
<%@ page import="java.util.*"%>       
<%@ page import="java.lang.*,java.io.*"%> 

<jsp:useBean id="sample"       scope="request" class="gov.nih.nci.qi.db.Qi_sample"       />
<jsp:useBean id="core"       scope="request" class="gov.nih.nci.qi.db.Core_type"       />
<jsp:useBean id="institution"       scope="request" class="gov.nih.nci.qi.db.Qi_institution"       />
<jsp:useBean id="h_e"       scope="request" class="gov.nih.nci.qi.db.Qi_h_e_review"       />
<jsp:useBean id="usability"       scope="request" class="gov.nih.nci.qi.db.Qi_usability"       />
<jsp:useBean id="touchPrep"       scope="request" class="gov.nih.nci.qi.db.Qi_touchprep_recvd"       />
<jsp:useBean id="frozen_Touch_Prep"       scope="request" class="gov.nih.nci.qi.db.Qi_frozen_touchprep"       />
<jsp:useBean id="frozen_H_E"       scope="request" class="gov.nih.nci.qi.db.Qi_frozen_h_e"       />





<% 
   Long submitterId  = null;
   Long sampleId = null;
   String mode = null;
   String institution_id = null;
   mode =(String)request.getParameter("mode");  
   SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
   SimpleDateFormat formatter3 = new SimpleDateFormat ("MM/yyyy");
  
 

   Long modelId      = null;   
   QISession mySession = null;
   DeleteRecord delete  = new DeleteRecord(); 
   boolean dna_rna_status = false;
   
   String id = (String) request.getParameter("id");
   String status = (String) request.getParameter("status");
   String status2 = (String) request.getParameter("status2");
   String status3 = (String) request.getParameter("status3");
   if( status2 != null && status2.equals("delete")){
      id ="";
   }
	SubmitterRole submitterRole = (SubmitterRole) session.getAttribute("nci.mmhcc.submitterrole");

	if (id != null && !(id.equals("")))
	{	 
	
	   
	   if(status3 != null){
	      sample = new Qi_sample();
	    }
		else{
	      sampleId = Long.decode(id);
		  sample.retrieveByKey(sampleId);	   
	      mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");  
		
	      if( sampleId != null ){
	        dna_rna_status =  delete.checkDNA_RNA_Protein_Status(sampleId);
	        }   
		 } 
	  }
	 else     
	 {  
		id = "";
		
		QISession originalSession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");
		submitterId = originalSession.getSubmitterUID();
		mySession   = new QISession(submitterId);
		
		session.removeAttribute("nci.mmhcc.mmhccSession");
		session.setAttribute("nci.mmhcc.mmhccSession",mySession);	
		
		sampleId = null;	
		
		if(mode != null && mode.equals("Submit")){
		 sample = new Qi_sample();
		}
		
	 }
	
%>

<%@ include file="General_info.html"%>


<%-- setProperty methods --%>
<jsp:setProperty name="sample" property="sample_generating_institute_id"         param="required_institution"/>
<jsp:setProperty name="sample" property="corenumber"      param="coreNo"/>
<jsp:setProperty name="sample" property="patient_accrual"      param="required_accrual"/>
<jsp:setProperty name="sample" property="patientdid"      param="patientdid"/>
<jsp:setProperty name="sample" property="timepoint_name"      param="required_timePoint"/>
<jsp:setProperty name="sample" property="core_type" 	   param="required_coreType"/>
<jsp:setProperty name="sample" property="labtrak_id" param="required_core_sample_Id"/>  
<jsp:setProperty name="sample" property="tumorpresence" param="tumorPresence"/>
<jsp:setProperty name="sample" property="nontumor" param="nontumor"/>
<jsp:setProperty name="sample" property="qi_comment" param="comment"/>
<jsp:setProperty name="sample" property="h_e_review" param="H_E"/>
<jsp:setProperty name="sample" property="usability" param="usability"/>
<jsp:setProperty name="sample" property="touchprep_recvd" param="touchPreps"/>
<jsp:setProperty name="sample" property="frozen_h_e" param="frozen_H_E"/>
<jsp:setProperty name="sample" property="frozen_touch_prep" param="frozen_Touch_Prep"/>
<jsp:setProperty name="sample" property="dna_reading" param="dnaReading"/>
<jsp:setProperty name="sample" property="dna_quantity_unit" param="dna_unit"/>
<jsp:setProperty name="sample" property="dna_instrument_id" param="dna_reading_instrument"/>
<jsp:setProperty name="sample" property="dna_extraction_quality" param="dnaQuality"/>
<jsp:setProperty name="sample" property="rna_reading" param="rnaReading"/>
<jsp:setProperty name="sample" property="rna_quantity_unit" param="rna_unit"/>
<jsp:setProperty name="sample" property="rna_instrument_id" param="rna_reading_instrument"/>
<jsp:setProperty name="sample" property="rna_extraction_quality" param="rnaQuality"/>



<%   

Long userTypeKey =  null;
boolean status1  = false; 

boolean update   = false;
boolean isDateMissing = false;
String thePage = "General_info.jsp";
session.setAttribute("gov.nih.nci.qi.constaint",thePage);



Submitter user = new Submitter();
Vector users   = user.retrieveAllWhere(" SUBMITTERKEY = "+ submitterId);
for(int i=0; i<users.size();i++)
{
   user = (Submitter)users.elementAt(i);
   mySession.setObjectID("SUBMITTER",submitterId,user.getUsername() + "," + user.getPassword());
}



    
if(mySession != null)
{
	Enumeration submitters = mySession.getObjectID("SUBMITTER");
	while(submitters.hasMoreElements())
	{ 	//as there is only one submitter for now
		submitterId = (Long) ((Label)submitters.nextElement()).getId();		
	}
}
	
     

boolean deleteStatus = false;	

if( status != null && status.equals("delete")){
      id ="";
   }
if(request.getParameter("delete_data") != null && id != null && !id.equals("")){  
 if(dna_rna_status ==false ){        
       String url="DispatcherServlet?url=General_info.jsp&"+"id=" + sampleId+"&status=delete" ;%>   
       <jsp:forward page="<%=url%>"/>
   <%}
  
 if(dna_rna_status == true){     
    
     deleteStatus = delete.deleteSample(sampleId);
     if(deleteStatus){	   
	   String url="General_info.jsp?mode=Submit&status2=delete"; %> 	  
        <jsp:forward page="<%=url%>"/>
	 <%
       }  
	 }
	 
 }

if (request.getParameter("submit_sample") != null)
{	
	boolean flag = DatabaseSetup.checkMissingRequiredFields(request);
	if(flag)
	{
	   
	   System.err.println("yes missing");		  
		  
	   // end saving the data in the session
	   		
	   Vector missing_feilds = (Vector) request.getAttribute("gov.nih.nci.qi.MissingFeilds");
	   if(missing_feilds == null)
	   {
		missing_feilds = new Vector();
	   }   
	   flag = true;
	   request.setAttribute("gov.nih.nci.qi.MissingFeilds",missing_feilds);
	}   
	
	System.err.println(flag);    
	
	if (flag)
	{ 
	   %>
	   <jsp:forward page="missing_required_feilds.jsp"/>
	<%}
	  

	ParsePosition pos = new ParsePosition(0);	
	java.util.Date currentTime_1 = new java.util.Date();
	String dateString  = formatter.format(currentTime_1);	
	java.util.Date timestamp = formatter.parse(dateString,pos);	
    Timestamp ts  = new Timestamp(timestamp.getTime());	 		
	sample.setTimestamp(ts);	 	
	
	
   String processDate = null;    
   ParsePosition pos2 = new ParsePosition(0);	
   processDate = (String) request.getParameter("process_date");   
   if(processDate != null && !processDate.equalsIgnoreCase("")){
	   processDate.trim();   			
	   java.util.Date pDate = formatter.parse(processDate,pos2);  
	   Timestamp ts2  = new Timestamp(pDate.getTime());					
	   sample.setProcess_date(ts2);	
   }
   
   
    if(request.getParameter("dna_process_date") != null && !request.getParameter("dna_process_date").equals(""))	{
			   String dna_processDate = null;    
			   ParsePosition pos3 = new ParsePosition(0);	
			   dna_processDate = (String) request.getParameter("dna_process_date");			 
			   processDate.trim();			     			
			   java.util.Date dnaDate = formatter.parse(dna_processDate,pos3);			  
			   Timestamp ts3  = new Timestamp(dnaDate.getTime());					
			   sample.setDna_process_date(ts3);				   
			  
			 }
  if(request.getParameter("rna_process_date") != null && !request.getParameter("rna_process_date").equals(""))	{
			   String rna_processDate = null;    
			   ParsePosition pos4 = new ParsePosition(0);	
			   rna_processDate = (String) request.getParameter("rna_process_date");			 
			   processDate.trim();			     			
			   java.util.Date rnaDate = formatter.parse(rna_processDate,pos4);			  
			   Timestamp ts4 = new Timestamp(rnaDate.getTime());					
			   sample.setRna_process_date(ts4);				   
			  
			 } 	  

  String collectDate = null;    

   /*ParsePosition pos3 = new ParsePosition(0); 

   collectDate = (String) request.getParameter("core_date");
   collectDate.trim();				
   java.util.Date cDate = formatter3.parse(collectDate,pos3);
   Timestamp ts3  = new Timestamp(cDate.getTime());
   sample.setCore_collect_date(ts3);  
  */

   

   sample.setCore_collect_date(null);	
   institution_id = (String)request.getParameter("required_institution");
   Qi_sample institutionSample = new Qi_sample();
   Vector institutionSamples = new Vector();
			
	if(sampleId != null) // just update record
	 {		
		sample.setSample_id(sampleId);		
		
		if(institution_id != null && institutionSamples.size()>1 && !institution_id.equals(sample.getSample_generating_institute_id())){
		  
		  %>
			 <jsp:forward page="institutionSelectionError.jsp"/>						 
		<% }
		if(mySession != null){
		   mySession.setSample(sample.getLabtrak_id(),sampleId);	
		 }
		try{
		   
			session.setAttribute("gov.nih.nci.qi.id",id);
			status1 = sample.updateByKey();
			update = true;
		  }
		catch(Exception e){%>
			<jsp:forward page="constraintError.jsp"/>	
			<%}
	  }
	else 
	{
		System.err.println("mySession:"+ mySession); 
		if(mySession != null)
		{
			sampleId = mySession.getDatabaseUID("QI_SAMPLE");				
			if(sampleId != null)				
			
			{	
			    				
				 sample.setSample_id(sampleId);	
				 String patAccrual=(String)request.getParameter("required_accrual");				
				 institutionSamples = institutionSample.retrieveAllWhere(" patient_accrual="+"'"+patAccrual+"'");
		          for(int q=0; q<institutionSamples.size();q++){
		             institutionSample = (Qi_sample)institutionSamples.elementAt(q);
				     if(institution_id != null && !institution_id.equals(institutionSample.getSample_generating_institute_id().toString())){
				       
					    %>
					     <jsp:forward page="institutionSelectionError.jsp"/>						 
		               <% 
					 }
		           }
				 			
				
				if(submitterId != null){	              
					sample.setSubmitterkey(submitterId);
				}
				try{
				   
				   status1 = sample.insert();
				   }
				catch(Exception e){
				  
				   %>
				   <jsp:forward page="constraintError.jsp"/>	
				<%}
			    mySession.setSample(sample.getLabtrak_id(),sampleId);				
				}
			  }
			}	
    	
			session.removeAttribute("nci.mmhcc.resubmit_key");
			
			
			%>
			 <jsp:forward page="Submission.jsp"/>		
			<%
			
 
}
	
%>





   <%@ include file="footer.jsp"%>
