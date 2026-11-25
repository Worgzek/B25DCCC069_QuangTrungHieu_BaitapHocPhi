# Bài Tập Quản Lý Học Phí Sinh Viên (Java OOP)

Repository chứa lời giải chi tiết cho 4 câu bài tập thực hành môn Lập trình Hướng đối tượng (Java), tuân thủ đầy đủ các chuẩn:
- Mỗi câu nộp bằng 1 file `Main.java`, không khai báo `package`.
- Đọc từ `System.in`, xuất chuẩn ra màn hình `System.out`.
- Ứng dụng các nguyên lý OOP: Kế thừa, Lớp trừu tượng (`abstract class`), Đa hình qua `interface`, Xử lý ngoại lệ tự định nghĩa (`Custom Exception`), Quản lý danh sách bằng `ArrayList`.

---

## 📁 Cấu trúc thư mục

```text
.
├── cau1/
│   └── Main.java   # Chuẩn hóa thông tin sinh viên (họ tên, ngày sinh)
├── cau2/
│   └── Main.java   # Tính học phí theo hệ đào tạo (abstract class SinhVien)
├── cau3/
│   └── Main.java   # Xử lý ngoại lệ DuLieuKhongHopLeException, sắp xếp học phí
├── cau4/
│   └── Main.java   # Áp dụng interface MienGiam, thống kê theo hệ đào tạo
├── .gitignore
└── README.md
```

---

## ⚙️ Yêu cầu môi trường

- **Java Development Kit (JDK)**: Phiên bản 8 trở lên (Khuyến nghị JDK 17 hoặc JDK 21).
- Kiểm tra cài đặt Java trên máy:
  ```bash
  java -version
  javac -version
  ```

---

## 🚀 Hướng dẫn biên dịch và chạy

### Cách 1: Chạy bằng Terminal / Command Prompt

Di chuyển vào thư mục gốc của dự án (`ktra`):

#### 🔹 Câu 1: Chuẩn hóa thông tin sinh viên
```bash
# Biên dịch
javac cau1/Main.java

# Chạy
java -cp cau1 Main
```
**Input mẫu:**
```text
3
B23DCCN001
nguYEN    van    an
2/5/2005
B23DCCN002
TRAN THI BINH
15/11/2005
B23DCCN003
le chi
9/12/2004
```

---

#### 🔹 Câu 2: Tính học phí theo hệ đào tạo
```bash
# Biên dịch
javac cau2/Main.java

# Chạy
java -cp cau2 Main
```
**Input mẫu:**
```text
4
CQ B23DCCN001 18
CLC B23DCCN002 20
LT B23DCCN003 15
CQ B23DCCN004 21
```

---

#### 🔹 Câu 3: Quản lý danh sách học phí & xử lý ngoại lệ
```bash
# Biên dịch
javac cau3/Main.java

# Chạy
java -cp cau3 Main
```
**Input mẫu:**
```text
8
CQ B23DCCN001 18
CLC B23DCCN002 20
LT B23DCCN003 15
CQ B23DCCN004 abc
VB B23DCCN005 10
CLC B23DCCN006 35
CQ B23DCCN001 12
CLC B23DCCN007 12
```

---

#### 🔹 Câu 4: Miễn giảm học phí và thống kê theo hệ
```bash
# Biên dịch
javac cau4/Main.java

# Chạy
java -cp cau4 Main
```
**Input mẫu:**
```text
5
CQ B23DCCN001 18
CLC B23DCCN002 20
LT B23DCCN003 15
CQ B23DCCN004 21
CLC B23DCCN005 4
4
B23DCCN001 CS
B23DCCN003 HN
B23DCCN009 HB
B23DCCN005 HB
```

---

### Cách 2: Chạy trên Visual Studio Code (VS Code)

1. Mở thư mục dự án trong VS Code (`File` -> `Open Folder...`).
2. Cài đặt extension: **Extension Pack for Java** (hoặc **Code Runner**).
3. Mở file `Main.java` của câu cần chạy (ví dụ `cau1/Main.java`).
4. Nhấn nút **Run** (hình tam giác ở góc trên bên phải) hoặc phím tắt `F5` / `Ctrl + F5`.
5. Nhập input vào tab **Terminal** phía dưới và nhấn `Enter`.

---

### Cách 3: Chạy trên IntelliJ IDEA

1. Mở dự án trong IntelliJ IDEA (`Open` -> chọn thư mục dự án).
2. Tìm đến file `Main.java` của từng câu.
3. Nhấp chuột phải vào file hoặc class `Main` -> Chọn **Run 'Main.main()'**.
4. Dán dữ liệu test vào cửa sổ **Run/Console**.

---

## 📌 Tóm tắt quy tắc tính toán

- **Đơn giá tín chỉ**: `450.000 VNĐ / tín chỉ`.
- **Học phí theo hệ**:
  - `CQ` (Chính quy): `số tín chỉ × 450.000`
  - `CLC` (Chất lượng cao): `số tín chỉ × 450.000 × 1.5`
  - `LT` (Liên thông): `số tín chỉ × 450.000 + 2.000.000` (phí quản lý)
- **Chính sách miễn giảm**:
  - `CS` (Con chính sách): Giảm 50% học phí.
  - `HN` (Hộ nghèo): Giảm 70% học phí.
  - `HB` (Học bổng): Giảm `min(học phí, 3.000.000)`.
- **Thứ tự in thống kê (Câu 4)**: Cố định theo thứ tự `CQ` -> `CLC` -> `LT` (chỉ in các hệ có sinh viên).
