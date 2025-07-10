package com.example.project;

public class Books {
    private static int id;
    private static String title;
    private static String author;
    private static boolean availability;

    public Books(int id, String title, String author, boolean availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availability = availability;
    }

    public int get_id() {
        return id;
    }
    public String get_title() {
        return title;
    }
    public String get_author() {
        return author;
    }
    public boolean get_availability() {
        return availability;
    }
    public void set_id(int id) {
        this.id = id;
    }
    public void set_title(String title) {
        this.title = title;
    }
    public void set_author(String author) {
        this.author = author;
    }
    public String toString() {
        return id + " " + title + " " + author + " " + availability;
    }
}
