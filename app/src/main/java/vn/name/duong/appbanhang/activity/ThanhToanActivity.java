package vn.name.duong.appbanhang.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.core.text.TextUtilsCompat;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DecimalFormat;

import okhttp3.internal.Util;
import vn.name.duong.appbanhang.R;
import vn.name.duong.appbanhang.utils.Utils;

public class ThanhToanActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView txttongtien, txtsodienthoai, txtemail;
    EditText edtdiachi;
    AppCompatButton btndathang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thanh_toan);
        initView();
        initControl();
    }

    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

// lấy tổng tiền từ bên Giỏ hang qua
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long tongtien = getIntent().getLongExtra("tongtien", 0);
        txttongtien.setText(decimalFormat.format(tongtien));
        txtsodienthoai.setText(Utils.user_current.getMobile());
        txtemail.setText(Utils.user_current.getEmail());

// bắt sk đặt hàng
        btndathang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_diachi= edtdiachi.getText().toString().trim();
                if (TextUtils.isEmpty(str_diachi)){
                    Toast.makeText(getApplicationContext(), "Bạn chưa nhập địa chỉ", Toast.LENGTH_SHORT).show();
                }else {
                    //Post data len server, luu vao DB
                    Log.d("test", new Gson().toJson(Utils.manggiohang));
                }
            }
        });
    }

    private void initView() {
        toolbar =findViewById(R.id.toobar);
        txttongtien =findViewById(R.id.txttongtien);
        txtsodienthoai =findViewById(R.id.txtsodienthoai);
        txtemail =findViewById(R.id.txtemail);
        edtdiachi =findViewById(R.id.edtdiachi);
        btndathang =findViewById(R.id.btndathang);
    }
}