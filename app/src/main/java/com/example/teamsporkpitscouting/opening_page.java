package com.example.teamsporkpitscouting;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class opening_page extends AppCompatActivity implements View.OnClickListener {

    Button pit_scouting, strategy, pit_issue_log, match_schedule, member_rotation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_page);

        pit_scouting = findViewById(R.id.pit_scouting_btn);
        strategy = findViewById(R.id.strategy_btn);
        pit_issue_log = findViewById(R.id.pit_issue_log_btn);
        match_schedule = findViewById(R.id.match_schedule_btn);
        member_rotation = findViewById(R.id.member_rotation_btn);

        pit_scouting.setOnClickListener(this);
        strategy.setOnClickListener(this);
        pit_issue_log.setOnClickListener(this);
        match_schedule.setOnClickListener(this);
        member_rotation.setOnClickListener(this);
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
        } else if (v==match_schedule) {
            Intent intent = new Intent (getApplicationContext(), match_schedule.class);
            startActivity(intent);
        } else if (v==member_rotation) {
            Intent intent = new Intent (getApplicationContext(), member_rotation.class);
            startActivity(intent);
        }
    }
}