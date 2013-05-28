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

import java.io.*;
import java.util.Hashtable;
import org.apache.poi.hssf.usermodel.*;

// Referenced classes of package gov.nih.nci.qi.util:
//            MessageRetriever

public class PopulateSummaryReport
{

    public PopulateSummaryReport()
    {
        workBook = new HSSFWorkbook();
        file = new File(summaryFile);
    }

    public void run(Hashtable hashtable, String s)
    {
        try
        {
            int i = 1;
            if(hashtable != null && hashtable.size() >= 1)
            {
                String s1 = MessageRetriever.getMessage((new StringBuilder()).append(s).append("Total").toString());
                sheet = workBook.createSheet();
                for(int j = 1; j <= (new Integer(s1)).intValue(); j++)
                {
                    String s2 = MessageRetriever.getMessage((new StringBuilder()).append(s).append(j).append("Header").toString());
                    String s3 = MessageRetriever.getMessage((new StringBuilder()).append(s).append(j).append("MultipleColumns").toString());
                    HSSFRow hssfrow = sheet.createRow(i);
                    i++;
                    if(j == 1)
                    {
                        hssfrow.createCell((short)0).setCellValue(s2);
                        hssfrow.createCell((short)1).setCellValue((String)hashtable.get(s2));
                        continue;
                    }
                    if(j == 2)
                    {
                        hssfrow.createCell((short)0).setCellValue(s2);
                        String as[] = (String[])(String[])hashtable.get(s2);
                        dataBuf = new StringBuffer();
                        for(int l = 0; l < as.length; l++)
                            dataBuf.append((new StringBuilder()).append(as[l]).append(", ").toString());

                        if(dataBuf.length() > 1)
                            dataBuf.deleteCharAt(dataBuf.length() - 2);
                        hssfrow.createCell((short)1).setCellValue(dataBuf.toString());
                        continue;
                    }
                    if(j <= 2)
                        continue;
                    hssfrow.createCell((short)0).setCellValue(s2);
                    int k = 0;
                    String s4 = null;
                    if(s3 != null)
                    {
                        k = (new Integer(s3)).intValue();
                        s4 = MessageRetriever.getMessage((new StringBuilder()).append(s).append(j).append(k).append("Header").toString());
                    }
                    Object obj = null;
                    if(s4 == null)
                        continue;
                    for(int i1 = 1; i1 <= k; i1++)
                    {
                        String s5 = MessageRetriever.getMessage((new StringBuilder()).append(s4).append(i1).append("Header").toString());
                        if(i1 == 1)
                            hssfrow.createCell((short)1).setCellValue(s5);
                        if(i1 == 2)
                            hssfrow.createCell((short)2).setCellValue(s5);
                        if(i1 == 3)
                            hssfrow.createCell((short)3).setCellValue(s5);
                        if(i1 == 4)
                            hssfrow.createCell((short)4).setCellValue(s5);
                        if(i1 == 5)
                            hssfrow.createCell((short)5).setCellValue(s5);
                        if(i1 == 6)
                            hssfrow.createCell((short)6).setCellValue(s5);
                        if(i1 == 7)
                            hssfrow.createCell((short)7).setCellValue(s5);
                    }

                    hssfrow = sheet.createRow(i);
                    i++;
                    byte byte0 = 0;
                    for(int j1 = 1; j1 <= k; j1++)
                    {
                        if(j1 == 2)
                            byte0 = 2;
                        if(j1 == 3)
                            byte0 = 3;
                        if(j1 == 4)
                            byte0 = 4;
                        if(j1 == 5)
                            byte0 = 5;
                        if(j1 == 6)
                            byte0 = 6;
                        if(j1 == 7)
                            byte0 = 7;
                        String s6 = MessageRetriever.getMessage((new StringBuilder()).append(s4).append(j1).append("Header").toString());
                        String s7 = MessageRetriever.getMessage((new StringBuilder()).append(s).append(j).append("SubRows").toString());
                        if(s7 == null)
                            continue;
                        for(int k1 = 1; k1 <= (new Integer(s7)).intValue(); k1++)
                        {
                            String as1[] = (String[])(String[])hashtable.get((new StringBuilder()).append(s6).append(k1).toString());
                            dataBuf = new StringBuffer();
                            if(as1 != null)
                            {
                                for(int l1 = 0; l1 < as1.length; l1++)
                                    dataBuf.append((new StringBuilder()).append(as1[l1]).append(", ").toString());

                                if(dataBuf.length() > 1)
                                    dataBuf.deleteCharAt(dataBuf.length() - 2);
                            }
                            System.out.print((new StringBuilder()).append("before:").append(dataBuf.toString()).toString());
                            if(j1 == 1)
                                if((new Integer(s7)).intValue() == 1)
                                {
                                    String s8 = "";
                                    if(dataBuf.toString().lastIndexOf(",") != -1)
                                    {
                                        String s9 = dataBuf.toString().substring(0, dataBuf.toString().lastIndexOf(","));
                                        hssfrow.createCell((short)1).setCellValue(s9);
                                    } else
                                    {
                                        hssfrow.createCell((short)1).setCellValue(dataBuf.toString());
                                    }
                                } else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    String s10 = "";
                                    String s12 = "";
                                    if(dataBuf.toString().lastIndexOf(",") != -1 && dataBuf.toString().lastIndexOf("%") != -1)
                                    {
                                        String s11 = dataBuf.toString().substring(0, dataBuf.toString().lastIndexOf(","));
                                        String s13 = dataBuf.toString().substring(dataBuf.toString().lastIndexOf("%") + 1);
                                        hssfrow.createCell((short)1).setCellValue((new StringBuilder()).append(s11).append(s13).toString());
                                    } else
                                    {
                                        hssfrow.createCell((short)1).setCellValue(dataBuf.toString());
                                    }
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 == 2)
                                if((new Integer(s7)).intValue() == 1)
                                    hssfrow.createCell((short)2).setCellValue(dataBuf.toString());
                                else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    if(byte0 == 2)
                                    {
                                        i = i - (new Integer(s7)).intValue() - 1;
                                        byte0 = 0;
                                    }
                                    hssfrow = sheet.createRow(i);
                                    hssfrow.createCell((short)2).setCellValue(dataBuf.toString());
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 == 3)
                                if((new Integer(s7)).intValue() == 1)
                                    hssfrow.createCell((short)3).setCellValue(dataBuf.toString());
                                else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    if(byte0 == 3)
                                    {
                                        i -= (new Integer(s7)).intValue();
                                        byte0 = 0;
                                    }
                                    hssfrow = sheet.createRow(i);
                                    hssfrow.createCell((short)3).setCellValue(dataBuf.toString());
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 == 4)
                                if((new Integer(s7)).intValue() == 1)
                                    hssfrow.createCell((short)4).setCellValue(dataBuf.toString());
                                else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    if(byte0 == 4)
                                    {
                                        i -= (new Integer(s7)).intValue();
                                        byte0 = 0;
                                    }
                                    hssfrow = sheet.createRow(i);
                                    hssfrow.createCell((short)4).setCellValue(dataBuf.toString());
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 == 5)
                                if((new Integer(s7)).intValue() == 1)
                                    hssfrow.createCell((short)5).setCellValue(dataBuf.toString());
                                else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    if(byte0 == 5)
                                    {
                                        i -= (new Integer(s7)).intValue();
                                        byte0 = 0;
                                    }
                                    hssfrow = sheet.createRow(i);
                                    hssfrow.createCell((short)5).setCellValue(dataBuf.toString());
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 == 6)
                                if((new Integer(s7)).intValue() == 1)
                                    hssfrow.createCell((short)6).setCellValue(dataBuf.toString());
                                else
                                if((new Integer(s7)).intValue() > 1)
                                {
                                    if(byte0 == 6)
                                    {
                                        i -= (new Integer(s7)).intValue();
                                        byte0 = 0;
                                    }
                                    hssfrow = sheet.createRow(i);
                                    hssfrow.createCell((short)6).setCellValue(dataBuf.toString());
                                    hssfrow = sheet.createRow(i);
                                    i++;
                                }
                            if(j1 != 7)
                                continue;
                            if((new Integer(s7)).intValue() == 1)
                            {
                                hssfrow.createCell((short)7).setCellValue(dataBuf.toString());
                                continue;
                            }
                            if((new Integer(s7)).intValue() <= 1)
                                continue;
                            if(byte0 == 7)
                            {
                                i -= (new Integer(s7)).intValue();
                                byte0 = 0;
                            }
                            hssfrow = sheet.createRow(i);
                            hssfrow.createCell((short)7).setCellValue(dataBuf.toString());
                            hssfrow = sheet.createRow(i);
                            i++;
                        }

                    }

                }

            }
            fos = new FileOutputStream(file);
            workBook.write(fos);
            fos.close();
            writeBook(workBook, fos);
        }
        catch(Exception exception)
        {
            System.out.println((new StringBuilder()).append("ehis:").append(exception).toString());
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

    private static final short ZERO = 0;
    private static final short ONE = 1;
    private static final short TWO = 2;
    private static final short THREE = 3;
    private static final short FOUR = 4;
    private static final short FIVE = 5;
    private static final short SIX = 6;
    private static final short SEVEN = 7;
    private HSSFWorkbook workBook;
    private HSSFSheet sheet;
    private HSSFCell cell;
    private File file;
    private StringBuffer dataBuf;
    private static final String summaryFile = MessageRetriever.getProperty("QI_SUMMARY_FILE");
    private FileOutputStream fos;

}
