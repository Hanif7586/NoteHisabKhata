package com.example.recyclerviewcrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class DeatilsActivity extends AppCompatActivity {
    TextView  aa,bb,cc,dd,ee,ff,gg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deatils);

        LottieAnimationView animationVieww = findViewById(R.id.animationlast);
        animationVieww.setSpeed(0.5f);

        aa=findViewById(R.id.a);
        bb=findViewById(R.id.b);
        cc=findViewById(R.id.c);
        dd=findViewById(R.id.d);
        ee=findViewById(R.id.e);
        ff=findViewById(R.id.f);
        gg=findViewById(R.id.g);

        Intent intent=getIntent();
        aa.setText(intent.getStringExtra("Title"));
        bb.setText(intent.getStringExtra("number"));
        cc.setText(intent.getStringExtra("location"));

        dd.setText(intent.getStringExtra("ponnerN"));
        ee.setText(intent.getStringExtra("ponnerPorima"));
        ff.setText(intent.getStringExtra("advantaka"));
        gg.setText(intent.getStringExtra("baktaka"));





    }
}