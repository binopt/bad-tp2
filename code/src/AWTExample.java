import javax.swing.*;
import java.awt.Button;
import java.awt.Frame;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AWTExample {

    public static void main(String[] args) {
        // Membuat frame
        Frame frame = new Frame("Contoh AWT");

        // Membuat label
        Label label = new Label("Halo, ini contoh AWT");

        // Membuat tombol
        Button button = new Button("Klik Saya");
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
