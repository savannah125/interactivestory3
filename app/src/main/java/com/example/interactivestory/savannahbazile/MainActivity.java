package com.example.interactivestory.savannahbazile;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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
                Toast.makeText(MainActivity.this, name, Toast.LENGTH_SHORT).show();
                startStory();
            }
        });
    }

    public void startStory() {

        Intent intent = new Intent(MainActivity.this, StoryActivity.class);
        startActivity(intent);
    }
}



