<%--L
  Copyright SAIC

  Distributed under the OSI-approved BSD 3-Clause License.
  See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
L--%>

  
<%@ page language="java" import="gov.nih.nci.qi.db.*,gov.nih.nci.qi.util.*, gov.nih.nci.qi.*" %>
<%@ page buffer="32kb"%>
<%@ page import="java.lang.*,java.text.*,java.sql.*"%>
<%@ page import="java.io.*,java.util.*,com.bigfoot.bugar.servlet.http.*"%>
<%@ page info="this page is the password validation page"%>

<%  try{
 MultipartFormData multipart = new MultipartFormData(request, 5242880L );
 

//5 MB limit on files
 
    gov.nih.nci.qi.DatabaseSetup.setPageExpiration(response);
	//SessionManager.verifySession(request,response,"Error.jsp");
  
    response.setHeader("Cache-Control","no-cache");
    response.setHeader("Pragma","no-cache");	

   //qi
	Long sampleId = null;
	Long proteinId = null;
	String mode = null;
    mode =(String)request.getParameter("mode");	
	SimpleDateFormat formatter = new SimpleDateFormat ("MM/dd/yyyy");
	
	// starting intializing image server related parameters
	String userDir = System.getProperty("user.dir");
	String url = null;    
	String path = null;    
	String username = null;  
	String pwd = null;
	String path2 = null;
	
	if(userDir.indexOf(":")!= -1){ //window
		url = getServletConfig().getServletContext().getInitParameter("ftpur1_WIN");	
		path = getServletConfig().getServletContext().getInitParameter("ftppath_WIN");		
	}
	else {
		url = getServletConfig().getServletContext().getInitParameter("ftpur1_UNIX");	
		path = getServletConfig().getServletContext().getInitParameter("ftppath_UNIX");
	}
	
	
	if(userDir.indexOf(":")!= -1){ 	 
	  path = path+"/QI_Images/Protein_Array_Images";
	}
	
	else{	
	  path = path+"/QI_Images/Protein_Array_Images";	 
	}
	
	
	if(userDir.indexOf(":")!= -1){ 
		username = getServletConfig().getServletContext().getInitParameter("ftpusername_WIN");
		pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_WIN");
	}
	else {
	    username = getServletConfig().getServletContext().getInitParameter("ftpusername_UNIX");
		pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_UNIX");
	}
	
	
	// end of image related server parameters	
			  
    Long modelId = null;    
	QISession mySession = (QISession) session.getAttribute("nci.mmhcc.mmhccSession"); 
	if(mySession != null){
	    sampleId = mySession.getSampleId();				
	}
%> 
<jsp:useBean id="protein" scope="request" class="gov.nih.nci.qi.db.Qi_protein"/>
<jsp:useBean id="protein_img" scope="request" class="gov.nih.nci.qi.db.Rna_quality_image"/>


<%  
	String id = (String) multipart.getParameter("id");	
	
	if(id != null && !(id.equals(""))){
		proteinId = Long.decode(id);			
	    protein.retrieveByKey(proteinId);%>
		
	<%}
	else{
		id="";
		if(mode != null && mode.equals("Submit")){
		   protein = new Qi_protein();
		   }
		}
%>  	

<%	
   
	boolean Status = false;
	boolean status2  = false;
	
	if(sampleId != null){
       protein.setSample_id(sampleId);
     }	
    String thePage = "protein.jsp";
    session.setAttribute("gov.nih.nci.qi.constaint",thePage);
	boolean deleteStatus = false;
	
if(multipart.getParameter("bk") != null){
	     	%>
			 <jsp:forward page="Submission.jsp"/>
			<%}
	
	
if(multipart.getParameter("delete_data") != null){
  DeleteRecord delete  = new DeleteRecord(); 
  delete.init (config);
  deleteStatus = delete.deleteProtein(proteinId);
  if(deleteStatus = true){%>
    <jsp:forward page="Submission.jsp"/>
     <%
   }  
 }

if(multipart.getParameter("submit_protein_quality") != null)
{
	
	boolean flag = DatabaseSetup.checkMissingRequiredFields(request,multipart);	
	if(flag)
	{
	   
	   System.err.println("yes missing");	
	   Vector missing_feilds = new Vector();	  
	   missing_feilds.add("Protein Sub-sample ID"); 
	   flag = true;
	   request.setAttribute("gov.nih.nci.qi.MissingFeilds",missing_feilds);
	   }   
	
	System.err.println("flag is :"+flag);    
	
	if (flag)
	{ 
	   %>
	   <jsp:forward page="missing_required_feilds.jsp"/>
	<%}
	
	
	
	if(proteinId != null){
	   protein.setProtein_id(proteinId);
	  
	   
	  try{		   
			session.setAttribute("gov.nih.nci.qi.id",id);
	        Status = protein.updateByKey();
		}
	  catch(Exception e){%>
			<jsp:forward page="constraintError.jsp"/>	
	   <%}	
	   System.err.println("update ");
	}
	else{
		proteinId = mySession.getDatabaseUID("QI_PROTEIN");
		
		if(proteinId != null){
		 	protein.setProtein_id(proteinId);					 
			try{
		 	   Status = protein.insert();
			   }
			catch(Exception e){
				  
				   %>
				   <jsp:forward page="constraintError.jsp"/>	
				<%}
			System.err.println("insert ");
			}
		}
	if(Status){	
	
	  if(multipart.getParameter("institution") != null && !multipart.getParameter("institution").equals(""))	{
	    protein.setInstitution_id(new Long(multipart.getParameter("institution")));
	  } 
	  
	   if(multipart.getParameter("efficiency") != null && !multipart.getParameter("efficiency").equals(""))	{
     	protein.setMicodissection_efficiency(multipart.getParameter("efficiency"));
	  } 
	  if(multipart.getParameter("sample_length") != null && !multipart.getParameter("sample_length").equals(""))	{
     	protein.setProtein_sample_length(multipart.getParameter("sample_length"));
	  } 
	  if(multipart.getParameter("sample_width") != null && !multipart.getParameter("sample_width").equals(""))	{
     	protein.setProtein_sample_width(multipart.getParameter("sample_width"));
	  } 
	  if(multipart.getParameter("tumor_presence") != null && !multipart.getParameter("tumor_presence").equals("") )	{
	    protein.setProtein_sample_tumor_presence(multipart.getParameter("tumor_presence"));
	  } 
	  if(multipart.getParameter("total_cells") != null && !multipart.getParameter("total_cells").equals(""))	{
	    protein.setTotal_number_cells(new Long(multipart.getParameter("total_cells")));		
		
	  }
	  if(multipart.getParameter("diagnosis") != null && !multipart.getParameter("diagnosis").equals(""))	{
	    protein.setPathologic_diagnosis(multipart.getParameter("diagnosis"));
		}
	
	  if(multipart.getParameter("required_Sub-sampleId") != null && !multipart.getParameter("required_Sub-sampleId").equals(""))	{
	    protein.setProtein_subsample_id(mySession.getLabtrak_id()+"-"+(String)multipart.getParameter("required_Sub-sampleId"));
		} 
		
		ParsePosition pos3 = new ParsePosition(0);	
		java.util.Date currentTime_1 = new java.util.Date();
		String dateString  = formatter.format(currentTime_1);		
		java.util.Date timestamp = formatter.parse(dateString,pos3);		
	    Timestamp ts3  = new Timestamp(timestamp.getTime());		
	    protein.setTimestamp(ts3);	
		
		
		if(multipart.getParameter("analysis_date") != null && !multipart.getParameter("analysis_date").equals(""))	{
		   String analysisDate = null;    
		   ParsePosition pos2 = new ParsePosition(0);   
		   analysisDate = (String) multipart.getParameter("analysis_date");
		   analysisDate.trim();				
		   java.util.Date aDate = formatter.parse(analysisDate,pos2);
		   Timestamp ts2  = new Timestamp(aDate.getTime());					
		   protein.setAnalysis_date(ts2);			 
			
		    }
		
	   
	 
			 
			 
			 
		// starting protein trace file upload
		 Long protein_img_id = null;
		
		 String uploadObject =  multipart.getFileName( "myUploadObject" );
		 if(uploadObject != null && !(uploadObject.equals(""))){	// either submit or update a trace file
			
			Vector protein_img_vec = new Vector();
			protein_img_vec = protein_img.retrieveAllWhere("PROTEIN_ID = " +protein.getProtein_id()+ "AND IMAGE_TYPE ="+"\'"+"Protein_Array_Image"+"\'");		
			// the size of protein_img_vec is always 1 or 0, b/c protein to trace img is one to one
		    
			String oldOriginalImgName = null;			
		    String oldImgFileId= null;
				 for(int p=0; p<protein_img_vec.size();p++){
				    protein_img = (Rna_quality_image)protein_img_vec.elementAt(p);
			        oldOriginalImgName =  protein_img.getOriginal_image_name();
					protein_img_id = protein_img.getRna_quality_image_id();
					oldImgFileId = protein_img.getImage_file_id();
				 
				 }
			
			 
			String fileSeparator = null;
				 if(uploadObject.indexOf("\\") != -1) { //uploaded file from windows
					  fileSeparator = "\\";
				}
				 else if (uploadObject.indexOf("/") != -1) { //uploaded file from unix or linux
				   fileSeparator = "/";
				  }
				 else { //if(uploadObject.indexOf(":") != -1) { //uploaded file from macs
				   fileSeparator = null;
				  }
		    	String imageName = uploadObject;// get file name only
				if(fileSeparator != null){
				   	 imageName = uploadObject.substring(uploadObject.lastIndexOf(fileSeparator)+1);// get file name only
				  	} 
			
			   String imageDir = multipart.getLocalPath( "myUploadObject" );
			   imageDir = imageDir.substring(0,imageDir.lastIndexOf(File.separator));			   
			   File file = new File( imageDir,imageName);				  
			   new File( multipart.getLocalPath( "myUploadObject" ) ).renameTo( file );
			   File[] file_ = {file}; 
			
			if(protein_img_vec.size() == 0){// insert only
			    protein_img_id = mySession.getDatabaseUID("RNA_QUALITY_IMAGE");
			    protein_img.setRna_quality_image_id(protein_img_id);
			    String imageType = uploadObject.substring(uploadObject.indexOf("."));
			    protein_img.setImage_file_id(protein_img_id+imageType);
				protein_img.setProtein_id(protein.getProtein_id());
				protein_img.setImage_type("Protein_array_image");
				protein_img.setOriginal_image_name(uploadObject);
			    protein_img.insert();  				
				  	
		  	      FTPUtil.transfer(file_, null, protein_img.getRna_quality_image_id(),url, path,username,pwd);	
		          file.delete();
				  }	 // end of insert
				
		   else if (protein_img_vec.size() == 1){// need to see if img needs to be updated
		    
			   if(oldOriginalImgName != null && !oldOriginalImgName.equals(uploadObject)){// replace the img			      
				   protein_img.setOriginal_image_name(uploadObject);	
				   String imageType = uploadObject.substring(uploadObject.indexOf("."));				   
				   protein_img.setImage_file_id(protein_img_id+imageType);	
				   protein_img.updateByKey(); 		   			    
				   File file2 = new File(oldImgFileId);	//getOriginalimagename()			
			       File[] file2_ = {file2};				  				  				 			 
		           FTPUtil.transfer(file_, file2_,protein_img.getRna_quality_image_id(),url, path,username,pwd);	
			       file2.delete();
				    }				    
			     }				    
			 }
			 
			 Vector protein_img_vec = new Vector();							  
			 if(protein.getProtein_id() != null){			  
			   String where = "PROTEIN_ID = " +protein.getProtein_id()+ " AND IMAGE_TYPE ="+"\'"+"Protein_array_image"+"\'" + "AND IMAGE_FILE_ID is not null";
			   protein_img_vec = protein_img.retrieveAllWhere(where);				  
			   }
							 
			 if(protein_img_vec.size()==1){
			   for(int j=0; j<protein_img_vec.size();j++){
				   protein_img = (Rna_quality_image)protein_img_vec.elementAt(j);	
				   protein_img_id = protein_img.getRna_quality_image_id();   
			   }
			  if(multipart.getParameter("file_title") != null)	{
			     protein_img.setImage_title(multipart.getParameter("file_title"));
			     }
		      if(multipart.getParameter("file_description") != null)	{
			     protein_img.setImage_desc(multipart.getParameter("file_description"));
			    }
			
			  protein_img.setRna_quality_image_id(protein_img_id);	
			  protein_img.updateByKey();   
			  } 			 
			  // end if inserting or updating protein array image
		     
			// end if uploadObject is not null for both inserting and updating trace files
			 // end of protein trace file uplaod			 
			 
		
			 
			 multipart.cleanUp();			  
	
		
	// end of image upload
	
	
	
	
	try{		   
	  session.setAttribute("gov.nih.nci.qi.id",id);
	   Status = protein.updateByKey();
	}
	catch(Exception e){ 
	}
	String label = protein.getProtein_subsample_id()+"(Lance Liotta Lab)";	
	mySession.setObjectID("PROTEIN", proteinId, protein.getProtein_subsample_id());
	%>
	 <jsp:forward page="Submission.jsp"/>
	<% //response.sendRedirect("Submission.jsp");
	%>

<%   }	
   }	
}catch (ByteLimitExceededException ble)
  	{
  		Vector byte_errors = new Vector();
		byte_errors.add("Uploaded file cannot be larger than 5 MB");				
		request.setAttribute("nci.mmhcc.MissingFeilds",byte_errors);%>
		<jsp:forward page="missing_required_feilds.jsp"/>
  <%} 	
  catch (EOFException eof)
  	{
  	    Vector byte_errors = new Vector();
		byte_errors.add("Uploaded file cannot be larger than 5 MB");				
		request.setAttribute("nci.mmhcc.MissingFeilds",byte_errors);%>
		<jsp:forward page="missing_required_feilds.jsp"/>
  <%} %>	


   
