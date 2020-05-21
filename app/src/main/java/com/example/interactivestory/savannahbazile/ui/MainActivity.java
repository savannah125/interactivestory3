package com.example.interactivestory.savannahbazile.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.interactivestory.savannahbazile.R;

public class MainActivity extends AppCompatActivity {
    EditText userInput;
    Button startButton;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = findViewById(R.id.userInput);
        startButton = findViewById(R.id.startButton);
        //1.Store our user's name inside of a variable
        //2.Navigate to a new screen.
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = userInput.getText().toString();
                startStory(name);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        userInput.setText("");
    }

    private void startStory(String name) {

        Resources resources = getResources();
        String key = resources.getString(R.string.user_name);

        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        intent.putExtra(key, name);
        startActivity(intent);
    }
}



