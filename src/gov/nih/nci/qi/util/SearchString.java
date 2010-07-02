// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;

import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchString
{

    public SearchString(String s, Vector vector)
    {
        strVec = new Vector();
        INPUT = s;
        strVec = vector;
    }

    public boolean performSearch()
    {
        int i = 0;
        do
        {
            if(i >= strVec.size())
                break;
            String s = (String)strVec.elementAt(i);
            pattern = Pattern.compile(INPUT);
            matcher = pattern.matcher(s);
            found = matcher.matches();
            if(found)
                break;
            i++;
        } while(true);
        return found;
    }

    private String INPUT;
    private Vector strVec;
    private Pattern pattern;
    private Matcher matcher;
    private boolean found;
}
