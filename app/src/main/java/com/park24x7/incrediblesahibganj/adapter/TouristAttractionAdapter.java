package com.park24x7.incrediblesahibganj.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.park24x7.incrediblesahibganj.R;
import com.park24x7.incrediblesahibganj.data.Dummy;
import com.park24x7.incrediblesahibganj.data.TouristAttactionData;
import com.park24x7.incrediblesahibganj.model.TouristAttraction;
import com.park24x7.incrediblesahibganj.util.Util;

import java.util.ArrayList;

public class TouristAttractionAdapter extends BaseAdapter {

    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<TouristAttraction> touristAttractions = new ArrayList<>();

    public TouristAttractionAdapter(Context mContext, ArrayList<TouristAttraction> touristAttractions) {
        this.mContext = mContext;
        mInflater = LayoutInflater.from(mContext);
        //touristAttractions = Dummy.getDummyTouristAttractions();
        this.touristAttractions = touristAttractions;
    }

    @Override
    public int getCount() {
        return touristAttractions.size();
    }

    @Override
    public Object getItem(int position) {
        return touristAttractions.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View hView = convertView;
        if (convertView == null) {
            hView = mInflater.inflate(R.layout.item_grid_places, null);
            ViewHolder holder = new ViewHolder();
            holder.tv_tourist_attraction = (TextView) hView.findViewById(R.id.tv_tourist_attraction);
            holder.iv_featureImage = (ImageView) hView.findViewById(R.id.iv_featureImage);
            hView.setTag(holder);
        }

        ViewHolder holder = (ViewHolder) hView.getTag();
        holder.tv_tourist_attraction.setText(touristAttractions.get(position).name);
        if (touristAttractions.get(position).images.size() > 0)

            holder.iv_featureImage.setImageBitmap(Util.getBitmapBase64FromString(touristAttractions.get(position).images.get(0).getBase64value()));
        Log.e("Size", "Size: " + touristAttractions.get(position).images.size());


        return hView;
    }


    class ViewHolder {
        TextView tv_tourist_attraction;
        ImageView iv_featureImage;

    }
}
