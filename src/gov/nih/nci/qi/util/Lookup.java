// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import gov.nih.nci.qi.SubmitterRole;
import gov.nih.nci.qi.constants.QIConstants;
import gov.nih.nci.qi.db.*;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.Hashtable;
import java.util.Vector;

public class Lookup
{

    public static synchronized Lookup getInstance()
    {
        if(onlyInstance == null)
            onlyInstance = new Lookup();
        return onlyInstance;
    }

    public static void clearAll()
    {
        members.clear();
    }

    private Lookup()
    {
        loadInstitutions();
        loadSampleInstitutions();
        loadCoreTypes();
        loadTimePoints();
        loadDNAQualities();
        loadRNAQualities();
        loadDnaInstruments();
        loadRnaProcessInstruments();
        loadRnaInstruments();
        loadPcrRatings();
        loadH_EReviews();
        loadUsabilities();
        loadTouchPreps();
        loadUnits();
        loadFrozen_H_E();
        loadFrozen_Touch_Prep();
        loadDNALabs();
        loadRNALabs();
        loadProteinLabs();
        loadAttempts();
        loadIntensities();
        loadCGHQualities();
    }

    private void loadCGHQualities()
    {
        try
        {
            Qi_cgh_quality qi_cgh_quality = new Qi_cgh_quality();
            cgh_qualities = qi_cgh_quality.retrieveAllWhere(" CGH_QUALITY_ID is not null ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadCGHQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadIntensities()
    {
        try
        {
            Qi_dna_labeling_intensity qi_dna_labeling_intensity = new Qi_dna_labeling_intensity();
            intensities = qi_dna_labeling_intensity.retrieveAllWhere(" INTENSITY_ID is not null ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadIntensities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadAttempts()
    {
        try
        {
            Qi_attempt qi_attempt = new Qi_attempt();
            attempts = qi_attempt.retrieveAllWhere(" ATTEMPT_ID is not null ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadAttempts() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadDNALabs()
    {
        try
        {
            Laboratory laboratory = new Laboratory();
            dna_labs = laboratory.retrieveAllWhere(" LABORATORY_ID =2 or LABORATORY_ID =3 ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNALabs() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadRNALabs()
    {
        try
        {
            Laboratory laboratory = new Laboratory();
            rna_labs = laboratory.retrieveAllWhere(" LABORATORY_ID =4 ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadRNALabs() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadProteinLabs()
    {
        try
        {
            Laboratory laboratory = new Laboratory();
            protein_labs = laboratory.retrieveAllWhere(" LABORATORY_ID =5 ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadProteinLabs() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadInstitutions()
    {
        try
        {
            Qi_institution qi_institution = new Qi_institution();
            institutions = qi_institution.retrieveAllWhere(" INSTITUTE_NAME is not null");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadInstitutions() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadSampleInstitutions()
    {
        try
        {
            Qi_institution qi_institution = new Qi_institution();
            sample_institutions = qi_institution.retrieveAllWhere(" INSTITUTION_ID is not null order by INSTITUTION_ID");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadInstitutions() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadCoreTypes()
    {
        try
        {
            Core_type core_type = new Core_type();
            coreTypes = core_type.retrieveAllWhere(" CORE_TYPE_DESC is not null");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadCoreTypes() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadTimePoints()
    {
        try
        {
            Qi_timepoint qi_timepoint = new Qi_timepoint();
            timePoints = qi_timepoint.retrieveAll();
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadTimePoints() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadDNAQualities()
    {
        try
        {
            Qi_quality qi_quality = new Qi_quality();
            dna_qualities = qi_quality.retrieveAllWhere(" QUALITY_CATEGORY = 'DNA'");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNAQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadRNAQualities()
    {
        try
        {
            Qi_quality qi_quality = new Qi_quality();
            rna_qualities = qi_quality.retrieveAllWhere(" QUALITY_CATEGORY = 'RNA'");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNAQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadDnaInstruments()
    {
        try
        {
            Qi_instrument qi_instrument = new Qi_instrument();
            dnaInstruments = qi_instrument.retrieveAllWhere(" INSTRUMENT_ID in(1,3,4) ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNAQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadRnaProcessInstruments()
    {
        try
        {
            Qi_instrument qi_instrument = new Qi_instrument();
            rnaProcessInstruments = qi_instrument.retrieveAllWhere(" INSTRUMENT_ID =1 ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNAQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadRnaInstruments()
    {
        try
        {
            Qi_instrument qi_instrument = new Qi_instrument();
            rnaInstruments = qi_instrument.retrieveAllWhere(" INSTRUMENT_ID =2 ");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadDNAQualities() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadPcrRatings()
    {
        try
        {
            Pcr_rating pcr_rating = new Pcr_rating();
            pcrRatings = pcr_rating.retrieveAllWhere(" PCR_RATING_DESC is not null  order by PCR_RATING");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadPcrRatings() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadH_EReviews()
    {
        try
        {
            Qi_h_e_review qi_h_e_review = new Qi_h_e_review();
            h_EReviews = qi_h_e_review.retrieveAllWhere(" H_E_REVIEW_DESC is not null  order by H_E_REVIEW");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadH_EReviews() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadFrozen_H_E()
    {
        try
        {
            Qi_frozen_h_e qi_frozen_h_e = new Qi_frozen_h_e();
            frozen_H_Es = qi_frozen_h_e.retrieveAllWhere(" H_E_REVIEW_DESC is not null  order by H_E_REVIEW");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadFrozen_H_E() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadFrozen_Touch_Prep()
    {
        try
        {
            Qi_frozen_touchprep qi_frozen_touchprep = new Qi_frozen_touchprep();
            frozen_Touch_Preps = qi_frozen_touchprep.retrieveAllWhere(" TOUCH_PREP_DESC is not null  order by TOUCH_PREP");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadFrozen_Touch_Prep() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadUsabilities()
    {
        try
        {
            Qi_usability qi_usability = new Qi_usability();
            usabilities = qi_usability.retrieveAllWhere(" USABILITY_DESC is not null  order by USABILITY");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadUsabilities method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadTouchPreps()
    {
        try
        {
            Qi_touchprep_recvd qi_touchprep_recvd = new Qi_touchprep_recvd();
            touchPreps = qi_touchprep_recvd.retrieveAllWhere(" TOUCHPREP_RECVD_DESC is not null  order by TOUCHPREP_RECVD");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadTouchPreps method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    private void loadUnits()
    {
        try
        {
            Qi_unit qi_unit = new Qi_unit();
            units = qi_unit.retrieveAllWhere(" UNIT_ID is not null order by UNIT_ID");
        }
        catch(SQLException sqlexception)
        {
            System.err.println("SQL Error in loadUnits() method of Lookup class");
            System.err.println(sqlexception);
        }
    }

    public static String getInstitutions(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < institutions.size(); j++)
        {
            Qi_institution qi_institution = (Qi_institution)institutions.elementAt(j);
            if(long1 != null && long1.equals(qi_institution.getInstitution_id()))
            {
                if(qi_institution.getInstitute_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\" selected >").append(qi_institution.getInstitute_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\" selected >").append(qi_institution.getInstitute_name()).append("\n").toString());
                continue;
            }
            if(qi_institution.getInstitute_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getSampleInstitutions(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < sample_institutions.size(); j++)
        {
            Qi_institution qi_institution = (Qi_institution)sample_institutions.elementAt(j);
            if(long1 != null && long1.equals(qi_institution.getInstitution_id()))
            {
                if(qi_institution.getInstitute_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\" selected >").append(qi_institution.getInstitute_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\" selected >").append(qi_institution.getInstitute_name()).append("\n").toString());
                continue;
            }
            if(qi_institution.getInstitute_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getCoreTypes(String s, int i, String s1, SubmitterRole submitterrole)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" onChange=\"check_coreType()\">").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < coreTypes.size(); j++)
        {
            Core_type core_type = (Core_type)coreTypes.elementAt(j);
            if(s1 != null && s1.equals(core_type.getCore_type()))
            {
                if(core_type.getCore_type_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(core_type.getCore_type()).append("\" selected >").append(core_type.getCore_type_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(core_type.getCore_type()).append("\" selected >").append(core_type.getCore_type_desc()).append("\n").toString());
                continue;
            }
            if(submitterrole != null && submitterrole.isInRole(QIConstants.FROZENSAMPLEROLE) && core_type.getCore_type().toUpperCase().startsWith("P") || submitterrole != null && submitterrole.isInRole(QIConstants.PARAFFINSAMPLEROLE) && core_type.getCore_type().toUpperCase().startsWith("F"))
                continue;
            if(core_type.getCore_type_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(core_type.getCore_type()).append("\">").append(core_type.getCore_type_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(core_type.getCore_type()).append("\">").append(core_type.getCore_type_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getTimePoints(String s, int i, String s1, SubmitterRole submitterrole)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < timePoints.size(); j++)
        {
            Qi_timepoint qi_timepoint = (Qi_timepoint)timePoints.elementAt(j);
            if(s1 != null && s1.equals(qi_timepoint.getTimepoint_name()))
            {
                if(qi_timepoint.getTimepoint_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\" selected >").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\" selected >").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
                continue;
            }
            if(submitterrole.isInRole(QIConstants.FROZENSAMPLEROLE) && qi_timepoint.getTimepoint_name().toUpperCase().startsWith("P") || submitterrole.isInRole(QIConstants.PARAFFINSAMPLEROLE) && qi_timepoint.getTimepoint_name().toUpperCase().startsWith("F"))
                continue;
            if(qi_timepoint.getTimepoint_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\">").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\">").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getMultiSelectInstitute(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" MULTIPLE >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"All\" Selected>All \n");
        for(int i = 0; i < sample_institutions.size(); i++)
        {
            Qi_institution qi_institution = (Qi_institution)sample_institutions.elementAt(i);
            if(qi_institution.getInstitute_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_institution.getInstitution_id()).append("\">").append(qi_institution.getInstitute_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getMultiSelectTimePoints(String s)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" MULTIPLE >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"All\" Selected>All \n");
        for(int i = 0; i < timePoints.size(); i++)
        {
            Qi_timepoint qi_timepoint = (Qi_timepoint)timePoints.elementAt(i);
            if(qi_timepoint.getTimepoint_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\">").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_timepoint.getTimepoint_name()).append("\">").append(qi_timepoint.getTimepoint_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getDNAQuality(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        System.out.println("--- I am in the getDNAQuality() method---");
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < dna_qualities.size(); j++)
        {
            Qi_quality qi_quality = (Qi_quality)dna_qualities.elementAt(j);
            System.out.println((new StringBuilder()).append("\tselectedKey is :").append(s1).toString());
            if(s1 != null && s1.equals(qi_quality.getQuality_name()))
            {
                if(qi_quality.getQuality_desc() != null)
                {
                    System.out.println(" I am in the if methosd");
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\" selected >").append(qi_quality.getQuality_desc()).append("\n").toString());
                } else
                {
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\" selected >").append(qi_quality.getQuality_desc()).append("\n").toString());
                }
                continue;
            }
            if(qi_quality.getQuality_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\">").append(qi_quality.getQuality_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\">").append(qi_quality.getQuality_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getDNAInstrument(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < dnaInstruments.size(); j++)
        {
            Qi_instrument qi_instrument = (Qi_instrument)dnaInstruments.elementAt(j);
            if(long1 != null && long1.equals(qi_instrument.getInstrument_id()))
            {
                if(qi_instrument.getInstrument_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                continue;
            }
            if(qi_instrument.getInstrument_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getRNAProcessInstrument(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < rnaProcessInstruments.size(); j++)
        {
            Qi_instrument qi_instrument = (Qi_instrument)rnaProcessInstruments.elementAt(j);
            if(long1 != null && long1.equals(qi_instrument.getInstrument_id()))
            {
                if(qi_instrument.getInstrument_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                continue;
            }
            if(qi_instrument.getInstrument_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getRNAInstrument(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < rnaInstruments.size(); j++)
        {
            Qi_instrument qi_instrument = (Qi_instrument)rnaInstruments.elementAt(j);
            if(long1 != null && long1.equals(qi_instrument.getInstrument_id()))
            {
                if(qi_instrument.getInstrument_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\" selected >").append(qi_instrument.getInstrument_name()).append("\n").toString());
                continue;
            }
            if(qi_instrument.getInstrument_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_instrument.getInstrument_id()).append("\">").append(qi_instrument.getInstrument_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getRNAQuality(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < rna_qualities.size(); j++)
        {
            Qi_quality qi_quality = (Qi_quality)rna_qualities.elementAt(j);
            if(s1 != null && s1.equals(qi_quality.getQuality_name()))
            {
                if(qi_quality.getQuality_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\" selected >").append(qi_quality.getQuality_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\" selected >").append(qi_quality.getQuality_desc()).append("\n").toString());
                continue;
            }
            if(qi_quality.getQuality_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\">").append(qi_quality.getQuality_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_quality.getQuality_name()).append("\">").append(qi_quality.getQuality_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getPCRRating(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < pcrRatings.size(); j++)
        {
            Pcr_rating pcr_rating = (Pcr_rating)pcrRatings.elementAt(j);
            if(s1 != null && s1.equals(pcr_rating.getPcr_rating()))
            {
                if(pcr_rating.getPcr_rating_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(pcr_rating.getPcr_rating()).append("\" selected >").append(pcr_rating.getPcr_rating_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(pcr_rating.getPcr_rating()).append("\" selected >").append(pcr_rating.getPcr_rating_desc()).append("\n").toString());
                continue;
            }
            if(pcr_rating.getPcr_rating_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(pcr_rating.getPcr_rating()).append("\">").append(pcr_rating.getPcr_rating_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(pcr_rating.getPcr_rating()).append("\">").append(pcr_rating.getPcr_rating_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getAttempts(String s, int i, Long along[])
    {
        StringBuffer stringbuffer = new StringBuffer();
        Qi_attempt qi_attempt = new Qi_attempt();
        Object obj = null;
        StringBuffer stringbuffer1 = new StringBuffer();
        System.out.println((new StringBuilder()).append("selectedKeys:").append(along).toString());
        if(along != null && along.length > 0)
        {
            if(along.length <= 4)
            {
                for(int j = 0; j < along.length; j++)
                {
                    Long long1 = along[j];
                    stringbuffer1.append((new StringBuilder()).append(" ATTEMPT_ID != ").append(long1).append(" AND ").toString());
                }

                stringbuffer1.append(" ATTEMPT_ID !=5");
            }
            attempts.clear();
            try
            {
                attempts = qi_attempt.retrieveAllWhere(stringbuffer1.toString());
                if(attempts.size() == 0)
                    attempts = qi_attempt.retrieveAllWhere(" ATTEMPT_ID =5");
            }
            catch(Exception exception) { }
        }
        if(along != null && along.length == 0 || along == null)
            try
            {
                attempts.clear();
                attempts = qi_attempt.retrieveAllWhere(" ATTEMPT_ID !=5");
            }
            catch(Exception exception1) { }
        System.out.println((new StringBuilder()).append("where = :").append(stringbuffer1.toString()).append("size:").append(attempts.size()).toString());
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int k = 0; k < attempts.size(); k++)
        {
            Qi_attempt qi_attempt1 = (Qi_attempt)attempts.elementAt(k);
            if(qi_attempt1.getAttempt_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_attempt1.getAttempt_desc()).append("\">").append(qi_attempt1.getAttempt_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_attempt1.getAttempt_desc()).append("\">").append(qi_attempt1.getAttempt_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getIntensities(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < intensities.size(); j++)
        {
            Qi_dna_labeling_intensity qi_dna_labeling_intensity = (Qi_dna_labeling_intensity)intensities.elementAt(j);
            if(long1 != null && long1.equals(qi_dna_labeling_intensity.getIntensity_id()))
            {
                if(qi_dna_labeling_intensity.getIntensity_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\" selected >").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\" selected >").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\n").toString());
                continue;
            }
            if(qi_dna_labeling_intensity.getIntensity_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\">").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\">").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getIntensities(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < intensities.size(); j++)
        {
            Qi_dna_labeling_intensity qi_dna_labeling_intensity = (Qi_dna_labeling_intensity)intensities.elementAt(j);
            if(qi_dna_labeling_intensity.getIntensity_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\">").append(qi_dna_labeling_intensity.getIntensity_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getCGHQualities(String s, int i, Long long1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < cgh_qualities.size(); j++)
        {
            Qi_cgh_quality qi_cgh_quality = (Qi_cgh_quality)cgh_qualities.elementAt(j);
            if(long1 != null && long1.equals(qi_cgh_quality.getCgh_quality_id()))
            {
                if(qi_cgh_quality.getCgh_quality_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_cgh_quality.getCgh_quality_desc()).append("\" selected >").append(qi_cgh_quality.getCgh_quality_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_cgh_quality.getCgh_quality_desc()).append("\" selected >").append(qi_cgh_quality.getCgh_quality_desc()).append("\n").toString());
                continue;
            }
            if(qi_cgh_quality.getCgh_quality_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_cgh_quality.getCgh_quality_desc()).append("\">").append(qi_cgh_quality.getCgh_quality_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_cgh_quality.getCgh_quality_desc()).append("\">").append(qi_cgh_quality.getCgh_quality_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getCGHQualities(String s, int i)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < cgh_qualities.size(); j++)
        {
            Qi_cgh_quality qi_cgh_quality = (Qi_cgh_quality)cgh_qualities.elementAt(j);
            if(qi_cgh_quality.getCgh_quality_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_cgh_quality.getCgh_quality_desc()).append("\">").append(qi_cgh_quality.getCgh_quality_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getH_EReviews(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < h_EReviews.size(); j++)
        {
            Qi_h_e_review qi_h_e_review = (Qi_h_e_review)h_EReviews.elementAt(j);
            if(s1 != null && s1.equals(qi_h_e_review.getH_e_review()))
            {
                if(qi_h_e_review.getH_e_review_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_h_e_review.getH_e_review()).append("\" selected >").append(qi_h_e_review.getH_e_review_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_h_e_review.getH_e_review()).append("\" selected >").append(qi_h_e_review.getH_e_review_desc()).append("\n").toString());
                continue;
            }
            if(qi_h_e_review.getH_e_review_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_h_e_review.getH_e_review()).append("\">").append(qi_h_e_review.getH_e_review_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_h_e_review.getH_e_review()).append("\">").append(qi_h_e_review.getH_e_review_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getFrozen_H_E(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < frozen_H_Es.size(); j++)
        {
            Qi_frozen_h_e qi_frozen_h_e = (Qi_frozen_h_e)frozen_H_Es.elementAt(j);
            if(s1 != null && s1.equals(qi_frozen_h_e.getH_e_review()))
            {
                if(qi_frozen_h_e.getH_e_review_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_h_e.getH_e_review()).append("\" selected >").append(qi_frozen_h_e.getH_e_review()).append(" (").append(qi_frozen_h_e.getH_e_review_desc()).append(")").append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_h_e.getH_e_review()).append("\" selected >").append(qi_frozen_h_e.getH_e_review()).append(" (").append(qi_frozen_h_e.getH_e_review_desc()).append(")").append("\n").toString());
                continue;
            }
            if(qi_frozen_h_e.getH_e_review_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_h_e.getH_e_review()).append("\">").append(qi_frozen_h_e.getH_e_review()).append(" (").append(qi_frozen_h_e.getH_e_review_desc()).append(")").append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_h_e.getH_e_review()).append("\">").append(qi_frozen_h_e.getH_e_review()).append(" (").append(qi_frozen_h_e.getH_e_review_desc()).append(")").append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getUsabilities(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < usabilities.size(); j++)
        {
            Qi_usability qi_usability = (Qi_usability)usabilities.elementAt(j);
            if(s1 != null && s1.equals(qi_usability.getUsability()))
            {
                if(qi_usability.getUsability_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_usability.getUsability()).append("\" selected >").append(qi_usability.getUsability_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_usability.getUsability()).append("\" selected >").append(qi_usability.getUsability_desc()).append("\n").toString());
                continue;
            }
            if(qi_usability.getUsability_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_usability.getUsability()).append("\">").append(qi_usability.getUsability_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_usability.getUsability()).append("\">").append(qi_usability.getUsability_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getTouchPreps(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < touchPreps.size(); j++)
        {
            Qi_touchprep_recvd qi_touchprep_recvd = (Qi_touchprep_recvd)touchPreps.elementAt(j);
            if(s1 != null && s1.equals(qi_touchprep_recvd.getTouchprep_recvd()))
            {
                if(qi_touchprep_recvd.getTouchprep_recvd_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_touchprep_recvd.getTouchprep_recvd()).append("\" selected >").append(qi_touchprep_recvd.getTouchprep_recvd_desc()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_touchprep_recvd.getTouchprep_recvd()).append("\" selected >").append(qi_touchprep_recvd.getTouchprep_recvd_desc()).append("\n").toString());
                continue;
            }
            if(qi_touchprep_recvd.getTouchprep_recvd_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_touchprep_recvd.getTouchprep_recvd()).append("\">").append(qi_touchprep_recvd.getTouchprep_recvd_desc()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_touchprep_recvd.getTouchprep_recvd()).append("\">").append(qi_touchprep_recvd.getTouchprep_recvd_desc()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getFrozen_TouchPreps(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < frozen_Touch_Preps.size(); j++)
        {
            Qi_frozen_touchprep qi_frozen_touchprep = (Qi_frozen_touchprep)frozen_Touch_Preps.elementAt(j);
            if(s1 != null && s1.equals(qi_frozen_touchprep.getTouch_prep()))
            {
                if(qi_frozen_touchprep.getTouch_prep_desc() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_touchprep.getTouch_prep()).append("\" selected >").append(qi_frozen_touchprep.getTouch_prep()).append(" (").append(qi_frozen_touchprep.getTouch_prep_desc()).append(")").append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_touchprep.getTouch_prep()).append("\" selected >").append(qi_frozen_touchprep.getTouch_prep()).append(" (").append(qi_frozen_touchprep.getTouch_prep_desc()).append(")").append("\n").toString());
                continue;
            }
            if(qi_frozen_touchprep.getTouch_prep_desc() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_touchprep.getTouch_prep()).append("\">").append(qi_frozen_touchprep.getTouch_prep()).append(" (").append(qi_frozen_touchprep.getTouch_prep_desc()).append(")").append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_frozen_touchprep.getTouch_prep()).append("\">").append(qi_frozen_touchprep.getTouch_prep()).append(" (").append(qi_frozen_touchprep.getTouch_prep_desc()).append(")").append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getUnits(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < units.size(); j++)
        {
            Qi_unit qi_unit = (Qi_unit)units.elementAt(j);
            if(s1 != null && s1.equals(qi_unit.getUnit_name()))
            {
                if(qi_unit.getUnit_name() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_unit.getUnit_name()).append("\" selected >").append(qi_unit.getUnit_name()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_unit.getUnit_name()).append("\" selected >").append(qi_unit.getUnit_name()).append("\n").toString());
                continue;
            }
            if(qi_unit.getUnit_name() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_unit.getUnit_name()).append("\">").append(qi_unit.getUnit_name()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(qi_unit.getUnit_name()).append("\">").append(qi_unit.getUnit_name()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getDNALabs(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < dna_labs.size(); j++)
        {
            Laboratory laboratory = (Laboratory)dna_labs.elementAt(j);
            if(s1 != null)
                s1 = s1.substring(s1.indexOf("-") + 1);
            if(s1 != null && s1.equals(laboratory.getLaboratory_id().toString()))
            {
                if(laboratory.getLaboratory() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                continue;
            }
            if(laboratory.getLaboratory() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getRNALabs(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < rna_labs.size(); j++)
        {
            Laboratory laboratory = (Laboratory)rna_labs.elementAt(j);
            if(s1 != null)
                s1 = s1.substring(s1.indexOf("-") + 1);
            if(s1 != null && s1.equals(laboratory.getLaboratory_id().toString()))
            {
                if(laboratory.getLaboratory() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                continue;
            }
            if(laboratory.getLaboratory() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    public static String getProteinLabs(String s, int i, String s1)
    {
        StringBuffer stringbuffer = new StringBuffer();
        stringbuffer.append((new StringBuilder()).append("<SELECT name=\"").append(s).append("\" size=\"").append(i).append("\" >").append("\n").toString());
        stringbuffer.append("<OPTION value=\"\">\n");
        for(int j = 0; j < protein_labs.size(); j++)
        {
            Laboratory laboratory = (Laboratory)protein_labs.elementAt(j);
            if(s1 != null)
                s1 = s1.substring(s1.indexOf("-") + 1);
            if(s1 != null && s1.equals(laboratory.getLaboratory_id().toString()))
            {
                if(laboratory.getLaboratory() != null)
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                else
                    stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\" selected >").append(laboratory.getLaboratory()).append("\n").toString());
                continue;
            }
            if(laboratory.getLaboratory() != null)
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
            else
                stringbuffer.append((new StringBuilder()).append("<OPTION value=\"").append(laboratory.getLaboratory_id()).append("\">").append(laboratory.getLaboratory()).append("\n").toString());
        }

        stringbuffer.append("</SELECT>\n");
        return stringbuffer.toString();
    }

    private static Vector institutions = new Vector();
    private static Vector sample_institutions = new Vector();
    private static Vector coreTypes = new Vector();
    private static Vector timePoints = new Vector();
    private static Vector dna_qualities = new Vector();
    private static Vector rna_qualities = new Vector();
    private static Vector dnaInstruments = new Vector();
    private static Vector rnaProcessInstruments = new Vector();
    private static Vector rnaInstruments = new Vector();
    private static Vector pcrRatings = new Vector();
    private static Vector h_EReviews = new Vector();
    private static Vector usabilities = new Vector();
    private static Vector touchPreps = new Vector();
    private static Vector units = new Vector();
    private static Vector frozen_H_Es = new Vector();
    private static Vector frozen_Touch_Preps = new Vector();
    private static Vector dna_labs = new Vector();
    private static Vector rna_labs = new Vector();
    private static Vector protein_labs = new Vector();
    private static Vector attempts = new Vector();
    private static Vector intensities = new Vector();
    private static Vector cgh_qualities = new Vector();
    private static Vector v = new Vector();
    private static Hashtable members = new Hashtable();
    private static Lookup onlyInstance = null;

}
