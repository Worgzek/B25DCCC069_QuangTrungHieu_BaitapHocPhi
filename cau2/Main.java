import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class SinhVien {
    protected String maHe;
    protected String maSinhVien;
    protected int soTinChi;

    public SinhVien(String maHe, String maSinhVien, int soTinChi) {
        this.maHe = maHe;
        this.maSinhVien = maSinhVien;
        this.soTinChi = soTinChi;
    }

    public abstract long hocPhi();

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getMaHe() {
        return maHe;
    }

    @Override
    public String toString() {
        return maSinhVien + " " + maHe + " " + hocPhi();
    }
}

class SinhVienChinhQuy extends SinhVien {
    public SinhVienChinhQuy(String maSinhVien, int soTinChi) {
        super("CQ", maSinhVien, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000L;
    }
}

class SinhVienChatLuongCao extends SinhVien {
    public SinhVienChatLuongCao(String maSinhVien, int soTinChi) {
        super("CLC", maSinhVien, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) Math.round(soTinChi * 450000.0 * 1.5);
    }
}

class SinhVienLienThong extends SinhVien {
    public SinhVienLienThong(String maSinhVien, int soTinChi) {
        super("LT", maSinhVien, soTinChi);
    }

    @Override
    public long hocPhi() {
        return (long) soTinChi * 450000L + 2000000L;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextInt()) return;
        int n = sc.nextInt();
        List<SinhVien> list = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String maHe = sc.next();
            String maSv = sc.next();
            int soTinChi = sc.nextInt();

            SinhVien sv = null;
            if (maHe.equals("CQ")) {
                sv = new SinhVienChinhQuy(maSv, soTinChi);
            } else if (maHe.equals("CLC")) {
                sv = new SinhVienChatLuongCao(maSv, soTinChi);
            } else if (maHe.equals("LT")) {
                sv = new SinhVienLienThong(maSv, soTinChi);
            }
            if (sv != null) {
                list.add(sv);
            }
        }

        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }
}
