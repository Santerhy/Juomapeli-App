package com.example.juomapeli;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class SetupActivity extends AppCompatActivity {

    public static ArrayList<String> playerNames = new ArrayList<String>();
    EditText inputfield;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        inputfield  = (EditText) findViewById(R.id.editTextTextPersonName);
        inputfield.setOnKeyListener(new View.OnKeyListener() {
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                // If the event is a key-down event on the "enter" button
                if ((event.getAction() == KeyEvent.ACTION_DOWN) &&
                        (keyCode == KeyEvent.KEYCODE_ENTER)) {
                    // Perform action on key press
                    
                    return true;
                }
                return false;
            }
        });
    }
}