package com.jay.country.model.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jay.country.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {

    private List<String> articleList;
    private List<String> imageList;

    private Context context;

    private LayoutInflater layoutInflater;

    public CityAdapter(List<String> articleList, List<String> imageList, Context context) {
        this.articleList = articleList;
        this.imageList = imageList;
        this.context = context;

        layoutInflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = layoutInflater.inflate(R.layout.recycler_view_cities, parent, false);

        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull CityAdapter.ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return articleList.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.article)
        TextView articleTextView;

        @BindView(R.id.article_image)
        ImageView articleImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ButterKnife.bind(this, itemView);
        }
    }
}
