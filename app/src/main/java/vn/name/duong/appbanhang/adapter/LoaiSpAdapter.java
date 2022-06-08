package vn.name.duong.appbanhang.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.name.duong.appbanhang.R;
import vn.name.duong.appbanhang.model.LoaiSp;
//Xay dung 1 khuon mau, dtr xu ly gtr truyen vao nhanh chong

public class LoaiSpAdapter extends BaseAdapter {
    //Dinh hinh khuon
    List<LoaiSp> array;
    Context context;

    public LoaiSpAdapter(Context context, List<LoaiSp> array) {
        this.array = array;
        this.context = context;
    }

    @Override
    public int getCount() {
        //Do het gtri ra
        return array.size();
    }
//lay ra tung tt trong mang
    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

//bat anhxa, chi can load cung 1 kieu lan dau tien
    public class ViewHordel{
        TextView textensp;
        ImageView imghinhanh;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHordel viewHordel = null;
        if (view == null ){
            viewHordel = new ViewHordel();
//GOi layout ra
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//view: tao khuon listView
            view = layoutInflater.inflate(R.layout.item_sanpham, null);
//Gan id cho cac thuoc tinh trong layout
            viewHordel.textensp = view.findViewById(R.id.item_tensp);
            viewHordel.imghinhanh = view.findViewById(R.id.item_image);
            view.setTag(viewHordel);
        }else {
            viewHordel = (ViewHordel) view.getTag();
        }
        viewHordel.textensp.setText(array.get(i).getTensanpham());
        Glide.with(context).load(array.get(i).getHinhanh()).into(viewHordel.imghinhanh);
        return view;
    }
}