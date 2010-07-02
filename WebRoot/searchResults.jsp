                                         
 <%@ page language="java" import="gov.nih.nci.qi.db.*" %>
 <%@ page info="this page is the animal model search results page"%>
 <%@ page import="gov.nih.nci.qi.*,gov.nih.nci.qi.util.*"%>
 <%@ page import="java.util.*,java.text.*,java.io.*"%>  
 <%@ page import="java.lang.Long"%> 
 <%@ page buffer="64kb"%>
 <%@ page import="java.lang.reflect.Array"%> 			 
                  
		 	<%! String sql, where, from, joins, select, orderby,pcr_rating,rna_analysis_quality; 			
 
			    //qi
				SampleEx sampleSearch ;
				Vector samples = new Vector(); 
				Hashtable patientHash = new Hashtable();
				
				//qi			 
			  
			 	 
				int resultsTotal = 0;				
			 	Long submitterKey = null;
				SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");			
			   
				
				Vector labTrakId_vec = new Vector(); 
			    
				   
				%>     
			<%
			
			
			String requestType = (String) session.getAttribute("gov.nih.nci.gi.requestType");
		    String labId = (String) session.getAttribute("gov.nih.nci.qi.labId");	
		    if (requestType == null){
				requestType = "Search";
			} 
			 
			 sampleSearch = new SampleEx();
			 
			 
		 
			if (requestType == null){
				requestType = "Search";
			}   
			 
			if( requestType != null && requestType.equals("Search")){
			   where = (String) session.getAttribute("gov.nih.nci.qi.where"); 
			   pcr_rating = (String)session.getAttribute("gov.nih.nci.qi.pcr_rating"); 
			   rna_analysis_quality = (String)session.getAttribute("gov.nih.nci.qi.rna_analysis_quality"); 
			   session.removeAttribute("gov.nih.nci.qi.pcr_rating");
			   session.removeAttribute("gov.nih.nci.qi.rna_analysis_quality");
			        
			     
			   
			 }		  
			 
      	%>   
			    
			<%    
			//try{
			    samples = sampleSearch.retrieveAllWhere(request,session,where);	
				request.getSession().setAttribute("gov.nih.nci.qi.searchResult",samples); 
			   
				if(samples != null){
				  resultsTotal = samples.size();
				  }
				  patientHash.clear();
				  labTrakId_vec.clear();
				 for(int u=0;u< samples.size();u++){
				  SampleEx resultSample = (SampleEx)samples.elementAt(u);	
				   labTrakId_vec.add(resultSample.getSample_id());	
				  patientHash.put(resultSample.getPatient_accrual(),resultSample.getPatient_accrual());
				  }
			    %>   
<%@ include file="search_results.html"%>

<%@ include file="footer.jsp"%> 

		

	

