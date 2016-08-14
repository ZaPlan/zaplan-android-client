package com.zaplan.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zaplan.Element;
import com.zaplan.ElementAdapter;
import com.zaplan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Display extends Activity {

    private List<Element> elementList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter mAdapter;
    TextView topLabel;

    int startT, endT, budgetV, distanceV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        init();

        Bundle extras = this.getIntent().getExtras();

        startT = extras.getInt("startT");
        endT = extras.getInt("endT");
        budgetV = extras.getInt("budgetV");
        distanceV = extras.getInt("distanceV");

        Toast.makeText(Display.this, startT + "-" + endT + ", " + budgetV + ", " + distanceV, Toast.LENGTH_SHORT).show();



        mAdapter = new ElementAdapter(elementList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareElementData();

    }

    private void prepareElementData() {
        Element element = new Element("Trekking - TMC Trek Clubs\n\n\n", "12:00", "16:00");
        elementList.add(element);

        element = new Element("Movie - Mad Max - Fury Road\n\n", "16:00", "19:00");
        elementList.add(element);

        element = new Element("Rajputra Restaurant\n", "20:00", "22:00");
        elementList.add(element);

        element = new Element("Long Drive", "22:00", "23:00");
        elementList.add(element);

        element = new Element("Trekking - TMC Trek Clubs\n\n\n", "12:00", "16:00");
        elementList.add(element);

        mAdapter.notifyDataSetChanged();
    }

    void init() {
        setContentView(R.layout.activity_display);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        topLabel = (TextView) findViewById(R.id.tv_display_topLabel);

        Typeface MontReg = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Regular.otf");
        Typeface MontBold = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Bold.otf");
        Typeface MontHair = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Hairline.otf");

        topLabel.setTypeface(MontBold);
    }
}
