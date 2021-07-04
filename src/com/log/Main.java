package com.log;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        // write your code here


        Map<String, Integer> countryCodes = new HashMap<>();
        Map<String, String> prefixes = new HashMap<>();

        countryCodes.put("GB", 44);
        prefixes.put("GB", "0");
        countryCodes.put("US", 1);
        prefixes.put("US", "1");
        countryCodes.put("DEN", 59);
        prefixes.put("DEN", "45678");

        NumberParser parser = new NumberParser(countryCodes, prefixes);

        System.out.println(assertEquals("+442079460056", parser.parsed("02079460056", "+441614960148")));
        System.out.println(assertEquals("+442079460056", parser.parsed("+442079460056", "+441614960148")));
        System.out.println(assertEquals("+592079460056", parser.parsed("456782079460056", "+441614960148")));

    }


    public static boolean assertEquals(String val1, String val2){
        return val1.equals(val2);
    }

}
