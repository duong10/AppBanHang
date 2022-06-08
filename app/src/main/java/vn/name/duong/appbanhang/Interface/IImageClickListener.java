package vn.name.duong.appbanhang.Interface;

import android.view.View;

//Hành vi thêm bớt sản phẩm trong giỏ hàng
// giatri = 1(phép trừ); = 2(phép Cộng)
public interface IImageClickListener {
    void onImageClieck(View view, int pos, int giatri);
}
