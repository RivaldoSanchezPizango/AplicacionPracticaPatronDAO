package dao.impl;

import dao.BD;
import dao.IDao;
import model.Dentist;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DentistDaoH2 implements IDao<Dentist> {

    private static final Logger LOGGER = Logger.getLogger(DentistDaoH2.class);

    private  static final  String SQL_INSERT = "INSERT INTO DENTIST (REGISTRATION, NAME, LASTNAME)" +
            " VALUES(?,?,?)";

    private static final String SQL_SELECT = "SELECT * FROM DENTIST WHERE ID = ?";

    private static final String SQL_UPDATE = "UPDATE DENTIST SET REGISTRATION=?, NAME=?, LASTNAME=? " +
            "WHERE ID=?";

    private static final String  SQL_DELETE = "DELETE FROM DENTIST WHERE ID=?";

    private static final String SQL_SELECT_ALL = "SELECT * FROM DENTIST";

    @Override
    public Dentist save(Dentist dentist) {

        Connection connection = null;

        try {

            LOGGER.info("Se inicio una operacion de guardado de odontólogo.");
            // Conectar a la BD
            connection = BD.getConnection();
            // Insertar valores
            PreparedStatement psInsert = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setInt(1, dentist.getRegistration());
            psInsert.setString(2,dentist.getName());
            psInsert.setString(3, dentist.getLastName());
            psInsert.execute();

            ResultSet rs = psInsert.getGeneratedKeys();
            while (rs.next()) {
                dentist.setId(rs.getInt(1));
                LOGGER.info("Este es el odontólogo que se guardo: " + dentist);
            }

        } catch (Exception e) {
            LOGGER.error("ERROR: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dentist;
    }

    @Override
    public Dentist findById(Integer id) {

        Connection connection = null;
        LOGGER.info("Iniciando la bsuqueda de un odontologo");
        Dentist dentist = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psSelect = connection.prepareStatement(SQL_SELECT);
            psSelect.setInt(1, id);

            ResultSet rs = psSelect.executeQuery();
            while (rs.next()) {
                dentist = new Dentist(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4));
                LOGGER.info("Consultamos el odontologo con id: " + dentist.getId() +
                        " es: " + dentist);
            }


        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        }finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }
        return dentist;
    }

    @Override
    public void update(Dentist dentist) {

        LOGGER.info("Iniciando la actualizacion de un odontólogo");
        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psUpdate = connection.prepareStatement(SQL_UPDATE);
            psUpdate.setInt(1, dentist.getRegistration());
            psUpdate.setString(2, dentist.getName());
            psUpdate.setString(3, dentist.getLastName());
            psUpdate.setInt(4, dentist.getId());
            psUpdate.execute();

        } catch (Exception e) {
            LOGGER.error("Error" + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public void delete(Integer id) {

        Connection connection = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psDelete = connection.prepareStatement(SQL_DELETE);

            psDelete.setInt(1, id);
            psDelete.execute();

            LOGGER.warn("Cudiado se elimino el odontologo con id: " + id);

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<Dentist> findAll() {

        Connection connection = null;
        LOGGER.info("Iniciando la busqueda de todos los odontólogos");

        List<Dentist> dentistList = new ArrayList<>();
        Dentist dentist = null;

        try {

            connection = BD.getConnection();
            PreparedStatement psSelectAll = connection.prepareStatement(SQL_SELECT_ALL);

            // Guardamos en un resulset la consulta de BD
            ResultSet rs = psSelectAll.executeQuery();

            while (rs.next()) {
                // Guardamos esa consulta proveniente del rs en un objeto en java
                dentist = new Dentist(rs.getInt(1), rs.getInt(2),
                        rs.getString(3), rs.getString(4));

                //Guardar los odontologos en la lista
                dentistList.add(dentist);
                LOGGER.info("Encontramos los odontologos con ID: " + dentist.getId() +
                        " ,matricula: " + dentist.getRegistration() + " ,nombre: " + dentist.getName() +
                        " ,apellido: " + dentist.getLastName());
            }

        } catch (Exception e) {
            LOGGER.error("Error: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Error: " + e.getMessage());
                e.printStackTrace();
            }
        }

        return dentistList;
    }

}
