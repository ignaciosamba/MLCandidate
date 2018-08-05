package com.example.ignac.mlcandidate.view.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ignac.mlcandidate.R;
import com.example.ignac.mlcandidate.model.Result;
import com.example.ignac.mlcandidate.model.Results;
import com.example.ignac.mlcandidate.utils.NetworkUtils;

import java.util.List;

import static java.lang.String.valueOf;

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ProductViewItemHolder>{

    private List<Result> mResultList;
    private ProductAdapterClickListener mProductAdapterClickListener;

    public ProductItemAdapter (Results results,
                               ProductAdapterClickListener productAdapterClickListener) {
        mResultList = results.getResultList();
        mProductAdapterClickListener = productAdapterClickListener;
    }

    public void setResultList(Results results) {
        System.out.println("SAMBA SETRESULTLIST");
        mResultList = results.getResultList();
        System.out.println("SAMBA SETRESULTLIST AFTER EL GETRESULTLIST");

        notifyDataSetChanged();
    }

    @Override
    public ProductViewItemHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.product_carview, parent, false);
        return new ProductViewItemHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewItemHolder holder, final int position) {
        System.out.println("SAMBA ONBINDVIEWHOLDER");
        holder.mProductTitle.setText(mResultList.get(position).getTitle());
        String price = "$ " + valueOf(mResultList.get(position).getPrice());
        holder.mProductPrice.setText(price);
        NetworkUtils.LoadImage(mResultList.get(position).getThumbnail(), holder.mProductThumbnail);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProductAdapterClickListener.onItemClick(mResultList.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mResultList != null) {
           return mResultList.size();
        } else {
            return 0;
        }
    }


    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ProductViewItemHolder extends RecyclerView.ViewHolder {

        private TextView mProductTitle;
        private TextView mProductPrice;
        private ImageView mProductThumbnail;

        public ProductViewItemHolder(View itemView) {
            super(itemView);
            mProductTitle = itemView.findViewById(R.id.item_title);
            mProductPrice = itemView.findViewById(R.id.item_price);
            mProductThumbnail = itemView.findViewById(R.id.item_img);
        }
    }
}
