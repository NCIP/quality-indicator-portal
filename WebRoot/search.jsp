<%-- Read db connection info from db.properties files and set up the static connect pool once --%>
<%@ page import="gov.nih.nci.qi.util.*,gov.nih.nci.qi.*"%>
<//%@ page buffer="64kB"%>
<%@ page import="java.util.*"%>
                 
<% QISession mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");
  
                          
	if(mySession == null){  
	   mySession = new QISession(); 
	}%>                                              

   
<%  
 	if(!session.isNew()){
		request.getSession(false);      
		}                        
		                                                                  
    request.getSession(true);    
	response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");    
	String mode = (String) request.getParameter("mode");
		if( mode != null){  
			session.setAttribute("nci.mmhcc.requestType",mode);
			}      
                                                   
	%>
	          
		<%@ include file="simple_search.jsp"%>

		<%@ include file="footer.jsp"%>  
            
 
