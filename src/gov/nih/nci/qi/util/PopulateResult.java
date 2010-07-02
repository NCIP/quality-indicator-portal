// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import gov.nih.nci.qi.SampleEx;
import gov.nih.nci.qi.db.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.servlet.http.HttpSession;
import org.apache.poi.hssf.usermodel.*;

// Referenced classes of package gov.nih.nci.qi.util:
//            MessageRetriever

public class PopulateResult
{

    public PopulateResult()
    {
        resultSet = new Vector();
        formatter = new SimpleDateFormat("MM/dd/yyyy");
        workBook = new HSSFWorkbook();
        file = new File(searchResultFile);
    }

    public void run(Vector vector, String s, String s1)
    {
        try
        {
            int i = 1;
            if(vector != null && vector.size() >= 1)
            {
                sheet = workBook.createSheet();
                HSSFRow hssfrow = sheet.createRow(i);
                hssfrow.createCell((short)0).setCellValue("I-SPY Patient ID");
                hssfrow.createCell((short)1).setCellValue("LabTrak ID");
                hssfrow.createCell((short)2).setCellValue("Institution(Core Collected)");
                hssfrow.createCell((short)3).setCellValue("T.P.");
                hssfrow.createCell((short)4).setCellValue("Core Type");
                hssfrow.createCell((short)5).setCellValue("Sample Process Date");
                hssfrow.createCell((short)6).setCellValue("Comment");
                hssfrow.createCell((short)7).setCellValue("Frozen Core Touch Prep");
                hssfrow.createCell((short)8).setCellValue("Frozen Core H&E Tumor Review");
                hssfrow.createCell((short)9).setCellValue("Tumor Density (%)");
                hssfrow.createCell((short)10).setCellValue("Non-Tumor Nucleated Cells (%)");
                hssfrow.createCell((short)11).setCellValue("DNA Process Date");
                hssfrow.createCell((short)12).setCellValue("DNA Yield");
                hssfrow.createCell((short)13).setCellValue("DNA Reading Instrument");
                hssfrow.createCell((short)14).setCellValue("DNA Extraction Quality");
                hssfrow.createCell((short)15).setCellValue("P53 Multiplex PCR Rating");
                hssfrow.createCell((short)16).setCellValue("DNA Labeling Attempts");
                hssfrow.createCell((short)17).setCellValue("Intensity of Labeling DNA at Last Attempt");
                hssfrow.createCell((short)18).setCellValue("Date of Labeling DNA at Last Attempt");
                hssfrow.createCell((short)19).setCellValue("CGH Hybridization Attempts");
                hssfrow.createCell((short)20).setCellValue("CGH Hybridization Quality at Last Attempt");
                hssfrow.createCell((short)21).setCellValue("Date of CGH Hybridization at Last Attempt");
                hssfrow.createCell((short)22).setCellValue("RNA Process Date");
                hssfrow.createCell((short)23).setCellValue("RNA Yield");
                hssfrow.createCell((short)24).setCellValue("RNA Reading Instrument");
                hssfrow.createCell((short)25).setCellValue("RNA Extraction Quality");
                hssfrow.createCell((short)26).setCellValue("RNA Analysis Quality");
                hssfrow.createCell((short)27).setCellValue("Paraffin Core H&E Review");
                hssfrow.createCell((short)28).setCellValue("Paraffin Core H&E Usability");
                hssfrow.createCell((short)29).setCellValue("Paraffin Core Touch Preps Received");
                hssfrow.createCell((short)30).setCellValue("Protein Sample Microdissection Efficiency of Capture");
                hssfrow.createCell((short)31).setCellValue("Protein Sample Tumor Presence");
                int j = 0;
                for(int k = 0; k < vector.size(); k++)
                {
                    j++;
                    SampleEx sampleex = (SampleEx)vector.elementAt(k);
                    i++;
                    HSSFRow hssfrow1 = sheet.createRow(i);
                    hssfrow1.createCell((short)0).setCellValue(sampleex.getPatient_accrual());
                    hssfrow1.createCell((short)1).setCellValue(sampleex.getLabtrak_id());
                    if(sampleex.getSample_generating_institute_id() != null)
                    {
                        Qi_institution qi_institution = new Qi_institution();
                        Vector vector1 = qi_institution.retrieveAllWhere((new StringBuilder()).append("INSTITUTION_ID = ").append(sampleex.getSample_generating_institute_id()).toString());
                        for(int l = 0; l < vector1.size(); l++)
                        {
                            Qi_institution qi_institution1 = (Qi_institution)vector1.elementAt(l);
                            hssfrow1.createCell((short)2).setCellValue(qi_institution1.getInstitute_name());
                        }

                    }
                    hssfrow1.createCell((short)3).setCellValue(sampleex.getTimepoint_name());
                    if(sampleex.getCore_type() != null)
                    {
                        Core_type core_type = new Core_type();
                        Vector vector2 = core_type.retrieveAllWhere((new StringBuilder()).append("CORE_TYPE = '").append(sampleex.getCore_type()).append("'").toString());
                        for(int i1 = 0; i1 < vector2.size(); i1++)
                        {
                            Core_type core_type1 = (Core_type)vector2.elementAt(i1);
                            hssfrow1.createCell((short)4).setCellValue(core_type1.getCore_type_desc());
                        }

                    }
                    if(sampleex.getProcess_date() != null)
                        hssfrow1.createCell((short)5).setCellValue(formatter.format(sampleex.getProcess_date()));
                    if(sampleex.getQi_comment() != null)
                        hssfrow1.createCell((short)6).setCellValue(sampleex.getQi_comment());
                    if(sampleex.getFrozen_touch_prep() != null)
                    {
                        Qi_frozen_touchprep qi_frozen_touchprep = new Qi_frozen_touchprep();
                        Vector vector3 = qi_frozen_touchprep.retrieveAllWhere((new StringBuilder()).append("TOUCH_PREP = '").append(sampleex.getFrozen_touch_prep()).append("'").toString());
                        for(int j1 = 0; j1 < vector3.size(); j1++)
                        {
                            Qi_frozen_touchprep qi_frozen_touchprep1 = (Qi_frozen_touchprep)vector3.elementAt(j1);
                            hssfrow1.createCell((short)7).setCellValue(qi_frozen_touchprep1.getTouch_prep());
                        }

                    }
                    if(sampleex.getFrozen_h_e() != null)
                    {
                        Qi_frozen_h_e qi_frozen_h_e = new Qi_frozen_h_e();
                        Vector vector4 = qi_frozen_h_e.retrieveAllWhere((new StringBuilder()).append("H_E_REVIEW = '").append(sampleex.getFrozen_h_e()).append("'").toString());
                        for(int k1 = 0; k1 < vector4.size(); k1++)
                        {
                            Qi_frozen_h_e qi_frozen_h_e1 = (Qi_frozen_h_e)vector4.elementAt(k1);
                            hssfrow1.createCell((short)8).setCellValue(qi_frozen_h_e1.getH_e_review());
                        }

                    }
                    hssfrow1.createCell((short)9).setCellValue(sampleex.getTumorpresence());
                    hssfrow1.createCell((short)10).setCellValue(sampleex.getNontumor());
                    if(sampleex.getDna_process_date() != null)
                        hssfrow1.createCell((short)11).setCellValue(formatter.format(sampleex.getDna_process_date()));
                    if(sampleex.getDna_reading() != null)
                        hssfrow1.createCell((short)12).setCellValue((new StringBuilder()).append(sampleex.getDna_reading()).append(sampleex.getDna_quantity_unit()).toString());
                    if(sampleex.getDna_instrument_id() != null)
                    {
                        Qi_instrument qi_instrument = new Qi_instrument();
                        Vector vector5 = qi_instrument.retrieveAllWhere((new StringBuilder()).append("INSTRUMENT_ID = ").append(sampleex.getDna_instrument_id()).toString());
                        for(int l1 = 0; l1 < vector5.size(); l1++)
                        {
                            Qi_instrument qi_instrument1 = (Qi_instrument)vector5.elementAt(l1);
                            hssfrow1.createCell((short)13).setCellValue(qi_instrument1.getInstrument_name());
                        }

                    }
                    if(sampleex.getDna_extraction_quality() != null)
                    {
                        Qi_quality qi_quality = new Qi_quality();
                        Vector vector6 = qi_quality.retrieveAllWhere((new StringBuilder()).append("QUALITY_NAME = '").append(sampleex.getDna_extraction_quality()).append("'").append(" AND QUALITY_CATEGORY = ").append("'").append("DNA").append("'").toString());
                        for(int i2 = 0; i2 < vector6.size(); i2++)
                        {
                            Qi_quality qi_quality1 = (Qi_quality)vector6.elementAt(i2);
                            hssfrow1.createCell((short)14).setCellValue(qi_quality1.getQuality_desc());
                        }

                    }
                    String s2 = null;
                    Qi_dna qi_dna = new Qi_dna();
                    if(s != null && !s.equals(""))
                        s2 = (new StringBuilder()).append(" dna_id IN (SELECT DNA_ID FROM QI_DNA WHERE SAMPLE_ID = ").append(sampleex.getSample_id()).append(" AND PCR_RATING =").append(s).append(")").toString();
                    else
                        s2 = (new StringBuilder()).append("SAMPLE_ID = ").append(sampleex.getSample_id()).toString();
                    if(s2 != null && !s2.equals(""))
                    {
                        Vector vector7 = qi_dna.retrieveAllWhere(s2);
                        if(vector7.size() > 0)
                        {
                            for(int j2 = 0; j2 < vector7.size(); j2++)
                            {
                                Qi_dna qi_dna1 = (Qi_dna)vector7.elementAt(j2);
                                hssfrow1.createCell((short)15).setCellValue(qi_dna1.getPcr_rating());
                            }

                        }
                    }
                    Qi_dna_labeling qi_dna_labeling = new Qi_dna_labeling();
                    Qi_cgh_hybridization qi_cgh_hybridization = new Qi_cgh_hybridization();
                    Qi_dna_labeling_intensity qi_dna_labeling_intensity = new Qi_dna_labeling_intensity();
                    Qi_cgh_quality qi_cgh_quality = new Qi_cgh_quality();
                    Vector vector8 = new Vector();
                    Vector vector9 = new Vector();
                    Vector vector10 = new Vector();
                    Vector vector12 = new Vector();
                    vector8 = qi_dna_labeling.retrieveAllWhere((new StringBuilder()).append(" DNA_ID IN (SELECT DNA_ID FROM QI_DNA WHERE sample_id IN (SELECT sample_id FROM QI_SAMPLE WHERE sample_id='").append(sampleex.getSample_id()).append("'))").toString());
                    hssfrow1.createCell((short)16).setCellValue(vector8.size());
                    int k2 = 0;
                    for(int l2 = 0; l2 < vector8.size(); l2++)
                    {
                        qi_dna_labeling = (Qi_dna_labeling)vector8.elementAt(l2);
                        if(qi_dna_labeling.getLabeling_id().intValue() > k2)
                            k2 = qi_dna_labeling.getLabeling_id().intValue();
                    }

                    if(k2 > 0)
                        vector8 = qi_dna_labeling.retrieveAllWhere((new StringBuilder()).append(" LABELING_ID = ").append(new Long(k2)).toString());
                    if(vector8.size() > 0)
                    {
                        for(int i3 = 0; i3 < vector8.size(); i3++)
                        {
                            Qi_dna_labeling qi_dna_labeling1 = (Qi_dna_labeling)vector8.elementAt(i3);
                            Vector vector11 = qi_dna_labeling_intensity.retrieveAllWhere((new StringBuilder()).append(" INTENSITY_ID=").append(qi_dna_labeling1.getIntensity_id()).toString());
                            for(int k3 = 0; k3 < vector11.size(); k3++)
                            {
                                qi_dna_labeling_intensity = (Qi_dna_labeling_intensity)vector11.elementAt(k3);
                                hssfrow1.createCell((short)17).setCellValue(qi_dna_labeling_intensity.getIntensity_desc());
                            }

                            if(qi_dna_labeling1.getLabeling_date() != null)
                                hssfrow1.createCell((short)18).setCellValue(formatter.format(qi_dna_labeling1.getLabeling_date()));
                        }

                    }
                    vector9 = qi_cgh_hybridization.retrieveAllWhere((new StringBuilder()).append(" DNA_ID IN (SELECT DNA_ID FROM QI_DNA WHERE sample_id IN (SELECT sample_id FROM QI_SAMPLE WHERE sample_id='").append(sampleex.getSample_id()).append("'))").toString());
                    hssfrow1.createCell((short)19).setCellValue(vector9.size());
                    if(vector9.size() > 0)
                    {
                        for(int j3 = 0; j3 < vector9.size(); j3++)
                        {
                            Qi_cgh_hybridization qi_cgh_hybridization1 = (Qi_cgh_hybridization)vector9.elementAt(vector9.size() - 1);
                            Vector vector13 = qi_cgh_quality.retrieveAllWhere((new StringBuilder()).append(" CGH_QUALITY_ID=").append(qi_cgh_hybridization1.getCgh_quality_id()).toString());
                            for(int l3 = 0; l3 < vector13.size(); l3++)
                                hssfrow1.createCell((short)20).setCellValue(((Qi_cgh_quality)vector13.elementAt(l3)).getCgh_quality_desc());

                            if(qi_cgh_hybridization1.getHybridization_date() != null)
                                hssfrow1.createCell((short)21).setCellValue(formatter.format(qi_cgh_hybridization1.getHybridization_date()));
                        }

                    }
                    if(sampleex.getRna_process_date() != null)
                        hssfrow1.createCell((short)22).setCellValue(formatter.format(sampleex.getRna_process_date()));
                    if(sampleex.getRna_reading() != null)
                        hssfrow1.createCell((short)23).setCellValue((new StringBuilder()).append(sampleex.getRna_reading()).append(sampleex.getRna_quantity_unit()).toString());
                    if(sampleex.getRna_instrument_id() != null)
                    {
                        Qi_instrument qi_instrument2 = new Qi_instrument();
                        Vector vector14 = qi_instrument2.retrieveAllWhere((new StringBuilder()).append("INSTRUMENT_ID = ").append(sampleex.getRna_instrument_id()).toString());
                        for(int i4 = 0; i4 < vector14.size(); i4++)
                        {
                            Qi_instrument qi_instrument3 = (Qi_instrument)vector14.elementAt(i4);
                            hssfrow1.createCell((short)24).setCellValue(qi_instrument3.getInstrument_name());
                        }

                    }
                    if(sampleex.getRna_extraction_quality() != null)
                    {
                        Qi_quality qi_quality2 = new Qi_quality();
                        Vector vector15 = qi_quality2.retrieveAllWhere((new StringBuilder()).append("QUALITY_NAME = '").append(sampleex.getRna_extraction_quality()).append("'").append(" AND QUALITY_CATEGORY = ").append("'").append("DNA").append("'").toString());
                        for(int j4 = 0; j4 < vector15.size(); j4++)
                        {
                            Qi_quality qi_quality3 = (Qi_quality)vector15.elementAt(j4);
                            hssfrow1.createCell((short)25).setCellValue(qi_quality3.getQuality_desc());
                        }

                    }
                    Qi_rna qi_rna = new Qi_rna();
                    String s3 = null;
                    if(s1 != null && !s1.equals(""))
                        s3 = (new StringBuilder()).append(" rna_id IN (SELECT RNA_ID FROM QI_RNA WHERE SAMPLE_ID = ").append(sampleex.getSample_id()).append(" AND RNA_ANALYSIS_QUALITY =").append("'").append(s1).append("'").append(")").toString();
                    if(s1 == null)
                        s3 = (new StringBuilder()).append("SAMPLE_ID = ").append(sampleex.getSample_id()).toString();
                    if(s3 == null)
                        continue;
                    Vector vector16 = qi_rna.retrieveAllWhere(s3);
                    if(vector16.size() > 0)
                    {
                        for(int k4 = 0; k4 < vector16.size(); k4++)
                        {
                            Qi_rna qi_rna1 = (Qi_rna)vector16.elementAt(k4);
                            if(qi_rna1.getRna_analysis_quality() == null)
                                continue;
                            Qi_quality qi_quality4 = new Qi_quality();
                            Vector vector21 = qi_quality4.retrieveAllWhere((new StringBuilder()).append("QUALITY_NAME = '").append(qi_rna1.getRna_analysis_quality()).append("'").append(" AND QUALITY_CATEGORY = ").append("'").append("RNA").append("'").toString());
                            for(int l5 = 0; l5 < vector21.size(); l5++)
                                qi_quality4 = (Qi_quality)vector21.elementAt(l5);

                            hssfrow1.createCell((short)26).setCellValue(qi_quality4.getQuality_desc());
                        }

                    }
                    if(sampleex.getH_e_review() != null)
                    {
                        Qi_h_e_review qi_h_e_review = new Qi_h_e_review();
                        Vector vector17 = qi_h_e_review.retrieveAllWhere((new StringBuilder()).append("H_E_REVIEW = ").append(sampleex.getH_e_review()).toString());
                        for(int l4 = 0; l4 < vector17.size(); l4++)
                        {
                            Qi_h_e_review qi_h_e_review1 = (Qi_h_e_review)vector17.elementAt(l4);
                            hssfrow1.createCell((short)27).setCellValue(qi_h_e_review1.getH_e_review_desc());
                        }

                    }
                    if(sampleex.getUsability() != null)
                    {
                        Qi_usability qi_usability = new Qi_usability();
                        Vector vector18 = qi_usability.retrieveAllWhere((new StringBuilder()).append("USABILITY = ").append(sampleex.getUsability()).toString());
                        for(int i5 = 0; i5 < vector18.size(); i5++)
                        {
                            Qi_usability qi_usability1 = (Qi_usability)vector18.elementAt(i5);
                            hssfrow1.createCell((short)28).setCellValue(qi_usability1.getUsability_desc());
                        }

                    }
                    if(sampleex.getTouchprep_recvd() != null)
                    {
                        Qi_touchprep_recvd qi_touchprep_recvd = new Qi_touchprep_recvd();
                        Vector vector19 = qi_touchprep_recvd.retrieveAllWhere((new StringBuilder()).append("TOUCHPREP_RECVD = ").append(sampleex.getTouchprep_recvd()).toString());
                        for(int j5 = 0; j5 < vector19.size(); j5++)
                        {
                            Qi_touchprep_recvd qi_touchprep_recvd1 = (Qi_touchprep_recvd)vector19.elementAt(j5);
                            hssfrow1.createCell((short)29).setCellValue(qi_touchprep_recvd1.getTouchprep_recvd_desc());
                        }

                    }
                    Qi_protein qi_protein = new Qi_protein();
                    Vector vector20 = qi_protein.retrieveAllWhere((new StringBuilder()).append("SAMPLE_ID = ").append(sampleex.getSample_id()).toString());
                    if(vector20.size() <= 0)
                        continue;
                    for(int k5 = 0; k5 < vector20.size(); k5++)
                    {
                        Qi_protein qi_protein1 = (Qi_protein)vector20.elementAt(k5);
                        hssfrow1.createCell((short)30).setCellValue(qi_protein1.getMicodissection_efficiency());
                        hssfrow1.createCell((short)31).setCellValue(qi_protein1.getMicodissection_efficiency());
                    }

                }

                System.out.println((new StringBuilder()).append("p:").append(j).toString());
            }
            fos = new FileOutputStream(file);
            workBook.write(fos);
            fos.close();
            writeBook(workBook, fos);
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("e:").append(exception).toString());
            exception.printStackTrace();
        }
    }

    public void writeBook(HSSFWorkbook hssfworkbook, FileOutputStream fileoutputstream)
    {
        try
        {
        hssfworkbook.write(fileoutputstream);

            fileoutputstream.close();
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        } finally {
        try {fileoutputstream.close();} catch (Exception exc) {}
      }
 
    }

    private static final short ONE = 1;
    private static final short TWO = 2;
    private static final short THREE = 3;
    private static final short FOUR = 4;
    private static final short FIVE = 5;
    private static final short SIX = 6;
    private static final short SEVEN = 7;
    private static final short EIGHT = 8;
    private static final short NINE = 9;
    private static final short TEN = 10;
    private static final short ELEVEN = 11;
    private static final short TWELVE = 12;
    private static final short THIRTEEN = 13;
    private static final short FOURTEEN = 14;
    private static final short FIFTEEN = 15;
    private static final short SIXTEEN = 16;
    private static final short SEVENTEEN = 17;
    private static final short EIGHTEEN = 18;
    private static final short NINETEEN = 19;
    private static final short TWENTY = 20;
    private static final short TWENTYONE = 21;
    private static final short TWENTYTWO = 22;
    private static final short TWENTYTHREE = 23;
    private static final short TWENTYFOUR = 24;
    private static final short TWENTYFIVE = 25;
    private static final short TWENTYSIX = 26;
    private static final short TWENTYSEVEN = 27;
    private static final short TWENTYEIGHT = 28;
    private static final short TWENTYNINE = 29;
    private static final short THIRTY = 30;
    private static final short THIRTYONE = 31;
    private static final short THIRTYTWO = 32;
    private static final short THIRTYTHREE = 33;
    private static final short THIRTYFOUR = 34;
    private HSSFWorkbook workBook;
    private HSSFSheet sheet;
    private HSSFCell cell;
    private File file;
    private static final String searchResultFile = MessageRetriever.getProperty("QI_SEARCH_RESULT_FILE");
    private static final String summaryFile = MessageRetriever.getProperty("QI_SUMMARY_FILE");
    private FileOutputStream fos;
    private Vector resultSet;
    private HttpSession session;
    private SimpleDateFormat formatter;

}
