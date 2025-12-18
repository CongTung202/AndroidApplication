package com.example.quanlynhanvien.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlynhanvien.R;
import com.example.quanlynhanvien.model.PhongBan;
import java.util.ArrayList;

public class PhongBanAdapter extends RecyclerView.Adapter<PhongBanAdapter.PhongBanViewHolder> {

    private Context context;
    private ArrayList<PhongBan> listPhongBan;
    private OnPhongBanClickListener listener;

    // Interface để gửi sự kiện click ra ngoài Activity
    public interface OnPhongBanClickListener {
        void onItemClick(PhongBan pb);
        void onDeleteClick(PhongBan pb);
    }

    public PhongBanAdapter(Context context, ArrayList<PhongBan> listPhongBan, OnPhongBanClickListener listener) {
        this.context = context;
        this.listPhongBan = listPhongBan;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PhongBanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_phong_ban, parent, false);
        return new PhongBanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhongBanViewHolder holder, int position) {
        PhongBan pb = listPhongBan.get(position);
        holder.tvTen.setText(pb.getTenPhong());

        // Bấm vào item thì xem chi tiết
        holder.itemView.setOnClickListener(v -> listener.onItemClick(pb));

        // Bấm vào nút xóa
        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(pb));
    }

    @Override
    public int getItemCount() {
        return listPhongBan.size();
    }

    public static class PhongBanViewHolder extends RecyclerView.ViewHolder {
        TextView tvTen;
        ImageButton btnDelete;

        public PhongBanViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTen = itemView.findViewById(R.id.tvTenPhong);
            btnDelete = itemView.findViewById(R.id.btnDeletePhong);
        }
    }
}