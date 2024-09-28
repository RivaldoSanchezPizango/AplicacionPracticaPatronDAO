import dao.BD;
import dao.impl.DentistDaoH2;
import model.Dentist;
import service.DentistService;

public class Main {
    public static void main(String[] args) {

        DentistService dentistService = new DentistService(new DentistDaoH2());

        // CREAMOS LAS TABLAS
        BD.createTables();

        // CREAMOS LOS OBJETOS
        Dentist dentist1 = new Dentist(132, "Lidia", "Pizango");
        Dentist dentist2 = new Dentist(345, "Llud", "Sanchez");
        Dentist dentist3 = new Dentist(543, "Nelia", "Lozano");

        // PERSISTER LOS OBJETOS EN LA BD (GUARDARLOS)
        dentistService.save(dentist1);
        dentistService.save(dentist2);
        dentistService.save(dentist3);

        // CONSULTAR UN ODONTOLOGO POR ID
        int id = 2;
        dentistService.findByid(id);

        // ACTUALIZAR ALGUNOS ATRIBUTOS DE UN ODONTOLOGO
        String updateName = "Loisi";
        dentist1.setName(updateName);

        dentistService.update(dentist1);

        System.out.println("El nombre actualizado es: " + dentist1.getName());

        // BORRAR ALGUNO DE LOS REGISTROS DE LA TABLA
        int idDelete = 2;
        dentistService.delete(idDelete);

        // CONSULTAR TODOS LS REGISTROS RESTANTES DE LA TABLA
        dentistService.findAll();

    }
}
