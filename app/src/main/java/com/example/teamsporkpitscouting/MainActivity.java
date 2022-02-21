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

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;
    EditText student_name, team_number, pit_number, robot_height, robot_weight, autonomous_strategy_comment, endgame_strategy_comment, drivetrain_type, order_of_wheels, number_of_wheels, additional_comments;
    Spinner cargo_number_spinner, cargo_pickup_location_spinner, cargo_shoot_location_spinner, cargo_shoot_location_hub_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        submit_button = findViewById(R.id.submit_button);
        submit_button.setOnClickListener(this);

        student_name = findViewById(R.id.name_editText);
        team_number = findViewById(R.id.team_number_editText);
        pit_number = findViewById(R.id.pit_number_editText);
        robot_height = findViewById(R.id.height_editText);
        robot_weight = findViewById(R.id.weight_editText);
        autonomous_strategy_comment = findViewById(R.id.autonomous_comment);
        endgame_strategy_comment = findViewById(R.id.endgame_comment);
        drivetrain_type = findViewById(R.id.drivetrain_editText);
        order_of_wheels = findViewById(R.id.editTextOrderOfWheels);
        number_of_wheels = findViewById(R.id.wheelNumber_editText);
        additional_comments = findViewById(R.id.additional_comment);

        //Cargo Number Spinner
        Spinner cargo_number_spinner = findViewById(R.id.powercell_number_dropdown);
        cargo_number_spinner.setOnItemSelectedListener(this);

        List<String> cargo_number_spinner_category = new ArrayList<>();
        cargo_number_spinner_category.add("0");
        cargo_number_spinner_category.add("1");
        cargo_number_spinner_category.add("2");

        ArrayAdapter<String> array_adapter_cargo_number_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_number_spinner_category);
        array_adapter_cargo_number_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_number_spinner.setAdapter(array_adapter_cargo_number_spinner);

        //Cargo Pickup Location
        Spinner cargo_pickup_location_spinner = findViewById(R.id.powercell_pickup_location_dropdown);
        cargo_pickup_location_spinner.setOnItemSelectedListener(this);

        List<String> cargo_pickup_location_spinner_category = new ArrayList<>();
        cargo_pickup_location_spinner_category.add("Human PLayer Station");
        cargo_pickup_location_spinner_category.add("On the Field");

        ArrayAdapter<String> array_adapter_cargo_pickup_location_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_pickup_location_spinner_category);
        array_adapter_cargo_pickup_location_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_pickup_location_spinner.setAdapter(array_adapter_cargo_pickup_location_spinner);

        //Cargo Shooting Location
        Spinner cargo_shoot_location_spinner = findViewById(R.id.powercell_shooting_location_dropdown);
        cargo_shoot_location_spinner.setOnItemSelectedListener(this);

        List<String> cargo_shoot_location_spinner_category = new ArrayList<>();
        cargo_shoot_location_spinner_category.add("Human PLayer Station");
        cargo_shoot_location_spinner_category.add("Launch Pad");
        cargo_shoot_location_spinner_category.add("Tarmac");

        ArrayAdapter<String> array_adapter_cargo_shoot_location_spinner= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_shoot_location_spinner_category);
        array_adapter_cargo_shoot_location_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_shoot_location_spinner.setAdapter(array_adapter_cargo_shoot_location_spinner);

        //Cargo Shooting Location
        Spinner cargo_shoot_location_hub_spinner = findViewById(R.id.powercell_shooting_hub_dropdown);
        cargo_shoot_location_hub_spinner.setOnItemSelectedListener(this);

        List<String> cargo_shoot_location_hub_spinner_category = new ArrayList<>();
        cargo_shoot_location_hub_spinner_category.add("Lower Hub");
        cargo_shoot_location_hub_spinner_category.add("Upper Hub");

        ArrayAdapter<String> array_adapter_cargo_shoot_location_hub_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_shoot_location_hub_spinner_category);
        array_adapter_cargo_shoot_location_hub_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_shoot_location_hub_spinner.setAdapter(array_adapter_cargo_shoot_location_hub_spinner);
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

                //Text
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

                //Spinners
                params.put("cargo_number_spinner",cargo_number_spinner.getSelectedItem().toString().trim());
                params.put("cargo_pickup_location_spinner",cargo_pickup_location_spinner.getSelectedItem().toString().trim());
                params.put("cargo_shoot_location_spinner",cargo_shoot_location_spinner.getSelectedItem().toString().trim());
                params.put("cargo_shoot_location_hub_spinner",cargo_shoot_location_hub_spinner.getSelectedItem().toString().trim());

                return params;
            }
        };

        int socketTimeOut = 5000;// u can change this .. here it is 50 seconds

        RetryPolicy retryPolicy = new DefaultRetryPolicy(socketTimeOut, 0, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(retryPolicy);

        RequestQueue queue = Volley.newRequestQueue(this);

        queue.add(stringRequest);
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