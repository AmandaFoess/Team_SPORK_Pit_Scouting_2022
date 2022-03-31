package com.example.teamsporkpitscouting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class opening_page extends AppCompatActivity implements View.OnClickListener {

    Button pit_scouting, strategy, pit_issue_log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_page);

        pit_scouting = findViewById(R.id.pit_scouting_btn);
        strategy = findViewById(R.id.strategy_btn);
        pit_issue_log = findViewById(R.id.pit_issue_log_btn);

        pit_scouting.setOnClickListener(this);
        strategy.setOnClickListener(this);
        pit_issue_log.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v==pit_scouting) {
            Intent intent = new Intent (getApplicationContext(), pit_scouting.class);
            startActivity(intent);
        } else if (v==strategy) {
            Intent intent = new Intent (getApplicationContext(), strategy.class);
            startActivity(intent);
        } else if (v==pit_issue_log) {
            Intent intent = new Intent (getApplicationContext(), pit_issue_log.class);
            startActivity(intent);
        }
    }
}