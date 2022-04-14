package com.example.juomapeli;

import android.util.Log;

import java.security.Identity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PlayerData {
    private static PlayerData INSTANCE = null;
    private PlayerData(){}
    ArrayList<String> players, questions;
    ArrayList<Integer> usedIndexes = new ArrayList<Integer>();
    int maxRound;
    public static synchronized PlayerData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new PlayerData();
        }
        return (INSTANCE);
    }


    public void setQuestions(List<String> _questions) {
        Log.d("loggaus", "aloitettu");
        questions = new ArrayList<String>(_questions);
        Log.d("loggaus", "lista luotu");
        Collections.shuffle(questions); //(questions, new Random(players.size())
        Log.d("loggaus", "lista sekoitettu");
        while (maxRound < questions.size())
            questions.remove(questions.size() - 1);
        Log.d("loggaus", questions.get(0));
    }

    public String getQuestion(int index) {
        return questions.get(index);
    }

    public void setPlayers(ArrayList<String> _players) {
        players = new ArrayList<String>(_players);
    }

    public String getPlayerWithIndex(int index) {
        if (index > players.size())
            return "null";
        else
            return players.get(index);
    }

    public void setMaxRound(int rounds){this.maxRound = rounds;}
    public int getMaxRound() {return this.maxRound;}
}
