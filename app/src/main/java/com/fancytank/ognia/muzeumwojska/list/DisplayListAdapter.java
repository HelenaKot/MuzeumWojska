package com.fancytank.ognia.muzeumwojska.list;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fancytank.ognia.muzeumwojska.R;
import com.fancytank.ognia.muzeumwojska.api.model.DisplayUnit;

import java.util.List;

public class DisplayListAdapter extends RecyclerView.Adapter<DisplayUnitViewHolder> {
    private List<DisplayUnit> data;
    private final Context context;

    public DisplayListAdapter(List<DisplayUnit> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public DisplayUnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.display_list_view, parent, false);
        return new DisplayUnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DisplayUnitViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

}

class DisplayUnitViewHolder extends RecyclerView.ViewHolder {
    TextView title;

    public DisplayUnitViewHolder(View itemView) {
        super(itemView);
        title = (TextView) itemView.findViewById(R.id.title);
    }
}