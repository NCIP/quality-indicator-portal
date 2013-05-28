<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ page language="java" import="gov.nih.nci.qi.*, java.lang.*,java.util.*,java.text.*, gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*,gov.nih.nci.qi.constants.*" %>

<%@ page buffer="32kb"%>
<%@ page import="java.net.*"%>
<%@ page import="java.lang.reflect.*"%>
<%@ page import="java.io.*"%> 
 
 <%     

response.setHeader("Cache-Control","no-cache"); 
response.setHeader("Pragma","no-cache");%>
  
<%

QISession mySession  = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");
SubmitterRole submitterRole = (SubmitterRole) session.getAttribute("nci.mmhcc.submitterrole");

SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy"); 

String lastName = null;
String firstName = null;
Long submitterKey  = null;             
                                               
 
int sampleAcct = 0; 
String id = (String)request.getParameter("id");


Long modelID = null;    
Long sampleId = null; 
Long thisCoreType = null;

Vector samples = new Vector();                                                  
                                                                                                    
	if(mySession != null){       

	    lastName = mySession.getSubmitterLastName();
		firstName = mySession.getSubmitterFirstName();		
		submitterKey = mySession.getSubmitterUID();		
		
		if(submitterKey != null){		    
			samples.clear();
			Qi_sample sample = new Qi_sample();
			if (submitterRole.isDemoUser() ) {
				// I am demo account.  Just give me my samples !!
				samples = sample.retrieveAllWhere(" SUBMITTERKEY = " + submitterKey);			
			}
			else {
				try {
					ArrayList allDemoSubmitters = SubmitterRole.getAllSubmitters(QIConstants.DEMOROLE);
					if (allDemoSubmitters.size() > 0) {
						String notInValue = allDemoSubmitters.toString().replace('[','(').replace(']',')');
						// If Frozen Sample Role
						if (submitterRole.isInRole(QIConstants.FROZENSAMPLEROLE)) {
							samples = sample.retrieveAllWhere("CORE_TYPE='Frozen' AND SUBMITTERKEY NOT IN " + notInValue);			
						}else {
							if (submitterRole.isInRole(QIConstants.PARAFFINSAMPLEROLE)) {
								samples = sample.retrieveAllWhere("CORE_TYPE='Paraffin' AND SUBMITTERKEY NOT IN " + notInValue);			
							}else {
								samples = sample.retrieveAllWhere(" SUBMITTERKEY NOT IN " + notInValue);			
							}
						}
					}else {

						// If Frozen Sample Role
						if (submitterRole.isInRole(QIConstants.FROZENSAMPLEROLE)) {
						   System.out.println("I AM a frozen core user.....");
							samples = sample.retrieveAllWhere("CORE_TYPE='Frozen'");			
						}else {
							if (submitterRole.isInRole(QIConstants.PARAFFINSAMPLEROLE)) {
								samples = sample.retrieveAllWhere("CORE_TYPE='Paraffin'");			
							}else {
								// No demo users defined in the system.  Give me everything !!
								samples = sample.retrieveAll();			
							}
						}

					}
					
				}catch (Exception e) {
				}
			}
		}
		
		sampleAcct = samples.size();		
		mySession.clearAll(); //clear all data first   	 		
		}         
	%>  
		<%@ include file="returning_user.html"%>     
		<%@ include file="footer.jsp"%>
             
