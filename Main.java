package com.madhesh.kural;

import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        List<Kural> kurals = KuralExtractor.extractKurals("src/main/resources/thirukkural_full_clean.txt");
        Scanner scanner = new Scanner(System.in);

        System.out.println("🧠 Thirukkural Chatbot is ready. Ask in Tamil or English:");

        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine();

            if (input.matches("\\d+")) {
                int number = Integer.parseInt(input);
                Kural k = SearchEngine.searchByNumber(kurals, number);
                if (k != null) System.out.println(k);
                else System.out.println("❌ Kural not found.");
            } else if (input.equalsIgnoreCase("exit")) {
                break;
            } else {
                List<Kural> results = SearchEngine.searchByKeyword(kurals, input);
                if (results.isEmpty()) {
                    System.out.println("❌ No relevant Kurals found.");
                } else {
                    StringBuilder context = new StringBuilder();
                    for (Kural k : results) {
                        context.append("Kural ").append(k.getNumber()).append(":\n").append(k.getTamil()).append("\n").append(k.getEnglishMeaning()).append("\n\n");
                    }

                    String response = LLMService.askLLM(context.toString(), input);
                    System.out.println("💬 AI says:\n" + response);
                }
            }
        }
    }
}