package com.example.teamsporkpitscouting;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    Button submit_button;
    EditText student_name, team_number, pit_number, robot_height, robot_weight, autonomous_strategy_comment, endgame_strategy_comment, drivetrain_type, order_of_wheels, number_of_wheels, additional_comments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit_button = (Button)findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        student_name = (EditText) findViewById(R.id.name_editText);
        team_number = (EditText) findViewById(R.id.team_number_editText);
        pit_number = (EditText) findViewById(R.id.pit_number_editText);
        robot_height = (EditText) findViewById(R.id.height_editText);
        robot_weight = (EditText) findViewById(R.id.weight_editText);
        autonomous_strategy_comment = (EditText) findViewById(R.id.autonomous_comment);
        endgame_strategy_comment = (EditText) findViewById(R.id.endgame_comment);
        drivetrain_type = (EditText) findViewById(R.id.drivetrain_editText);
        order_of_wheels = (EditText) findViewById(R.id.editTextOrderOfWheels);
        number_of_wheels = (EditText) findViewById(R.id.wheelNumber_editText);
        additional_comments = (EditText) findViewById(R.id.additional_comment);
    }

    @Override
    public void onClick(View v) {
        add_data_to_sheet();
    }

    public void add_data_to_sheet() {

        final ProgressDialog loading = ProgressDialog.show(this,"Adding Item","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbx0N9HhNYpj0oNBikKxrx2Z-EDZrKUfOeiG0_-Btu1cTyld5eEVjiGsjMefzhByLke4lw/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(MainActivity.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
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

                //here we pass params
                params.put("action","add_raw_pit_data");
                params.put("student_name",student_name.getText().toString().trim());
                params.put("team_number",team_number.getText().toString().trim());
                params.put("pit_number",pit_number.getText().toString().trim());
                params.put("robot_height",robot_height.getText().toString().trim());
                params.put("robot_weight",robot_weight.getText().toString().trim());
                params.put("autonomous_strategy_comment",autonomous_strategy_comment.getText().toString().trim());
                params.put("endgame_strategy_comment",endgame_strategy_comment.getText().toString().trim());
                params.put("drivetrain_type",drivetrain_type.getText().toString().trim());
                params.put("order_of_wheels",order_of_wheels.getText().toString().trim());
                params.put("number_of_wheels",number_of_wheels.getText().toString().trim());
                params.put("additional_comment",additional_comments.getText().toString().trim());

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