package com.fancytank.ognia.muzeumwojska.list;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancytank.ognia.muzeumwojska.DetailsView;
import com.fancytank.ognia.muzeumwojska.MainActivity;
import com.fancytank.ognia.muzeumwojska.R;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;

import java.util.ArrayList;
import java.util.List;

public class DisplayListAdapter extends RecyclerView.Adapter<DisplayUnitViewHolder> {
    public static String TAG = "MY_UNIT_DATA";
    private List<DisplayUnit> data;
    private final Context context;

    public DisplayListAdapter(List<DisplayUnit> data, Context context) {
        this.data = data;
        this.context = context;
    }

    public DisplayListAdapter(Context context) {
        this.data = new ArrayList<>();
        this.context = context;
    }

    @Override
    public DisplayUnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_list_view, parent, false);
        return new DisplayUnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayUnitViewHolder holder, final int position) {
        final DisplayUnit unit = data.get(position);
        holder.title.setText(unit.getTitle());
        if (unit.hasImage()) {
            holder.image.setImageResource(unit.getImage());
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.service.sendRequestForId(data.get(position).getId(), context);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public List<DisplayUnit> getData() {
        return data;
    }
}

class DisplayUnitViewHolder extends RecyclerView.ViewHolder {
    TextView title;
    ImageView image;
    View view;

    public DisplayUnitViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        title = (TextView) itemView.findViewById(R.id.title);
        image = (ImageView) itemView.findViewById(R.id.image);
    }
}