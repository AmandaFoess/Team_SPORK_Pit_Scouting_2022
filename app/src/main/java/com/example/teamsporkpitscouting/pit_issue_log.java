package com.example.teamsporkpitscouting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_issue_log);

        //Initialize button
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        //initialize edit_texts
        issues_found_editText = findViewById(R.id.issues_found_editText);
        issues_resolved_editText = findViewById(R.id.issues_resolved_editText);
        unresolved_issues_editText = findViewById(R.id.unresolved_issue_editText);
        pit_comments_editText = findViewById(R.id.pit_comments_editText);
    }

    @Override
    public void onClick(View v) {
        add_pit_issue_log_data();
    }

    public void add_pit_issue_log_data() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Pit Issue Log Data","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbyKCPcP8o1aTa8pQaljOohijo__KHR8kpBBoMmYu6yXejFPpfs7uEQSMfZCstEj0NJAEw/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(pit_issue_log.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),pit_issue_log.class);
                        startActivity(intent);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();

                //Text
                params.put("action","add_raw_pit_issue_log_data");
                params.put("issues_found",issues_found_editText.getText().toString().trim());
                params.put("issues_resolved",issues_resolved_editText.getText().toString().trim());
                params.put("unresolved_issues",unresolved_issues_editText.getText().toString().trim());
                params.put("pit_comments",pit_comments_editText.getText().toString().trim());

                return params;
            }
        };

        int socketTimeOut = 5000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }
}