package com.example.teamsporkpitscouting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

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

public class strategy extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;
    EditText student_name_editText, team_number_editText, match_number_editText, red_total_points_editText, blue_total_points_editText, additional_comments_editText;
    TextView auto_cargo_lower_hub_textView, auto_cargo_upper_hub_textView, teleop_cargo_lower_hub_textView, teleop_cargo_upper_hub_textView, red_penalty_points_textView, blue_penalty_points_textView;
    Spinner driver_station_spinner, climber_spinner, penalty_card_spinner;
    Switch move_auto_switch, broken_switch, disabled_switch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.strategy);

        //initialize button
        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        //initialize edit texts
        student_name_editText = findViewById(R.id.student_name_editText);
        team_number_editText = findViewById(R.id.team_number_editText);
        match_number_editText = findViewById(R.id.match_number_editText);
        red_total_points_editText = findViewById(R.id.red_total_points_editText);
        blue_total_points_editText = findViewById(R.id.blue_total_points_editText);
        additional_comments_editText = findViewById(R.id.additional_comments);

        //initialize text view
        auto_cargo_lower_hub_textView = findViewById(R.id.auto_cargo_lower_hub);
        auto_cargo_upper_hub_textView = findViewById(R.id.auto_cargo_upper_hub);
        teleop_cargo_lower_hub_textView = findViewById(R.id.teleop_cargo_lower_hub);
        teleop_cargo_upper_hub_textView = findViewById(R.id.teleop_cargo_upper_hub);
        red_penalty_points_textView = findViewById(R.id.red_penalty_points);
        blue_penalty_points_textView = findViewById(R.id.blue_penalty_points);

        //initialize spinners

        //Driver station spinner
        driver_station_spinner = findViewById(R.id.driver_station_dropdown);
        driver_station_spinner.setOnItemSelectedListener(this);

        List<String> driver_station_spinner_list = new ArrayList<>();
        driver_station_spinner_list.add("Red 1");
        driver_station_spinner_list.add("Red 2");
        driver_station_spinner_list.add("Red 3");
        driver_station_spinner_list.add("Blue 1");
        driver_station_spinner_list.add("Blue 2");
        driver_station_spinner_list.add("Blue 3");

        ArrayAdapter<String> array_adapter_driver_station_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, driver_station_spinner_list);
        array_adapter_driver_station_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        driver_station_spinner.setAdapter(array_adapter_driver_station_spinner);

        //climber dropdown spinner
        climber_spinner = findViewById(R.id.climber_dropdown);
        climber_spinner.setOnItemSelectedListener(this);

        List<String> climber_spinner_list = new ArrayList<>();
        climber_spinner_list.add("Does Not Climb");
        climber_spinner_list.add("Low Rung");
        climber_spinner_list.add("Mid Rung");
        climber_spinner_list.add("High Run");
        climber_spinner_list.add("Traversal Rung");

        ArrayAdapter<String> array_adapter_climber_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, climber_spinner_list);
        array_adapter_climber_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        climber_spinner.setAdapter(array_adapter_climber_spinner);

        //Driver station spinner
        penalty_card_spinner = findViewById(R.id.penalty_card_dropdown);
        penalty_card_spinner.setOnItemSelectedListener(this);

        List<String> penalty_card_spinner_list = new ArrayList<>();
        penalty_card_spinner_list.add("No Card");
        penalty_card_spinner_list.add("Yellow Card");
        penalty_card_spinner_list.add("Red Card");

        ArrayAdapter<String> array_adapter_penalty_card_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, penalty_card_spinner_list);
        array_adapter_penalty_card_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        penalty_card_spinner.setAdapter(array_adapter_penalty_card_spinner);

        //initialize switch
        move_auto_switch = findViewById(R.id.move_auto_switch);
        broken_switch = findViewById(R.id.broken_switch);
        disabled_switch = findViewById(R.id.disable_switch);
    }

    @Override
    public void onClick(View v) {
        add_strategy_data_to_sheet();
    }

    public void add_strategy_data_to_sheet() {
        final ProgressDialog loading = ProgressDialog.show(this,"Adding Strategy Data","Please wait");

        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://script.google.com/macros/s/AKfycbx0N9HhNYpj0oNBikKxrx2Z-EDZrKUfOeiG0_-Btu1cTyld5eEVjiGsjMefzhByLke4lw/exec",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        Toast.makeText(strategy.this,response,Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(),strategy.class);
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
                params.put("action","add_raw_strategy_data");
                params.put("student_name",student_name_editText.getText().toString().trim());
                params.put("team_number",team_number_editText.getText().toString().trim());
                params.put("match_number",match_number_editText.getText().toString().trim());
                params.put("red_total_points",red_total_points_editText.getText().toString().trim());
                params.put("blue_total_points",blue_total_points_editText.getText().toString().trim());
                params.put("red_penalty_points",red_penalty_points_textView.getText().toString().trim());
                params.put("blue_penalty_points",blue_penalty_points_textView.getText().toString().trim());
                params.put("auto_cargo_lower_hu",auto_cargo_lower_hub_textView.getText().toString().trim());
                params.put("auto_cargo_upper_hub",auto_cargo_upper_hub_textView.getText().toString().trim());
                params.put("teleop_cargo_lower_hub",teleop_cargo_lower_hub_textView.getText().toString().trim());
                params.put("teleop_cargo_upper_hub",teleop_cargo_upper_hub_textView.getText().toString().trim());

                //Spinners
                params.put("driver_station_spinner",driver_station_spinner.getSelectedItem().toString().trim());
                params.put("climber_spinner",climber_spinner.getSelectedItem().toString().trim());
                params.put("penalty_card_spinner",penalty_card_spinner.getSelectedItem().toString().trim());

                //Boolean from switch
                params.put("move_auto_switch",String.valueOf(move_auto_switch.isChecked()).trim());
                params.put("broken_switch",String.valueOf(broken_switch.isChecked()).trim());
                params.put("disabled_switch",String.valueOf(disabled_switch.isChecked()).trim());

                return params;
            }
        };

        int socketTimeOut = 5000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
    }

    public void auto_cargo_lower_hub_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(auto_cargo_lower_hub_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        auto_cargo_lower_hub_textView.setText(a);
    }

    public void auto_cargo_lower_hub_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(auto_cargo_lower_hub_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        auto_cargo_lower_hub_textView.setText(a);
    }

    public void auto_cargo_upper_hub_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(auto_cargo_upper_hub_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        auto_cargo_upper_hub_textView.setText(a);
    }

    public void auto_cargo_upper_hub_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(auto_cargo_upper_hub_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        auto_cargo_upper_hub_textView.setText(a);
    }

    public void teleop_cargo_lower_hub_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(teleop_cargo_lower_hub_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        teleop_cargo_lower_hub_textView.setText(a);
    }

    public void teleop_cargo_lower_hub_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(teleop_cargo_lower_hub_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        teleop_cargo_lower_hub_textView.setText(a);
    }

    public void teleop_cargo_upper_hub_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(teleop_cargo_upper_hub_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        teleop_cargo_upper_hub_textView.setText(a);
    }

    public void teleop_cargo_upper_hub_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(teleop_cargo_upper_hub_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        teleop_cargo_upper_hub_textView.setText(a);
    }

    public void red_penalty_points_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(red_penalty_points_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        red_penalty_points_textView.setText(a);
    }

    public void red_penalty_points_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(red_penalty_points_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        red_penalty_points_textView.setText(a);
    }

    public void blue_penalty_points_add(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(blue_penalty_points_textView.getText().toString());
        //increment
        i = i + 1;
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        blue_penalty_points_textView.setText(a);
    }

    public void blue_penalty_points_min(View view) {
        // get current string and converts string to int
        int i = Integer.parseInt(blue_penalty_points_textView.getText().toString());
        //increment
        if (i <= 0) {
            i = 0;
        } else {
            i = i - 1;
        }
        // convert int to string
        String a = Integer.valueOf(i).toString();
        // sets the score to the corresponding text view
        blue_penalty_points_textView.setText(a);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}