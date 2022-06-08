package vn.name.duong.appbanhang.utils;

import java.util.List;

import vn.name.duong.appbanhang.model.GioHang;
import vn.name.duong.appbanhang.model.User;

public class Utils {
    public static final String localhost ="192.168.2.5";
    public static final String BASE_URL = "http://" + localhost + "/banhang/";
    public static List<GioHang> manggiohang;
    public static User user_current = new User();
}
// tôi đã chỉnh sửa