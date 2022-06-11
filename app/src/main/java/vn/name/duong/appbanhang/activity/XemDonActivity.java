package vn.name.duong.appbanhang.activity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.duong.appbanhang.R;
import vn.name.duong.appbanhang.retrofit.ApiBanHang;
import vn.name.duong.appbanhang.retrofit.RetrofitClient;
import vn.name.duong.appbanhang.utils.Utils;

public class XemDonActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiBanHang apiBanHang;

    @Override
    protected void onCreate(Bundle saveInstanceState){
        super.onCreate(saveInstanceState);
        setContentView(R.layout.activity_xem_don);
        apiBanHang = RetrofitClient.getInstance(Utils.BASE_URL).create(ApiBanHang.class);

        compositeDisposable.add((apiBanHang.xemDonHang(Utils.user_current.getId()))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(
                        donHangModel -> {
                            Toast.makeText(getApplicationContext(), donHangModel.getResult().get(0).getItem().get(1).getTensp(), Toast.LENGTH_SHORT).show();
                        },
                        throwable -> {

                        }
                ));
    }
}
