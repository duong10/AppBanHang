package vn.name.duong.appbanhang.model;

import java.util.List;

public class SanPhamMoiModel {
    boolean success;
    String message;
    List<SanPhamMoi> result;
    //Lay, xet gtri tt
    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SanPhamMoi> getResult() {
        return result;
    }

    public void setResult(List<SanPhamMoi> result) {
        this.result = result;
    }
}
