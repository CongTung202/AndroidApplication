package com.example.quanlynhanvien.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.quanlynhanvien.R;
import com.example.quanlynhanvien.model.NhanVien;
import com.example.quanlynhanvien.utils.DbBitmapUtility;
import java.util.ArrayList;

public class NhanVienAdapter extends RecyclerView.Adapter<NhanVienAdapter.NhanVienViewHolder> {

    private Context context;
    private ArrayList<NhanVien> listNhanVien;
    private OnNhanVienClickListener listener;

    public interface OnNhanVienClickListener {
        void onDeleteClick(NhanVien nv);
    }

    public NhanVienAdapter(Context context, ArrayList<NhanVien> listNhanVien, OnNhanVienClickListener listener) {
        this.context = context;
        this.listNhanVien = listNhanVien;
        this.listener = listener;
    }

    @NonNull
    @Override
    public NhanVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_nhan_vien, parent, false);
        return new NhanVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NhanVienViewHolder holder, int position) {
        NhanVien nv = listNhanVien.get(position);
        holder.tvTen.setText(nv.getTenNV());
        holder.tvSdt.setText(nv.getSdt());

        // Hiển thị tên phòng ban
        if (nv.getTenPhong() != null && !nv.getTenPhong().isEmpty()) {
            holder.tvTenPhong.setText("Phòng: " + nv.getTenPhong());
            holder.tvTenPhong.setVisibility(View.VISIBLE);
        } else {
            holder.tvTenPhong.setVisibility(View.GONE);
        }

        // --- XỬ LÝ ẢNH ---
        if (nv.getHinhAnh() != null) {
            Bitmap bitmap = DbBitmapUtility.getImage(nv.getHinhAnh());
            holder.imgAvatar.setImageBitmap(bitmap);
        } else {
            holder.imgAvatar.setImageResource(R.mipmap.ic_launcher);
        }

        holder.btnDelete.setOnClickListener(v -> listener.onDeleteClick(nv));
    }

    @Override
    public int getItemCount() {
        return listNhanVien.size();
    }

    public static class NhanVienViewHolder extends RecyclerView.ViewHolder {
        ImageView imgAvatar;
        TextView tvTen, tvSdt, tvTenPhong; // Thêm tvTenPhong
        ImageButton btnDelete;

        public NhanVienViewHolder(@NonNull View itemView) {
            super(itemView);
            imgAvatar = itemView.findViewById(R.id.imgAvatarItem);
            tvTen = itemView.findViewById(R.id.tvTenNV);
            tvSdt = itemView.findViewById(R.id.tvSdtNV);
            tvTenPhong = itemView.findViewById(R.id.tvTenPhong); // Tìm ID của TextView mới
            btnDelete = itemView.findViewById(R.id.btnDeleteNV);
        }
    }
}