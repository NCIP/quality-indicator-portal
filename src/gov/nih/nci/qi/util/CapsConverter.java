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


public class CapsConverter
{

    public CapsConverter()
    {
    }

    public static String convert(String s)
    {
        String s1 = "";
        if(s != null)
        {
            s = s.toLowerCase();
            char c = s.charAt(0);
            s1 = (new StringBuilder()).append(Character.toUpperCase(c)).append(s.substring(1)).toString();
        }
        return s1;
    }
}
