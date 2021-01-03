package com.example.tudien;

public class DictEE {
    int id;
    String word;
    String definition;
    String example;
    String synonyms;
    String antonyms;

    public DictEE(int id, String word, String definition, String example, String synonyms, String antonyms) {
        this.id = id;
        this.word = word;
        this.definition = definition;
        this.example = example;
        this.synonyms = synonyms;
        this.antonyms = antonyms;
    }

    public int getId() { return id; }
    public String getWord() {
        return word;
    }

    public String getDefinition() {
        return definition;
    }

    public String getExample() {
        return example;
    }

    public String getAntonyms() { return antonyms; }

    public String getSynonyms() { return synonyms; }


    @Override
    public String toString() {
        return "{" +
                ", word ='" + getWord() + '\'' +
                ", definition ='" + getDefinition() + '\'' +
                ", example ='" + getExample() + '\'' +
                ", synonyms ='" + getSynonyms() + '\'' +
                ", antonyms ='" + getAntonyms() + '\'' +
                '}';
    }
}
