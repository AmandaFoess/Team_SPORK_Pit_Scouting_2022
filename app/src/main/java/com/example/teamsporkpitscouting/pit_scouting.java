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
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class pit_scouting extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;
    EditText student_name, team_number, pit_number, robot_height, robot_weight, autonomous_strategy_comment, endgame_strategy_comment, drivetrain_type, order_of_wheels, number_of_wheels, additional_comments;
    Spinner cargo_number_spinner, cargo_pickup_location_spinner, cargo_shoot_location_spinner, cargo_shoot_location_hub_spinner, climber_level_spinner;
    String[] array = new String[16];
    CSVWriter writer;
    String csv, cargo_number_value, cargo_pickup_location_value, cargo_shoot_location_value, cargo_shoot_location_hub_value, climber_level_value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pit_scouting);

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
        cargo_pickup_location_spinner_category.add("Does not pick up");
        cargo_pickup_location_spinner_category.add("Human Player Station");
        cargo_pickup_location_spinner_category.add("On the Field");
        cargo_pickup_location_spinner_category.add("Picks Up Anywhere");

        ArrayAdapter<String> array_adapter_cargo_pickup_location_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_pickup_location_spinner_category);
        array_adapter_cargo_pickup_location_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_pickup_location_spinner.setAdapter(array_adapter_cargo_pickup_location_spinner);

        //Cargo Shooting Location
        Spinner cargo_shoot_location_spinner = findViewById(R.id.powercell_shooting_location_dropdown);
        cargo_shoot_location_spinner.setOnItemSelectedListener(this);

        List<String> cargo_shoot_location_spinner_category = new ArrayList<>();
        cargo_shoot_location_spinner_category.add("Does Not Shoot");
        cargo_shoot_location_spinner_category.add("Human Player Station");
        cargo_shoot_location_spinner_category.add("Launch Pad");
        cargo_shoot_location_spinner_category.add("Tarmac");
        cargo_shoot_location_spinner_category.add("Shoots Anywhere");

        ArrayAdapter<String> array_adapter_cargo_shoot_location_spinner= new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_shoot_location_spinner_category);
        array_adapter_cargo_shoot_location_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_shoot_location_spinner.setAdapter(array_adapter_cargo_shoot_location_spinner);

        //Cargo Shooting Location
        Spinner cargo_shoot_location_hub_spinner = findViewById(R.id.powercell_shooting_hub_dropdown);
        cargo_shoot_location_hub_spinner.setOnItemSelectedListener(this);

        List<String> cargo_shoot_location_hub_spinner_category = new ArrayList<>();
        cargo_shoot_location_hub_spinner_category.add("Does Not Shoot");
        cargo_shoot_location_hub_spinner_category.add("Lower Hub");
        cargo_shoot_location_hub_spinner_category.add("Upper Hub");
        cargo_shoot_location_hub_spinner_category.add("Shoots Anywhere");

        ArrayAdapter<String> array_adapter_cargo_shoot_location_hub_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cargo_shoot_location_hub_spinner_category);
        array_adapter_cargo_shoot_location_hub_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cargo_shoot_location_hub_spinner.setAdapter(array_adapter_cargo_shoot_location_hub_spinner);

        //Endgame strategy
        Spinner climber_level_spinner = findViewById(R.id.endgame_dropdown);
        climber_level_spinner.setOnItemSelectedListener(this);

        List<String> climber_level_spinner_category = new ArrayList<>();
        climber_level_spinner_category.add("Does Not Climb");
        climber_level_spinner_category.add("Low Rung");
        climber_level_spinner_category.add("Mid Rung");
        climber_level_spinner_category.add("High Rung");
        climber_level_spinner_category.add("Traversal Rung");

        ArrayAdapter<String> array_adapter_climber_level_spinner = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, climber_level_spinner_category);
        array_adapter_climber_level_spinner.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        climber_level_spinner.setAdapter(array_adapter_climber_level_spinner);

        csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/TeamSPORK_PitScouting.csv");
    }

    @Override
    public void onClick(View v) {
        add_data_to_sheet();
    }

    public void add_data_to_sheet() {

        try{
            writer = new CSVWriter(new FileWriter(csv, true));

            array[0] = ("null");
            array[1] = ("null");
            array[2] = ("null");
            array[3] = ("null");
            array[4] = ("null");
            array[5] = ("null");
            array[6] = ("null");
            array[7] = ("null");
            array[8] = ("null");
            array[9] = ("null");
            array[10] = ("null");
            array[11] = ("null");
            array[12] = ("null");
            array[13] = ("null");
            array[14] = ("null");
            array[15] = ("null");

            array[0] = student_name.getText().toString().trim();
            array[1] = team_number.getText().toString().trim();
            array[2] = pit_number.getText().toString().trim();
            array[3] = robot_height.getText().toString().trim();
            array[4] = robot_weight.getText().toString().trim();
            array[5] = autonomous_strategy_comment.getText().toString().trim();
            array[6] = endgame_strategy_comment.getText().toString().trim();
            array[7] = drivetrain_type.getText().toString().trim();
            array[8] = order_of_wheels.getText().toString().trim();
            array[9] = number_of_wheels.getText().toString().trim();
            array[10] = additional_comments.getText().toString().trim();
            array[11] = cargo_number_value.trim();
            array[12] = cargo_pickup_location_value.trim();
            array[13] = cargo_shoot_location_value.trim();
            array[14] = cargo_shoot_location_hub_value.trim();
            array[15] = climber_level_value.trim();

            writer.writeNext(array);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (parent == cargo_number_spinner) {
            cargo_number_value = item;
        } else if (parent == cargo_pickup_location_spinner){
            cargo_pickup_location_value = item;
        } else if (parent == cargo_shoot_location_spinner){
            cargo_shoot_location_value = item;
        } else if (parent == cargo_shoot_location_hub_spinner){
            cargo_shoot_location_hub_value = item;
        } else if (parent == climber_level_spinner){
            climber_level_value = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}