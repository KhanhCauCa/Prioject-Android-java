package com.example.testapi.object;

public class NhaCungCap {
    private String MaNCC;
    private String TenNCC;

    public NhaCungCap(String maNCC, String tenNCC) {
        MaNCC = maNCC;
        TenNCC = tenNCC;
    }

    public String getMaNCC() {
        return MaNCC;
    }

    public void setMaNCC(String maNCC) {
        MaNCC = maNCC;
    }

    public String getTenNCC() {
        return TenNCC;
    }

    public void setTenNCC(String tenNCC) {
        TenNCC = tenNCC;
    }
}
