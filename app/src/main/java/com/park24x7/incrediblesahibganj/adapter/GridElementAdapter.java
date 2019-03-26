package com.park24x7.incrediblesahibganj.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.park24x7.incrediblesahibganj.R;

import java.util.ArrayList;
import java.util.List;

public class GridElementAdapter extends RecyclerView.Adapter<GridElementAdapter.SimpleViewHolder> {

    private Context context;
    private List<String> elements;

    public GridElementAdapter(Context context) {
        this.context = context;
        this.elements = new ArrayList<>();
        // Fill dummy list
        for (int i = 0; i < 40; i++) {
            this.elements.add(i, "Position : " + i);
        }
    }

    public static class SimpleViewHolder extends RecyclerView.ViewHolder {
        public final Button button;

        public SimpleViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.button);
        }
    }

    @Override
    public SimpleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(this.context).inflate(R.layout.grid_element, parent, false);
        return new SimpleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SimpleViewHolder holder, final int position) {
        holder.button.setText(elements.get(position));
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Position =" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return this.elements.size();
    }
}
