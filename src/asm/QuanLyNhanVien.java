/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package asm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

/**
 *
 * @author admin
 */
public class QuanLyNhanVien implements NhanVienInter {

    ArrayList<NhanVien> listNhanVien = new ArrayList<>();
    private static final String P_EMAIL = "^[A-Za-z0-9+_.-]+@(.+)$";

    @Override
    public ArrayList<NhanVien> fakeData() {
        listNhanVien.add(new NhanVien("NV01", "Nguyễn Hữu Nghĩa", "23", "nghianhph46340@gmail.com", 9999999.87878));
        listNhanVien.add(new NhanVien("NV02", "Hoàng Thọ Chính", "18", "chinh12345@gmail.com", 922999.87878));
        listNhanVien.add(new NhanVien("NV03", "Phạm Thị Quỳnh Thu", "21", "Thuptq@gmail.com", 978999.87878));
        listNhanVien.add(new NhanVien("NV04", "Phùn Văn Lềnh", "20", "lenhpvph46331@gmail.com", 6799999.87878));
        return listNhanVien;
    }

    ArrayList<NhanVien> getListNhanVien() {
        return listNhanVien;
    }

    ArrayList<NhanVien> search(String maCanTim) {
        ArrayList<NhanVien> listResult = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien) {
            if (nhanVien.getMaNhanVien().equals(maCanTim)) {
                listResult.add(nhanVien);
            }
        }
        return listResult;
    }

    public String ghiFile(String fn) {
        File file = new File(fn);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileOutputStream fos = new FileOutputStream(file); ObjectOutputStream oos = new ObjectOutputStream(fos)) {
                for (NhanVien nhanVien : listNhanVien) {
                    oos.writeObject(nhanVien);
                }
            }
            return "Ghi thành công";
        } catch (Exception e) {
            return "Ghi thất bại";
        }
    }

    public String docFile(String fn) {
        File file = new File(fn);
        try {
            if (!file.exists()) {
                return "File không tồn tại";
            }
            try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis)) {
                while (fis.available() > 0) {
                    listNhanVien.add((NhanVien) ois.readObject());
                }
            }
            return "Đọc thành công";
        } catch (Exception e) {
            return "Đọc thất bại";
        }
    }

    Boolean addNhanVien(NhanVien nhanVien) {
        listNhanVien.add(nhanVien);
        return true;
    }

    Boolean checkma(NhanVien nhanVien1) {
        for (NhanVien nhanVien : listNhanVien) {
            if (nhanVien.getMaNhanVien().equals(nhanVien1.getMaNhanVien())) {
                return true;
            }
        }
        return false;
    }

    Boolean capNhatSave(int vitri, NhanVien nhanVienNew) {
        if (vitri >= 0 && vitri < listNhanVien.size()) {
            NhanVien nhanVienCu = listNhanVien.get(vitri);

            if (nhanVienCu.getMaNhanVien().equals(nhanVienNew.getMaNhanVien())
                    && nhanVienCu.getHoVaTen().equals(nhanVienNew.getHoVaTen())
                    && nhanVienCu.getTuoi().equals(nhanVienNew.getTuoi())
                    && nhanVienCu.getEmail().equals(nhanVienNew.getEmail())
                    && Objects.equals(nhanVienCu.getLuong(), nhanVienNew.getLuong())) {

                return false;
            }

            listNhanVien.set(vitri, nhanVienNew);
            return true;
        } else {
            return false;
        }
    }

    public static Boolean checkEmail(String email) {
        if (email == null) {
            return false;
        }
        Pattern pattern = Pattern.compile(P_EMAIL);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    ArrayList<NhanVien> delete(String maNV) {
        ArrayList<NhanVien> listSauKhiXoa = new ArrayList<>();
        for (NhanVien nhanVien : listNhanVien) {
            if (nhanVien.getMaNhanVien().equals(maNV)) {
                listSauKhiXoa.remove(nhanVien);
            } else {
                listSauKhiXoa.add(nhanVien);
            }
        }

        return listSauKhiXoa;
    }

}
