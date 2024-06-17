package turneromedico.model;

public class Paciente extends Persona {
    private String historiaClinica;

    public Paciente(Integer id, String nombre, String apellido, String telefono, String email, String historiaClinica) {
        super(id, nombre, apellido, telefono, email);
        this.historiaClinica = historiaClinica;
    }

    public String getHistoriaClinica() { return historiaClinica; }
    public void setHistoriaClinica(String historiaClinica) { this.historiaClinica = historiaClinica; }

    @Override
    public void mostrarInformacion() {
        System.out.println("Id: " + getId() + " Paciente: " + getNombre() + " " + getApellido() + " - Historia Clínica: " + historiaClinica);
    }
}
