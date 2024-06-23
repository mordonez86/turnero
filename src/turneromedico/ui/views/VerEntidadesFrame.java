package turneromedico.ui.views;

import java.awt.*;
import java.util.List;
import javax.swing.*;

public abstract class VerEntidadesFrame<T> extends JFrame {
    public VerEntidadesFrame(String title, String[] columnNames, Object[][] data) {
        setTitle(title);
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    protected abstract Object[][] getData(List<T> entidades);
}
