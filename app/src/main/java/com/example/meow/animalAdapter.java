package com.example.meow;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class animalAdapter extends RecyclerView.Adapter<animalAdapter.ViewHolder> {

    private ArrayList<animals> animal;
    private OnItemListener mOnItemListener;


    public animalAdapter (Context context, ArrayList<animals> list, OnItemListener onItemListener){

        animal = list;
        this.mOnItemListener = onItemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView dogName,sexo,idade,porte,endereco;
        ImageView imageDog,favourite_border;
        OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener) {
            super(itemView);

            dogName = itemView.findViewById(R.id.dogName);
            sexo = itemView.findViewById(R.id.sexo);
            idade = itemView.findViewById(R.id.idade);
            porte = itemView.findViewById(R.id.porte);
            endereco = itemView.findViewById(R.id.endereco);

            imageDog = itemView.findViewById(R.id.imageDog);
            favourite_border = itemView.findViewById(R.id.favourite_border);
            this.onItemListener = onItemListener;

            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }
    public interface OnItemListener{
        void onItemClick(int position);
    }
    @NonNull
    @Override
    public animalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_animals, viewGroup, false);

        return new ViewHolder(v,mOnItemListener);
    }
    @Override
    public void onBindViewHolder(@NonNull animalAdapter.ViewHolder viewHolder, int i) {

        viewHolder.itemView.setTag(animal.get(i));
        viewHolder.dogName.setText(animal.get(i).getNome());
        viewHolder.sexo.setText(animal.get(i).getSexo());
        viewHolder.idade.setText(animal.get(i).getIdade());
        viewHolder.porte.setText((animal.get(i).getPorte()));
        viewHolder.endereco.setText(animal.get(i).getEndereco());

        if(animal.get(i).getNome().equals("Charlie")){
            viewHolder.imageDog.setImageResource(R.drawable.dog1);

        }else if(animal.get(i).getNome().equals("Lisa")) {
            viewHolder.imageDog.setImageResource(R.drawable.dog2);

        }else if (animal.get(i).getNome().equals("Daisy")){
            viewHolder.imageDog.setImageResource(R.drawable.dog3);
        }else if (animal.get(i).getNome().equals("Daisy 2")) {
            viewHolder.imageDog.setImageResource(R.drawable.dog4);
        }
    }
    @Override
    public int getItemCount() {
        return animal.size();
    }
}
