<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

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
 
  Qi_sample qi_sample  = new Qi_sample();
   String requestType = (String) session.getAttribute("gov.nih.nci.gi.requestType");
   Long sampleId = new Long((String)request.getParameter("sampleId"));
   String core_sample_id = null;
   String where = "sample_id = " + sampleId;
   Vector samples = qi_sample.retrieveAllWhere(where);
   for(int k=0;k<samples.size();k++){
      qi_sample = (Qi_sample)samples.elementAt(k);
      core_sample_id = qi_sample.getLabtrak_id();
   }   
 
 //qi  
 
 	
	%>
		<%@ include file="search_general_info.html"%> 
 
		<%@ include file="footer.jsp"%>