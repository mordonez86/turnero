package turneromedico.ui.main;

import javax.swing.*;

import java.awt.*;

public class MainFrame extends JFrame {
    public MainFrame() {
        setTitle("Turnero Médico");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear el panel de menú y añadirlo al frame
        MenuPanel menuPanel = new MenuPanel(this);
        add(menuPanel);

        // Configurar el layout
        setLayout(new BorderLayout());
        add(menuPanel, BorderLayout.CENTER);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame mainFrame = new MainFrame();
            mainFrame.setVisible(true);
        });
    }
}
