package com.example.applicationvrinsoft.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.applicationvrinsoft.R;
import com.example.applicationvrinsoft.adapter.ProductItemAdapter;
import com.example.applicationvrinsoft.model.ProductMast;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Activity activity = null;
    private static final String TAG = "MainActivity";
    private Toolbar toolbar;
    private AppCompatEditText edittextName, edittextPrice;
    private AppCompatTextView textviewSave;
    private AppCompatSpinner acSpinnerCategory;
    private String categoryitem;
    private ArrayList<ProductMast> productMastsList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = MainActivity.this;
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final Drawable upArrow = getResources().getDrawable(R.drawable.ic_back);
        upArrow.setColorFilter(getResources().getColor(R.color.white), PorterDuff.Mode.SRC_ATOP);
        getSupportActionBar().setHomeAsUpIndicator(upArrow);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        AppCompatTextView txtHeaderNname = (AppCompatTextView) toolbar.findViewById(R.id.actv_header_name);
        txtHeaderNname.setText(getString(R.string.mayurpractical));

        productMastsList = new ArrayList<>();
        edittextName = (AppCompatEditText) findViewById(R.id.edittextName);
        edittextPrice = (AppCompatEditText) findViewById(R.id.edittextPrice);
        textviewSave = (AppCompatTextView) findViewById(R.id.textviewSave);
        textviewSave.setOnClickListener(this);
        String[] SPINNER_DATA = {"Select Category", "Colthes", "Electronics", "Beauty"};
        acSpinnerCategory = (AppCompatSpinner) findViewById(R.id.acspinerCategory);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(activity, android.R.layout.simple_dropdown_item_1line, SPINNER_DATA);

        acSpinnerCategory.setAdapter(adapter);
        acSpinnerCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (!parent.getItemAtPosition(position).equals("Select Category")) {
                    categoryitem = parent.getItemAtPosition(position).toString();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {

        if (!edittextName.getText().toString().isEmpty() && !edittextPrice.getText().toString().isEmpty() && categoryitem != ("")) {
            ProductMast productMast = new ProductMast(edittextName.getText().toString().trim(), edittextPrice.getText().toString().trim(), categoryitem);
            productMastsList.add(productMast);
            Intent intent = new Intent(activity, Main2Activity.class);
            Bundle args = new Bundle();
            args.putSerializable("ARRAYLIST", productMastsList);
            intent.putExtra("BUNDLE", args);
            startActivity(intent);
        }
    }
}
