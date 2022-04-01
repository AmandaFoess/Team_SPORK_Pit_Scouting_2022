package com.example.teamsporkpitscouting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pit_issue_log extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;
    EditText issues_found_editText, issues_resolved_editText, unresolved_issues_editText, pit_comments_editText;
    Switch blank;
    String csv, sensor_index_value;
    Spinner sensor_index_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_issue_log);

        //Initialize button
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/TeamSPORK_Strategy.csv");

        List<String> pit_issue_log_list = new ArrayList<>();
        pit_issue_log_list.add("Good");
        pit_issue_log_list.add("Worn/Adjustment needed");
        pit_issue_log_list.add("Urgent/Needs Replacement");

        ArrayAdapter<String> array_adapter_pit_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, pit_issue_log_list);
        array_adapter_pit_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        sensor_index_spinner = findViewById(R.id.sensor_index_dropdown);
        sensor_index_spinner.setOnItemSelectedListener(this);
        sensor_index_spinner.setAdapter(array_adapter_pit_spinner);

    }

    @Override
    public void onClick(View v) {
        add_pit_issue_log_data();
    }

    public void add_pit_issue_log_data() {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (parent == sensor_index_spinner) {
            sensor_index_value = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}