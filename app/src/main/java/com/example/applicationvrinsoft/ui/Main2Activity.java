package com.example.applicationvrinsoft.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.example.applicationvrinsoft.R;
import com.example.applicationvrinsoft.adapter.ProductItemAdapter;
import com.example.applicationvrinsoft.model.ProductMast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {
    private LinearLayoutManager layoutManager = null;
    private RecyclerView rvItemList = null;
    private Activity activity = null;
    private ProductItemAdapter productItemAdapter = null;
    private ArrayList<ProductMast> productMastsList = null;
    private Toolbar toolbar;
    private boolean name, price, category;
    final int CATEGORY = 0;
    final int NORMAL = 1;
    int type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        activity = Main2Activity.this;
        rvItemList = (RecyclerView) findViewById(R.id.recyclerview_item);

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
                startActivity(new Intent(activity, MainActivity.class));
                overridePendingTransition(R.anim.left_in, R.anim.right_out);
            }
        });
        type = NORMAL;
        AppCompatTextView txtHeaderNname = (AppCompatTextView) toolbar.findViewById(R.id.actv_header_name);
        txtHeaderNname.setText(getString(R.string.productlist));


        layoutManager = new LinearLayoutManager(activity, layoutManager.VERTICAL, false);
        rvItemList.setLayoutManager(layoutManager);
        setRecyclerViewData(NORMAL);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("BUNDLE");
        productMastsList = (ArrayList<ProductMast>) args.getSerializable("ARRAYLIST");
        setRecyclerViewData(NORMAL);


        Map<String, ArrayList<ProductMast>> productmastByCategory = new HashMap<>();

        for (ProductMast p : productMastsList) {
            if (!productmastByCategory.containsKey(p.getCategory())) {
                productmastByCategory.put(p.getCategory(), new ArrayList<>());
            }
            productmastByCategory.get(p.getCategory()).add(p);
        }

        System.out.println("Person grouped by cities : " + productmastByCategory);

        /*Java * or Latter*/
        /* productmastByCategory =  productMastsList.stream()
                .collect(Collectors.groupingBy(ProductMast::getCategory));*/
    }

    private void setRecyclerViewData(int TYPE) {
        productItemAdapter = new ProductItemAdapter(new WeakReference<Context>(activity), productMastsList, TYPE);
        rvItemList.setAdapter(productItemAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.shortmenu:
                return true;
            case R.id.name:

                productItemAdapter.changeType(NORMAL);
                if (!name) {
                    Collections.sort(productMastsList, new ProductItemAdapter.SortbyName());
                    productItemAdapter.notifyDataSetChanged();
                    name = true;

                } else {
                   /* Collections.sort(productMastsList, new ProductItemAdapter.disSortbyName());
                    productItemAdapter.notifyDataSetChanged();*/
                    name = false;
                }

                return true;
            case R.id.prices:
                productItemAdapter.changeType(NORMAL);
                if (!price) {
                    Collections.sort(productMastsList, new ProductItemAdapter.SortbyPrice());
                    productItemAdapter.notifyDataSetChanged();
                    price = true;

                } else {

                    price = false;
                }
                return true;
            case R.id.category:
                productItemAdapter.changeType(CATEGORY);
                if (!category) {
                    Collections.sort(productMastsList, new ProductItemAdapter.SortbyCategory());
                    productItemAdapter.notifyDataSetChanged();
                    category = true;

                } else {
                   /* Collections.sort(productMastsList, new ProductItemAdapter.disSortbyCategory());
                    productItemAdapter.notifyDataSetChanged();*/
                    category = false;
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
