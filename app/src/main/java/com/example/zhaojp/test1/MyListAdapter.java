package com.example.zhaojp.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ViewHolder> {

    private List<String> myList;
    private List<String> item;
    private List<Integer> picture;
    private int rowLayout;
    private Context mContext;
    private View v;
    private OnItemClickListener onItemClickListener;

    public MyListAdapter(List<String> myList,List<String> item,List<Integer> picture, int rowLayout, Context context) {
        this.myList = myList;
        this.item=item;
        this.picture=picture;

        this.rowLayout = rowLayout;
        this.mContext = context;
    }


    // ① 定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(MyListAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup,int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);

        /*v.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,Trainer_introduction.class);
                mContext.startActivity(intent);
            }
        });*/

        return new ViewHolder(v);  //this is the major change here.
    }

    @Override
    public int getItemCount() {
        return myList == null ? 0 : myList.size();
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, int position) {
        viewHolder.name.setText(myList.get(position));
        viewHolder.item.setText(item.get(position));

        /*viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                Toast.makeText(mContext,tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

        viewHolder.Pic.setImageResource(picture.get(position));


        //③ 对RecyclerView的每一个itemView设置点击事件
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                if(onItemClickListener != null) {
                    int pos = viewHolder.getLayoutPosition();
                    onItemClickListener.onItemClick(viewHolder.itemView, pos);
                }
            }
        });

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView Pic;
        public TextView name,item;
        public ViewHolder(View itemView) {
            super(itemView);
            Pic= itemView.findViewById(R.id.imageView);
            name =itemView.findViewById(R.id.textName);
            item =itemView.findViewById(R.id.textItem);
            //content = itemView.findViewById(R.id.textContent);
        }

    }

}

