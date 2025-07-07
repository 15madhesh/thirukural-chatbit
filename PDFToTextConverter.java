package com.madhesh.kural;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.*;

public class PDFToTextConverter {
    public static void main(String[] args) {
        String inputPdf = "src/main/resources/Thirukkural-with-meaning.pdf";
        String outputTxt = "src/main/resources/thirukkural_full_clean.txt";

        try (PDDocument document = PDDocument.load(new File(inputPdf))) {
            PDFTextStripper stripper = new PDFTextStripper();
            String text = stripper.getText(document);
            String[] lines = text.split("\\r?\\n");

            try (PrintWriter writer = new PrintWriter(new FileWriter(outputTxt, false))) {
                int kuralNumber = 1;

                for (int i = 0; i < lines.length - 2; i++) {
                    if (lines[i].trim().equals(String.valueOf(kuralNumber))) {
                        String tamil = lines[i + 1].trim();
                        String english = lines[i + 2].trim();

                        writer.println(kuralNumber);
                        writer.println(tamil);
                        writer.println(english);
                        writer.println();

                        kuralNumber++;
                        i += 2;
                    }
                }

                System.out.println("✅ Extracted " + (kuralNumber - 1) + " Kurals to: " + outputTxt);
            }

        } catch (IOException e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}
