// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package gov.nih.nci.qi.util;


public class Label
{

    public Label(String s, Long long1)
    {
        labelName = null;
        id = null;
        relatedId = null;
        labelName = s;
        id = long1;
    }

    public Label(String s, Long long1, Long long2)
    {
        labelName = null;
        id = null;
        relatedId = null;
        labelName = s;
        id = long1;
        relatedId = long2;
    }

    public String getLabelName()
    {
        return labelName;
    }

    public Long getId()
    {
        return id;
    }

    public Long getRelatedId()
    {
        return relatedId;
    }

    public String toString()
    {
        String s = (new StringBuilder()).append("LabelName: ").append(labelName).append(" Id: ").append(id).append(" SubId: ").append(relatedId).toString();
        return s;
    }

    private String labelName;
    private Long id;
    private Long relatedId;
}
