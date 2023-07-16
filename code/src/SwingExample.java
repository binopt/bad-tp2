import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingExample {

    public static void main(String[] args) {
        // Membuat frame
        JFrame frame = new JFrame("Contoh Swing");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Membuat label
        JLabel label = new JLabel("Halo, ini contoh Swing");

        // Membuat tombol
        JButton button = new JButton("Klik Saya");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Tombol telah diklik!");
            }
        });

        // Menambahkan komponen ke frame
        JPanel panel = new JPanel();
        panel.add(label);
        panel.add(button);
        frame.add(panel);

        // Mengatur ukuran frame
        frame.setSize(300, 200);
        frame.setVisible(true);
    }
}
