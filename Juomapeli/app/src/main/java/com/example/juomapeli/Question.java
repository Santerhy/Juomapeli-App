package com.example.juomapeli;

public class Question {
    String title;
    String description;
    boolean timedEvent;
    boolean targerPlayer;

    public Question(String _title, String _description, boolean _timedEvent, boolean _targerPlayer) {
        this.title = _title;
        this.description = _description;
        this.timedEvent = _timedEvent;
        this.targerPlayer = _targerPlayer;
    }
    public String getTitle() {return this.title;}
    public String getDescription() {return this.description;}
    public boolean getTimedEvent() {return this.timedEvent;}
}
