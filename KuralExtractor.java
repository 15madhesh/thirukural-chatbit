package com.madhesh.kural;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KuralExtractor {

    public static List<Kural> extractKurals(String filePath) throws IOException {
        List<Kural> kurals = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        String line;
        int number;

        while ((line = reader.readLine()) != null) {
            line = line.trim();

            if (line.matches("^\\d+\\.$")) {
                number = Integer.parseInt(line.replace(".", "").trim());

                String tamil1 = reader.readLine();
                String tamil2 = reader.readLine();
                String tamilMeaningLine = reader.readLine();
                String englishLine = reader.readLine();

                if (tamil1 != null && tamil2 != null && tamilMeaningLine != null && englishLine != null) {
                    String fullTamil = tamil1.trim() + "\n" + tamil2.trim();
                    String tamilMeaning = tamilMeaningLine.replace("பொருள் (Tamil):", "").trim();
                    String englishMeaning = englishLine.replace("Meaning (English):", "").trim();

                    kurals.add(new Kural(number, fullTamil, tamilMeaning, englishMeaning));
                }
            }
        }

        reader.close();
        return kurals;
    }
}
