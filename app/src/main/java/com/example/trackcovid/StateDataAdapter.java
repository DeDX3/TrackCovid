package com.example.trackcovid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StateDataAdapter extends RecyclerView.Adapter<StateDataAdapter.StateDataViewHolder> {

    private Context context;
    private ArrayList<StateListItem> StateDataList;

    public StateDataAdapter(Context context, ArrayList<StateListItem> StateDataList){
        this.context = context;
        this.StateDataList = StateDataList;
    }

    @NonNull
    @Override
    public StateDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_state_list_layout, parent, false);
        return new StateDataViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull StateDataViewHolder holder, int position) {
        StateListItem currentItem = StateDataList.get(position);

        String state = currentItem.getState();
        String stConfirmed = currentItem.getStConfirmed();
        String stActive = currentItem.getStActive();
        String stRecovered = currentItem.getStRecovered();
        String stDeaths = currentItem.getStDeaths();
        String stNewConfirmed = currentItem.getStNewConfirmed();
        String stNewRecovered = currentItem.getStNewRecovered();
        String stNewDeaths = currentItem.getStNewDeaths();
        int stNewActive = currentItem.getStNewActive();

        holder.tvState.setText(state);
        holder.tvStConfirmed.setText(stConfirmed);
        holder.tvStActive.setText(stActive);
        holder.tvStRecovered.setText(stRecovered);
        holder.tvStDeaths.setText(stDeaths);
        holder.tvStNewConfirmed.setText("[+"+stNewConfirmed+"]");
        holder.tvStNewActive.setText("[+"+stNewActive+"]");
        holder.tvStNewRecovered.setText("[+"+stNewRecovered+"]");
        holder.tvStNewDeaths.setText("[+"+stNewDeaths+"]");

    }

    @Override
    public int getItemCount() {
        return StateDataList.size();
    }

    public class StateDataViewHolder extends RecyclerView.ViewHolder{

        public TextView tvState, tvStNewConfirmed, tvStNewActive, tvStNewRecovered, tvStNewDeaths;
        public TextView tvStConfirmed, tvStActive, tvStRecovered, tvStDeaths;

        public StateDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvState = itemView.findViewById(R.id.tvState);

            tvStConfirmed = itemView.findViewById(R.id.tvStConfirmed);
            tvStActive = itemView.findViewById(R.id.tvStActive);
            tvStRecovered = itemView.findViewById(R.id.tvStRecovered);
            tvStDeaths = itemView.findViewById(R.id.tvStDeaths);

            tvStNewConfirmed = itemView.findViewById(R.id.tvStNewConfirmed);
            tvStNewActive = itemView.findViewById(R.id.tvStNewActive);
            tvStNewRecovered = itemView.findViewById(R.id.tvStNewRecovered);
            tvStNewDeaths = itemView.findViewById(R.id.tvStNewDeaths);

        }
    }
}