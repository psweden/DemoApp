package com.demo.demoapp.view;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.demo.demoapp.R;
import com.demo.demoapp.controller.MainActivity;
import com.demo.demoapp.model.sqlite.DBHandler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingsActivity extends AppCompatActivity {

    private EditText inputName, inputPhonenumber, deleteNum;
    private Button button2, button1;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        inputName = findViewById(R.id.inputName);
        inputPhonenumber = findViewById(R.id.inputPhonenumber);
        button2 = findViewById(R.id.button2);
        button1 = findViewById(R.id.button1);
        deleteNum = findViewById(R.id.deleteNum);

        button1.setVisibility(View.GONE);
        deleteNum.setVisibility(View.GONE);

        dbHandler = new DBHandler(SettingsActivity.this);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // String nameofattribute, String phonenumberplusdtmf
                String nameofattribute = inputName.getText().toString();
                String phonenumberplusdtmf = inputPhonenumber.getText().toString();

                // validating if the text fields are empty or not.
                if (nameofattribute.isEmpty()&& phonenumberplusdtmf.isEmpty()) {
                    Toast.makeText(SettingsActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Add new user!
                dbHandler.addNewUser(nameofattribute, phonenumberplusdtmf);

                // Displaying a toast message to show the new user.
                Toast.makeText(SettingsActivity.this, "The user has been added.", Toast.LENGTH_SHORT).show();
                inputName.setText("");
                inputPhonenumber.setText("");
            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String nameofattribute, String phonenumberplusdtmf
                // below line is to get data from all edit text fields.
                String nameofattribute = deleteNum.getText().toString();

                // validating if the text fields are empty or not.
                if (nameofattribute.isEmpty()) {
                    Toast.makeText(SettingsActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }
                // after adding the data we are displaying a toast message.
                Toast.makeText(SettingsActivity.this, "The user" + nameofattribute + " has been added.", Toast.LENGTH_SHORT).show();
                deleteNum.setText("");
            }
        });
    }
}