package com.example.interactivestory.savannahbazile.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.interactivestory.savannahbazile.R;
import com.example.interactivestory.savannahbazile.model.Page;
import com.example.interactivestory.savannahbazile.model.Story;

import java.util.Stack;

public class StoryActivity extends AppCompatActivity {

    private TextView pageTextView;
    private ImageView pageImageView;
    private Button choiceButton1;
    private Button choiceButton2;
    private Stack<Integer>pageStack = new Stack<Integer>();


    String name;
    Story story;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_story);
        pageTextView = findViewById(R.id.pageTextView);
        pageImageView = findViewById(R.id.pageImageView);
        choiceButton1 = findViewById(R.id.choiceButton1);
        choiceButton2 = findViewById(R.id.choiceButton2);


        //This gets the intent from previous activity.
        Intent intent = getIntent();
        String name = intent.getStringExtra(getString(R.string.user_name));
        //Gives name a default value if user doesn't enter name.
        if (name == null || name.isEmpty()) ;
        name = "Friend";


        story = new Story();
        loadPage(0);
    }


    private void loadPage(int pageNumber) {

        pageStack.push(pageNumber);

        final Page page = story.getPage(pageNumber);

        Drawable image = ContextCompat.getDrawable(this, page.getImageId());
        pageImageView.setImageDrawable(image);

        String pageText = getString(page.getTextId());

        pageText = String.format(pageText, name);

        pageTextView.setText(pageText);

        if (page.isFinalPage()) {
            choiceButton1.setVisibility(View.INVISIBLE);
            choiceButton2.setText(R.string.play_again_button_text);
            choiceButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //go back to first page.
                    loadPage(0);

                }
            });


        } else {
            loadButtons(page);
        }
    }
        private void loadButtons ( final Page page){
        choiceButton1.setVisibility(View.VISIBLE);
        choiceButton1.setText(page.getChoice1().getTextId());
        choiceButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nextPage = page.getChoice1().getNextPage();
                loadPage(nextPage);

            }
        });
            choiceButton2.setVisibility(View.VISIBLE);
            choiceButton2.setText(page.getChoice2().getTextId());
            choiceButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int nextPage = page.getChoice2().getNextPage();
                    loadPage(nextPage);

                }
            });

        }



    @Override
    public void onBackPressed() {
        pageStack.pop();
        if(pageStack.isEmpty()){
            //takes us to the previous activity.
            super.onBackPressed();
        }

    else{
        loadPage(pageStack.pop());
    }

    }

}