package com.example.phoneauthdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;


public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    Spinner spinner;
    EditText edt_phoneNumber;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        edt_phoneNumber = findViewById(R.id.edt_phoneNumber);
        btnContinue = findViewById(R.id.btn_continue);

        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String countryCode = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];
                Log.d(TAG, "countryCode: "+ countryCode);
                String number = edt_phoneNumber.getText().toString();
                Log.d(TAG, "number: "+ number);

                String phoneNumber = "+" + countryCode + number;
                Log.d(TAG, "phoneNumber: "+ phoneNumber);

                Intent intent = new Intent(MainActivity.this, VerifyCodeActivity.class);
                intent.putExtra("phoneNumber", phoneNumber);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(FirebaseAuth.getInstance().getCurrentUser() != null){
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
        }
    }
}