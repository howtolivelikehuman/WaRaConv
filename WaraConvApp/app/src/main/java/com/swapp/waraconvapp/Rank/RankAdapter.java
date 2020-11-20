package com.swapp.waraconvapp.Rank;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swapp.waraconvapp.DB.DetailInfo;
import com.swapp.waraconvapp.R;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    ArrayList<DetailInfo> rankItems=new ArrayList<DetailInfo>();
    //private OnItemClickListener mListerner=null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvRank, tvName, tvParent, tvTotalScore, tvProfitScore, tvStableScore;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            tvRank=itemView.findViewById(R.id.textViewRank);
            tvName=itemView.findViewById(R.id.textViewName);
            tvParent=itemView.findViewById(R.id.textViewParent);
            tvTotalScore=itemView.findViewById(R.id.textView_totalscore);
            tvProfitScore=itemView.findViewById(R.id.textView_profitscore);
            tvStableScore=itemView.findViewById(R.id.textView_stablescore);

            //아이템 클릭 리스너
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        DetailInfo item = rankItems.get(pos);
                        Intent intent = new Intent(itemView.getContext(), InfoDetailActivity.class);
                        intent.putExtra("detailinfo", item);
                        view.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void setItem(DetailInfo item){
            tvRank.setText(Integer.toString(item.getRanknum())+"위");
            tvName.setText(item.getName());
            tvParent.setText(item.getParentName());
            tvTotalScore.setText("총 : "+Float.toString(item.getTotalscore())+"점");
            tvProfitScore.setText("수익성 : "+Float.toString(item.getProfitscore())+"점");
            tvStableScore.setText("안정성 : "+Float.toString(item.getStablescore())+"점");
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.item_rank,parent,false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetailInfo item=rankItems.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return rankItems.size();
    }

    public void addItem(DetailInfo item){
        rankItems.add(item);
    }

    public void setItems(ArrayList<DetailInfo> rankItems){
        this.rankItems=rankItems;
    }

    public DetailInfo getItem(int position){
        return rankItems.get(position);
    }

    public void setItem(int position, DetailInfo item){
        this.rankItems.set(position,item);
    }
}