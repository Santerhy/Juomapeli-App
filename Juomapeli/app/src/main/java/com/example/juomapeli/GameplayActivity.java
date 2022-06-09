package com.example.juomapeli;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class GameplayActivity extends AppCompatActivity {

    TextView roundText, playerText, questionText, questionTitle, fullRound;
    Button nextCard;
    int currentRound = 0;
    int maxRounds, playerIndex, playerSize;
    Random random;
    PlayerData playerData;
    Question currentQuestion;
    String currentQuestionDesc;
    Queue<FullRoundQuestion> fullRoundQuestions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gameplay);

        roundText = (TextView) findViewById(R.id.textViewRound);
        playerText = (TextView) findViewById(R.id.textViewCurrentPlayer);
        questionText = (TextView) findViewById(R.id.textViewQuestion);
        questionTitle = (TextView) findViewById(R.id.textViewTitle);
        fullRound = (TextView) findViewById(R.id.textViewFullRound);
        nextCard = (Button) findViewById(R.id.buttonNext);

        fullRoundQuestions = new LinkedList<>();

        playerData = com.example.juomapeli.PlayerData.getInstance();
        //Generate random int for starting player, get their name and set it to textView
        random = new Random();
        playerSize = playerData.getPlayerSize();
        playerIndex = random.nextInt(playerSize);
        playerText.setText(playerData.getPlayerWithIndex(playerIndex));

        //Get max rounds and set it
        maxRounds = playerData.getMaxRound();
        roundText.setText(currentRound + 1 + "/" + maxRounds);

        //Get first question and its description
        currentQuestion = playerData.getQuestion(currentRound);
        currentQuestionDesc = currentQuestion.getDescription();

        questionTitle.setText(currentQuestion.getTitle());

        //Check if targetPlayer is true
        if (currentQuestion.targerPlayer) {
            currentQuestionDesc += playerData.getPlayerWithIndex(getTargetPlayer());
        }
        questionText.setText(currentQuestionDesc);
        fullRound.setText("");
        checkFullRoundQuestions();
    }

    public void updateRound(View view) {
        if (currentRound + 1 == maxRounds) {
            endingText();
            currentRound++;
        } else if (currentRound + 1 > maxRounds)
            backToMenu();
        else {
            currentRound++;
            if (playerIndex == playerSize -1)
                playerIndex = 0;
            else
                playerIndex++;
            currentQuestion = playerData.getQuestion(currentRound);
            updateTextViews();
        }
    }

    private void endingText() {
        nextCard.setText(getString(R.string.ending_button));
        questionTitle.setText(getString(R.string.ending_text));
        playerText.setText("");
        questionText.setText("");
    }

    private void backToMenu() {
        startActivity(new Intent(GameplayActivity.this, MainActivity.class));
        finish();
    }

    private void updateTextViews() {
        currentQuestionDesc = currentQuestion.getDescription();
        playerText.setText(playerData.getPlayerWithIndex(playerIndex));
        if (currentQuestion.targerPlayer) {
            setSpanString(currentQuestionDesc, playerData.getPlayerWithIndex(getTargetPlayer()), questionText);
            //currentQuestionDesc += playerData.getPlayerWithIndex(getTargetPlayer());
        } else {
            questionText.setText(currentQuestionDesc);
        }
        questionTitle.setText(playerData.getQuestion(currentRound).getTitle());
        roundText.setText(currentRound + 1 + "/" + maxRounds);
        fullRound.setText("");
        checkFullRoundQuestions();
    }
    private void setSpanString(String desc, String name, TextView textView) {
        SpannableStringBuilder builder=new SpannableStringBuilder();
        SpannableString txtSpannable= new SpannableString(name);
        StyleSpan boldSpan = new StyleSpan(Typeface.BOLD);
        txtSpannable.setSpan(boldSpan, 0, name.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        builder.append(desc);
        builder.append(txtSpannable);
        //builder.append(name);
        textView.setText(builder, TextView.BufferType.SPANNABLE);
    }

    private int getTargetPlayer() {
        while (true) {
            int targetPlayerIndex = random.nextInt(playerSize);
            if (targetPlayerIndex != playerIndex)
                return targetPlayerIndex;
        }
    }

    private void checkFullRoundQuestions() {
        for (FullRoundQuestion frq : fullRoundQuestions) {
            if (frq.getStartingIndex() == playerIndex) {
                String fullRoundString = frq.getPlayerName() + ", voit lopettaa tehtävän: " + frq.getQuestionTitle();
                fullRound.setText(fullRoundString);
                fullRoundQuestions.poll();
                Log.d("loggaus", fullRoundString);
                break;
            } else
                fullRound.setText("");
        }
        if (currentQuestion.getTimedEvent()) {
            fullRoundQuestions.add(new FullRoundQuestion(playerIndex, playerData.getPlayerWithIndex(playerIndex), currentQuestion.getTitle()));
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle savedInstance) {
        super.onSaveInstanceState(savedInstance);
        savedInstance.putInt("currentRound", currentRound);
        savedInstance.putInt("playerIndex", playerIndex);

    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstance) {
        super.onRestoreInstanceState(savedInstance);
        currentRound = savedInstance.getInt("currentRound");
        playerIndex = savedInstance.getInt("playerIndex");
    }
}