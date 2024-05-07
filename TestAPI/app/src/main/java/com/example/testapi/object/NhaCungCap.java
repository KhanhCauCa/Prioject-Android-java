package com.example.testapi.object;

public class NhaCungCap {
    private String mancc;
    private String tenncc;

    public NhaCungCap(String maNCC, String tenNCC) {
        mancc = maNCC;
        tenncc = tenNCC;
    }

    public String getMancc() {
        return mancc;
    }

    public void setMancc(String mancc) {
        this.mancc = mancc;
    }

    public String getTenncc() {
        return tenncc;
    }

    public void setTenncc(String tenncc) {
        this.tenncc = tenncc;
    }
}
