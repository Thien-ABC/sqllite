package stu.edu.vn.sqllite.model;

import java.io.Serializable;

public class Sach implements Serializable {

    private int ma;
    private String ten;
    private String tacgia;
    private int namxuatban;

    public Sach() {
    }

    public Sach( String ten, String tacgia, int namxuatban) {
        this.ma = ma;
        this.ten = ten;
        this.tacgia = tacgia;
        this.namxuatban = namxuatban;
    }

    @Override
    public String toString() {
        return
                "Mã:" + ma +
                        ", Tên:'" + ten + '\'' +
                        ", Tác giả:'" + tacgia + '\'' +
                        ", Nhà xuất bản:" + namxuatban ;
    }

    public int getMa() {
        return ma;
    }

    public void setMa(int ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getTacgia() {
        return tacgia;
    }

    public void setTacgia(String tacgia) {
        this.tacgia = tacgia;
    }

    public int getNamxuatban() {
        return namxuatban;
    }

    public void setNamxuatban(int namxuatban) {
        this.namxuatban = namxuatban;
    }
}
