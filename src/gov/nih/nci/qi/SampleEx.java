/*L
 * Copyright SAIC
 *
 * Distributed under the OSI-approved BSD 3-Clause License.
 * See http://ncip.github.com/quality-indicator-portal/LICENSE.txt for details.
 */

// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi;

import gov.nih.nci.qi.db.Qi_sample;
import java.io.PrintStream;
import java.sql.*;
import java.util.Vector;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class SampleEx extends Qi_sample
{

    public SampleEx()
    {
    }

    public Vector retrieveAllWhere(HttpServletRequest httpservletrequest, HttpSession httpsession, String s)
        throws SQLException
    {
        boolean flag = false;
        Vector vector = new Vector();
        StringBuffer stringbuffer = new StringBuffer();
        String s7 = (String)httpsession.getAttribute("gov.nih.nci.gi.requestType");
        try
        {
            if(connect())
            {
                if(s.indexOf("%-2") != -1)
                {
                    String s1 = "select  qiSample.SAMPLE_ID, qiSample.LABTRAK_ID, qiSample.SUBMITTERKEY, qiSample.CORENUMBER,  qiSample.TIMESTAMP, qiSample.TUMORPRESENCE, qiSample.NONTUMOR, qiSample.QI_COMMENT, qiSample.H_E_REVIEW, qiSample.USABILITY, qiSample.TOUCHPREP_RECVD,qiSample.PATIENTDID,qiSample.TIMEPOINT_NAME,qiSample.PATIENT_ACCRUAL,qiSample.PROCESS_DATE,qiSample.CORE_COLLECT_DATE, qiSample.CORE_TYPE, qiSample.SAMPLE_GENERATING_INSTITUTE_ID, qiSample.FROZEN_TOUCH_PREP, qiSample.FROZEN_H_E, qiSample.DNA_PROCESS_DATE,qiSample.DNA_READING,qiSample.DNA_QUANTITY_UNIT,qiSample.DNA_INSTITUTION_ID,qiSample.DNA_INSTRUMENT_ID,qiSample.DNA_EXTRACTION_QUALITY,qiSample.RNA_PROCESS_DATE,qiSample.RNA_READING,qiSample.RNA_QUANTITY_UNIT,qiSample.RNA_INSTITUTION_ID,qiSample.RNA_INSTRUMENT_ID,qiSample.RNA_EXTRACTION_QUALITY,i.INSTITUTE_NAME, sb.LASTNAME, sb.FIRSTNAME, sb.EMAIL";
                    String s3 = " from QI_SAMPLE qiSample, QI_INSTITUTION i, SUBMITTER sb";
                    String s5 = " qiSample.SUBMITTERKEY = sb.SUBMITTERKEY and qiSample.SAMPLE_GENERATING_INSTITUTE_ID = i.INSTITUTION_ID ";
                    stringbuffer.append((new StringBuilder()).append(s1).append(s3).append(" where ").append(s).append(" and sample_id IN (SELECT sample_id FROM ").append(" QI_DNA WHERE dna_id IN (SELECT dna_id FROM QI_CGH_HYBRIDIZATION))  and  ").append(s5).toString());
                    stringbuffer.append(" UNION ");
                    stringbuffer.append((new StringBuilder()).append(s1).append(s3).append(" where ").append(s).append(" AND sample_id IN (SELECT sample_id FROM ").append(" QI_DNA WHERE dna_id IN (SELECT dna_id FROM QI_DNA_LABELING)) and ").append(s5).toString());
                } else
                {
                    String s2 = "select qiSample.SAMPLE_ID, qiSample.LABTRAK_ID, qiSample.SUBMITTERKEY, qiSample.CORENUMBER,  qiSample.TIMESTAMP, qiSample.TUMORPRESENCE, qiSample.NONTUMOR, qiSample.QI_COMMENT, qiSample.H_E_REVIEW, qiSample.USABILITY, qiSample.TOUCHPREP_RECVD,qiSample.PATIENTDID,qiSample.TIMEPOINT_NAME,qiSample.PATIENT_ACCRUAL,qiSample.PROCESS_DATE,qiSample.CORE_COLLECT_DATE, qiSample.CORE_TYPE, qiSample.SAMPLE_GENERATING_INSTITUTE_ID, qiSample.FROZEN_TOUCH_PREP, qiSample.FROZEN_H_E, qiSample.DNA_PROCESS_DATE,qiSample.DNA_READING,qiSample.DNA_QUANTITY_UNIT,qiSample.DNA_INSTITUTION_ID,qiSample.DNA_INSTRUMENT_ID,qiSample.DNA_EXTRACTION_QUALITY,qiSample.RNA_PROCESS_DATE,qiSample.RNA_READING,qiSample.RNA_QUANTITY_UNIT,qiSample.RNA_INSTITUTION_ID,qiSample.RNA_INSTRUMENT_ID,qiSample.RNA_EXTRACTION_QUALITY,i.INSTITUTE_NAME, sb.LASTNAME, sb.FIRSTNAME, sb.EMAIL";
                    String s4 = " from QI_SAMPLE qiSample, QI_INSTITUTION i, SUBMITTER sb";
                    String s6 = " qiSample.SUBMITTERKEY = sb.SUBMITTERKEY and qiSample.SAMPLE_GENERATING_INSTITUTE_ID = i.INSTITUTION_ID order by  qiSample.SAMPLE_ID ASC";
                    stringbuffer.append((new StringBuilder()).append(s2).append(s4).append(" where ").append(s).append(" and ").append(s6).toString());
                }
                System.err.println(stringbuffer.toString());
                ResultSet resultset = executeQuery(stringbuffer.toString());
                boolean flag1 = false;
                SampleEx sampleex;
                for(; resultset.next(); vector.addElement(sampleex))
                {
                    sampleex = new SampleEx();
                    sampleex.getFromResultSet(resultset);
                }

                disconnect();
            }
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("Error occurs in retrieveAllWhere of SampleEx class: ").append(exception).toString());
            exception.printStackTrace();
        }
        return vector;
    }

    public int count(String s)
        throws SQLException
    {
        int i = -1;
        if(connect())
        {
            String s1 = (new StringBuilder()).append("select count(DISTINCT PATIENT_ACCRUAL ) from QI_SAMPLE where ").append(s).toString();
            ResultSet resultset = executeQuery(s1);
            if(resultset.next())
                i = resultset.getInt(1);
            resultset.close();
            queryStatement.close();
            disconnect();
        }
        return i;
    }
}
