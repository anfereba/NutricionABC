package com.anfereba.nutricionabc.FragmentosAdministrador.Listas;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.anfereba.nutricionabc.R;
import com.anfereba.nutricionabc.db.Entidades.Usuario;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {
    private List<Usuario> mData;
    private LayoutInflater mInflater;
    private Context context;

    public ListAdapter(List<Usuario> itemList, Context context){
        this.mInflater = LayoutInflater.from(context);
        this.context = context;
        this.mData = itemList;

    }

    @NonNull
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.list_clientes, null);
        return new ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListAdapter.ViewHolder holder, int position) {
        holder.bindData(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void setItems(List<Usuario> items){ mData = items;}

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView IMGCardView;
        TextView nameTextView, cityTextView, statusTextView;

        ViewHolder(View itemView){
            super(itemView);
            IMGCardView = itemView.findViewById(R.id.IMGCardView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            cityTextView = itemView.findViewById(R.id.cityTextView);
            statusTextView = itemView.findViewById(R.id.statusTextView);
        }

        void bindData(final Usuario item){

            Bitmap bitmap = BitmapFactory.decodeByteArray(item.getFotoPerfil(), 0, item.getFotoPerfil().length);

            IMGCardView.setImageBitmap(bitmap);
            nameTextView.setText(item.getNombres() + " " + item.getApellidos());
            cityTextView.setText(item.getCiudad() + " - " + item.getCorreo());
           // statusTextView.setText(item.getCorreo());
        }

    }
}
