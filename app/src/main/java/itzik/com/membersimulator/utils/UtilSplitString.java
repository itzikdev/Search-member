package itzik.com.membersimulator.utils;

import android.util.Log;

import java.util.Arrays;

public class UtilSplitString {

    public static final String TAG = UtilSplitString.class.getSimpleName();

    public static int  [] splitValue(String o,String regex){
        if(o!=null && !o.isEmpty() && o.contains(regex)){
            final int n = 0,m = 1;
           String [] temp  = o.replace("+\\s","").split(regex);
            Log.d(TAG, "splitValue: temp? "+ Arrays.deepToString(temp));
           if(temp!=null && temp.length == 2) {
               int [] result = new int [2];
               Log.d(TAG, "splitValue: temp[0]: " + temp[0]);
               Log.d(TAG, "splitValue: temp[1]: " + temp[1]);
               result[n] = Integer.parseInt(temp[0]);
               result[m] = Integer.parseInt(temp[1]);
               return result;
           }
        }

        return null;
    }
}
