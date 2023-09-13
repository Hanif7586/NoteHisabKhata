package com.example.curdsql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static com.example.curdsql.DBmain.TABLENAME;


public class activity_display_dataa extends AppCompatActivity {
    DBmain dBmain;
    SQLiteDatabase sqLiteDatabase;
    RecyclerView recyclerView;
    MyAdapter adapter;
    SearchView searchView;

    private ArrayList<Model> courseModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_dataa);
        dBmain=new DBmain(this);
        //create method
        findid();
        displayData();

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        searchView=findViewById(R.id.searchView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                // inside on query text change method we are
                // calling a method to filter our recycler view.
                filter(newText);
                return true;
            }
        });

    }

    private void filter(String text) {
        // creating a new array list to filter our data.
        ArrayList<Model> filteredlist = new ArrayList<Model>();

        // running a for loop to compare elements.
        for (Model item : courseModelArrayList) {
            // checking if the entered string matched with any item of our recycler view.
            if (item.getFirstname().toLowerCase().contains(text.toLowerCase())) {
                // if the item is matched we are
                // adding it to our filtered list.
                filteredlist.add(item);
            }
        }
        if (filteredlist.isEmpty()) {
            // if no item is added in filtered list we are
            // displaying a toast message as no data found.
            Toast.makeText(this, "No Data Found..", Toast.LENGTH_SHORT).show();
        } else {
            // at last we are passing that filtered
            // list to our adapter class.
            adapter.filterList(filteredlist);
        }
    }


    private void displayData() {
        sqLiteDatabase=dBmain.getReadableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("select *from "+TABLENAME+"",null);
        courseModelArrayList = new ArrayList<Model>();
        while (cursor.moveToNext()){
            int id=cursor.getInt(0);
            String fnamee=cursor.getString(1);
            String lnumber=cursor.getString(2);
            String lnlocation=cursor.getString(3);
            String lnponnername=cursor.getString(4);
            String lnponnerporiman=cursor.getString(5);
            String ladvancetk=cursor.getString(6);
            String lbakitk=cursor.getString(7);
            courseModelArrayList.add(new Model(id, fnamee, lnumber,lnlocation,lnponnername,lnponnerporiman,ladvancetk,lbakitk));
        }
        cursor.close();
        TextView txtTotalAmount = findViewById(R.id.hanif);
        TextView txtTotalALLAmount = findViewById(R.id.rohan);
        // Make sure to replace R.id.txtTotalAmount with the actual ID of your TextView

// Create the MyAdapter instance with all required arguments
        adapter = new MyAdapter(courseModelArrayList, activity_display_dataa.this, sqLiteDatabase, txtTotalAmount,txtTotalALLAmount);

        recyclerView.setAdapter(adapter);
    }

    private void findid() {
        recyclerView=findViewById(R.id.rv);


    }
}