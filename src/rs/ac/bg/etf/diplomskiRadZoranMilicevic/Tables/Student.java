package rs.ac.bg.etf.diplomskiRadZoranMilicevic.Tables;

import rs.ac.bg.etf.diplomskiRadZoranMilicevic.DB.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Student {
    private String id;
    private String name;
    private String surname;
    private String age;

    private static ArrayList<Student> studentTable = new ArrayList<>();
    private static ArrayList<Student> studentTableAnonymised = new ArrayList<>();

    public Student(String id, String name, String surname, String age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public static void addStudent(Student s){
        studentTable.add(s);
    }

    public static void addStudentAnonymised(Student s){
        studentTableAnonymised.add(s);
    }

    public static void populateTableFromDB(){
        Connection con = DB.getInstance().getConnection();
        String query = "select * from Student";
        ResultSet rs = null;

        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next() != false){
                String id = Integer.toString(rs.getInt(1));
                String name = rs.getString(2);
                String surname = rs.getString(3);
                String age = Integer.toString(rs.getInt(4));
                Student s = new Student(id, name, surname, age);
                studentTable.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
