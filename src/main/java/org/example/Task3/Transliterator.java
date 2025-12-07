package org.example.Task3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Transliterator {
    private static final String[][] RULES = {
            {"а", "a"}, {"б", "b"}, {"в", "v"}, {"г", "g"}, {"д", "d"},
            {"е", "e"}, {"ё", "yo"}, {"ж", "zh"}, {"з", "z"}, {"и", "i"},
            {"й", "j"}, {"к", "k"}, {"л", "l"}, {"м", "m"}, {"н", "n"},
            {"о", "o"}, {"п", "p"}, {"р", "r"}, {"с", "s"}, {"т", "t"},
            {"у", "u"}, {"ф", "f"}, {"х", "x"}, {"ц", "cz"}, {"ч", "ch"},
            {"ш", "sh"}, {"щ", "shh"}, {"ъ", "``"}, {"ы", "y`"}, {"ь", "`"},
            {"э", "e`"}, {"ю", "yu"}, {"я", "ya"}
    };

    private static final HashMap<String, String> CYR_TO_LAT =
            new LinkedHashMap<>();
    private static final HashMap<String, String> LAT_TO_CYR =
            new LinkedHashMap<>();

    static {
        for (String [] rule : RULES) {
            CYR_TO_LAT.put(rule[0], rule[1]);
        }

        String[][] sortedRules = Arrays.copyOf(RULES, RULES.length);
        Arrays.sort(
                sortedRules,
                Comparator.comparingInt(a -> a[1].length())
        );
        for (String[] rule : sortedRules) {
            LAT_TO_CYR.put(rule[1], rule[0]);
        }
    }

    public static String toLatin(String text) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            char symbol = text.charAt(i);

            String lat = CYR_TO_LAT.get(
                    String.valueOf(Character.toLowerCase(symbol))
            );

            if (lat == null) { // Символ не указан в правилах
                sb.append(symbol);
            } else { // символ указан в правилах
                if (Character.isUpperCase(symbol)) { // символ в верхнем регистре
                    if (lat.length() > 1) // транслитерация длинне одного символа
                        sb.append(Character.toUpperCase(lat.charAt(0)))
                                .append(lat.substring(1));
                    else // транслитерация - 1 символ
                        sb.append(lat.toUpperCase());
                } else { // символ в нижнем регистре
                    sb.append(lat);
                }
            }
        }
        return sb.toString();
    }

    public static String toCyrillic(String text) {
        StringBuilder sb = new StringBuilder();
        int i = 0;

        while (i < text.length()) {
            boolean matched = false;

            for (String lat : LAT_TO_CYR.keySet()) {
                int length = lat.length();
                if ((length != 0) && (i + length <= text.length())) {
                    String substring = text.substring(i, i + length);

                    if (substring.equalsIgnoreCase(lat)) { // фрагмент встретился в правилах
                        String cyr = LAT_TO_CYR.get(lat); // соответствующая буква кириллицы
                        if (Character.isUpperCase(substring.charAt(0))) { // фрагмент с буквы в верхнем регистре
                            sb.append(cyr.toUpperCase());
                        } else { // фрагмент с буквы в нижнем регистре
                            sb.append(cyr);
                        }

                        i += length;
                        matched = true;
                        break;
                    }
                }
            }
            if (!matched) { // символ не указан в правилах
                sb.append(text.charAt(i));
                i++;
            }
        }
        return sb.toString();
    }
}
