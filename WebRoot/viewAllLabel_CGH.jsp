<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

     
<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*, gov.nih.nci.qi.constants.*" %>
<%@ page buffer="32kb"%>
<%@ page import="java.lang.*,java.text.*,java.sql.*"%>
<%@ page import="java.util.*,java.io.*"%> 
<%@ page info="this page is the password validation page"%> 
    
<%    
 	//qi	      
	         
	Long dnaId = null; 
	Long dna_labeling_id = null;
	Long cgh_hyb_id = null;	
	Long sampleId = null;
	SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
	Vector dna_labels = new Vector();
	Vector cgh_hybs = new Vector();
	Vector attempts = new Vector();
	boolean status = false;
	  
	        
	//qi  
	   
			        
 
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
<jsp:useBean id="qi_attempt" scope="request" class="gov.nih.nci.qi.db.Qi_attempt"/>





<% 
	String id = (String) request.getParameter("id");
	System.err.println(" id:" + id );
	
	
	
	if(id != null && !(id.equals(""))){
		dnaId = Long.decode(id);	
		dna.retrieveByKey(dnaId);
		dna_labels.clear();
		cgh_hybs.clear();
		dna_labels= dna_label.retrieveAllWhere(" DNA_ID = "+dnaId +  " Order by LABELING_ATTEMPT_ID asc");	
		cgh_hybs= cgh_hyb.retrieveAllWhere(" DNA_ID = "+dnaId + " Order by CGH_HYB_ATTEMPT_ID asc");	
	}
	
 	
%>  			


	
<%@ include file="viewAllLabel_CGH.html"%>


<%	

	boolean Status = false;
	boolean status2  = false;
	
	String thePage = "viewAllLabel_CGH.jsp";
    session.setAttribute("gov.nih.nci.qi.constaint",thePage);
	boolean deleteStatus = false;
	
	if(request.getParameter("bk_to_previous") != null){
	      String pageStr = "DispatcherServlet?url=dna.jsp&id="+id;
	   //  String pageStr = "dna.jsp?id="+dnaId;
		 System.out.println("pageStr:"+pageStr);
	     	%>
			
			 <jsp:forward page="<%=pageStr%>"/>
			<%} 
	
if(request.getParameter("delete_dna_label_cgh_data") != null && id != null){
  DeleteRecord delete  = new DeleteRecord(); 
  deleteStatus = delete.deleteLabelingDNA_CGH_HYB(new Long(id));
  if(deleteStatus = true){
      String pageStr = "DispatcherServlet?url=dna.jsp&id="+id;
      //String pageStr = "dna.jsp&id="+id;%>
      <jsp:forward page="<%=pageStr%>"/>
     <%
   }  
 }
	
if(request.getParameter("update_dna_labeling_cgh_quality") != null)
{
		// deal with dna labeling + cgh hyb info
		
		for(int i=0;i<dna_labels.size();i++){
		     dna_label = (Qi_dna_labeling)dna_labels.elementAt(i);			
			 dna_labeling_id = dna_label.getLabeling_id();	
			
		
			  System.out.println("00000000000before");
			   System.out.println("00000000000:"+request.getParameter("intensity0"));
			  
			 if(request.getParameter("intensity"+i) != null && !request.getParameter("intensity"+i).equals(""))	{
			  
			   String label_intensity = (String)request.getParameter("intensity"+i);
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
			 
			 if(request.getParameter("label_date"+i) != null && !request.getParameter("label_date"+i).equals(""))	{
			   String labelDate = null;    
			   ParsePosition pos4 = new ParsePosition(0);	
			   labelDate = (String) request.getParameter("label_date"+i);			 
			   labelDate.trim();			     			
			   java.util.Date lDate = formatter.parse(labelDate,pos4);			  
			   Timestamp ts4  = new Timestamp(lDate.getTime());					
			   dna_label.setLabeling_date(ts4);				   
			  
			 }
			
			  if(dnaId != null){
			     dna_label.setDna_id(dnaId);		
			  }
				dna_label.updateByKey();
			}
			
			for(int i=0;i<cgh_hybs.size();i++){
		      cgh_hyb = (Qi_cgh_hybridization)cgh_hybs.elementAt(i);			
			  cgh_hyb_id = cgh_hyb.getHybridization_id();		
			
			
		
			 
			if(request.getParameter("cgh_hyb"+i) != null && !request.getParameter("cgh_hyb"+i).equals(""))	{
			   String cgh_quality = (String)request.getParameter("cgh_hyb"+i);
			   if(cgh_quality.trim().equals("Good, Acceptable( score >=3)")){			   
			     cgh_hyb.setCgh_quality_id(new Long(1));
				 }
			   else{
				 cgh_hyb.setCgh_quality_id(new Long(2));
				} 
			 }		
			 
			 
			
			
			if(request.getParameter("hyb_date"+i) != null && !request.getParameter("hyb_date"+i).equals(""))	{
			   String hybDate = null;    
			   ParsePosition pos5 = new ParsePosition(0);	
			   hybDate = (String) request.getParameter("hyb_date"+i);			 
			   hybDate.trim();			     			
			   java.util.Date hDate = formatter.parse(hybDate,pos5);			  
			   Timestamp ts5  = new Timestamp(hDate.getTime());					
			   cgh_hyb.setHybridization_date(ts5);				   
			  
			 }
			 
			  if(dnaId != null){
			     cgh_hyb.setDna_id(dnaId);		
			  }
			  cgh_hyb.updateByKey();		
			
		    }%>
			
			
	
		<script language="JavaScript">	 
	       location.replace("viewAllLabel_CGH.jsp?id=<%=id%>");
	     </script> 

	
<%}		 

 %>	

         
 

<%@ include file="footer.jsp"%>
   


   
