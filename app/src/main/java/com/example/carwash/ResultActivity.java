package com.example.carwash;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class ResultActivity extends AppCompatActivity {
    private TextView total;
    private Button submitBtn;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        ResultFragment rf = new ResultFragment();

        Bundle arguments = new Bundle();
        String string_key = "total_string";
        String response = getIntent().getStringExtra(string_key);

        arguments.putString(string_key, response);
        rf.setArguments(arguments);
        ft.add(R.id.result_activity, rf);
        ft.commit();
    }
}
