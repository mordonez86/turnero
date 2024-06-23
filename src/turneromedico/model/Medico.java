package turneromedico.model;

public class Medico extends Persona {
    private String especialidad;

    public Medico(Integer id, String nombre, String apellido, String telefono, String email, String especialidad) {
        super(id, nombre, apellido, telefono, email);
        this.especialidad = especialidad;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public void mostrarInformacion() {
        System.out.println("Id: " + getId() + " " +
                           "Médico: " + getNombre() + " " + getApellido() + 
                           " - Especialidad: " + especialidad);
    }
}
