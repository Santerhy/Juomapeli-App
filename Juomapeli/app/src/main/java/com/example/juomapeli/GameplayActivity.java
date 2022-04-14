package com.example.juomapeli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class GameplayActivity extends AppCompatActivity {

    TextView roundText, playerText, questionText;
    Button nextCard;
    int currentRound = 0;
    int maxRounds;
    PlayerData playerData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        roundText = (TextView) findViewById(R.id.textViewRound);
        playerText = (TextView) findViewById(R.id.textViewCurrentPlayer);
        questionText = (TextView) findViewById(R.id.textViewQuestion);
        nextCard = (Button) findViewById(R.id.buttonNext);

        playerData = com.example.juomapeli.PlayerData.getInstance();
        maxRounds = playerData.getMaxRound();
        playerText.setText(playerData.getPlayerWithIndex(1));
        questionText.setText(playerData.getQuestion(currentRound));
        roundText.setText(currentRound + 1 + "/" + maxRounds);
    }

    public void updateRound() {

    }
}