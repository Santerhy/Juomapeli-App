package com.example.juomapeli;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONStringer;
import org.json.JSONTokener;



public class SetupActivity extends AppCompatActivity {

    public static ArrayList<String> playerNames = new ArrayList<String>();
    public ArrayList<TextView> textViews = new ArrayList<TextView>();
    public ArrayList<Question> questionList = new ArrayList<Question>();
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
                    inputfield.getText().clear();
                    return true;
                }
                return false;
            }
        });
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playerData.setMaxRound(maxRounds);
                playerData.setQuestions(questionList);
                playerData.setPlayers(playerNames);
                startActivity(new Intent(SetupActivity.this, GameplayActivity.class));
                finish();
            }
        });

        playerData = com.example.juomapeli.PlayerData.getInstance();
        generateQuestions();

    }

    private void generateQuestions() {
        try {
            Log.d("loggaus", "aloitettu");
            JSONObject obj = new JSONObject(loadJSONFromAsset(getApplicationContext()));
            Log.d("loggaus", "objekti luotu");
            JSONArray jarr = obj.getJSONArray("questions");
            Log.d("loggaus", "json: " + obj);
            for (int i = 0; i < jarr.length(); i++) {
                JSONObject sq = jarr.getJSONObject(i);
                String title = sq.getString("title");
                String desc = sq.getString("description");
                boolean timedEvent = sq.getBoolean("timedEvent");
                boolean targetPlayer = sq.getBoolean("targetPlayer");
                questionList.add(new Question(title, desc, timedEvent, targetPlayer));
            }
        } catch (JSONException e){

        }
    }


    public String loadJSONFromAsset(Context context) {
        String json;
        try {
            InputStream is = context.getAssets().open("questions.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, StandardCharsets.UTF_8);
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
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