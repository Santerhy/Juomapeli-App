package com.example.juomapeli;

public class FullRoundQuestion {
    String playerName, questionTitle;
    int startingIndex;
    public FullRoundQuestion(int index, String _playerName, String _questionTitle) {
        this.startingIndex = index;
        this.playerName = _playerName;
        this.questionTitle = _questionTitle;
    }

    public int getStartingIndex() { return startingIndex; }
    public  String getPlayerName() { return playerName; }
    public String getQuestionTitle() { return questionTitle; }
}
