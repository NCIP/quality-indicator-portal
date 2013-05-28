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

import gov.nih.nci.qi.KeyRetriever;
import gov.nih.nci.qi.db.*;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.*;

// Referenced classes of package gov.nih.nci.qi.util:
//            Label

public class QISession
{

    public QISession()
    {
        labtrak_id = null;
        sampleID = null;
        isPopulated = false;
        email = null;
        submitterUID = null;
        memberOf = null;
        submitter = new Submitter();
        submitters = new Vector();
        dna = new Vector();
        rna = new Vector();
        protein = new Vector();
        objectUIDs = new Hashtable();
        objectUIDs.put("SUBMITTER", submitters);
        objectUIDs.put("DNA", dna);
        objectUIDs.put("RNA", rna);
        objectUIDs.put("PROTEIN", protein);
        IDs = new Hashtable();
    }

    public QISession(Long long1)
    {
        labtrak_id = null;
        sampleID = null;
        isPopulated = false;
        email = null;
        submitterUID = null;
        memberOf = null;
        submitter = new Submitter();
        submitters = new Vector();
        dna = new Vector();
        rna = new Vector();
        protein = new Vector();
        objectUIDs = new Hashtable();
        objectUIDs.put("SUBMITTER", submitters);
        objectUIDs.put("DNA", dna);
        objectUIDs.put("RNA", rna);
        objectUIDs.put("PROTEIN", protein);
        IDs = new Hashtable();
        try
        {
            if(long1 != null)
            {
                submitterUID = long1;
                submitter.retrieveByIndex(long1);
            }
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in Lookup");
            System.err.println(sqlexception);
        }
    }

    public void clearAll()
    {
        isPopulated = false;
        dna.clear();
        rna.clear();
        protein.clear();
    }

    public Long getSubmitterUID()
    {
        return submitterUID;
    }

    public String getSubmitterLastName()
    {
        if(submitterUID != null)
            return submitter.getLastname();
        else
            return null;
    }

    public String getSubmitterFirstName()
    {
        if(submitterUID != null)
            return submitter.getFirstname();
        else
            return null;
    }

    public String getSubmitterUserName()
    {
        if(submitterUID != null)
            return submitter.getUsername();
        else
            return null;
    }

    public String getSubmitterPassword()
    {
        if(submitterUID != null)
            return submitter.getPassword();
        else
            return null;
    }

    public String getSubmitterEmail()
    {
        if(submitterUID != null)
            return submitter.getEmail();
        else
            return null;
    }

    public void setSample(String s, Long long1)
    {
        if(s == null || long1 == null)
            System.out.println((new StringBuilder()).append("check if sample name  is correct:").append(s).append(" id: ").append(long1).toString());
        labtrak_id = s;
        sampleID = long1;
    }

    public void setSubmitterUID(Long long1)
    {
        try
        {
            if(long1 != null)
            {
                submitterUID = long1;
                submitter.retrieveByIndex(long1);
                System.out.println((new StringBuilder()).append("email is :").append(submitter.getEmail()).toString());
            }
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in Lookup");
            System.err.println(sqlexception);
        }
    }

    public void setSampleId(Long long1)
    {
        sampleID = long1;
    }

    public void setLabtrak_id(String s)
    {
        labtrak_id = s;
    }

    public Long getSampleId()
    {
        return sampleID;
    }

    public String getLabtrak_id()
    {
        return labtrak_id;
    }

    public static Long getDatabaseUID(String s)
    {
        KeyRetriever keyretriever = new KeyRetriever();
        return keyretriever.getNextKey(s);
    }

    public boolean setObjectID(String s, Long long1, String s1)
    {
        System.out.println((new StringBuilder()).append("***************-------:").append(s1).toString());
        boolean flag = false;
        Vector vector = (Vector)objectUIDs.get(s);
        System.out.println((new StringBuilder()).append(" list size :").append(vector.size()).toString());
        for(Enumeration enumeration = vector.elements(); enumeration.hasMoreElements(); System.out.println((new StringBuilder()).append("CHECK TABLE NAME:").append(enumeration.nextElement()).toString()));
        if(vector != null)
        {
            boolean flag1 = false;
            for(int i = 0; i < vector.size(); i++)
            {
                Label label = (Label)(Label)vector.elementAt(i);
                System.out.println((new StringBuilder()).append("inside loop").append(vector.elementAt(i).toString()).toString());
                System.out.println((new StringBuilder()).append(" inside loop id ").append(long1).toString());
                if(long1.equals(label.getId()))
                {
                    vector.setElementAt(new Label(s1, long1), i);
                    System.out.println((new StringBuilder()).append("performing update ").append(vector.elementAt(i).toString()).toString());
                    flag1 = true;
                }
            }

            if(!flag1)
            {
                System.out.println((new StringBuilder()).append("THe label name :").append(s1).append(" and id :").append(long1).toString());
                vector.add(new Label(s1, long1));
            }
            System.out.println(vector.lastElement().toString());
            flag = true;
        } else
        {
            System.out.println((new StringBuilder()).append("check if name is correct:").append(s).append(" label: ").append(s1).toString());
        }
        return flag;
    }

    public boolean setObjectID(String s, Long long1, String s1, Long long2)
    {
        boolean flag = false;
        Vector vector = (Vector)objectUIDs.get(s);
        if(vector != null)
        {
            boolean flag1 = false;
            for(int i = 0; i < vector.size(); i++)
            {
                Label label = (Label)(Label)vector.elementAt(i);
                System.out.println((new StringBuilder()).append("inside loop2 ").append(vector.elementAt(i).toString()).toString());
                System.out.println((new StringBuilder()).append("relatedid ").append(long1).toString());
                if(long1.equals(label.getId()))
                {
                    vector.setElementAt(new Label(s1, long1, long2), i);
                    System.out.println((new StringBuilder()).append("performing update ").append(vector.elementAt(i).toString()).toString());
                    flag1 = true;
                }
            }

            if(!flag1)
            {
                vector.add(new Label(s1, long1, long2));
                System.out.println((new StringBuilder()).append("performing add ").append(vector.lastElement().toString()).toString());
            }
            System.out.println(vector.lastElement().toString());
            flag = true;
        } else
        {
            System.out.println((new StringBuilder()).append("check if name is correct:").append(s).append(" label: ").append(s1).toString());
        }
        return flag;
    }

    public Enumeration getObjectID(String s)
    {
        Enumeration enumeration = null;
        Vector vector = (Vector)objectUIDs.get(s);
        if(vector != null)
            enumeration = vector.elements();
        else
            System.out.println((new StringBuilder()).append("check if name is correct:").append(s).toString());
        return enumeration;
    }

    public Long setID(String s, Long long1)
    {
        Long long2 = null;
        IDs.put(s, long1);
        System.out.println((new StringBuilder()).append("the id is:").append(IDs.toString()).toString());
        if(!IDs.isEmpty())
            long2 = (Long)IDs.get(s);
        return long2;
    }

    public Long getID(String s)
    {
        return (Long)IDs.get(s);
    }

    public void populateRecords(Long long1)
    {
        try
        {
            Qi_dna qi_dna = new Qi_dna();
            Vector vector = qi_dna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
            for(int i = 0; i < vector.size(); i++)
            {
                Qi_dna qi_dna1 = (Qi_dna)vector.elementAt(i);
                if(qi_dna1.getDna_subsample_id() == null && qi_dna1.getDna_reading() != null && qi_dna1.getDna_quantity_unit() != null)
                    setObjectID("DNA", qi_dna1.getDna_id(), (new StringBuilder()).append(qi_dna1.getDna_reading()).append(" ").append(qi_dna1.getDna_quantity_unit()).toString());
                else
                    setObjectID("DNA", qi_dna1.getDna_id(), qi_dna1.getDna_subsample_id());
            }

            Qi_rna qi_rna = new Qi_rna();
            Vector vector1 = qi_rna.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
            for(int j = 0; j < vector1.size(); j++)
            {
                Qi_rna qi_rna1 = (Qi_rna)vector1.elementAt(j);
                if(qi_rna1.getRna_subsample_id() == null && qi_rna1.getRna_reading() != null && qi_rna1.getRna_quantity_unit() != null)
                    setObjectID("RNA", qi_rna1.getRna_id(), (new StringBuilder()).append(qi_rna1.getRna_reading()).append(" ").append(qi_rna1.getRna_quantity_unit()).toString());
                else
                    setObjectID("RNA", qi_rna1.getRna_id(), qi_rna1.getRna_subsample_id());
            }

            Qi_protein qi_protein = new Qi_protein();
            Vector vector2 = qi_protein.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(long1).toString());
            for(int k = 0; k < vector2.size(); k++)
            {
                Qi_protein qi_protein1 = (Qi_protein)vector2.elementAt(k);
                setObjectID("PROTEIN", qi_protein1.getProtein_id(), qi_protein1.getProtein_subsample_id());
            }

        }
        catch(Exception exception)
        {
            System.out.println("Error in populateRecords () method");
        }
    }

    private String labtrak_id;
    private Long sampleID;
    private boolean isPopulated;
    private String email;
    private Long submitterUID;
    private Vector memberOf;
    private Submitter submitter;
    private Vector submitters;
    private Vector dna;
    private Vector rna;
    private Vector protein;
    private Hashtable objectUIDs;
    private Hashtable IDs;
}
