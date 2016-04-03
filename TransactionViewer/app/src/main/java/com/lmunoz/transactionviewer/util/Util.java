package com.lmunoz.transactionviewer.util;

import android.content.Context;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * Created by Luis on 03/04/2016.
 */
public class Util {

    public static Float toTwoDecimalPlaces(Float quantity){
        BigDecimal bd = new BigDecimal(Float.toString(quantity));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return bd.floatValue();
    }

    public static String loadJSONFromAsset(Context context, String filename) {
        String json = null;
        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
