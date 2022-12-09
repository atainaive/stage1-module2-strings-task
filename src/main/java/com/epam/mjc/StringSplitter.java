package com.epam.mjc;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringSplitter {

    /**
     * Splits given string applying all delimeters to it. Keeps order of result substrings as in source string.
     *
     * @param source source string
     * @param delimiters collection of delimiter strings
     * @return List of substrings
     */
    public List<String> splitByDelimiters(String source, Collection<String> delimiters) {
        List<String> list = new ArrayList<>();
        StringBuilder current = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            if (delimiters.contains(String.valueOf(source.charAt(i)))) {
                if (current.length() != 0) {
                    list.add(current.toString());
                    current.setLength(0);
                }
            } else {
                current.append(source.charAt(i));
            }
        }
        if (current.length() != 0) {
            list.add(current.toString());
        }
        return list;
    }
}
