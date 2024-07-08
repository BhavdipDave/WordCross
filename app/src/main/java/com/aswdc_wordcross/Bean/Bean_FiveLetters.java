package com.aswdc_wordcross.Bean;

public class Bean_FiveLetters {

    public static final String TABLE_NAME = "WC_FiveLetter";
    public static final String COLUMN_ID = "ID";
    public static final String COLUMN_WORDS = "words";

    private int WordID;
    private String Words;

    public int getWordID() {

        return WordID;
    }

    public void setWordID(int wordID) {
        WordID = wordID;
    }

    public String getWords() {
        return Words;
    }

    public void setWords(String words) {
        Words = words;
    }
}
