// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import gov.nih.nci.qi.db.*;
import java.io.File;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Vector;
import javax.servlet.*;
import javax.servlet.http.HttpServlet;

// Referenced classes of package gov.nih.nci.qi.util:
//            FTPUtil

public class DeleteRecord extends HttpServlet
{

    public DeleteRecord()
    {
        userDir = System.getProperty("user.dir");
        url = null;
        path = null;
        username = null;
        pwd = null;
        path2 = null;
        path3 = null;
    }

    public static synchronized DeleteRecord getInstance()
    {
        if(onlyInstance == null)
            onlyInstance = new DeleteRecord();
        return onlyInstance;
    }

    public void init(ServletConfig servletconfig)
        throws ServletException
    {
        super.init(servletconfig);
        try
        {
            ServletContext servletcontext = servletconfig.getServletContext();
            if(userDir.indexOf(":") != -1)
            {
                url = getServletConfig().getServletContext().getInitParameter("ftpur1_WIN");
                path = getServletConfig().getServletContext().getInitParameter("ftppath_WIN");
                path3 = (new StringBuilder()).append(path).append("/QI_Images/Protein_Array_Images").toString();
                path2 = (new StringBuilder()).append(path).append("/QI_Images/RNA_Gel_Images").toString();
                path = (new StringBuilder()).append(path).append("/QI_Images/RNA_Trace_Files").toString();
                username = getServletConfig().getServletContext().getInitParameter("ftpusername_WIN");
                pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_WIN");
            } else
            {
                url = getServletConfig().getServletContext().getInitParameter("ftpur1_UNIX");
                path = getServletConfig().getServletContext().getInitParameter("ftppath_UNIX");
                path3 = (new StringBuilder()).append(path).append("/QI_Images/Protein_Array_Images").toString();
                path2 = (new StringBuilder()).append(path).append("/QI_Images/RNA_Gel_Images").toString();
                path = (new StringBuilder()).append(path).append("/QI_Images/RNA_Trace_Files").toString();
                username = getServletConfig().getServletContext().getInitParameter("ftpusername_UNIX");
                pwd = getServletConfig().getServletContext().getInitParameter("ftppwd_UNIX");
            }
        }
        catch(Exception exception)
        {
            System.out.println(" Error in Init for DeleteRecord");
            exception.printStackTrace();
        }
    }

    public boolean deleteLabelingDNA_CGH_HYB(Long long1)
    {
        boolean flag = false;
        boolean flag1 = false;
        boolean flag2 = false;
        Qi_dna_labeling qi_dna_labeling = new Qi_dna_labeling();
        Qi_cgh_hybridization qi_cgh_hybridization = new Qi_cgh_hybridization();
        Vector vector = new Vector();
        Vector vector2 = new Vector();
        if(long1 != null)
            try
            {
                Vector vector1 = qi_dna_labeling.retrieveAllWhere((new StringBuilder()).append(" DNA_ID = ").append(long1).toString());
                for(int i = 0; i < vector1.size(); i++)
                {
                    Qi_dna_labeling qi_dna_labeling1 = (Qi_dna_labeling)vector1.elementAt(i);
                    flag1 = qi_dna_labeling1.deleteByKey(qi_dna_labeling1.getLabeling_id());
                }

                Vector vector3 = qi_cgh_hybridization.retrieveAllWhere((new StringBuilder()).append(" DNA_ID = ").append(long1).toString());
                for(int j = 0; j < vector3.size(); j++)
                {
                    Qi_cgh_hybridization qi_cgh_hybridization1 = (Qi_cgh_hybridization)vector3.elementAt(j);
                    flag2 = qi_cgh_hybridization1.deleteByKey(qi_cgh_hybridization1.getHybridization_id());
                }

            }
            catch(SQLException sqlexception) { }
        if(flag1 || flag2)
            flag = true;
        return flag;
    }

    public boolean deleteDNA(Long long1)
    {
        boolean flag = false;
        Qi_dna qi_dna = new Qi_dna();
        if(long1 != null)
            try
            {
                flag = qi_dna.deleteByKey(long1);
            }
            catch(SQLException sqlexception)
            {
                System.out.println((new StringBuilder()).append("Error in deleteDNA method() : ").append(sqlexception).toString());
            }
        return flag;
    }

    public boolean deleteRNA(Long long1)
    {
        boolean flag = false;
        Qi_rna qi_rna = new Qi_rna();
        if(long1 != null)
            try
            {
                Rna_quality_image rna_quality_image = new Rna_quality_image();
                Object obj = null;
                Object obj1 = null;
                Vector vector = new Vector();
                Vector vector2 = new Vector();
                Object obj2 = null;
                Object obj3 = null;
                File afile[] = null;
                File afile1[] = null;
                Vector vector4 = new Vector();
                vector4 = qi_rna.retrieveAllWhere((new StringBuilder()).append("RNA_ID = ").append(long1).toString());
                for(int i = 0; i < vector4.size(); i++)
                {
                    Qi_rna qi_rna1 = (Qi_rna)vector4.elementAt(i);
                    long1 = qi_rna1.getRna_id();
                    if(long1 != null)
                    {
                        Vector vector1 = rna_quality_image.retrieveAllWhere((new StringBuilder()).append("RNA_ID = ").append(long1).append(" and IMAGE_TYPE = ").append("'").append("RNA_Trace").append("'").toString());
                        if(vector1.size() >= 1)
                        {
                            afile = new File[vector1.size()];
                            for(int j = 0; j < vector1.size(); j++)
                            {
                                rna_quality_image = (Rna_quality_image)vector1.elementAt(j);
                                String s = rna_quality_image.getImage_file_id();
                                afile[j] = new File(s);
                                rna_quality_image.deleteByKey(rna_quality_image.getRna_quality_image_id());
                            }

                        }
                        Vector vector3 = rna_quality_image.retrieveAllWhere((new StringBuilder()).append("RNA_ID = ").append(long1).append(" and IMAGE_TYPE = ").append("'").append("RNA_Gel").append("'").toString());
                        if(vector3.size() >= 1)
                        {
                            afile1 = new File[vector3.size()];
                            for(int k = 0; k < vector3.size(); k++)
                            {
                                rna_quality_image = (Rna_quality_image)vector3.elementAt(k);
                                String s1 = rna_quality_image.getImage_file_id();
                                afile1[k] = new File(s1);
                                rna_quality_image.deleteByKey(rna_quality_image.getRna_quality_image_id());
                            }

                        }
                        FTPUtil ftputil = new FTPUtil();
                        ftputil.deleteAllFiles(afile, afile1, url, path, path2, username, pwd);
                        flag = qi_rna1.deleteByKey(long1);
                    }
                }

            }
            catch(SQLException sqlexception)
            {
                System.out.println((new StringBuilder()).append("Error in deleteRNA() method: ").append(sqlexception).toString());
            }
        return flag;
    }

    public boolean deleteProtein(Long long1)
    {
        boolean flag = false;
        Qi_protein qi_protein = new Qi_protein();
        if(long1 != null)
            try
            {
                Rna_quality_image rna_quality_image = new Rna_quality_image();
                Object obj = null;
                Vector vector = new Vector();
                Object obj1 = null;
                File afile[] = null;
                Vector vector2 = new Vector();
                vector2 = qi_protein.retrieveAllWhere((new StringBuilder()).append("PROTEIN_ID = ").append(long1).toString());
                for(int i = 0; i < vector2.size(); i++)
                {
                    Qi_protein qi_protein1 = (Qi_protein)vector2.elementAt(i);
                    long1 = qi_protein1.getProtein_id();
                    if(long1 != null)
                    {
                        Vector vector1 = rna_quality_image.retrieveAllWhere((new StringBuilder()).append("PROTEIN_ID = ").append(long1).append(" and IMAGE_TYPE = ").append("'").append("Protein_array_image").append("'").toString());
                        if(vector1.size() >= 1)
                        {
                            afile = new File[vector1.size()];
                            for(int j = 0; j < vector1.size(); j++)
                            {
                                rna_quality_image = (Rna_quality_image)vector1.elementAt(j);
                                String s = rna_quality_image.getOriginal_image_name();
                                afile[j] = new File(s);
                                rna_quality_image.deleteByKey(rna_quality_image.getRna_quality_image_id());
                            }

                        }
                        FTPUtil.transfer(null, afile, rna_quality_image.getRna_quality_image_id(), url, path3, username, pwd);
                        flag = qi_protein1.deleteByKey(long1);
                    }
                }

            }
            catch(SQLException sqlexception)
            {
                System.out.println((new StringBuilder()).append("Error in deletePROTEIN() method: ").append(sqlexception).toString());
            }
        return flag;
    }

    public boolean checkDNA_RNA_Protein_Status(Long long1)
    {
        boolean flag = false;
        if(long1 != null)
            try
            {
                Qi_dna qi_dna = new Qi_dna();
                Qi_rna qi_rna = new Qi_rna();
                Qi_protein qi_protein = new Qi_protein();
                Vector vector = new Vector();
                Vector vector1 = new Vector();
                Vector vector2 = new Vector();
                vector = qi_dna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
                vector1 = qi_rna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
                vector2 = qi_protein.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
                if(vector.size() == 0 && vector1.size() == 0 && vector2.size() == 0)
                    flag = true;
            }
            catch(SQLException sqlexception)
            {
                System.out.println((new StringBuilder()).append("Error in checkDNA_RNA_Protein_Status() method: ").append(sqlexception).toString());
            }
        return flag;
    }

    public boolean deleteSample(Long long1)
    {
        boolean flag = false;
        Qi_sample qi_sample = new Qi_sample();
        Qi_dna qi_dna = new Qi_dna();
        Qi_rna qi_rna = new Qi_rna();
        Vector vector = new Vector();
        Vector vector1 = new Vector();
        try
        {
            if(long1 != null)
            {
                vector.clear();
                vector = qi_dna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
                vector1.clear();
                vector1 = qi_rna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
                if(vector.size() == 0 && vector1.size() == 0)
                {
                    System.out.println((new StringBuilder()).append("deleteStatus********** before :").append(flag).toString());
                    flag = qi_sample.deleteByKey(long1);
                    System.out.println((new StringBuilder()).append("deleteStatus**********: after ").append(flag).toString());
                }
            }
        }
        catch(SQLException sqlexception)
        {
            System.out.println((new StringBuilder()).append("Error in deleteSample() method: ").append(sqlexception).toString());
        }
        System.out.println((new StringBuilder()).append("deleteStatus**********:").append(flag).toString());
        return flag;
    }

    private String userDir;
    private String url;
    private String path;
    private String username;
    private String pwd;
    private String path2;
    private String path3;
    private static DeleteRecord onlyInstance = null;

}
