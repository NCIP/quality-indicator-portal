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

import gov.nih.nci.qi.SampleEx;
import gov.nih.nci.qi.db.*;
import java.io.PrintStream;
import java.util.Hashtable;
import java.util.Vector;

public class SummaryReport
{

    public int getSampleSize()
    {
        return samples.size();
    }

    private void getInstitutionPatientNum(String s)
    {
        try
        {
            s = (new StringBuilder()).append(" ").append(s).toString();
            allPt = sampleSearch.count((new StringBuilder()).append("PATIENT_ACCRUAL IS NOT NULL").append(s).toString());
            System.out.println((new StringBuilder()).append("allPt=").append(allPt).toString());
            ucsfPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            uncPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            upennPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            ucPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            uabPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            mskccPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            gwPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            uwPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            utPt = sampleSearch.count((new StringBuilder()).append("SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            ptNumArray = (new String[] {
                (new StringBuilder()).append(allPt).append("(Total Number Of Patients)").toString(), (new StringBuilder()).append(ucsfPt).append("(UCSF) ").toString(), (new StringBuilder()).append(uncPt).append("(UNC)").toString(), (new StringBuilder()).append(upennPt).append("(UPENN)").toString(), (new StringBuilder()).append(ucPt).append("(Univ. of Chicago)").toString(), (new StringBuilder()).append(uabPt).append("(UAB)").toString(), (new StringBuilder()).append(mskccPt).append("(MSKCC)").toString(), (new StringBuilder()).append(gwPt).append("(GTWN)").toString(), (new StringBuilder()).append(uwPt).append("(Univ. of Wash.)").toString(), (new StringBuilder()).append(utPt).append("(Univ. ofTexas.)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenTouchPrepInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_f = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Frozen' and FROZEN_TOUCH_PREP  is not null").append(s).toString());
            f_touch_prep = ((float)samples_f.size() / (float)frozenSamples.size()) * 100F;
            touchPrep_f_Array = (new String[] {
                (new Integer(samples_f.size())).toString(), (new Float(f_touch_prep)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_touch_prep)).toString().substring(0, (new Float(f_touch_prep)).toString().indexOf("."))).append("%").toString()
            });
            samples_f_pos_touch_prep = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND CORE_TYPE='Frozen'").append(s).toString());
            f_pos_touch_prep = ((float)samples_f_pos_touch_prep.size() / (float)samples_f.size()) * 100F;
            touchPrep_f_pos_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep.size())).toString(), (new Float(f_pos_touch_prep)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_pos_touch_prep)).toString().substring(0, (new Float(f_pos_touch_prep)).toString().indexOf("."))).append("%").toString()
            });
            samples_f_pos_neg_touch_prep = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND CORE_TYPE='Frozen'").append(s).toString());
            f_pos_neg_touch_prep = ((float)samples_f_pos_neg_touch_prep.size() / (float)samples_f.size()) * 100F;
            touchPrep_f_pos_neg_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep.size())).toString(), (new Float(f_pos_neg_touch_prep)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_pos_neg_touch_prep)).toString().substring(0, (new Float(f_pos_neg_touch_prep)).toString().indexOf("."))).append("%").toString()
            });
            samples_f_neg_touch_prep = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND CORE_TYPE='Frozen'").append(s).toString());
            f_neg_touch_prep = ((float)samples_f_neg_touch_prep.size() / (float)samples_f.size()) * 100F;
            touchPrep_f_neg_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep.size())).toString(), (new Float(f_neg_touch_prep)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_neg_touch_prep)).toString().substring(0, (new Float(f_neg_touch_prep)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenInstitutionTouchPrepInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            Vector vector = new Vector();
            vector = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Frozen' and SAMPLE_GENERATING_INSTITUTE_ID IS NOT NULL").append(s).toString());
            samples_f_touch_prep_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Frozen' and FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_allInstitutes = ((float)samples_f_touch_prep_allInstitutes.size() / (float)vector.size()) * 100F;
            touch_prep_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_touch_prep_allInstitutes.size())).toString(), (new Float(touch_prep_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_allInstitutes)).toString().substring(0, (new Float(touch_prep_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_touch_prep_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =1 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_ucsf = ((float)samples_f_touch_prep_ucsf.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_ucsf_Array = (new String[] {
                (new Integer(samples_f_touch_prep_ucsf.size())).toString(), (new Float(touch_prep_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_ucsf)).toString().substring(0, (new Float(touch_prep_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_touch_prep_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =2 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_unc = ((float)samples_f_touch_prep_unc.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_unc_Array = (new String[] {
                (new Integer(samples_f_touch_prep_ucsf.size())).toString(), (new Float(touch_prep_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_unc)).toString().substring(0, (new Float(touch_prep_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_touch_prep_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =3 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_upenn = ((float)samples_f_touch_prep_upenn.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_upenn_Array = (new String[] {
                (new Integer(samples_f_touch_prep_upenn.size())).toString(), (new Float(touch_prep_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_upenn)).toString().substring(0, (new Float(touch_prep_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_touch_prep_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =4 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_uc = ((float)samples_f_touch_prep_uc.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_uc_Array = (new String[] {
                (new Integer(samples_f_touch_prep_uc.size())).toString(), (new Float(touch_prep_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_uc)).toString().substring(0, (new Float(touch_prep_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_touch_prep_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =5 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_uab = ((float)samples_f_touch_prep_uab.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_uab_Array = (new String[] {
                (new Integer(samples_f_touch_prep_uab.size())).toString(), (new Float(touch_prep_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_uab)).toString().substring(0, (new Float(touch_prep_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_touch_prep_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =6 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_mskcc = ((float)samples_f_touch_prep_mskcc.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_mskcc_Array = (new String[] {
                (new Integer(samples_f_touch_prep_mskcc.size())).toString(), (new Float(touch_prep_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_mskcc)).toString().substring(0, (new Float(touch_prep_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_touch_prep_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =7 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_gw = ((float)samples_f_touch_prep_gw.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_gw_Array = (new String[] {
                (new Integer(samples_f_touch_prep_gw.size())).toString(), (new Float(touch_prep_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_gw)).toString().substring(0, (new Float(touch_prep_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_touch_prep_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =8 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_uw = ((float)samples_f_touch_prep_uw.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_uw_Array = (new String[] {
                (new Integer(samples_f_touch_prep_uw.size())).toString(), (new Float(touch_prep_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_uw)).toString().substring(0, (new Float(touch_prep_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_touch_prep_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =9 and  FROZEN_TOUCH_PREP is not null").append(s).toString());
            touch_prep_ut = ((float)samples_f_touch_prep_ut.size() / (float)samples_f_touch_prep_allInstitutes.size()) * 100F;
            touch_prep_ut_Array = (new String[] {
                (new Integer(samples_f_touch_prep_ut.size())).toString(), (new Float(touch_prep_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_ut)).toString().substring(0, (new Float(touch_prep_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            Vector vector1 = new Vector();
            vector1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID IS NOT NULL AND FROZEN_TOUCH_PREP IS NOT NULL").append(s).toString());
            samples_f_pos_touch_prep_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+'").append(s).toString());
            pos_touch_prep_allInstitutes = ((float)samples_f_pos_touch_prep_allInstitutes.size() / (float)vector1.size()) * 100F;
            pos_touch_prep_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_allInstitutes.size())).toString(), (new Float(pos_touch_prep_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_allInstitutes)).toString().substring(0, (new Float(pos_touch_prep_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_pos_touch_prep_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            pos_touch_prep_ucsf = ((float)samples_f_pos_touch_prep_ucsf.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_ucsf_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_ucsf.size())).toString(), (new Float(pos_touch_prep_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_ucsf)).toString().substring(0, (new Float(pos_touch_prep_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_pos_touch_prep_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            pos_touch_prep_unc = ((float)samples_f_pos_touch_prep_unc.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_unc_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_unc.size())).toString(), (new Float(pos_touch_prep_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_unc)).toString().substring(0, (new Float(pos_touch_prep_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_pos_touch_prep_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            pos_touch_prep_upenn = ((float)samples_f_pos_touch_prep_upenn.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_upenn_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_upenn.size())).toString(), (new Float(pos_touch_prep_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_upenn)).toString().substring(0, (new Float(pos_touch_prep_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_pos_touch_prep_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            pos_touch_prep_uc = ((float)samples_f_pos_touch_prep_uc.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_uc_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_uc.size())).toString(), (new Float(pos_touch_prep_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_uc)).toString().substring(0, (new Float(pos_touch_prep_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_pos_touch_prep_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            pos_touch_prep_uab = ((float)samples_f_pos_touch_prep_uab.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_uab_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_uab.size())).toString(), (new Float(pos_touch_prep_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_uab)).toString().substring(0, (new Float(f_touch_prep)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_pos_touch_prep_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            pos_touch_prep_mskcc = ((float)samples_f_pos_touch_prep_mskcc.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_mskcc_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_mskcc.size())).toString(), (new Float(pos_touch_prep_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_mskcc)).toString().substring(0, (new Float(pos_touch_prep_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_pos_touch_prep_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            pos_touch_prep_gw = ((float)samples_f_pos_touch_prep_gw.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_gw_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_gw.size())).toString(), (new Float(pos_touch_prep_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_gw)).toString().substring(0, (new Float(pos_touch_prep_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_pos_touch_prep_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            pos_touch_prep_uw = ((float)samples_f_pos_touch_prep_uw.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_uw_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_uw.size())).toString(), (new Float(pos_touch_prep_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_uw)).toString().substring(0, (new Float(pos_touch_prep_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_pos_touch_prep_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            pos_touch_prep_ut = ((float)samples_f_pos_touch_prep_ut.size() / (float)samples_f_pos_touch_prep_allInstitutes.size()) * 100F;
            pos_touch_prep_ut_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_ut.size())).toString(), (new Float(pos_touch_prep_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_ut)).toString().substring(0, (new Float(pos_touch_prep_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_f_pos_neg_touch_prep_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-'").append(s).toString());
            pos_neg_touch_prep_allInstitutes = ((float)samples_f_pos_neg_touch_prep_allInstitutes.size() / (float)vector1.size()) * 100F;
            pos_neg_touch_prep_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_allInstitutes.size())).toString(), (new Float(pos_neg_touch_prep_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_allInstitutes)).toString().substring(0, (new Float(pos_neg_touch_prep_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_pos_neg_touch_prep_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            pos_neg_touch_prep_ucsf = ((float)samples_f_pos_neg_touch_prep_ucsf.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_ucsf_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_allInstitutes.size())).toString(), (new Float(pos_neg_touch_prep_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_ucsf)).toString().substring(0, (new Float(pos_neg_touch_prep_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_pos_neg_touch_prep_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            pos_neg_touch_prep_unc = ((float)samples_f_pos_neg_touch_prep_unc.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_unc_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_unc.size())).toString(), (new Float(pos_neg_touch_prep_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_unc)).toString().substring(0, (new Float(pos_neg_touch_prep_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_pos_neg_touch_prep_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            pos_neg_touch_prep_upenn = ((float)samples_f_pos_neg_touch_prep_upenn.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_upenn_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_upenn.size())).toString(), (new Float(pos_neg_touch_prep_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_upenn)).toString().substring(0, (new Float(pos_neg_touch_prep_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_pos_neg_touch_prep_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            pos_neg_touch_prep_uc = ((float)samples_f_pos_neg_touch_prep_uc.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_uc_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_uc.size())).toString(), (new Float(pos_neg_touch_prep_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_uc)).toString().substring(0, (new Float(pos_neg_touch_prep_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_pos_neg_touch_prep_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            pos_neg_touch_prep_uab = ((float)samples_f_pos_neg_touch_prep_uab.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_uab_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_uab.size())).toString(), (new Float(pos_neg_touch_prep_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_uab)).toString().substring(0, (new Float(pos_neg_touch_prep_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_pos_neg_touch_prep_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            pos_neg_touch_prep_mskcc = ((float)samples_f_pos_neg_touch_prep_mskcc.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_mskcc_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_mskcc.size())).toString(), (new Float(pos_neg_touch_prep_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_mskcc)).toString().substring(0, (new Float(pos_neg_touch_prep_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_pos_neg_touch_prep_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            pos_neg_touch_prep_gw = ((float)samples_f_pos_neg_touch_prep_gw.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_gw_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_gw.size())).toString(), (new Float(pos_neg_touch_prep_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_gw)).toString().substring(0, (new Float(pos_neg_touch_prep_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_pos_neg_touch_prep_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            pos_neg_touch_prep_uw = ((float)samples_f_pos_neg_touch_prep_uw.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_uw_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_uw.size())).toString(), (new Float(pos_neg_touch_prep_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_uw)).toString().substring(0, (new Float(pos_neg_touch_prep_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_pos_neg_touch_prep_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            pos_neg_touch_prep_ut = ((float)samples_f_pos_neg_touch_prep_ut.size() / (float)samples_f_pos_neg_touch_prep_allInstitutes.size()) * 100F;
            pos_neg_touch_prep_ut_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_ut.size())).toString(), (new Float(pos_neg_touch_prep_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_ut)).toString().substring(0, (new Float(pos_neg_touch_prep_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_f_neg_touch_prep_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-'").append(s).toString());
            neg_touch_prep_allInstitutes = ((float)samples_f_neg_touch_prep_allInstitutes.size() / (float)vector1.size()) * 100F;
            neg_touch_prep_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_allInstitutes.size())).toString(), (new Float(neg_touch_prep_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_allInstitutes)).toString().substring(0, (new Float(neg_touch_prep_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_neg_touch_prep_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            neg_touch_prep_ucsf = ((float)samples_f_neg_touch_prep_ucsf.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_ucsf_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_ucsf.size())).toString(), (new Float(neg_touch_prep_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_ucsf)).toString().substring(0, (new Float(neg_touch_prep_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_neg_touch_prep_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            neg_touch_prep_unc = ((float)samples_f_neg_touch_prep_unc.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_unc_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_unc.size())).toString(), (new Float(neg_touch_prep_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_unc)).toString().substring(0, (new Float(neg_touch_prep_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_neg_touch_prep_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            neg_touch_prep_upenn = ((float)samples_f_neg_touch_prep_upenn.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_upenn_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_upenn.size())).toString(), (new Float(neg_touch_prep_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_upenn)).toString().substring(0, (new Float(neg_touch_prep_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_neg_touch_prep_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            neg_touch_prep_uc = ((float)samples_f_neg_touch_prep_uc.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_uc_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_uc.size())).toString(), (new Float(neg_touch_prep_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_uc)).toString().substring(0, (new Float(neg_touch_prep_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_neg_touch_prep_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            neg_touch_prep_uab = ((float)samples_f_neg_touch_prep_uab.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_uab_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_uab.size())).toString(), (new Float(neg_touch_prep_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_uab)).toString().substring(0, (new Float(neg_touch_prep_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_neg_touch_prep_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            neg_touch_prep_mskcc = ((float)samples_f_neg_touch_prep_mskcc.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_mskcc_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_mskcc.size())).toString(), (new Float(neg_touch_prep_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_mskcc)).toString().substring(0, (new Float(neg_touch_prep_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_neg_touch_prep_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            neg_touch_prep_gw = ((float)samples_f_neg_touch_prep_gw.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_gw_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_gw.size())).toString(), (new Float(neg_touch_prep_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_gw)).toString().substring(0, (new Float(neg_touch_prep_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_neg_touch_prep_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            neg_touch_prep_uw = ((float)samples_f_neg_touch_prep_uw.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_uw_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_uw.size())).toString(), (new Float(neg_touch_prep_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_uw)).toString().substring(0, (new Float(neg_touch_prep_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_neg_touch_prep_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            neg_touch_prep_ut = ((float)samples_f_neg_touch_prep_ut.size() / (float)samples_f_neg_touch_prep_allInstitutes.size()) * 100F;
            neg_touch_prep_ut_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_ut.size())).toString(), (new Float(neg_touch_prep_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_ut)).toString().substring(0, (new Float(neg_touch_prep_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenTimePtTouchPrepInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_f_touch_prep_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null AND TIMEPOINT_NAME IN('F1','F2','F3','F4','F5','F6','FS')").append(s).toString());
            touch_prep_alltps = ((float)samples_f_touch_prep_alltps.size() / (float)frozenSamples.size()) * 100F;
            touch_prep_alltps_Array = (new String[] {
                (new Integer(samples_f_touch_prep_alltps.size())).toString(), (new Float(touch_prep_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_alltps)).toString().substring(0, (new Float(touch_prep_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_f_touch_prep_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null AND TIMEPOINT_NAME ='F1'").append(s).toString());
            touch_prep_f1 = ((float)samples_f_touch_prep_f1.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f1_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f1.size())).toString(), (new Float(touch_prep_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f1)).toString().substring(0, (new Float(touch_prep_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_touch_prep_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null AND TIMEPOINT_NAME ='F2'").append(s).toString());
            touch_prep_f2 = ((float)samples_f_touch_prep_f2.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f2_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f2.size())).toString(), (new Float(touch_prep_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f2)).toString().substring(0, (new Float(touch_prep_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_touch_prep_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null AND TIMEPOINT_NAME ='F3'").append(s).toString());
            touch_prep_f3 = ((float)samples_f_touch_prep_f3.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f3_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f3.size())).toString(), (new Float(touch_prep_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f3)).toString().substring(0, (new Float(touch_prep_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_touch_prep_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null AND TIMEPOINT_NAME ='F4'").append(s).toString());
            touch_prep_f4 = ((float)samples_f_touch_prep_f4.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f4_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f4.size())).toString(), (new Float(touch_prep_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f4)).toString().substring(0, (new Float(touch_prep_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_touch_prep_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null  AND TIMEPOINT_NAME ='F5'").append(s).toString());
            touch_prep_f5 = ((float)samples_f_touch_prep_f5.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f5_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f5.size())).toString(), (new Float(touch_prep_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f5)).toString().substring(0, (new Float(touch_prep_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_touch_prep_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append("FROZEN_TOUCH_PREP is not null  AND  TIMEPOINT_NAME ='F6'").append(s).toString());
            touch_prep_f6 = ((float)samples_f_touch_prep_f6.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_f6_Array = (new String[] {
                (new Integer(samples_f_touch_prep_f6.size())).toString(), (new Float(touch_prep_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_f6)).toString().substring(0, (new Float(touch_prep_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_touch_prep_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP is not null  AND TIMEPOINT_NAME ='FS'").append(s).toString());
            touch_prep_fs = ((float)samples_f_touch_prep_fs.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            touch_prep_fs_Array = (new String[] {
                (new Integer(samples_f_touch_prep_fs.size())).toString(), (new Float(touch_prep_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(touch_prep_fs)).toString().substring(0, (new Float(touch_prep_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
            samples_f_pos_touch_prep_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+'").append(s).toString());
            pos_touch_prep_alltps = ((float)samples_f_pos_touch_prep_alltps.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_alltps_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_alltps.size())).toString(), (new Float(pos_touch_prep_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_alltps)).toString().substring(0, (new Float(pos_touch_prep_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_f_pos_touch_prep_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F1'").append(s).toString());
            pos_touch_prep_f1 = ((float)samples_f_pos_touch_prep_f1.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f1_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f1.size())).toString(), (new Float(pos_touch_prep_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f1)).toString().substring(0, (new Float(pos_touch_prep_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_pos_touch_prep_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F2'").append(s).toString());
            pos_touch_prep_f2 = ((float)samples_f_pos_touch_prep_f2.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f2_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f2.size())).toString(), (new Float(pos_touch_prep_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f2)).toString().substring(0, (new Float(pos_touch_prep_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_pos_touch_prep_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F3'").append(s).toString());
            pos_touch_prep_f3 = ((float)samples_f_pos_touch_prep_f3.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f3_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f3.size())).toString(), (new Float(pos_touch_prep_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f3)).toString().substring(0, (new Float(pos_touch_prep_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_pos_touch_prep_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F4'").append(s).toString());
            pos_touch_prep_f4 = ((float)samples_f_pos_touch_prep_f4.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f4_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f4.size())).toString(), (new Float(pos_touch_prep_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f4)).toString().substring(0, (new Float(pos_touch_prep_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_pos_touch_prep_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F5'").append(s).toString());
            pos_touch_prep_f5 = ((float)samples_f_pos_touch_prep_f5.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f5_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f5.size())).toString(), (new Float(pos_touch_prep_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f5)).toString().substring(0, (new Float(pos_touch_prep_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_pos_touch_prep_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='F6'").append(s).toString());
            pos_touch_prep_f6 = ((float)samples_f_pos_touch_prep_f6.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_f6_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_f6.size())).toString(), (new Float(pos_touch_prep_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_f6)).toString().substring(0, (new Float(pos_touch_prep_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_pos_touch_prep_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+' AND TIMEPOINT_NAME='FS'").append(s).toString());
            pos_touch_prep_fs = ((float)samples_f_pos_touch_prep_fs.size() / (float)samples_f_pos_touch_prep_alltps.size()) * 100F;
            pos_touch_prep_fs_Array = (new String[] {
                (new Integer(samples_f_pos_touch_prep_fs.size())).toString(), (new Float(pos_touch_prep_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_touch_prep_fs)).toString().substring(0, (new Float(pos_touch_prep_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
            samples_f_pos_neg_touch_prep_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-'").append(s).toString());
            pos_neg_touch_prep_alltps = ((float)samples_f_pos_neg_touch_prep_alltps.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_alltps_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_alltps.size())).toString(), (new Float(pos_neg_touch_prep_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_alltps)).toString().substring(0, (new Float(pos_neg_touch_prep_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_f_pos_neg_touch_prep_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F1'").append(s).toString());
            pos_neg_touch_prep_f1 = ((float)samples_f_pos_neg_touch_prep_f1.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f1_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f1.size())).toString(), (new Float(pos_neg_touch_prep_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f1)).toString().substring(0, (new Float(pos_neg_touch_prep_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_pos_neg_touch_prep_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F2'").append(s).toString());
            pos_neg_touch_prep_f2 = ((float)samples_f_pos_neg_touch_prep_f2.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f2_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f2.size())).toString(), (new Float(pos_neg_touch_prep_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f2)).toString().substring(0, (new Float(pos_neg_touch_prep_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_pos_neg_touch_prep_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F3'").append(s).toString());
            pos_neg_touch_prep_f3 = ((float)samples_f_pos_neg_touch_prep_f3.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f3_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f3.size())).toString(), (new Float(pos_neg_touch_prep_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f3)).toString().substring(0, (new Float(pos_neg_touch_prep_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_pos_neg_touch_prep_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F4'").append(s).toString());
            pos_neg_touch_prep_f4 = ((float)samples_f_pos_neg_touch_prep_f4.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f4_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f4.size())).toString(), (new Float(pos_neg_touch_prep_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f4)).toString().substring(0, (new Float(pos_neg_touch_prep_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_pos_neg_touch_prep_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F5'").append(s).toString());
            pos_neg_touch_prep_f5 = ((float)samples_f_pos_neg_touch_prep_f5.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f5_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f5.size())).toString(), (new Float(pos_neg_touch_prep_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f5)).toString().substring(0, (new Float(pos_neg_touch_prep_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_pos_neg_touch_prep_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='F6'").append(s).toString());
            pos_neg_touch_prep_f6 = ((float)samples_f_pos_neg_touch_prep_f6.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_f6_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_f6.size())).toString(), (new Float(pos_neg_touch_prep_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_f6)).toString().substring(0, (new Float(pos_neg_touch_prep_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_pos_neg_touch_prep_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='+/-' AND TIMEPOINT_NAME='FS'").append(s).toString());
            pos_neg_touch_prep_fs = ((float)samples_f_pos_neg_touch_prep_fs.size() / (float)samples_f_pos_neg_touch_prep_alltps.size()) * 100F;
            pos_neg_touch_prep_fs_Array = (new String[] {
                (new Integer(samples_f_pos_neg_touch_prep_fs.size())).toString(), (new Float(pos_neg_touch_prep_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_neg_touch_prep_fs)).toString().substring(0, (new Float(pos_neg_touch_prep_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
            samples_f_neg_touch_prep_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-'").append(s).toString());
            neg_touch_prep_alltps = ((float)samples_f_neg_touch_prep_alltps.size() / (float)samples_f_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_alltps_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_alltps.size())).toString(), (new Float(neg_touch_prep_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_alltps)).toString().substring(0, (new Float(neg_touch_prep_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_f_neg_touch_prep_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F1'").append(s).toString());
            neg_touch_prep_f1 = ((float)samples_f_neg_touch_prep_f1.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f1_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f1.size())).toString(), (new Float(neg_touch_prep_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f1)).toString().substring(0, (new Float(neg_touch_prep_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_neg_touch_prep_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F2'").append(s).toString());
            neg_touch_prep_f2 = ((float)samples_f_neg_touch_prep_f2.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f2_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f2.size())).toString(), (new Float(neg_touch_prep_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f2)).toString().substring(0, (new Float(neg_touch_prep_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_neg_touch_prep_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F3'").append(s).toString());
            neg_touch_prep_f3 = ((float)samples_f_neg_touch_prep_f3.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f3_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f3.size())).toString(), (new Float(neg_touch_prep_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f3)).toString().substring(0, (new Float(neg_touch_prep_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_neg_touch_prep_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F4'").append(s).toString());
            neg_touch_prep_f4 = ((float)samples_f_neg_touch_prep_f4.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f4_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f4.size())).toString(), (new Float(neg_touch_prep_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f4)).toString().substring(0, (new Float(neg_touch_prep_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_neg_touch_prep_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F5'").append(s).toString());
            neg_touch_prep_f5 = ((float)samples_f_neg_touch_prep_f5.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f5_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f5.size())).toString(), (new Float(neg_touch_prep_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f5)).toString().substring(0, (new Float(neg_touch_prep_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_neg_touch_prep_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='F6'").append(s).toString());
            neg_touch_prep_f6 = ((float)samples_f_neg_touch_prep_f6.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_f6_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_f6.size())).toString(), (new Float(neg_touch_prep_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_f6)).toString().substring(0, (new Float(neg_touch_prep_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_neg_touch_prep_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_TOUCH_PREP ='-' AND TIMEPOINT_NAME='FS'").append(s).toString());
            neg_touch_prep_fs = ((float)samples_f_neg_touch_prep_fs.size() / (float)samples_f_neg_touch_prep_alltps.size()) * 100F;
            neg_touch_prep_fs_Array = (new String[] {
                (new Integer(samples_f_neg_touch_prep_fs.size())).toString(), (new Float(neg_touch_prep_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_touch_prep_fs)).toString().substring(0, (new Float(neg_touch_prep_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_f_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Frozen' and FROZEN_h_e is not null").append(s).toString());
            f_h_e = ((float)samples_f_h_e.size() / (float)frozenSamples.size()) * 100F;
            f_h_e_Array = (new String[] {
                (new Integer(samples_f_h_e.size())).toString(), (new Float(f_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_h_e)).toString().substring(0, (new Float(f_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_f_pos_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND CORE_TYPE='Frozen'").append(s).toString());
            f_pos_h_e = ((float)samples_f_pos_h_e.size() / (float)samples_f_h_e.size()) * 100F;
            f_pos_h_e_Array = (new String[] {
                (new Integer(samples_f_pos_h_e.size())).toString(), (new Float(f_pos_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_pos_h_e)).toString().substring(0, (new Float(f_pos_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_f_neg_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='-' AND CORE_TYPE='Frozen'").append(s).toString());
            f_neg_h_e = ((float)samples_f_neg_h_e.size() / (float)samples_f_h_e.size()) * 100F;
            f_neg_h_e_Array = (new String[] {
                (new Integer(samples_f_neg_h_e.size())).toString(), (new Float(f_neg_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f_neg_h_e)).toString().substring(0, (new Float(f_neg_h_e)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenInstitutionH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_f_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E is not null").append(s).toString());
            h_e_allInstitutes = ((float)samples_f_h_e_allInstitutes.size() / (float)frozenSamples.size()) * 100F;
            h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_h_e_allInstitutes.size())).toString(), (new Float(h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_allInstitutes)).toString().substring(0, (new Float(h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =1 and FROZEN_H_E is not null").append(s).toString());
            h_e_ucsf = ((float)samples_f_h_e_ucsf.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_ucsf_Array = (new String[] {
                (new Integer(samples_f_h_e_ucsf.size())).toString(), (new Float(h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_ucsf)).toString().substring(0, (new Float(h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =2  and FROZEN_H_E is not null").append(s).toString());
            h_e_unc = ((float)samples_f_h_e_unc.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_unc_Array = (new String[] {
                (new Integer(samples_f_h_e_unc.size())).toString(), (new Float(h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_unc)).toString().substring(0, (new Float(h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =3  and FROZEN_H_E is not null").append(s).toString());
            h_e_upenn = ((float)samples_f_h_e_upenn.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_upenn_Array = (new String[] {
                (new Integer(samples_f_h_e_upenn.size())).toString(), (new Float(h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_upenn)).toString().substring(0, (new Float(h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =4  and FROZEN_H_E is not null").append(s).toString());
            h_e_uc = ((float)samples_f_h_e_uc.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_uc_Array = (new String[] {
                (new Integer(samples_f_h_e_uc.size())).toString(), (new Float(h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_uc)).toString().substring(0, (new Float(h_e_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =5  and FROZEN_H_E is not null").append(s).toString());
            h_e_uab = ((float)samples_f_h_e_uab.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_uab_Array = (new String[] {
                (new Integer(samples_f_h_e_uab.size())).toString(), (new Float(h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_uab)).toString().substring(0, (new Float(h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =6  and FROZEN_H_E is not null").append(s).toString());
            h_e_mskcc = ((float)samples_f_h_e_mskcc.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_mskcc_Array = (new String[] {
                (new Integer(samples_f_h_e_mskcc.size())).toString(), (new Float(h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_mskcc)).toString().substring(0, (new Float(h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =7  and FROZEN_H_E is not null").append(s).toString());
            h_e_gw = ((float)samples_f_h_e_gw.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_gw_Array = (new String[] {
                (new Integer(samples_f_h_e_gw.size())).toString(), (new Float(h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_gw)).toString().substring(0, (new Float(h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =8  and FROZEN_H_E is not null").append(s).toString());
            h_e_uw = ((float)samples_f_h_e_uw.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_uw_Array = (new String[] {
                (new Integer(samples_f_h_e_uw.size())).toString(), (new Float(h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_uw)).toString().substring(0, (new Float(h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =9  and FROZEN_H_E is not null").append(s).toString());
            h_e_ut = ((float)samples_f_h_e_ut.size() / (float)samples_f_h_e_allInstitutes.size()) * 100F;
            h_e_ut_Array = (new String[] {
                (new Integer(samples_f_h_e_ut.size())).toString(), (new Float(h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_ut)).toString().substring(0, (new Float(h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_f_h_e_not_null = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID IS NOT NULL AND FROZEN_h_e IS NOT NULL").append(s).toString());
            samples_f_pos_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+'").append(s).toString());
            pos_h_e_allInstitutes = ((float)samples_f_pos_h_e_allInstitutes.size() / (float)samples_f_h_e_not_null.size()) * 100F;
            pos_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_allInstitutes.size())).toString(), (new Float(pos_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_allInstitutes)).toString().substring(0, (new Float(pos_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_pos_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            pos_h_e_ucsf = ((float)samples_f_pos_h_e_ucsf.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_ucsf.size())).toString(), (new Float(pos_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_ucsf)).toString().substring(0, (new Float(pos_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_pos_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            pos_h_e_unc = ((float)samples_f_pos_h_e_unc.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_unc_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_unc.size())).toString(), (new Float(pos_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_unc)).toString().substring(0, (new Float(pos_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_pos_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            pos_h_e_upenn = ((float)samples_f_pos_h_e_upenn.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_upenn_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_upenn.size())).toString(), (new Float(pos_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_upenn)).toString().substring(0, (new Float(pos_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_pos_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            pos_h_e_uc = ((float)samples_f_pos_h_e_uc.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_uc_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_uc.size())).toString(), (new Float(pos_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_uc)).toString().substring(0, (new Float(pos_h_e_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_pos_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            pos_h_e_uab = ((float)samples_f_pos_h_e_uab.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_uab_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_uab.size())).toString(), (new Float(pos_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_uab)).toString().substring(0, (new Float(pos_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_pos_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            pos_h_e_mskcc = ((float)samples_f_pos_h_e_mskcc.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_mskcc.size())).toString(), (new Float(pos_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_mskcc)).toString().substring(0, (new Float(pos_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_pos_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            pos_h_e_gw = ((float)samples_f_pos_h_e_gw.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_gw_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_gw.size())).toString(), (new Float(pos_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_gw)).toString().substring(0, (new Float(pos_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_pos_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            pos_h_e_uw = ((float)samples_f_pos_h_e_uw.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_uw_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_uw.size())).toString(), (new Float(pos_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_uw)).toString().substring(0, (new Float(pos_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_pos_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_H_E ='+' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            pos_h_e_ut = ((float)samples_f_pos_h_e_ut.size() / (float)samples_f_pos_h_e_allInstitutes.size()) * 100F;
            pos_h_e_ut_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_ut.size())).toString(), (new Float(pos_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_ut)).toString().substring(0, (new Float(pos_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_f_neg_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-'").append(s).toString());
            neg_h_e_allInstitutes = ((float)samples_f_neg_h_e_allInstitutes.size() / (float)samples_f_h_e_not_null.size()) * 100F;
            neg_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_allInstitutes.size())).toString(), (new Float(neg_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_allInstitutes)).toString().substring(0, (new Float(neg_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_f_neg_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            neg_h_e_ucsf = ((float)samples_f_neg_h_e_ucsf.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_ucsf.size())).toString(), (new Float(neg_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_ucsf)).toString().substring(0, (new Float(neg_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_f_neg_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            neg_h_e_unc = ((float)samples_f_neg_h_e_unc.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_unc_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_unc.size())).toString(), (new Float(neg_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_unc)).toString().substring(0, (new Float(neg_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_f_neg_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            neg_h_e_upenn = ((float)samples_f_neg_h_e_upenn.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_upenn_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_upenn.size())).toString(), (new Float(neg_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_upenn)).toString().substring(0, (new Float(neg_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_f_neg_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            neg_h_e_uc = ((float)samples_f_neg_h_e_uc.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_uc_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_uc.size())).toString(), (new Float(neg_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_uc)).toString().substring(0, (new Float(neg_h_e_uc)).toString().indexOf("."))).append("%(Univ.of Chicago)").toString()
            });
            samples_f_neg_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            neg_h_e_uab = ((float)samples_f_neg_h_e_uab.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_uab_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_uab.size())).toString(), (new Float(neg_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_uab)).toString().substring(0, (new Float(neg_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_f_neg_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            neg_h_e_mskcc = ((float)samples_f_neg_h_e_mskcc.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_mskcc.size())).toString(), (new Float(neg_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_mskcc)).toString().substring(0, (new Float(neg_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_f_neg_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            neg_h_e_gw = ((float)samples_f_neg_h_e_gw.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_gw_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_gw.size())).toString(), (new Float(neg_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_gw)).toString().substring(0, (new Float(neg_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_f_neg_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            neg_h_e_uw = ((float)samples_f_neg_h_e_uw.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_uw_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_uw.size())).toString(), (new Float(neg_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_uw)).toString().substring(0, (new Float(neg_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_f_neg_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            neg_h_e_ut = ((float)samples_f_neg_h_e_ut.size() / (float)samples_f_neg_h_e_allInstitutes.size()) * 100F;
            neg_h_e_ut_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_ut.size())).toString(), (new Float(neg_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_ut)).toString().substring(0, (new Float(neg_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getFrozenTimePtH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_f_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null AND TIMEPOINT_NAME IN('F1','F2','F3','F4','F5','F6','FS')").append(s).toString());
            h_e_alltps = ((float)samples_f_h_e_alltps.size() / (float)frozenSamples.size()) * 100F;
            h_e_alltps_Array = (new String[] {
                (new Integer(samples_f_h_e_alltps.size())).toString(), (new Float(h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_alltps)).toString().substring(0, (new Float(h_e_alltps)).toString().indexOf("."))).append("%(ALL T.P.)").toString()
            });
            samples_f_h_e_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null AND TIMEPOINT_NAME ='F1'").append(s).toString());
            h_e_f1 = ((float)samples_f_h_e_f1.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f1_Array = (new String[] {
                (new Integer(samples_f_h_e_f1.size())).toString(), (new Float(h_e_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f1)).toString().substring(0, (new Float(h_e_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_h_e_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null AND TIMEPOINT_NAME ='F2'").append(s).toString());
            h_e_f2 = ((float)samples_f_h_e_f2.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f2_Array = (new String[] {
                (new Integer(samples_f_h_e_f2.size())).toString(), (new Float(h_e_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f2)).toString().substring(0, (new Float(h_e_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_h_e_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null AND TIMEPOINT_NAME ='F3'").append(s).toString());
            h_e_f3 = ((float)samples_f_h_e_f3.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f3_Array = (new String[] {
                (new Integer(samples_f_h_e_f3.size())).toString(), (new Float(h_e_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f3)).toString().substring(0, (new Float(h_e_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_h_e_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null AND TIMEPOINT_NAME ='F4'").append(s).toString());
            h_e_f4 = ((float)samples_f_h_e_f4.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f4_Array = (new String[] {
                (new Integer(samples_f_h_e_f4.size())).toString(), (new Float(h_e_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f4)).toString().substring(0, (new Float(h_e_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_h_e_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null  AND TIMEPOINT_NAME ='F5'").append(s).toString());
            h_e_f5 = ((float)samples_f_h_e_f5.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f5_Array = (new String[] {
                (new Integer(samples_f_h_e_f5.size())).toString(), (new Float(h_e_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f5)).toString().substring(0, (new Float(h_e_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_h_e_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append("FROZEN_h_e is not null  AND  TIMEPOINT_NAME ='F6'").append(s).toString());
            h_e_f6 = ((float)samples_f_h_e_f6.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_f6_Array = (new String[] {
                (new Integer(samples_f_h_e_f6.size())).toString(), (new Float(h_e_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_f6)).toString().substring(0, (new Float(h_e_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_h_e_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e is not null  AND TIMEPOINT_NAME ='FS'").append(s).toString());
            h_e_fs = ((float)samples_f_h_e_fs.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            h_e_fs_Array = (new String[] {
                (new Integer(samples_f_h_e_fs.size())).toString(), (new Float(h_e_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(h_e_fs)).toString().substring(0, (new Float(h_e_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
            samples_f_pos_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+'").append(s).toString());
            pos_h_e_alltps = ((float)samples_f_pos_h_e_alltps.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            pos_h_e_alltps_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_alltps.size())).toString(), (new Float(pos_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_alltps)).toString().substring(0, (new Float(pos_h_e_alltps)).toString().indexOf("."))).append("%(ALL T.P.)").toString()
            });
            samples_f_pos_h_e_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F1'").append(s).toString());
            pos_h_e_f1 = ((float)samples_f_pos_h_e_f1.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f1_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f1.size())).toString(), (new Float(pos_h_e_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f1)).toString().substring(0, (new Float(pos_h_e_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_pos_h_e_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F2'").append(s).toString());
            pos_h_e_f2 = ((float)samples_f_pos_h_e_f2.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f2_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f2.size())).toString(), (new Float(pos_h_e_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f2)).toString().substring(0, (new Float(pos_h_e_f2)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_pos_h_e_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F3'").append(s).toString());
            pos_h_e_f3 = ((float)samples_f_pos_h_e_f3.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f3_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f3.size())).toString(), (new Float(pos_h_e_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f3)).toString().substring(0, (new Float(pos_h_e_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_pos_h_e_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F4'").append(s).toString());
            pos_h_e_f4 = ((float)samples_f_pos_h_e_f4.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f4_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f4.size())).toString(), (new Float(pos_h_e_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f4)).toString().substring(0, (new Float(pos_h_e_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_pos_h_e_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F5'").append(s).toString());
            pos_h_e_f5 = ((float)samples_f_pos_h_e_f5.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f5_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f5.size())).toString(), (new Float(pos_h_e_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f5)).toString().substring(0, (new Float(pos_h_e_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_pos_h_e_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='F6'").append(s).toString());
            pos_h_e_f6 = ((float)samples_f_pos_h_e_f6.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_f6_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_f6.size())).toString(), (new Float(pos_h_e_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_f6)).toString().substring(0, (new Float(pos_h_e_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_pos_h_e_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='+' AND TIMEPOINT_NAME='FS'").append(s).toString());
            pos_h_e_fs = ((float)samples_f_pos_h_e_fs.size() / (float)samples_f_pos_h_e_alltps.size()) * 100F;
            pos_h_e_fs_Array = (new String[] {
                (new Integer(samples_f_pos_h_e_fs.size())).toString(), (new Float(pos_h_e_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(pos_h_e_fs)).toString().substring(0, (new Float(pos_h_e_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
            samples_f_neg_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-'").append(s).toString());
            neg_h_e_alltps = ((float)samples_f_neg_h_e_alltps.size() / (float)samples_f_h_e_alltps.size()) * 100F;
            neg_h_e_alltps_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_alltps.size())).toString(), (new Float(neg_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_alltps)).toString().substring(0, (new Float(neg_h_e_alltps)).toString().indexOf("."))).append("%(ALL T.P.)").toString()
            });
            samples_f_neg_h_e_f1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F1'").append(s).toString());
            neg_h_e_f1 = ((float)samples_f_neg_h_e_f1.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f1_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f1.size())).toString(), (new Float(neg_h_e_f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f1)).toString().substring(0, (new Float(neg_h_e_f1)).toString().indexOf("."))).append("%(F1)").toString()
            });
            samples_f_neg_h_e_f2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F2'").append(s).toString());
            neg_h_e_f2 = ((float)samples_f_neg_h_e_f2.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f2_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f2.size())).toString(), (new Float(neg_h_e_f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f2)).toString().substring(0, (new Float(f_touch_prep)).toString().indexOf("."))).append("%(F2)").toString()
            });
            samples_f_neg_h_e_f3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F3'").append(s).toString());
            neg_h_e_f3 = ((float)samples_f_neg_h_e_f3.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f3_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f3.size())).toString(), (new Float(neg_h_e_f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f3)).toString().substring(0, (new Float(neg_h_e_f3)).toString().indexOf("."))).append("%(F3)").toString()
            });
            samples_f_neg_h_e_f4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F4'").append(s).toString());
            neg_h_e_f4 = ((float)samples_f_neg_h_e_f4.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f4_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f4.size())).toString(), (new Float(neg_h_e_f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f4)).toString().substring(0, (new Float(neg_h_e_f4)).toString().indexOf("."))).append("%(F4)").toString()
            });
            samples_f_neg_h_e_f5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F5'").append(s).toString());
            neg_h_e_f5 = ((float)samples_f_neg_h_e_f5.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f5_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f5.size())).toString(), (new Float(neg_h_e_f5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f5)).toString().substring(0, (new Float(neg_h_e_f5)).toString().indexOf("."))).append("%(F5)").toString()
            });
            samples_f_neg_h_e_f6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='F6'").append(s).toString());
            neg_h_e_f6 = ((float)samples_f_neg_h_e_f6.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_f6_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_f6.size())).toString(), (new Float(neg_h_e_f6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_f6)).toString().substring(0, (new Float(neg_h_e_f6)).toString().indexOf("."))).append("%(F6)").toString()
            });
            samples_f_neg_h_e_fs = qi_sample.retrieveAllWhere((new StringBuilder()).append(" FROZEN_h_e ='-' AND TIMEPOINT_NAME='FS'").append(s).toString());
            neg_h_e_fs = ((float)samples_f_neg_h_e_fs.size() / (float)samples_f_neg_h_e_alltps.size()) * 100F;
            neg_h_e_fs_Array = (new String[] {
                (new Integer(samples_f_neg_h_e_fs.size())).toString(), (new Float(neg_h_e_fs)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(neg_h_e_fs)).toString().substring(0, (new Float(neg_h_e_fs)).toString().indexOf("."))).append("%(FS)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Paraffin' and H_E_REVIEW is not null").append(s).toString());
            p_h_e = ((float)samples_p.size() / (float)paraffinSamples.size()) * 100F;
            p_h_e_Array = (new String[] {
                (new Integer(samples_p.size())).toString(), (new Float(p_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e)).toString().substring(0, (new Float(p_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_pos_pos_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_pos_pos_h_e = ((float)samples_p_pos_pos_h_e.size() / (float)samples_p.size()) * 100F;
            p_pos_pos_h_e_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e.size())).toString(), (new Float(p_pos_pos_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e)).toString().substring(0, (new Float(p_pos_pos_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_neg_neg_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_neg_neg_h_e = ((float)samples_p_neg_neg_h_e.size() / (float)samples_p.size()) * 100F;
            p_neg_neg_h_e_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e.size())).toString(), (new Float(p_neg_neg_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e)).toString().substring(0, (new Float(p_neg_neg_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_pos_neg_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_pos_neg_h_e = ((float)samples_p_pos_neg_h_e.size() / (float)samples_p.size()) * 100F;
            p_pos_neg_h_e_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e.size())).toString(), (new Float(p_pos_neg_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e)).toString().substring(0, (new Float(p_pos_neg_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_neg_pos_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_neg_pos_h_e = ((float)samples_p_neg_pos_h_e.size() / (float)samples_p.size()) * 100F;
            p_neg_pos_h_e_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e.size())).toString(), (new Float(p_neg_pos_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e)).toString().substring(0, (new Float(p_neg_pos_h_e)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_blank_h_e = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_blank_h_e = ((float)samples_p_blank_h_e.size() / (float)samples_p.size()) * 100F;
            p_blank_h_e_Array = (new String[] {
                (new Integer(samples_p_blank_h_e.size())).toString(), (new Float(p_blank_h_e)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e)).toString().substring(0, (new Float(p_blank_h_e)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinInstitutionH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW is not null").append(s).toString());
            p_h_e_allInstitutes = ((float)samples_p_h_e_allInstitutes.size() / (float)paraffinSamples.size()) * 100F;
            p_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_h_e_allInstitutes.size())).toString(), (new Float(p_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_allInstitutes)).toString().substring(0, (new Float(p_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =1 and H_E_REVIEW is not null").append(s).toString());
            p_h_e_ucsf = ((float)samples_p_h_e_ucsf.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_h_e_ucsf.size())).toString(), (new Float(p_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_ucsf)).toString().substring(0, (new Float(p_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =2  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_unc = ((float)samples_p_h_e_unc.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_h_e_unc.size())).toString(), (new Float(p_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_unc)).toString().substring(0, (new Float(p_h_e_upenn)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =3  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_upenn = ((float)samples_p_h_e_upenn.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_h_e_upenn.size())).toString(), (new Float(p_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_upenn)).toString().substring(0, (new Float(p_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =4  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_uc = ((float)samples_p_h_e_uc.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_h_e_uc.size())).toString(), (new Float(p_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_uc)).toString().substring(0, (new Float(p_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =5  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_uab = ((float)samples_p_h_e_uab.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_h_e_uab.size())).toString(), (new Float(p_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_uab)).toString().substring(0, (new Float(p_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =6  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_mskcc = ((float)samples_p_h_e_mskcc.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_h_e_mskcc.size())).toString(), (new Float(p_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_mskcc)).toString().substring(0, (new Float(p_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =7  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_gw = ((float)samples_p_h_e_gw.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_h_e_gw.size())).toString(), (new Float(p_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_gw)).toString().substring(0, (new Float(p_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =8  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_uw = ((float)samples_p_h_e_uw.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_h_e_uw.size())).toString(), (new Float(p_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_uw)).toString().substring(0, (new Float(p_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =9  and H_E_REVIEW is not null").append(s).toString());
            p_h_e_ut = ((float)samples_p_h_e_ut.size() / (float)samples_p_h_e_allInstitutes.size()) * 100F;
            p_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_h_e_ut.size())).toString(), (new Float(p_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_ut)).toString().substring(0, (new Float(p_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_h_e_not_null = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID IS NOT NULL AND H_E_REVIEW IS NOT NULL").append(s).toString());
            samples_p_pos_pos_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1'").append(s).toString());
            p_pos_pos_h_e_allInstitutes = ((float)samples_p_pos_pos_h_e_allInstitutes.size() / (float)samples_p_h_e_not_null.size()) * 100F;
            p_pos_pos_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_allInstitutes.size())).toString(), (new Float(p_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_ut)).toString().substring(0, (new Float(p_h_e_ut)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_pos_pos_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_pos_pos_h_e_ucsf = ((float)samples_p_pos_pos_h_e_ucsf.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_ucsf.size())).toString(), (new Float(p_pos_pos_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_ucsf)).toString().substring(0, (new Float(p_pos_pos_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_pos_pos_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_pos_pos_h_e_unc = ((float)samples_p_pos_pos_h_e_unc.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_unc.size())).toString(), (new Float(p_pos_pos_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_unc)).toString().substring(0, (new Float(p_pos_pos_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_pos_pos_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_pos_pos_h_e_upenn = ((float)samples_p_pos_pos_h_e_upenn.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_upenn.size())).toString(), (new Float(p_pos_pos_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_upenn)).toString().substring(0, (new Float(p_pos_pos_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_pos_pos_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_pos_pos_h_e_uc = ((float)samples_p_pos_pos_h_e_uc.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_uc.size())).toString(), (new Float(p_pos_pos_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_uc)).toString().substring(0, (new Float(p_pos_pos_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_pos_pos_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_pos_pos_h_e_uab = ((float)samples_p_pos_pos_h_e_uab.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_uab.size())).toString(), (new Float(p_pos_pos_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_uab)).toString().substring(0, (new Float(p_pos_pos_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_pos_pos_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_pos_pos_h_e_mskcc = ((float)samples_p_pos_pos_h_e_mskcc.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_mskcc.size())).toString(), (new Float(p_pos_pos_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_mskcc)).toString().substring(0, (new Float(p_pos_pos_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_pos_pos_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_pos_pos_h_e_gw = ((float)samples_p_pos_pos_h_e_gw.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_gw.size())).toString(), (new Float(p_pos_pos_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_gw)).toString().substring(0, (new Float(p_pos_pos_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_pos_pos_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_pos_pos_h_e_uw = ((float)samples_p_pos_pos_h_e_uw.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_uw.size())).toString(), (new Float(p_pos_pos_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_uw)).toString().substring(0, (new Float(p_pos_pos_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_pos_pos_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_pos_pos_h_e_ut = ((float)samples_p_pos_pos_h_e_ut.size() / (float)samples_p_pos_pos_h_e_allInstitutes.size()) * 100F;
            p_pos_pos_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_ut.size())).toString(), (new Float(p_pos_pos_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_ut)).toString().substring(0, (new Float(p_pos_pos_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_neg_neg_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2'").append(s).toString());
            p_neg_neg_h_e_allInstitutes = ((float)samples_p_neg_neg_h_e_allInstitutes.size() / (float)samples_p_h_e_not_null.size()) * 100F;
            p_neg_neg_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_allInstitutes.size())).toString(), (new Float(p_neg_neg_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_allInstitutes)).toString().substring(0, (new Float(p_neg_neg_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_neg_neg_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_neg_neg_h_e_ucsf = ((float)samples_p_neg_neg_h_e_ucsf.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_ucsf.size())).toString(), (new Float(p_neg_neg_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_ucsf)).toString().substring(0, (new Float(p_neg_neg_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_neg_neg_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_neg_neg_h_e_unc = ((float)samples_p_neg_neg_h_e_unc.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_unc.size())).toString(), (new Float(p_neg_neg_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_unc)).toString().substring(0, (new Float(p_neg_neg_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_neg_neg_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_neg_neg_h_e_upenn = ((float)samples_p_neg_neg_h_e_upenn.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_upenn.size())).toString(), (new Float(p_neg_neg_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_upenn)).toString().substring(0, (new Float(p_neg_neg_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_neg_neg_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_neg_neg_h_e_uc = ((float)samples_p_neg_neg_h_e_uc.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_uc.size())).toString(), (new Float(p_neg_neg_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_uc)).toString().substring(0, (new Float(p_neg_neg_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_neg_neg_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_neg_neg_h_e_uab = ((float)samples_p_neg_neg_h_e_uab.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_uab.size())).toString(), (new Float(p_neg_neg_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_uab)).toString().substring(0, (new Float(p_neg_neg_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_neg_neg_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_neg_neg_h_e_mskcc = ((float)samples_p_neg_neg_h_e_mskcc.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_mskcc.size())).toString(), (new Float(p_neg_neg_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_mskcc)).toString().substring(0, (new Float(p_neg_neg_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_neg_neg_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_neg_neg_h_e_gw = ((float)samples_p_neg_neg_h_e_gw.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_gw.size())).toString(), (new Float(p_neg_neg_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_gw)).toString().substring(0, (new Float(p_neg_neg_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_neg_neg_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_neg_neg_h_e_uw = ((float)samples_p_neg_neg_h_e_uw.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_uw.size())).toString(), (new Float(p_neg_neg_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_uw)).toString().substring(0, (new Float(p_neg_neg_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_neg_neg_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_neg_neg_h_e_ut = ((float)samples_p_neg_neg_h_e_ut.size() / (float)samples_p_neg_neg_h_e_allInstitutes.size()) * 100F;
            p_neg_neg_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_ut.size())).toString(), (new Float(p_neg_neg_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_ut)).toString().substring(0, (new Float(p_neg_neg_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_pos_neg_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3'").append(s).toString());
            p_pos_neg_h_e_allInstitutes = ((float)samples_p_pos_neg_h_e_allInstitutes.size() / (float)samples_p_h_e_not_null.size()) * 100F;
            p_pos_neg_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_allInstitutes.size())).toString(), (new Float(p_pos_neg_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_allInstitutes)).toString().substring(0, (new Float(p_pos_neg_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_pos_neg_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_pos_neg_h_e_ucsf = ((float)samples_p_pos_neg_h_e_ucsf.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_ucsf.size())).toString(), (new Float(p_pos_neg_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_ucsf)).toString().substring(0, (new Float(p_pos_neg_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_pos_neg_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_pos_neg_h_e_unc = ((float)samples_p_pos_neg_h_e_unc.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_unc.size())).toString(), (new Float(p_pos_neg_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_unc)).toString().substring(0, (new Float(p_pos_neg_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_pos_neg_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_pos_neg_h_e_upenn = ((float)samples_p_pos_neg_h_e_upenn.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_upenn.size())).toString(), (new Float(p_pos_neg_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_upenn)).toString().substring(0, (new Float(p_pos_neg_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_pos_neg_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_pos_neg_h_e_uc = ((float)samples_p_pos_neg_h_e_uc.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_uc.size())).toString(), (new Float(p_pos_neg_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_uc)).toString().substring(0, (new Float(p_pos_neg_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_pos_neg_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_pos_neg_h_e_uab = ((float)samples_p_pos_neg_h_e_uab.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_uab.size())).toString(), (new Float(p_pos_neg_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_uab)).toString().substring(0, (new Float(p_pos_neg_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_pos_neg_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_pos_neg_h_e_mskcc = ((float)samples_p_pos_neg_h_e_mskcc.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_mskcc.size())).toString(), (new Float(p_pos_neg_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_mskcc)).toString().substring(0, (new Float(p_pos_neg_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_pos_neg_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_pos_neg_h_e_gw = ((float)samples_p_pos_neg_h_e_gw.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_gw.size())).toString(), (new Float(p_pos_neg_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_gw)).toString().substring(0, (new Float(p_pos_neg_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_pos_neg_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_pos_neg_h_e_uw = ((float)samples_p_pos_neg_h_e_uw.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_uw.size())).toString(), (new Float(p_pos_neg_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_uw)).toString().substring(0, (new Float(p_pos_neg_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_pos_neg_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_pos_neg_h_e_ut = ((float)samples_p_pos_neg_h_e_ut.size() / (float)samples_p_pos_neg_h_e_allInstitutes.size()) * 100F;
            p_pos_neg_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_ut.size())).toString(), (new Float(p_pos_neg_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_ut)).toString().substring(0, (new Float(p_pos_neg_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_neg_pos_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4'").append(s).toString());
            p_neg_pos_h_e_allInstitutes = ((float)samples_p_neg_pos_h_e_allInstitutes.size() / (float)samples_p_h_e_not_null.size()) * 100F;
            p_neg_pos_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_allInstitutes.size())).toString(), (new Float(p_neg_pos_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_allInstitutes)).toString().substring(0, (new Float(p_neg_pos_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_neg_pos_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_neg_pos_h_e_ucsf = ((float)samples_p_neg_pos_h_e_ucsf.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_ucsf.size())).toString(), (new Float(p_neg_pos_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_ucsf)).toString().substring(0, (new Float(p_neg_pos_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_neg_pos_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_neg_pos_h_e_unc = ((float)samples_p_neg_pos_h_e_unc.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_unc.size())).toString(), (new Float(p_neg_pos_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_unc)).toString().substring(0, (new Float(p_neg_pos_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_neg_pos_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_neg_pos_h_e_upenn = ((float)samples_p_neg_pos_h_e_upenn.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_upenn.size())).toString(), (new Float(p_neg_pos_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_upenn)).toString().substring(0, (new Float(p_neg_pos_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_neg_pos_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_neg_pos_h_e_uc = ((float)samples_p_neg_pos_h_e_uc.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_uc.size())).toString(), (new Float(p_neg_pos_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_uc)).toString().substring(0, (new Float(p_neg_pos_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_neg_pos_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_neg_pos_h_e_uab = ((float)samples_p_neg_pos_h_e_uab.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_uab.size())).toString(), (new Float(p_neg_pos_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_uab)).toString().substring(0, (new Float(p_neg_pos_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_neg_pos_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_neg_pos_h_e_mskcc = ((float)samples_p_neg_pos_h_e_mskcc.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_mskcc.size())).toString(), (new Float(p_neg_pos_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_mskcc)).toString().substring(0, (new Float(p_neg_pos_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_neg_pos_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_neg_pos_h_e_gw = ((float)samples_p_neg_pos_h_e_gw.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_gw.size())).toString(), (new Float(p_neg_pos_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_gw)).toString().substring(0, (new Float(p_neg_pos_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_neg_pos_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_neg_pos_h_e_uw = ((float)samples_p_neg_pos_h_e_uw.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_uw.size())).toString(), (new Float(p_neg_pos_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_uw)).toString().substring(0, (new Float(p_neg_pos_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_neg_pos_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_neg_pos_h_e_ut = ((float)samples_p_neg_pos_h_e_ut.size() / (float)samples_p_neg_pos_h_e_allInstitutes.size()) * 100F;
            p_neg_pos_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_ut.size())).toString(), (new Float(p_neg_pos_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_ut)).toString().substring(0, (new Float(p_neg_pos_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_blank_h_e_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5'").append(s).toString());
            p_blank_h_e_allInstitutes = ((float)samples_p_blank_h_e_allInstitutes.size() / (float)samples_p_h_e_not_null.size()) * 100F;
            p_blank_h_e_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_allInstitutes.size())).toString(), (new Float(p_blank_h_e_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_allInstitutes)).toString().substring(0, (new Float(p_blank_h_e_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_blank_h_e_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_blank_h_e_ucsf = ((float)samples_p_blank_h_e_ucsf.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_ucsf_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_ucsf.size())).toString(), (new Float(p_blank_h_e_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_ucsf)).toString().substring(0, (new Float(p_blank_h_e_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_blank_h_e_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_blank_h_e_unc = ((float)samples_p_blank_h_e_unc.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_unc_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_unc.size())).toString(), (new Float(p_blank_h_e_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_unc)).toString().substring(0, (new Float(p_blank_h_e_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_blank_h_e_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_blank_h_e_upenn = ((float)samples_p_blank_h_e_upenn.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_upenn_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_upenn.size())).toString(), (new Float(p_blank_h_e_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_upenn)).toString().substring(0, (new Float(p_blank_h_e_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_blank_h_e_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_blank_h_e_uc = ((float)samples_p_blank_h_e_uc.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_uc_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_uc.size())).toString(), (new Float(p_blank_h_e_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_uc)).toString().substring(0, (new Float(p_blank_h_e_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_blank_h_e_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_blank_h_e_uab = ((float)samples_p_blank_h_e_uab.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_uab_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_uab.size())).toString(), (new Float(p_blank_h_e_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_uab)).toString().substring(0, (new Float(p_blank_h_e_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_blank_h_e_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_blank_h_e_mskcc = ((float)samples_p_blank_h_e_mskcc.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_mskcc_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_mskcc.size())).toString(), (new Float(p_blank_h_e_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_mskcc)).toString().substring(0, (new Float(p_blank_h_e_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_blank_h_e_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_blank_h_e_gw = ((float)samples_p_blank_h_e_gw.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_gw_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_gw.size())).toString(), (new Float(p_blank_h_e_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_gw)).toString().substring(0, (new Float(p_blank_h_e_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_blank_h_e_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_blank_h_e_uw = ((float)samples_p_blank_h_e_uw.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_uw_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_uw.size())).toString(), (new Float(p_blank_h_e_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_uw)).toString().substring(0, (new Float(p_blank_h_e_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_blank_h_e_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_blank_h_e_ut = ((float)samples_p_blank_h_e_ut.size() / (float)samples_p_blank_h_e_allInstitutes.size()) * 100F;
            p_blank_h_e_ut_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_ut.size())).toString(), (new Float(p_blank_h_e_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_ut)).toString().substring(0, (new Float(p_blank_h_e_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinTimePtH_EInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null AND TIMEPOINT_NAME IN('P1','P2','P3','P4','P5','P6','PS')").append(s).toString());
            p_h_e_alltps = ((float)samples_p_h_e_alltps.size() / (float)paraffinSamples.size()) * 100F;
            p_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_h_e_alltps.size())).toString(), (new Float(p_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_alltps)).toString().substring(0, (new Float(p_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null AND TIMEPOINT_NAME ='P1'").append(s).toString());
            p_h_e_p1 = ((float)samples_p_h_e_p1.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_h_e_p1.size())).toString(), (new Float(p_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p1)).toString().substring(0, (new Float(p_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null AND TIMEPOINT_NAME ='P2'").append(s).toString());
            p_h_e_p2 = ((float)samples_p_h_e_p2.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_h_e_p2.size())).toString(), (new Float(p_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p2)).toString().substring(0, (new Float(p_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null AND TIMEPOINT_NAME ='P3'").append(s).toString());
            p_h_e_p3 = ((float)samples_p_h_e_p3.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_h_e_p3.size())).toString(), (new Float(p_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p3)).toString().substring(0, (new Float(p_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null AND TIMEPOINT_NAME ='P4'").append(s).toString());
            p_h_e_p4 = ((float)samples_p_h_e_p4.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_h_e_p4.size())).toString(), (new Float(p_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p4)).toString().substring(0, (new Float(p_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null  AND TIMEPOINT_NAME ='P5'").append(s).toString());
            p_h_e_p5 = ((float)samples_p_h_e_p5.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_h_e_p5.size())).toString(), (new Float(p_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p5)).toString().substring(0, (new Float(p_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null  AND  TIMEPOINT_NAME ='P6'").append(s).toString());
            p_h_e_p6 = ((float)samples_p_h_e_p6.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_h_e_p6.size())).toString(), (new Float(p_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_p6)).toString().substring(0, (new Float(p_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" h_e_REVIEW is not null  AND TIMEPOINT_NAME ='PS'").append(s).toString());
            p_h_e_ps = ((float)samples_p_h_e_ps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_h_e_ps.size())).toString(), (new Float(p_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_h_e_ps)).toString().substring(0, (new Float(p_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_pos_pos_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1'").append(s).toString());
            p_pos_pos_h_e_alltps = ((float)samples_p_pos_pos_h_e_alltps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_alltps.size())).toString(), (new Float(p_pos_pos_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_alltps)).toString().substring(0, (new Float(p_pos_pos_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_pos_pos_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_pos_pos_h_e_p1 = ((float)samples_p_pos_pos_h_e_p1.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p1.size())).toString(), (new Float(p_pos_pos_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p1)).toString().substring(0, (new Float(p_pos_pos_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_pos_pos_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_pos_pos_h_e_p2 = ((float)samples_p_pos_pos_h_e_p2.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p2.size())).toString(), (new Float(p_pos_pos_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p2)).toString().substring(0, (new Float(p_pos_pos_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_pos_pos_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_pos_pos_h_e_p3 = ((float)samples_p_pos_pos_h_e_p3.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p3.size())).toString(), (new Float(p_pos_pos_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p3)).toString().substring(0, (new Float(p_pos_pos_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_pos_pos_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_pos_pos_h_e_p4 = ((float)samples_p_pos_pos_h_e_p4.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p4.size())).toString(), (new Float(p_pos_pos_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p4)).toString().substring(0, (new Float(p_pos_pos_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_pos_pos_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_pos_pos_h_e_p5 = ((float)samples_p_pos_pos_h_e_p5.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p5.size())).toString(), (new Float(p_pos_pos_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p5)).toString().substring(0, (new Float(p_pos_pos_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_pos_pos_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_pos_pos_h_e_p6 = ((float)samples_p_pos_pos_h_e_p6.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_p6.size())).toString(), (new Float(p_pos_pos_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_p6)).toString().substring(0, (new Float(p_pos_pos_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_pos_pos_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='1' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_pos_pos_h_e_ps = ((float)samples_p_pos_pos_h_e_ps.size() / (float)samples_p_pos_pos_h_e_alltps.size()) * 100F;
            p_pos_pos_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_pos_pos_h_e_ps.size())).toString(), (new Float(p_pos_pos_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_pos_h_e_ps)).toString().substring(0, (new Float(p_pos_pos_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_neg_neg_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2'").append(s).toString());
            p_neg_neg_h_e_alltps = ((float)samples_p_neg_neg_h_e_alltps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_alltps.size())).toString(), (new Float(p_neg_neg_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_alltps)).toString().substring(0, (new Float(p_neg_neg_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_neg_neg_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_neg_neg_h_e_p1 = ((float)samples_p_neg_neg_h_e_p1.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p1.size())).toString(), (new Float(p_neg_neg_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p1)).toString().substring(0, (new Float(p_neg_neg_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_neg_neg_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_neg_neg_h_e_p2 = ((float)samples_p_neg_neg_h_e_p2.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p2.size())).toString(), (new Float(p_neg_neg_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p2)).toString().substring(0, (new Float(p_neg_neg_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_neg_neg_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_neg_neg_h_e_p3 = ((float)samples_p_neg_neg_h_e_p3.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p3.size())).toString(), (new Float(p_neg_neg_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p3)).toString().substring(0, (new Float(p_neg_neg_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_neg_neg_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_neg_neg_h_e_p4 = ((float)samples_p_neg_neg_h_e_p4.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p4.size())).toString(), (new Float(p_neg_neg_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p4)).toString().substring(0, (new Float(p_neg_neg_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_neg_neg_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_neg_neg_h_e_p5 = ((float)samples_p_neg_neg_h_e_p5.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p5.size())).toString(), (new Float(p_neg_neg_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p5)).toString().substring(0, (new Float(p_neg_neg_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_neg_neg_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_neg_neg_h_e_p6 = ((float)samples_p_neg_neg_h_e_p6.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_p6.size())).toString(), (new Float(p_neg_neg_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_p6)).toString().substring(0, (new Float(p_neg_neg_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_neg_neg_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='2' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_neg_neg_h_e_ps = ((float)samples_p_neg_neg_h_e_ps.size() / (float)samples_p_neg_neg_h_e_alltps.size()) * 100F;
            p_neg_neg_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_neg_neg_h_e_ps.size())).toString(), (new Float(p_neg_neg_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_neg_h_e_ps)).toString().substring(0, (new Float(p_neg_neg_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_pos_neg_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3'").append(s).toString());
            p_pos_neg_h_e_alltps = ((float)samples_p_pos_neg_h_e_alltps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_alltps.size())).toString(), (new Float(p_pos_neg_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_alltps)).toString().substring(0, (new Float(p_pos_neg_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_pos_neg_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_pos_neg_h_e_p1 = ((float)samples_p_pos_neg_h_e_p1.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p1.size())).toString(), (new Float(p_pos_neg_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p1)).toString().substring(0, (new Float(p_pos_neg_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_pos_neg_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_pos_neg_h_e_p2 = ((float)samples_p_pos_neg_h_e_p2.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p2.size())).toString(), (new Float(p_pos_neg_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p2)).toString().substring(0, (new Float(p_pos_neg_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_pos_neg_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_pos_neg_h_e_p3 = ((float)samples_p_pos_neg_h_e_p3.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p3.size())).toString(), (new Float(p_pos_neg_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p3)).toString().substring(0, (new Float(p_pos_neg_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_pos_neg_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_pos_neg_h_e_p4 = ((float)samples_p_pos_neg_h_e_p4.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p4.size())).toString(), (new Float(p_pos_neg_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p4)).toString().substring(0, (new Float(p_pos_neg_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_pos_neg_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_pos_neg_h_e_p5 = ((float)samples_p_pos_neg_h_e_p5.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p5.size())).toString(), (new Float(p_pos_neg_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p5)).toString().substring(0, (new Float(p_pos_neg_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_pos_neg_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_pos_neg_h_e_p6 = ((float)samples_p_pos_neg_h_e_p6.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_p6.size())).toString(), (new Float(p_pos_neg_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_p6)).toString().substring(0, (new Float(p_pos_neg_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_pos_neg_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='3' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_pos_neg_h_e_ps = ((float)samples_p_pos_neg_h_e_ps.size() / (float)samples_p_pos_neg_h_e_alltps.size()) * 100F;
            p_pos_neg_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_pos_neg_h_e_ps.size())).toString(), (new Float(p_pos_neg_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_pos_neg_h_e_ps)).toString().substring(0, (new Float(p_pos_neg_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_neg_pos_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4'").append(s).toString());
            p_neg_pos_h_e_alltps = ((float)samples_p_neg_pos_h_e_alltps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_alltps.size())).toString(), (new Float(p_neg_pos_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_alltps)).toString().substring(0, (new Float(p_neg_pos_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_neg_pos_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_neg_pos_h_e_p1 = ((float)samples_p_neg_pos_h_e_p1.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p1.size())).toString(), (new Float(p_neg_pos_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p1)).toString().substring(0, (new Float(p_neg_pos_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_neg_pos_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_neg_pos_h_e_p2 = ((float)samples_p_neg_pos_h_e_p2.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p2.size())).toString(), (new Float(p_neg_pos_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p2)).toString().substring(0, (new Float(p_neg_pos_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_neg_pos_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_neg_pos_h_e_p3 = ((float)samples_p_neg_pos_h_e_p3.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p3.size())).toString(), (new Float(p_neg_pos_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p3)).toString().substring(0, (new Float(p_neg_pos_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_neg_pos_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_neg_pos_h_e_p4 = ((float)samples_p_neg_pos_h_e_p4.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p4.size())).toString(), (new Float(p_neg_pos_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p4)).toString().substring(0, (new Float(p_neg_pos_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_neg_pos_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_neg_pos_h_e_p5 = ((float)samples_p_neg_pos_h_e_p5.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p5.size())).toString(), (new Float(p_neg_pos_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p5)).toString().substring(0, (new Float(p_neg_pos_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_neg_pos_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_neg_pos_h_e_p6 = ((float)samples_p_neg_pos_h_e_p6.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_p6.size())).toString(), (new Float(p_neg_pos_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_p6)).toString().substring(0, (new Float(p_neg_pos_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_neg_pos_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='4' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_neg_pos_h_e_ps = ((float)samples_p_neg_pos_h_e_ps.size() / (float)samples_p_neg_pos_h_e_alltps.size()) * 100F;
            p_neg_pos_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_neg_pos_h_e_ps.size())).toString(), (new Float(p_neg_pos_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_neg_pos_h_e_ps)).toString().substring(0, (new Float(p_neg_pos_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_blank_h_e_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5'").append(s).toString());
            p_blank_h_e_alltps = ((float)samples_p_blank_h_e_alltps.size() / (float)samples_p_h_e_alltps.size()) * 100F;
            p_blank_h_e_alltps_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_alltps.size())).toString(), (new Float(p_blank_h_e_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_alltps)).toString().substring(0, (new Float(p_blank_h_e_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_blank_h_e_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_blank_h_e_p1 = ((float)samples_p_blank_h_e_p1.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p1_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p1.size())).toString(), (new Float(p_blank_h_e_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p1)).toString().substring(0, (new Float(p_blank_h_e_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_blank_h_e_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_blank_h_e_p2 = ((float)samples_p_blank_h_e_p2.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p2_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p2.size())).toString(), (new Float(p_blank_h_e_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p2)).toString().substring(0, (new Float(p_blank_h_e_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_blank_h_e_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_blank_h_e_p3 = ((float)samples_p_blank_h_e_p3.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p3_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p3.size())).toString(), (new Float(p_blank_h_e_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p3)).toString().substring(0, (new Float(p_blank_h_e_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_blank_h_e_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_blank_h_e_p4 = ((float)samples_p_blank_h_e_p4.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p4_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p4.size())).toString(), (new Float(p_blank_h_e_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p4)).toString().substring(0, (new Float(p_blank_h_e_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_blank_h_e_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_blank_h_e_p5 = ((float)samples_p_blank_h_e_p5.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p5_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p5.size())).toString(), (new Float(p_blank_h_e_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p5)).toString().substring(0, (new Float(p_blank_h_e_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_blank_h_e_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_blank_h_e_p6 = ((float)samples_p_blank_h_e_p6.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_p6_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_p6.size())).toString(), (new Float(p_blank_h_e_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_p6)).toString().substring(0, (new Float(p_blank_h_e_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_blank_h_e_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" H_E_REVIEW ='5' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_blank_h_e_ps = ((float)samples_p_blank_h_e_ps.size() / (float)samples_p_blank_h_e_alltps.size()) * 100F;
            p_blank_h_e_ps_Array = (new String[] {
                (new Integer(samples_p_blank_h_e_ps.size())).toString(), (new Float(p_blank_h_e_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_h_e_ps)).toString().substring(0, (new Float(p_blank_h_e_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinUsabilityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_usability = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Paraffin' and USABILITY is not null").append(s).toString());
            p_usability = ((float)samples_p_usability.size() / (float)paraffinSamples.size()) * 100F;
            p_usability_Array = (new String[] {
                (new Integer(samples_p_usability.size())).toString(), (new Float(p_usability)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability)).toString().substring(0, (new Float(p_usability)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_yes_usability = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_yes_usability = ((float)samples_p_yes_usability.size() / (float)samples_p_usability.size()) * 100F;
            p_yes_usability_Array = (new String[] {
                (new Integer(samples_p_yes_usability.size())).toString(), (new Float(p_yes_usability)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability)).toString().substring(0, (new Float(p_yes_usability)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_no_usability = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_no_usability = ((float)samples_p_no_usability.size() / (float)samples_p_usability.size()) * 100F;
            p_no_usability_Array = (new String[] {
                (new Integer(samples_p_no_usability.size())).toString(), (new Float(p_no_usability)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability)).toString().substring(0, (new Float(p_no_usability)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_blank_usability = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_blank_usability = ((float)samples_p_blank_usability.size() / (float)samples_p_usability.size()) * 100F;
            p_blank_usability_Array = (new String[] {
                (new Integer(samples_p_blank_usability.size())).toString(), (new Float(p_blank_usability)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability)).toString().substring(0, (new Float(p_blank_usability)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinInstitutionUsabilityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_usability_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null").append(s).toString());
            p_usability_allInstitutes = ((float)samples_p_usability_allInstitutes.size() / (float)paraffinSamples.size()) * 100F;
            p_usability_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_usability_allInstitutes.size())).toString(), (new Float(p_usability_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_allInstitutes)).toString().substring(0, (new Float(p_usability_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_usability_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =1 and USABILITY is not null").append(s).toString());
            p_usability_ucsf = ((float)samples_p_usability_ucsf.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_ucsf_Array = (new String[] {
                (new Integer(samples_p_usability_ucsf.size())).toString(), (new Float(p_usability_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_ucsf)).toString().substring(0, (new Float(p_usability_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_usability_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =2  and USABILITY is not null").append(s).toString());
            p_usability_unc = ((float)samples_p_usability_unc.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_unc_Array = (new String[] {
                (new Integer(samples_p_usability_unc.size())).toString(), (new Float(p_usability_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_unc)).toString().substring(0, (new Float(p_usability_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_usability_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =3  and USABILITY is not null").append(s).toString());
            p_usability_upenn = ((float)samples_p_usability_upenn.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_upenn_Array = (new String[] {
                (new Integer(samples_p_usability_upenn.size())).toString(), (new Float(p_usability_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_upenn)).toString().substring(0, (new Float(p_usability_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_usability_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =4  and USABILITY is not null").append(s).toString());
            p_usability_uc = ((float)samples_p_usability_uc.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_uc_Array = (new String[] {
                (new Integer(samples_p_usability_uc.size())).toString(), (new Float(p_usability_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_uc)).toString().substring(0, (new Float(p_usability_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_usability_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =5  and USABILITY is not null").append(s).toString());
            p_usability_uab = ((float)samples_p_usability_uab.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_uab_Array = (new String[] {
                (new Integer(samples_p_usability_uab.size())).toString(), (new Float(p_usability_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_uab)).toString().substring(0, (new Float(p_usability_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_usability_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =6  and USABILITY is not null").append(s).toString());
            p_usability_mskcc = ((float)samples_p_usability_mskcc.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_mskcc_Array = (new String[] {
                (new Integer(samples_p_usability_mskcc.size())).toString(), (new Float(p_usability_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_mskcc)).toString().substring(0, (new Float(p_usability_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_usability_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =7  and USABILITY is not null").append(s).toString());
            p_usability_gw = ((float)samples_p_usability_gw.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_gw_Array = (new String[] {
                (new Integer(samples_p_usability_gw.size())).toString(), (new Float(p_usability_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_gw)).toString().substring(0, (new Float(p_usability_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_usability_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =8  and USABILITY is not null").append(s).toString());
            p_usability_uw = ((float)samples_p_usability_uw.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_uw_Array = (new String[] {
                (new Integer(samples_p_usability_uw.size())).toString(), (new Float(p_usability_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_uw)).toString().substring(0, (new Float(p_usability_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_usability_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =9  and USABILITY is not null").append(s).toString());
            p_usability_ut = ((float)samples_p_usability_ut.size() / (float)samples_p_usability_allInstitutes.size()) * 100F;
            p_usability_ut_Array = (new String[] {
                (new Integer(samples_p_usability_ut.size())).toString(), (new Float(p_usability_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_ut)).toString().substring(0, (new Float(p_usability_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_yes_usability_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1'").append(s).toString());
            p_yes_usability_allInstitutes = ((float)samples_p_yes_usability_allInstitutes.size() / (float)samples_p_usability.size()) * 100F;
            p_yes_usability_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_yes_usability_allInstitutes.size())).toString(), (new Float(p_yes_usability_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_allInstitutes)).toString().substring(0, (new Float(p_yes_usability_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_yes_usability_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_yes_usability_ucsf = ((float)samples_p_yes_usability_ucsf.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_ucsf_Array = (new String[] {
                (new Integer(samples_p_yes_usability_ucsf.size())).toString(), (new Float(p_yes_usability_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_ucsf)).toString().substring(0, (new Float(p_yes_usability_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_yes_usability_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_yes_usability_unc = ((float)samples_p_yes_usability_unc.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_unc_Array = (new String[] {
                (new Integer(samples_p_yes_usability_unc.size())).toString(), (new Float(p_yes_usability_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_unc)).toString().substring(0, (new Float(p_yes_usability_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_yes_usability_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_yes_usability_upenn = ((float)samples_p_yes_usability_upenn.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_upenn_Array = (new String[] {
                (new Integer(samples_p_yes_usability_upenn.size())).toString(), (new Float(p_yes_usability_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_upenn)).toString().substring(0, (new Float(p_yes_usability_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_yes_usability_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_yes_usability_uc = ((float)samples_p_yes_usability_uc.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_uc_Array = (new String[] {
                (new Integer(samples_p_yes_usability_uc.size())).toString(), (new Float(p_yes_usability_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_uc)).toString().substring(0, (new Float(p_yes_usability_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_yes_usability_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_yes_usability_uab = ((float)samples_p_yes_usability_uab.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_uab_Array = (new String[] {
                (new Integer(samples_p_yes_usability_uab.size())).toString(), (new Float(p_yes_usability_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_uab)).toString().substring(0, (new Float(p_yes_usability_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_yes_usability_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_yes_usability_mskcc = ((float)samples_p_yes_usability_mskcc.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_mskcc_Array = (new String[] {
                (new Integer(samples_p_yes_usability_mskcc.size())).toString(), (new Float(p_yes_usability_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_mskcc)).toString().substring(0, (new Float(p_yes_usability_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_yes_usability_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_yes_usability_gw = ((float)samples_p_yes_usability_gw.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_gw_Array = (new String[] {
                (new Integer(samples_p_yes_usability_gw.size())).toString(), (new Float(p_yes_usability_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_gw)).toString().substring(0, (new Float(p_yes_usability_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_yes_usability_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_yes_usability_uw = ((float)samples_p_yes_usability_uw.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_uw_Array = (new String[] {
                (new Integer(samples_p_yes_usability_uw.size())).toString(), (new Float(p_yes_usability_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_uw)).toString().substring(0, (new Float(p_yes_usability_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_yes_usability_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_yes_usability_ut = ((float)samples_p_yes_usability_ut.size() / (float)samples_p_yes_usability_allInstitutes.size()) * 100F;
            p_yes_usability_ut_Array = (new String[] {
                (new Integer(samples_p_yes_usability_ut.size())).toString(), (new Float(p_yes_usability_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_ut)).toString().substring(0, (new Float(p_yes_usability_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_no_usability_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2'").append(s).toString());
            p_no_usability_allInstitutes = ((float)samples_p_no_usability_allInstitutes.size() / (float)samples_p_usability.size()) * 100F;
            p_no_usability_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_no_usability_allInstitutes.size())).toString(), (new Float(p_no_usability_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_allInstitutes)).toString().substring(0, (new Float(p_no_usability_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_no_usability_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_no_usability_ucsf = ((float)samples_p_no_usability_ucsf.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_ucsf_Array = (new String[] {
                (new Integer(samples_p_no_usability_ucsf.size())).toString(), (new Float(p_no_usability_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_ucsf)).toString().substring(0, (new Float(p_no_usability_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_no_usability_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_no_usability_unc = ((float)samples_p_no_usability_unc.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_unc_Array = (new String[] {
                (new Integer(samples_p_no_usability_unc.size())).toString(), (new Float(p_no_usability_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_unc)).toString().substring(0, (new Float(p_no_usability_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_no_usability_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_no_usability_upenn = ((float)samples_p_no_usability_upenn.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_upenn_Array = (new String[] {
                (new Integer(samples_p_no_usability_upenn.size())).toString(), (new Float(p_no_usability_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_upenn)).toString().substring(0, (new Float(p_no_usability_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_no_usability_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_no_usability_uc = ((float)samples_p_no_usability_uc.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_uc_Array = (new String[] {
                (new Integer(samples_p_no_usability_uc.size())).toString(), (new Float(p_no_usability_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_uc)).toString().substring(0, (new Float(p_no_usability_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_no_usability_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_no_usability_uab = ((float)samples_p_no_usability_uab.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_uab_Array = (new String[] {
                (new Integer(samples_p_no_usability_uab.size())).toString(), (new Float(p_no_usability_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_uab)).toString().substring(0, (new Float(p_no_usability_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_no_usability_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_no_usability_mskcc = ((float)samples_p_no_usability_mskcc.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_mskcc_Array = (new String[] {
                (new Integer(samples_p_no_usability_mskcc.size())).toString(), (new Float(p_no_usability_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_mskcc)).toString().substring(0, (new Float(p_no_usability_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_no_usability_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_no_usability_gw = ((float)samples_p_no_usability_gw.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_gw_Array = (new String[] {
                (new Integer(samples_p_no_usability_gw.size())).toString(), (new Float(p_no_usability_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_gw)).toString().substring(0, (new Float(p_no_usability_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_no_usability_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_no_usability_uw = ((float)samples_p_no_usability_uw.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_uw_Array = (new String[] {
                (new Integer(samples_p_no_usability_uw.size())).toString(), (new Float(p_no_usability_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_uw)).toString().substring(0, (new Float(p_no_usability_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_no_usability_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_no_usability_ut = ((float)samples_p_no_usability_ut.size() / (float)samples_p_no_usability_allInstitutes.size()) * 100F;
            p_no_usability_ut_Array = (new String[] {
                (new Integer(samples_p_no_usability_ut.size())).toString(), (new Float(p_no_usability_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_ut)).toString().substring(0, (new Float(p_no_usability_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_blank_usability_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3'").append(s).toString());
            p_blank_usability_allInstitutes = ((float)samples_p_blank_usability_allInstitutes.size() / (float)samples_p_usability.size()) * 100F;
            p_blank_usability_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_blank_usability_allInstitutes.size())).toString(), (new Float(p_blank_usability_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_allInstitutes)).toString().substring(0, (new Float(p_blank_usability_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_blank_usability_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_blank_usability_ucsf = ((float)samples_p_blank_usability_ucsf.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_ucsf_Array = (new String[] {
                (new Integer(samples_p_blank_usability_ucsf.size())).toString(), (new Float(p_blank_usability_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_ucsf)).toString().substring(0, (new Float(p_blank_usability_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_blank_usability_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_blank_usability_unc = ((float)samples_p_blank_usability_unc.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_unc_Array = (new String[] {
                (new Integer(samples_p_blank_usability_unc.size())).toString(), (new Float(p_blank_usability_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_unc)).toString().substring(0, (new Float(p_blank_usability_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_blank_usability_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_blank_usability_upenn = ((float)samples_p_blank_usability_upenn.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_upenn_Array = (new String[] {
                (new Integer(samples_p_blank_usability_upenn.size())).toString(), (new Float(p_blank_usability_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_upenn)).toString().substring(0, (new Float(p_blank_usability_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_blank_usability_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_blank_usability_uc = ((float)samples_p_blank_usability_uc.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_uc_Array = (new String[] {
                (new Integer(samples_p_blank_usability_uc.size())).toString(), (new Float(p_blank_usability_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_uc)).toString().substring(0, (new Float(p_blank_usability_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_blank_usability_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_blank_usability_uab = ((float)samples_p_blank_usability_uab.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_uab_Array = (new String[] {
                (new Integer(samples_p_blank_usability_uab.size())).toString(), (new Float(p_blank_usability_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_uab)).toString().substring(0, (new Float(p_blank_usability_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_blank_usability_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_blank_usability_mskcc = ((float)samples_p_blank_usability_mskcc.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_mskcc_Array = (new String[] {
                (new Integer(samples_p_blank_usability_mskcc.size())).toString(), (new Float(p_blank_usability_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_mskcc)).toString().substring(0, (new Float(p_blank_usability_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_blank_usability_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_blank_usability_gw = ((float)samples_p_blank_usability_gw.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_gw_Array = (new String[] {
                (new Integer(samples_p_blank_usability_gw.size())).toString(), (new Float(p_blank_usability_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_gw)).toString().substring(0, (new Float(p_blank_usability_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_blank_usability_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_blank_usability_uw = ((float)samples_p_blank_usability_uw.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_uw_Array = (new String[] {
                (new Integer(samples_p_blank_usability_uw.size())).toString(), (new Float(p_blank_usability_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_uw)).toString().substring(0, (new Float(p_blank_usability_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_blank_usability_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_blank_usability_ut = ((float)samples_p_blank_usability_ut.size() / (float)samples_p_blank_usability_allInstitutes.size()) * 100F;
            p_blank_usability_ut_Array = (new String[] {
                (new Integer(samples_p_blank_usability_ut.size())).toString(), (new Float(p_blank_usability_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_ut)).toString().substring(0, (new Float(p_blank_usability_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinTimePtUsabilityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_usability_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null AND TIMEPOINT_NAME IN('P1','P2','P3','P4','P5','P6','PS')").append(s).toString());
            p_usability_alltps = ((float)samples_p_usability_alltps.size() / (float)paraffinSamples.size()) * 100F;
            p_usability_alltps_Array = (new String[] {
                (new Integer(samples_p_usability_alltps.size())).toString(), (new Float(p_usability_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_alltps)).toString().substring(0, (new Float(p_usability_p1)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_usability_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null AND TIMEPOINT_NAME ='P1'").append(s).toString());
            p_usability_p1 = ((float)samples_p_usability_p1.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p1_Array = (new String[] {
                (new Integer(samples_p_usability_p1.size())).toString(), (new Float(p_usability_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p1)).toString().substring(0, (new Float(p_usability_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_usability_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null AND TIMEPOINT_NAME ='P2'").append(s).toString());
            p_usability_p2 = ((float)samples_p_usability_p2.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p2_Array = (new String[] {
                (new Integer(samples_p_usability_p2.size())).toString(), (new Float(p_usability_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p2)).toString().substring(0, (new Float(p_usability_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_usability_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null AND TIMEPOINT_NAME ='P3'").append(s).toString());
            p_usability_p3 = ((float)samples_p_usability_p3.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p3_Array = (new String[] {
                (new Integer(samples_p_usability_p3.size())).toString(), (new Float(p_usability_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p3)).toString().substring(0, (new Float(p_usability_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_usability_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null AND TIMEPOINT_NAME ='P4'").append(s).toString());
            p_usability_p4 = ((float)samples_p_usability_p4.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p4_Array = (new String[] {
                (new Integer(samples_p_usability_p4.size())).toString(), (new Float(p_usability_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p4)).toString().substring(0, (new Float(p_usability_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_usability_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null  AND TIMEPOINT_NAME ='P5'").append(s).toString());
            p_usability_p5 = ((float)samples_p_usability_p5.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p5_Array = (new String[] {
                (new Integer(samples_p_usability_p5.size())).toString(), (new Float(p_usability_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p5)).toString().substring(0, (new Float(p_usability_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_usability_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null  AND  TIMEPOINT_NAME ='P6'").append(s).toString());
            p_usability_p6 = ((float)samples_p_usability_p6.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_p6_Array = (new String[] {
                (new Integer(samples_p_usability_p6.size())).toString(), (new Float(p_usability_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_p6)).toString().substring(0, (new Float(p_usability_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_usability_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY is not null  AND TIMEPOINT_NAME ='PS'").append(s).toString());
            p_usability_ps = ((float)samples_p_usability_ps.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_usability_ps_Array = (new String[] {
                (new Integer(samples_p_usability_ps.size())).toString(), (new Float(p_usability_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_usability_ps)).toString().substring(0, (new Float(p_usability_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_yes_usability_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1'").append(s).toString());
            p_yes_usability_alltps = ((float)samples_p_yes_usability_alltps.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_yes_usability_alltps_Array = (new String[] {
                (new Integer(samples_p_yes_usability_alltps.size())).toString(), (new Float(p_yes_usability_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_alltps)).toString().substring(0, (new Float(p_yes_usability_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_yes_usability_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_yes_usability_p1 = ((float)samples_p_yes_usability_p1.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p1_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p1.size())).toString(), (new Float(p_yes_usability_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p1)).toString().substring(0, (new Float(p_yes_usability_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_yes_usability_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_yes_usability_p2 = ((float)samples_p_yes_usability_p2.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p2_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p2.size())).toString(), (new Float(p_yes_usability_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p2)).toString().substring(0, (new Float(p_yes_usability_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_yes_usability_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_yes_usability_p3 = ((float)samples_p_yes_usability_p3.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p3_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p3.size())).toString(), (new Float(p_yes_usability_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p3)).toString().substring(0, (new Float(p_yes_usability_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_yes_usability_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_yes_usability_p4 = ((float)samples_p_yes_usability_p4.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p4_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p4.size())).toString(), (new Float(p_yes_usability_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p4)).toString().substring(0, (new Float(p_yes_usability_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_yes_usability_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_yes_usability_p5 = ((float)samples_p_yes_usability_p5.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p5_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p5.size())).toString(), (new Float(p_yes_usability_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p5)).toString().substring(0, (new Float(p_yes_usability_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_yes_usability_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_yes_usability_p6 = ((float)samples_p_yes_usability_p6.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_p6_Array = (new String[] {
                (new Integer(samples_p_yes_usability_p6.size())).toString(), (new Float(p_yes_usability_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_p6)).toString().substring(0, (new Float(p_yes_usability_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_yes_usability_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='1' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_yes_usability_ps = ((float)samples_p_yes_usability_ps.size() / (float)samples_p_yes_usability_alltps.size()) * 100F;
            p_yes_usability_ps_Array = (new String[] {
                (new Integer(samples_p_yes_usability_ps.size())).toString(), (new Float(p_yes_usability_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_usability_ps)).toString().substring(0, (new Float(p_yes_usability_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_no_usability_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2'").append(s).toString());
            p_no_usability_alltps = ((float)samples_p_no_usability_alltps.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_no_usability_alltps_Array = (new String[] {
                (new Integer(samples_p_no_usability_alltps.size())).toString(), (new Float(p_no_usability_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_alltps)).toString().substring(0, (new Float(p_no_usability_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_no_usability_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_no_usability_p1 = ((float)samples_p_no_usability_p1.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p1_Array = (new String[] {
                (new Integer(samples_p_no_usability_p1.size())).toString(), (new Float(p_no_usability_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p1)).toString().substring(0, (new Float(p_no_usability_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_no_usability_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_no_usability_p2 = ((float)samples_p_no_usability_p2.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p2_Array = (new String[] {
                (new Integer(samples_p_no_usability_p2.size())).toString(), (new Float(p_no_usability_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p2)).toString().substring(0, (new Float(p_no_usability_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_no_usability_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_no_usability_p3 = ((float)samples_p_no_usability_p3.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p3_Array = (new String[] {
                (new Integer(samples_p_no_usability_p3.size())).toString(), (new Float(p_no_usability_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p3)).toString().substring(0, (new Float(p_no_usability_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_no_usability_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_no_usability_p4 = ((float)samples_p_no_usability_p4.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p4_Array = (new String[] {
                (new Integer(samples_p_no_usability_p4.size())).toString(), (new Float(p_no_usability_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p4)).toString().substring(0, (new Float(p_no_usability_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_no_usability_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_no_usability_p5 = ((float)samples_p_no_usability_p5.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p5_Array = (new String[] {
                (new Integer(samples_p_no_usability_p5.size())).toString(), (new Float(p_no_usability_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p5)).toString().substring(0, (new Float(p_no_usability_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_no_usability_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_no_usability_p6 = ((float)samples_p_no_usability_p6.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_p6_Array = (new String[] {
                (new Integer(samples_p_no_usability_p6.size())).toString(), (new Float(p_no_usability_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_p6)).toString().substring(0, (new Float(p_no_usability_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_no_usability_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='2' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_no_usability_ps = ((float)samples_p_no_usability_ps.size() / (float)samples_p_no_usability_alltps.size()) * 100F;
            p_no_usability_ps_Array = (new String[] {
                (new Integer(samples_p_no_usability_ps.size())).toString(), (new Float(p_no_usability_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_usability_ps)).toString().substring(0, (new Float(p_no_usability_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_blank_usability_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3'").append(s).toString());
            p_blank_usability_alltps = ((float)samples_p_blank_usability_alltps.size() / (float)samples_p_usability_alltps.size()) * 100F;
            p_blank_usability_alltps_Array = (new String[] {
                (new Integer(samples_p_blank_usability_alltps.size())).toString(), (new Float(p_blank_usability_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_alltps)).toString().substring(0, (new Float(p_blank_usability_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_blank_usability_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_blank_usability_p1 = ((float)samples_p_blank_usability_p1.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p1_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p1.size())).toString(), (new Float(p_blank_usability_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p1)).toString().substring(0, (new Float(p_blank_usability_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_blank_usability_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_blank_usability_p2 = ((float)samples_p_blank_usability_p2.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p2_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p2.size())).toString(), (new Float(p_blank_usability_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p2)).toString().substring(0, (new Float(p_blank_usability_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_blank_usability_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_blank_usability_p3 = ((float)samples_p_blank_usability_p3.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p3_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p3.size())).toString(), (new Float(p_blank_usability_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p3)).toString().substring(0, (new Float(p_blank_usability_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_blank_usability_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_blank_usability_p4 = ((float)samples_p_blank_usability_p4.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p4_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p4.size())).toString(), (new Float(p_blank_usability_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p4)).toString().substring(0, (new Float(p_blank_usability_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_blank_usability_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_blank_usability_p5 = ((float)samples_p_blank_usability_p5.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p5_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p5.size())).toString(), (new Float(p_blank_usability_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p5)).toString().substring(0, (new Float(p_blank_usability_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_blank_usability_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_blank_usability_p6 = ((float)samples_p_blank_usability_p6.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_p6_Array = (new String[] {
                (new Integer(samples_p_blank_usability_p6.size())).toString(), (new Float(p_blank_usability_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_p6)).toString().substring(0, (new Float(p_blank_usability_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_blank_usability_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" USABILITY ='3' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_blank_usability_ps = ((float)samples_p_blank_usability_ps.size() / (float)samples_p_blank_usability_alltps.size()) * 100F;
            p_blank_usability_ps_Array = (new String[] {
                (new Integer(samples_p_blank_usability_ps.size())).toString(), (new Float(p_blank_usability_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_usability_ps)).toString().substring(0, (new Float(p_blank_usability_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinTouchPrepsInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_touchPrepRecvd = qi_sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Paraffin' and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd = ((float)samples_p_touchPrepRecvd.size() / (float)paraffinSamples.size()) * 100F;
            p_touchPrepRecvd_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd.size())).toString(), (new Float(p_touchPrepRecvd)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd)).toString().substring(0, (new Float(p_touchPrepRecvd)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_yes_touchPrepRecvd = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_yes_touchPrepRecvd = ((float)samples_p_yes_touchPrepRecvd.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_yes_touchPrepRecvd_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd.size())).toString(), (new Float(p_yes_touchPrepRecvd)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd)).toString().substring(0, (new Float(p_yes_touchPrepRecvd)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_no_touchPrepRecvd = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_no_touchPrepRecvd = ((float)samples_p_no_touchPrepRecvd.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_no_touchPrepRecvd_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd.size())).toString(), (new Float(p_no_touchPrepRecvd)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd)).toString().substring(0, (new Float(p_no_touchPrepRecvd)).toString().indexOf("."))).append("%").toString()
            });
            samples_p_blank_touchPrepRecvd = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND CORE_TYPE='Paraffin'").append(s).toString());
            p_blank_touchPrepRecvd = ((float)samples_p_blank_touchPrepRecvd.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_blank_touchPrepRecvd_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd.size())).toString(), (new Float(p_blank_touchPrepRecvd)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd)).toString().substring(0, (new Float(p_blank_touchPrepRecvd)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinInstitutionTouchPrepsInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_touchPrepRecvd_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_allInstitutes = ((float)samples_p_touchPrepRecvd_allInstitutes.size() / (float)paraffinSamples.size()) * 100F;
            p_touchPrepRecvd_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_allInstitutes.size())).toString(), (new Float(p_touchPrepRecvd_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_allInstitutes)).toString().substring(0, (new Float(p_touchPrepRecvd_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_touchPrepRecvd_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =1 and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_ucsf = ((float)samples_p_touchPrepRecvd_ucsf.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_ucsf_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_ucsf.size())).toString(), (new Float(p_touchPrepRecvd_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_ucsf)).toString().substring(0, (new Float(p_touchPrepRecvd_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_touchPrepRecvd_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =2  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_unc = ((float)samples_p_touchPrepRecvd_unc.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_unc_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_unc.size())).toString(), (new Float(p_touchPrepRecvd_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_unc)).toString().substring(0, (new Float(p_touchPrepRecvd_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_touchPrepRecvd_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =3  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_upenn = ((float)samples_p_touchPrepRecvd_upenn.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_upenn_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_upenn.size())).toString(), (new Float(p_touchPrepRecvd_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_upenn)).toString().substring(0, (new Float(p_touchPrepRecvd_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_touchPrepRecvd_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =4  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_uc = ((float)samples_p_touchPrepRecvd_uc.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_uc_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_uc.size())).toString(), (new Float(p_touchPrepRecvd_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_uc)).toString().substring(0, (new Float(p_touchPrepRecvd_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_touchPrepRecvd_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =5  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_uab = ((float)samples_p_touchPrepRecvd_uab.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_uab_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_uab.size())).toString(), (new Float(p_touchPrepRecvd_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_uab)).toString().substring(0, (new Float(p_touchPrepRecvd_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_touchPrepRecvd_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =6  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_mskcc = ((float)samples_p_touchPrepRecvd_mskcc.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_mskcc_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_mskcc.size())).toString(), (new Float(p_touchPrepRecvd_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_mskcc)).toString().substring(0, (new Float(p_touchPrepRecvd_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_touchPrepRecvd_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =7  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_gw = ((float)samples_p_touchPrepRecvd_gw.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_gw_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_gw.size())).toString(), (new Float(p_touchPrepRecvd_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_gw)).toString().substring(0, (new Float(p_touchPrepRecvd_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_touchPrepRecvd_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =8  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_uw = ((float)samples_p_touchPrepRecvd_uw.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_uw_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_uw.size())).toString(), (new Float(p_touchPrepRecvd_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_uw)).toString().substring(0, (new Float(p_touchPrepRecvd_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_touchPrepRecvd_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" SAMPLE_GENERATING_INSTITUTE_ID =9  and TOUCHPREP_RECVD is not null").append(s).toString());
            p_touchPrepRecvd_ut = ((float)samples_p_touchPrepRecvd_ut.size() / (float)samples_p_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_touchPrepRecvd_ut_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_ut.size())).toString(), (new Float(p_touchPrepRecvd_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_ut)).toString().substring(0, (new Float(p_touchPrepRecvd_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_yes_touchPrepRecvd_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1'").append(s).toString());
            p_yes_touchPrepRecvd_allInstitutes = ((float)samples_p_yes_touchPrepRecvd_allInstitutes.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_yes_touchPrepRecvd_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_allInstitutes.size())).toString(), (new Float(p_yes_touchPrepRecvd_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_allInstitutes)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_yes_touchPrepRecvd_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_yes_touchPrepRecvd_ucsf = ((float)samples_p_yes_touchPrepRecvd_ucsf.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_ucsf_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_ucsf.size())).toString(), (new Float(p_yes_touchPrepRecvd_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_ucsf)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_yes_touchPrepRecvd_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_yes_touchPrepRecvd_unc = ((float)samples_p_yes_touchPrepRecvd_unc.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_unc_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_unc.size())).toString(), (new Float(p_yes_touchPrepRecvd_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_unc)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_yes_touchPrepRecvd_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_yes_touchPrepRecvd_upenn = ((float)samples_p_yes_touchPrepRecvd_upenn.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_upenn_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_upenn.size())).toString(), (new Float(p_yes_touchPrepRecvd_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_upenn)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_yes_touchPrepRecvd_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_yes_touchPrepRecvd_uc = ((float)samples_p_yes_touchPrepRecvd_uc.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_uc_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_uc.size())).toString(), (new Float(p_yes_touchPrepRecvd_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_uc)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_yes_touchPrepRecvd_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_yes_touchPrepRecvd_uab = ((float)samples_p_yes_touchPrepRecvd_uab.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_uab_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_uab.size())).toString(), (new Float(p_yes_touchPrepRecvd_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_uab)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_yes_touchPrepRecvd_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_yes_touchPrepRecvd_mskcc = ((float)samples_p_yes_touchPrepRecvd_mskcc.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_mskcc_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_mskcc.size())).toString(), (new Float(p_yes_touchPrepRecvd_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_mskcc)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_yes_touchPrepRecvd_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_yes_touchPrepRecvd_gw = ((float)samples_p_yes_touchPrepRecvd_gw.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_gw_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_gw.size())).toString(), (new Float(p_yes_touchPrepRecvd_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_gw)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_yes_touchPrepRecvd_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_yes_touchPrepRecvd_uw = ((float)samples_p_yes_touchPrepRecvd_uw.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_uw_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_uw.size())).toString(), (new Float(p_yes_touchPrepRecvd_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_uw)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_yes_touchPrepRecvd_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_yes_touchPrepRecvd_ut = ((float)samples_p_yes_touchPrepRecvd_ut.size() / (float)samples_p_yes_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_yes_touchPrepRecvd_ut_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_ut.size())).toString(), (new Float(p_yes_touchPrepRecvd_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_ut)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_no_touchPrepRecvd_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2'").append(s).toString());
            p_no_touchPrepRecvd_allInstitutes = ((float)samples_p_no_touchPrepRecvd_allInstitutes.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_no_touchPrepRecvd_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_allInstitutes.size())).toString(), (new Float(p_no_touchPrepRecvd_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_allInstitutes)).toString().substring(0, (new Float(p_no_touchPrepRecvd_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_no_touchPrepRecvd_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_no_touchPrepRecvd_ucsf = ((float)samples_p_no_touchPrepRecvd_ucsf.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_ucsf_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_ucsf.size())).toString(), (new Float(p_no_touchPrepRecvd_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_ucsf)).toString().substring(0, (new Float(p_no_touchPrepRecvd_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_no_touchPrepRecvd_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_no_touchPrepRecvd_unc = ((float)samples_p_no_touchPrepRecvd_unc.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_unc_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_unc.size())).toString(), (new Float(p_no_touchPrepRecvd_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_unc)).toString().substring(0, (new Float(p_no_touchPrepRecvd_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_no_touchPrepRecvd_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_no_touchPrepRecvd_upenn = ((float)samples_p_no_touchPrepRecvd_upenn.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_upenn_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_upenn.size())).toString(), (new Float(p_no_touchPrepRecvd_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_upenn)).toString().substring(0, (new Float(p_no_touchPrepRecvd_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_no_touchPrepRecvd_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_no_touchPrepRecvd_uc = ((float)samples_p_no_touchPrepRecvd_uc.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_uc_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_uc.size())).toString(), (new Float(p_no_touchPrepRecvd_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_uc)).toString().substring(0, (new Float(f_touch_prep)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_no_touchPrepRecvd_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_no_touchPrepRecvd_uab = ((float)samples_p_no_touchPrepRecvd_uab.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_uab_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_uab.size())).toString(), (new Float(p_no_touchPrepRecvd_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_uab)).toString().substring(0, (new Float(p_no_touchPrepRecvd_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_no_touchPrepRecvd_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_no_touchPrepRecvd_mskcc = ((float)samples_p_no_touchPrepRecvd_mskcc.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_mskcc_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_mskcc.size())).toString(), (new Float(p_no_touchPrepRecvd_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_mskcc)).toString().substring(0, (new Float(p_no_touchPrepRecvd_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_no_touchPrepRecvd_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_no_touchPrepRecvd_gw = ((float)samples_p_no_touchPrepRecvd_gw.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_gw_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_gw.size())).toString(), (new Float(p_no_touchPrepRecvd_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_gw)).toString().substring(0, (new Float(p_no_touchPrepRecvd_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_no_touchPrepRecvd_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_no_touchPrepRecvd_uw = ((float)samples_p_no_touchPrepRecvd_uw.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_uw_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_uw.size())).toString(), (new Float(p_no_touchPrepRecvd_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_uw)).toString().substring(0, (new Float(p_no_touchPrepRecvd_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_no_touchPrepRecvd_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_no_touchPrepRecvd_ut = ((float)samples_p_no_touchPrepRecvd_ut.size() / (float)samples_p_no_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_no_touchPrepRecvd_ut_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_ut.size())).toString(), (new Float(p_no_touchPrepRecvd_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_ut)).toString().substring(0, (new Float(p_no_touchPrepRecvd_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
            samples_p_blank_touchPrepRecvd_allInstitutes = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3'").append(s).toString());
            p_blank_touchPrepRecvd_allInstitutes = ((float)samples_p_blank_touchPrepRecvd_allInstitutes.size() / (float)samples_p_touchPrepRecvd.size()) * 100F;
            p_blank_touchPrepRecvd_allInstitutes_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_allInstitutes.size())).toString(), (new Float(p_blank_touchPrepRecvd_allInstitutes)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_allInstitutes)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_allInstitutes)).toString().indexOf("."))).append("%(All Institutions)").toString()
            });
            samples_p_blank_touchPrepRecvd_ucsf = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=1").append(s).toString());
            p_blank_touchPrepRecvd_ucsf = ((float)samples_p_blank_touchPrepRecvd_ucsf.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_ucsf_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_ucsf.size())).toString(), (new Float(p_blank_touchPrepRecvd_ucsf)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_ucsf)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_ucsf)).toString().indexOf("."))).append("%(UCSF)").toString()
            });
            samples_p_blank_touchPrepRecvd_unc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=2").append(s).toString());
            p_blank_touchPrepRecvd_unc = ((float)samples_p_blank_touchPrepRecvd_unc.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_unc_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_unc.size())).toString(), (new Float(p_blank_touchPrepRecvd_unc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_unc)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_unc)).toString().indexOf("."))).append("%(UNC)").toString()
            });
            samples_p_blank_touchPrepRecvd_upenn = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=3").append(s).toString());
            p_blank_touchPrepRecvd_upenn = ((float)samples_p_blank_touchPrepRecvd_upenn.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_upenn_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_upenn.size())).toString(), (new Float(p_blank_touchPrepRecvd_upenn)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_upenn)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_upenn)).toString().indexOf("."))).append("%(UPENN)").toString()
            });
            samples_p_blank_touchPrepRecvd_uc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=4").append(s).toString());
            p_blank_touchPrepRecvd_uc = ((float)samples_p_blank_touchPrepRecvd_uc.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_uc_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_uc.size())).toString(), (new Float(p_blank_touchPrepRecvd_uc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_uc)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_uc)).toString().indexOf("."))).append("%(Univ. of Chicago)").toString()
            });
            samples_p_blank_touchPrepRecvd_uab = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=5").append(s).toString());
            p_blank_touchPrepRecvd_uab = ((float)samples_p_blank_touchPrepRecvd_uab.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_uab_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_uab.size())).toString(), (new Float(p_blank_touchPrepRecvd_uab)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_uab)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_uab)).toString().indexOf("."))).append("%(UAB)").toString()
            });
            samples_p_blank_touchPrepRecvd_mskcc = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=6").append(s).toString());
            p_blank_touchPrepRecvd_mskcc = ((float)samples_p_blank_touchPrepRecvd_mskcc.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_mskcc_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_mskcc.size())).toString(), (new Float(p_blank_touchPrepRecvd_mskcc)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_mskcc)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_mskcc)).toString().indexOf("."))).append("%(MSKCC)").toString()
            });
            samples_p_blank_touchPrepRecvd_gw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=7").append(s).toString());
            p_blank_touchPrepRecvd_gw = ((float)samples_p_blank_touchPrepRecvd_gw.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_gw_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_gw.size())).toString(), (new Float(p_blank_touchPrepRecvd_gw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_gw)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_gw)).toString().indexOf("."))).append("%(GTWN)").toString()
            });
            samples_p_blank_touchPrepRecvd_uw = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=8").append(s).toString());
            p_blank_touchPrepRecvd_uw = ((float)samples_p_blank_touchPrepRecvd_uw.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_uw_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_uw.size())).toString(), (new Float(p_blank_touchPrepRecvd_uw)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_uw)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_uw)).toString().indexOf("."))).append("%(Univ. of Wash.)").toString()
            });
            samples_p_blank_touchPrepRecvd_ut = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND SAMPLE_GENERATING_INSTITUTE_ID=9").append(s).toString());
            p_blank_touchPrepRecvd_ut = ((float)samples_p_blank_touchPrepRecvd_ut.size() / (float)samples_p_blank_touchPrepRecvd_allInstitutes.size()) * 100F;
            p_blank_touchPrepRecvd_ut_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_ut.size())).toString(), (new Float(p_blank_touchPrepRecvd_ut)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_ut)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_ut)).toString().indexOf("."))).append("%(Univ. of Texas)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getParaffinTimePtTouchPrepsInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_p_touchPrepRecvd_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null AND TIMEPOINT_NAME IN('P1','P2','P3','P4','P5','P6','PS')").append(s).toString());
            p_touchPrepRecvd_alltps = ((float)samples_p_touchPrepRecvd_alltps.size() / (float)paraffinSamples.size()) * 100F;
            p_touchPrepRecvd_alltps_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_alltps.size())).toString(), (new Float(p_touchPrepRecvd_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_alltps)).toString().substring(0, (new Float(p_touchPrepRecvd_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_touchPrepRecvd_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null AND TIMEPOINT_NAME ='P1'").append(s).toString());
            p_touchPrepRecvd_p1 = ((float)samples_p_touchPrepRecvd_p1.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p1_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p1.size())).toString(), (new Float(p_touchPrepRecvd_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p1)).toString().substring(0, (new Float(p_touchPrepRecvd_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_touchPrepRecvd_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null AND TIMEPOINT_NAME ='P2'").append(s).toString());
            p_touchPrepRecvd_p2 = ((float)samples_p_touchPrepRecvd_p2.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p2_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p2.size())).toString(), (new Float(p_touchPrepRecvd_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p2)).toString().substring(0, (new Float(p_touchPrepRecvd_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_touchPrepRecvd_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null AND TIMEPOINT_NAME ='P3'").append(s).toString());
            p_touchPrepRecvd_p3 = ((float)samples_p_touchPrepRecvd_p3.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p3_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p3.size())).toString(), (new Float(p_touchPrepRecvd_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p3)).toString().substring(0, (new Float(p_touchPrepRecvd_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_touchPrepRecvd_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null AND TIMEPOINT_NAME ='P4'").append(s).toString());
            p_touchPrepRecvd_p4 = ((float)samples_p_touchPrepRecvd_p4.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p4_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p4.size())).toString(), (new Float(p_touchPrepRecvd_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p4)).toString().substring(0, (new Float(p_touchPrepRecvd_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_touchPrepRecvd_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null  AND TIMEPOINT_NAME ='P5'").append(s).toString());
            p_touchPrepRecvd_p5 = ((float)samples_p_touchPrepRecvd_p5.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p5_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p5.size())).toString(), (new Float(p_touchPrepRecvd_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p5)).toString().substring(0, (new Float(p_touchPrepRecvd_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_touchPrepRecvd_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null  AND  TIMEPOINT_NAME ='P6'").append(s).toString());
            p_touchPrepRecvd_p6 = ((float)samples_p_touchPrepRecvd_p6.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_p6_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_p6.size())).toString(), (new Float(p_touchPrepRecvd_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_p6)).toString().substring(0, (new Float(p_touchPrepRecvd_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_touchPrepRecvd_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD is not null  AND TIMEPOINT_NAME ='PS'").append(s).toString());
            p_touchPrepRecvd_ps = ((float)samples_p_touchPrepRecvd_ps.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_touchPrepRecvd_ps_Array = (new String[] {
                (new Integer(samples_p_touchPrepRecvd_ps.size())).toString(), (new Float(p_touchPrepRecvd_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_touchPrepRecvd_ps)).toString().substring(0, (new Float(p_touchPrepRecvd_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_yes_touchPrepRecvd_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1'").append(s).toString());
            p_yes_touchPrepRecvd_alltps = ((float)samples_p_yes_touchPrepRecvd_alltps.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_alltps_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_alltps.size())).toString(), (new Float(p_yes_touchPrepRecvd_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_alltps)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_yes_touchPrepRecvd_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_yes_touchPrepRecvd_p1 = ((float)samples_p_yes_touchPrepRecvd_p1.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p1_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p1.size())).toString(), (new Float(p_yes_touchPrepRecvd_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p1)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_yes_touchPrepRecvd_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_yes_touchPrepRecvd_p2 = ((float)samples_p_yes_touchPrepRecvd_p2.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p2_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p2.size())).toString(), (new Float(p_yes_touchPrepRecvd_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p2)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_yes_touchPrepRecvd_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_yes_touchPrepRecvd_p3 = ((float)samples_p_yes_touchPrepRecvd_p3.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p3_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p3.size())).toString(), (new Float(p_yes_touchPrepRecvd_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p3)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_yes_touchPrepRecvd_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_yes_touchPrepRecvd_p4 = ((float)samples_p_yes_touchPrepRecvd_p4.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p4_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p4.size())).toString(), (new Float(p_yes_touchPrepRecvd_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p4)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_yes_touchPrepRecvd_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_yes_touchPrepRecvd_p5 = ((float)samples_p_yes_touchPrepRecvd_p5.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p5_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p5.size())).toString(), (new Float(p_yes_touchPrepRecvd_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p5)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_yes_touchPrepRecvd_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_yes_touchPrepRecvd_p6 = ((float)samples_p_yes_touchPrepRecvd_p6.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_p6_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_p6.size())).toString(), (new Float(p_yes_touchPrepRecvd_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_p6)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_yes_touchPrepRecvd_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='1' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_yes_touchPrepRecvd_ps = ((float)samples_p_yes_touchPrepRecvd_ps.size() / (float)samples_p_yes_touchPrepRecvd_alltps.size()) * 100F;
            p_yes_touchPrepRecvd_ps_Array = (new String[] {
                (new Integer(samples_p_yes_touchPrepRecvd_ps.size())).toString(), (new Float(p_yes_touchPrepRecvd_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_yes_touchPrepRecvd_ps)).toString().substring(0, (new Float(p_yes_touchPrepRecvd_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_no_touchPrepRecvd_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2'").append(s).toString());
            p_no_touchPrepRecvd_alltps = ((float)samples_p_no_touchPrepRecvd_alltps.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_alltps_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_alltps.size())).toString(), (new Float(p_no_touchPrepRecvd_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_alltps)).toString().substring(0, (new Float(p_no_touchPrepRecvd_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_no_touchPrepRecvd_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_no_touchPrepRecvd_p1 = ((float)samples_p_no_touchPrepRecvd_p1.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p1_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p1.size())).toString(), (new Float(p_no_touchPrepRecvd_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p1)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_no_touchPrepRecvd_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_no_touchPrepRecvd_p2 = ((float)samples_p_no_touchPrepRecvd_p2.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p2_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p2.size())).toString(), (new Float(p_no_touchPrepRecvd_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p2)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_no_touchPrepRecvd_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_no_touchPrepRecvd_p3 = ((float)samples_p_no_touchPrepRecvd_p3.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p3_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p3.size())).toString(), (new Float(p_no_touchPrepRecvd_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p3)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            samples_p_no_touchPrepRecvd_p4 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_no_touchPrepRecvd_p4 = ((float)samples_p_no_touchPrepRecvd_p4.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p4_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p4.size())).toString(), (new Float(p_no_touchPrepRecvd_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p4)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_no_touchPrepRecvd_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_no_touchPrepRecvd_p5 = ((float)samples_p_no_touchPrepRecvd_p5.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p5_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p5.size())).toString(), (new Float(p_no_touchPrepRecvd_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p5)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_no_touchPrepRecvd_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_no_touchPrepRecvd_p6 = ((float)samples_p_no_touchPrepRecvd_p6.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_p6_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_p6.size())).toString(), (new Float(p_no_touchPrepRecvd_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_p6)).toString().substring(0, (new Float(p_no_touchPrepRecvd_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_no_touchPrepRecvd_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='2' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_no_touchPrepRecvd_ps = ((float)samples_p_no_touchPrepRecvd_ps.size() / (float)samples_p_no_touchPrepRecvd_alltps.size()) * 100F;
            p_no_touchPrepRecvd_ps_Array = (new String[] {
                (new Integer(samples_p_no_touchPrepRecvd_ps.size())).toString(), (new Float(p_no_touchPrepRecvd_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_no_touchPrepRecvd_ps)).toString().substring(0, (new Float(p_no_touchPrepRecvd_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
            samples_p_blank_touchPrepRecvd_alltps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3'").append(s).toString());
            p_blank_touchPrepRecvd_alltps = ((float)samples_p_blank_touchPrepRecvd_alltps.size() / (float)samples_p_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_alltps_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_alltps.size())).toString(), (new Float(p_blank_touchPrepRecvd_alltps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_alltps)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_alltps)).toString().indexOf("."))).append("%(All T.P.)").toString()
            });
            samples_p_blank_touchPrepRecvd_p1 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P1'").append(s).toString());
            p_blank_touchPrepRecvd_p1 = ((float)samples_p_blank_touchPrepRecvd_p1.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p1_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_p1.size())).toString(), (new Float(p_blank_touchPrepRecvd_p1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p1)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p1)).toString().indexOf("."))).append("%(P1)").toString()
            });
            samples_p_blank_touchPrepRecvd_p2 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P2'").append(s).toString());
            p_blank_touchPrepRecvd_p2 = ((float)samples_p_blank_touchPrepRecvd_p2.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p2_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_p2.size())).toString(), (new Float(p_blank_touchPrepRecvd_p2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p2)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p2)).toString().indexOf("."))).append("%(P2)").toString()
            });
            samples_p_blank_touchPrepRecvd_p3 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P3'").append(s).toString());
            p_blank_touchPrepRecvd_p3 = ((float)samples_p_blank_touchPrepRecvd_p3.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p3_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_p3.size())).toString(), (new Float(p_blank_touchPrepRecvd_p3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p3)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p3)).toString().indexOf("."))).append("%(P3)").toString()
            });
            Vector vector = new Vector();
            vector = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P4'").append(s).toString());
            p_blank_touchPrepRecvd_p4 = ((float)vector.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p4_Array = (new String[] {
                (new Integer(vector.size())).toString(), (new Float(p_blank_touchPrepRecvd_p4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p4)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p4)).toString().indexOf("."))).append("%(P4)").toString()
            });
            samples_p_blank_touchPrepRecvd_p5 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P5'").append(s).toString());
            p_blank_touchPrepRecvd_p5 = ((float)samples_p_blank_touchPrepRecvd_p5.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p5_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_p5.size())).toString(), (new Float(p_blank_touchPrepRecvd_p5)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p5)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p5)).toString().indexOf("."))).append("%(P5)").toString()
            });
            samples_p_blank_touchPrepRecvd_p6 = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='P6'").append(s).toString());
            p_blank_touchPrepRecvd_p6 = ((float)samples_p_blank_touchPrepRecvd_p6.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_p6_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_p6.size())).toString(), (new Float(p_blank_touchPrepRecvd_p6)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_p6)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_p6)).toString().indexOf("."))).append("%(P6)").toString()
            });
            samples_p_blank_touchPrepRecvd_ps = qi_sample.retrieveAllWhere((new StringBuilder()).append(" TOUCHPREP_RECVD ='3' AND TIMEPOINT_NAME='PS'").append(s).toString());
            p_blank_touchPrepRecvd_ps = ((float)samples_p_blank_touchPrepRecvd_ps.size() / (float)samples_p_blank_touchPrepRecvd_alltps.size()) * 100F;
            p_blank_touchPrepRecvd_ps_Array = (new String[] {
                (new Integer(samples_p_blank_touchPrepRecvd_ps.size())).toString(), (new Float(p_blank_touchPrepRecvd_ps)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(p_blank_touchPrepRecvd_ps)).toString().substring(0, (new Float(p_blank_touchPrepRecvd_ps)).toString().indexOf("."))).append("%(PS)").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getSampleDNAQualityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_dna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" dna_extraction_quality IS NOT NULL").append(s).toString());
            samples_no_dna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" dna_extraction_quality IS  NULL").append(s).toString());
            samples_good_dna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" dna_extraction_quality ='Good'").append(s).toString());
            samples_good_dna_ratio = ((float)samples_good_dna.size() / (float)samples_dna.size()) * 100F;
            good_dna_ratio_Array = (new String[] {
                (new Integer(samples_good_dna.size())).toString(), (new Float(samples_good_dna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_good_dna_ratio)).toString().substring(0, (new Float(samples_good_dna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            samples_fair_dna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" dna_extraction_quality ='Fair'").append(s).toString());
            samples_fair_dna_ratio = ((float)samples_fair_dna.size() / (float)samples_dna.size()) * 100F;
            fair_dna_ratio_Array = (new String[] {
                (new Integer(samples_fair_dna.size())).toString(), (new Float(samples_fair_dna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_fair_dna_ratio)).toString().substring(0, (new Float(samples_fair_dna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            samples_poor_dna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" dna_extraction_quality ='Poor'").append(s).toString());
            samples_poor_dna_ratio = ((float)samples_poor_dna.size() / (float)samples_dna.size()) * 100F;
            poor_dna_ratio_Array = (new String[] {
                (new Integer(samples_poor_dna.size())).toString(), (new Float(samples_poor_dna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_poor_dna_ratio)).toString().substring(0, (new Float(samples_poor_dna_ratio)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getSampleRNAQualityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            samples_rna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" rna_extraction_quality IS NOT NULL").append(s).toString());
            samples_no_rna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" rna_extraction_quality IS  NULL").append(s).toString());
            samples_good_rna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" rna_extraction_quality ='Good'").append(s).toString());
            samples_good_rna_ratio = ((float)samples_good_rna.size() / (float)samples_rna.size()) * 100F;
            good_rna_ratio_Array = (new String[] {
                (new Integer(samples_good_rna.size())).toString(), (new Float(samples_good_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_good_rna_ratio)).toString().substring(0, (new Float(samples_good_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            samples_fair_rna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" rna_extraction_quality ='Fair'").append(s).toString());
            samples_fair_rna_ratio = ((float)samples_fair_rna.size() / (float)samples_rna.size()) * 100F;
            fair_rna_ratio_Array = (new String[] {
                (new Integer(samples_fair_rna.size())).toString(), (new Float(samples_fair_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_fair_rna_ratio)).toString().substring(0, (new Float(samples_fair_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            samples_poor_rna = qi_sample.retrieveAllWhere((new StringBuilder()).append(" rna_extraction_quality ='Poor'").append(s).toString());
            samples_poor_rna_ratio = ((float)samples_poor_rna.size() / (float)samples_rna.size()) * 100F;
            poor_rna_ratio_Array = (new String[] {
                (new Integer(samples_poor_rna.size())).toString(), (new Float(samples_poor_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(samples_poor_rna_ratio)).toString().substring(0, (new Float(samples_poor_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getSubSamplePCRQualityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            subsamples_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING IS NOT NULL ").append(s).toString());
            subsamples_no_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING IS  NULL ").append(s).toString());
            subsamples_best_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING ='Best'").append(s).toString());
            subsamples_best_pcr_ratio = ((float)subsamples_best_pcr.size() / (float)subsamples_pcr.size()) * 100F;
            subsamples_best_pcr_ratio_Array = (new String[] {
                (new Integer(subsamples_best_pcr.size())).toString(), (new Float(subsamples_best_pcr_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_best_pcr_ratio)).toString().substring(0, (new Float(subsamples_best_pcr_ratio)).toString().indexOf("."))).append("%").toString()
            });
            subsamples_good_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING ='Good'").append(s).toString());
            subsamples_good_pcr_ratio = ((float)subsamples_good_pcr.size() / (float)subsamples_pcr.size()) * 100F;
            subsamples_good_pcr_ratio_Array = (new String[] {
                (new Integer(subsamples_good_pcr.size())).toString(), (new Float(subsamples_good_pcr_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_good_pcr_ratio)).toString().substring(0, (new Float(subsamples_good_pcr_ratio)).toString().indexOf("."))).append("%").toString()
            });
            subsamples_poor_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING ='Poor'").append(s).toString());
            subsamples_poor_pcr_ratio = ((float)subsamples_poor_pcr.size() / (float)subsamples_pcr.size()) * 100F;
            subsamples_poor_pcr_ratio_Array = (new String[] {
                (new Integer(subsamples_poor_pcr.size())).toString(), (new Float(subsamples_poor_pcr_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_poor_pcr_ratio)).toString().substring(0, (new Float(subsamples_poor_pcr_ratio)).toString().indexOf("."))).append("%").toString()
            });
            subsamples_worst_pcr = dna.retrieveAllWhere((new StringBuilder()).append(" PCR_RATING ='Worst'").append(s).toString());
            subsamples_worst_pcr_ratio = ((float)subsamples_worst_pcr.size() / (float)subsamples_pcr.size()) * 100F;
            subsamples_worst_pcr_ratio_Array = (new String[] {
                (new Integer(subsamples_worst_pcr.size())).toString(), (new Float(subsamples_worst_pcr_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_worst_pcr_ratio)).toString().substring(0, (new Float(subsamples_worst_pcr_ratio)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getSubSampleRNAQualityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            subsamples_no_rna = rna.retrieveAllWhere((new StringBuilder()).append(" RNA_ANALYSIS_QUALITY IS  NULL ").append(s).toString());
            subsamples_rna = rna.retrieveAllWhere((new StringBuilder()).append(" RNA_ANALYSIS_QUALITY IS NOT NULL ").append(s).toString());
            subsamples_good_rna = rna.retrieveAllWhere((new StringBuilder()).append(" RNA_ANALYSIS_QUALITY  ='Good' ").append(s).toString());
            subsamples_good_rna_ratio = ((float)subsamples_good_rna.size() / (float)subsamples_rna.size()) * 100F;
            subsamples_good_rna_ratio_Array = (new String[] {
                (new Integer(subsamples_good_rna.size())).toString(), (new Float(subsamples_good_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_good_rna_ratio)).toString().substring(0, (new Float(subsamples_good_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            subsamples_fair_rna = rna.retrieveAllWhere((new StringBuilder()).append(" RNA_ANALYSIS_QUALITY ='Fair' ").append(s).toString());
            subsamples_fair_rna_ratio = ((float)subsamples_fair_rna.size() / (float)subsamples_rna.size()) * 100F;
            subsamples_fair_rna_ratio_Array = (new String[] {
                (new Integer(subsamples_fair_rna.size())).toString(), (new Float(subsamples_fair_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_fair_rna_ratio)).toString().substring(0, (new Float(subsamples_fair_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
            subsamples_poor_rna = rna.retrieveAllWhere((new StringBuilder()).append(" RNA_ANALYSIS_QUALITY ='Poor' ").append(s).toString());
            subsamples_poor_rna_ratio = ((float)subsamples_poor_rna.size() / (float)subsamples_rna.size()) * 100F;
            subsamples_poor_rna_ratio_Array = (new String[] {
                (new Integer(subsamples_poor_rna.size())).toString(), (new Float(subsamples_poor_rna_ratio)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(subsamples_poor_rna_ratio)).toString().substring(0, (new Float(subsamples_poor_rna_ratio)).toString().indexOf("."))).append("%").toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    private void getSubSampleProteinQualityInfo(Qi_sample qi_sample, String s)
    {
        try
        {
            proteins = protein.retrieveAllWhere((new StringBuilder()).append(" Protein_ID is not null ").append(s).toString());
            protein_tumor = protein.retrieveAllWhere((new StringBuilder()).append(" PROTEIN_SAMPLE_TUMOR_PRESENCE IS NOT NULL ").append(s).toString());
            protein_no_tumor = protein.retrieveAllWhere((new StringBuilder()).append(" PROTEIN_SAMPLE_TUMOR_PRESENCE IS  NULL ").append(s).toString());
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
    }

    public Hashtable getReportHash()
    {
        hashExl.clear();
        hashExl.put("Number of Labtrak Samples", (new Integer(samples.size())).toString());
        hashExl.put("Number of Patients By Institutions", ptNumArray);
        hashExl.put("Total No. of touch prep samples1", touchPrep_f_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present)1", touchPrep_f_pos_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells)1", touchPrep_f_pos_neg_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present)1", touchPrep_f_neg_Array);
        hashExl.put("Total No. of touch prep samples by institution1", touch_prep_allInstitutes_Array);
        hashExl.put("Total No. of touch prep samples by institution2", touch_prep_ucsf_Array);
        hashExl.put("Total No. of touch prep samples by institution3", touch_prep_unc_Array);
        hashExl.put("Total No. of touch prep samples by institution4", touch_prep_upenn_Array);
        hashExl.put("Total No. of touch prep samples by institution5", touch_prep_uc_Array);
        hashExl.put("Total No. of touch prep samples by institution6", touch_prep_uab_Array);
        hashExl.put("Total No. of touch prep samples by institution7", touch_prep_mskcc_Array);
        hashExl.put("Total No. of touch prep samples by institution8", touch_prep_gw_Array);
        hashExl.put("Total No. of touch prep samples by institution9", touch_prep_uw_Array);
        hashExl.put("Total No. of touch prep samples by institution10", touch_prep_ut_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution1", pos_touch_prep_allInstitutes_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution2", pos_touch_prep_ucsf_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution3", pos_touch_prep_unc_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution4", pos_touch_prep_upenn_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution5", pos_touch_prep_uc_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution6", pos_touch_prep_uab_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution7", pos_touch_prep_mskcc_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution8", pos_touch_prep_gw_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution9", pos_touch_prep_uw_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by institution10", pos_touch_prep_ut_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution1", pos_neg_touch_prep_allInstitutes_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution2", pos_neg_touch_prep_ucsf_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution3", pos_neg_touch_prep_unc_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution4", pos_neg_touch_prep_upenn_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution5", pos_neg_touch_prep_uc_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution6", pos_neg_touch_prep_uab_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution7", pos_neg_touch_prep_mskcc_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution8", pos_neg_touch_prep_gw_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution9", pos_neg_touch_prep_uw_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by institution10", pos_neg_touch_prep_ut_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution1", neg_touch_prep_allInstitutes_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution2", neg_touch_prep_ucsf_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution3", neg_touch_prep_unc_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution4", neg_touch_prep_upenn_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution5", neg_touch_prep_uc_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution6", neg_touch_prep_uab_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution7", neg_touch_prep_mskcc_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution8", neg_touch_prep_gw_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution9", neg_touch_prep_uw_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by institution10", neg_touch_prep_ut_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.1", touch_prep_alltps_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.2", touch_prep_f1_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.3", touch_prep_f2_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.4", touch_prep_f3_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.5", touch_prep_f4_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.6", touch_prep_f5_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.7", touch_prep_f6_Array);
        hashExl.put("Total No. of touch prep samples by different T.P.8", touch_prep_fs_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.1", pos_touch_prep_alltps_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.2", pos_touch_prep_f1_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.3", pos_touch_prep_f2_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.4", pos_touch_prep_f3_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.5", pos_touch_prep_f4_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.6", pos_touch_prep_f5_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.7", pos_touch_prep_f6_Array);
        hashExl.put("No. , % of total samples with + (malignant cells present) by different T.P.8", pos_touch_prep_fs_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.1", pos_neg_touch_prep_alltps_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.2", pos_neg_touch_prep_f1_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.3", pos_neg_touch_prep_f2_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.4", pos_neg_touch_prep_f3_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.5", pos_neg_touch_prep_f4_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.6", pos_neg_touch_prep_f5_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.7", pos_neg_touch_prep_f6_Array);
        hashExl.put("No. , % of total samples with +/- (suggestive of malignant cells) by different T.P.8", pos_neg_touch_prep_fs_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.1", neg_touch_prep_alltps_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.2", neg_touch_prep_f1_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.3", neg_touch_prep_f2_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.4", neg_touch_prep_f3_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.5", neg_touch_prep_f4_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.6", neg_touch_prep_f5_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.7", neg_touch_prep_f6_Array);
        hashExl.put("No. , % of total samples with - (no malignant cells present) by different T.P.8", neg_touch_prep_fs_Array);
        hashExl.put("Total No. of H&E tumor samples1", f_h_e_Array);
        hashExl.put("No. , % of total samples with + (cancer present)1", f_pos_h_e_Array);
        hashExl.put("No. , % of total samples with - (no cancer present)1", f_neg_h_e_Array);
        hashExl.put("Total No. of H&E tumor samples by institution1", h_e_allInstitutes_Array);
        hashExl.put("Total No. of H&E tumor samples by institution2", h_e_ucsf_Array);
        hashExl.put("Total No. of H&E tumor samples by institution3", h_e_unc_Array);
        hashExl.put("Total No. of H&E tumor samples by institution4", h_e_upenn_Array);
        hashExl.put("Total No. of H&E tumor samples by institution5", h_e_uc_Array);
        hashExl.put("Total No. of H&E tumor samples by institution6", h_e_uab_Array);
        hashExl.put("Total No. of H&E tumor samples by institution7", h_e_mskcc_Array);
        hashExl.put("Total No. of H&E tumor samples by institution8", h_e_gw_Array);
        hashExl.put("Total No. of H&E tumor samples by institution9", h_e_uw_Array);
        hashExl.put("Total No. of H&E tumor samples by institution10", h_e_ut_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution1", pos_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution2", pos_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution3", pos_h_e_unc_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution4", pos_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution5", pos_h_e_uc_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution6", pos_h_e_uab_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution7", pos_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution8", pos_h_e_gw_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution9", pos_h_e_uw_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by institution10", pos_h_e_ut_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution1", neg_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution2", neg_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution3", neg_h_e_unc_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution4", neg_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution5", neg_h_e_uc_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution6", neg_h_e_uab_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution7", neg_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution8", neg_h_e_gw_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution9", neg_h_e_uw_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by institution10", neg_h_e_ut_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.1", h_e_alltps_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.2", h_e_f1_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.3", h_e_f2_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.4", h_e_f3_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.5", h_e_f4_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.6", h_e_f5_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.7", h_e_f6_Array);
        hashExl.put("Total No. of H&E tumor samples by different T.P.8", h_e_fs_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.1", pos_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.2", pos_h_e_f1_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.3", pos_h_e_f2_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.4", pos_h_e_f3_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.5", pos_h_e_f4_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.6", pos_h_e_f5_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.7", pos_h_e_f6_Array);
        hashExl.put("No. , % of total samples with + (cancer present) by different T.P.8", pos_h_e_fs_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.1", neg_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.2", neg_h_e_f1_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.3", neg_h_e_f2_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.4", neg_h_e_f3_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.5", neg_h_e_f4_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.6", neg_h_e_f5_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.7", neg_h_e_f6_Array);
        hashExl.put("No. , % of total samples with - (no cancer present) by different T.P.8", neg_h_e_fs_Array);
        hashExl.put("Total No. of H&E review samples1", p_h_e_Array);
        hashExl.put("No. , % of total samples with +/+1", p_pos_pos_h_e_Array);
        hashExl.put("No. , % of total samples with -/-1", p_neg_neg_h_e_Array);
        hashExl.put("No. , % of total samples with +/-1", p_pos_neg_h_e_Array);
        hashExl.put("No. , % of total samples with -/+1", p_neg_pos_h_e_Array);
        hashExl.put("No. , % of total samples with blank1", p_blank_h_e_Array);
        hashExl.put("Total No. of H&E review samples by institution1", p_h_e_allInstitutes_Array);
        hashExl.put("Total No. of H&E review samples by institution2", p_h_e_ucsf_Array);
        hashExl.put("Total No. of H&E review samples by institution3", p_h_e_unc_Array);
        hashExl.put("Total No. of H&E review samples by institution4", p_h_e_upenn_Array);
        hashExl.put("Total No. of H&E review samples by institution5", p_h_e_uc_Array);
        hashExl.put("Total No. of H&E review samples by institution6", p_h_e_uab_Array);
        hashExl.put("Total No. of H&E review samples by institution7", p_h_e_mskcc_Array);
        hashExl.put("Total No. of H&E review samples by institution8", p_h_e_gw_Array);
        hashExl.put("Total No. of H&E review samples by institution9", p_h_e_uw_Array);
        hashExl.put("Total No. of H&E review samples by institution10", p_h_e_ut_Array);
        hashExl.put("No. , % of total samples with +/+ by institution1", p_pos_pos_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with +/+ by institution2", p_pos_pos_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with +/+ by institution3", p_pos_pos_h_e_unc_Array);
        hashExl.put("No. , % of total samples with +/+ by institution4", p_pos_pos_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with +/+ by institution5", p_pos_pos_h_e_uc_Array);
        hashExl.put("No. , % of total samples with +/+ by institution6", p_pos_pos_h_e_uab_Array);
        hashExl.put("No. , % of total samples with +/+ by institution7", p_pos_pos_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with +/+ by institution8", p_pos_pos_h_e_gw_Array);
        hashExl.put("No. , % of total samples with +/+ by institution9", p_pos_pos_h_e_uw_Array);
        hashExl.put("No. , % of total samples with +/+ by institution10", p_pos_pos_h_e_ut_Array);
        hashExl.put("No. , % of total samples with -/- by institution1", p_neg_neg_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with -/- by institution2", p_neg_neg_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with -/- by institution3", p_neg_neg_h_e_unc_Array);
        hashExl.put("No. , % of total samples with -/- by institution4", p_neg_neg_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with -/- by institution5", p_neg_neg_h_e_uc_Array);
        hashExl.put("No. , % of total samples with -/- by institution6", p_neg_neg_h_e_uab_Array);
        hashExl.put("No. , % of total samples with -/- by institution7", p_neg_neg_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with -/- by institution8", p_neg_neg_h_e_gw_Array);
        hashExl.put("No. , % of total samples with -/- by institution9", p_neg_neg_h_e_uw_Array);
        hashExl.put("No. , % of total samples with -/- by institution10", p_neg_neg_h_e_ut_Array);
        hashExl.put("No. , % of total samples with +/- by institution1", p_pos_neg_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with +/- by institution2", p_pos_neg_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with +/- by institution3", p_pos_neg_h_e_unc_Array);
        hashExl.put("No. , % of total samples with +/- by institution4", p_pos_neg_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with +/- by institution5", p_pos_neg_h_e_uc_Array);
        hashExl.put("No. , % of total samples with +/- by institution6", p_pos_neg_h_e_uab_Array);
        hashExl.put("No. , % of total samples with +/- by institution7", p_pos_neg_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with +/- by institution8", p_pos_neg_h_e_gw_Array);
        hashExl.put("No. , % of total samples with +/- by institution9", p_pos_neg_h_e_uw_Array);
        hashExl.put("No. , % of total samples with +/- by institution10", p_pos_neg_h_e_ut_Array);
        hashExl.put("No. , % of total samples with -/+ by institution1", p_neg_pos_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with -/+ by institution2", p_neg_pos_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with -/+ by institution3", p_neg_pos_h_e_unc_Array);
        hashExl.put("No. , % of total samples with -/+ by institution4", p_neg_pos_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with -/+ by institution5", p_neg_pos_h_e_uc_Array);
        hashExl.put("No. , % of total samples with -/+ by institution6", p_neg_pos_h_e_uab_Array);
        hashExl.put("No. , % of total samples with -/+ by institution7", p_neg_pos_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with -/+ by institution8", p_neg_pos_h_e_gw_Array);
        hashExl.put("No. , % of total samples with -/+ by institution9", p_neg_pos_h_e_uw_Array);
        hashExl.put("No. , % of total samples with -/+ by institution10", p_neg_pos_h_e_ut_Array);
        hashExl.put("No. , % of total samples with blank by institution1", p_blank_h_e_allInstitutes_Array);
        hashExl.put("No. , % of total samples with blank by institution2", p_blank_h_e_ucsf_Array);
        hashExl.put("No. , % of total samples with blank by institution3", p_blank_h_e_unc_Array);
        hashExl.put("No. , % of total samples with blank by institution4", p_blank_h_e_upenn_Array);
        hashExl.put("No. , % of total samples with blank by institution5", p_blank_h_e_uc_Array);
        hashExl.put("No. , % of total samples with blank by institution6", p_blank_h_e_uab_Array);
        hashExl.put("No. , % of total samples with blank by institution7", p_blank_h_e_mskcc_Array);
        hashExl.put("No. , % of total samples with blank by institution8", p_blank_h_e_gw_Array);
        hashExl.put("No. , % of total samples with blank by institution9", p_blank_h_e_uw_Array);
        hashExl.put("No. , % of total samples with blank by institution10", p_blank_h_e_ut_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.1", p_h_e_alltps_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.2", p_h_e_p1_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.3", p_h_e_p2_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.4", p_h_e_p3_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.5", p_h_e_p4_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.6", p_h_e_p5_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.7", p_h_e_p6_Array);
        hashExl.put("Total No. of H&E review samples by  different T.P.8", p_h_e_ps_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.1", p_pos_pos_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.2", p_pos_pos_h_e_p1_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.3", p_pos_pos_h_e_p2_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.4", p_pos_pos_h_e_p3_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.5", p_pos_pos_h_e_p4_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.6", p_pos_pos_h_e_p5_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.7", p_pos_pos_h_e_p6_Array);
        hashExl.put("No. , % of total samples with +/+ by different T.P.8", p_pos_pos_h_e_ps_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.1", p_neg_neg_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.2", p_neg_neg_h_e_p1_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.3", p_neg_neg_h_e_p2_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.4", p_neg_neg_h_e_p3_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.5", p_neg_neg_h_e_p4_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.6", p_neg_neg_h_e_p5_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.7", p_neg_neg_h_e_p6_Array);
        hashExl.put("No. , % of total samples with -/- by different T.P.8", p_neg_neg_h_e_ps_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.1", p_pos_neg_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.2", p_pos_neg_h_e_p1_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.3", p_pos_neg_h_e_p2_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.4", p_pos_neg_h_e_p3_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.5", p_pos_neg_h_e_p4_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.6", p_pos_neg_h_e_p5_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.7", p_pos_neg_h_e_p6_Array);
        hashExl.put("No. , % of total samples with +/- by different T.P.8", p_pos_neg_h_e_ps_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.1", p_neg_pos_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.2", p_neg_pos_h_e_p1_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.3", p_neg_pos_h_e_p2_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.4", p_neg_pos_h_e_p3_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.5", p_neg_pos_h_e_p4_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.6", p_neg_pos_h_e_p5_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.7", p_neg_pos_h_e_p6_Array);
        hashExl.put("No. , % of total samples with -/+ by different T.P.8", p_neg_pos_h_e_ps_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.1", p_blank_h_e_alltps_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.2", p_blank_h_e_p1_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.3", p_blank_h_e_p2_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.4", p_blank_h_e_p3_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.5", p_blank_h_e_p4_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.6", p_blank_h_e_p5_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.7", p_blank_h_e_p6_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.8", p_blank_h_e_ps_Array);
        hashExl.put("Total No. of H&E usability samples1", p_usability_Array);
        hashExl.put("No. , % of total samples with yes1", p_yes_usability_Array);
        hashExl.put("No. , % of total samples with no1", p_no_usability_Array);
        hashExl.put("No. , % of total samples with blank1", p_blank_usability_Array);
        hashExl.put("Total No. of H&E usability samples by institution1", p_usability_allInstitutes_Array);
        hashExl.put("Total No. of H&E usability samples by institution2", p_usability_ucsf_Array);
        hashExl.put("Total No. of H&E usability samples by institution3", p_usability_unc_Array);
        hashExl.put("Total No. of H&E usability samples by institution4", p_usability_upenn_Array);
        hashExl.put("Total No. of H&E usability samples by institution5", p_usability_uc_Array);
        hashExl.put("Total No. of H&E usability samples by institution6", p_usability_uab_Array);
        hashExl.put("Total No. of H&E usability samples by institution7", p_usability_mskcc_Array);
        hashExl.put("Total No. of H&E usability samples by institution8", p_usability_gw_Array);
        hashExl.put("Total No. of H&E usability samples by institution9", p_usability_uw_Array);
        hashExl.put("Total No. of H&E usability samples by institution10", p_usability_ut_Array);
        hashExl.put("No. , % of total samples with yes by institution1", p_yes_usability_allInstitutes_Array);
        hashExl.put("No. , % of total samples with yes by institution2", p_yes_usability_ucsf_Array);
        hashExl.put("No. , % of total samples with yes by institution3", p_yes_usability_unc_Array);
        hashExl.put("No. , % of total samples with yes by institution4", p_yes_usability_upenn_Array);
        hashExl.put("No. , % of total samples with yes by institution5", p_yes_usability_uc_Array);
        hashExl.put("No. , % of total samples with yes by institution6", p_yes_usability_uab_Array);
        hashExl.put("No. , % of total samples with yes by institution7", p_yes_usability_mskcc_Array);
        hashExl.put("No. , % of total samples with yes by institution8", p_yes_usability_gw_Array);
        hashExl.put("No. , % of total samples with yes by institution9", p_yes_usability_uw_Array);
        hashExl.put("No. , % of total samples with yes by institution10", p_yes_usability_ut_Array);
        hashExl.put("No. , % of total samples with no by institution1", p_no_usability_allInstitutes_Array);
        hashExl.put("No. , % of total samples with no by institution2", p_no_usability_ucsf_Array);
        hashExl.put("No. , % of total samples with no by institution3", p_no_usability_unc_Array);
        hashExl.put("No. , % of total samples with no by institution4", p_no_usability_upenn_Array);
        hashExl.put("No. , % of total samples with no by institution5", p_no_usability_uc_Array);
        hashExl.put("No. , % of total samples with no by institution6", p_no_usability_uab_Array);
        hashExl.put("No. , % of total samples with no by institution7", p_no_usability_mskcc_Array);
        hashExl.put("No. , % of total samples with no by institution8", p_no_usability_gw_Array);
        hashExl.put("No. , % of total samples with no by institution9", p_no_usability_uw_Array);
        hashExl.put("No. , % of total samples with no by institution10", p_no_usability_ut_Array);
        hashExl.put("No. , % of total samples with blank by institution1", p_blank_usability_allInstitutes_Array);
        hashExl.put("No. , % of total samples with blank by institution2", p_blank_usability_ucsf_Array);
        hashExl.put("No. , % of total samples with blank by institution3", p_blank_usability_unc_Array);
        hashExl.put("No. , % of total samples with blank by institution4", p_blank_usability_upenn_Array);
        hashExl.put("No. , % of total samples with blank by institution5", p_blank_usability_uc_Array);
        hashExl.put("No. , % of total samples with blank by institution6", p_blank_usability_uab_Array);
        hashExl.put("No. , % of total samples with blank by institution7", p_blank_usability_mskcc_Array);
        hashExl.put("No. , % of total samples with blank by institution8", p_blank_usability_gw_Array);
        hashExl.put("No. , % of total samples with blank by institution9", p_blank_usability_uw_Array);
        hashExl.put("No. , % of total samples with blank by institution10", p_blank_usability_ut_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.1", p_usability_alltps_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.2", p_usability_p1_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.3", p_usability_p2_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.4", p_usability_p3_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.5", p_usability_p4_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.6", p_usability_p5_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.7", p_usability_p6_Array);
        hashExl.put("Total No. of H&E usability samples by different T.P.8", p_usability_ps_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.1", p_yes_usability_alltps_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.2", p_yes_usability_p1_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.3", p_yes_usability_p2_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.4", p_yes_usability_p3_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.5", p_yes_usability_p4_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.6", p_yes_usability_p5_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.7", p_yes_usability_p6_Array);
        hashExl.put("No. , % of total samples with yes by different T.P.8", p_yes_usability_ps_Array);
        hashExl.put("No. , % of total samples with no by different T.P.1", p_no_usability_alltps_Array);
        hashExl.put("No. , % of total samples with no by different T.P.2", p_no_usability_p1_Array);
        hashExl.put("No. , % of total samples with no by different T.P.3", p_no_usability_p2_Array);
        hashExl.put("No. , % of total samples with no by different T.P.4", p_no_usability_p3_Array);
        hashExl.put("No. , % of total samples with no by different T.P.5", p_no_usability_p4_Array);
        hashExl.put("No. , % of total samples with no by different T.P.6", p_no_usability_p5_Array);
        hashExl.put("No. , % of total samples with no by different T.P.7", p_no_usability_p6_Array);
        hashExl.put("No. , % of total samples with no by different T.P.8", p_no_usability_ps_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.1", p_blank_usability_alltps_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.2", p_blank_usability_p1_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.3", p_blank_usability_p2_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.4", p_blank_usability_p3_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.5", p_blank_usability_p4_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.6", p_blank_usability_p5_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.7", p_blank_usability_p6_Array);
        hashExl.put("No. , % of total samples with blank by different T.P.8", p_blank_usability_ps_Array);
        hashExl.put("Total No. of touch preps received samples1", p_touchPrepRecvd_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes1", p_yes_touchPrepRecvd_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no1", p_no_touchPrepRecvd_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank1", p_blank_touchPrepRecvd_Array);
        hashExl.put("Total No. of touch preps received samples by institution1", p_touchPrepRecvd_allInstitutes_Array);
        hashExl.put("Total No. of touch preps received samples by institution2", p_touchPrepRecvd_ucsf_Array);
        hashExl.put("Total No. of touch preps received samples by institution3", p_touchPrepRecvd_unc_Array);
        hashExl.put("Total No. of touch preps received samples by institution4", p_touchPrepRecvd_upenn_Array);
        hashExl.put("Total No. of touch preps received samples by institution5", p_touchPrepRecvd_uc_Array);
        hashExl.put("Total No. of touch preps received samples by institution6", p_touchPrepRecvd_uab_Array);
        hashExl.put("Total No. of touch preps received samples by institution7", p_touchPrepRecvd_mskcc_Array);
        hashExl.put("Total No. of touch preps received samples by institution8", p_touchPrepRecvd_gw_Array);
        hashExl.put("Total No. of touch preps received samples by institution9", p_touchPrepRecvd_uw_Array);
        hashExl.put("Total No. of touch preps received samples by institution10", p_touchPrepRecvd_ut_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution1", p_yes_touchPrepRecvd_allInstitutes_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution2", p_yes_touchPrepRecvd_ucsf_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution3", p_yes_touchPrepRecvd_unc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution4", p_yes_touchPrepRecvd_upenn_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution5", p_yes_touchPrepRecvd_uc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution6", p_yes_touchPrepRecvd_uab_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution7", p_yes_touchPrepRecvd_mskcc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution8", p_yes_touchPrepRecvd_gw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution9", p_yes_touchPrepRecvd_uw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by institution10", p_yes_touchPrepRecvd_ut_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution1", p_no_touchPrepRecvd_allInstitutes_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution2", p_no_touchPrepRecvd_ucsf_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution3", p_no_touchPrepRecvd_unc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution4", p_no_touchPrepRecvd_upenn_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution5", p_no_touchPrepRecvd_uc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution6", p_no_touchPrepRecvd_uab_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution7", p_no_touchPrepRecvd_mskcc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution8", p_no_touchPrepRecvd_gw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution9", p_no_touchPrepRecvd_uw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by institution10", p_no_touchPrepRecvd_ut_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution1", p_blank_touchPrepRecvd_allInstitutes_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution2", p_blank_touchPrepRecvd_ucsf_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution3", p_blank_touchPrepRecvd_unc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution4", p_blank_touchPrepRecvd_upenn_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution5", p_blank_touchPrepRecvd_uc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution6", p_blank_touchPrepRecvd_uab_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution7", p_blank_touchPrepRecvd_mskcc_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution8", p_blank_touchPrepRecvd_gw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution9", p_blank_touchPrepRecvd_uw_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by institution10", p_blank_touchPrepRecvd_ut_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.1", p_touchPrepRecvd_alltps_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.2", p_touchPrepRecvd_p1_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.3", p_touchPrepRecvd_p2_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.4", p_touchPrepRecvd_p3_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.5", p_touchPrepRecvd_p4_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.6", p_touchPrepRecvd_p5_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.7", p_touchPrepRecvd_p6_Array);
        hashExl.put("Total No. of touch preps received samples by different T.P.8", p_touchPrepRecvd_ps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.1", p_yes_touchPrepRecvd_alltps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.2", p_yes_touchPrepRecvd_p1_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.3", p_yes_touchPrepRecvd_p2_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.4", p_yes_touchPrepRecvd_p3_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.5", p_yes_touchPrepRecvd_p4_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.6", p_yes_touchPrepRecvd_p5_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.7", p_yes_touchPrepRecvd_p6_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with yes by different T.P.8", p_yes_touchPrepRecvd_ps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.1", p_no_touchPrepRecvd_alltps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.2", p_no_touchPrepRecvd_p1_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.3", p_no_touchPrepRecvd_p2_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.4", p_no_touchPrepRecvd_p3_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.5", p_no_touchPrepRecvd_p4_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.6", p_no_touchPrepRecvd_p5_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.7", p_no_touchPrepRecvd_p6_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with no by different T.P.8", p_no_touchPrepRecvd_ps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.1", p_blank_touchPrepRecvd_alltps_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.2", p_blank_touchPrepRecvd_p1_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.3", p_blank_touchPrepRecvd_p2_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.4", p_blank_touchPrepRecvd_p3_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.5", p_blank_touchPrepRecvd_p4_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.6", p_blank_touchPrepRecvd_p5_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.7", p_blank_touchPrepRecvd_p6_Array);
        hashExl.put("No. , % of total parrafin touch prep received samples with blank by different T.P.8", p_blank_touchPrepRecvd_ps_Array);
        hashExl.put("Total No. of DNA samples1", new String[] {
            (new Integer(samples_dna.size())).toString()
        });
        hashExl.put("No. , % of total samples with good (ratio of 1.7 - 2.0 for 260/280 absorbance) DNA1", good_dna_ratio_Array);
        hashExl.put("No. , % of total samples with fair (ratio of 1.6 - 1.7) DNA1", fair_dna_ratio_Array);
        hashExl.put("No. , % of total samples with poor (ratio of 1.6 - 1.7) DNA1", poor_dna_ratio_Array);
        hashExl.put("Total No. of DNA sub-samples with P53 multiplex PCR quality info1", new String[] {
            (new Integer(subsamples_pcr.size())).toString()
        });
        hashExl.put("No. , % of total sub-samples with best P53 multiplex PCR rating1", subsamples_best_pcr_ratio_Array);
        hashExl.put("No. , % of total sub-samples with good P53 multiplex PCR rating1", subsamples_good_pcr_ratio_Array);
        hashExl.put("No. , % of total sub-samples with poor P53 multiplex PCR rating1", subsamples_poor_pcr_ratio_Array);
        hashExl.put("No. , % of total sub-samples with worst P53 multiplex PCR rating1", subsamples_worst_pcr_ratio_Array);
        float f = ((float)getHigh_dna_labels_total() / (float)getDna_labels_total()) * 100F;
        float f1 = ((float)getMedium_dna_labels_total() / (float)getDna_labels_total()) * 100F;
        float f2 = ((float)getLow_dna_labels_total() / (float)getDna_labels_total()) * 100F;
        float f3 = ((float)getGood_cgh_hybs_total() / (float)getCgh_hybs_total()) * 100F;
        float f4 = ((float)getPoor_cgh_hybs_total() / (float)getCgh_hybs_total()) * 100F;
        hashExl.put("Total No. of DNA Samples with DNA Labeling Info1", new String[] {
            (new Integer(getDna_with_labeling_total())).toString()
        });
        hashExl.put("Total No. of DNA Labeling Samples1", new String[] {
            (new Integer(getDna_labels_total())).toString()
        });
        hashExl.put("No. , % of High (intensity reading >=8) DNA Labeling Intensity1", new String[] {
            (new Integer(getHigh_dna_labels_total())).toString(), (new Float(f)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f)).toString().substring(0, (new Float(f)).toString().indexOf("."))).append("%").toString()
        });
        hashExl.put("No. , % of Medium (intensity reading btwn 6-8) DNA Labeling Intensity1", new String[] {
            (new Integer(getMedium_dna_labels_total())).toString(), (new Float(f1)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f1)).toString().substring(0, (new Float(f1)).toString().indexOf("."))).append("%").toString()
        });
        hashExl.put("No. , % of Low (intensity reading < 6) DNA Labeling Intensity1", new String[] {
            (new Integer(getLow_dna_labels_total())).toString(), (new Float(f2)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f2)).toString().substring(0, (new Float(f2)).toString().indexOf("."))).append("%").toString()
        });
        hashExl.put("Total No. of DNA Samples with CGH Hybridization Info1", new String[] {
            (new Integer(getDna_with_cgh_total())).toString()
        });
        hashExl.put("Total No. of CGH Hybridization Samples1", new String[] {
            (new Integer(getCgh_hybs_total())).toString()
        });
        hashExl.put("No. , % of Good, Acceptable( score >=3) CGH Hybridization Samples1", new String[] {
            (new Integer(getGood_cgh_hybs_total())).toString(), (new Float(f3)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f3)).toString().substring(0, (new Float(f3)).toString().indexOf("."))).append("%").toString()
        });
        hashExl.put("No. , % of Poor (score <3) CGH Hybridization Samples1", new String[] {
            (new Integer(getPoor_cgh_hybs_total())).toString(), (new Float(f4)).toString().equals("NaN") ? "NaN" : (new StringBuilder()).append((new Float(f4)).toString().substring(0, (new Float(f4)).toString().indexOf("."))).append("%").toString()
        });
        hashExl.put("Total No. of RNA samples1", new String[] {
            (new Integer(samples_rna.size())).toString()
        });
        hashExl.put("No. , % of total samples with good (ratio of 1.7 - 2.0 for 260/280 absorbance) RNA1", good_rna_ratio_Array);
        hashExl.put("No. , % of total samples with fair (ratio of 1.6 - 1.7) RNA1", fair_rna_ratio_Array);
        hashExl.put("No. , % of total samples with poor (ratio of 1.6 - 1.7) RNA1", poor_rna_ratio_Array);
        try
        {
            hashExl.put("Total No. of RNA sub-samples1", new String[] {
                (new Integer(subsamples_rna.size() + subsamples_no_rna.size())).toString()
            });
        }
        catch(Exception exception)
        {
            System.out.println(exception);
        }
        hashExl.put("Total No. of RNA sub-samples containing RNA analysis quality info1", new String[] {
            (new Integer(subsamples_rna.size())).toString()
        });
        hashExl.put("Total No. of RNA sub-samples not containing any RNA analysis quality info(ignored for calculating %)1", new String[] {
            (new Integer(subsamples_no_rna.size())).toString()
        });
        hashExl.put("No. , % of RNA sub-samples with good (discernable 18s and 28s peaks and no degradation) RNA rating1", subsamples_good_rna_ratio_Array);
        hashExl.put("No. , % of RNA sub-samples with fair (discernable peaks with substantial degradation) RNA rating1", subsamples_fair_rna_ratio_Array);
        hashExl.put("No. , % of RNA sub-samples with poor (no discernable peaks) RNA rating1", subsamples_poor_rna_ratio_Array);
        try
        {
            hashExl.put("Total No. of protein sub-samples1", new String[] {
                (new Integer(getProteinSize())).toString()
            });
        }
        catch(Exception exception1)
        {
            System.out.println(exception1);
        }
        hashExl.put("Total No. of protein sub-samples with tumor presence info1", new String[] {
            (new Integer(protein_tumor.size())).toString()
        });
        hashExl.put("Total No. of protein sub-samples w/o any tumor presence info1", new String[] {
            (new Integer(protein_no_tumor.size())).toString()
        });
        return hashExl;
    }

    public SummaryReport(Vector vector, String s)
    {
        sample = new Qi_sample();
        sampleSearch = new SampleEx();
        hashExl = new Hashtable();
        samples_f = new Vector();
        samples = new Vector();
        frozenSamples = new Vector();
        paraffinSamples = new Vector();
        sampleSize = 0;
        allPt = 0;
        ucsfPt = 0;
        uncPt = 0;
        upennPt = 0;
        ucPt = 0;
        uabPt = 0;
        gwPt = 0;
        mskccPt = 0;
        uwPt = 0;
        utPt = 0;
        samples_f_pos_touch_prep = new Vector();
        samples_f_pos_neg_touch_prep = new Vector();
        samples_f_neg_touch_prep = new Vector();
        samples_f_touch_prep_allInstitutes = new Vector();
        touch_prep_allInstitutes = 0.0F;
        samples_f_touch_prep_ucsf = new Vector();
        touch_prep_ucsf = 0.0F;
        samples_f_touch_prep_unc = new Vector();
        touch_prep_unc = 0.0F;
        samples_f_touch_prep_upenn = new Vector();
        touch_prep_upenn = 0.0F;
        samples_f_touch_prep_uc = new Vector();
        touch_prep_uc = 0.0F;
        samples_f_touch_prep_uab = new Vector();
        touch_prep_uab = 0.0F;
        samples_f_touch_prep_mskcc = new Vector();
        touch_prep_mskcc = 0.0F;
        samples_f_touch_prep_gw = new Vector();
        touch_prep_gw = 0.0F;
        samples_f_touch_prep_uw = new Vector();
        touch_prep_uw = 0.0F;
        samples_f_touch_prep_ut = new Vector();
        touch_prep_ut = 0.0F;
        samples_f_pos_touch_prep_allInstitutes = new Vector();
        pos_touch_prep_allInstitutes = 0.0F;
        samples_f_pos_touch_prep_ucsf = new Vector();
        pos_touch_prep_ucsf = 0.0F;
        samples_f_pos_touch_prep_unc = new Vector();
        pos_touch_prep_unc = 0.0F;
        samples_f_pos_touch_prep_upenn = new Vector();
        pos_touch_prep_upenn = 0.0F;
        samples_f_pos_touch_prep_uc = new Vector();
        pos_touch_prep_uc = 0.0F;
        samples_f_pos_touch_prep_uab = new Vector();
        pos_touch_prep_uab = 0.0F;
        samples_f_pos_touch_prep_mskcc = new Vector();
        pos_touch_prep_mskcc = 0.0F;
        samples_f_pos_touch_prep_gw = new Vector();
        pos_touch_prep_gw = 0.0F;
        samples_f_pos_touch_prep_uw = new Vector();
        pos_touch_prep_uw = 0.0F;
        samples_f_pos_touch_prep_ut = new Vector();
        pos_touch_prep_ut = 0.0F;
        samples_f_pos_neg_touch_prep_allInstitutes = new Vector();
        pos_neg_touch_prep_allInstitutes = 0.0F;
        samples_f_pos_neg_touch_prep_ucsf = new Vector();
        pos_neg_touch_prep_ucsf = 0.0F;
        samples_f_pos_neg_touch_prep_unc = new Vector();
        pos_neg_touch_prep_unc = 0.0F;
        samples_f_pos_neg_touch_prep_upenn = new Vector();
        pos_neg_touch_prep_upenn = 0.0F;
        samples_f_pos_neg_touch_prep_uc = new Vector();
        pos_neg_touch_prep_uc = 0.0F;
        samples_f_pos_neg_touch_prep_uab = new Vector();
        pos_neg_touch_prep_uab = 0.0F;
        samples_f_pos_neg_touch_prep_mskcc = new Vector();
        pos_neg_touch_prep_mskcc = 0.0F;
        samples_f_pos_neg_touch_prep_gw = new Vector();
        pos_neg_touch_prep_gw = 0.0F;
        samples_f_pos_neg_touch_prep_uw = new Vector();
        pos_neg_touch_prep_uw = 0.0F;
        samples_f_pos_neg_touch_prep_ut = new Vector();
        pos_neg_touch_prep_ut = 0.0F;
        samples_f_neg_touch_prep_allInstitutes = new Vector();
        neg_touch_prep_allInstitutes = 0.0F;
        samples_f_neg_touch_prep_ucsf = new Vector();
        neg_touch_prep_ucsf = 0.0F;
        samples_f_neg_touch_prep_unc = new Vector();
        neg_touch_prep_unc = 0.0F;
        samples_f_neg_touch_prep_upenn = new Vector();
        neg_touch_prep_upenn = 0.0F;
        samples_f_neg_touch_prep_uc = new Vector();
        neg_touch_prep_uc = 0.0F;
        samples_f_neg_touch_prep_uab = new Vector();
        neg_touch_prep_uab = 0.0F;
        samples_f_neg_touch_prep_mskcc = new Vector();
        neg_touch_prep_mskcc = 0.0F;
        samples_f_neg_touch_prep_gw = new Vector();
        neg_touch_prep_gw = 0.0F;
        samples_f_neg_touch_prep_uw = new Vector();
        neg_touch_prep_uw = 0.0F;
        samples_f_neg_touch_prep_ut = new Vector();
        neg_touch_prep_ut = 0.0F;
        f_touch_prep = 0.0F;
        f_pos_touch_prep = 0.0F;
        f_pos_neg_touch_prep = 0.0F;
        f_neg_touch_prep = 0.0F;
        samples_f_touch_prep_alltps = new Vector();
        touch_prep_alltps = 0.0F;
        samples_f_touch_prep_f1 = new Vector();
        touch_prep_f1 = 0.0F;
        samples_f_touch_prep_f2 = new Vector();
        touch_prep_f2 = 0.0F;
        samples_f_touch_prep_f3 = new Vector();
        touch_prep_f3 = 0.0F;
        samples_f_touch_prep_f4 = new Vector();
        touch_prep_f4 = 0.0F;
        samples_f_touch_prep_f5 = new Vector();
        touch_prep_f5 = 0.0F;
        samples_f_touch_prep_f6 = new Vector();
        touch_prep_f6 = 0.0F;
        samples_f_touch_prep_fs = new Vector();
        touch_prep_fs = 0.0F;
        samples_f_pos_touch_prep_alltps = new Vector();
        pos_touch_prep_alltps = 0.0F;
        samples_f_pos_touch_prep_f1 = new Vector();
        pos_touch_prep_f1 = 0.0F;
        samples_f_pos_touch_prep_f2 = new Vector();
        pos_touch_prep_f2 = 0.0F;
        samples_f_pos_touch_prep_f3 = new Vector();
        pos_touch_prep_f3 = 0.0F;
        samples_f_pos_touch_prep_f4 = new Vector();
        pos_touch_prep_f4 = 0.0F;
        samples_f_pos_touch_prep_f5 = new Vector();
        pos_touch_prep_f5 = 0.0F;
        samples_f_pos_touch_prep_f6 = new Vector();
        pos_touch_prep_f6 = 0.0F;
        samples_f_pos_touch_prep_fs = new Vector();
        pos_touch_prep_fs = 0.0F;
        samples_f_pos_neg_touch_prep_alltps = new Vector();
        pos_neg_touch_prep_alltps = 0.0F;
        samples_f_pos_neg_touch_prep_f1 = new Vector();
        pos_neg_touch_prep_f1 = 0.0F;
        samples_f_pos_neg_touch_prep_f2 = new Vector();
        pos_neg_touch_prep_f2 = 0.0F;
        samples_f_pos_neg_touch_prep_f3 = new Vector();
        pos_neg_touch_prep_f3 = 0.0F;
        samples_f_pos_neg_touch_prep_f4 = new Vector();
        pos_neg_touch_prep_f4 = 0.0F;
        samples_f_pos_neg_touch_prep_f5 = new Vector();
        pos_neg_touch_prep_f5 = 0.0F;
        samples_f_pos_neg_touch_prep_f6 = new Vector();
        pos_neg_touch_prep_f6 = 0.0F;
        samples_f_pos_neg_touch_prep_fs = new Vector();
        pos_neg_touch_prep_fs = 0.0F;
        samples_f_neg_touch_prep_alltps = new Vector();
        neg_touch_prep_alltps = 0.0F;
        samples_f_neg_touch_prep_f1 = new Vector();
        neg_touch_prep_f1 = 0.0F;
        samples_f_neg_touch_prep_f2 = new Vector();
        neg_touch_prep_f2 = 0.0F;
        samples_f_neg_touch_prep_f3 = new Vector();
        neg_touch_prep_f3 = 0.0F;
        samples_f_neg_touch_prep_f4 = new Vector();
        neg_touch_prep_f4 = 0.0F;
        samples_f_neg_touch_prep_f5 = new Vector();
        neg_touch_prep_f5 = 0.0F;
        samples_f_neg_touch_prep_f6 = new Vector();
        neg_touch_prep_f6 = 0.0F;
        samples_f_neg_touch_prep_fs = new Vector();
        neg_touch_prep_fs = 0.0F;
        samples_f_h_e = new Vector();
        f_h_e = 0.0F;
        f_h_e_Array = null;
        samples_f_pos_h_e = new Vector();
        f_pos_h_e = 0.0F;
        f_pos_h_e_Array = null;
        samples_f_neg_h_e = new Vector();
        f_neg_h_e = 0.0F;
        f_neg_h_e_Array = null;
        samples_f_h_e_allInstitutes = new Vector();
        h_e_allInstitutes = 0.0F;
        h_e_allInstitutes_Array = null;
        samples_f_h_e_ucsf = new Vector();
        h_e_ucsf = 0.0F;
        h_e_ucsf_Array = null;
        samples_f_h_e_unc = new Vector();
        h_e_unc = 0.0F;
        h_e_unc_Array = null;
        samples_f_h_e_upenn = new Vector();
        h_e_upenn = 0.0F;
        h_e_upenn_Array = null;
        samples_f_h_e_uc = new Vector();
        h_e_uc = 0.0F;
        h_e_uc_Array = null;
        samples_f_h_e_uab = new Vector();
        h_e_uab = 0.0F;
        h_e_uab_Array = null;
        samples_f_h_e_mskcc = new Vector();
        h_e_mskcc = 0.0F;
        h_e_mskcc_Array = null;
        samples_f_h_e_gw = new Vector();
        h_e_gw = 0.0F;
        h_e_gw_Array = null;
        samples_f_h_e_uw = new Vector();
        h_e_uw = 0.0F;
        h_e_uw_Array = null;
        samples_f_h_e_ut = new Vector();
        h_e_ut = 0.0F;
        h_e_ut_Array = null;
        samples_f_h_e_not_null = new Vector();
        samples_f_pos_h_e_allInstitutes = new Vector();
        pos_h_e_allInstitutes = 0.0F;
        pos_h_e_allInstitutes_Array = null;
        samples_f_pos_h_e_ucsf = new Vector();
        pos_h_e_ucsf = 0.0F;
        pos_h_e_ucsf_Array = null;
        samples_f_pos_h_e_unc = new Vector();
        pos_h_e_unc = 0.0F;
        pos_h_e_unc_Array = null;
        samples_f_pos_h_e_upenn = new Vector();
        pos_h_e_upenn = 0.0F;
        pos_h_e_upenn_Array = null;
        samples_f_pos_h_e_uc = new Vector();
        pos_h_e_uc = 0.0F;
        pos_h_e_uc_Array = null;
        samples_f_pos_h_e_uab = new Vector();
        pos_h_e_uab = 0.0F;
        pos_h_e_uab_Array = null;
        samples_f_pos_h_e_mskcc = new Vector();
        pos_h_e_mskcc = 0.0F;
        pos_h_e_mskcc_Array = null;
        samples_f_pos_h_e_gw = new Vector();
        pos_h_e_gw = 0.0F;
        pos_h_e_gw_Array = null;
        samples_f_pos_h_e_uw = new Vector();
        pos_h_e_uw = 0.0F;
        pos_h_e_uw_Array = null;
        samples_f_pos_h_e_ut = new Vector();
        pos_h_e_ut = 0.0F;
        pos_h_e_ut_Array = null;
        samples_f_neg_h_e_allInstitutes = new Vector();
        neg_h_e_allInstitutes = 0.0F;
        neg_h_e_allInstitutes_Array = null;
        samples_f_neg_h_e_ucsf = new Vector();
        neg_h_e_ucsf = 0.0F;
        neg_h_e_ucsf_Array = null;
        samples_f_neg_h_e_unc = new Vector();
        neg_h_e_unc = 0.0F;
        neg_h_e_unc_Array = null;
        samples_f_neg_h_e_upenn = new Vector();
        neg_h_e_upenn = 0.0F;
        neg_h_e_upenn_Array = null;
        samples_f_neg_h_e_uc = new Vector();
        neg_h_e_uc = 0.0F;
        neg_h_e_uc_Array = null;
        samples_f_neg_h_e_uab = new Vector();
        neg_h_e_uab = 0.0F;
        neg_h_e_uab_Array = null;
        samples_f_neg_h_e_mskcc = new Vector();
        neg_h_e_mskcc = 0.0F;
        neg_h_e_mskcc_Array = null;
        samples_f_neg_h_e_gw = new Vector();
        neg_h_e_gw = 0.0F;
        neg_h_e_gw_Array = null;
        samples_f_neg_h_e_uw = new Vector();
        neg_h_e_uw = 0.0F;
        neg_h_e_uw_Array = null;
        samples_f_neg_h_e_ut = new Vector();
        neg_h_e_ut = 0.0F;
        neg_h_e_ut_Array = null;
        samples_f_h_e_alltps = new Vector();
        h_e_alltps = 0.0F;
        h_e_alltps_Array = null;
        samples_f_h_e_f1 = new Vector();
        h_e_f1 = 0.0F;
        h_e_f1_Array = null;
        samples_f_h_e_f2 = new Vector();
        h_e_f2 = 0.0F;
        h_e_f2_Array = null;
        samples_f_h_e_f3 = new Vector();
        h_e_f3 = 0.0F;
        h_e_f3_Array = null;
        samples_f_h_e_f4 = new Vector();
        h_e_f4 = 0.0F;
        h_e_f4_Array = null;
        samples_f_h_e_f5 = new Vector();
        h_e_f5 = 0.0F;
        h_e_f5_Array = null;
        samples_f_h_e_f6 = new Vector();
        h_e_f6 = 0.0F;
        h_e_f6_Array = null;
        samples_f_h_e_fs = new Vector();
        h_e_fs = 0.0F;
        h_e_fs_Array = null;
        samples_f_pos_h_e_alltps = new Vector();
        pos_h_e_alltps = 0.0F;
        pos_h_e_alltps_Array = null;
        samples_f_pos_h_e_f1 = new Vector();
        pos_h_e_f1 = 0.0F;
        pos_h_e_f1_Array = null;
        samples_f_pos_h_e_f2 = new Vector();
        pos_h_e_f2 = 0.0F;
        pos_h_e_f2_Array = null;
        samples_f_pos_h_e_f3 = new Vector();
        pos_h_e_f3 = 0.0F;
        pos_h_e_f3_Array = null;
        samples_f_pos_h_e_f4 = new Vector();
        pos_h_e_f4 = 0.0F;
        pos_h_e_f4_Array = null;
        samples_f_pos_h_e_f5 = new Vector();
        pos_h_e_f5 = 0.0F;
        pos_h_e_f5_Array = null;
        samples_f_pos_h_e_f6 = new Vector();
        pos_h_e_f6 = 0.0F;
        pos_h_e_f6_Array = null;
        samples_f_pos_h_e_fs = new Vector();
        pos_h_e_fs = 0.0F;
        pos_h_e_fs_Array = null;
        samples_f_neg_h_e_alltps = new Vector();
        neg_h_e_alltps = 0.0F;
        neg_h_e_alltps_Array = null;
        samples_f_neg_h_e_f1 = new Vector();
        neg_h_e_f1 = 0.0F;
        neg_h_e_f1_Array = null;
        samples_f_neg_h_e_f2 = new Vector();
        neg_h_e_f2 = 0.0F;
        neg_h_e_f2_Array = null;
        samples_f_neg_h_e_f3 = new Vector();
        neg_h_e_f3 = 0.0F;
        neg_h_e_f3_Array = null;
        samples_f_neg_h_e_f4 = new Vector();
        neg_h_e_f4 = 0.0F;
        neg_h_e_f4_Array = null;
        samples_f_neg_h_e_f5 = new Vector();
        neg_h_e_f5 = 0.0F;
        neg_h_e_f5_Array = null;
        samples_f_neg_h_e_f6 = new Vector();
        neg_h_e_f6 = 0.0F;
        neg_h_e_f6_Array = null;
        samples_f_neg_h_e_fs = new Vector();
        neg_h_e_fs = 0.0F;
        neg_h_e_fs_Array = null;
        samples_p = new Vector();
        p_h_e = 0.0F;
        p_h_e_Array = null;
        samples_p_pos_pos_h_e = new Vector();
        p_pos_pos_h_e = 0.0F;
        p_pos_pos_h_e_Array = null;
        samples_p_neg_neg_h_e = new Vector();
        p_neg_neg_h_e = 0.0F;
        p_neg_neg_h_e_Array = null;
        samples_p_pos_neg_h_e = new Vector();
        p_pos_neg_h_e = 0.0F;
        p_pos_neg_h_e_Array = null;
        samples_p_neg_pos_h_e = new Vector();
        p_neg_pos_h_e = 0.0F;
        p_neg_pos_h_e_Array = null;
        samples_p_blank_h_e = new Vector();
        p_blank_h_e = 0.0F;
        p_blank_h_e_Array = null;
        samples_p_h_e_allInstitutes = new Vector();
        p_h_e_allInstitutes = 0.0F;
        p_h_e_allInstitutes_Array = null;
        samples_p_h_e_ucsf = new Vector();
        p_h_e_ucsf = 0.0F;
        p_h_e_ucsf_Array = null;
        samples_p_h_e_unc = new Vector();
        p_h_e_unc = 0.0F;
        p_h_e_unc_Array = null;
        samples_p_h_e_upenn = new Vector();
        p_h_e_upenn = 0.0F;
        p_h_e_upenn_Array = null;
        samples_p_h_e_uc = new Vector();
        p_h_e_uc = 0.0F;
        p_h_e_uc_Array = null;
        samples_p_h_e_uab = new Vector();
        p_h_e_uab = 0.0F;
        p_h_e_uab_Array = null;
        samples_p_h_e_mskcc = new Vector();
        p_h_e_mskcc = 0.0F;
        p_h_e_mskcc_Array = null;
        samples_p_h_e_gw = new Vector();
        p_h_e_gw = 0.0F;
        p_h_e_gw_Array = null;
        samples_p_h_e_uw = new Vector();
        p_h_e_uw = 0.0F;
        p_h_e_uw_Array = null;
        samples_p_h_e_ut = new Vector();
        p_h_e_ut = 0.0F;
        p_h_e_ut_Array = null;
        samples_p_h_e_not_null = new Vector();
        samples_p_pos_pos_h_e_allInstitutes = new Vector();
        p_pos_pos_h_e_allInstitutes = 0.0F;
        p_pos_pos_h_e_allInstitutes_Array = null;
        samples_p_pos_pos_h_e_ucsf = new Vector();
        p_pos_pos_h_e_ucsf = 0.0F;
        p_pos_pos_h_e_ucsf_Array = null;
        samples_p_pos_pos_h_e_unc = new Vector();
        p_pos_pos_h_e_unc = 0.0F;
        p_pos_pos_h_e_unc_Array = null;
        samples_p_pos_pos_h_e_upenn = new Vector();
        p_pos_pos_h_e_upenn = 0.0F;
        p_pos_pos_h_e_upenn_Array = null;
        samples_p_pos_pos_h_e_uc = new Vector();
        p_pos_pos_h_e_uc = 0.0F;
        p_pos_pos_h_e_uc_Array = null;
        samples_p_pos_pos_h_e_uab = new Vector();
        p_pos_pos_h_e_uab = 0.0F;
        p_pos_pos_h_e_uab_Array = null;
        samples_p_pos_pos_h_e_mskcc = new Vector();
        p_pos_pos_h_e_mskcc = 0.0F;
        p_pos_pos_h_e_mskcc_Array = null;
        samples_p_pos_pos_h_e_gw = new Vector();
        p_pos_pos_h_e_gw = 0.0F;
        p_pos_pos_h_e_gw_Array = null;
        samples_p_pos_pos_h_e_uw = new Vector();
        p_pos_pos_h_e_uw = 0.0F;
        p_pos_pos_h_e_uw_Array = null;
        samples_p_pos_pos_h_e_ut = new Vector();
        p_pos_pos_h_e_ut = 0.0F;
        p_pos_pos_h_e_ut_Array = null;
        samples_p_neg_neg_h_e_allInstitutes = new Vector();
        p_neg_neg_h_e_allInstitutes = 0.0F;
        p_neg_neg_h_e_allInstitutes_Array = null;
        samples_p_neg_neg_h_e_ucsf = new Vector();
        p_neg_neg_h_e_ucsf = 0.0F;
        p_neg_neg_h_e_ucsf_Array = null;
        samples_p_neg_neg_h_e_unc = new Vector();
        p_neg_neg_h_e_unc = 0.0F;
        p_neg_neg_h_e_unc_Array = null;
        samples_p_neg_neg_h_e_upenn = new Vector();
        p_neg_neg_h_e_upenn = 0.0F;
        p_neg_neg_h_e_upenn_Array = null;
        samples_p_neg_neg_h_e_uc = new Vector();
        p_neg_neg_h_e_uc = 0.0F;
        p_neg_neg_h_e_uc_Array = null;
        samples_p_neg_neg_h_e_uab = new Vector();
        p_neg_neg_h_e_uab = 0.0F;
        p_neg_neg_h_e_uab_Array = null;
        samples_p_neg_neg_h_e_mskcc = new Vector();
        p_neg_neg_h_e_mskcc = 0.0F;
        p_neg_neg_h_e_mskcc_Array = null;
        samples_p_neg_neg_h_e_gw = new Vector();
        p_neg_neg_h_e_gw = 0.0F;
        p_neg_neg_h_e_gw_Array = null;
        samples_p_neg_neg_h_e_uw = new Vector();
        p_neg_neg_h_e_uw = 0.0F;
        p_neg_neg_h_e_uw_Array = null;
        samples_p_neg_neg_h_e_ut = new Vector();
        p_neg_neg_h_e_ut = 0.0F;
        p_neg_neg_h_e_ut_Array = null;
        samples_p_pos_neg_h_e_allInstitutes = new Vector();
        p_pos_neg_h_e_allInstitutes = 0.0F;
        p_pos_neg_h_e_allInstitutes_Array = null;
        samples_p_pos_neg_h_e_ucsf = new Vector();
        p_pos_neg_h_e_ucsf = 0.0F;
        p_pos_neg_h_e_ucsf_Array = null;
        samples_p_pos_neg_h_e_unc = new Vector();
        p_pos_neg_h_e_unc = 0.0F;
        p_pos_neg_h_e_unc_Array = null;
        samples_p_pos_neg_h_e_upenn = new Vector();
        p_pos_neg_h_e_upenn = 0.0F;
        p_pos_neg_h_e_upenn_Array = null;
        samples_p_pos_neg_h_e_uc = new Vector();
        p_pos_neg_h_e_uc = 0.0F;
        p_pos_neg_h_e_uc_Array = null;
        samples_p_pos_neg_h_e_uab = new Vector();
        p_pos_neg_h_e_uab = 0.0F;
        p_pos_neg_h_e_uab_Array = null;
        samples_p_pos_neg_h_e_mskcc = new Vector();
        p_pos_neg_h_e_mskcc = 0.0F;
        p_pos_neg_h_e_mskcc_Array = null;
        samples_p_pos_neg_h_e_gw = new Vector();
        p_pos_neg_h_e_gw = 0.0F;
        p_pos_neg_h_e_gw_Array = null;
        samples_p_pos_neg_h_e_uw = new Vector();
        p_pos_neg_h_e_uw = 0.0F;
        p_pos_neg_h_e_uw_Array = null;
        samples_p_pos_neg_h_e_ut = new Vector();
        p_pos_neg_h_e_ut = 0.0F;
        p_pos_neg_h_e_ut_Array = null;
        samples_p_neg_pos_h_e_allInstitutes = new Vector();
        p_neg_pos_h_e_allInstitutes = 0.0F;
        p_neg_pos_h_e_allInstitutes_Array = null;
        samples_p_neg_pos_h_e_ucsf = new Vector();
        p_neg_pos_h_e_ucsf = 0.0F;
        p_neg_pos_h_e_ucsf_Array = null;
        samples_p_neg_pos_h_e_unc = new Vector();
        p_neg_pos_h_e_unc = 0.0F;
        p_neg_pos_h_e_unc_Array = null;
        samples_p_neg_pos_h_e_upenn = new Vector();
        p_neg_pos_h_e_upenn = 0.0F;
        p_neg_pos_h_e_upenn_Array = null;
        samples_p_neg_pos_h_e_uc = new Vector();
        p_neg_pos_h_e_uc = 0.0F;
        p_neg_pos_h_e_uc_Array = null;
        samples_p_neg_pos_h_e_uab = new Vector();
        p_neg_pos_h_e_uab = 0.0F;
        p_neg_pos_h_e_uab_Array = null;
        samples_p_neg_pos_h_e_mskcc = new Vector();
        p_neg_pos_h_e_mskcc = 0.0F;
        p_neg_pos_h_e_mskcc_Array = null;
        samples_p_neg_pos_h_e_gw = new Vector();
        p_neg_pos_h_e_gw = 0.0F;
        p_neg_pos_h_e_gw_Array = null;
        samples_p_neg_pos_h_e_uw = new Vector();
        p_neg_pos_h_e_uw = 0.0F;
        p_neg_pos_h_e_uw_Array = null;
        samples_p_neg_pos_h_e_ut = new Vector();
        p_neg_pos_h_e_ut = 0.0F;
        p_neg_pos_h_e_ut_Array = null;
        samples_p_blank_h_e_allInstitutes = new Vector();
        p_blank_h_e_allInstitutes = 0.0F;
        p_blank_h_e_allInstitutes_Array = null;
        samples_p_blank_h_e_ucsf = new Vector();
        p_blank_h_e_ucsf = 0.0F;
        p_blank_h_e_ucsf_Array = null;
        samples_p_blank_h_e_unc = new Vector();
        p_blank_h_e_unc = 0.0F;
        p_blank_h_e_unc_Array = null;
        samples_p_blank_h_e_upenn = new Vector();
        p_blank_h_e_upenn = 0.0F;
        p_blank_h_e_upenn_Array = null;
        samples_p_blank_h_e_uc = new Vector();
        p_blank_h_e_uc = 0.0F;
        p_blank_h_e_uc_Array = null;
        samples_p_blank_h_e_uab = new Vector();
        p_blank_h_e_uab = 0.0F;
        p_blank_h_e_uab_Array = null;
        samples_p_blank_h_e_mskcc = new Vector();
        p_blank_h_e_mskcc = 0.0F;
        p_blank_h_e_mskcc_Array = null;
        samples_p_blank_h_e_gw = new Vector();
        p_blank_h_e_gw = 0.0F;
        p_blank_h_e_gw_Array = null;
        samples_p_blank_h_e_uw = new Vector();
        p_blank_h_e_uw = 0.0F;
        p_blank_h_e_uw_Array = null;
        samples_p_blank_h_e_ut = new Vector();
        p_blank_h_e_ut = 0.0F;
        p_blank_h_e_ut_Array = null;
        samples_p_h_e_alltps = new Vector();
        p_h_e_alltps = 0.0F;
        p_h_e_alltps_Array = null;
        samples_p_h_e_p1 = new Vector();
        p_h_e_p1 = 0.0F;
        p_h_e_p1_Array = null;
        samples_p_h_e_p2 = new Vector();
        p_h_e_p2 = 0.0F;
        p_h_e_p2_Array = null;
        samples_p_h_e_p3 = new Vector();
        p_h_e_p3 = 0.0F;
        p_h_e_p3_Array = null;
        samples_p_h_e_p4 = new Vector();
        p_h_e_p4 = 0.0F;
        p_h_e_p4_Array = null;
        samples_p_h_e_p5 = new Vector();
        p_h_e_p5 = 0.0F;
        p_h_e_p5_Array = null;
        samples_p_h_e_p6 = new Vector();
        p_h_e_p6 = 0.0F;
        p_h_e_p6_Array = null;
        samples_p_h_e_ps = new Vector();
        p_h_e_ps = 0.0F;
        p_h_e_ps_Array = null;
        samples_p_pos_pos_h_e_alltps = new Vector();
        p_pos_pos_h_e_alltps = 0.0F;
        p_pos_pos_h_e_alltps_Array = null;
        samples_p_pos_pos_h_e_p1 = new Vector();
        p_pos_pos_h_e_p1 = 0.0F;
        p_pos_pos_h_e_p1_Array = null;
        samples_p_pos_pos_h_e_p2 = new Vector();
        p_pos_pos_h_e_p2 = 0.0F;
        p_pos_pos_h_e_p2_Array = null;
        samples_p_pos_pos_h_e_p3 = new Vector();
        p_pos_pos_h_e_p3 = 0.0F;
        p_pos_pos_h_e_p3_Array = null;
        samples_p_pos_pos_h_e_p4 = new Vector();
        p_pos_pos_h_e_p4 = 0.0F;
        p_pos_pos_h_e_p4_Array = null;
        samples_p_pos_pos_h_e_p5 = new Vector();
        p_pos_pos_h_e_p5 = 0.0F;
        p_pos_pos_h_e_p5_Array = null;
        samples_p_pos_pos_h_e_p6 = new Vector();
        p_pos_pos_h_e_p6 = 0.0F;
        p_pos_pos_h_e_p6_Array = null;
        samples_p_pos_pos_h_e_ps = new Vector();
        p_pos_pos_h_e_ps = 0.0F;
        p_pos_pos_h_e_ps_Array = null;
        samples_p_neg_neg_h_e_alltps = new Vector();
        p_neg_neg_h_e_alltps = 0.0F;
        p_neg_neg_h_e_alltps_Array = null;
        samples_p_neg_neg_h_e_p1 = new Vector();
        p_neg_neg_h_e_p1 = 0.0F;
        p_neg_neg_h_e_p1_Array = null;
        samples_p_neg_neg_h_e_p2 = new Vector();
        p_neg_neg_h_e_p2 = 0.0F;
        p_neg_neg_h_e_p2_Array = null;
        samples_p_neg_neg_h_e_p3 = new Vector();
        p_neg_neg_h_e_p3 = 0.0F;
        p_neg_neg_h_e_p3_Array = null;
        samples_p_neg_neg_h_e_p4 = new Vector();
        p_neg_neg_h_e_p4 = 0.0F;
        p_neg_neg_h_e_p4_Array = null;
        samples_p_neg_neg_h_e_p5 = new Vector();
        p_neg_neg_h_e_p5 = 0.0F;
        p_neg_neg_h_e_p5_Array = null;
        samples_p_neg_neg_h_e_p6 = new Vector();
        p_neg_neg_h_e_p6 = 0.0F;
        p_neg_neg_h_e_p6_Array = null;
        samples_p_neg_neg_h_e_ps = new Vector();
        p_neg_neg_h_e_ps = 0.0F;
        p_neg_neg_h_e_ps_Array = null;
        samples_p_pos_neg_h_e_alltps = new Vector();
        p_pos_neg_h_e_alltps = 0.0F;
        p_pos_neg_h_e_alltps_Array = null;
        samples_p_pos_neg_h_e_p1 = new Vector();
        p_pos_neg_h_e_p1 = 0.0F;
        p_pos_neg_h_e_p1_Array = null;
        samples_p_pos_neg_h_e_p2 = new Vector();
        p_pos_neg_h_e_p2 = 0.0F;
        p_pos_neg_h_e_p2_Array = null;
        samples_p_pos_neg_h_e_p3 = new Vector();
        p_pos_neg_h_e_p3 = 0.0F;
        p_pos_neg_h_e_p3_Array = null;
        samples_p_pos_neg_h_e_p4 = new Vector();
        p_pos_neg_h_e_p4 = 0.0F;
        p_pos_neg_h_e_p4_Array = null;
        samples_p_pos_neg_h_e_p5 = new Vector();
        p_pos_neg_h_e_p5 = 0.0F;
        p_pos_neg_h_e_p5_Array = null;
        samples_p_pos_neg_h_e_p6 = new Vector();
        p_pos_neg_h_e_p6 = 0.0F;
        p_pos_neg_h_e_p6_Array = null;
        samples_p_pos_neg_h_e_ps = new Vector();
        p_pos_neg_h_e_ps = 0.0F;
        p_pos_neg_h_e_ps_Array = null;
        samples_p_neg_pos_h_e_alltps = new Vector();
        p_neg_pos_h_e_alltps = 0.0F;
        p_neg_pos_h_e_alltps_Array = null;
        samples_p_neg_pos_h_e_p1 = new Vector();
        p_neg_pos_h_e_p1 = 0.0F;
        p_neg_pos_h_e_p1_Array = null;
        samples_p_neg_pos_h_e_p2 = new Vector();
        p_neg_pos_h_e_p2 = 0.0F;
        p_neg_pos_h_e_p2_Array = null;
        samples_p_neg_pos_h_e_p3 = new Vector();
        p_neg_pos_h_e_p3 = 0.0F;
        p_neg_pos_h_e_p3_Array = null;
        samples_p_neg_pos_h_e_p4 = new Vector();
        p_neg_pos_h_e_p4 = 0.0F;
        p_neg_pos_h_e_p4_Array = null;
        samples_p_neg_pos_h_e_p5 = new Vector();
        p_neg_pos_h_e_p5 = 0.0F;
        p_neg_pos_h_e_p5_Array = null;
        samples_p_neg_pos_h_e_p6 = new Vector();
        p_neg_pos_h_e_p6 = 0.0F;
        p_neg_pos_h_e_p6_Array = null;
        samples_p_neg_pos_h_e_ps = new Vector();
        p_neg_pos_h_e_ps = 0.0F;
        p_neg_pos_h_e_ps_Array = null;
        samples_p_blank_h_e_alltps = new Vector();
        p_blank_h_e_alltps = 0.0F;
        p_blank_h_e_alltps_Array = null;
        samples_p_blank_h_e_p1 = new Vector();
        p_blank_h_e_p1 = 0.0F;
        p_blank_h_e_p1_Array = null;
        samples_p_blank_h_e_p2 = new Vector();
        p_blank_h_e_p2 = 0.0F;
        p_blank_h_e_p2_Array = null;
        samples_p_blank_h_e_p3 = new Vector();
        p_blank_h_e_p3 = 0.0F;
        p_blank_h_e_p3_Array = null;
        samples_p_blank_h_e_p4 = new Vector();
        p_blank_h_e_p4 = 0.0F;
        p_blank_h_e_p4_Array = null;
        samples_p_blank_h_e_p5 = new Vector();
        p_blank_h_e_p5 = 0.0F;
        p_blank_h_e_p5_Array = null;
        samples_p_blank_h_e_p6 = new Vector();
        p_blank_h_e_p6 = 0.0F;
        p_blank_h_e_p6_Array = null;
        samples_p_blank_h_e_ps = new Vector();
        p_blank_h_e_ps = 0.0F;
        p_blank_h_e_ps_Array = null;
        samples_p_usability = new Vector();
        p_usability = 0.0F;
        p_usability_Array = null;
        samples_p_yes_usability = new Vector();
        p_yes_usability = 0.0F;
        p_yes_usability_Array = null;
        samples_p_no_usability = new Vector();
        p_no_usability = 0.0F;
        p_no_usability_Array = null;
        samples_p_blank_usability = new Vector();
        p_blank_usability = 0.0F;
        p_blank_usability_Array = null;
        samples_p_usability_allInstitutes = new Vector();
        p_usability_allInstitutes = 0.0F;
        p_usability_allInstitutes_Array = null;
        samples_p_usability_ucsf = new Vector();
        p_usability_ucsf = 0.0F;
        p_usability_ucsf_Array = null;
        samples_p_usability_unc = new Vector();
        p_usability_unc = 0.0F;
        p_usability_unc_Array = null;
        samples_p_usability_upenn = new Vector();
        p_usability_upenn = 0.0F;
        p_usability_upenn_Array = null;
        samples_p_usability_uc = new Vector();
        p_usability_uc = 0.0F;
        p_usability_uc_Array = null;
        samples_p_usability_uab = new Vector();
        p_usability_uab = 0.0F;
        p_usability_uab_Array = null;
        samples_p_usability_mskcc = new Vector();
        p_usability_mskcc = 0.0F;
        p_usability_mskcc_Array = null;
        samples_p_usability_gw = new Vector();
        p_usability_gw = 0.0F;
        p_usability_gw_Array = null;
        samples_p_usability_uw = new Vector();
        p_usability_uw = 0.0F;
        p_usability_uw_Array = null;
        samples_p_usability_ut = new Vector();
        p_usability_ut = 0.0F;
        p_usability_ut_Array = null;
        samples_p_yes_usability_allInstitutes = new Vector();
        p_yes_usability_allInstitutes = 0.0F;
        p_yes_usability_allInstitutes_Array = null;
        samples_p_yes_usability_ucsf = new Vector();
        p_yes_usability_ucsf = 0.0F;
        p_yes_usability_ucsf_Array = null;
        samples_p_yes_usability_unc = new Vector();
        p_yes_usability_unc = 0.0F;
        p_yes_usability_unc_Array = null;
        samples_p_yes_usability_upenn = new Vector();
        p_yes_usability_upenn = 0.0F;
        p_yes_usability_upenn_Array = null;
        samples_p_yes_usability_uc = new Vector();
        p_yes_usability_uc = 0.0F;
        p_yes_usability_uc_Array = null;
        samples_p_yes_usability_uab = new Vector();
        p_yes_usability_uab = 0.0F;
        p_yes_usability_uab_Array = null;
        samples_p_yes_usability_mskcc = new Vector();
        p_yes_usability_mskcc = 0.0F;
        p_yes_usability_mskcc_Array = null;
        samples_p_yes_usability_gw = new Vector();
        p_yes_usability_gw = 0.0F;
        p_yes_usability_gw_Array = null;
        samples_p_yes_usability_uw = new Vector();
        p_yes_usability_uw = 0.0F;
        p_yes_usability_uw_Array = null;
        samples_p_yes_usability_ut = new Vector();
        p_yes_usability_ut = 0.0F;
        p_yes_usability_ut_Array = null;
        samples_p_no_usability_allInstitutes = new Vector();
        p_no_usability_allInstitutes = 0.0F;
        p_no_usability_allInstitutes_Array = null;
        samples_p_no_usability_ucsf = new Vector();
        p_no_usability_ucsf = 0.0F;
        p_no_usability_ucsf_Array = null;
        samples_p_no_usability_unc = new Vector();
        p_no_usability_unc = 0.0F;
        p_no_usability_unc_Array = null;
        samples_p_no_usability_upenn = new Vector();
        p_no_usability_upenn = 0.0F;
        p_no_usability_upenn_Array = null;
        samples_p_no_usability_uc = new Vector();
        p_no_usability_uc = 0.0F;
        p_no_usability_uc_Array = null;
        samples_p_no_usability_uab = new Vector();
        p_no_usability_uab = 0.0F;
        p_no_usability_uab_Array = null;
        samples_p_no_usability_mskcc = new Vector();
        p_no_usability_mskcc = 0.0F;
        p_no_usability_mskcc_Array = null;
        samples_p_no_usability_gw = new Vector();
        p_no_usability_gw = 0.0F;
        p_no_usability_gw_Array = null;
        samples_p_no_usability_uw = new Vector();
        p_no_usability_uw = 0.0F;
        p_no_usability_uw_Array = null;
        samples_p_no_usability_ut = new Vector();
        p_no_usability_ut = 0.0F;
        p_no_usability_ut_Array = null;
        samples_p_blank_usability_allInstitutes = new Vector();
        p_blank_usability_allInstitutes = 0.0F;
        p_blank_usability_allInstitutes_Array = null;
        samples_p_blank_usability_ucsf = new Vector();
        p_blank_usability_ucsf = 0.0F;
        p_blank_usability_ucsf_Array = null;
        samples_p_blank_usability_unc = new Vector();
        p_blank_usability_unc = 0.0F;
        p_blank_usability_unc_Array = null;
        samples_p_blank_usability_upenn = new Vector();
        p_blank_usability_upenn = 0.0F;
        p_blank_usability_upenn_Array = null;
        samples_p_blank_usability_uc = new Vector();
        p_blank_usability_uc = 0.0F;
        p_blank_usability_uc_Array = null;
        samples_p_blank_usability_uab = new Vector();
        p_blank_usability_uab = 0.0F;
        p_blank_usability_uab_Array = null;
        samples_p_blank_usability_mskcc = new Vector();
        p_blank_usability_mskcc = 0.0F;
        p_blank_usability_mskcc_Array = null;
        samples_p_blank_usability_gw = new Vector();
        p_blank_usability_gw = 0.0F;
        p_blank_usability_gw_Array = null;
        samples_p_blank_usability_uw = new Vector();
        p_blank_usability_uw = 0.0F;
        p_blank_usability_uw_Array = null;
        samples_p_blank_usability_ut = new Vector();
        p_blank_usability_ut = 0.0F;
        p_blank_usability_ut_Array = null;
        samples_p_usability_alltps = new Vector();
        p_usability_alltps = 0.0F;
        p_usability_alltps_Array = null;
        samples_p_usability_p1 = new Vector();
        p_usability_p1 = 0.0F;
        p_usability_p1_Array = null;
        samples_p_usability_p2 = new Vector();
        p_usability_p2 = 0.0F;
        p_usability_p2_Array = null;
        samples_p_usability_p3 = new Vector();
        p_usability_p3 = 0.0F;
        p_usability_p3_Array = null;
        samples_p_usability_p4 = new Vector();
        p_usability_p4 = 0.0F;
        p_usability_p4_Array = null;
        samples_p_usability_p5 = new Vector();
        p_usability_p5 = 0.0F;
        p_usability_p5_Array = null;
        samples_p_usability_p6 = new Vector();
        p_usability_p6 = 0.0F;
        p_usability_p6_Array = null;
        samples_p_usability_ps = new Vector();
        p_usability_ps = 0.0F;
        p_usability_ps_Array = null;
        samples_p_yes_usability_alltps = new Vector();
        p_yes_usability_alltps = 0.0F;
        p_yes_usability_alltps_Array = null;
        samples_p_yes_usability_p1 = new Vector();
        p_yes_usability_p1 = 0.0F;
        p_yes_usability_p1_Array = null;
        samples_p_yes_usability_p2 = new Vector();
        p_yes_usability_p2 = 0.0F;
        p_yes_usability_p2_Array = null;
        samples_p_yes_usability_p3 = new Vector();
        p_yes_usability_p3 = 0.0F;
        p_yes_usability_p3_Array = null;
        samples_p_yes_usability_p4 = new Vector();
        p_yes_usability_p4 = 0.0F;
        p_yes_usability_p4_Array = null;
        samples_p_yes_usability_p5 = new Vector();
        p_yes_usability_p5 = 0.0F;
        p_yes_usability_p5_Array = null;
        samples_p_yes_usability_p6 = new Vector();
        p_yes_usability_p6 = 0.0F;
        p_yes_usability_p6_Array = null;
        samples_p_yes_usability_ps = new Vector();
        p_yes_usability_ps = 0.0F;
        p_yes_usability_ps_Array = null;
        samples_p_no_usability_alltps = new Vector();
        p_no_usability_alltps = 0.0F;
        p_no_usability_alltps_Array = null;
        samples_p_no_usability_p1 = new Vector();
        p_no_usability_p1 = 0.0F;
        p_no_usability_p1_Array = null;
        samples_p_no_usability_p2 = new Vector();
        p_no_usability_p2 = 0.0F;
        p_no_usability_p2_Array = null;
        samples_p_no_usability_p3 = new Vector();
        p_no_usability_p3 = 0.0F;
        p_no_usability_p3_Array = null;
        samples_p_no_usability_p4 = new Vector();
        p_no_usability_p4 = 0.0F;
        p_no_usability_p4_Array = null;
        samples_p_no_usability_p5 = new Vector();
        p_no_usability_p5 = 0.0F;
        p_no_usability_p5_Array = null;
        samples_p_no_usability_p6 = new Vector();
        p_no_usability_p6 = 0.0F;
        p_no_usability_p6_Array = null;
        samples_p_no_usability_ps = new Vector();
        p_no_usability_ps = 0.0F;
        p_no_usability_ps_Array = null;
        samples_p_blank_usability_alltps = new Vector();
        p_blank_usability_alltps = 0.0F;
        p_blank_usability_alltps_Array = null;
        samples_p_blank_usability_p1 = new Vector();
        p_blank_usability_p1 = 0.0F;
        p_blank_usability_p1_Array = null;
        samples_p_blank_usability_p2 = new Vector();
        p_blank_usability_p2 = 0.0F;
        p_blank_usability_p2_Array = null;
        samples_p_blank_usability_p3 = new Vector();
        p_blank_usability_p3 = 0.0F;
        p_blank_usability_p3_Array = null;
        samples_p_blank_usability_p4 = new Vector();
        p_blank_usability_p4 = 0.0F;
        p_blank_usability_p4_Array = null;
        samples_p_blank_usability_p5 = new Vector();
        p_blank_usability_p5 = 0.0F;
        p_blank_usability_p5_Array = null;
        samples_p_blank_usability_p6 = new Vector();
        p_blank_usability_p6 = 0.0F;
        p_blank_usability_p6_Array = null;
        samples_p_blank_usability_ps = new Vector();
        p_blank_usability_ps = 0.0F;
        p_blank_usability_ps_Array = null;
        samples_p_touchPrepRecvd = new Vector();
        p_touchPrepRecvd = 0.0F;
        p_touchPrepRecvd_Array = null;
        samples_p_yes_touchPrepRecvd = new Vector();
        p_yes_touchPrepRecvd = 0.0F;
        p_yes_touchPrepRecvd_Array = null;
        samples_p_no_touchPrepRecvd = new Vector();
        p_no_touchPrepRecvd = 0.0F;
        p_no_touchPrepRecvd_Array = null;
        samples_p_blank_touchPrepRecvd = new Vector();
        p_blank_touchPrepRecvd = 0.0F;
        p_blank_touchPrepRecvd_Array = null;
        samples_p_touchPrepRecvd_allInstitutes = new Vector();
        p_touchPrepRecvd_allInstitutes = 0.0F;
        p_touchPrepRecvd_allInstitutes_Array = null;
        samples_p_touchPrepRecvd_ucsf = new Vector();
        p_touchPrepRecvd_ucsf = 0.0F;
        p_touchPrepRecvd_ucsf_Array = null;
        samples_p_touchPrepRecvd_unc = new Vector();
        p_touchPrepRecvd_unc = 0.0F;
        p_touchPrepRecvd_unc_Array = null;
        samples_p_touchPrepRecvd_upenn = new Vector();
        p_touchPrepRecvd_upenn = 0.0F;
        p_touchPrepRecvd_upenn_Array = null;
        samples_p_touchPrepRecvd_uc = new Vector();
        p_touchPrepRecvd_uc = 0.0F;
        p_touchPrepRecvd_uc_Array = null;
        samples_p_touchPrepRecvd_uab = new Vector();
        p_touchPrepRecvd_uab = 0.0F;
        p_touchPrepRecvd_uab_Array = null;
        samples_p_touchPrepRecvd_mskcc = new Vector();
        p_touchPrepRecvd_mskcc = 0.0F;
        p_touchPrepRecvd_mskcc_Array = null;
        samples_p_touchPrepRecvd_gw = new Vector();
        p_touchPrepRecvd_gw = 0.0F;
        p_touchPrepRecvd_gw_Array = null;
        samples_p_touchPrepRecvd_uw = new Vector();
        p_touchPrepRecvd_uw = 0.0F;
        p_touchPrepRecvd_uw_Array = null;
        samples_p_touchPrepRecvd_ut = new Vector();
        p_touchPrepRecvd_ut = 0.0F;
        p_touchPrepRecvd_ut_Array = null;
        samples_p_yes_touchPrepRecvd_allInstitutes = new Vector();
        p_yes_touchPrepRecvd_allInstitutes = 0.0F;
        p_yes_touchPrepRecvd_allInstitutes_Array = null;
        samples_p_yes_touchPrepRecvd_ucsf = new Vector();
        p_yes_touchPrepRecvd_ucsf = 0.0F;
        p_yes_touchPrepRecvd_ucsf_Array = null;
        samples_p_yes_touchPrepRecvd_unc = new Vector();
        p_yes_touchPrepRecvd_unc = 0.0F;
        p_yes_touchPrepRecvd_unc_Array = null;
        samples_p_yes_touchPrepRecvd_upenn = new Vector();
        p_yes_touchPrepRecvd_upenn = 0.0F;
        p_yes_touchPrepRecvd_upenn_Array = null;
        samples_p_yes_touchPrepRecvd_uc = new Vector();
        p_yes_touchPrepRecvd_uc = 0.0F;
        p_yes_touchPrepRecvd_uc_Array = null;
        samples_p_yes_touchPrepRecvd_uab = new Vector();
        p_yes_touchPrepRecvd_uab = 0.0F;
        p_yes_touchPrepRecvd_uab_Array = null;
        samples_p_yes_touchPrepRecvd_mskcc = new Vector();
        p_yes_touchPrepRecvd_mskcc = 0.0F;
        p_yes_touchPrepRecvd_mskcc_Array = null;
        samples_p_yes_touchPrepRecvd_gw = new Vector();
        p_yes_touchPrepRecvd_gw = 0.0F;
        p_yes_touchPrepRecvd_gw_Array = null;
        samples_p_yes_touchPrepRecvd_uw = new Vector();
        p_yes_touchPrepRecvd_uw = 0.0F;
        p_yes_touchPrepRecvd_uw_Array = null;
        samples_p_yes_touchPrepRecvd_ut = new Vector();
        p_yes_touchPrepRecvd_ut = 0.0F;
        p_yes_touchPrepRecvd_ut_Array = null;
        samples_p_no_touchPrepRecvd_allInstitutes = new Vector();
        p_no_touchPrepRecvd_allInstitutes = 0.0F;
        p_no_touchPrepRecvd_allInstitutes_Array = null;
        samples_p_no_touchPrepRecvd_ucsf = new Vector();
        p_no_touchPrepRecvd_ucsf = 0.0F;
        p_no_touchPrepRecvd_ucsf_Array = null;
        samples_p_no_touchPrepRecvd_unc = new Vector();
        p_no_touchPrepRecvd_unc = 0.0F;
        p_no_touchPrepRecvd_unc_Array = null;
        samples_p_no_touchPrepRecvd_upenn = new Vector();
        p_no_touchPrepRecvd_upenn = 0.0F;
        p_no_touchPrepRecvd_upenn_Array = null;
        samples_p_no_touchPrepRecvd_uc = new Vector();
        p_no_touchPrepRecvd_uc = 0.0F;
        p_no_touchPrepRecvd_uc_Array = null;
        samples_p_no_touchPrepRecvd_uab = new Vector();
        p_no_touchPrepRecvd_uab = 0.0F;
        p_no_touchPrepRecvd_uab_Array = null;
        samples_p_no_touchPrepRecvd_mskcc = new Vector();
        p_no_touchPrepRecvd_mskcc = 0.0F;
        p_no_touchPrepRecvd_mskcc_Array = null;
        samples_p_no_touchPrepRecvd_gw = new Vector();
        p_no_touchPrepRecvd_gw = 0.0F;
        p_no_touchPrepRecvd_gw_Array = null;
        samples_p_no_touchPrepRecvd_uw = new Vector();
        p_no_touchPrepRecvd_uw = 0.0F;
        p_no_touchPrepRecvd_uw_Array = null;
        samples_p_no_touchPrepRecvd_ut = new Vector();
        p_no_touchPrepRecvd_ut = 0.0F;
        p_no_touchPrepRecvd_ut_Array = null;
        samples_p_blank_touchPrepRecvd_allInstitutes = new Vector();
        p_blank_touchPrepRecvd_allInstitutes = 0.0F;
        p_blank_touchPrepRecvd_allInstitutes_Array = null;
        samples_p_blank_touchPrepRecvd_ucsf = new Vector();
        p_blank_touchPrepRecvd_ucsf = 0.0F;
        p_blank_touchPrepRecvd_ucsf_Array = null;
        samples_p_blank_touchPrepRecvd_unc = new Vector();
        p_blank_touchPrepRecvd_unc = 0.0F;
        p_blank_touchPrepRecvd_unc_Array = null;
        samples_p_blank_touchPrepRecvd_upenn = new Vector();
        p_blank_touchPrepRecvd_upenn = 0.0F;
        p_blank_touchPrepRecvd_upenn_Array = null;
        samples_p_blank_touchPrepRecvd_uc = new Vector();
        p_blank_touchPrepRecvd_uc = 0.0F;
        p_blank_touchPrepRecvd_uc_Array = null;
        samples_p_blank_touchPrepRecvd_uab = new Vector();
        p_blank_touchPrepRecvd_uab = 0.0F;
        p_blank_touchPrepRecvd_uab_Array = null;
        samples_p_blank_touchPrepRecvd_mskcc = new Vector();
        p_blank_touchPrepRecvd_mskcc = 0.0F;
        p_blank_touchPrepRecvd_mskcc_Array = null;
        samples_p_blank_touchPrepRecvd_gw = new Vector();
        p_blank_touchPrepRecvd_gw = 0.0F;
        p_blank_touchPrepRecvd_gw_Array = null;
        samples_p_blank_touchPrepRecvd_uw = new Vector();
        p_blank_touchPrepRecvd_uw = 0.0F;
        p_blank_touchPrepRecvd_uw_Array = null;
        samples_p_blank_touchPrepRecvd_ut = new Vector();
        p_blank_touchPrepRecvd_ut = 0.0F;
        p_blank_touchPrepRecvd_ut_Array = null;
        samples_p_touchPrepRecvd_alltps = new Vector();
        p_touchPrepRecvd_alltps = 0.0F;
        p_touchPrepRecvd_alltps_Array = null;
        samples_p_touchPrepRecvd_p1 = new Vector();
        p_touchPrepRecvd_p1 = 0.0F;
        p_touchPrepRecvd_p1_Array = null;
        samples_p_touchPrepRecvd_p2 = new Vector();
        p_touchPrepRecvd_p2 = 0.0F;
        p_touchPrepRecvd_p2_Array = null;
        samples_p_touchPrepRecvd_p3 = new Vector();
        p_touchPrepRecvd_p3 = 0.0F;
        p_touchPrepRecvd_p3_Array = null;
        samples_p_touchPrepRecvd_p4 = new Vector();
        p_touchPrepRecvd_p4 = 0.0F;
        p_touchPrepRecvd_p4_Array = null;
        samples_p_touchPrepRecvd_p5 = new Vector();
        p_touchPrepRecvd_p5 = 0.0F;
        p_touchPrepRecvd_p5_Array = null;
        samples_p_touchPrepRecvd_p6 = new Vector();
        p_touchPrepRecvd_p6 = 0.0F;
        p_touchPrepRecvd_p6_Array = null;
        samples_p_touchPrepRecvd_ps = new Vector();
        p_touchPrepRecvd_ps = 0.0F;
        p_touchPrepRecvd_ps_Array = null;
        samples_p_yes_touchPrepRecvd_alltps = new Vector();
        p_yes_touchPrepRecvd_alltps = 0.0F;
        p_yes_touchPrepRecvd_alltps_Array = null;
        samples_p_yes_touchPrepRecvd_p1 = new Vector();
        p_yes_touchPrepRecvd_p1 = 0.0F;
        p_yes_touchPrepRecvd_p1_Array = null;
        samples_p_yes_touchPrepRecvd_p2 = new Vector();
        p_yes_touchPrepRecvd_p2 = 0.0F;
        p_yes_touchPrepRecvd_p2_Array = null;
        samples_p_yes_touchPrepRecvd_p3 = new Vector();
        p_yes_touchPrepRecvd_p3 = 0.0F;
        p_yes_touchPrepRecvd_p3_Array = null;
        samples_p_yes_touchPrepRecvd_p4 = new Vector();
        p_yes_touchPrepRecvd_p4 = 0.0F;
        p_yes_touchPrepRecvd_p4_Array = null;
        samples_p_yes_touchPrepRecvd_p5 = new Vector();
        p_yes_touchPrepRecvd_p5 = 0.0F;
        p_yes_touchPrepRecvd_p5_Array = null;
        samples_p_yes_touchPrepRecvd_p6 = new Vector();
        p_yes_touchPrepRecvd_p6 = 0.0F;
        p_yes_touchPrepRecvd_p6_Array = null;
        samples_p_yes_touchPrepRecvd_ps = new Vector();
        p_yes_touchPrepRecvd_ps = 0.0F;
        p_yes_touchPrepRecvd_ps_Array = null;
        samples_p_no_touchPrepRecvd_alltps = new Vector();
        p_no_touchPrepRecvd_alltps = 0.0F;
        p_no_touchPrepRecvd_alltps_Array = null;
        samples_p_no_touchPrepRecvd_p1 = new Vector();
        p_no_touchPrepRecvd_p1 = 0.0F;
        p_no_touchPrepRecvd_p1_Array = null;
        samples_p_no_touchPrepRecvd_p2 = new Vector();
        p_no_touchPrepRecvd_p2 = 0.0F;
        p_no_touchPrepRecvd_p2_Array = null;
        samples_p_no_touchPrepRecvd_p3 = new Vector();
        p_no_touchPrepRecvd_p3 = 0.0F;
        p_no_touchPrepRecvd_p3_Array = null;
        samples_p_no_touchPrepRecvd_p4 = new Vector();
        p_no_touchPrepRecvd_p4 = 0.0F;
        p_no_touchPrepRecvd_p4_Array = null;
        samples_p_no_touchPrepRecvd_p5 = new Vector();
        p_no_touchPrepRecvd_p5 = 0.0F;
        p_no_touchPrepRecvd_p5_Array = null;
        samples_p_no_touchPrepRecvd_p6 = new Vector();
        p_no_touchPrepRecvd_p6 = 0.0F;
        p_no_touchPrepRecvd_p6_Array = null;
        samples_p_no_touchPrepRecvd_ps = new Vector();
        p_no_touchPrepRecvd_ps = 0.0F;
        p_no_touchPrepRecvd_ps_Array = null;
        samples_p_blank_touchPrepRecvd_alltps = new Vector();
        p_blank_touchPrepRecvd_alltps = 0.0F;
        p_blank_touchPrepRecvd_alltps_Array = null;
        samples_p_blank_touchPrepRecvd_p1 = new Vector();
        p_blank_touchPrepRecvd_p1 = 0.0F;
        p_blank_touchPrepRecvd_p1_Array = null;
        samples_p_blank_touchPrepRecvd_p2 = new Vector();
        p_blank_touchPrepRecvd_p2 = 0.0F;
        p_blank_touchPrepRecvd_p2_Array = null;
        samples_p_blank_touchPrepRecvd_p3 = new Vector();
        p_blank_touchPrepRecvd_p3 = 0.0F;
        p_blank_touchPrepRecvd_p3_Array = null;
        samples_p_blank_touchPrepRecvd_p4 = new Vector();
        p_blank_touchPrepRecvd_p4 = 0.0F;
        p_blank_touchPrepRecvd_p4_Array = null;
        samples_p_blank_touchPrepRecvd_p5 = new Vector();
        p_blank_touchPrepRecvd_p5 = 0.0F;
        p_blank_touchPrepRecvd_p5_Array = null;
        samples_p_blank_touchPrepRecvd_p6 = new Vector();
        p_blank_touchPrepRecvd_p6 = 0.0F;
        p_blank_touchPrepRecvd_p6_Array = null;
        samples_p_blank_touchPrepRecvd_ps = new Vector();
        p_blank_touchPrepRecvd_ps = 0.0F;
        p_blank_touchPrepRecvd_ps_Array = null;
        samples_dna = new Vector();
        samples_no_dna = new Vector();
        samples_good_dna = new Vector();
        samples_fair_dna = new Vector();
        samples_poor_dna = new Vector();
        samples_good_dna_ratio = 0.0F;
        samples_fair_dna_ratio = 0.0F;
        samples_poor_dna_ratio = 0.0F;
        good_dna_ratio_Array = null;
        fair_dna_ratio_Array = null;
        poor_dna_ratio_Array = null;
        samples_rna = new Vector();
        samples_no_rna = new Vector();
        samples_good_rna = new Vector();
        samples_fair_rna = new Vector();
        samples_poor_rna = new Vector();
        samples_good_rna_ratio = 0.0F;
        samples_fair_rna_ratio = 0.0F;
        samples_poor_rna_ratio = 0.0F;
        good_rna_ratio_Array = null;
        fair_rna_ratio_Array = null;
        poor_rna_ratio_Array = null;
        dna = new Qi_dna();
        subsamples_pcr = new Vector();
        subsamples_no_pcr = new Vector();
        subsamples_best_pcr = new Vector();
        subsamples_good_pcr = new Vector();
        subsamples_poor_pcr = new Vector();
        subsamples_worst_pcr = new Vector();
        subsamples_best_pcr_ratio = 0.0F;
        subsamples_good_pcr_ratio = 0.0F;
        subsamples_poor_pcr_ratio = 0.0F;
        subsamples_worst_pcr_ratio = 0.0F;
        subsamples_best_pcr_ratio_Array = null;
        subsamples_good_pcr_ratio_Array = null;
        subsamples_poor_pcr_ratio_Array = null;
        subsamples_worst_pcr_ratio_Array = null;
        rna = new Qi_rna();
        subsamples_rna = new Vector();
        subsamples_no_rna = new Vector();
        subsamples_good_rna = new Vector();
        subsamples_fair_rna = new Vector();
        subsamples_poor_rna = new Vector();
        subsamples_good_rna_ratio = 0.0F;
        subsamples_fair_rna_ratio = 0.0F;
        subsamples_poor_rna_ratio = 0.0F;
        subsamples_good_rna_ratio_Array = null;
        subsamples_fair_rna_ratio_Array = null;
        subsamples_poor_rna_ratio_Array = null;
        protein = new Qi_protein();
        proteins = new Vector();
        protein_tumor = new Vector();
        protein_no_tumor = new Vector();
        ptNumArray = null;
        touchPrep_f_Array = null;
        touchPrep_f_pos_Array = null;
        touchPrep_f_neg_Array = null;
        touchPrep_f_pos_neg_Array = null;
        touch_prep_allInstitutes_Array = null;
        touch_prep_ucsf_Array = null;
        touch_prep_unc_Array = null;
        touch_prep_upenn_Array = null;
        touch_prep_uc_Array = null;
        touch_prep_uab_Array = null;
        touch_prep_mskcc_Array = null;
        touch_prep_gw_Array = null;
        touch_prep_uw_Array = null;
        touch_prep_ut_Array = null;
        pos_touch_prep_allInstitutes_Array = null;
        pos_touch_prep_ucsf_Array = null;
        pos_touch_prep_unc_Array = null;
        pos_touch_prep_upenn_Array = null;
        pos_touch_prep_uc_Array = null;
        pos_touch_prep_uab_Array = null;
        pos_touch_prep_mskcc_Array = null;
        pos_touch_prep_gw_Array = null;
        pos_touch_prep_uw_Array = null;
        pos_touch_prep_ut_Array = null;
        pos_neg_touch_prep_allInstitutes_Array = null;
        pos_neg_touch_prep_ucsf_Array = null;
        pos_neg_touch_prep_unc_Array = null;
        pos_neg_touch_prep_upenn_Array = null;
        pos_neg_touch_prep_uc_Array = null;
        pos_neg_touch_prep_uab_Array = null;
        pos_neg_touch_prep_mskcc_Array = null;
        pos_neg_touch_prep_gw_Array = null;
        pos_neg_touch_prep_uw_Array = null;
        pos_neg_touch_prep_ut_Array = null;
        neg_touch_prep_allInstitutes_Array = null;
        neg_touch_prep_ucsf_Array = null;
        neg_touch_prep_unc_Array = null;
        neg_touch_prep_upenn_Array = null;
        neg_touch_prep_uc_Array = null;
        neg_touch_prep_uab_Array = null;
        neg_touch_prep_mskcc_Array = null;
        neg_touch_prep_gw_Array = null;
        neg_touch_prep_uw_Array = null;
        neg_touch_prep_ut_Array = null;
        touch_prep_alltps_Array = null;
        touch_prep_f1_Array = null;
        touch_prep_f2_Array = null;
        touch_prep_f3_Array = null;
        touch_prep_f4_Array = null;
        touch_prep_f5_Array = null;
        touch_prep_f6_Array = null;
        touch_prep_fs_Array = null;
        pos_touch_prep_alltps_Array = null;
        pos_touch_prep_f1_Array = null;
        pos_touch_prep_f2_Array = null;
        pos_touch_prep_f3_Array = null;
        pos_touch_prep_f4_Array = null;
        pos_touch_prep_f5_Array = null;
        pos_touch_prep_f6_Array = null;
        pos_touch_prep_fs_Array = null;
        pos_neg_touch_prep_alltps_Array = null;
        pos_neg_touch_prep_f1_Array = null;
        pos_neg_touch_prep_f2_Array = null;
        pos_neg_touch_prep_f3_Array = null;
        pos_neg_touch_prep_f4_Array = null;
        pos_neg_touch_prep_f5_Array = null;
        pos_neg_touch_prep_f6_Array = null;
        pos_neg_touch_prep_fs_Array = null;
        neg_touch_prep_alltps_Array = null;
        neg_touch_prep_f1_Array = null;
        neg_touch_prep_f2_Array = null;
        neg_touch_prep_f3_Array = null;
        neg_touch_prep_f4_Array = null;
        neg_touch_prep_f5_Array = null;
        neg_touch_prep_f6_Array = null;
        neg_touch_prep_fs_Array = null;
        dna_with_labeling_total = 0;
        dna_labels_total = 0;
        high_dna_labels_total = 0;
        medium_dna_labels_total = 0;
        low_dna_labels_total = 0;
        dna_with_cgh_total = 0;
        cgh_hybs_total = 0;
        good_cgh_hybs_total = 0;
        poor_cgh_hybs_total = 0;
        try
        {
            samples = vector;
            s = (new StringBuilder()).append(" ").append(s).toString();
            frozenSamples = sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Frozen' ").append(s).toString());
            paraffinSamples = sample.retrieveAllWhere((new StringBuilder()).append(" CORE_TYPE='Paraffin' ").append(s).toString());
            getInstitutionPatientNum(s);
            getFrozenTouchPrepInfo(sample, s);
            getFrozenInstitutionTouchPrepInfo(sample, s);
            getFrozenTimePtTouchPrepInfo(sample, s);
            getFrozenH_EInfo(sample, s);
            getFrozenInstitutionH_EInfo(sample, s);
            getFrozenTimePtH_EInfo(sample, s);
            getParaffinH_EInfo(sample, s);
            getParaffinInstitutionH_EInfo(sample, s);
            getParaffinTimePtH_EInfo(sample, s);
            getParaffinUsabilityInfo(sample, s);
            getParaffinInstitutionUsabilityInfo(sample, s);
            getParaffinTimePtUsabilityInfo(sample, s);
            getParaffinTouchPrepsInfo(sample, s);
            getParaffinInstitutionTouchPrepsInfo(sample, s);
            getParaffinTimePtTouchPrepsInfo(sample, s);
            getSampleDNAQualityInfo(sample, s);
            getSampleRNAQualityInfo(sample, s);
            getSubSamplePCRQualityInfo(sample, s);
            getSubSampleRNAQualityInfo(sample, s);
            getSubSampleProteinQualityInfo(sample, s);
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("e:").append(exception).toString());
        }
    }

    public int getDna_labels_total()
    {
        return dna_labels_total;
    }

    public void setDna_labels_total(int i)
    {
        dna_labels_total = i;
    }

    public int getHigh_dna_labels_total()
    {
        return high_dna_labels_total;
    }

    public void setHigh_dna_labels_total(int i)
    {
        high_dna_labels_total = i;
    }

    public int getMedium_dna_labels_total()
    {
        return medium_dna_labels_total;
    }

    public void setMedium_dna_labels_total(int i)
    {
        medium_dna_labels_total = i;
    }

    public int getLow_dna_labels_total()
    {
        return low_dna_labels_total;
    }

    public void setLow_dna_labels_total(int i)
    {
        low_dna_labels_total = i;
    }

    public int getCgh_hybs_total()
    {
        return cgh_hybs_total;
    }

    public void setCgh_hybs_total(int i)
    {
        cgh_hybs_total = i;
    }

    public int getGood_cgh_hybs_total()
    {
        return good_cgh_hybs_total;
    }

    public void setGood_cgh_hybs_total(int i)
    {
        good_cgh_hybs_total = i;
    }

    public int getPoor_cgh_hybs_total()
    {
        return poor_cgh_hybs_total;
    }

    public void setPoor_cgh_hybs_total(int i)
    {
        poor_cgh_hybs_total = i;
    }

    public int getProteinSize()
    {
        return proteins.size();
    }

    public int getDna_with_cgh_total()
    {
        return dna_with_cgh_total;
    }

    public void setDna_with_cgh_total(int i)
    {
        dna_with_cgh_total = i;
    }

    public int getDna_with_labeling_total()
    {
        return dna_with_labeling_total;
    }

    public void setDna_with_labeling_total(int i)
    {
        dna_with_labeling_total = i;
    }

    private Qi_sample sample;
    private SampleEx sampleSearch;
    private Hashtable hashExl;
    public Vector samples_f;
    public Vector samples;
    public Vector frozenSamples;
    public Vector paraffinSamples;
    public int sampleSize;
    public int allPt;
    public int ucsfPt;
    public int uncPt;
    public int upennPt;
    public int ucPt;
    public int uabPt;
    public int gwPt;
    public int mskccPt;
    public int uwPt;
    public int utPt;
    public Vector samples_f_pos_touch_prep;
    public Vector samples_f_pos_neg_touch_prep;
    public Vector samples_f_neg_touch_prep;
    public Vector samples_f_touch_prep_allInstitutes;
    public float touch_prep_allInstitutes;
    public Vector samples_f_touch_prep_ucsf;
    public float touch_prep_ucsf;
    public Vector samples_f_touch_prep_unc;
    public float touch_prep_unc;
    public Vector samples_f_touch_prep_upenn;
    public float touch_prep_upenn;
    public Vector samples_f_touch_prep_uc;
    public float touch_prep_uc;
    public Vector samples_f_touch_prep_uab;
    public float touch_prep_uab;
    public Vector samples_f_touch_prep_mskcc;
    public float touch_prep_mskcc;
    public Vector samples_f_touch_prep_gw;
    public float touch_prep_gw;
    public Vector samples_f_touch_prep_uw;
    public float touch_prep_uw;
    public Vector samples_f_touch_prep_ut;
    public float touch_prep_ut;
    public Vector samples_f_pos_touch_prep_allInstitutes;
    public float pos_touch_prep_allInstitutes;
    public Vector samples_f_pos_touch_prep_ucsf;
    public float pos_touch_prep_ucsf;
    public Vector samples_f_pos_touch_prep_unc;
    public float pos_touch_prep_unc;
    public Vector samples_f_pos_touch_prep_upenn;
    public float pos_touch_prep_upenn;
    public Vector samples_f_pos_touch_prep_uc;
    public float pos_touch_prep_uc;
    public Vector samples_f_pos_touch_prep_uab;
    public float pos_touch_prep_uab;
    public Vector samples_f_pos_touch_prep_mskcc;
    public float pos_touch_prep_mskcc;
    public Vector samples_f_pos_touch_prep_gw;
    public float pos_touch_prep_gw;
    public Vector samples_f_pos_touch_prep_uw;
    public float pos_touch_prep_uw;
    public Vector samples_f_pos_touch_prep_ut;
    public float pos_touch_prep_ut;
    public Vector samples_f_pos_neg_touch_prep_allInstitutes;
    public float pos_neg_touch_prep_allInstitutes;
    public Vector samples_f_pos_neg_touch_prep_ucsf;
    public float pos_neg_touch_prep_ucsf;
    public Vector samples_f_pos_neg_touch_prep_unc;
    public float pos_neg_touch_prep_unc;
    public Vector samples_f_pos_neg_touch_prep_upenn;
    public float pos_neg_touch_prep_upenn;
    public Vector samples_f_pos_neg_touch_prep_uc;
    public float pos_neg_touch_prep_uc;
    public Vector samples_f_pos_neg_touch_prep_uab;
    public float pos_neg_touch_prep_uab;
    public Vector samples_f_pos_neg_touch_prep_mskcc;
    public float pos_neg_touch_prep_mskcc;
    public Vector samples_f_pos_neg_touch_prep_gw;
    public float pos_neg_touch_prep_gw;
    public Vector samples_f_pos_neg_touch_prep_uw;
    public float pos_neg_touch_prep_uw;
    public Vector samples_f_pos_neg_touch_prep_ut;
    public float pos_neg_touch_prep_ut;
    public Vector samples_f_neg_touch_prep_allInstitutes;
    public float neg_touch_prep_allInstitutes;
    public Vector samples_f_neg_touch_prep_ucsf;
    public float neg_touch_prep_ucsf;
    public Vector samples_f_neg_touch_prep_unc;
    public float neg_touch_prep_unc;
    public Vector samples_f_neg_touch_prep_upenn;
    public float neg_touch_prep_upenn;
    public Vector samples_f_neg_touch_prep_uc;
    public float neg_touch_prep_uc;
    public Vector samples_f_neg_touch_prep_uab;
    public float neg_touch_prep_uab;
    public Vector samples_f_neg_touch_prep_mskcc;
    public float neg_touch_prep_mskcc;
    public Vector samples_f_neg_touch_prep_gw;
    public float neg_touch_prep_gw;
    public Vector samples_f_neg_touch_prep_uw;
    public float neg_touch_prep_uw;
    public Vector samples_f_neg_touch_prep_ut;
    public float neg_touch_prep_ut;
    public float f_touch_prep;
    public float f_pos_touch_prep;
    public float f_pos_neg_touch_prep;
    public float f_neg_touch_prep;
    public Vector samples_f_touch_prep_alltps;
    public float touch_prep_alltps;
    public Vector samples_f_touch_prep_f1;
    public float touch_prep_f1;
    public Vector samples_f_touch_prep_f2;
    public float touch_prep_f2;
    public Vector samples_f_touch_prep_f3;
    public float touch_prep_f3;
    public Vector samples_f_touch_prep_f4;
    public float touch_prep_f4;
    public Vector samples_f_touch_prep_f5;
    public float touch_prep_f5;
    public Vector samples_f_touch_prep_f6;
    public float touch_prep_f6;
    public Vector samples_f_touch_prep_fs;
    public float touch_prep_fs;
    public Vector samples_f_pos_touch_prep_alltps;
    public float pos_touch_prep_alltps;
    public Vector samples_f_pos_touch_prep_f1;
    public float pos_touch_prep_f1;
    public Vector samples_f_pos_touch_prep_f2;
    public float pos_touch_prep_f2;
    public Vector samples_f_pos_touch_prep_f3;
    public float pos_touch_prep_f3;
    public Vector samples_f_pos_touch_prep_f4;
    public float pos_touch_prep_f4;
    public Vector samples_f_pos_touch_prep_f5;
    public float pos_touch_prep_f5;
    public Vector samples_f_pos_touch_prep_f6;
    public float pos_touch_prep_f6;
    public Vector samples_f_pos_touch_prep_fs;
    public float pos_touch_prep_fs;
    public Vector samples_f_pos_neg_touch_prep_alltps;
    public float pos_neg_touch_prep_alltps;
    public Vector samples_f_pos_neg_touch_prep_f1;
    public float pos_neg_touch_prep_f1;
    public Vector samples_f_pos_neg_touch_prep_f2;
    public float pos_neg_touch_prep_f2;
    public Vector samples_f_pos_neg_touch_prep_f3;
    public float pos_neg_touch_prep_f3;
    public Vector samples_f_pos_neg_touch_prep_f4;
    public float pos_neg_touch_prep_f4;
    public Vector samples_f_pos_neg_touch_prep_f5;
    public float pos_neg_touch_prep_f5;
    public Vector samples_f_pos_neg_touch_prep_f6;
    public float pos_neg_touch_prep_f6;
    public Vector samples_f_pos_neg_touch_prep_fs;
    public float pos_neg_touch_prep_fs;
    public Vector samples_f_neg_touch_prep_alltps;
    public float neg_touch_prep_alltps;
    public Vector samples_f_neg_touch_prep_f1;
    public float neg_touch_prep_f1;
    public Vector samples_f_neg_touch_prep_f2;
    public float neg_touch_prep_f2;
    public Vector samples_f_neg_touch_prep_f3;
    public float neg_touch_prep_f3;
    public Vector samples_f_neg_touch_prep_f4;
    public float neg_touch_prep_f4;
    public Vector samples_f_neg_touch_prep_f5;
    public float neg_touch_prep_f5;
    public Vector samples_f_neg_touch_prep_f6;
    public float neg_touch_prep_f6;
    public Vector samples_f_neg_touch_prep_fs;
    public float neg_touch_prep_fs;
    public Vector samples_f_h_e;
    public float f_h_e;
    public String f_h_e_Array[];
    public Vector samples_f_pos_h_e;
    public float f_pos_h_e;
    public String f_pos_h_e_Array[];
    public Vector samples_f_neg_h_e;
    public float f_neg_h_e;
    public String f_neg_h_e_Array[];
    public Vector samples_f_h_e_allInstitutes;
    public float h_e_allInstitutes;
    public String h_e_allInstitutes_Array[];
    public Vector samples_f_h_e_ucsf;
    public float h_e_ucsf;
    public String h_e_ucsf_Array[];
    public Vector samples_f_h_e_unc;
    public float h_e_unc;
    public String h_e_unc_Array[];
    public Vector samples_f_h_e_upenn;
    public float h_e_upenn;
    public String h_e_upenn_Array[];
    public Vector samples_f_h_e_uc;
    public float h_e_uc;
    public String h_e_uc_Array[];
    public Vector samples_f_h_e_uab;
    public float h_e_uab;
    public String h_e_uab_Array[];
    public Vector samples_f_h_e_mskcc;
    public float h_e_mskcc;
    public String h_e_mskcc_Array[];
    public Vector samples_f_h_e_gw;
    public float h_e_gw;
    public String h_e_gw_Array[];
    public Vector samples_f_h_e_uw;
    public float h_e_uw;
    public String h_e_uw_Array[];
    public Vector samples_f_h_e_ut;
    public float h_e_ut;
    public String h_e_ut_Array[];
    public Vector samples_f_h_e_not_null;
    public Vector samples_f_pos_h_e_allInstitutes;
    public float pos_h_e_allInstitutes;
    public String pos_h_e_allInstitutes_Array[];
    public Vector samples_f_pos_h_e_ucsf;
    public float pos_h_e_ucsf;
    public String pos_h_e_ucsf_Array[];
    public Vector samples_f_pos_h_e_unc;
    public float pos_h_e_unc;
    public String pos_h_e_unc_Array[];
    public Vector samples_f_pos_h_e_upenn;
    public float pos_h_e_upenn;
    public String pos_h_e_upenn_Array[];
    public Vector samples_f_pos_h_e_uc;
    public float pos_h_e_uc;
    public String pos_h_e_uc_Array[];
    public Vector samples_f_pos_h_e_uab;
    public float pos_h_e_uab;
    public String pos_h_e_uab_Array[];
    public Vector samples_f_pos_h_e_mskcc;
    public float pos_h_e_mskcc;
    public String pos_h_e_mskcc_Array[];
    public Vector samples_f_pos_h_e_gw;
    public float pos_h_e_gw;
    public String pos_h_e_gw_Array[];
    public Vector samples_f_pos_h_e_uw;
    public float pos_h_e_uw;
    public String pos_h_e_uw_Array[];
    public Vector samples_f_pos_h_e_ut;
    public float pos_h_e_ut;
    public String pos_h_e_ut_Array[];
    public Vector samples_f_neg_h_e_allInstitutes;
    public float neg_h_e_allInstitutes;
    public String neg_h_e_allInstitutes_Array[];
    public Vector samples_f_neg_h_e_ucsf;
    public float neg_h_e_ucsf;
    public String neg_h_e_ucsf_Array[];
    public Vector samples_f_neg_h_e_unc;
    public float neg_h_e_unc;
    public String neg_h_e_unc_Array[];
    public Vector samples_f_neg_h_e_upenn;
    public float neg_h_e_upenn;
    public String neg_h_e_upenn_Array[];
    public Vector samples_f_neg_h_e_uc;
    public float neg_h_e_uc;
    public String neg_h_e_uc_Array[];
    public Vector samples_f_neg_h_e_uab;
    public float neg_h_e_uab;
    public String neg_h_e_uab_Array[];
    public Vector samples_f_neg_h_e_mskcc;
    public float neg_h_e_mskcc;
    public String neg_h_e_mskcc_Array[];
    public Vector samples_f_neg_h_e_gw;
    public float neg_h_e_gw;
    public String neg_h_e_gw_Array[];
    public Vector samples_f_neg_h_e_uw;
    public float neg_h_e_uw;
    public String neg_h_e_uw_Array[];
    public Vector samples_f_neg_h_e_ut;
    public float neg_h_e_ut;
    public String neg_h_e_ut_Array[];
    public Vector samples_f_h_e_alltps;
    public float h_e_alltps;
    public String h_e_alltps_Array[];
    public Vector samples_f_h_e_f1;
    public float h_e_f1;
    public String h_e_f1_Array[];
    public Vector samples_f_h_e_f2;
    public float h_e_f2;
    public String h_e_f2_Array[];
    public Vector samples_f_h_e_f3;
    public float h_e_f3;
    public String h_e_f3_Array[];
    public Vector samples_f_h_e_f4;
    public float h_e_f4;
    public String h_e_f4_Array[];
    public Vector samples_f_h_e_f5;
    public float h_e_f5;
    public String h_e_f5_Array[];
    public Vector samples_f_h_e_f6;
    public float h_e_f6;
    public String h_e_f6_Array[];
    public Vector samples_f_h_e_fs;
    public float h_e_fs;
    public String h_e_fs_Array[];
    public Vector samples_f_pos_h_e_alltps;
    public float pos_h_e_alltps;
    public String pos_h_e_alltps_Array[];
    public Vector samples_f_pos_h_e_f1;
    public float pos_h_e_f1;
    public String pos_h_e_f1_Array[];
    public Vector samples_f_pos_h_e_f2;
    public float pos_h_e_f2;
    public String pos_h_e_f2_Array[];
    public Vector samples_f_pos_h_e_f3;
    public float pos_h_e_f3;
    public String pos_h_e_f3_Array[];
    public Vector samples_f_pos_h_e_f4;
    public float pos_h_e_f4;
    public String pos_h_e_f4_Array[];
    public Vector samples_f_pos_h_e_f5;
    public float pos_h_e_f5;
    public String pos_h_e_f5_Array[];
    public Vector samples_f_pos_h_e_f6;
    public float pos_h_e_f6;
    public String pos_h_e_f6_Array[];
    public Vector samples_f_pos_h_e_fs;
    public float pos_h_e_fs;
    public String pos_h_e_fs_Array[];
    public Vector samples_f_neg_h_e_alltps;
    public float neg_h_e_alltps;
    public String neg_h_e_alltps_Array[];
    public Vector samples_f_neg_h_e_f1;
    public float neg_h_e_f1;
    public String neg_h_e_f1_Array[];
    public Vector samples_f_neg_h_e_f2;
    public float neg_h_e_f2;
    public String neg_h_e_f2_Array[];
    public Vector samples_f_neg_h_e_f3;
    public float neg_h_e_f3;
    public String neg_h_e_f3_Array[];
    public Vector samples_f_neg_h_e_f4;
    public float neg_h_e_f4;
    public String neg_h_e_f4_Array[];
    public Vector samples_f_neg_h_e_f5;
    public float neg_h_e_f5;
    public String neg_h_e_f5_Array[];
    public Vector samples_f_neg_h_e_f6;
    public float neg_h_e_f6;
    public String neg_h_e_f6_Array[];
    public Vector samples_f_neg_h_e_fs;
    public float neg_h_e_fs;
    public String neg_h_e_fs_Array[];
    public Vector samples_p;
    public float p_h_e;
    public String p_h_e_Array[];
    public Vector samples_p_pos_pos_h_e;
    public float p_pos_pos_h_e;
    public String p_pos_pos_h_e_Array[];
    public Vector samples_p_neg_neg_h_e;
    public float p_neg_neg_h_e;
    public String p_neg_neg_h_e_Array[];
    public Vector samples_p_pos_neg_h_e;
    public float p_pos_neg_h_e;
    public String p_pos_neg_h_e_Array[];
    public Vector samples_p_neg_pos_h_e;
    public float p_neg_pos_h_e;
    public String p_neg_pos_h_e_Array[];
    public Vector samples_p_blank_h_e;
    public float p_blank_h_e;
    public String p_blank_h_e_Array[];
    public Vector samples_p_h_e_allInstitutes;
    public float p_h_e_allInstitutes;
    public String p_h_e_allInstitutes_Array[];
    public Vector samples_p_h_e_ucsf;
    public float p_h_e_ucsf;
    public String p_h_e_ucsf_Array[];
    public Vector samples_p_h_e_unc;
    public float p_h_e_unc;
    public String p_h_e_unc_Array[];
    public Vector samples_p_h_e_upenn;
    public float p_h_e_upenn;
    public String p_h_e_upenn_Array[];
    public Vector samples_p_h_e_uc;
    public float p_h_e_uc;
    public String p_h_e_uc_Array[];
    public Vector samples_p_h_e_uab;
    public float p_h_e_uab;
    public String p_h_e_uab_Array[];
    public Vector samples_p_h_e_mskcc;
    public float p_h_e_mskcc;
    public String p_h_e_mskcc_Array[];
    public Vector samples_p_h_e_gw;
    public float p_h_e_gw;
    public String p_h_e_gw_Array[];
    public Vector samples_p_h_e_uw;
    public float p_h_e_uw;
    public String p_h_e_uw_Array[];
    public Vector samples_p_h_e_ut;
    public float p_h_e_ut;
    public String p_h_e_ut_Array[];
    public Vector samples_p_h_e_not_null;
    public Vector samples_p_pos_pos_h_e_allInstitutes;
    public float p_pos_pos_h_e_allInstitutes;
    public String p_pos_pos_h_e_allInstitutes_Array[];
    public Vector samples_p_pos_pos_h_e_ucsf;
    public float p_pos_pos_h_e_ucsf;
    public String p_pos_pos_h_e_ucsf_Array[];
    public Vector samples_p_pos_pos_h_e_unc;
    public float p_pos_pos_h_e_unc;
    public String p_pos_pos_h_e_unc_Array[];
    public Vector samples_p_pos_pos_h_e_upenn;
    public float p_pos_pos_h_e_upenn;
    public String p_pos_pos_h_e_upenn_Array[];
    public Vector samples_p_pos_pos_h_e_uc;
    public float p_pos_pos_h_e_uc;
    public String p_pos_pos_h_e_uc_Array[];
    public Vector samples_p_pos_pos_h_e_uab;
    public float p_pos_pos_h_e_uab;
    public String p_pos_pos_h_e_uab_Array[];
    public Vector samples_p_pos_pos_h_e_mskcc;
    public float p_pos_pos_h_e_mskcc;
    public String p_pos_pos_h_e_mskcc_Array[];
    public Vector samples_p_pos_pos_h_e_gw;
    public float p_pos_pos_h_e_gw;
    public String p_pos_pos_h_e_gw_Array[];
    public Vector samples_p_pos_pos_h_e_uw;
    public float p_pos_pos_h_e_uw;
    public String p_pos_pos_h_e_uw_Array[];
    public Vector samples_p_pos_pos_h_e_ut;
    public float p_pos_pos_h_e_ut;
    public String p_pos_pos_h_e_ut_Array[];
    public Vector samples_p_neg_neg_h_e_allInstitutes;
    public float p_neg_neg_h_e_allInstitutes;
    public String p_neg_neg_h_e_allInstitutes_Array[];
    public Vector samples_p_neg_neg_h_e_ucsf;
    public float p_neg_neg_h_e_ucsf;
    public String p_neg_neg_h_e_ucsf_Array[];
    public Vector samples_p_neg_neg_h_e_unc;
    public float p_neg_neg_h_e_unc;
    public String p_neg_neg_h_e_unc_Array[];
    public Vector samples_p_neg_neg_h_e_upenn;
    public float p_neg_neg_h_e_upenn;
    public String p_neg_neg_h_e_upenn_Array[];
    public Vector samples_p_neg_neg_h_e_uc;
    public float p_neg_neg_h_e_uc;
    public String p_neg_neg_h_e_uc_Array[];
    public Vector samples_p_neg_neg_h_e_uab;
    public float p_neg_neg_h_e_uab;
    public String p_neg_neg_h_e_uab_Array[];
    public Vector samples_p_neg_neg_h_e_mskcc;
    public float p_neg_neg_h_e_mskcc;
    public String p_neg_neg_h_e_mskcc_Array[];
    public Vector samples_p_neg_neg_h_e_gw;
    public float p_neg_neg_h_e_gw;
    public String p_neg_neg_h_e_gw_Array[];
    public Vector samples_p_neg_neg_h_e_uw;
    public float p_neg_neg_h_e_uw;
    public String p_neg_neg_h_e_uw_Array[];
    public Vector samples_p_neg_neg_h_e_ut;
    public float p_neg_neg_h_e_ut;
    public String p_neg_neg_h_e_ut_Array[];
    public Vector samples_p_pos_neg_h_e_allInstitutes;
    public float p_pos_neg_h_e_allInstitutes;
    public String p_pos_neg_h_e_allInstitutes_Array[];
    public Vector samples_p_pos_neg_h_e_ucsf;
    public float p_pos_neg_h_e_ucsf;
    public String p_pos_neg_h_e_ucsf_Array[];
    public Vector samples_p_pos_neg_h_e_unc;
    public float p_pos_neg_h_e_unc;
    public String p_pos_neg_h_e_unc_Array[];
    public Vector samples_p_pos_neg_h_e_upenn;
    public float p_pos_neg_h_e_upenn;
    public String p_pos_neg_h_e_upenn_Array[];
    public Vector samples_p_pos_neg_h_e_uc;
    public float p_pos_neg_h_e_uc;
    public String p_pos_neg_h_e_uc_Array[];
    public Vector samples_p_pos_neg_h_e_uab;
    public float p_pos_neg_h_e_uab;
    public String p_pos_neg_h_e_uab_Array[];
    public Vector samples_p_pos_neg_h_e_mskcc;
    public float p_pos_neg_h_e_mskcc;
    public String p_pos_neg_h_e_mskcc_Array[];
    public Vector samples_p_pos_neg_h_e_gw;
    public float p_pos_neg_h_e_gw;
    public String p_pos_neg_h_e_gw_Array[];
    public Vector samples_p_pos_neg_h_e_uw;
    public float p_pos_neg_h_e_uw;
    public String p_pos_neg_h_e_uw_Array[];
    public Vector samples_p_pos_neg_h_e_ut;
    public float p_pos_neg_h_e_ut;
    public String p_pos_neg_h_e_ut_Array[];
    public Vector samples_p_neg_pos_h_e_allInstitutes;
    public float p_neg_pos_h_e_allInstitutes;
    public String p_neg_pos_h_e_allInstitutes_Array[];
    public Vector samples_p_neg_pos_h_e_ucsf;
    public float p_neg_pos_h_e_ucsf;
    public String p_neg_pos_h_e_ucsf_Array[];
    public Vector samples_p_neg_pos_h_e_unc;
    public float p_neg_pos_h_e_unc;
    public String p_neg_pos_h_e_unc_Array[];
    public Vector samples_p_neg_pos_h_e_upenn;
    public float p_neg_pos_h_e_upenn;
    public String p_neg_pos_h_e_upenn_Array[];
    public Vector samples_p_neg_pos_h_e_uc;
    public float p_neg_pos_h_e_uc;
    public String p_neg_pos_h_e_uc_Array[];
    public Vector samples_p_neg_pos_h_e_uab;
    public float p_neg_pos_h_e_uab;
    public String p_neg_pos_h_e_uab_Array[];
    public Vector samples_p_neg_pos_h_e_mskcc;
    public float p_neg_pos_h_e_mskcc;
    public String p_neg_pos_h_e_mskcc_Array[];
    public Vector samples_p_neg_pos_h_e_gw;
    public float p_neg_pos_h_e_gw;
    public String p_neg_pos_h_e_gw_Array[];
    public Vector samples_p_neg_pos_h_e_uw;
    public float p_neg_pos_h_e_uw;
    public String p_neg_pos_h_e_uw_Array[];
    public Vector samples_p_neg_pos_h_e_ut;
    public float p_neg_pos_h_e_ut;
    public String p_neg_pos_h_e_ut_Array[];
    public Vector samples_p_blank_h_e_allInstitutes;
    public float p_blank_h_e_allInstitutes;
    public String p_blank_h_e_allInstitutes_Array[];
    public Vector samples_p_blank_h_e_ucsf;
    public float p_blank_h_e_ucsf;
    public String p_blank_h_e_ucsf_Array[];
    public Vector samples_p_blank_h_e_unc;
    public float p_blank_h_e_unc;
    public String p_blank_h_e_unc_Array[];
    public Vector samples_p_blank_h_e_upenn;
    public float p_blank_h_e_upenn;
    public String p_blank_h_e_upenn_Array[];
    public Vector samples_p_blank_h_e_uc;
    public float p_blank_h_e_uc;
    public String p_blank_h_e_uc_Array[];
    public Vector samples_p_blank_h_e_uab;
    public float p_blank_h_e_uab;
    public String p_blank_h_e_uab_Array[];
    public Vector samples_p_blank_h_e_mskcc;
    public float p_blank_h_e_mskcc;
    public String p_blank_h_e_mskcc_Array[];
    public Vector samples_p_blank_h_e_gw;
    public float p_blank_h_e_gw;
    public String p_blank_h_e_gw_Array[];
    public Vector samples_p_blank_h_e_uw;
    public float p_blank_h_e_uw;
    public String p_blank_h_e_uw_Array[];
    public Vector samples_p_blank_h_e_ut;
    public float p_blank_h_e_ut;
    public String p_blank_h_e_ut_Array[];
    public Vector samples_p_h_e_alltps;
    public float p_h_e_alltps;
    public String p_h_e_alltps_Array[];
    public Vector samples_p_h_e_p1;
    public float p_h_e_p1;
    public String p_h_e_p1_Array[];
    public Vector samples_p_h_e_p2;
    public float p_h_e_p2;
    public String p_h_e_p2_Array[];
    public Vector samples_p_h_e_p3;
    public float p_h_e_p3;
    public String p_h_e_p3_Array[];
    public Vector samples_p_h_e_p4;
    public float p_h_e_p4;
    public String p_h_e_p4_Array[];
    public Vector samples_p_h_e_p5;
    public float p_h_e_p5;
    public String p_h_e_p5_Array[];
    public Vector samples_p_h_e_p6;
    public float p_h_e_p6;
    public String p_h_e_p6_Array[];
    public Vector samples_p_h_e_ps;
    public float p_h_e_ps;
    public String p_h_e_ps_Array[];
    public Vector samples_p_pos_pos_h_e_alltps;
    public float p_pos_pos_h_e_alltps;
    public String p_pos_pos_h_e_alltps_Array[];
    public Vector samples_p_pos_pos_h_e_p1;
    public float p_pos_pos_h_e_p1;
    public String p_pos_pos_h_e_p1_Array[];
    public Vector samples_p_pos_pos_h_e_p2;
    public float p_pos_pos_h_e_p2;
    public String p_pos_pos_h_e_p2_Array[];
    public Vector samples_p_pos_pos_h_e_p3;
    public float p_pos_pos_h_e_p3;
    public String p_pos_pos_h_e_p3_Array[];
    public Vector samples_p_pos_pos_h_e_p4;
    public float p_pos_pos_h_e_p4;
    public String p_pos_pos_h_e_p4_Array[];
    public Vector samples_p_pos_pos_h_e_p5;
    public float p_pos_pos_h_e_p5;
    public String p_pos_pos_h_e_p5_Array[];
    public Vector samples_p_pos_pos_h_e_p6;
    public float p_pos_pos_h_e_p6;
    public String p_pos_pos_h_e_p6_Array[];
    public Vector samples_p_pos_pos_h_e_ps;
    public float p_pos_pos_h_e_ps;
    public String p_pos_pos_h_e_ps_Array[];
    public Vector samples_p_neg_neg_h_e_alltps;
    public float p_neg_neg_h_e_alltps;
    public String p_neg_neg_h_e_alltps_Array[];
    public Vector samples_p_neg_neg_h_e_p1;
    public float p_neg_neg_h_e_p1;
    public String p_neg_neg_h_e_p1_Array[];
    public Vector samples_p_neg_neg_h_e_p2;
    public float p_neg_neg_h_e_p2;
    public String p_neg_neg_h_e_p2_Array[];
    public Vector samples_p_neg_neg_h_e_p3;
    public float p_neg_neg_h_e_p3;
    public String p_neg_neg_h_e_p3_Array[];
    public Vector samples_p_neg_neg_h_e_p4;
    public float p_neg_neg_h_e_p4;
    public String p_neg_neg_h_e_p4_Array[];
    public Vector samples_p_neg_neg_h_e_p5;
    public float p_neg_neg_h_e_p5;
    public String p_neg_neg_h_e_p5_Array[];
    public Vector samples_p_neg_neg_h_e_p6;
    public float p_neg_neg_h_e_p6;
    public String p_neg_neg_h_e_p6_Array[];
    public Vector samples_p_neg_neg_h_e_ps;
    public float p_neg_neg_h_e_ps;
    public String p_neg_neg_h_e_ps_Array[];
    public Vector samples_p_pos_neg_h_e_alltps;
    public float p_pos_neg_h_e_alltps;
    public String p_pos_neg_h_e_alltps_Array[];
    public Vector samples_p_pos_neg_h_e_p1;
    public float p_pos_neg_h_e_p1;
    public String p_pos_neg_h_e_p1_Array[];
    public Vector samples_p_pos_neg_h_e_p2;
    public float p_pos_neg_h_e_p2;
    public String p_pos_neg_h_e_p2_Array[];
    public Vector samples_p_pos_neg_h_e_p3;
    public float p_pos_neg_h_e_p3;
    public String p_pos_neg_h_e_p3_Array[];
    public Vector samples_p_pos_neg_h_e_p4;
    public float p_pos_neg_h_e_p4;
    public String p_pos_neg_h_e_p4_Array[];
    public Vector samples_p_pos_neg_h_e_p5;
    public float p_pos_neg_h_e_p5;
    public String p_pos_neg_h_e_p5_Array[];
    public Vector samples_p_pos_neg_h_e_p6;
    public float p_pos_neg_h_e_p6;
    public String p_pos_neg_h_e_p6_Array[];
    public Vector samples_p_pos_neg_h_e_ps;
    public float p_pos_neg_h_e_ps;
    public String p_pos_neg_h_e_ps_Array[];
    public Vector samples_p_neg_pos_h_e_alltps;
    public float p_neg_pos_h_e_alltps;
    public String p_neg_pos_h_e_alltps_Array[];
    public Vector samples_p_neg_pos_h_e_p1;
    public float p_neg_pos_h_e_p1;
    public String p_neg_pos_h_e_p1_Array[];
    public Vector samples_p_neg_pos_h_e_p2;
    public float p_neg_pos_h_e_p2;
    public String p_neg_pos_h_e_p2_Array[];
    public Vector samples_p_neg_pos_h_e_p3;
    public float p_neg_pos_h_e_p3;
    public String p_neg_pos_h_e_p3_Array[];
    public Vector samples_p_neg_pos_h_e_p4;
    public float p_neg_pos_h_e_p4;
    public String p_neg_pos_h_e_p4_Array[];
    public Vector samples_p_neg_pos_h_e_p5;
    public float p_neg_pos_h_e_p5;
    public String p_neg_pos_h_e_p5_Array[];
    public Vector samples_p_neg_pos_h_e_p6;
    public float p_neg_pos_h_e_p6;
    public String p_neg_pos_h_e_p6_Array[];
    public Vector samples_p_neg_pos_h_e_ps;
    public float p_neg_pos_h_e_ps;
    public String p_neg_pos_h_e_ps_Array[];
    public Vector samples_p_blank_h_e_alltps;
    public float p_blank_h_e_alltps;
    public String p_blank_h_e_alltps_Array[];
    public Vector samples_p_blank_h_e_p1;
    public float p_blank_h_e_p1;
    public String p_blank_h_e_p1_Array[];
    public Vector samples_p_blank_h_e_p2;
    public float p_blank_h_e_p2;
    public String p_blank_h_e_p2_Array[];
    public Vector samples_p_blank_h_e_p3;
    public float p_blank_h_e_p3;
    public String p_blank_h_e_p3_Array[];
    public Vector samples_p_blank_h_e_p4;
    public float p_blank_h_e_p4;
    public String p_blank_h_e_p4_Array[];
    public Vector samples_p_blank_h_e_p5;
    public float p_blank_h_e_p5;
    public String p_blank_h_e_p5_Array[];
    public Vector samples_p_blank_h_e_p6;
    public float p_blank_h_e_p6;
    public String p_blank_h_e_p6_Array[];
    public Vector samples_p_blank_h_e_ps;
    public float p_blank_h_e_ps;
    public String p_blank_h_e_ps_Array[];
    public Vector samples_p_usability;
    public float p_usability;
    public String p_usability_Array[];
    public Vector samples_p_yes_usability;
    public float p_yes_usability;
    public String p_yes_usability_Array[];
    public Vector samples_p_no_usability;
    public float p_no_usability;
    public String p_no_usability_Array[];
    public Vector samples_p_blank_usability;
    public float p_blank_usability;
    public String p_blank_usability_Array[];
    public Vector samples_p_usability_allInstitutes;
    public float p_usability_allInstitutes;
    public String p_usability_allInstitutes_Array[];
    public Vector samples_p_usability_ucsf;
    public float p_usability_ucsf;
    public String p_usability_ucsf_Array[];
    public Vector samples_p_usability_unc;
    public float p_usability_unc;
    public String p_usability_unc_Array[];
    public Vector samples_p_usability_upenn;
    public float p_usability_upenn;
    public String p_usability_upenn_Array[];
    public Vector samples_p_usability_uc;
    public float p_usability_uc;
    public String p_usability_uc_Array[];
    public Vector samples_p_usability_uab;
    public float p_usability_uab;
    public String p_usability_uab_Array[];
    public Vector samples_p_usability_mskcc;
    public float p_usability_mskcc;
    public String p_usability_mskcc_Array[];
    public Vector samples_p_usability_gw;
    public float p_usability_gw;
    public String p_usability_gw_Array[];
    public Vector samples_p_usability_uw;
    public float p_usability_uw;
    public String p_usability_uw_Array[];
    public Vector samples_p_usability_ut;
    public float p_usability_ut;
    public String p_usability_ut_Array[];
    public Vector samples_p_yes_usability_allInstitutes;
    public float p_yes_usability_allInstitutes;
    public String p_yes_usability_allInstitutes_Array[];
    public Vector samples_p_yes_usability_ucsf;
    public float p_yes_usability_ucsf;
    public String p_yes_usability_ucsf_Array[];
    public Vector samples_p_yes_usability_unc;
    public float p_yes_usability_unc;
    public String p_yes_usability_unc_Array[];
    public Vector samples_p_yes_usability_upenn;
    public float p_yes_usability_upenn;
    public String p_yes_usability_upenn_Array[];
    public Vector samples_p_yes_usability_uc;
    public float p_yes_usability_uc;
    public String p_yes_usability_uc_Array[];
    public Vector samples_p_yes_usability_uab;
    public float p_yes_usability_uab;
    public String p_yes_usability_uab_Array[];
    public Vector samples_p_yes_usability_mskcc;
    public float p_yes_usability_mskcc;
    public String p_yes_usability_mskcc_Array[];
    public Vector samples_p_yes_usability_gw;
    public float p_yes_usability_gw;
    public String p_yes_usability_gw_Array[];
    public Vector samples_p_yes_usability_uw;
    public float p_yes_usability_uw;
    public String p_yes_usability_uw_Array[];
    public Vector samples_p_yes_usability_ut;
    public float p_yes_usability_ut;
    public String p_yes_usability_ut_Array[];
    public Vector samples_p_no_usability_allInstitutes;
    public float p_no_usability_allInstitutes;
    public String p_no_usability_allInstitutes_Array[];
    public Vector samples_p_no_usability_ucsf;
    public float p_no_usability_ucsf;
    public String p_no_usability_ucsf_Array[];
    public Vector samples_p_no_usability_unc;
    public float p_no_usability_unc;
    public String p_no_usability_unc_Array[];
    public Vector samples_p_no_usability_upenn;
    public float p_no_usability_upenn;
    public String p_no_usability_upenn_Array[];
    public Vector samples_p_no_usability_uc;
    public float p_no_usability_uc;
    public String p_no_usability_uc_Array[];
    public Vector samples_p_no_usability_uab;
    public float p_no_usability_uab;
    public String p_no_usability_uab_Array[];
    public Vector samples_p_no_usability_mskcc;
    public float p_no_usability_mskcc;
    public String p_no_usability_mskcc_Array[];
    public Vector samples_p_no_usability_gw;
    public float p_no_usability_gw;
    public String p_no_usability_gw_Array[];
    public Vector samples_p_no_usability_uw;
    public float p_no_usability_uw;
    public String p_no_usability_uw_Array[];
    public Vector samples_p_no_usability_ut;
    public float p_no_usability_ut;
    public String p_no_usability_ut_Array[];
    public Vector samples_p_blank_usability_allInstitutes;
    public float p_blank_usability_allInstitutes;
    public String p_blank_usability_allInstitutes_Array[];
    public Vector samples_p_blank_usability_ucsf;
    public float p_blank_usability_ucsf;
    public String p_blank_usability_ucsf_Array[];
    public Vector samples_p_blank_usability_unc;
    public float p_blank_usability_unc;
    public String p_blank_usability_unc_Array[];
    public Vector samples_p_blank_usability_upenn;
    public float p_blank_usability_upenn;
    public String p_blank_usability_upenn_Array[];
    public Vector samples_p_blank_usability_uc;
    public float p_blank_usability_uc;
    public String p_blank_usability_uc_Array[];
    public Vector samples_p_blank_usability_uab;
    public float p_blank_usability_uab;
    public String p_blank_usability_uab_Array[];
    public Vector samples_p_blank_usability_mskcc;
    public float p_blank_usability_mskcc;
    public String p_blank_usability_mskcc_Array[];
    public Vector samples_p_blank_usability_gw;
    public float p_blank_usability_gw;
    public String p_blank_usability_gw_Array[];
    public Vector samples_p_blank_usability_uw;
    public float p_blank_usability_uw;
    public String p_blank_usability_uw_Array[];
    public Vector samples_p_blank_usability_ut;
    public float p_blank_usability_ut;
    public String p_blank_usability_ut_Array[];
    public Vector samples_p_usability_alltps;
    public float p_usability_alltps;
    public String p_usability_alltps_Array[];
    public Vector samples_p_usability_p1;
    public float p_usability_p1;
    public String p_usability_p1_Array[];
    public Vector samples_p_usability_p2;
    public float p_usability_p2;
    public String p_usability_p2_Array[];
    public Vector samples_p_usability_p3;
    public float p_usability_p3;
    public String p_usability_p3_Array[];
    public Vector samples_p_usability_p4;
    public float p_usability_p4;
    public String p_usability_p4_Array[];
    public Vector samples_p_usability_p5;
    public float p_usability_p5;
    public String p_usability_p5_Array[];
    public Vector samples_p_usability_p6;
    public float p_usability_p6;
    public String p_usability_p6_Array[];
    public Vector samples_p_usability_ps;
    public float p_usability_ps;
    public String p_usability_ps_Array[];
    public Vector samples_p_yes_usability_alltps;
    public float p_yes_usability_alltps;
    public String p_yes_usability_alltps_Array[];
    public Vector samples_p_yes_usability_p1;
    public float p_yes_usability_p1;
    public String p_yes_usability_p1_Array[];
    public Vector samples_p_yes_usability_p2;
    public float p_yes_usability_p2;
    public String p_yes_usability_p2_Array[];
    public Vector samples_p_yes_usability_p3;
    public float p_yes_usability_p3;
    public String p_yes_usability_p3_Array[];
    public Vector samples_p_yes_usability_p4;
    public float p_yes_usability_p4;
    public String p_yes_usability_p4_Array[];
    public Vector samples_p_yes_usability_p5;
    public float p_yes_usability_p5;
    public String p_yes_usability_p5_Array[];
    public Vector samples_p_yes_usability_p6;
    public float p_yes_usability_p6;
    public String p_yes_usability_p6_Array[];
    public Vector samples_p_yes_usability_ps;
    public float p_yes_usability_ps;
    public String p_yes_usability_ps_Array[];
    public Vector samples_p_no_usability_alltps;
    public float p_no_usability_alltps;
    public String p_no_usability_alltps_Array[];
    public Vector samples_p_no_usability_p1;
    public float p_no_usability_p1;
    public String p_no_usability_p1_Array[];
    public Vector samples_p_no_usability_p2;
    public float p_no_usability_p2;
    public String p_no_usability_p2_Array[];
    public Vector samples_p_no_usability_p3;
    public float p_no_usability_p3;
    public String p_no_usability_p3_Array[];
    public Vector samples_p_no_usability_p4;
    public float p_no_usability_p4;
    public String p_no_usability_p4_Array[];
    public Vector samples_p_no_usability_p5;
    public float p_no_usability_p5;
    public String p_no_usability_p5_Array[];
    public Vector samples_p_no_usability_p6;
    public float p_no_usability_p6;
    public String p_no_usability_p6_Array[];
    public Vector samples_p_no_usability_ps;
    public float p_no_usability_ps;
    public String p_no_usability_ps_Array[];
    public Vector samples_p_blank_usability_alltps;
    public float p_blank_usability_alltps;
    public String p_blank_usability_alltps_Array[];
    public Vector samples_p_blank_usability_p1;
    public float p_blank_usability_p1;
    public String p_blank_usability_p1_Array[];
    public Vector samples_p_blank_usability_p2;
    public float p_blank_usability_p2;
    public String p_blank_usability_p2_Array[];
    public Vector samples_p_blank_usability_p3;
    public float p_blank_usability_p3;
    public String p_blank_usability_p3_Array[];
    public Vector samples_p_blank_usability_p4;
    public float p_blank_usability_p4;
    public String p_blank_usability_p4_Array[];
    public Vector samples_p_blank_usability_p5;
    public float p_blank_usability_p5;
    public String p_blank_usability_p5_Array[];
    public Vector samples_p_blank_usability_p6;
    public float p_blank_usability_p6;
    public String p_blank_usability_p6_Array[];
    public Vector samples_p_blank_usability_ps;
    public float p_blank_usability_ps;
    public String p_blank_usability_ps_Array[];
    public Vector samples_p_touchPrepRecvd;
    public float p_touchPrepRecvd;
    public String p_touchPrepRecvd_Array[];
    public Vector samples_p_yes_touchPrepRecvd;
    public float p_yes_touchPrepRecvd;
    public String p_yes_touchPrepRecvd_Array[];
    public Vector samples_p_no_touchPrepRecvd;
    public float p_no_touchPrepRecvd;
    public String p_no_touchPrepRecvd_Array[];
    public Vector samples_p_blank_touchPrepRecvd;
    public float p_blank_touchPrepRecvd;
    public String p_blank_touchPrepRecvd_Array[];
    public Vector samples_p_touchPrepRecvd_allInstitutes;
    public float p_touchPrepRecvd_allInstitutes;
    public String p_touchPrepRecvd_allInstitutes_Array[];
    public Vector samples_p_touchPrepRecvd_ucsf;
    public float p_touchPrepRecvd_ucsf;
    public String p_touchPrepRecvd_ucsf_Array[];
    public Vector samples_p_touchPrepRecvd_unc;
    public float p_touchPrepRecvd_unc;
    public String p_touchPrepRecvd_unc_Array[];
    public Vector samples_p_touchPrepRecvd_upenn;
    public float p_touchPrepRecvd_upenn;
    public String p_touchPrepRecvd_upenn_Array[];
    public Vector samples_p_touchPrepRecvd_uc;
    public float p_touchPrepRecvd_uc;
    public String p_touchPrepRecvd_uc_Array[];
    public Vector samples_p_touchPrepRecvd_uab;
    public float p_touchPrepRecvd_uab;
    public String p_touchPrepRecvd_uab_Array[];
    public Vector samples_p_touchPrepRecvd_mskcc;
    public float p_touchPrepRecvd_mskcc;
    public String p_touchPrepRecvd_mskcc_Array[];
    public Vector samples_p_touchPrepRecvd_gw;
    public float p_touchPrepRecvd_gw;
    public String p_touchPrepRecvd_gw_Array[];
    public Vector samples_p_touchPrepRecvd_uw;
    public float p_touchPrepRecvd_uw;
    public String p_touchPrepRecvd_uw_Array[];
    public Vector samples_p_touchPrepRecvd_ut;
    public float p_touchPrepRecvd_ut;
    public String p_touchPrepRecvd_ut_Array[];
    public Vector samples_p_yes_touchPrepRecvd_allInstitutes;
    public float p_yes_touchPrepRecvd_allInstitutes;
    public String p_yes_touchPrepRecvd_allInstitutes_Array[];
    public Vector samples_p_yes_touchPrepRecvd_ucsf;
    public float p_yes_touchPrepRecvd_ucsf;
    public String p_yes_touchPrepRecvd_ucsf_Array[];
    public Vector samples_p_yes_touchPrepRecvd_unc;
    public float p_yes_touchPrepRecvd_unc;
    public String p_yes_touchPrepRecvd_unc_Array[];
    public Vector samples_p_yes_touchPrepRecvd_upenn;
    public float p_yes_touchPrepRecvd_upenn;
    public String p_yes_touchPrepRecvd_upenn_Array[];
    public Vector samples_p_yes_touchPrepRecvd_uc;
    public float p_yes_touchPrepRecvd_uc;
    public String p_yes_touchPrepRecvd_uc_Array[];
    public Vector samples_p_yes_touchPrepRecvd_uab;
    public float p_yes_touchPrepRecvd_uab;
    public String p_yes_touchPrepRecvd_uab_Array[];
    public Vector samples_p_yes_touchPrepRecvd_mskcc;
    public float p_yes_touchPrepRecvd_mskcc;
    public String p_yes_touchPrepRecvd_mskcc_Array[];
    public Vector samples_p_yes_touchPrepRecvd_gw;
    public float p_yes_touchPrepRecvd_gw;
    public String p_yes_touchPrepRecvd_gw_Array[];
    public Vector samples_p_yes_touchPrepRecvd_uw;
    public float p_yes_touchPrepRecvd_uw;
    public String p_yes_touchPrepRecvd_uw_Array[];
    public Vector samples_p_yes_touchPrepRecvd_ut;
    public float p_yes_touchPrepRecvd_ut;
    public String p_yes_touchPrepRecvd_ut_Array[];
    public Vector samples_p_no_touchPrepRecvd_allInstitutes;
    public float p_no_touchPrepRecvd_allInstitutes;
    public String p_no_touchPrepRecvd_allInstitutes_Array[];
    public Vector samples_p_no_touchPrepRecvd_ucsf;
    public float p_no_touchPrepRecvd_ucsf;
    public String p_no_touchPrepRecvd_ucsf_Array[];
    public Vector samples_p_no_touchPrepRecvd_unc;
    public float p_no_touchPrepRecvd_unc;
    public String p_no_touchPrepRecvd_unc_Array[];
    public Vector samples_p_no_touchPrepRecvd_upenn;
    public float p_no_touchPrepRecvd_upenn;
    public String p_no_touchPrepRecvd_upenn_Array[];
    public Vector samples_p_no_touchPrepRecvd_uc;
    public float p_no_touchPrepRecvd_uc;
    public String p_no_touchPrepRecvd_uc_Array[];
    public Vector samples_p_no_touchPrepRecvd_uab;
    public float p_no_touchPrepRecvd_uab;
    public String p_no_touchPrepRecvd_uab_Array[];
    public Vector samples_p_no_touchPrepRecvd_mskcc;
    public float p_no_touchPrepRecvd_mskcc;
    public String p_no_touchPrepRecvd_mskcc_Array[];
    public Vector samples_p_no_touchPrepRecvd_gw;
    public float p_no_touchPrepRecvd_gw;
    public String p_no_touchPrepRecvd_gw_Array[];
    public Vector samples_p_no_touchPrepRecvd_uw;
    public float p_no_touchPrepRecvd_uw;
    public String p_no_touchPrepRecvd_uw_Array[];
    public Vector samples_p_no_touchPrepRecvd_ut;
    public float p_no_touchPrepRecvd_ut;
    public String p_no_touchPrepRecvd_ut_Array[];
    public Vector samples_p_blank_touchPrepRecvd_allInstitutes;
    public float p_blank_touchPrepRecvd_allInstitutes;
    public String p_blank_touchPrepRecvd_allInstitutes_Array[];
    public Vector samples_p_blank_touchPrepRecvd_ucsf;
    public float p_blank_touchPrepRecvd_ucsf;
    public String p_blank_touchPrepRecvd_ucsf_Array[];
    public Vector samples_p_blank_touchPrepRecvd_unc;
    public float p_blank_touchPrepRecvd_unc;
    public String p_blank_touchPrepRecvd_unc_Array[];
    public Vector samples_p_blank_touchPrepRecvd_upenn;
    public float p_blank_touchPrepRecvd_upenn;
    public String p_blank_touchPrepRecvd_upenn_Array[];
    public Vector samples_p_blank_touchPrepRecvd_uc;
    public float p_blank_touchPrepRecvd_uc;
    public String p_blank_touchPrepRecvd_uc_Array[];
    public Vector samples_p_blank_touchPrepRecvd_uab;
    public float p_blank_touchPrepRecvd_uab;
    public String p_blank_touchPrepRecvd_uab_Array[];
    public Vector samples_p_blank_touchPrepRecvd_mskcc;
    public float p_blank_touchPrepRecvd_mskcc;
    public String p_blank_touchPrepRecvd_mskcc_Array[];
    public Vector samples_p_blank_touchPrepRecvd_gw;
    public float p_blank_touchPrepRecvd_gw;
    public String p_blank_touchPrepRecvd_gw_Array[];
    public Vector samples_p_blank_touchPrepRecvd_uw;
    public float p_blank_touchPrepRecvd_uw;
    public String p_blank_touchPrepRecvd_uw_Array[];
    public Vector samples_p_blank_touchPrepRecvd_ut;
    public float p_blank_touchPrepRecvd_ut;
    public String p_blank_touchPrepRecvd_ut_Array[];
    public Vector samples_p_touchPrepRecvd_alltps;
    public float p_touchPrepRecvd_alltps;
    public String p_touchPrepRecvd_alltps_Array[];
    public Vector samples_p_touchPrepRecvd_p1;
    public float p_touchPrepRecvd_p1;
    public String p_touchPrepRecvd_p1_Array[];
    public Vector samples_p_touchPrepRecvd_p2;
    public float p_touchPrepRecvd_p2;
    public String p_touchPrepRecvd_p2_Array[];
    public Vector samples_p_touchPrepRecvd_p3;
    public float p_touchPrepRecvd_p3;
    public String p_touchPrepRecvd_p3_Array[];
    public Vector samples_p_touchPrepRecvd_p4;
    public float p_touchPrepRecvd_p4;
    public String p_touchPrepRecvd_p4_Array[];
    public Vector samples_p_touchPrepRecvd_p5;
    public float p_touchPrepRecvd_p5;
    public String p_touchPrepRecvd_p5_Array[];
    public Vector samples_p_touchPrepRecvd_p6;
    public float p_touchPrepRecvd_p6;
    public String p_touchPrepRecvd_p6_Array[];
    public Vector samples_p_touchPrepRecvd_ps;
    public float p_touchPrepRecvd_ps;
    public String p_touchPrepRecvd_ps_Array[];
    public Vector samples_p_yes_touchPrepRecvd_alltps;
    public float p_yes_touchPrepRecvd_alltps;
    public String p_yes_touchPrepRecvd_alltps_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p1;
    public float p_yes_touchPrepRecvd_p1;
    public String p_yes_touchPrepRecvd_p1_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p2;
    public float p_yes_touchPrepRecvd_p2;
    public String p_yes_touchPrepRecvd_p2_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p3;
    public float p_yes_touchPrepRecvd_p3;
    public String p_yes_touchPrepRecvd_p3_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p4;
    public float p_yes_touchPrepRecvd_p4;
    public String p_yes_touchPrepRecvd_p4_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p5;
    public float p_yes_touchPrepRecvd_p5;
    public String p_yes_touchPrepRecvd_p5_Array[];
    public Vector samples_p_yes_touchPrepRecvd_p6;
    public float p_yes_touchPrepRecvd_p6;
    public String p_yes_touchPrepRecvd_p6_Array[];
    public Vector samples_p_yes_touchPrepRecvd_ps;
    public float p_yes_touchPrepRecvd_ps;
    public String p_yes_touchPrepRecvd_ps_Array[];
    public Vector samples_p_no_touchPrepRecvd_alltps;
    public float p_no_touchPrepRecvd_alltps;
    public String p_no_touchPrepRecvd_alltps_Array[];
    public Vector samples_p_no_touchPrepRecvd_p1;
    public float p_no_touchPrepRecvd_p1;
    public String p_no_touchPrepRecvd_p1_Array[];
    public Vector samples_p_no_touchPrepRecvd_p2;
    public float p_no_touchPrepRecvd_p2;
    public String p_no_touchPrepRecvd_p2_Array[];
    public Vector samples_p_no_touchPrepRecvd_p3;
    public float p_no_touchPrepRecvd_p3;
    public String p_no_touchPrepRecvd_p3_Array[];
    public Vector samples_p_no_touchPrepRecvd_p4;
    public float p_no_touchPrepRecvd_p4;
    public String p_no_touchPrepRecvd_p4_Array[];
    public Vector samples_p_no_touchPrepRecvd_p5;
    public float p_no_touchPrepRecvd_p5;
    public String p_no_touchPrepRecvd_p5_Array[];
    public Vector samples_p_no_touchPrepRecvd_p6;
    public float p_no_touchPrepRecvd_p6;
    public String p_no_touchPrepRecvd_p6_Array[];
    public Vector samples_p_no_touchPrepRecvd_ps;
    public float p_no_touchPrepRecvd_ps;
    public String p_no_touchPrepRecvd_ps_Array[];
    public Vector samples_p_blank_touchPrepRecvd_alltps;
    public float p_blank_touchPrepRecvd_alltps;
    public String p_blank_touchPrepRecvd_alltps_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p1;
    public float p_blank_touchPrepRecvd_p1;
    public String p_blank_touchPrepRecvd_p1_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p2;
    public float p_blank_touchPrepRecvd_p2;
    public String p_blank_touchPrepRecvd_p2_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p3;
    public float p_blank_touchPrepRecvd_p3;
    public String p_blank_touchPrepRecvd_p3_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p4;
    public float p_blank_touchPrepRecvd_p4;
    public String p_blank_touchPrepRecvd_p4_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p5;
    public float p_blank_touchPrepRecvd_p5;
    public String p_blank_touchPrepRecvd_p5_Array[];
    public Vector samples_p_blank_touchPrepRecvd_p6;
    public float p_blank_touchPrepRecvd_p6;
    public String p_blank_touchPrepRecvd_p6_Array[];
    public Vector samples_p_blank_touchPrepRecvd_ps;
    public float p_blank_touchPrepRecvd_ps;
    public String p_blank_touchPrepRecvd_ps_Array[];
    public Vector samples_dna;
    public Vector samples_no_dna;
    public Vector samples_good_dna;
    public Vector samples_fair_dna;
    public Vector samples_poor_dna;
    public float samples_good_dna_ratio;
    public float samples_fair_dna_ratio;
    public float samples_poor_dna_ratio;
    public String good_dna_ratio_Array[];
    public String fair_dna_ratio_Array[];
    public String poor_dna_ratio_Array[];
    public Vector samples_rna;
    public Vector samples_no_rna;
    public Vector samples_good_rna;
    public Vector samples_fair_rna;
    public Vector samples_poor_rna;
    public float samples_good_rna_ratio;
    public float samples_fair_rna_ratio;
    public float samples_poor_rna_ratio;
    public String good_rna_ratio_Array[];
    public String fair_rna_ratio_Array[];
    public String poor_rna_ratio_Array[];
    public Qi_dna dna;
    public Vector subsamples_pcr;
    public Vector subsamples_no_pcr;
    public Vector subsamples_best_pcr;
    public Vector subsamples_good_pcr;
    public Vector subsamples_poor_pcr;
    public Vector subsamples_worst_pcr;
    public float subsamples_best_pcr_ratio;
    public float subsamples_good_pcr_ratio;
    public float subsamples_poor_pcr_ratio;
    public float subsamples_worst_pcr_ratio;
    public String subsamples_best_pcr_ratio_Array[];
    public String subsamples_good_pcr_ratio_Array[];
    public String subsamples_poor_pcr_ratio_Array[];
    public String subsamples_worst_pcr_ratio_Array[];
    public Qi_rna rna;
    public Vector subsamples_rna;
    public Vector subsamples_no_rna;
    public Vector subsamples_good_rna;
    public Vector subsamples_fair_rna;
    public Vector subsamples_poor_rna;
    public float subsamples_good_rna_ratio;
    public float subsamples_fair_rna_ratio;
    public float subsamples_poor_rna_ratio;
    public String subsamples_good_rna_ratio_Array[];
    public String subsamples_fair_rna_ratio_Array[];
    public String subsamples_poor_rna_ratio_Array[];
    public Qi_protein protein;
    public Vector proteins;
    public Vector protein_tumor;
    public Vector protein_no_tumor;
    private String ptNumArray[];
    private String touchPrep_f_Array[];
    private String touchPrep_f_pos_Array[];
    private String touchPrep_f_neg_Array[];
    private String touchPrep_f_pos_neg_Array[];
    private String touch_prep_allInstitutes_Array[];
    private String touch_prep_ucsf_Array[];
    private String touch_prep_unc_Array[];
    private String touch_prep_upenn_Array[];
    private String touch_prep_uc_Array[];
    private String touch_prep_uab_Array[];
    private String touch_prep_mskcc_Array[];
    private String touch_prep_gw_Array[];
    private String touch_prep_uw_Array[];
    private String touch_prep_ut_Array[];
    private String pos_touch_prep_allInstitutes_Array[];
    private String pos_touch_prep_ucsf_Array[];
    private String pos_touch_prep_unc_Array[];
    private String pos_touch_prep_upenn_Array[];
    private String pos_touch_prep_uc_Array[];
    private String pos_touch_prep_uab_Array[];
    private String pos_touch_prep_mskcc_Array[];
    private String pos_touch_prep_gw_Array[];
    private String pos_touch_prep_uw_Array[];
    private String pos_touch_prep_ut_Array[];
    private String pos_neg_touch_prep_allInstitutes_Array[];
    private String pos_neg_touch_prep_ucsf_Array[];
    private String pos_neg_touch_prep_unc_Array[];
    private String pos_neg_touch_prep_upenn_Array[];
    private String pos_neg_touch_prep_uc_Array[];
    private String pos_neg_touch_prep_uab_Array[];
    private String pos_neg_touch_prep_mskcc_Array[];
    private String pos_neg_touch_prep_gw_Array[];
    private String pos_neg_touch_prep_uw_Array[];
    private String pos_neg_touch_prep_ut_Array[];
    private String neg_touch_prep_allInstitutes_Array[];
    private String neg_touch_prep_ucsf_Array[];
    private String neg_touch_prep_unc_Array[];
    private String neg_touch_prep_upenn_Array[];
    private String neg_touch_prep_uc_Array[];
    private String neg_touch_prep_uab_Array[];
    private String neg_touch_prep_mskcc_Array[];
    private String neg_touch_prep_gw_Array[];
    private String neg_touch_prep_uw_Array[];
    private String neg_touch_prep_ut_Array[];
    private String touch_prep_alltps_Array[];
    private String touch_prep_f1_Array[];
    private String touch_prep_f2_Array[];
    private String touch_prep_f3_Array[];
    private String touch_prep_f4_Array[];
    private String touch_prep_f5_Array[];
    private String touch_prep_f6_Array[];
    private String touch_prep_fs_Array[];
    private String pos_touch_prep_alltps_Array[];
    private String pos_touch_prep_f1_Array[];
    private String pos_touch_prep_f2_Array[];
    private String pos_touch_prep_f3_Array[];
    private String pos_touch_prep_f4_Array[];
    private String pos_touch_prep_f5_Array[];
    private String pos_touch_prep_f6_Array[];
    private String pos_touch_prep_fs_Array[];
    private String pos_neg_touch_prep_alltps_Array[];
    private String pos_neg_touch_prep_f1_Array[];
    private String pos_neg_touch_prep_f2_Array[];
    private String pos_neg_touch_prep_f3_Array[];
    private String pos_neg_touch_prep_f4_Array[];
    private String pos_neg_touch_prep_f5_Array[];
    private String pos_neg_touch_prep_f6_Array[];
    private String pos_neg_touch_prep_fs_Array[];
    private String neg_touch_prep_alltps_Array[];
    private String neg_touch_prep_f1_Array[];
    private String neg_touch_prep_f2_Array[];
    private String neg_touch_prep_f3_Array[];
    private String neg_touch_prep_f4_Array[];
    private String neg_touch_prep_f5_Array[];
    private String neg_touch_prep_f6_Array[];
    private String neg_touch_prep_fs_Array[];
    private int dna_with_labeling_total;
    private int dna_labels_total;
    private int high_dna_labels_total;
    private int medium_dna_labels_total;
    private int low_dna_labels_total;
    private int dna_with_cgh_total;
    private int cgh_hybs_total;
    private int good_cgh_hybs_total;
    private int poor_cgh_hybs_total;
}
