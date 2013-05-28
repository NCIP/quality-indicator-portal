<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

<%@ page language="java" import="java.util.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*,gov.nih.nci.qi.db.*" %>
<%@ page info="this page is the login page, first name & last name and verified on tbis page "%>
 <jsp:useBean id="user"  scope="page" class="gov.nih.nci.qi.db.Submitter" />
 <jsp:setProperty name="user" property="username" param="UserName"/>
 <jsp:setProperty name="user" property="password" param="password"/>
 <%@ page buffer="64kb"%> 
  
 <%	String mode = (String) request.getParameter("mode");
	if(	mode==null){
	   mode = (String) session.getAttribute("nci.mmhcc.newuser");
	    }
		System.out.println("mode:"+mode);
		 if( mode != null){ 
			session.setAttribute("nci.mmhcc.requestType",mode); 
		}
  %>
 <%! String where;  %> 
 <%  where = "USERNAME  ='"+ user.getUsername() +"'" + " and PASSWORD = '" + user.getPassword() + "'"; %>
 <% 	 System.err.println( "login_where: " + where);%>
 
 <%! Vector records = new Vector(); %>
 
  <%  System.out.println("mode is %%%%:"+mode);
  	 Long SubmitterId;  
     Long userTypekey = null;
  
     Enumeration userTypekeys = null;
	
	
 if (request.getParameter("submit_name")!= null && (user.getUsername()!= null &user.getPassword()!= null)){
 		// set session id
	 session.setAttribute("nci.mmhcc.sessionId",session.getId()); 
     session.setAttribute("nci.mmhcc.submitter.userName",user.getUsername()); 		
     System.err.println("login_a requesttype:" + ((String)session.getAttribute("nci.mmhcc.requestType")));
     records = user.retrieveAllWhere(where);
     if(records.size() == 1){  //yes found a match, now check the password 
		 user = (Submitter) records.firstElement();
		 // 
		 SubmitterRole userRole = new SubmitterRole(user);
		 try {
	 		userRole.getRoles();
	 
		 	session.removeAttribute("nci.mmhcc.submitter");
		 	session.removeAttribute("nci.mmhcc.submitterrole");
			
			
			session.setAttribute("nci.mmhcc.submitter",user);
			session.setAttribute("nci.mmhcc.submitterrole",userRole); // Add User role to session
			
			SubmitterId = user.getSubmitterkey(); 	
			System.err.println("set submitterid" + SubmitterId);  
			System.err.println( "login_a userName:" + user.getUsername());
			System.err.println( "login_a getpassword:" + user.getPassword());
			QISession mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession");
			System.out.println("mySession:-----"+mySession);
		
			mySession = new QISession(SubmitterId);
		
			session.setAttribute("nci.mmhcc.submitter.submitterKey",SubmitterId);
		 	String requestType = (String) session.getAttribute("nci.mmhcc.requestType");
			String requestType2 = (String) session.getAttribute("nci.mmhcc.requestType2");
			Long pageNum = (Long)session.getAttribute("nci.mmhcc.pagenumber");
			System.err.println("requestType:" +requestType );
			System.err.println("requestType2----------:" +requestType2 );
			System.out.println("submitKey is:"+SubmitterId);			
			
				
				
			mySession.setObjectID("SUBMITTER",SubmitterId,user.getUsername()+","+user.getPassword());
			session.removeAttribute("nci.mmhcc.mmhccSession");
			session.setAttribute("nci.mmhcc.mmhccSession",mySession);
			response.sendRedirect("select.jsp");
	
		 }catch (Exception e) {
			response.sendRedirect("RoleError.jsp");
		 }
	}
	else  { //no match so show login error page 
		    System.out.println("pwd error");
			response.sendRedirect("login_c.jsp?mode="+mode);

	 }  

	}
	else  { //no change so show logic page again 
	
	    response.sendRedirect("index.jsp");
	    
		//response.sendRedirect("login_a.jsp?mode="+mode);

	 }  %>
