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
    String csv, sensor_index_value, chain_index_value, belts_index_value, pulleys_index_value, motor_index_value, baseplate_index_value, structure_index_value, feeder_rail_index_value, bearings_index_value,  chain_chassis_value, wheel_sprockets_chassis_value, gearboxes_chassis_value, falcon_chassis_value, versaframe_structure_chassis_value, bellypan_chassis_value, intake_slot_chassis_value, fasteners_chassis_value, wheels_chassis_value, bearings_intake_value, mecanum_wheels_intake_value, compliance_wheels_intake_value, spacers_intake_value, thunderhex_rod_intake_value, mounting_plates_intake_value, chassis_mount_intake_value, motor_gearbox_intake_value, traction_wheels_shooter_value, neo_motors_shooter_value, motors_shooter_value, welds_shooter_value, shooting_plate_shooter_value, index_connector_shooter_value, winch_climber_value, motors_climber_value, rope_climber_value, lower_chassis_mounts_climber_value, bearings_climber_value, pistons_climber_value, versaframe_climber_value, pass_through_hooks_climber_value, extendable_arms_climber_value, arm_bearing_blocks_climber_value, sensor_climber_value;
    Spinner sensor_index_spinner, chain_index_spinner, belts_index_spinner, pulleys_index_spinner, motor_index_spinner, baseplate_index_spinner, structure_index_spinner, feeder_rail_index_spinner, bearings_index_spinner,  chain_chassis_spinner, wheel_sprockets_chassis_spinner, gearboxes_chassis_spinner, falcon_chassis_spinner, versaframe_structure_chassis_spinner, bellypan_chassis_spinner, intake_slot_chassis_spinner, fasteners_chassis_spinner, wheels_chassis_spinner, bearings_intake_spinner, mecanum_wheels_intake_spinner, compliance_wheels_intake_spinner, spacers_intake_spinner, thunderhex_rod_intake_spinner, mounting_plates_intake_spinner, chassis_mount_intake_spinner, motor_gearbox_intake_spinner, traction_wheels_shooter_spinner, neo_motors_shooter_spinner, motors_shooter_spinner, welds_shooter_spinner, shooting_plate_shooter_spinner, index_connector_shooter_spinner, winch_climber_spinner, motors_climber_spinner, rope_climber_spinner, lower_chassis_mounts_climber_spinner, bearings_climber_spinner, pistons_climber_spinner, versaframe_climber_spinner, pass_through_hooks_climber_spinner, extendable_arms_climber_spinner, arm_bearing_blocks_climber_spinner, sensor_climber_spinner;

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

        chain_index_spinner = findViewById(R.id.chain_index_dropdown);
        chain_index_spinner.setOnItemSelectedListener(this);
        chain_index_spinner.setAdapter(array_adapter_pit_spinner);

        belts_index_spinner = findViewById(R.id.belts_index_dropdown);
        belts_index_spinner.setOnItemSelectedListener(this);
        belts_index_spinner.setAdapter(array_adapter_pit_spinner);

        pulleys_index_spinner = findViewById(R.id.pulleys_index_dropdown);
        pulleys_index_spinner.setOnItemSelectedListener(this);
        pulleys_index_spinner.setAdapter(array_adapter_pit_spinner);

        motor_index_spinner = findViewById(R.id.motor_index_dropdown);
        motor_index_spinner.setOnItemSelectedListener(this);
        motor_index_spinner.setAdapter(array_adapter_pit_spinner);

        baseplate_index_spinner = findViewById(R.id.baseplate_index_dropdown);
        baseplate_index_spinner.setOnItemSelectedListener(this);
        baseplate_index_spinner.setAdapter(array_adapter_pit_spinner);

        structure_index_spinner = findViewById(R.id.structure_index_dropdown);
        structure_index_spinner.setOnItemSelectedListener(this);
        structure_index_spinner.setAdapter(array_adapter_pit_spinner);

        feeder_rail_index_spinner = findViewById(R.id.feeder_rail_index_dropdown);
        feeder_rail_index_spinner.setOnItemSelectedListener(this);
        feeder_rail_index_spinner.setAdapter(array_adapter_pit_spinner);

        bearings_index_spinner = findViewById(R.id.bearings_index_dropdown);
        chain_index_spinner.setOnItemSelectedListener(this);
        chain_index_spinner.setAdapter(array_adapter_pit_spinner);

        chain_chassis_spinner = findViewById(R.id.chain_chassis_dropdown);
        chain_chassis_spinner.setOnItemSelectedListener(this);
        chain_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        wheel_sprockets_chassis_spinner = findViewById(R.id.wheel_sprockets_chassis_dropdown);
        wheel_sprockets_chassis_spinner.setOnItemSelectedListener(this);
        wheel_sprockets_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        gearboxes_chassis_spinner = findViewById(R.id.gearboxes_chassis_dropdown);
        gearboxes_chassis_spinner.setOnItemSelectedListener(this);
        gearboxes_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        falcon_chassis_spinner = findViewById(R.id.falcon_chassis_dropdown);
        falcon_chassis_spinner.setOnItemSelectedListener(this);
        falcon_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        versaframe_structure_chassis_spinner = findViewById(R.id.versaframe_structure_chassis_dropdown);
        versaframe_structure_chassis_spinner.setOnItemSelectedListener(this);
        versaframe_structure_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        bellypan_chassis_spinner = findViewById(R.id.bellypan_chassis_dropdown);
        bellypan_chassis_spinner.setOnItemSelectedListener(this);
        bellypan_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        intake_slot_chassis_spinner = findViewById(R.id.intake_slot_chassis_dropdown);
        intake_slot_chassis_spinner.setOnItemSelectedListener(this);
        intake_slot_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        fasteners_chassis_spinner = findViewById(R.id.fasteners_chassis_dropdown);
        fasteners_chassis_spinner.setOnItemSelectedListener(this);
        fasteners_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        wheels_chassis_spinner = findViewById(R.id.wheels_chassis_dropdown);
        wheels_chassis_spinner.setOnItemSelectedListener(this);
        wheels_chassis_spinner.setAdapter(array_adapter_pit_spinner);

        bearings_intake_spinner = findViewById(R.id.bearings_intake_dropdown);
        bearings_intake_spinner.setOnItemSelectedListener(this);
        bearings_intake_spinner.setAdapter(array_adapter_pit_spinner);

        mecanum_wheels_intake_spinner = findViewById(R.id.mecanum_wheels_intake_dropdown);
        mecanum_wheels_intake_spinner.setOnItemSelectedListener(this);
        mecanum_wheels_intake_spinner.setAdapter(array_adapter_pit_spinner);

        compliance_wheels_intake_spinner = findViewById(R.id.compliance_wheels_intake_dropdown);
        compliance_wheels_intake_spinner.setOnItemSelectedListener(this);
        compliance_wheels_intake_spinner.setAdapter(array_adapter_pit_spinner);

        spacers_intake_spinner = findViewById(R.id.spacers_intake_dropdown);
        spacers_intake_spinner.setOnItemSelectedListener(this);
        spacers_intake_spinner.setAdapter(array_adapter_pit_spinner);

        thunderhex_rod_intake_spinner = findViewById(R.id.thunderhex_rod_intake_dropdown);
        thunderhex_rod_intake_spinner.setOnItemSelectedListener(this);
        thunderhex_rod_intake_spinner.setAdapter(array_adapter_pit_spinner);

        mounting_plates_intake_spinner = findViewById(R.id.mounting_plates_intake_dropdown);
        mounting_plates_intake_spinner.setOnItemSelectedListener(this);
        mounting_plates_intake_spinner.setAdapter(array_adapter_pit_spinner);

        chassis_mount_intake_spinner = findViewById(R.id.chassis_mount_intake_dropdown);
        chassis_mount_intake_spinner.setOnItemSelectedListener(this);
        chassis_mount_intake_spinner.setAdapter(array_adapter_pit_spinner);

        motor_gearbox_intake_spinner = findViewById(R.id.motor_gearbox_intake_dropdown);
        motor_gearbox_intake_spinner.setOnItemSelectedListener(this);
        motor_gearbox_intake_spinner.setAdapter(array_adapter_pit_spinner);

        traction_wheels_shooter_spinner = findViewById(R.id.traction_wheels_shooter_dropdown);
        traction_wheels_shooter_spinner.setOnItemSelectedListener(this);
        traction_wheels_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        neo_motors_shooter_spinner = findViewById(R.id.neo_motors_shooter_dropdown);
        neo_motors_shooter_spinner.setOnItemSelectedListener(this);
        neo_motors_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        motors_shooter_spinner = findViewById(R.id.motors_shooter_dropdown);
        motors_shooter_spinner.setOnItemSelectedListener(this);
        motors_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        welds_shooter_spinner = findViewById(R.id.welds_shooter_dropdown);
        welds_shooter_spinner.setOnItemSelectedListener(this);
        welds_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        shooting_plate_shooter_spinner = findViewById(R.id.shooting_plate_shooter_dropdown);
        shooting_plate_shooter_spinner.setOnItemSelectedListener(this);
        shooting_plate_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        index_connector_shooter_spinner = findViewById(R.id.index_connector_shooter_dropdown);
        index_connector_shooter_spinner.setOnItemSelectedListener(this);
        index_connector_shooter_spinner.setAdapter(array_adapter_pit_spinner);

        winch_climber_spinner = findViewById(R.id.winch_climber_dropdown);
        winch_climber_spinner.setOnItemSelectedListener(this);
        winch_climber_spinner.setAdapter(array_adapter_pit_spinner);

        motors_climber_spinner = findViewById(R.id.motors_climber_dropdown);
        motors_climber_spinner.setOnItemSelectedListener(this);
        motors_climber_spinner.setAdapter(array_adapter_pit_spinner);

        rope_climber_spinner = findViewById(R.id.rope_climber_dropdown);
        rope_climber_spinner.setOnItemSelectedListener(this);
        rope_climber_spinner.setAdapter(array_adapter_pit_spinner);

        lower_chassis_mounts_climber_spinner = findViewById(R.id.lower_chassis_mounts_climber_dropdown);
        lower_chassis_mounts_climber_spinner.setOnItemSelectedListener(this);
        lower_chassis_mounts_climber_spinner.setAdapter(array_adapter_pit_spinner);

        bearings_climber_spinner = findViewById(R.id.bearings_climber_dropdown);
        bearings_climber_spinner.setOnItemSelectedListener(this);
        bearings_climber_spinner.setAdapter(array_adapter_pit_spinner);

        pistons_climber_spinner = findViewById(R.id.pistons_climber_dropdown);
        pistons_climber_spinner.setOnItemSelectedListener(this);
        pistons_climber_spinner.setAdapter(array_adapter_pit_spinner);

        versaframe_climber_spinner = findViewById(R.id.versaframe_climber_dropdown);
        versaframe_climber_spinner.setOnItemSelectedListener(this);
        versaframe_climber_spinner.setAdapter(array_adapter_pit_spinner);

        pass_through_hooks_climber_spinner = findViewById(R.id.pass_through_hooks_climber_dropdown);
        pass_through_hooks_climber_spinner.setOnItemSelectedListener(this);
        pass_through_hooks_climber_spinner.setAdapter(array_adapter_pit_spinner);

        extendable_arms_climber_spinner = findViewById(R.id.extendable_arms_climber_dropdown);
        extendable_arms_climber_spinner.setOnItemSelectedListener(this);
        extendable_arms_climber_spinner.setAdapter(array_adapter_pit_spinner);

        arm_bearing_blocks_climber_spinner = findViewById(R.id.arm_bearing_blocks_climber_dropdown);
        arm_bearing_blocks_climber_spinner.setOnItemSelectedListener(this);
        arm_bearing_blocks_climber_spinner.setAdapter(array_adapter_pit_spinner);

        sensor_climber_spinner = findViewById(R.id.sensors_climber_dropdown);
        sensor_climber_spinner.setOnItemSelectedListener(this);
        sensor_climber_spinner.setAdapter(array_adapter_pit_spinner);
    }


    @Override
    public void onClick(View v) {
        add_pit_issue_log_data();
    }

    public void add_pit_issue_log_data() {

    }

    @Override
    public void onItemSelected(AdapterView<?>  parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        if (parent == sensor_index_spinner) {
            sensor_index_value = item;
        } else if (parent == chain_index_spinner){
            chain_index_value = item;
        } else if (parent == belts_index_spinner){
            belts_index_value = item;
        } else if (parent == pulleys_index_spinner){
            pulleys_index_value = item;
        } else if (parent == motor_index_spinner){
            motor_index_value = item;
        } else if (parent == baseplate_index_spinner){
            baseplate_index_value = item;
        } else if (parent == structure_index_spinner){
            structure_index_value = item;
        } else if (parent == feeder_rail_index_spinner){
            feeder_rail_index_value = item;
        } else if (parent == bearings_index_spinner){
            bearings_index_value = item;
        } else if (parent == chain_chassis_spinner){
            chain_chassis_value = item;
        } else if (parent == wheel_sprockets_chassis_spinner){
            wheel_sprockets_chassis_value = item;
        } else if (parent == gearboxes_chassis_spinner){
            gearboxes_chassis_value = item;
        } else if (parent == falcon_chassis_spinner){
            falcon_chassis_value = item;
        } else if (parent == versaframe_structure_chassis_spinner){
            versaframe_structure_chassis_value= item;
        } else if (parent == bellypan_chassis_spinner){
            bellypan_chassis_value = item;
        } else if (parent == intake_slot_chassis_spinner){
            intake_slot_chassis_value = item;
        } else if (parent == fasteners_chassis_spinner){
            fasteners_chassis_value = item;
        } else if (parent == wheels_chassis_spinner){
            wheels_chassis_value = item;
        } else if (parent == bearings_intake_spinner){
            bearings_intake_value= item;
        } else if (parent == mecanum_wheels_intake_spinner){
            mecanum_wheels_intake_value = item;
        } else if (parent == compliance_wheels_intake_spinner){
            compliance_wheels_intake_value = item;
        } else if (parent == spacers_intake_spinner){
            spacers_intake_value = item;
        } else if (parent == thunderhex_rod_intake_spinner){
            thunderhex_rod_intake_value = item;
        } else if (parent == mounting_plates_intake_spinner){
            mounting_plates_intake_value = item;
        } else if (parent == chassis_mount_intake_spinner){
            chassis_mount_intake_value = item;
        } else if (parent == motor_gearbox_intake_spinner){
            motor_gearbox_intake_value = item;
        } else if (parent == traction_wheels_shooter_spinner){
            traction_wheels_shooter_value = item;
        } else if (parent == neo_motors_shooter_spinner){
            neo_motors_shooter_value = item;
        } else if (parent == motors_shooter_spinner){
            motors_shooter_value = item;
        } else if (parent == welds_shooter_spinner){
            welds_shooter_value = item;
        } else if (parent == shooting_plate_shooter_spinner){
            shooting_plate_shooter_value = item;
        } else if (parent == index_connector_shooter_spinner){
            index_connector_shooter_value = item;
        } else if (parent == winch_climber_spinner){
            winch_climber_value = item;
        } else if (parent == motors_climber_spinner ){
            motors_climber_value = item;
        } else if (parent == rope_climber_spinner){
            rope_climber_value = item;
        } else if (parent == lower_chassis_mounts_climber_spinner){
            lower_chassis_mounts_climber_value= item;
        } else if (parent == bearings_climber_spinner){
            bearings_climber_value = item;
        } else if (parent == pistons_climber_spinner){
            pistons_climber_value = item;
        } else if (parent == versaframe_climber_spinner){
            versaframe_climber_value = item;
        } else if (parent == pass_through_hooks_climber_spinner){
            pass_through_hooks_climber_value = item;
        } else if (parent == extendable_arms_climber_spinner){
            extendable_arms_climber_value= item;
        } else if (parent == arm_bearing_blocks_climber_spinner){
            arm_bearing_blocks_climber_value= item;
        } else if (parent == sensor_climber_spinner){
            sensor_climber_value = item;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}