package com.example.applicationvrinsoft.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.applicationvrinsoft.R;
import com.example.applicationvrinsoft.model.ProductMast;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Comparator;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.CustomViewHolder> {
    private Context context;
    private ArrayList<ProductMast> productMastsList = null;
    private int type;
    final int CATEGORY = 0;
    final int NORMAL = 1;

    public ProductItemAdapter(WeakReference<Context> context, ArrayList<ProductMast> productMastsList, int type) {
        this.context = context.get();
        this.productMastsList = productMastsList;
        this.type = type;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View custom_view;

        if (viewType == CATEGORY) {
            //this is use in Gridlayout of result
            custom_view = LayoutInflater.from(context).inflate(R.layout.productby_category, parent, false);

        } else {

            //this is use in listview of result
            custom_view = LayoutInflater.from(context).inflate(R.layout.productitem_view, parent, false);
        }

        return new CustomViewHolder(custom_view, viewType);

    }

    private boolean hasDate(int position){
        if (position == 0)
            return true;
        return !productMastsList.get(position).getCategory().equals(productMastsList.get(position-1).getCategory());
    }

    @Override
    public void onBindViewHolder(CustomViewHolder holder, int position) {
        ProductMast productMast = productMastsList.get(position);

        if (type == CATEGORY) {
            holder.actvCategory.setVisibility(hasDate(position) ? View.VISIBLE : View.GONE);
            holder.actvName.setText("Name :".concat(productMast.getName()).trim());
            holder.actvPrice.setText("Price :".concat(productMast.getPrice()).trim());
            holder.actvCategory.setText("Category :".concat(productMast.getCategory()).trim());
        } else {

            holder.actvName.setText("Name :".concat(productMast.getName()).trim());
            holder.actvPrice.setText("Price :".concat(productMast.getPrice()).trim());
            holder.actvCategory.setText("Category :".concat(productMast.getCategory()).trim());
        }
    }

    @Override
    public int getItemCount() {
        return productMastsList.size();
    }

    public void changeType(int type) {
        this.type = type;
        notifyDataSetChanged();
    }


    @Override
    public int getItemViewType(int position) {
        if (type == CATEGORY) {
            return CATEGORY;
        } else {
            return NORMAL;
        }
    }

    public static class SortbyName implements Comparator<ProductMast> {
        // Used for sorting in ascending order of

        @Override
        public int compare(ProductMast a, ProductMast b) {
            return a.getName().compareTo(b.getName());
        }
    }

    public static class SortbyPrice implements Comparator<ProductMast> {
        // Used for sorting in ascending order of

        @Override
        public int compare(ProductMast a, ProductMast b) {
            return a.getPrice().compareTo(b.getPrice());
        }
    }

    public static class SortbyCategory implements Comparator<ProductMast> {
        // Used for sorting in ascending order of

        @Override
        public int compare(ProductMast a, ProductMast b) {
            return a.getCategory().compareTo(b.getCategory());
        }
    }

    /*Dissending order*/
   /* public static class disSortbyName implements Comparator<ProductMast> {
        // Used for Descending order of
        @Override
        public int compare(ProductMast a, ProductMast b) {
            return b.getName().compareTo(a.getName());
        }
    }*/

   /* public static class disSortbyCategory implements Comparator<ProductMast> {
        // Used for Descending order of
        @Override
        public int compare(ProductMast a, ProductMast b) {
            return b.getCategory().compareTo(a.getCategory());
        }
    }*/

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        private AppCompatTextView actvName, actvPrice, actvCategory;

        public CustomViewHolder(View view, int type) {
            super(view);
            actvName = (AppCompatTextView) view.findViewById(R.id.actv_name);
            actvPrice = (AppCompatTextView) view.findViewById(R.id.actv_price);
            actvCategory = (AppCompatTextView) view.findViewById(R.id.actv_category);
        }
    }

}
