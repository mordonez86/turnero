package turneromedico.ui.main;

import javax.swing.*;

import turneromedico.ui.panels.MedicoPanel;
import turneromedico.ui.panels.PacientePanel;
import turneromedico.ui.views.VerMedicosFrame;
import turneromedico.ui.views.VerPacientesFrame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {
    private JFrame parentFrame;

    public MenuPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 20, 10, 20); // Márgenes de los botones

        JButton btnCargarMedico = new JButton("Cargar Médico");
        btnCargarMedico.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new MedicoPanel(parentFrame));
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        JButton btnCargarPaciente = new JButton("Cargar Paciente");
        btnCargarPaciente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new PacientePanel(parentFrame));
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });

        JButton btnVerMedicos = new JButton("Ver Médicos");
        btnVerMedicos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerMedicosFrame();
            }
        });

        JButton btnVerPacientes = new JButton("Ver Pacientes");
        btnVerPacientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new VerPacientesFrame();
            }
        });

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        add(btnCargarMedico, gbc);
        add(btnCargarPaciente, gbc);
        add(btnVerMedicos, gbc);
        add(btnVerPacientes, gbc);
        add(btnSalir, gbc);

        JLabel labelInfo = new JLabel("Sistema Turnero Medico", SwingConstants.CENTER);
        labelInfo.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.fill = GridBagConstraints.BOTH;
        add(labelInfo, gbc);
    }
}
