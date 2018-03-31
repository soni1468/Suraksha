package hu.pe.yummykart.surakshauser;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class MyAdapterVar extends RecyclerView.Adapter<MyAdapterVar.MyViewHolderVar> {
    private List<RecyclerItemVariation> listItem;
    private Context mContext;

    public MyAdapterVar(List<RecyclerItemVariation> listItems, Context mContext) {
        this.listItem = listItems;
        this.mContext = mContext;
    }

    @Override
    public MyAdapterVar.MyViewHolderVar onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclervariation, parent, false);
        return new MyViewHolderVar(v);
    }

    @Override
    public void onBindViewHolder(final MyAdapterVar.MyViewHolderVar holder, int position) {

        RecyclerItemVariation item = listItem.get(position);
        holder.crimeV.setText(item.getsCrime());
        holder.s2014num.setText(item.gets2014num());
        holder.s2015num.setText(item.gets2015num());
        holder.sVar.setText("% Variation in 2015 over 2014    :  "+item.getsVariation()+"%");
        holder.sArrest.setText("Number of Arestees in 2015    :  "+(item.getsArrestees())+" ");
    }

    @Override
    public int getItemCount() {
        return listItem.size();
    }

    public class MyViewHolderVar extends RecyclerView.ViewHolder {
        TextView crimeV;
        TextView s2014num;
        TextView s2015num;
        TextView sVar;
        TextView sArrest;

        public MyViewHolderVar(View itemView) {
            super(itemView);
            crimeV = (TextView) itemView.findViewById(R.id.textCrimeT);
            s2014num = (TextView) itemView.findViewById(R.id.text2014num);
            s2015num = (TextView) itemView.findViewById(R.id.text2015num);
            sVar = (TextView) itemView.findViewById(R.id.textVariation);
            sArrest = (TextView) itemView.findViewById(R.id.textArrestees);
        }
    }
}
