package turneromedico.ui.panels;

import turneromedico.model.Medico;
import turneromedico.service.MedicoService;
import turneromedico.ui.main.MenuPanel;
import turneromedico.util.LimpiarCampos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MedicoPanel extends JPanel {
    private MedicoService medicoService;
    private JTextField nombreField, apellidoField, telefonoField, emailField, especialidadField;
    private JFrame parentFrame;

    public MedicoPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.medicoService = new MedicoService();

        setLayout(new GridLayout(7, 2));

        // Campos de texto para los atributos del médico
        add(new JLabel(" Nombre:"));
        nombreField = new JTextField();
        add(nombreField);

        add(new JLabel(" Apellido:"));
        apellidoField = new JTextField();
        add(apellidoField);

        add(new JLabel(" Teléfono:"));
        telefonoField = new JTextField();
        add(telefonoField);

        add(new JLabel(" Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel(" Especialidad:"));
        especialidadField = new JTextField();
        add(especialidadField);

        // Botón para guardar el médico
        JButton guardarButton = new JButton(" Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarMedico();
            }
        });
        add(guardarButton);

        // Botón para regresar al menú
        JButton regresarButton = new JButton(" Regresar");
        regresarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentFrame.setContentPane(new MenuPanel(parentFrame));
                parentFrame.invalidate();
                parentFrame.validate();
            }
        });
        add(regresarButton);
    }

    private void guardarMedico() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();
        String especialidad = especialidadField.getText();

        Medico medico = new Medico(null, nombre, apellido, telefono, email, especialidad);
        medicoService.crear(medico);
        JOptionPane.showMessageDialog(this, " Médico creado exitosamente.");

        // Limpiar campos después de guardar
        LimpiarCampos.limpiarCampos(this);
    }
}
