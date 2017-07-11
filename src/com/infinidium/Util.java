package com.infinidium;

import java.util.Locale;

/**
 * Created by abdullaht on 11.07.2017.
 */
public class Util {
    public static String lowerAll(String str){
        String str1 =  str.replaceAll("İ","i")
                .replaceAll("Ü","ü")
                .replaceAll("Ö","ö")
                .replaceAll("Ğ","ğ")
                .replaceAll("I","ı")
                .replaceAll("Ç","ç")
                .replaceAll("Ş","ş");
        return str1.toLowerCase(Locale.ENGLISH);
    }
}
