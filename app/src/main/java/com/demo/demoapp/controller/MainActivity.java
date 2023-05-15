package com.demo.demoapp.controller;

/**
 * Peter Albertsson 20230215
 *
 * Teknik-stack
 * - Antroid emulator
 * - Extern Android telefon
 * - Android SDK
 * - Andoid Studio
 * - Git
 * - GitHub
 * - Java 11
 * - Nätverk GSM -> mot telefonväxlar med DTMF signaler!
 * */

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.demo.demoapp.R;
import com.demo.demoapp.model.sqlite.DBHandler;
import com.demo.demoapp.view.SettingsActivity;

public class MainActivity extends AppCompatActivity {

    private String id = "", attributeName  = "", dtmfDigits  = "", size = "";
    private Button button1, button2, button3, button4, button5;
    private DBHandler dbHandler;
    private String[] parts = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbHandler = new DBHandler(MainActivity.this);
        this.size = dbHandler.getDBSize();
        int sizeOfDB = Integer.parseInt(size);
        setButtonID();

        if(sizeOfDB == 1)
        {
            button2.setVisibility(View.GONE);
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
        }
        if(sizeOfDB == 2)
        {
            button3.setVisibility(View.GONE);
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
        }
        if(sizeOfDB == 3)
        {
            button4.setVisibility(View.GONE);
            button5.setVisibility(View.GONE);
        }
        if(sizeOfDB == 4)
        {
            button5.setVisibility(View.GONE);
        }

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String recordPost = dbHandler.getRecordPost(1);
                splitRecordPost(recordPost);
                button1.setText(attributeName);
                makePhoneCall(dtmfDigits);
                Toast.makeText(MainActivity.this, "This button is BUTTON 1 | ID: 1 | DTMF: " + dtmfDigits,
                        Toast.LENGTH_SHORT).show();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recordPost = dbHandler.getRecordPost(2);
                splitRecordPost(recordPost);
                button2.setText(attributeName);
                makePhoneCall(dtmfDigits);
                Toast.makeText(MainActivity.this, "This button is BUTTON 2 | ID: 2 | DTMF: " + dtmfDigits,
                        Toast.LENGTH_SHORT).show();
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recordPost = dbHandler.getRecordPost(3);
                splitRecordPost(recordPost);
                button3.setText(attributeName);
                makePhoneCall(dtmfDigits);
                Toast.makeText(MainActivity.this, "This button is BUTTON 3 | ID: 3 | DTMF: " + dtmfDigits,
                        Toast.LENGTH_SHORT).show();
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recordPost = dbHandler.getRecordPost(4);
                splitRecordPost(recordPost);
                button4.setText(attributeName);
                makePhoneCall(dtmfDigits);
                Toast.makeText(MainActivity.this, "This button is BUTTON 4 | ID: 4 | DTMF: " + dtmfDigits,
                        Toast.LENGTH_SHORT).show();
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String recordPost = dbHandler.getRecordPost(5);
                splitRecordPost(recordPost);
                button5.setText(attributeName);
                makePhoneCall(dtmfDigits);
                Toast.makeText(MainActivity.this, "This button is BUTTON 5 | ID: 5 | DTMF: " + dtmfDigits,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void setButtonID()
    {
        this.button1 = (Button) findViewById(R.id.button1);
        this.button2 = (Button) findViewById(R.id.button2);
        this.button3 = (Button) findViewById(R.id.button3);
        this.button4 = (Button) findViewById(R.id.button4);
        this.button5 = (Button) findViewById(R.id.button5);

        int sizeOfDb = Integer.parseInt(size);
        if (sizeOfDb == 0) {
            Toast.makeText(getBaseContext(), "Please add a new user!", Toast.LENGTH_SHORT).show();
            Intent intent1 = new Intent(this, SettingsActivity.class);
            startActivity(intent1);
        }

        if (sizeOfDb == 1) {
            String recordPost = dbHandler.getRecordPost(1);
            splitRecordPost(recordPost);
            button1.setText(attributeName);
        }
        if (sizeOfDb == 2) {
            String recordPost = dbHandler.getRecordPost(1);
            splitRecordPost(recordPost);
            button1.setText(attributeName);

            String recordPost2 = dbHandler.getRecordPost(2);
            splitRecordPost(recordPost2);
            button2.setText(attributeName);
        }
        if (sizeOfDb == 3) {
            String recordPost = dbHandler.getRecordPost(1);
            splitRecordPost(recordPost);
            button1.setText(attributeName);

            String recordPost2 = dbHandler.getRecordPost(2);
            splitRecordPost(recordPost2);
            button2.setText(attributeName);

            String recordPost3 = dbHandler.getRecordPost(3);
            splitRecordPost(recordPost3);
            button3.setText(attributeName);
        }
        if (sizeOfDb == 4) {
            String recordPost = dbHandler.getRecordPost(1);
            splitRecordPost(recordPost);
            button1.setText(attributeName);

            String recordPost2 = dbHandler.getRecordPost(2);
            splitRecordPost(recordPost2);
            button2.setText(attributeName);

            String recordPost3 = dbHandler.getRecordPost(3);
            splitRecordPost(recordPost3);
            button3.setText(attributeName);

            String recordPost4 = dbHandler.getRecordPost(4);
            splitRecordPost(recordPost4);
            button4.setText(attributeName);
        }
        if (sizeOfDb == 5) {
            String recordPost = dbHandler.getRecordPost(1);
            splitRecordPost(recordPost);
            button1.setText(attributeName);

            String recordPost2 = dbHandler.getRecordPost(2);
            splitRecordPost(recordPost2);
            button2.setText(attributeName);

            String recordPost3 = dbHandler.getRecordPost(3);
            splitRecordPost(recordPost3);
            button3.setText(attributeName);

            String recordPost4 = dbHandler.getRecordPost(4);
            splitRecordPost(recordPost4);
            button4.setText(attributeName);

            String recordPost5 = dbHandler.getRecordPost(5);
            splitRecordPost(recordPost5);
            button5.setText(attributeName);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Set >> Update icon on actionbar
        MenuInflater inflater1 = getMenuInflater();
        inflater1.inflate(R.menu.main_menu, menu);
        // Find the menuItem to add your SubMenu
        MenuItem myMenuItem = menu.findItem(R.id.my_menu_item);
        getMenuInflater().inflate(R.menu.sub_menu, myMenuItem.getSubMenu());

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // About the App
            case R.id.submenu_one:
                Toast.makeText(getBaseContext(), "OM!", Toast.LENGTH_SHORT).show();
                break;
            // Settings
            case R.id.submenu_two:
                //Toast.makeText(getBaseContext(), "INSTÄLLNINGAR!", Toast.LENGTH_SHORT).show();
                Intent intent1 = new Intent(this, SettingsActivity.class);
                startActivity(intent1);
                break;
            // Exit
            case R.id.submenu_three:
                alertExit();
                default:
                return super.onOptionsItemSelected(item);
        }
        return false;
    }

    public void alertExit(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?").setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void makePhoneCall(String phoneNumber){
        Intent intentCallForward = new Intent(Intent.ACTION_CALL);
        Uri uri = Uri.fromParts("tel", phoneNumber, "#"); //
        intentCallForward.setData(uri);
        startActivity(intentCallForward);
    }

    public void splitRecordPost(String recordPost)
    {
        recordPost = recordPost.replace("||", ",");

        String string = recordPost;
        parts = string.split(",");
        this.attributeName = parts[1].trim();
        this.dtmfDigits = parts[2].trim();
    }
}