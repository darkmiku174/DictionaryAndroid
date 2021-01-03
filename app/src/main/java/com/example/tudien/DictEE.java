package com.example.tudien;

public class DictEE {
    String word;
    String wordtype;
    String definition;

    public DictEE(String word, String wordtype, String definition) {
        this.word = word;
        this.wordtype = wordtype;
        this.definition = definition;
    }

    public String getWord() {
        return word;
    }
    public String getWordType() {
        return wordtype;
    }
    public String getDefinition() {
        return definition;
    }

    @Override
    public String toString() {
        return "{" +
                ", word ='" + getWord() + '\'' +
                ", type ='" + getWordType() + '\'' +
                ", definition ='" + getDefinition() + '\'' +
                '}';
    }
}
