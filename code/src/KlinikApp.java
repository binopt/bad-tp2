import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class KlinikApp extends JFrame {
    private JTextField txtNama, txtAlamat, txtNik, txtTglLahir;
    private JButton btnTambah, btnUpdate, btnHapus, btnDaftar, btnKeluar;
    private JTable table;
    private DefaultTableModel tableModel;
    private int currentRow = -1;

    public KlinikApp() {
        setTitle("Aplikasi Klinik");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        // Membuat input fields
        JLabel lblNama = new JLabel("Nama:");
        txtNama = new JTextField(20);
        JLabel lblAlamat = new JLabel("Alamat:");
        txtAlamat = new JTextField(50);
        JLabel lblNik = new JLabel("NIK:");
        txtNik = new JTextField(15);
        JLabel lblTglLahir = new JLabel("Tanggal Lahir (YYYY-MM-DD):");
        txtTglLahir = new JTextField(10);

        // Membuat tombol
        btnTambah = new JButton("Tambah");
        btnUpdate = new JButton("Update");
        btnHapus = new JButton("Hapus");
        btnDaftar = new JButton("Daftar Pasien");
        btnKeluar = new JButton("Keluar");

        // Membuat tabel
        tableModel = new DefaultTableModel();
        tableModel.addColumn("No");
        tableModel.addColumn("Nama Pasien");
        tableModel.addColumn("NIK");
        tableModel.addColumn("Tanggal Lahir");
        tableModel.addColumn("Alamat");
        table = new JTable(tableModel);

        // Menambahkan komponen ke frame
        add(lblNama);
        add(txtNama);
        add(lblAlamat);
        add(txtAlamat);
        add(lblNik);
        add(txtNik);
        add(lblTglLahir);
        add(txtTglLahir);
        add(btnTambah);
        add(btnUpdate);
        add(btnHapus);
        add(btnDaftar);
        add(btnKeluar);
//        add(new JScrollPane(table));

        // Mengatur tampilan awal
        updateButtonState();

        // Menambahkan action listener untuk tombol-tombol
        btnTambah.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tambahPasien();
            }
        });

        btnUpdate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updatePasien();
            }
        });

        btnHapus.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                hapusPasien();
            }
        });

        btnDaftar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tampilkanDaftarPasien();
            }
        });

        btnKeluar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                currentRow = table.getSelectedRow();
                tampilkanDataPasien();
            }
        });

        pack();
        setSize(2000,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void tambahPasien() {
        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String nik = txtNik.getText();
        String tglLahir = txtTglLahir.getText();

        if (nama.isEmpty() || alamat.isEmpty() || nik.isEmpty() || tglLahir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (currentRow == -1) {
            // Tambah data baru
            Vector<Object> row = new Vector<>();
            row.add(tableModel.getRowCount() + 1);
            row.add(nama);
            row.add(nik);
            row.add(tglLahir);
            row.add(alamat);
            tableModel.addRow(row);
        } else {
            // Update data
            tableModel.setValueAt(nama, currentRow, 1);
            tableModel.setValueAt(nik, currentRow, 2);
            tableModel.setValueAt(tglLahir, currentRow, 3);
            tableModel.setValueAt(alamat, currentRow, 4);
            currentRow = -1; // Reset currentRow setelah update
        }

        resetForm();
    }

    private void updatePasien() {
        if (currentRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pasien yang ingin diupdate!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nama = txtNama.getText();
        String alamat = txtAlamat.getText();
        String nik = txtNik.getText();
        String tglLahir = txtTglLahir.getText();

        if (nama.isEmpty() || alamat.isEmpty() || nik.isEmpty() || tglLahir.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Semua field harus diisi!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Update data
        tableModel.setValueAt(nama, currentRow, 1);
        tableModel.setValueAt(nik, currentRow, 2);
        tableModel.setValueAt(tglLahir, currentRow, 3);
        tableModel.setValueAt(alamat, currentRow, 4);

        resetForm();
    }

    private void hapusPasien() {
        if (currentRow == -1) {
            JOptionPane.showMessageDialog(this, "Pilih data pasien yang ingin dihapus!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data pasien ini?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            tableModel.removeRow(currentRow);
            resetForm();
        }
    }

    private void tampilkanDaftarPasien() {
        JFrame daftarFrame = new JFrame("Daftar Pasien");
        daftarFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        daftarFrame.add(new JScrollPane(table));

        daftarFrame.pack();
        daftarFrame.setLocationRelativeTo(this);
        daftarFrame.setVisible(true);
    }

    private void resetForm() {
        txtNama.setText("");
        txtAlamat.setText("");
        txtNik.setText("");
        txtTglLahir.setText("");

        currentRow = -1;

        updateButtonState();
    }

    private void tampilkanDataPasien() {
        if (currentRow == -1) {
            return;
        }

        String nama = (String) tableModel.getValueAt(currentRow, 1);
        String nik = (String) tableModel.getValueAt(currentRow, 2);
        String tglLahir = (String) tableModel.getValueAt(currentRow, 3);
        String alamat = (String) tableModel.getValueAt(currentRow, 4);

        txtNama.setText(nama);
        txtNik.setText(nik);
        txtTglLahir.setText(tglLahir);
        txtAlamat.setText(alamat);

        updateButtonState();
    }

    private void updateButtonState() {
        int rowCount = tableModel.getRowCount();
        btnTambah.setEnabled(currentRow == -1);
        btnUpdate.setEnabled(rowCount > 0 && currentRow != -1);
        btnHapus.setEnabled(rowCount > 0 && currentRow != -1);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new KlinikApp();
            }
        });
    }
}
