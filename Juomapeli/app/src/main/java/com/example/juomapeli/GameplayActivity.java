package com.example.juomapeli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class GameplayActivity extends AppCompatActivity {

    TextView roundText, playerText, questionText, questionTitle;
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
        questionTitle = (TextView) findViewById(R.id.textViewTitle);
        nextCard = (Button) findViewById(R.id.buttonNext);

        playerData = com.example.juomapeli.PlayerData.getInstance();
        Log.d("loggaus", "koko " + playerData.getQuestionsSize());
        maxRounds = playerData.getMaxRound();
        playerText.setText(playerData.getPlayerWithIndex(0));
        questionText.setText(playerData.getQuestion(currentRound).getDescription());
        Log.d("loggaus", "kyssäri " + playerData.getQuestion(currentRound).getTitle());
        questionTitle.setText(playerData.getQuestion(currentRound).getTitle());
        roundText.setText(currentRound + 1 + "/" + maxRounds);
    }

    public void updateRound() {

    }
}