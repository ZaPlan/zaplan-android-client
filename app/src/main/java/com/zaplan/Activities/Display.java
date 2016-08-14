package com.zaplan.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.zaplan.Element;
import com.zaplan.ElementAdapter;
import com.zaplan.R;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by prasang7 on 13/8/16.
 */
public class Display extends Activity {

    private List<Element> elementList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ElementAdapter mAdapter;
    TextView topLabel;
    ImageButton ib_refresh, ib_thumbs, ib_review;

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

        //Toast.makeText(Display.this, startT + "-" + endT + ", " + budgetV + ", " + distanceV, Toast.LENGTH_SHORT).show();

        mAdapter = new ElementAdapter(elementList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        prepareElementData();

        callAPI(startT, endT, budgetV, distanceV);


        ib_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Display.this, "refresh", Toast.LENGTH_SHORT).show();
                prepareElementData();
            }
        });

        ib_thumbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Display.this, "thims up", Toast.LENGTH_SHORT).show();
            }
        });

        ib_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Display.this, "review", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void callAPI(int _start, int _end, int _budget, int _distance) {

        // TODO : CALL API FROM HERE!



    }

    private void prepareElementData() {

        int randomNum;
        randomNum = (int)(Math.random() * 3);

        if (randomNum == 0) {
            Element element = new Element("Museum - Sardar Patel Museum", "9:00", "11:00");
            elementList.add(element);

            element = new Element("Book store - M3D Infotech", "12:00", "13:00");
            elementList.add(element);

            element = new Element("Lunch - Hell's Kitchen", "13:00", "15:00");
            elementList.add(element);

            element = new Element("Bowling Alley - ROCK & BOWL", "15:00", "16:00");
            elementList.add(element);

            element = new Element("Night Club - Knock-Out Resto Lounge", "20:00", "23:00");
            elementList.add(element);
        }
        else if (randomNum == 1){
            Element element = new Element("Movie - Suicide Squad 3D", "9:00", "12:00");
            elementList.add(element);

            element = new Element("Nanwadi Chicken Centre", "12:00", "13:00");
            elementList.add(element);

            element = new Element("Cafe Coffee Day - Inside Golden Square", "15:00", "16:00");
            elementList.add(element);

            element = new Element("Cad M Cad B", "18:00", "19:00");
            elementList.add(element);

            element = new Element("Dinner - KM Restaurant", "20:00", "22:00");
            elementList.add(element);
        }
        else if (randomNum == 2){
            Element element = new Element("Trekking - TMC Trek Clubs", "12:00", "16:00");
            elementList.add(element);

            element = new Element("Movie - Mad Max - Fury Road", "16:00", "19:00");
            elementList.add(element);

            element = new Element("Rajputra Restaurant", "20:00", "22:00");
            elementList.add(element);

            element = new Element("Long Drive", "22:00", "23:00");
            elementList.add(element);

        }


        /*
        TreeMap<String, ArrayList<Element>> dayOne = new TreeMap<>();

        String currentVal = "0900 - 1100 - Museum";
        dayOne.put((currentVal), new ArrayList<Element>());

        dayOne.get(currentVal).add(new Element("Sardar Patel Museum"));
        dayOne.get(currentVal).add(new Element("SaGruhshobha Handicraft"));
        dayOne.get(currentVal).add(new Element("Nau Educational Museum"));
        dayOne.get(currentVal).add(new Element("Sardar Vallabhbhai Patel Museum"));

        currentVal = "1200 - 1300 - BookStore";
        dayOne.put((currentVal), new ArrayList<Element>());

        dayOne.get(currentVal).add(new Element("Book World"));
        dayOne.get(currentVal).add(new Element("Medirite Corporation"));
        dayOne.get(currentVal).add(new Element("M3D Infotech"));
        dayOne.get(currentVal).add(new Element("Crossword"));

        currentVal = "1300 - 1500 - Lunch";
        dayOne.get(currentVal).add(new Element("Hell's Kitchen"));
        dayOne.get(currentVal).add(new Element("Nanwadi Chicken Centre"));
        dayOne.get(currentVal).add(new Element("Red Tandoor"));
        dayOne.get(currentVal).add(new Element("Shiva's Coffee Bar & Snacks"));

        currentVal = "1500 - 1600 - Bowling";
        dayOne.get(currentVal).add(new Element("ROCK & BOWL"));

        currentVal = "1730 - 1900 - Nightclub";
        dayOne.get(currentVal).add(new Element("Knock-Out Resto Lounge"));
        dayOne.get(currentVal).add(new Element("Online Ticket Services"));
        dayOne.get(currentVal).add(new Element("Coffee Culture The Ristorante Lounge"));
        */



        Element element = new Element("Trekking - TMC Trek Clubs", "12:00", "16:00");
        elementList.add(element);

        element = new Element("Movie - Mad Max - Fury Road", "16:00", "19:00");
        elementList.add(element);

        element = new Element("Rajputra Restaurant", "20:00", "22:00");
        elementList.add(element);

        element = new Element("Long Drive", "22:00", "23:00");
        elementList.add(element);

        element = new Element("Trekking - TMC Trek Clubs", "12:00", "16:00");
        elementList.add(element);


        mAdapter.notifyDataSetChanged();
    }

    void init() {
        setContentView(R.layout.activity_display);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        topLabel = (TextView) findViewById(R.id.tv_display_topLabel);

        ib_refresh = (ImageButton) findViewById(R.id.ib_display_refresh);
        ib_thumbs = (ImageButton) findViewById(R.id.ib_display_thumb);
        ib_review = (ImageButton) findViewById(R.id.ib_display_review);

        Typeface MontReg = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Regular.otf");
        Typeface MontBold = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Bold.otf");
        Typeface MontHair = Typeface.createFromAsset(getApplication().getAssets(), "Montserrat-Hairline.otf");

        topLabel.setTypeface(MontBold);

        setStatusBarTranslucent(true);
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
