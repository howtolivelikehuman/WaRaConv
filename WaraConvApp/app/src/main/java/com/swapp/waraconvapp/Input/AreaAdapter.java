package com.swapp.waraconvapp.Input;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.swapp.waraconvapp.R;

import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter {
    public static final int HEADER = 0;
    public static final int CHILD = 1;

    public List<Gu> data;
    public ArrayList<String> area = new ArrayList<>();

    public AreaAdapter(List<Gu> data){
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        Context context = parent.getContext();
        float dp = context.getResources().getDisplayMetrics().density;
        int subItemPaddingLeft = (int)(18*dp);
        int subItemPaddingTopAndBottom = (int)(5*dp);

        switch (viewType){
            case HEADER:
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.area_header, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;

            case CHILD:
                TextView itemTextView = new TextView(context);
                itemTextView.setPadding(subItemPaddingLeft, subItemPaddingTopAndBottom, 0, subItemPaddingTopAndBottom);
                itemTextView.setTextColor(0x88000000);
                itemTextView.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                ViewGroup.LayoutParams.WRAP_CONTENT,
                                ViewGroup.LayoutParams.WRAP_CONTENT));
                return new RecyclerView.ViewHolder(itemTextView) {
                };
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        final Gu item = data.get(position);
        switch (item.type) {
            case HEADER:
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.refferalItem = item;
                itemController.header_title.setText(item.text);

                //FOR CHECK
                itemController.checkBox.setOnCheckedChangeListener(null);
                itemController.checkBox.setChecked(item.checked);
                itemController.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                        item.checked = b;
                        //새로 체크된 애
                        if(item.checked == true){
                            area.add(item.text);
                        }
                        //체크 취소된 애
                        else{
                            area.remove(item.text);
                        }
                    }
                });

                if (item.invisibleChildren == null) {
                    itemController.btn_expand.setImageResource(R.drawable.sort_minus);
                } else {
                    itemController.btn_expand.setImageResource(R.drawable.sort_plus);
                }

                itemController.btn_expand.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (item.invisibleChildren == null) {
                            item.invisibleChildren = new ArrayList<Gu>();
                            int count = 0;
                            int pos = data.indexOf(itemController.refferalItem);
                            while (data.size() > pos + 1 && data.get(pos + 1).type == CHILD) {
                                item.invisibleChildren.add(data.remove(pos + 1));
                                count++;
                            }
                            notifyItemRangeRemoved(pos + 1, count);
                            itemController.btn_expand.setImageResource(R.drawable.sort_plus);
                        } else {
                            int pos = data.indexOf(itemController.refferalItem);
                            int index = pos + 1;
                            for (Gu i : item.invisibleChildren) {
                                data.add(index, i);
                                index++;
                            }
                            notifyItemRangeInserted(pos + 1, index - pos - 1);
                            itemController.btn_expand.setImageResource(R.drawable.sort_minus);
                            item.invisibleChildren = null;
                        }
                    }
                });
                break;
            case CHILD:
                TextView itemTextView = (TextView) holder.itemView;
                itemTextView.setText(data.get(position).text);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder{
        public TextView header_title;
        public ImageView btn_expand;
        public Gu refferalItem;
        public CheckBox checkBox;

        public ListHeaderViewHolder(View itemView){
            super(itemView);
            header_title = (TextView)itemView.findViewById(R.id.header_title);
            btn_expand = (ImageView)itemView.findViewById(R.id.btn_expand);
            checkBox = (CheckBox) itemView.findViewById(R.id.area_checkBox);
        }
    }

    public static class Gu{
        public int type;
        public String text;
        public java.util.List<Gu> invisibleChildren;
        public boolean checked;

        public Gu(){

        }
        public Gu(int type, String text){
            this.type = type;
            this.text = text;
        }
    }
}
