package com.madhesh.kural;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    public static Kural searchByNumber(List<Kural> kurals, int number) {
        return kurals.stream().filter(k -> k.getNumber() == number).findFirst().orElse(null);
    }

    public static List<Kural> searchByKeyword(List<Kural> kurals, String keyword) {
        List<Kural> result = new ArrayList<>();
        for (Kural k : kurals) {
            if (k.getTamil().contains(keyword) || k.getEnglishMeaning().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(k);
            }
        }
        return result;
    }
}