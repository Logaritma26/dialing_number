package com.log;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.IntStream;

public class NumberParser {

    private Map<String, Integer> countryCodes = new HashMap<>();
    private Map<String, String> prefixes = new HashMap<>();

    public NumberParser(Map<String, Integer> countryCodes, Map<String, String> prefixes) {
        this.countryCodes = countryCodes;
        this.prefixes = prefixes;
    }


    public String parse(String dialedNumber, String userNumber) {

        if (!dialedNumber.contains("+")) {
            final StringBuilder intNatDNumberBuilder = new StringBuilder();
            countryCodes.forEach((key, value) -> {
                IntStream stream = userNumber.substring(1,5).chars();
                StringBuilder builder = new StringBuilder();
                stream.forEach(i -> {
                    if(builder.append(Character.toChars(i)).toString().equals(value.toString())) {
                        Optional<String> prefixValue = prefixes.entrySet().stream()
                                .filter(entry -> Objects.equals(entry.getKey(), key))
                                .map(Map.Entry::getValue).findFirst();

                        if (prefixValue.isPresent()) {
                            String intNatDialledNumber=dialedNumber.replaceFirst(prefixValue.get(), "");
                            intNatDNumberBuilder.append("+"+value.toString()+intNatDialledNumber);
                        }
                    }
                });
            });
            return intNatDNumberBuilder.toString();
        }
        else
            return dialedNumber;
    }

    public String parsed(String dialedNumber, String userNumber) {

        if (!dialedNumber.contains("+")) {
            final StringBuilder number = new StringBuilder();
            for(Map.Entry<String,String> entry : prefixes.entrySet()) {
                final String prefix = entry.getValue();
                final int size = prefix.length();
                if (prefix.equals(dialedNumber.substring(0,size))){
                    final String key = entry.getKey();
                    final String countryCode = countryCodes.get(key).toString();
                    number.append("+").append(countryCode).append(dialedNumber.substring(size));
                }
            }
            return number.toString();
        }
        else
            return dialedNumber;
    }

}
