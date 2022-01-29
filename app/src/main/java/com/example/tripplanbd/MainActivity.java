package com.example.tripplanbd;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripplanbd.adapter.RecentsAdapter;
import com.example.tripplanbd.adapter.TopPlacesAdapter;
import com.example.tripplanbd.model.RecentsData;
import com.example.tripplanbd.model.TopPlacesData;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recentRecycler, topPlacesRecycler;
    RecentsAdapter recentsAdapter;
    TopPlacesAdapter topPlacesAdapter;

    private FirebaseAuth auth;
    ImageView imgLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();
        imgLogout = findViewById(R.id.logout);
        imgLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent = new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
            }
        });

        // Now here we will add some dummy data

        List<RecentsData> recentsDataList = new ArrayList<>();
        recentsDataList.add(new RecentsData("Sundarbans Mangrove ","Bangladesh","Ticket: $200",R.drawable.sundarban));
        recentsDataList.add(new RecentsData("Saint Martin’s Island","Bangladesh","Ticket: $300",R.drawable.saint));
        recentsDataList.add(new RecentsData("Cox’s Bazar","Bangladesh","Ticket: $200",R.drawable.cox));
        recentsDataList.add(new RecentsData("Jaflong","Bangladesh","Ticket: $300",R.drawable.jl));
        recentsDataList.add(new RecentsData("Bandarban","Bangladesh","Ticket: $200",R.drawable.bandorban));
        recentsDataList.add(new RecentsData("Srimongol","Bangladesh","Ticket: $300",R.drawable.srimongol));
        recentsDataList.add(new RecentsData("Sylet","Bangladesh","Ticket: $200",R.drawable.sylet));
        recentsDataList.add(new RecentsData("Himchori","Bangladesh","Ticket: $300",R.drawable.himchari));
        recentsDataList.add(new RecentsData("Teknaf","Bangladesh","Ticket: $200",R.drawable.teknaf));
        recentsDataList.add(new RecentsData("Rangamati","Bangladesh","Ticket: $300",R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Tetulia","Bangladesh","Ticket: $200",R.drawable.tetuliya));
        recentsDataList.add(new RecentsData("Panchagor","Bangladesh","Ticket: $300",R.drawable.pancho));
        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Sajek Valley","Bangladesh","Ticket: $200 - $500",R.drawable.sajek));
        topPlacesDataList.add(new TopPlacesData("Kuakata","Bangladesh","Ticket: $200 - $500",R.drawable.cox));
        topPlacesDataList.add(new TopPlacesData("Sonargaon","Bangladesh","Ticket: $200 - $500",R.drawable.sonarga));
        topPlacesDataList.add(new TopPlacesData("Dhaka","Bangladesh","Ticket: $200 - $500",R.drawable.dhaka));
        topPlacesDataList.add(new TopPlacesData("Khulna","Bangladesh","Ticket: $200 - $500",R.drawable.himchari));
        topPlacesDataList.add(new TopPlacesData("Barisal","Bangladesh","Ticket: $200 - $500",R.drawable.bandorban));
        topPlacesDataList.add(new TopPlacesData("Benapol","Bangladesh","Ticket: $200 - $500",R.drawable.benapol));
        topPlacesDataList.add(new TopPlacesData("Gopalgonj","Bangladesh","Ticket: $200 - $500",R.drawable.gopal));
        topPlacesDataList.add(new TopPlacesData("IT Park","Bangladesh","Ticket: $200 - $500",R.drawable.khulna));
        topPlacesDataList.add(new TopPlacesData("LalBag Kella","Bangladesh","Ticket: $200 - $500",R.drawable.topplaces));
        setTopPlacesRecycler(topPlacesDataList);
    }

    private  void setRecentRecycler(List<RecentsData> recentsDataList){

        recentRecycler = findViewById(R.id.recent_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        recentRecycler.setLayoutManager(layoutManager);
        recentsAdapter = new RecentsAdapter(this, recentsDataList);
        recentRecycler.setAdapter(recentsAdapter);

    }

    private  void setTopPlacesRecycler(List<TopPlacesData> topPlacesDataList){

        topPlacesRecycler = findViewById(R.id.top_places_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        topPlacesRecycler.setLayoutManager(layoutManager);
        topPlacesAdapter = new TopPlacesAdapter(this, topPlacesDataList);
        topPlacesRecycler.setAdapter(topPlacesAdapter);

    }
}