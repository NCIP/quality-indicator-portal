<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*,gov.nih.nci.qi.constants.*" %>
<%@ page buffer="32kb"%>
<%@ page import="java.io.*,java.util.*"%>
<%@ page import="java.lang.*,java.text.*,java.sql.*"%>
<%@ page info="this page is the password validation page"%>

<%  
    gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
	SessionManager.verifySession(request,response,"Error.jsp");
  
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");	

   //qi	
	Long sampleId = null;
	Long rnaId = null;
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


<jsp:useBean id="rna" scope="request" class="gov.nih.nci.qi.db.Qi_rna"/>
<jsp:useBean id="rna_img" scope="request" class="gov.nih.nci.qi.db.Rna_quality_image"/>




<% 	Long chemdrugUID = null;
	String id = (String) request.getParameter("id");
	System.err.println(" id:" + id );
	
	
	
	
	if(id != null && !(id.equals(""))){
		rnaId = Long.decode(id);	
		System.err.println("parameter rnaId:"+rnaId);
	    rna.retrieveByKey(rnaId);%>
		<!--- <INPUT TYPE = "HIDDEN" NAME = "id" VALUE = "<%=id%>"> --->
	<%}
	else{
		id="";
		if(mode != null && mode.equals("Submit")){
		   rna = new Qi_rna();
		   }
		}
%>  			

<%@ include file="rna.html"%>


<%@ include file="footer.jsp"%>	


   
