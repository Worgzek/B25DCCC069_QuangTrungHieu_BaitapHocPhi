import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class DuLieuKhongHopLeException extends Exception {
    public DuLieuKhongHopLeException(String message) {
        super(message);
    }
}

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

class QuanLyHocPhi {
    private ArrayList<SinhVien> danhSach = new ArrayList<>();

    public boolean daTonTai(String maSv) {
        for (SinhVien sv : danhSach) {
            if (sv.getMaSinhVien().equals(maSv)) {
                return true;
            }
        }
        return false;
    }

    public void themSinhVien(SinhVien sv) {
        danhSach.add(sv);
    }

    public void sapXep() {
        danhSach.sort((sv1, sv2) -> {
            if (sv2.hocPhi() != sv1.hocPhi()) {
                return Long.compare(sv2.hocPhi(), sv1.hocPhi());
            }
            return sv1.getMaSinhVien().compareTo(sv2.getMaSinhVien());
        });
    }

    public long getTongHocPhi() {
        long tong = 0;
        for (SinhVien sv : danhSach) {
            tong += sv.hocPhi();
        }
        return tong;
    }

    public ArrayList<SinhVien> getDanhSach() {
        return danhSach;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        if (!sc.hasNextLine()) return;
        String lineN = sc.nextLine().trim();
        while (lineN.isEmpty() && sc.hasNextLine()) {
            lineN = sc.nextLine().trim();
        }
        if (lineN.isEmpty()) return;

        int n = Integer.parseInt(lineN);
        QuanLyHocPhi quanLy = new QuanLyHocPhi();
        List<String> danhSachKhongHopLe = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            while (line.isEmpty() && sc.hasNextLine()) {
                line = sc.nextLine().trim();
            }
            String[] parts = line.split("\\s+");
            String maHe = parts[0];
            String maSv = parts[1];
            String tcStr = parts[2];

            try {
                // 1. Ma he khong phai CQ, CLC, LT
                if (!maHe.equals("CQ") && !maHe.equals("CLC") && !maHe.equals("LT")) {
                    throw new DuLieuKhongHopLeException(maSv);
                }

                // 2. So tin chi khong phai so nguyen
                int soTinChi;
                try {
                    soTinChi = Integer.parseInt(tcStr);
                } catch (NumberFormatException e) {
                    throw new DuLieuKhongHopLeException(maSv);
                }

                // 3. So tin chi nam ngoai [1, 30]
                if (soTinChi < 1 || soTinChi > 30) {
                    throw new DuLieuKhongHopLeException(maSv);
                }

                // 4. Ma sinh vien trung voi ma cua mot sinh vien hop le da nhap truoc do
                if (quanLy.daTonTai(maSv)) {
                    throw new DuLieuKhongHopLeException(maSv);
                }

                SinhVien sv = null;
                if (maHe.equals("CQ")) {
                    sv = new SinhVienChinhQuy(maSv, soTinChi);
                } else if (maHe.equals("CLC")) {
                    sv = new SinhVienChatLuongCao(maSv, soTinChi);
                } else if (maHe.equals("LT")) {
                    sv = new SinhVienLienThong(maSv, soTinChi);
                }

                if (sv != null) {
                    quanLy.themSinhVien(sv);
                }
            } catch (DuLieuKhongHopLeException e) {
                danhSachKhongHopLe.add("INVALID " + e.getMessage());
            }
        }

        // In danh sach khong hop le theo thu tu nhap
        for (String msg : danhSachKhongHopLe) {
            System.out.println(msg);
        }

        // Sap xep va in danh sach hop le
        quanLy.sapXep();
        for (SinhVien sv : quanLy.getDanhSach()) {
            System.out.println(sv);
        }

        // In tong hoc phi
        System.out.println("TONG " + quanLy.getTongHocPhi());
    }
}
