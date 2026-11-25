import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

interface MienGiam {
    long tinhGiam(long hocPhi);
}

class ConChinhSach implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return (long) Math.round(hocPhi * 0.5);
    }
}

class HoNgheo implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return (long) Math.round(hocPhi * 0.7);
    }
}

class HocBong implements MienGiam {
    @Override
    public long tinhGiam(long hocPhi) {
        return Math.min(hocPhi, 3000000L);
    }
}

abstract class SinhVien {
    protected String maHe;
    protected String maSinhVien;
    protected int soTinChi;
    protected MienGiam mienGiam;

    public SinhVien(String maHe, String maSinhVien, int soTinChi) {
        this.maHe = maHe;
        this.maSinhVien = maSinhVien;
        this.soTinChi = soTinChi;
        this.mienGiam = null;
    }

    public abstract long hocPhi();

    public void setMienGiam(MienGiam mienGiam) {
        this.mienGiam = mienGiam;
    }

    public long getSoTienGiam() {
        return mienGiam != null ? mienGiam.tinhGiam(hocPhi()) : 0L;
    }

    public long getPhaiNop() {
        return hocPhi() - getSoTienGiam();
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public String getMaHe() {
        return maHe;
    }

    @Override
    public String toString() {
        return maSinhVien + " " + maHe + " " + hocPhi() + " " + getSoTienGiam() + " " + getPhaiNop();
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
        List<SinhVien> danhSach = new ArrayList<>();
        Map<String, SinhVien> map = new LinkedHashMap<>();

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
                danhSach.add(sv);
                map.put(maSv, sv);
            }
        }

        if (!sc.hasNextInt()) return;
        int m = sc.nextInt();
        List<String> notFoundList = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String maSv = sc.next();
            String maChinhSach = sc.next();

            if (map.containsKey(maSv)) {
                SinhVien sv = map.get(maSv);
                if (maChinhSach.equals("CS")) {
                    sv.setMienGiam(new ConChinhSach());
                } else if (maChinhSach.equals("HN")) {
                    sv.setMienGiam(new HoNgheo());
                } else if (maChinhSach.equals("HB")) {
                    sv.setMienGiam(new HocBong());
                }
            } else {
                notFoundList.add("NOT FOUND " + maSv);
            }
        }

        // 1. In cac dong NOT FOUND
        for (String msg : notFoundList) {
            System.out.println(msg);
        }

        // 2. In danh sach sinh vien theo thu tu nhap
        for (SinhVien sv : danhSach) {
            System.out.println(sv);
        }

        // 3. Thong ke theo thu tu CQ, CLC, LT (bo qua he khong co sinh vien)
        String[] dsHe = {"CQ", "CLC", "LT"};
        for (String he : dsHe) {
            int count = 0;
            long tongPhaiNop = 0;
            for (SinhVien sv : danhSach) {
                if (sv.getMaHe().equals(he)) {
                    count++;
                    tongPhaiNop += sv.getPhaiNop();
                }
            }
            if (count > 0) {
                System.out.println(he + " " + count + " " + tongPhaiNop);
            }
        }
    }
}
