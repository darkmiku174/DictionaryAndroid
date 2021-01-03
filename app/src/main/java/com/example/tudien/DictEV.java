package com.example.tudien;

public class DictEV {
    int id;
    String word;
    String html;
    String description;
    String pronounce;

    public DictEV(String word, String html, String description, String pronounce) {
        this.word = word;
        this.html = html;
        this.description = description;
        this.pronounce = pronounce;
    }

    public DictEV(int id, String word, String html, String description, String pronounce) {
        this.id = id;
        this.word = word;
        this.html = html;
        this.description = description;
        this.pronounce = pronounce;
    }

    public int getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public String getHtml() {
        return html;
    }

    public String getDescription() {
        return description;
    }

    public String getPronounce() {
        return pronounce;
    }

    @Override
    public String toString() {
        return "{" +
                "id ='" + getId() + '\'' +
                ", word ='" + getWord() + '\'' +
                ", description ='" + getDescription() + '\'' +
                '}';
    }
}
