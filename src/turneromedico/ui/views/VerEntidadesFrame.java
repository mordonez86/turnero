package turneromedico.ui.views;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class VerEntidadesFrame<T> extends JFrame {
    public VerEntidadesFrame(String title, String[] columnNames, List<T> entidades) {
        setTitle(title);
        setSize(800, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        Object[][] data = getData(entidades);

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);
        setVisible(true);
    }

    protected abstract Object[][] getData(List<T> entidades);
}
