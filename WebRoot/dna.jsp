  
<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*, gov.nih.nci.qi.constants.*" %>
<%@ page buffer="32kb"%>
<%@ page import="java.lang.*,java.text.*,java.sql.*"%>
<%@ page import="java.util.*,java.io.*"%> 
<%@ page info="this page is the password validation page"%>

<%  
    gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
	SessionManager.verifySession(request,response,"Error.jsp");
  
response.setHeader("Cache-Control","no-cache");
response.setHeader("Pragma","no-cache");%>
 
  
 
<% 	   
	//qi	
	Long sampleId = null;
	Long dnaId = null;
	Long dna_labeling_id = null;
	Long cgh_hyb_id = null;
	Long[] labeling_attempt_ids = null;
	Long[] cgh_hyb_attempt_ids = null;
	Vector dna_labels = new Vector();
	Vector cgh_hybs = new Vector();
	String mode = null;
    mode =(String)request.getParameter("mode");
	SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
	
	     
	//qi   
	
			  
    Long modelId = null;
    String labtrak_id = null;
    
	QISession mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession"); 
	SubmitterRole submitterRole = (SubmitterRole) session.getAttribute("nci.mmhcc.submitterrole");
	
	if(mySession != null){
	   
	    sampleId = mySession.getSampleId();	  
		System.err.println("1. sampleId:" + sampleId );		
		labtrak_id = mySession.getLabtrak_id();
	}
%> 
<jsp:useBean id="dna" scope="request" class="gov.nih.nci.qi.db.Qi_dna"/>
<jsp:useBean id="dna_label" scope="request" class="gov.nih.nci.qi.db.Qi_dna_labeling"/>
<jsp:useBean id="cgh_hyb" scope="request" class="gov.nih.nci.qi.db.Qi_cgh_hybridization"/>





<% 	Long chemdrugUID = null;
	String id = (String) request.getParameter("id");
	System.err.println(" id:" + id );
	
	
	
	if(id != null && !(id.equals(""))){
		dnaId = Long.decode(id);	
		System.err.println("parameter dnaId:"+dnaId);
	    dna.retrieveByKey(dnaId);
		dna_labels= dna_label.retrieveAllWhere(" DNA_ID = "+dnaId);
		labeling_attempt_ids = new Long[dna_labels.size()];
		for(int i=0;i<dna_labels.size();i++){
		   dna_label = (Qi_dna_labeling)dna_labels.elementAt(i);
		   dna_labeling_id = dna_label.getLabeling_id();
		   labeling_attempt_ids[i] = dna_label.getLabeling_attempt_id();
		  
		   }
	
		cgh_hybs= cgh_hyb.retrieveAllWhere(" DNA_ID = "+dnaId);
		cgh_hyb_attempt_ids = new Long[cgh_hybs.size()];
		for(int i=0;i<cgh_hybs.size();i++){
		   cgh_hyb = (Qi_cgh_hybridization)cgh_hybs.elementAt(i);
		   cgh_hyb_id = cgh_hyb.getHybridization_id();
		   cgh_hyb_attempt_ids[i] = cgh_hyb.getCgh_hyb_attempt_id();
		  
		   } 
		 
		
		
		%>
		<!--- <INPUT TYPE = "HIDDEN" NAME = "id" VALUE = "<%=id%>"> --->
	<%}
	else{
		id="";
		if(mode != null && mode.equals("Submit")){
		   dna = new Qi_dna();
		  }
		}
 if(sampleId != null){
   dna.setSample_id(sampleId);
   }		
%>  			


	
<%@ include file="dna.html"%>

<//jsp:setProperty name="dna" property="institution_id" param="institution"/>
<//jsp:setProperty name="dna" property="dna_reading" param="dnaReading"/>
<//jsp:setProperty name="dna" property="instrument_id" param="reading_instrument"/>
<//jsp:setProperty name="dna" property="dna_extraction_quality" param="dnaQuality"/>
<//jsp:setProperty name="dna" property="dna_subsample_id" param="required_Sub-sampleId"/>
<jsp:setProperty name="dna" property="pcr_rating" param="pcr_rating"/>
<jsp:setProperty name="dna" property="dna_quantity_unit" param="dna_unit"/>
<//jsp:setProperty name="dna" property="dna_quantity_unit" param="cgh_quality"/>

<%	

	boolean Status = false;
	boolean status2  = false;
	if(dnaId!= null){
	  dna.setSample_id(sampleId); 
	  }
	String thePage = "dna.jsp";
    session.setAttribute("gov.nih.nci.qi.constaint",thePage);
	boolean deleteStatus = false;
	
	if(request.getParameter("bk") != null){
	     	%>
			 <jsp:forward page="Submission.jsp"/>
			<%}
	
if(request.getParameter("delete_data") != null){
  DeleteRecord delete  = new DeleteRecord(); 
  deleteStatus = delete.deleteDNA(dnaId);
  if(deleteStatus = true){%>
    <jsp:forward page="Submission.jsp"/>
     <%
   }  
 }
	
if(request.getParameter("submit_dan_quality") != null)
{

  boolean flag = DatabaseSetup.checkMissingRequiredFields(request);
	if(flag)
	{
	   
	   System.err.println("yes missing");		  
		  
	   // end saving the data in the session
	   		
	   Vector missing_feilds = (Vector)request.getAttribute("gov.nih.nci.qi.MissingFeilds");
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
	
	
	if(dnaId != null){
	   dna.setDna_id(dnaId);
	    if(request.getParameter("required_dna_Sub-sampleId") != null && !request.getParameter("required_dna_Sub-sampleId").equals("")){			
			 dna.setDna_subsample_id(mySession.getLabtrak_id()+"-"+(String)request.getParameter("required_dna_Sub-sampleId"));
			  }
	   try{		   
			session.setAttribute("gov.nih.nci.qi.id",id);
	        Status = dna.updateByKey();
		 }	
	   catch(Exception e){%>
			<jsp:forward page="constraintError.jsp"/>	
	   <%}
	}
	else{
		dnaId = mySession.getDatabaseUID("QI_DNA");
		if(dnaId != null){
		 	dna.setDna_id(dnaId);	
			 if(request.getParameter("required_dna_Sub-sampleId") != null && !request.getParameter("required_dna_Sub-sampleId").equals("")){			
			 dna.setDna_subsample_id(mySession.getLabtrak_id()+"-"+(String)request.getParameter("required_dna_Sub-sampleId"));
			  }
			try{	
			   Status = dna.insert();
			      }  //this
			catch(Exception e){				    		  
				   %>
				  <jsp:forward page="constraintError.jsp"/>	
				<%}
			
			System.err.println("insert ");
			}
		}
	if(Status){	
	     
		
	     	
	      if(request.getParameter("process_date") != null && !request.getParameter("process_date").equals(""))	{
			   String processDate = null;    
			   ParsePosition pos = new ParsePosition(0);	
			   processDate = (String) request.getParameter("process_date");			 
			   processDate.trim();			     			
			   java.util.Date pDate = formatter.parse(processDate,pos);			  
			   Timestamp ts  = new Timestamp(pDate.getTime());					
			   dna.setProcess_date(ts);				   
			  
			 }
		   if(request.getParameter("analysis_date") != null && !request.getParameter("analysis_date").equals(""))	{
			   String analysisDate = null;    
			   ParsePosition pos2 = new ParsePosition(0);   
			   analysisDate = (String) request.getParameter("analysis_date");
			   analysisDate.trim();				
			   java.util.Date aDate = formatter.parse(analysisDate,pos2);
			   Timestamp ts2  = new Timestamp(aDate.getTime());					
			   dna.setAnalysis_date(ts2);			 
			
		    }
			
			
			ParsePosition pos3 = new ParsePosition(0);	
			java.util.Date currentTime_1 = new java.util.Date();
			String dateString  = formatter.format(currentTime_1);
			System.out.println("dateString:"+dateString+"pos3 is :"+pos3);
			java.util.Date timestamp = formatter.parse(dateString,pos3);
			 System.out.println("timestamp is :"+timestamp);
		    Timestamp ts3  = new Timestamp(timestamp.getTime());		
		    dna.setTimestamp(ts3);	
			
			
			// deal with dna labeling + cgh hyb info
			
			
			String lable_attempt = null; 
			if(request.getParameter("labeling_attempt") != null && !request.getParameter("labeling_attempt").equals(""))	{
			   lable_attempt = (String)request.getParameter("labeling_attempt");
			   dna_label.setLabeling_attempt_id(new Long(lable_attempt.substring(0,1)));
			 }
			 
			 if(request.getParameter("intensity") != null && !request.getParameter("intensity").equals(""))	{
			   String label_intensity = (String)request.getParameter("intensity");
			   if(label_intensity.trim().equals("High")){			   
			     dna_label.setIntensity_id(new Long(1));
				 }
				else  if(label_intensity.trim().equals("Medium")){			   
			     dna_label.setIntensity_id(new Long(2));
				 }
			   else{
				 dna_label.setIntensity_id(new Long(3));
				} 
			 }	
			 
			 if(request.getParameter("label_date") != null && !request.getParameter("label_date").equals(""))	{
			   String labelDate = null;    
			   ParsePosition pos4 = new ParsePosition(0);	
			   labelDate = (String) request.getParameter("label_date");			 
			   labelDate.trim();			     			
			   java.util.Date lDate = formatter.parse(labelDate,pos4);			  
			   Timestamp ts4  = new Timestamp(lDate.getTime());					
			   dna_label.setLabeling_date(ts4);				   
			  
			 }
			
			if(lable_attempt != null){
			     dna_labeling_id = mySession.getDatabaseUID("QI_DNA_LABELING");					
			     if(dna_labeling_id!= null){
			        dna_label.setLabeling_id(dna_labeling_id);	
				    dna_label.insert();			
			      } 
			      if(dnaId != null){
			         dna_label.setDna_id(dnaId);		
			        }
				   dna_label.updateByKey();
			   }
			   
			 
			
			String hyb_attempt = null;
			if(request.getParameter("cgh_hyb_attempt") != null && !request.getParameter("cgh_hyb_attempt").equals(""))	{
			   hyb_attempt = (String)request.getParameter("cgh_hyb_attempt");
			   cgh_hyb.setCgh_hyb_attempt_id(new Long(hyb_attempt.substring(0,1)));
			 }	
			 
			
			 
			if(request.getParameter("cgh_quality") != null && !request.getParameter("cgh_quality").equals(""))	{
			   String cgh_quality = (String)request.getParameter("cgh_quality");
			   if(cgh_quality.trim().equals("Good, Acceptable( score >=3)")){			   
			     cgh_hyb.setCgh_quality_id(new Long(1));
				 }
			   else{
				 cgh_hyb.setCgh_quality_id(new Long(2));
				} 
			 }		
			 
			 
			
			
			if(request.getParameter("hyb_date") != null && !request.getParameter("hyb_date").equals(""))	{
			   String hybDate = null;    
			   ParsePosition pos5 = new ParsePosition(0);	
			   hybDate = (String) request.getParameter("hyb_date");			 
			   hybDate.trim();			     			
			   java.util.Date hDate = formatter.parse(hybDate,pos5);			  
			   Timestamp ts5  = new Timestamp(hDate.getTime());					
			   cgh_hyb.setHybridization_date(ts5);				   
			  
			 }
			 
			 if(hyb_attempt != null){
			     cgh_hyb_id =   mySession.getDatabaseUID("QI_CGH_HYBRIDIZATION");	

			     if(cgh_hyb_id != null){
			         cgh_hyb.setHybridization_id(cgh_hyb_id);			  
			         cgh_hyb.insert();
			      } 
			 
			      if(dnaId != null){
			        cgh_hyb.setDna_id(dnaId);		
			       }
			     cgh_hyb.updateByKey();		
			}
		   if(request.getParameter("comment") != null && !request.getParameter("comment").equals(""))	{
			   dna.setComments(request.getParameter("comment"));
			 }
				
		    
			try{	  
			  Status = dna.updateByKey();
			  
			  }
			catch(Exception e){	
			   		  
				   %>
				  <jsp:forward page="constraintError.jsp"/>	
				<%}
			if(dna.getDna_subsample_id() != null){
			  if(dna.getDna_subsample_id().indexOf("-2")!=-1){
			      String label = dna.getDna_subsample_id()+"(Joe Gray Lab)";			    
			
			      mySession.setObjectID("DNA",dnaId,label);
				 }
			 else{
			     String label = dna.getDna_subsample_id()+"(Kathy Conway Dorsey Lab)";		
				  mySession.setObjectID("DNA",dnaId,label);
				} 
			  }
			else if(dna.getDna_subsample_id() == null && dna.getDna_reading() != null && dna.getDna_quantity_unit()!= null){
               String label = dna.getDna_reading()+ " " +dna.getDna_quantity_unit();				    
			   mySession.setObjectID("DNA",dnaId,label);
			  } 
			%>
			 <jsp:forward page="Submission.jsp"/>
			<% //response.sendRedirect("Submission.jsp");
			%>
	
		<%}
		 
 
	
}		   %>	

<%@ include file="footer.jsp"%>
   


   
