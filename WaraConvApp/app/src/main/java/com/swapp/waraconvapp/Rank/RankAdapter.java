package com.swapp.waraconvapp.Rank;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swapp.waraconvapp.R;

import java.util.ArrayList;

public class RankAdapter extends RecyclerView.Adapter<RankAdapter.ViewHolder> {
    ArrayList<Rank> rankItems=new ArrayList<Rank>();
    //private OnItemClickListener mListerner=null;

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewRank, textViewDongName, textViewScore;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);

            textViewRank=itemView.findViewById(R.id.textViewRank);
            textViewDongName=itemView.findViewById(R.id.textViewDongName);
            textViewScore=itemView.findViewById(R.id.textViewScore);

            //아이템 클릭 리스너
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();
                    if(pos!=RecyclerView.NO_POSITION){
                        Rank item = rankItems.get(pos);
                        Intent intent = new Intent(itemView.getContext(), InforDetailActivity.class);

                        view.getContext().startActivity(intent);
                    }
                }
            });
        }

        public void setItem(Rank item){
            textViewRank.setText(item.getRank()+"위");
            textViewDongName.setText(item.getDongName());
            textViewScore.setText(item.getScore()+"점");
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
        Rank item=rankItems.get(position);
        holder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return rankItems.size();
    }

    public void addItem(Rank item){
        rankItems.add(item);
    }

    public void setItems(ArrayList<Rank> rankItems){
        this.rankItems=rankItems;
    }

    public Rank getItem(int position){
        return rankItems.get(position);
    }

    public void setItem(int position, Rank item){
        this.rankItems.set(position,item);
    }
}
