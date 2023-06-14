package com.example.projector;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import  com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import  com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class tasksAdapter extends FirestoreRecyclerAdapter<tasks,tasksAdapter.tasksAdapterViewHolder> {

    public tasksAdapter(@NonNull FirestoreRecyclerOptions<tasks> options)
    {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull tasksAdapter.tasksAdapterViewHolder holder, int position, @NonNull tasks model) {
        holder.task_name.setText(model.getDescription());
        holder.task_category.setText(model.getCategory());
        holder.priority.setText(model.getPriority());
    }

    @NonNull
    @Override
    public tasksAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new tasksAdapter.tasksAdapterViewHolder(view);
    }

    class tasksAdapterViewHolder extends RecyclerView.ViewHolder{
        TextView task_name, task_category, priority;
        public tasksAdapterViewHolder(@NonNull View itemView)
        {
            super(itemView);
            task_name = itemView.findViewById(R.id.task_name);
            task_category = itemView.findViewById(R.id.task_category);
            priority = itemView.findViewById(R.id.priority);
        }

    }
}

