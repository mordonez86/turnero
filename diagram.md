@startuml

package com.tuempresa.turneromedico.dao {
    interface CRUD {
        +crear(T t)
        +leer(String id) T
        +actualizar(T t)
        +eliminar(String id)
        +listar() List~T~
    }

    class MedicoDAOImpl {
        +crear(Medico medico)
        +leer(String id) Medico
        +actualizar(Medico medico)
        +eliminar(String id)
        +listar() List~Medico~
    }

    class PacienteDAOImpl {
        +crear(Paciente paciente)
        +leer(String id) Paciente
        +actualizar(Paciente paciente)
        +eliminar(String id)
        +listar() List~Paciente~
    }
}

package com.tuempresa.turneromedico.model {
    abstract class Persona {
        String id
        String nombre
        String apellido
        String telefono
        String email

        +getId() String
        +setId(String id)
        +getNombre() String
        +setNombre(String nombre)
        +getApellido() String
        +setApellido(String apellido)
        +getTelefono() String
        +setTelefono(String telefono)
        +getEmail() String
        +setEmail(String email)
        +mostrarInformacion()
    }

    class Medico {
        String especialidad

        +getEspecialidad() String
        +setEspecialidad(String especialidad)
        +mostrarInformacion()
    }

    class Paciente {
        String historiaClinica

        +getHistoriaClinica() String
        +setHistoriaClinica(String historiaClinica)
        +mostrarInformacion()
    }
}

package com.tuempresa.turneromedico.service {
    class MedicoService {
        MedicoDAOImpl medicoDAO

        +crear(Medico medico)
        +leer(String id) Medico
        +actualizar(Medico medico)
        +eliminar(String id)
        +listar() List~Medico~
    }

    class PacienteService {
        PacienteDAOImpl pacienteDAO

        +crear(Paciente paciente)
        +leer(String id) Paciente
        +actualizar(Paciente paciente)
        +eliminar(String id)
        +listar() List~Paciente~
    }
}

package com.tuempresa.turneromedico.ui {
    class MainFrame {
        +MainFrame()
    }

    class MenuPanel {
        JFrame parentFrame

        +MenuPanel(JFrame parentFrame)
    }

    class MedicoPanel {
        MedicoService medicoService
        JTextField idField
        JTextField nombreField
        JTextField apellidoField
        JTextField telefonoField
        JTextField emailField
        JTextField especialidadField
        JFrame parentFrame

        +MedicoPanel(JFrame parentFrame)
        +guardarMedico()
        +limpiarCampos()
    }

    class PacientePanel {
        PacienteService pacienteService
        JTextField idField
        JTextField nombreField
        JTextField apellidoField
        JTextField telefonoField
        JTextField emailField
        JTextField historiaClinicaField
        JFrame parentFrame

        +PacientePanel(JFrame parentFrame)
        +guardarPaciente()
        +limpiarCampos()
    }

    class VerMedicosFrame {
        MedicoService medicoService

        +VerMedicosFrame()
    }

    class VerPacientesFrame {
        PacienteService pacienteService

        +VerPacientesFrame()
    }
}

CRUD <|-- MedicoDAOImpl
CRUD <|-- PacienteDAOImpl

Persona <|-- Medico
Persona <|-- Paciente

MedicoDAOImpl <|-- MedicoService
PacienteDAOImpl <|-- PacienteService

MainFrame --> MenuPanel
MainFrame --> MedicoPanel
MainFrame --> PacientePanel
MainFrame --> VerMedicosFrame
MainFrame --> VerPacientesFrame

@enduml
