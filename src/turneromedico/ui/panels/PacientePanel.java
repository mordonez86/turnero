package turneromedico.ui.panels;

import turneromedico.model.Paciente;
import turneromedico.service.PacienteService;
import turneromedico.exceptions.ServiceException;
import turneromedico.ui.main.MenuPanel;
import turneromedico.util.LimpiarCampos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PacientePanel extends JPanel {
    private PacienteService pacienteService;
    private JTextField nombreField, apellidoField, telefonoField, emailField, historiaClinicaField;
    private JFrame parentFrame;

    public PacientePanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        try {
            this.pacienteService = new PacienteService();
        } catch (ServiceException e) {
            mostrarError("Error al inicializar el servicio de pacientes: " + e.getMessage());
        }

        setLayout(new GridLayout(7, 2));

        // Campos de texto para los atributos del paciente
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

        add(new JLabel(" Historia Clínica:"));
        historiaClinicaField = new JTextField();
        add(historiaClinicaField);

        // Botón para guardar el paciente
        JButton guardarButton = new JButton(" Guardar");
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPaciente();
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

    private void guardarPaciente() {
        String nombre = nombreField.getText();
        String apellido = apellidoField.getText();
        String telefono = telefonoField.getText();
        String email = emailField.getText();
        String historiaClinica = historiaClinicaField.getText();

        Paciente paciente = new Paciente(null, nombre, apellido, telefono, email, historiaClinica);

        try {
            pacienteService.crear(paciente);
            JOptionPane.showMessageDialog(this, " Paciente creado exitosamente.");
            // Limpiar campos después de guardar
            LimpiarCampos.limpiarCampos(this);
        } catch (ServiceException e) {
            mostrarError("Error al crear el paciente: " + e.getMessage());
        }
    }

    private void mostrarError(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
