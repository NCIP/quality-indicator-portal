   <%@ page language="java" import="gov.nih.nci.qi.*,gov.nih.nci.qi.util.*,gov.nih.nci.qi.db.*" %> 
  <%@ page buffer="32kb"%>  
 <%@ page import="java.util.*,java.io.*"%>  
  <%@ page import="java.sql.Date"%>    
                    
  <%@ page import="java.text.SimpleDateFormat"%>      
 <%@ page info="this page is the animal model search results page"%>
  <%@ page import="java.lang.reflect.Array"%>  
  
     
  <%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Pragma","no-cache"); 
   %>                                                       
 <%  
    
 // qi
  
   Qi_rna rna = new Qi_rna();
   String requestType = (String) session.getAttribute("gov.nih.nci.gi.requestType");
   Long sampleId = new Long((String)request.getParameter("sampleId"));
   String core_sample_id = (String) request.getParameter("core_sample_id");
   String where = "SAMPLE_ID = " + sampleId;
   Vector rnas = rna.retrieveAllWhere(where);
   SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
  
      
 
 //qi
  
 
 
 
 
	
	
		
		
	%>
		<%@ include file="rnaResultsDetail.html"%> 
 
		<%@ include file="footer.jsp"%>
