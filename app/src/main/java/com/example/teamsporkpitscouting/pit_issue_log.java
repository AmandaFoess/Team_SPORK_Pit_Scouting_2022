package com.example.teamsporkpitscouting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class pit_issue_log extends AppCompatActivity implements View.OnClickListener {

    Button submit_button;
    EditText issues_found_editText, issues_resolved_editText, unresolved_issues_editText, pit_comments_editText;
    Switch blank;
    String csv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_issue_log);

        //Initialize button
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/TeamSPORK_Strategy.csv");

    }

    @Override
    public void onClick(View v) {
        add_pit_issue_log_data();
    }

    public void add_pit_issue_log_data() {

    }

}