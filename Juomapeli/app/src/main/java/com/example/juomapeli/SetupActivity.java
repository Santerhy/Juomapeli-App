package com.example.juomapeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

public class SetupActivity extends AppCompatActivity {

    public static ArrayList<String> playerNames = new ArrayList<String>();
    public ArrayList<TextView> textViews = new ArrayList<TextView>();
    EditText inputfield;
    Button deleteButton, buttonStart;
    PlayerData playerData;
    int maxRounds = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        textViews.add((TextView) findViewById(R.id.nameText0));
        textViews.add((TextView) findViewById(R.id.nameText1));
        textViews.add((TextView) findViewById(R.id.nameText2));
        textViews.add((TextView) findViewById(R.id.nameText3));
        textViews.add((TextView) findViewById(R.id.nameText4));
        textViews.add((TextView) findViewById(R.id.nameText5));
        textViews.add((TextView) findViewById(R.id.nameText6));
        textViews.add((TextView) findViewById(R.id.nameText7));
        textViews.add((TextView) findViewById(R.id.nameText8));
        textViews.add((TextView) findViewById(R.id.nameText9));


        inputfield = (EditText) findViewById(R.id.editTextTextPersonName);
        deleteButton = (Button) findViewById(R.id.buttonDeleteLatest);
        buttonStart = (Button) findViewById(R.id.buttonStart);

        inputfield.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    getName(inputfield.getText().toString());
                    return true;
                }
                return false;
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerData.setMaxRound(maxRounds);
                playerData.setQuestions(Arrays.asList(getResources().getStringArray(R.array.player_questions)));
                playerData.setPlayers(playerNames);
                startActivity(new Intent(SetupActivity.this, GameplayActivity.class));
                finish();
            }
        });

        playerData = com.example.juomapeli.PlayerData.getInstance();


    }
        public void getName(String name) {
            if (playerNames.size() < 10) {
                playerNames.add(name);
                updateNameviews();
            }
        }

        public void updateNameviews() {
        for (int i = 0; i < textViews.size(); i++) {
            if (i >= playerNames.size())
                textViews.get(i).setText("");
            else
                textViews.get(i).setText(playerNames.get(i).toString());
        }
        }

        public void deleteLatest(View view) {
        if (playerNames.size() > 0) {
            playerNames.remove(playerNames.size() - 1);
            updateNameviews();
        }
        }


}