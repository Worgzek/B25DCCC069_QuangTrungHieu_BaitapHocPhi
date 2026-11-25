import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class SinhVien {
    private String maSinhVien;
    private String hoTen;
    private String ngaySinh;

    public SinhVien(String maSinhVien, String hoTen, String ngaySinh) {
        this.maSinhVien = maSinhVien.trim();
        this.hoTen = chuanHoaHoTen(hoTen);
        this.ngaySinh = chuanHoaNgaySinh(ngaySinh);
    }

    private String chuanHoaHoTen(String name) {
        if (name == null) return "";
        String[] words = name.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < words.length; i++) {
            String w = words[i];
            if (!w.isEmpty()) {
                sb.append(Character.toUpperCase(w.charAt(0)));
                if (w.length() > 1) {
                    sb.append(w.substring(1).toLowerCase());
                }
                if (i < words.length - 1) {
                    sb.append(" ");
                }
            }
        }
        return sb.toString();
    }

    private String chuanHoaNgaySinh(String dob) {
        if (dob == null) return "";
        String[] parts = dob.trim().split("/");
        if (parts.length == 3) {
            int d = Integer.parseInt(parts[0]);
            int m = Integer.parseInt(parts[1]);
            int y = Integer.parseInt(parts[2]);
            return String.format("%02d/%02d/%04d", d, m, y);
        }
        return dob.trim();
    }

    @Override
    public String toString() {
        return maSinhVien + " " + hoTen + " " + ngaySinh;
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
        List<SinhVien> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String ma = sc.nextLine();
            String ten = sc.nextLine();
            String ns = sc.nextLine();
            list.add(new SinhVien(ma, ten, ns));
        }

        for (SinhVien sv : list) {
            System.out.println(sv);
        }
    }
}
