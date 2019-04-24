package com.nex3z.tflitemnist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomePage extends AppCompatActivity {

    ImageView shekhaoButton,shikhoButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        shekhaoButton = findViewById(R.id.shekhaoButton);
        shikhoButton = findViewById(R.id.shikhoButton);

        shikhoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ShikhoActivity.class);
                startActivity(i);
            }
        });

        shekhaoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomePage.this, ShekhaoActivity.class);
                startActivity(i);
            }
        });
    }
}
