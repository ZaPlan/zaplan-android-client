package com.zaplan;

/**
 * Created by prasang7 on 13/8/16.
 */
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ElementAdapter extends RecyclerView.Adapter<ElementAdapter.MyViewHolder> {

    private List<Element> elementList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, start, end;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            start = (TextView) view.findViewById(R.id.start);
            end = (TextView) view.findViewById(R.id.end);
        }
    }


    public ElementAdapter(List<Element> elementList) {
        this.elementList = elementList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.element_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Element movie = elementList.get(position);
        holder.title.setText(movie.getTitle());
        holder.start.setText(movie.getStart());
        holder.end.setText(movie.getEnd());
    }

    @Override
    public int getItemCount() {
        return elementList.size();
    }
}