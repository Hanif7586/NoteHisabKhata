package com.example.recyclerviewcrud;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import static com.example.recyclerviewcrud.DBmain.TABLENAME;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ModelViewHolder> {
    private ArrayList<Model> courseModelArrayList;
    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    private TextView txtTotalAmount;
    private TextView txtTotalALLAmount;

    public MyAdapter(ArrayList<Model> courseModelArrayList, Context context, SQLiteDatabase sqLiteDatabase, TextView txtTotalAmount, TextView txtTotalALLAmount) {
        this.courseModelArrayList = courseModelArrayList;
        this.context = context;
        this.sqLiteDatabase = sqLiteDatabase;
        this.txtTotalAmount = txtTotalAmount;
        this.txtTotalALLAmount = txtTotalALLAmount;
    }

    public void filterList(ArrayList<Model> filterlist) {
        courseModelArrayList = filterlist;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyAdapter.ModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.singledata, null);
        return new ModelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ModelViewHolder holder, int position) {
        final Model model = courseModelArrayList.get(position);

        holder.txtfname.setText(model.getFirstname());
        holder.txtlname.setText(model.getLastname());
        holder.txtlocationh.setText(model.getLocationh());
        holder.txtponnername.setText(model.getPonnername());
        holder.txtponnerporiman.setText(model.getPonnerporiman());
        holder.txtadvancetk.setText(model.getAdvancetkg());
        holder.txtbakitk.setText(model.getBakitkg());

        holder.maincard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DeatilsActivity.class);

                intent.putExtra("Title", courseModelArrayList.get(holder.getAdapterPosition()).getFirstname());
                intent.putExtra("number", courseModelArrayList.get(holder.getAdapterPosition()).getLastname());
                intent.putExtra("location", courseModelArrayList.get(holder.getAdapterPosition()).getLocationh());
                intent.putExtra("ponnerN", courseModelArrayList.get(holder.getAdapterPosition()).getPonnername());
                intent.putExtra("ponnerPorima", courseModelArrayList.get(holder.getAdapterPosition()).getPonnerporiman());
                intent.putExtra("advantaka", courseModelArrayList.get(holder.getAdapterPosition()).getAdvancetkg());
                intent.putExtra("baktaka", courseModelArrayList.get(holder.getAdapterPosition()).getBakitkg());

                context.startActivity(intent);
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt("id", model.getId());
                bundle.putString("fname", model.getFirstname());
                bundle.putString("lname", model.getLastname());
                bundle.putString("llocationn", model.getLocationh());
                bundle.putString("ponnername", model.getPonnername());
                bundle.putString("ponnerporiman", model.getPonnerporiman());
                bundle.putString("advancetk", model.getAdvancetkg());
                bundle.putString("bakitk", model.getBakitkg());
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra("userdata", bundle);
                context.startActivity(intent);
            }
        });

        holder.delete.setOnClickListener(new View.OnClickListener() {
            DBmain dBmain = new DBmain(context);

            @Override
            public void onClick(View v) {
                sqLiteDatabase = dBmain.getReadableDatabase();
                long delete = sqLiteDatabase.delete(TABLENAME, "id=" + model.getId(), null);
                if (delete != -1) {
                    Toast.makeText(context, "deleted data successfully", Toast.LENGTH_SHORT).show();
                    courseModelArrayList.remove(position);
                    notifyDataSetChanged();
                    updateTotalAmount();
                    updateAllamoun();
                    // Update total amount after deletion
                }
            }
        });

        updateTotalAmount();
        updateAllamoun();

    }



    @Override
    public int getItemCount() {
        return courseModelArrayList.size();
    }

    public class ModelViewHolder extends RecyclerView.ViewHolder {
        TextView txtfname, txtlname, txtlocationh, txtponnername, txtponnerporiman, txtadvancetk, txtbakitk;
        ImageView edit, delete;
        CardView maincard;

        public ModelViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfname = itemView.findViewById(R.id.txtfname);
            txtlname = itemView.findViewById(R.id.txtlname);
            txtlocationh = itemView.findViewById(R.id.txtlloca);
            txtponnername = itemView.findViewById(R.id.txtponnername);
            txtponnerporiman = itemView.findViewById(R.id.txtponnerporiman);
            txtadvancetk = itemView.findViewById(R.id.txtadvancetkk);
            txtbakitk = itemView.findViewById(R.id.txtbakitkk);
            edit = itemView.findViewById(R.id.txt_btn_edit);
            delete = itemView.findViewById(R.id.txt_btn_delete);
            maincard = itemView.findViewById(R.id.cardviewdatils);
        }
    }

    // Calculate and update the total amount
    private void updateTotalAmount() {
        double totalAmount = 0.0;
        for (Model model : courseModelArrayList) {
            totalAmount += Double.parseDouble(model.getBakitkg());
        }
        txtTotalAmount.setText("বাকির পরিমাণ: " + totalAmount);


    }

    private void updateAllamoun() {
        double totalAmountt = 0.0;
        for (Model model : courseModelArrayList) {
            totalAmountt += Double.parseDouble(model.getAdvancetkg());
        }
        txtTotalALLAmount.setText("মোট আয়: " + totalAmountt);


    } }
