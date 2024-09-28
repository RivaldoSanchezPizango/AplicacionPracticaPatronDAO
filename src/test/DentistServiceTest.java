package test;

import dao.BD;
import dao.impl.DentistDaoH2;
import model.Dentist;
import org.junit.jupiter.api.Test;
import service.DentistService;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DentistServiceTest {

    DentistService dentistService = new DentistService(new DentistDaoH2());

    @Test
    void save() {
        BD.createTables();
        Dentist dentist = new Dentist();
        dentist.setRegistration(555);
        dentist.setName("Oiler");
        dentist.setLastName("Sanchez");

        dentistService.save(dentist);

        assertNotNull(dentist.getId());
    }

    @Test
    void findByid() {

        Dentist dentist = dentistService.findByid(1);
        assertNotNull(dentist);

    }

    @Test
    void update() {

        Dentist dentist = new Dentist();
        dentist.setId(1);
        dentist.setName("wilfredo jose");
        dentist.setLastName("Lozano");
        dentist.setRegistration(2321);
        dentistService.update(dentist);

        assertNotNull(dentist.getId());

    }

    @Test
    void delete() {

        Dentist dentistDeleted = dentistService.findByid(2);

        assertNull(dentistDeleted);

    }

    @Test
    void findAll() {

        List<Dentist> dentistList = dentistService.findAll();

        assertTrue(dentistList.size() > 0);

    }
}