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

import gov.nih.nci.qi.db.DatabaseAccess;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Hashtable;

public class KeyRetriever
{

    private static Hashtable tableSequences;

    static 
    {
        tableSequences = new Hashtable();
        tableSequences.put("SUBMITTER", "SUBMITTERUID");
        tableSequences.put("QI_SAMPLE", "SAMPLE_ID");
        tableSequences.put("QI_DNA", "DNA_ID");
        tableSequences.put("QI_RNA", "RNA_ID");
        tableSequences.put("RNA_QUALITY_IMAGE", "RNA_QUALITY_IMAGE_ID");
        tableSequences.put("QI_PROTEIN", "PROTEIN_ID");
        tableSequences.put("QI_DNA_LABELING", "LABELING_ID");
        tableSequences.put("QI_CGH_HYBRIDIZATION", "HYBRIDIZATION_ID");
    }
    /**
     * Retrieves the next key value in an automatic numeric sequence for a
     * given table. These keys are provided in the database via a separate
     * sequence table. This call increments the key value, guaranteeing that
     * the returned value is unique (avoiding database race conditions).
     *
     * @param	tableName	name of the database table (case-insensitive)
     * @return	a Long containing the new key value, or null if the given table has no sequence
     */
    public Long getNextKey (String tableName)
    {
      // To do this:
      // 1. Open a new connection with NO autocommit.
      // 2. UPDATE the key value (by incrementing it) in the sequence table.
      // 3. SELECT the key value from the table.
      // 4. Commit the connection and close.
      // 5. Return the key!
      // This sequence of actions should implicitly lock the table row in step 2
      // and unlock it in 4, thereby guaranteeing uniqueness.

      tableName = tableName.toUpperCase();
      String keyName = (String) tableSequences.get (tableName);
  	System.out.println("keyName:"+keyName);
      if (keyName == null)
        {
  	System.err.println ("No sequence is in place for table " +
  			       tableName);
  	return null;
        }

      Long keyValue = null;
      DatabaseAccess access = new DatabaseAccess();
      try {
        // 1. Open a connection with NO autocommit.
        access.connect();
        access._con.setAutoCommit (false);

        // 2. UPDATE the key value (by incrementing it) in the sequence table.
        String updateQuery =
  	"UPDATE TABLE_KEY " +
  	"SET KEY_VALUE = KEY_VALUE + 1 " +
  	"WHERE KEY_NAME = '" + keyName + "'";
  	//System.out.println("updateQuery:"+updateQuery);
        access.doUpdateQuery (updateQuery);

        // 3. SELECT the key value from the table.
        String selectQuery =
  	"SELECT KEY_VALUE FROM TABLE_KEY " +
  	"WHERE KEY_NAME = '" + keyName + "'";
  	//System.out.println("selectQuery:"+selectQuery);
        ResultSet rset = access.executeQuery (selectQuery);
        if (rset.next())
  	{
  	  keyValue = new Long (rset.getLong ("KEY_VALUE"));
  	 // System.out.println("keyValue:"+keyValue);
  	}
        else
  	{
  	  System.err.println ("Couldn't locate sequence " + keyName +
  				 " in sequence table");
  	  keyValue = null;
  	}

        // 4. Commit the connection and close.
        access._con.commit();
      } catch (Exception exc) {
        System.err.println (exc.getMessage());
      } finally {
        try {access.disconnect();} catch (Exception exc) {}
      }

      return keyValue;
    }

}
