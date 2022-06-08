package vn.name.duong.appbanhang.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import vn.name.duong.appbanhang.Interface.IImageClickListener;
import vn.name.duong.appbanhang.R;
import vn.name.duong.appbanhang.model.EventBus.TinhTongEvent;
import vn.name.duong.appbanhang.model.GioHang;
import vn.name.duong.appbanhang.utils.Utils;

public class GioHangAdapter extends RecyclerView.Adapter<GioHangAdapter.MyViewHolder> {
    Context context;
    List<GioHang> gioHangList;

    public GioHangAdapter(Context context, List<GioHang> gioHangList) {
        this.context = context;
        this.gioHangList = gioHangList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_giohang, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        GioHang gioHang = gioHangList.get(position);
        holder.item_giohang_tensp.setText(gioHang.getTensp());
        holder.item_giohang_soluong.setText(gioHang.getSoluong() +" ");
        Glide.with(context).load(gioHang.getHinhsp()).into(holder.item_giohang_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.item_giohang_gia.setText(decimalFormat.format((gioHang.getGiasp()))+" đ");
        long gia = gioHang.getSoluong()*gioHang.getGiasp();
        holder.item_giohang_giasp2.setText(decimalFormat.format(gia));

        //Xử lý thêm, bớt sản phẩm trong giỏ hàng
        holder.setListener(new IImageClickListener() {
            @Override
            public void onImageClieck(View view, int pos, int giatri) {
                if(giatri ==1){
                    if (gioHangList.get(pos).getSoluong() > 1){
                        int soluongmoi = gioHangList.get(pos).getSoluong()-1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                    }else if (gioHangList.get(pos).getSoluong() == 1){
                        //thông báo loại bỏ sp ra man hinh
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Thông báo");
                        builder.setMessage("Bạn muốn xóa sản phẩm này khỏi giỏ hàng?");
                        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Utils.manggiohang.remove(pos);
                                notifyDataSetChanged();
                                EventBus.getDefault().postSticky(new TinhTongEvent());
                            }
                        });
                        builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                }else if (giatri == 2){
                    if (gioHangList.get(pos).getSoluong() < 10){
                        int soluongmoi = gioHangList.get(pos).getSoluong()+1;
                        gioHangList.get(pos).setSoluong(soluongmoi);
                    }
                }
                //Cập nhật lại giá trị số lượng, giá trong giỏ hàng
                holder.item_giohang_soluong.setText(gioHangList.get(pos).getSoluong() +" ");
                long gia = gioHangList.get(pos).getSoluong()*gioHangList.get(pos).getGiasp();
                holder.item_giohang_giasp2.setText(decimalFormat.format(gia));
                // Gửi sự kiện chuyển tổng giá sp -> GioHangActivity
                EventBus.getDefault().postSticky(new TinhTongEvent());
            }
        });
    }

    @Override
    public int getItemCount() {
        return gioHangList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_giohang_image, imgtru, imgcong;
        TextView item_giohang_tensp, item_giohang_gia, item_giohang_soluong, item_giohang_giasp2;
        IImageClickListener listener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_giohang_image = itemView.findViewById(R.id.item_giohang_image);
            item_giohang_tensp = itemView.findViewById(R.id.item_giohang_tensp);
            item_giohang_gia = itemView.findViewById(R.id.item_giohang_gia);
            item_giohang_soluong = itemView.findViewById(R.id.item_giohang_soluong);
            item_giohang_giasp2 = itemView.findViewById(R.id.item_giohang_giasp2);
            imgtru = itemView.findViewById(R.id.item_giohang_tru);
            imgcong = itemView.findViewById(R.id.item_giohang_cong);

            //event click
            imgtru.setOnClickListener(this);
            imgcong.setOnClickListener(this);
        }

        public void setListener(IImageClickListener listener) {
            this.listener = listener;
        }

        @Override
        public void onClick(View view) {
            if(view == imgtru){
                listener.onImageClieck(view, getAdapterPosition(), 1);
                //1: trừ
            }else if (view == imgcong){
                listener.onImageClieck(view, getAdapterPosition(), 2);
                //2: cộng
            }
        }
    }
}
