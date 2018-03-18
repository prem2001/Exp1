package com.example.prem.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.toolbox.ImageLoader;
import com.example.prem.R;
import com.example.prem.interfaces.OnItemClickListener;
import com.example.prem.model.MessageModel;

import java.io.File;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by prem on 9/3/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    String TAG = "MessageAdapter";
    ArrayList<MessageModel> messageModels;
    Context context;
    private OnItemClickListener mItemClickListener;

    public MessageAdapter(Context context, ArrayList<MessageModel> messageModels) {
        this.context = context;
        this.messageModels = messageModels;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        Log.d(TAG, viewType + "");
        View view = null;

        int layout = 0;
        switch (viewType) {
            case 1:
                layout = R.layout.message_list_cardview;

                break;
            case 2:
                layout = R.layout.sender_image_cardview;


                break;
            default:
                layout = R.layout.message_receiver_list_cardview;
                break;
//
        }


        view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new RecevierViewHolder(view);

    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        MessageModel messageModel = messageModels.get(position);

        RecevierViewHolder recevireViewHolder = (RecevierViewHolder) holder;
        Log.d(TAG, "RRR: " + messageModel.getReceiverMessage());
        Log.d(TAG, "sss: " + messageModel.getSenderMessage());
        Log.d(TAG, String.valueOf("ttt: " + messageModel.getType()));

//        switch (holder.getItemViewType()) {
        switch (messageModel.getType()) {
            case 1:
                recevireViewHolder.senderTextView.setText(messageModel.getReceiverMessage());

                break;
            case 2:

                Bitmap myBitmap = BitmapFactory.decodeFile(messageModel.getReceiverMessage());
                recevireViewHolder.senderImageView.setImageBitmap(myBitmap);


                break;
            case 0:

                recevireViewHolder.recevier_text_view.setText(messageModel.getReceiverMessage());

                break;
        }

    }

    @Override
    public int getItemViewType(int position) {
        switch (messageModels.get(position).getType()) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return -1;
        }

    }

    @Override
    public int getItemCount() {
        return messageModels.size();
    }


    public class RecevierViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{
        TextView senderTextView;

        TextView recevier_text_view;
        ImageView senderImageView;

        public RecevierViewHolder(View itemView) {
            super(itemView);
            recevier_text_view = itemView.findViewById(R.id.recevier_text_view);
            senderTextView = itemView.findViewById(R.id.sender_text_view);
            senderImageView = itemView.findViewById(R.id.sender_image_view);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }
    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
}
