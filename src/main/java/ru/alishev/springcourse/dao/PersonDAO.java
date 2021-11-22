package ru.alishev.springcourse.dao;

import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/first_db";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "1235813wg";

    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public List<Person> index() throws SQLException {
        List<Person> people = new ArrayList<>();


        PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM person");
        //String SQL = "SELECT * FROM Person";
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Person person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setGamerRole(resultSet.getString("gamerRole"));
            person.setImages(resultSet.getString("images"));

            people.add(person);
        }

        return people;
    }


    public Person show(int id) throws SQLException {
        Person person = null;


            PreparedStatement preparedStatement =
                    connection.prepareStatement("SELECT * FROM person WHERE id=?");

            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            resultSet.next();

            person = new Person();

            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setImages(resultSet.getString("images"));

        return person;
    }


    public void save(Person person) throws SQLException {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO person VALUES(1, ?, ?, ?)");

            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2, person.getAge());

            preparedStatement.executeUpdate();
        }

    public void update(int id, Person updatedPerson) throws SQLException {

            PreparedStatement preparedStatement =
                    connection.prepareStatement("UPDATE person SET name=?, age=? WHERE id=?");

            preparedStatement.setString(1, updatedPerson.getName());
            preparedStatement.setInt(2, updatedPerson.getAge());
            preparedStatement.setInt(3, id);

            preparedStatement.executeUpdate();

    }

    public void delete(int id) throws SQLException {

           PreparedStatement preparedStatement =
                   connection.prepareStatement("DELETE FROM person WHERE id=?");

            preparedStatement.setInt(1, id);

            preparedStatement.executeUpdate();
        }

    }

