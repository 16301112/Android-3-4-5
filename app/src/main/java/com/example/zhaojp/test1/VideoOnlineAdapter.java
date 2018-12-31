package com.example.zhaojp.test1;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class VideoOnlineAdapter extends RecyclerView.Adapter<VideoOnlineAdapter.ViewHolder> {

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_online);
    }*/

    private List<String> name;
    private List<String> source;
    private List<String> time;
    private int rowLayout;
    private Context mContext;
    private View v;
    private VideoOnlineAdapter.OnItemClickListener onItemClickListener;

    public VideoOnlineAdapter(List<String> name, List<String> source, List<String> time, int rowLayout, Context context) {
        this.name = name;
        this.source=source;
        this.time = time;

        this.rowLayout = rowLayout;
        this.mContext = context;
    }


    // ① 定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
        //void onItemLongClick(View view, int position);
    }

    // ② 定义一个设置点击监听器的方法
    public void setOnItemClickListener(VideoOnlineAdapter.OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    public VideoOnlineAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(rowLayout, viewGroup, false);
        return new VideoOnlineAdapter.ViewHolder(v);  //this is the major change here.
    }


    public int getItemCount() {
        return name == null ? 0 : name.size();
    }


    public void onBindViewHolder(final VideoOnlineAdapter.ViewHolder viewHolder, int position) {

        viewHolder.name.setText(name.get(position));
        viewHolder.source.setText(source.get(position));
        viewHolder.time.setText(time.get(position));

        /*viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv = (TextView)v;
                Toast.makeText(mContext,tv.getText(), Toast.LENGTH_SHORT).show();
            }
        });*/

        viewHolder.Pic.setImageResource(R.drawable.trainer1);


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
        public TextView name,source,time;

        public ViewHolder(View itemView) {
            super(itemView);
            Pic= itemView.findViewById(R.id.videoPicture);

            name =itemView.findViewById(R.id.fileName);
            source =itemView.findViewById(R.id.fileSource);
            time =itemView.findViewById(R.id.fileTime);
        }

    }
}


