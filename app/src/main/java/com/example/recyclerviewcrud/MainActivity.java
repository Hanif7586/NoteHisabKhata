package com.example.recyclerviewcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;

import static com.example.recyclerviewcrud.DBmain.TABLENAME;

public class MainActivity extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    TextInputLayout fname, lname, llocation, lponnername, lponnerporiman, ladvancetk, lbakitk;
    Button submit, display, edit;
    Button buy,sell;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dBmain = new DBmain(this);
        // Create method
        findid();
        insertData();
        editData();



        LottieAnimationView animationView = findViewById(R.id.animation_view);
        animationView.setSpeed(0.5f);






    }

    private void editData() {
        if (getIntent().getBundleExtra("userdata") != null) {
            Bundle bundle = getIntent().getBundleExtra("userdata");
            id = bundle.getInt("id");
            // Access the EditText fields inside the TextInputLayout and set their text
            fname.getEditText().setText(bundle.getString("fname"));
            lname.getEditText().setText(bundle.getString("lname"));
            llocation.getEditText().setText(bundle.getString("llocationn"));
            lponnername.getEditText().setText(bundle.getString("ponnername"));
            lponnerporiman.getEditText().setText(bundle.getString("ponnerporiman"));
            ladvancetk.getEditText().setText(bundle.getString("advancetk"));
            lbakitk.getEditText().setText(bundle.getString("bakitk"));
            edit.setVisibility(View.VISIBLE);
            submit.setVisibility(View.GONE);
            buy.setVisibility(View.GONE);
            sell.setVisibility(View.VISIBLE);
        }
    }

    private void insertData() {
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("lname", lname.getEditText().getText().toString());
                cv.put("llocationn", llocation.getEditText().getText().toString());
                cv.put("ponnername", lponnername.getEditText().getText().toString());
                cv.put("ponnerporiman", lponnerporiman.getEditText().getText().toString());
                cv.put("advancetk", ladvancetk.getEditText().getText().toString());
                cv.put("bakitk", lbakitk.getEditText().getText().toString());

                sqLiteDatabase = dBmain.getWritableDatabase();
                long recinsert = sqLiteDatabase.insert(TABLENAME, null, cv);
                if (recinsert != -1) {
                    Toast.makeText(MainActivity.this, "Successfully inserted data", Toast.LENGTH_SHORT).show();
                    // Clear when clicking on submit
                    clearInputFields();
                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // When clicked on display button, open display data activity
        display.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_display_dataa.class);
                startActivity(intent);
            }
        });

        // Storing edited data
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues cv = new ContentValues();
                cv.put("fname", fname.getEditText().getText().toString());
                cv.put("lname", lname.getEditText().getText().toString());
                cv.put("llocationn", llocation.getEditText().getText().toString());
                cv.put("ponnername", lponnername.getEditText().getText().toString());
                cv.put("ponnerporiman", lponnerporiman.getEditText().getText().toString());
                cv.put("advancetk", ladvancetk.getEditText().getText().toString());
                cv.put("bakitk", lbakitk.getEditText().getText().toString());

                clearInputFields();

                sqLiteDatabase = dBmain.getReadableDatabase();
                long recedit = sqLiteDatabase.update(TABLENAME, cv, "id=" + id, null);
                if (recedit != -1) {
                    Toast.makeText(MainActivity.this, "Data updated successfully", Toast.LENGTH_SHORT).show();
                    submit.setVisibility(View.VISIBLE);
                    edit.setVisibility(View.GONE);
                    buy.setVisibility(View.VISIBLE);
                    sell.setVisibility(View.GONE);

                } else {
                    Toast.makeText(MainActivity.this, "Something went wrong, please try again", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void findid() {
        fname = findViewById(R.id.edit_fname);
        lname = findViewById(R.id.edit_lname);
        llocation = findViewById(R.id.edit_location);
        lponnername = findViewById(R.id.edit_ponnername);
        lponnerporiman = findViewById(R.id.edit_ponnerporiman);
        ladvancetk = findViewById(R.id.edit_advancetk);
        lbakitk = findViewById(R.id.edit_bakitk);
        submit = findViewById(R.id.submit_btn);
        display = findViewById(R.id.display_btn);
        edit = findViewById(R.id.edit_btn);
        buy=findViewById(R.id.buyID);
        sell=findViewById(R.id.sellID);
    }

    // Helper method to clear input fields
    private void clearInputFields() {
        fname.getEditText().setText("");
        lname.getEditText().setText("");
        llocation.getEditText().setText("");
        lponnername.getEditText().setText("");
        lponnerporiman.getEditText().setText("");
        ladvancetk.getEditText().setText("");
        lbakitk.getEditText().setText("");
    }
    @Override
    public void onBackPressed(){
        finish();

    }
}
