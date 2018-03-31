package hu.pe.yummykart.surakshauser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by PRanshu on 29-10-2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<RecyclerItem> listItems;
    private Context mContext;

    public MyAdapter(List<RecyclerItem> listItems, Context mContext) {
        this.listItems = listItems;
        this.mContext = mContext;
    }

    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyAdapter.MyViewHolder holder, int position) {

        RecyclerItem item = listItems.get(position);
        holder.crime.setText(item.getsCrime());
        holder.s2010.setText(item.getS2010());
        holder.s2011.setText(item.getS2011());
        holder.s2012.setText(item.getS2012());
    }
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView crime;
        TextView s2010;
        TextView s2011;
        TextView s2012;
        public MyViewHolder(View itemView) {
            super(itemView);
            crime = (TextView) itemView.findViewById(R.id.textCrimeType);
            s2010 = (TextView) itemView.findViewById(R.id.text2010);
            s2011 = (TextView) itemView.findViewById(R.id.text2011);
            s2012 = (TextView) itemView.findViewById(R.id.text2012);
        }
    }

}
