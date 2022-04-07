package com.example.cityguide.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cityguide.R;

import java.util.ArrayList;

public class MostviewedAdapter extends RecyclerView.Adapter<MostviewedAdapter.MostviewedViewHolder> {
    ArrayList<MostviewedHelperClass> Mostviewed;

    public MostviewedAdapter(ArrayList<MostviewedHelperClass> mostviewed) {
        this.Mostviewed = mostviewed;
    }


    @NonNull
    @Override
    public MostviewedViewHolder onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        View view = LayoutInflater.from ( parent.getContext ()).inflate ( R.layout.most_viewed_card_design ,parent,false);
         MostviewedViewHolder mostviewedViewHolder = new MostviewedViewHolder ( view );

        return mostviewedViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MostviewedAdapter.MostviewedViewHolder holder , int position) {
        MostviewedHelperClass mostviewedHelperClass = Mostviewed.get ( position );

        holder.image.setImageResource (mostviewedHelperClass.getImage () );
        holder.title.setText (mostviewedHelperClass.getTitle () );
        holder.desc.setText (mostviewedHelperClass.getDescription () );
    }

    @Override
    public int getItemCount() {
        return Mostviewed.size ();
    }

    public static class MostviewedViewHolder extends RecyclerView.ViewHolder{

        ImageView image;
        TextView title,desc;

        public MostviewedViewHolder(@NonNull View itemView) {
            super ( itemView );

            //Hooks
            image = itemView.findViewById ( R.id.mv_image );
            title = itemView.findViewById ( R.id.mv_title );
            desc = itemView.findViewById ( R.id.mv_desc);
        }
    }


}
