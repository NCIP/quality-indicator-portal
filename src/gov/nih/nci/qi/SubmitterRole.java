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

import gov.nih.nci.qi.constants.QIConstants;
import gov.nih.nci.qi.db.Submitter;
import gov.nih.nci.qi.db.Submitter_role;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class SubmitterRole
{

    public SubmitterRole(Submitter submitter)
    {
        if(submitter != null)
            currentSubmitter = submitter;
        else
            currentSubmitter = new Submitter();
        currentRoles = new ArrayList();
    }

    public SubmitterRole()
    {
        currentSubmitter = new Submitter();
        currentRoles = new ArrayList();
    }

    public static ArrayList getAllSubmitters(Long long1)
        throws Exception
    {
        Object obj = null;
        ArrayList arraylist = new ArrayList();
        if(long1 != null)
        {
            String s = (new StringBuilder()).append("ROLE_ID = ").append(long1.toString()).toString();
            Submitter_role submitter_role = new Submitter_role();
            try
            {
                Vector vector = submitter_role.retrieveAllWhere(s);
                for(int i = 0; i < vector.size(); i++)
                {
                    Submitter_role submitter_role1 = (Submitter_role)vector.elementAt(i);
                    arraylist.add(submitter_role1.getSubmitterkey());
                }

            }
            catch(SQLException sqlexception)
            {
                System.out.println("SQL Error in Retrieve all method for getAllSubmitters()");
                throw new Exception(sqlexception.getMessage());
            }
        }
        return arraylist;
    }

    public ArrayList getRoles()
        throws Exception
    {
        Object obj = null;
        if(currentSubmitter != null)
        {
            Long long1 = currentSubmitter.getSubmitterkey();
            if(long1 != null && long1.longValue() > 0L)
            {
                String s = (new StringBuilder()).append("SUBMITTERKEY = ").append(long1.toString()).toString();
                Submitter_role submitter_role = new Submitter_role();
                try
                {
                    Vector vector = submitter_role.retrieveAllWhere(s);
                    for(int i = 0; i < vector.size(); i++)
                    {
                        Submitter_role submitter_role1 = (Submitter_role)vector.elementAt(i);
                        currentRoles.add(submitter_role1.getRole_id());
                    }

                }
                catch(SQLException sqlexception)
                {
                    System.out.println("SQL Error in Retrieve all method for getRoles()");
                    throw new Exception(sqlexception.getMessage());
                }
            }
        }
        if(currentRoles.size() <= 0)
            throw new Exception("No roles defined for this user!!");
        else
            return currentRoles;
    }

    public boolean isInRole(Long long1)
    {
        return long1 != null && currentRoles.contains(long1);
    }

    public boolean isSuperUser()
    {
        return currentRoles.contains(QIConstants.SUPERROLE);
    }

    public boolean isDemoUser()
    {
        return currentRoles.contains(QIConstants.DEMOROLE);
    }

    public Submitter getCurrentSubmitter()
    {
        return currentSubmitter;
    }

    public void setCurrentSubmitter(Submitter submitter)
    {
        currentSubmitter = submitter;
    }

    public ArrayList getCurrentRoles()
    {
        return currentRoles;
    }

    public void setCurrentRoles(ArrayList arraylist)
    {
        currentRoles = arraylist;
    }

    private Submitter currentSubmitter;
    private ArrayList currentRoles;
}
