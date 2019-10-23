package com.example.carwash;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class FormFragment extends Fragment {
    private EditText numberOfWashesInput;
    private RadioGroup radioGroupWash;
    private RadioButton extRadio;
    private RadioButton fullRadio;
    private Button calculateButton;
    private boolean radioIsSelected = false;

    private WashPackage washPackage;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_form, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        washPackage = new WashPackage();

        numberOfWashesInput = view.findViewById(R.id.numberofwashesinput);
        radioGroupWash = view.findViewById(R.id.radioGroupWash);
        extRadio = view.findViewById(R.id.radioexterior);
        fullRadio = view.findViewById(R.id.radiofull);
        calculateButton = view.findViewById(R.id.calculateButton);

        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(radioIsSelected) {
                    //if the input is empty, the default value set would be 1
                    if(numberOfWashesInput.getText().toString().matches("")) {
                        numberOfWashesInput.setText("1");
                    }

                    //gets the input value
                    int numberOfWashes = Integer.parseInt(numberOfWashesInput.getText().toString());
                    washPackage.setNumberOfWashes(numberOfWashes);
                    openResultActivity();
                } else {
                    Toast.makeText(getContext(), "Please select an option", Toast.LENGTH_LONG).show();
                }
            }
        });

        //checks which radio button is checked
        radioGroupWash.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
            if(extRadio.isChecked()) {
                washPackage.setPricePerWash(washPackage.EXT);
            } else if(fullRadio.isChecked()){
                washPackage.setPricePerWash(washPackage.FULL);
            }

            radioIsSelected = true;
            }
        });
    }

    //method to open ResultActivity
    public void openResultActivity() {
        String formattedTotalPrice = String.format("%.2f", washPackage.getTotalPrice());

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            Intent intent = new Intent(getActivity(), ResultActivity.class);
            intent.putExtra("total_string", formattedTotalPrice);
            startActivity(intent);
        } else {
            FragmentTransaction t = getActivity().getSupportFragmentManager().beginTransaction();
            Fragment rf = new ResultFragment();
            Bundle bundle = new Bundle();
            bundle.putString("total_string", formattedTotalPrice);
            rf.setArguments(bundle);
            t.add(R.id.result_fragment, rf);
            t.commit();
        }


    }
}
