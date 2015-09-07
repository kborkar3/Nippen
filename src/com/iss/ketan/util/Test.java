package com.iss.ketan.util;
public class Test
{
    public static boolean[] generatePFArray(final int item[])
    {
     final int len=item.length;
     final boolean[] pfArr=new boolean[len];

     for (int i=0;i<len;i++)
     {
      /*pfArr[i]=!(KetanUtilities.search(Current.PF_INDEX,item[i])==-1);*/
     }
     return pfArr;
    }



    public static boolean[] generateEnergyArray(final int item[])
    {
     final int len=item.length;
     final boolean[] enArr=new boolean[len];

     for (int i=0;i<len;i++)
     {
     /* enArr[i]=!(KetanUtilities.search(Current.energy_parameters,item[i])==-1);*/
     }
     return enArr;
    }
}