// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import com.oroinc.net.ftp.FTPClient;
import com.oroinc.net.ftp.FTPReply;
import java.io.*;
import java.util.Hashtable;

public class FTPUtil extends FTPClient
    implements Runnable
{

    public static synchronized FTPUtil getInstance()
    {
        if(onlyInstance == null)
            onlyInstance = new FTPUtil();
        return onlyInstance;
    }

    public FTPUtil()
    {
    }

    public static void transfer(File afile[], File afile1[], Long long1, String s, String s1, String s2, String s3)
    {
        serverURL_ = s;
        serverUsername_ = s2;
        serverPassword_ = s3;
        fileArray_ = afile;
        fileArray2_ = afile1;
        path_ = s1;
        imageUID_ = long1;
        transfer_ = new Thread(new FTPUtil());
        transfer_.run();
    }

    public void run()
    {
        Object obj = null;
        try
        {
            connect(serverURL_);
            login(serverUsername_, serverPassword_);
            changeWorkingDirectory(path_);
            int i = getReplyCode();
            if(!FTPReply.isPositiveCompletion(i))
            {
                disconnect();
                System.err.println("FTP server refused connection.");
            }
            Hashtable hashtable = getFilename(fileArray_);
            Hashtable hashtable1 = getFilename(fileArray2_);
            if(hashtable != null && hashtable.size() > 0)
            {
                for(int j = 0; j < hashtable.size(); j++)
                {
                    String s = (String)hashtable.get((new StringBuilder()).append("\"").append(j).append("\"").toString());
                    System.out.println((new StringBuilder()).append("111111str1:").append(s).toString());
                    System.out.println((new StringBuilder()).append("files2.size():").append(hashtable1.size()).toString());
                    if(hashtable1 != null && hashtable1.size() == 0)
                    {
                        setFileType(2);
                        System.out.println((new StringBuilder()).append("str1 is 999999:").append(s).append("fileArray_[i]:").append(fileArray_[j]).toString());
                        boolean flag = storeFile(s, new FileInputStream(fileArray_[j]));
                        if(flag)
                            System.out.println((new StringBuilder()).append("\n").append(s).append(" successfully transferred...").toString());
                        else
                            System.out.println((new StringBuilder()).append("Transfer failed for file...").append(s).toString());
                    }
                    if(hashtable1 == null || hashtable1.size() <= 0)
                        continue;
                    for(int l = 0; l < hashtable1.size(); l++)
                    {
                        String s2 = (String)hashtable1.get((new StringBuilder()).append("\"").append(l).append("\"").toString());
                        boolean flag1 = deleteFile(s2);
                        setFileType(2);
                        System.out.println((new StringBuilder()).append("str1 is 999999:").append(s).append("fileArray_[i]:").append(fileArray_[j]).toString());
                        flag1 = storeFile(s, new FileInputStream(fileArray_[j]));
                        if(flag1)
                            System.out.println((new StringBuilder()).append("\n").append(s2).append(" successfully  deleted.. and ").append(s).append("sucessfully transferred").toString());
                        else
                            System.out.println("Transfer failed for file...");
                    }

                }

            }
            if(hashtable.size() == 0 && hashtable1 != null && hashtable1.size() > 0)
            {
                for(int k = 0; k < hashtable1.size(); k++)
                {
                    String s1 = (String)hashtable1.get((new StringBuilder()).append("\"").append(k).append("\"").toString());
                    System.out.println((new StringBuilder()).append("files2.size():").append(hashtable1.size()).append("str2:").append(s1).toString());
                    if(hashtable != null && hashtable.size() == 0)
                    {
                        System.out.println("---I am here---");
                        boolean flag2 = deleteFile(s1);
                        if(flag2)
                            System.out.println((new StringBuilder()).append("\n").append(s1).append(" successfully deleted...").toString());
                        else
                            System.out.println((new StringBuilder()).append("Delete failed for file...").append(s1).toString());
                    }
                }

            }
        }
        catch(IOException ioexception)
        {
            if(isConnected())
            {
                try
                {
                    disconnect();
                    System.out.println("disconnect ftp");
                }
                catch(IOException ioexception2) { }
                System.err.println("Could not connect to server.");
                ioexception.printStackTrace();
            }
        }
        try
        {
            disconnect();
        }
        catch(IOException ioexception1) { }
    }

    private Hashtable getFilename(File afile[])
    {
        Hashtable hashtable = new Hashtable();
        if(afile != null)
        {
            for(int i = 0; i < afile.length; i++)
            {
                File file = afile[i];
                String s = file.getName().trim();
                System.out.println((new StringBuilder()).append("file2:").append(s).toString());
                System.out.println((new StringBuilder()).append("imageUID_:").append(imageUID_).toString());
                if(imageUID_ != null)
                {
                    String s1 = (new StringBuilder()).append(imageUID_).append(".").append(s.substring(s.lastIndexOf(".") + 1)).toString();
                    System.out.println((new StringBuilder()).append("file3:").append(s1).toString());
                    hashtable.put((new StringBuilder()).append("\"").append(i).append("\"").toString(), s1);
                } else
                {
                    System.out.println("this is for deleting");
                    hashtable.put((new StringBuilder()).append("\"").append(i).append("\"").toString(), s);
                }
            }

        }
        return hashtable;
    }

    public void deleteAllFiles(File afile[], File afile1[], String s, String s1, String s2, String s3, String s4)
    {
        serverURL_ = s;
        serverUsername_ = s3;
        serverPassword_ = s4;
        fileArray_ = afile;
        fileArray2_ = afile1;
        path_ = s1;
        path2_ = s2;
        imageUID_ = null;
        delete();
    }

    private void delete()
    {
        Object obj = null;
        boolean flag = false;
        boolean flag1 = false;
        try
        {
            connect(serverURL_);
            System.out.println(serverURL_);
            System.out.print(getReplyString());
            login(serverUsername_, serverPassword_);
            if(fileArray_ != null && fileArray_.length > 0 && !flag1)
            {
                System.out.println("this is for modelImg");
                flag = changeWorkingDirectory(path_);
            }
            if(flag && !flag1)
            {
                Hashtable hashtable = getFilename(fileArray_);
                for(int j = 0; j < hashtable.size(); j++)
                {
                    String s = (String)hashtable.get((new StringBuilder()).append("\"").append(j).append("\"").toString());
                    System.out.println((new StringBuilder()).append("files.size():").append(hashtable.size()).toString());
                    boolean flag3 = deleteFile(s);
                    if(flag3)
                        System.out.println((new StringBuilder()).append("\n").append(s).append(" successfully deleted...").toString());
                    else
                        System.out.println((new StringBuilder()).append("Delete failed for file...").append(s).toString());
                }

                flag = false;
            }
            if(fileArray2_ != null && fileArray2_.length > 0 && !flag)
            {
                System.out.println((new StringBuilder()).append("this is for constructsImg").append(printWorkingDirectory()).toString());
                flag1 = changeWorkingDirectory(path2_);
                System.out.println((new StringBuilder()).append("this is for constructsImg after ").append(printWorkingDirectory()).toString());
            }
            if(flag1 && !flag)
            {
                System.out.println("Test Msg...");
                Hashtable hashtable1 = getFilename(fileArray2_);
                for(int k = 0; k < hashtable1.size(); k++)
                {
                    String s1 = (String)hashtable1.get((new StringBuilder()).append("\"").append(k).append("\"").toString());
                    System.out.println((new StringBuilder()).append("files2.size():").append(hashtable1.size()).toString());
                    boolean flag4 = deleteFile(s1);
                    if(flag4)
                        System.out.println((new StringBuilder()).append("\n").append(s1).append(" successfully deleted...").toString());
                    else
                        System.out.println((new StringBuilder()).append("Delete failed for file...").append(s1).toString());
                }

                boolean flag2 = false;
            }
            int i = getReplyCode();
            System.out.println((new StringBuilder()).append("reply:").append(i).toString());
            if(!FTPReply.isPositiveCompletion(i))
            {
                disconnect();
                System.err.println("FTP server refused connection.");
            }
        }
        catch(IOException ioexception)
        {
            if(isConnected())
            {
                try
                {
                    disconnect();
                    System.out.println("disconnect ftp");
                }
                catch(IOException ioexception2) { }
                System.err.println("Could not connect to server.");
                ioexception.printStackTrace();
            }
        }
        try
        {
            disconnect();
        }
        catch(IOException ioexception1) { }
    }

    private static File fileArray_[];
    private static File fileArray2_[];
    private static String serverURL_ = null;
    private static String serverPassword_ = null;
    private static String serverUsername_ = null;
    private static Thread transfer_ = null;
    private static Thread delete_ = null;
    private static String path_ = null;
    private static String path2_ = null;
    private static Long imageUID_ = null;
    private static FTPUtil onlyInstance = null;

}
