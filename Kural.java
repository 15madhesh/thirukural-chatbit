package com.madhesh.kural;

public class Kural {
    private int number;
    private String tamil;
    private String tamilMeaning;
    private String englishMeaning;

    public Kural(int number, String tamil, String tamilMeaning, String englishMeaning) {
        this.number = number;
        this.tamil = tamil;
        this.tamilMeaning = tamilMeaning;
        this.englishMeaning = englishMeaning;
    }

    public int getNumber() {
        return number;
    }

    public String getTamil() {
        return tamil;
    }

    public String getTamilMeaning() {
        return tamilMeaning;
    }

    public String getEnglishMeaning() {
        return englishMeaning;
    }

    @Override
    public String toString() {
        return "Kural " + number + ":\n" +
               tamil + "\n" +
               "📜 பொருள் (Tamil): " + tamilMeaning + "\n" +
               "💬 Meaning (English): " + englishMeaning;
    }
}
