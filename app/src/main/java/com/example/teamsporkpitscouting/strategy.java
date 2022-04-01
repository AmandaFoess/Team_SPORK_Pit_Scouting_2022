package com.example.teamsporkpitscouting;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class strategy extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    Button submit_button;
    EditText student_name_editText, team_number_editText, match_number_editText, red_total_points_editText, blue_total_points_editText, additional_comments_editText;
    TextView auto_cargo_lower_hub_textView, auto_cargo_upper_hub_textView, teleop_cargo_lower_hub_textView, teleop_cargo_upper_hub_textView, red_penalty_points_textView, blue_penalty_points_textView;
    Spinner driver_station_spinner, climber_spinner, penalty_card_spinner;
    Switch move_auto_switch, broken_switch, disabled_switch;
    String driver_station_value, climber_value, penalty_card_value, csv;
    String[] array = new String[18];
    CSVWriter writer;

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
        climber_spinner_list.add("High Rung");
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

        csv = (Environment.getExternalStorageDirectory().getAbsolutePath() + "/TeamSPORK_Strategy.csv");
    }

    @Override
    public void onClick(View v) {
        add_strategy_data_to_sheet();
    }

    public void add_strategy_data_to_sheet() {
        try {
            writer = new CSVWriter(new FileWriter(csv, true));

            array[0] = student_name_editText.getText().toString().trim();
            array[1] = team_number_editText.getText().toString().trim();
            array[2] = match_number_editText.getText().toString().trim();
            array[3] = red_total_points_editText.getText().toString().trim();
            array[4] = blue_total_points_editText.getText().toString().trim();
            array[5] = red_penalty_points_textView.getText().toString().trim();
            array[6] = blue_penalty_points_textView.getText().toString().trim();
            array[7] = auto_cargo_lower_hub_textView.getText().toString().trim();
            array[8] = auto_cargo_upper_hub_textView.getText().toString().trim();
            array[9] = teleop_cargo_lower_hub_textView.getText().toString().trim();
            array[10] = teleop_cargo_upper_hub_textView.getText().toString().trim();
            array[11] = driver_station_value.trim();
            array[12] = climber_value.trim();
            array[13] = penalty_card_value.trim();
            array[14] = String.valueOf(move_auto_switch.isChecked()).trim();
            array[15] = String.valueOf(broken_switch.isChecked()).trim();
            array[16] = String.valueOf(disabled_switch.isChecked()).trim();
            array[17] = additional_comments_editText.getText().toString().trim();

            writer.writeNext(array);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        student_name_editText.setText("");
        team_number_editText.setText("");
        match_number_editText.setText("");
        red_total_points_editText.setText("");
        blue_total_points_editText.setText("");
        additional_comments_editText.setText("");
        red_penalty_points_textView.setText("0");
        blue_penalty_points_textView.setText("0");
        auto_cargo_lower_hub_textView.setText("0");
        auto_cargo_upper_hub_textView.setText("0");
        teleop_cargo_lower_hub_textView.setText("0");
        teleop_cargo_upper_hub_textView.setText("0");

        move_auto_switch.setChecked(false);
        broken_switch.setChecked(false);
        disabled_switch.setChecked(false);

        driver_station_spinner.setSelection(0);
        climber_spinner.setSelection(0);
        penalty_card_spinner.setSelection(0);
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
        if (parent == driver_station_spinner) {
            driver_station_value = item;
        } else if (parent == climber_spinner){
            climber_value = item;
        } else if (parent == penalty_card_spinner){
            penalty_card_value = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}