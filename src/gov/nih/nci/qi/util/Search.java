/*L
 * Copyright SAIC
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import gov.nih.nci.qi.DatabaseSetup;
import java.io.IOException;
import java.io.PrintStream;
import java.util.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;

public class Search extends HttpServlet
{

    public Search()
    {
    }

    public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        String s = httpservletrequest.getParameter("search");
        if(s != null)
        {
            ids = httpservletrequest.getParameter(s);
            doSearch(httpservletrequest, httpservletresponse);
        } else
        {
            RequestDispatcher requestdispatcher = httpservletrequest.getRequestDispatcher("search.jsp");
            requestdispatcher.forward(httpservletrequest, httpservletresponse);
        }
    }

    public void doPost(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        doGet(httpservletrequest, httpservletresponse);
    }

    private void doSearch(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
        throws ServletException, IOException
    {
        String s = null;
        String s1 = null;
        Enumeration enumeration = httpservletrequest.getParameterNames();
        Vector vector = new Vector();
        for(; enumeration.hasMoreElements(); System.out.println((new StringBuilder()).append(vector.lastElement()).append(":").append(httpservletrequest.getParameter((String)vector.lastElement())).toString()))
            vector.add((String)enumeration.nextElement());

        String s2 = httpservletrequest.getParameter("core_sample_Id");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s2.equalsIgnoreCase("All"))
                s1 = " qiSample.LABTRAK_ID is not null ";
            else
                s1 = (new StringBuilder()).append(" qiSample.LABTRAK_ID = '").append(s2).append("'").toString();
            if(s == null)
                s = s1;
            else
            if(s1 != null)
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        String as[] = httpservletrequest.getParameterValues("required_institution");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(s2.equalsIgnoreCase("All"))
            {
                s1 = "qiSample.SAMPLE_GENERATING_INSTITUTE_ID is not null ";
            } else
            {
                s1 = null;
                for(int i = 0; i < as.length; i++)
                {
                    s2 = as[i];
                    if(s2 == null || s2.trim().length() <= 0)
                        continue;
                    if(s1 == null)
                        s1 = (new StringBuilder()).append("  qiSample.SAMPLE_GENERATING_INSTITUTE_ID = ").append(s2.trim()).toString();
                    else
                        s1 = (new StringBuilder()).append(s1).append(" or qiSample.SAMPLE_GENERATING_INSTITUTE_ID = ").append(s2.trim()).toString();
                }

            }
            s1 = (new StringBuilder()).append("(").append(s1).append(" )").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("timePoint");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(s2.equalsIgnoreCase("All"))
            {
                s1 = "qiSample.TIMEPOINT_NAME is not null ";
            } else
            {
                s1 = null;
                for(int j = 0; j < as.length; j++)
                {
                    s2 = as[j];
                    if(s2 == null || s2.trim().length() <= 0)
                        continue;
                    if(s1 == null)
                        s1 = (new StringBuilder()).append("  qiSample.TIMEPOINT_NAME = '").append(s2.trim()).append("'").toString();
                    else
                        s1 = (new StringBuilder()).append(s1).append(" or qiSample.TIMEPOINT_NAME = '").append(s2.trim()).append("'").toString();
                }

            }
            s1 = (new StringBuilder()).append("(").append(s1).append(" )").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        String s4 = httpservletrequest.getParameter("accrualfrom");
        String s5 = httpservletrequest.getParameter("accrualto");
        s1 = null;
        if(ids != null)
        {
            Object obj = new ArrayList();
            obj = Arrays.asList(ids.split(","));
            for(int j1 = 0; j1 < ((List) (obj)).size(); j1++)
            {
                Integer.parseInt((String)((List) (obj)).get(j1));
                System.out.println(((List) (obj)).get(j1));
            }

            String s7 = "";
            s1 = "(qiSample.PATIENT_ACCRUAL IN ( ";
            for(int l1 = 0; l1 < ((List) (obj)).size(); l1++)
                s7 = (new StringBuilder()).append(s7).append(((List) (obj)).get(l1)).append(",").toString();

            int i2 = s7.lastIndexOf(",");
            s7 = s7.substring(0, i2);
            s1 = (new StringBuilder()).append(s1).append(s7).append("))").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        if(s4 != null && s4.trim().length() > 0 && s5 != null && s5.trim().length() > 0)
            try
            {
                int k = Integer.parseInt(s4);
                int k1 = Integer.parseInt(s5);
                if(k1 >= k)
                {
                    s1 = (new StringBuilder()).append(" (qiSample.PATIENT_ACCRUAL >= ").append(k).append(" AND qiSample.PATIENT_ACCRUAL <= ").append(k1).append(" )").toString();
                    if(s == null)
                        s = s1;
                    else
                        s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
                }
            }
            catch(NumberFormatException numberformatexception) { }
        if(s4 != null && s4.trim().length() > 0 && (s5 == null || s5 != null && s5.trim().length() == 0))
            try
            {
                int l = Integer.parseInt(s4);
                s1 = (new StringBuilder()).append(" (qiSample.PATIENT_ACCRUAL >= ").append(l).append(" )").toString();
                if(s == null)
                    s = s1;
                else
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
            catch(NumberFormatException numberformatexception1) { }
        if((s4 == null || s4 != null && s4.trim().length() == 0) && s5 != null && s5.trim().length() > 0)
            try
            {
                int i1 = Integer.parseInt(s5);
                s1 = (new StringBuilder()).append(" (qiSample.PATIENT_ACCRUAL <= ").append(i1).append(" )").toString();
                if(s == null)
                    s = s1;
                else
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
            catch(NumberFormatException numberformatexception2) { }
        s1 = null;
        s2 = httpservletrequest.getParameter("process_date_start");
        String s3 = httpservletrequest.getParameter("process_date_end");
        if(s2 != null && s2.trim().length() > 0 && s3.equals(""))
            s1 = (new StringBuilder()).append(" UPPER (qiSample.process_date) >= TO_DATE ('").append(s2).append("','mm/dd/yyyy')").toString();
        if(s2 != null && s2.trim().length() > 0 && s3 != null && s3.trim().length() > 0)
            s1 = (new StringBuilder()).append(" UPPER (qiSample.process_date) >= TO_DATE ('").append(s2).append("','mm/dd/yyyy') and UPPER (q.process_date) <= TO_DATE (").append("'").append(s3).append("','mm/dd/yyyy')").toString();
        if(s3 != null && s3.trim().length() > 0 && s2.equals(""))
            s1 = (new StringBuilder()).append(" UPPER (qiSample.process_date) <= TO_DATE ('").append(s3).append("','mm/dd/yyyy')").toString();
        if(s == null)
            s = s1;
        else
        if(s1 != null)
            s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        s1 = null;
        s2 = httpservletrequest.getParameter("coreType");
        if(s2 != null && s2.trim().length() > 0)
            s1 = (new StringBuilder()).append(" UPPER (qiSample.CORE_TYPE) like UPPER('%").append(s2.trim()).append("%') ").toString();
        if(s == null)
            s = s1;
        else
        if(s1 != null)
            s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        s1 = null;
        s2 = httpservletrequest.getParameter("tumorPresence");
        String s6 = httpservletrequest.getParameter("Tpchoice1");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s6 != null && !s6.equals(""))
                s1 = (new StringBuilder()).append("  TO_NUMBER(qiSample.TUMORPRESENCE) ").append(s6).append(s2.trim()).toString();
            else
                s1 = (new StringBuilder()).append("  TO_NUMBER(qiSample.TUMORPRESENCE) = ").append(s2.trim()).toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("nontumor");
        String s8 = httpservletrequest.getParameter("Tpchoice2");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s8 != null && !s8.equals(""))
                s1 = (new StringBuilder()).append("  TO_NUMBER(qiSample.NONTUMOR) ").append(s8).append(s2.trim()).toString();
            else
                s1 = (new StringBuilder()).append("  TO_NUMBER(qiSample.NONTUMOR) = ").append(s2.trim()).toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("frozen_Touch_Prep");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(!s2.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.FROZEN_TOUCH_PREP= '").append(s2.trim()).append("'").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("frozen_H_E");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(!s2.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.FROZEN_H_E = '").append(s2.trim()).append("'").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("lab");
        String s9 = null;
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s2.equals("2"))
            {
                s1 = "  qiSample.sample_id in (select sample_id from QI_DNA where DNA_SUBSAMPLE_ID LIKE ('%-2')) ";
                s9 = "2";
            } else
            if(s2.equals("3"))
            {
                s1 = "  qiSample.sample_id in (select sample_id from QI_DNA where DNA_SUBSAMPLE_ID LIKE ('%-3')) ";
                s9 = "3";
            } else
            if(s2.equals("4"))
                s1 = "  qiSample.sample_id in (select sample_id from QI_RNA where RNA_SUBSAMPLE_ID is not null )";
            else
            if(s2.equals("5"))
                s1 = "  qiSample.sample_id in (select sample_id from QI_PROTEIN where PROTEIN_SUBSAMPLE_ID is not null )";
            httpservletrequest.getSession().setAttribute("gov.nih.nci.qi.labId", s9);
            if(s == null)
                s = s1;
            else
            if(s1 != null)
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("H_E");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(!s2.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.H_E_REVIEW in (select  H_E_REVIEW from QI_H_E_REVIEW  where H_E_REVIEW = '").append(s2.trim()).append("')").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("usability");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(!s2.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.USABILITY in (select  USABILITY from QI_USABILITY  where USABILITY = '").append(s2.trim()).append("')").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("touchPreps");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(!s2.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.TOUCHPREP_RECVD in (select  TOUCHPREP_RECVD from QI_TOUCHPREP_RECVD  where TOUCHPREP_RECVD = '").append(s2.trim()).append("')").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("dnaQuality");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = "  qiSample.DNA_EXTRACTION_QUALITY in (select QUALITY_NAME from QI_QUALITY where QUALITY_ID is not null ) ";
                } else
                {
                    s1 = null;
                    s1 = "  qiSample.DNA_EXTRACTION_QUALITY in (select QUALITY_NAME from QI_QUALITY where QUALITY_ID in(";
                    for(int j2 = 0; j2 < as.length; j2++)
                    {
                        s2 = as[j2];
                        if(s2 != null && s2.trim().length() > 0)
                            s1 = (new StringBuilder()).append(s1).append(s2.trim()).append(",").toString();
                    }

                    int k2 = s1.lastIndexOf(",");
                    s1 = s1.substring(0, k2);
                    s1 = (new StringBuilder()).append(s1).append("))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("pcr_rating");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = " qiSample.sample_id in (select sample_id from QI_DNA where PCR_RATING  is not null  ) ";
                } else
                {
                    s1 = null;
                    s1 = "  qiSample.sample_id in (select sample_id from QI_DNA where PCR_RATING  in(";
                    String s10 = "";
                    for(int l4 = 0; l4 < as.length; l4++)
                    {
                        s2 = as[l4];
                        if(s2 != null && s2.trim().length() > 0)
                        {
                            s1 = (new StringBuilder()).append(s1).append("'").append(s2.trim()).append("',").toString();
                            s10 = (new StringBuilder()).append(s10).append("'").append(s2.trim()).append("',").toString();
                        }
                    }

                    int i5 = s1.lastIndexOf(",");
                    int j5 = s10.lastIndexOf(",");
                    s10 = s10.substring(0, j5);
                    httpservletrequest.getSession().setAttribute("gov.nih.nci.qi.pcr_rating", s10);
                    s1 = s1.substring(0, i5);
                    s1 = (new StringBuilder()).append(s1).append("))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("rnaProcessQuality");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = "  qiSample.RNA_EXTRACTION_QUALITY in (select QUALITY_NAME from QI_QUALITY where QUALITY_ID is not null) ";
                } else
                {
                    s1 = null;
                    s1 = "   qiSample.RNA_EXTRACTION_QUALITY in (select QUALITY_NAME from QI_QUALITY where QUALITY_ID in(";
                    for(int l2 = 0; l2 < as.length; l2++)
                    {
                        s2 = as[l2];
                        if(s2 != null && s2.trim().length() > 0)
                            s1 = (new StringBuilder()).append(s1).append(s2.trim()).append(",").toString();
                    }

                    int i3 = s1.lastIndexOf(",");
                    s1 = s1.substring(0, i3);
                    s1 = (new StringBuilder()).append(s1).append("))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("rnaAnalysisQuality");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = "  qiSample.sample_id in (select sample_id from QI_RNA where RNA_ANALYSIS_QUALITY is not null) ";
                } else
                {
                    s1 = null;
                    s1 = "   qiSample.sample_id in (select sample_id from QI_RNA where RNA_ANALYSIS_QUALITY  in(";
                    for(int j3 = 0; j3 < as.length; j3++)
                    {
                        s2 = as[j3];
                        if(s2 != null && s2.trim().length() > 0)
                            s1 = (new StringBuilder()).append(s1).append("'").append(s2.trim()).append("',").toString();
                    }

                    int k3 = s1.lastIndexOf(",");
                    s1 = s1.substring(0, k3);
                    s1 = (new StringBuilder()).append(s1).append("))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("intensity");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = " qiSample.sample_id in (select sample_id from QI_DNA  where DNA_ID in(select DNA_ID from QI_DNA_LABELING where INTENSITY_ID  is not null )) ";
                } else
                {
                    s1 = null;
                    s1 = "  qiSample.sample_id in (select sample_id from QI_DNA  where DNA_ID IN (SELECT dna_id FROM ( SELECT DNA_ID, MAX(labeling_attempt_id) labeling_attempt_id  FROM   QI_DNA_LABELING  GROUP BY dna_ID  INTERSECT  SELECT DNA_ID, labeling_attempt_id  FROM   QI_DNA_LABELING   WHERE  INTENSITY_ID in( ";
                    for(int l3 = 0; l3 < as.length; l3++)
                    {
                        s2 = as[l3];
                        if(s2 != null && s2.trim().length() > 0)
                            s1 = (new StringBuilder()).append(s1).append(s2.trim()).append(",").toString();
                    }

                    int i4 = s1.lastIndexOf(",");
                    s1 = s1.substring(0, i4);
                    s1 = (new StringBuilder()).append(s1).append("))))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        as = httpservletrequest.getParameterValues("cgh_quality");
        if(as != null && as.length > 0)
        {
            s2 = as[0];
            if(!s2.equalsIgnoreCase("-1"))
            {
                if(s2.equalsIgnoreCase("0"))
                {
                    s1 = "  qiSample.sample_id in (select sample_id from QI_DNA  where DNA_ID in(select DNA_ID from QI_CGH_HYBRIDIZATION where CGH_QUALITY_ID is not null )) ";
                } else
                {
                    s1 = null;
                    s1 = "  qiSample.sample_id in (select sample_id from QI_DNA  where DNA_ID IN (SELECT dna_id FROM ( SELECT DNA_ID, MAX(CGH_HYB_ATTEMPT_ID) cgh_hyb_attempt_id  FROM   QI_CGH_HYBRIDIZATION  GROUP BY dna_ID  INTERSECT  SELECT DNA_ID, CGH_HYB_ATTEMPT_ID  FROM   QI_CGH_HYBRIDIZATION   WHERE  CGH_QUALITY_ID in( ";
                    for(int j4 = 0; j4 < as.length; j4++)
                    {
                        s2 = as[j4];
                        if(s2 != null && s2.trim().length() > 0)
                            s1 = (new StringBuilder()).append(s1).append(s2.trim()).append(",").toString();
                    }

                    int k4 = s1.lastIndexOf(",");
                    s1 = s1.substring(0, k4);
                    s1 = (new StringBuilder()).append(s1).append("))))").toString();
                }
                if(s == null)
                    s = s1;
                else
                if(s1 != null)
                    s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
            }
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("efficiency");
        String s11 = httpservletrequest.getParameter("Tpchoice3");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s11 != null && !s11.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.sample_id in (select sample_id from QI_PROTEIN where to_number(MICODISSECTION_EFFICIENCY) ").append(s11).append(s2.trim()).append(") ").toString();
            else
                s1 = (new StringBuilder()).append("  qiSample.sample_id in (select sample_id from QI_PROTEIN where to_number(MICODISSECTION_EFFICIENCY) =").append(s2.trim()).append("  ) ").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        s1 = null;
        s2 = httpservletrequest.getParameter("tumor_presence");
        String s12 = httpservletrequest.getParameter("Tpchoice4");
        if(s2 != null && s2.trim().length() > 0)
        {
            if(s12 != null && !s12.equals(""))
                s1 = (new StringBuilder()).append("  qiSample.sample_id in (select sample_id from QI_PROTEIN where to_number(PROTEIN_SAMPLE_TUMOR_PRESENCE) ").append(s12).append(s2.trim()).append(") ").toString();
            else
                s1 = (new StringBuilder()).append("  qiSample.sample_id in (select sample_id from QI_PROTEIN where to_number(PROTEIN_SAMPLE_TUMOR_PRESENCE) =").append(s2.trim()).append("  ) ").toString();
            if(s == null)
                s = s1;
            else
                s = (new StringBuilder()).append(s).append(" and ").append(s1).toString();
        }
        if(s == null && s1 == null)
            s = " qiSample.LABTRAK_ID is not null ";
        if(s != null && s.indexOf("and null") != -1)
            s = s.substring(0, s.indexOf("and null"));
        httpservletrequest.getSession().setAttribute("gov.nih.nci.qi.where", s);
        System.out.println((new StringBuilder()).append("where").append(s).toString());
        RequestDispatcher requestdispatcher = httpservletrequest.getRequestDispatcher("searchResults.jsp");
        requestdispatcher.forward(httpservletrequest, httpservletresponse);
    }

    private DatabaseSetup database;
    String ids;
}
