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
        recentsDataList.add(new RecentsData("Sundarbans Mangrove ","Khulna","Available Flight: 5 May",R.drawable.sundarban));
        recentsDataList.add(new RecentsData("Saint Martin’s Island","Cox's bazer","Available Flight: 9 May",R.drawable.saint));
        recentsDataList.add(new RecentsData("Cox’s Bazar","Bangladesh","Available Flight: 5 May",R.drawable.cox));
        recentsDataList.add(new RecentsData("Jaflong","Sylet","Available Flight: 4 May",R.drawable.jl));
        recentsDataList.add(new RecentsData("Bandarban","Bangladesh","Available Flight: 3 May",R.drawable.bandorban));
        recentsDataList.add(new RecentsData("Srimongol","Bangladesh","Available Flight: 1 May",R.drawable.srimongol));
        recentsDataList.add(new RecentsData("Sylet","Bangladesh","Available Flight: 11 May",R.drawable.sylet));
        recentsDataList.add(new RecentsData("Himchori","Bangladesh","Available Flight: 6 May",R.drawable.himchari));
        recentsDataList.add(new RecentsData("Teknaf","Bangladesh","Available Flight: 5 May",R.drawable.teknaf));
        recentsDataList.add(new RecentsData("Rangamati","Bangladesh","Available Flight: 3 May",R.drawable.recentimage1));
        recentsDataList.add(new RecentsData("Tetulia","Bangladesh","Available Flight: 1 May",R.drawable.tetuliya));
        recentsDataList.add(new RecentsData("Panchagor","Bangladesh","Available Flight: 6 May",R.drawable.pancho));
        setRecentRecycler(recentsDataList);

        List<TopPlacesData> topPlacesDataList = new ArrayList<>();
        topPlacesDataList.add(new TopPlacesData("Sajek Valley","Bandarban","Available Flight: 5 May 2022",R.drawable.sajek));
        topPlacesDataList.add(new TopPlacesData("Sonargaon","Dhaka","Available Flight: 9 May 2022",R.drawable.sonarga));
        topPlacesDataList.add(new TopPlacesData("Dhaka","Bangladesh","Available Flight: 4 May 2022",R.drawable.dhaka));
        topPlacesDataList.add(new TopPlacesData("Khulna","Bangladesh","Available Flight: 3 May 2022",R.drawable.himchari));
        topPlacesDataList.add(new TopPlacesData("Barisal","Bangladesh","Available Flight: 4 May 2022",R.drawable.bandorban));
        topPlacesDataList.add(new TopPlacesData("Benapol","Bangladesh","Available Flight: 6 May 2022",R.drawable.benapol));
        topPlacesDataList.add(new TopPlacesData("Kuakata","Barisal","Available Flight: 11 May 2022",R.drawable.cox));
        topPlacesDataList.add(new TopPlacesData("Gopalgonj","Bangladesh","Available Flight: 1 May 2022",R.drawable.gopal));
        topPlacesDataList.add(new TopPlacesData("IT Park","Bangladesh","Available Flight: 2 May 2022",R.drawable.khulna));
        topPlacesDataList.add(new TopPlacesData("LalBag Kella","Bangladesh","Available Flight: 3 May 2022",R.drawable.topplaces));
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