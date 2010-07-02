   <%@ page language="java" import="gov.nih.nci.qi.*,gov.nih.nci.qi.util.*,gov.nih.nci.qi.db.*" %> 
  <%@ page buffer="32kb"%>  
 <%@ page import="java.util.*,java.io.*"%>  
  <%@ page import="java.sql.Date"%>    
                    
  <%@ page import="java.text.SimpleDateFormat"%>      
 <%@ page info="this page is the animal model search results page"%>
  <%@ page import="java.lang.reflect.Array"%>  
  <jsp:useBean id="dna_label" scope="request" class="gov.nih.nci.qi.db.Qi_dna_labeling"/>
<jsp:useBean id="cgh_hyb" scope="request" class="gov.nih.nci.qi.db.Qi_cgh_hybridization"/>
<jsp:useBean id="qi_attempt" scope="request" class="gov.nih.nci.qi.db.Qi_attempt"/>
     
  <%
   response.setHeader("Cache-Control","no-cache"); 
   response.setHeader("Pragma","no-cache"); 
   %>                                                       
 <%  
      
 // qi
  
    Qi_dna dna = new Qi_dna();
    String requestType = (String) session.getAttribute("gov.nih.nci.gi.requestType");
    Long sampleId = new Long((String)request.getParameter("sampleId"));
    String core_sample_id = (String) request.getParameter("core_sample_id");
    String where = "SAMPLE_ID = " + sampleId;
    Vector dnas = dna.retrieveAllWhere(where);
    SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
	
    Long dna_labeling_id = null;
    Long cgh_hyb_id = null;	
	
	
	Vector dna_labels = new Vector();
	Vector cgh_hybs = new Vector();
	Vector attempts = new Vector();
	Vector intensitys = new Vector();
	Vector cgh_hyb_qualitys = new Vector();
		 
	
	
	dna_labels= dna_label.retrieveAllWhere(" DNA_ID in (select DNA_ID from QI_DNA where SAMPLE_ID = " + sampleId +")"+" Order by LABELING_ATTEMPT_ID asc");	
	cgh_hybs= cgh_hyb.retrieveAllWhere(" DNA_ID in (select DNA_ID from QI_DNA where SAMPLE_ID = " + sampleId +")"+" Order by CGH_HYB_ATTEMPT_ID asc" );	
	
	  
  
%>


<%@ include file="dnaResultsDetail.html"%>  
<%@ include file="footer.jsp"%>
