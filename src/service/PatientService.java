package service;

import dao.IDao;
import model.Patient;

import javax.swing.plaf.SplitPaneUI;
import java.util.List;

public class PatientService {

    private IDao<Patient> patientIDao;

    public PatientService(IDao<Patient> patientIDao) {
        this.patientIDao = patientIDao;
    }

    public Patient save (Patient patient) {
        return patientIDao.save(patient);
    }

    public Patient findById(Integer id) {
        return patientIDao.findById(id);
    }

    public void update (Patient patient) {
        patientIDao.update(patient);
    }

    public void delete (Integer id) {
        patientIDao.delete(id);
    }

    public List<Patient> findAll() {
        return patientIDao.findAll();
    }

}
