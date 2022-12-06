package com.amiel.mission2;


import java.util.LinkedList;
import java.util.List;

public class Model {
    private static final Model _instance = new Model();

    public static Model instance(){
        return _instance;
    }

    private Model(){

        addStudent(new Student("Aharon", "205967841","",false,"0528886341","Afula"));
        addStudent(new Student("Barney", "205967842", "" , false, "0528886342", "Bat Yam"));
        addStudent(new Student("Charlie","205967843", "", false,"0528886343","Chicago"));
        addStudent(new Student("David","205967844","",false,"0528886344","Dimona"));
        addStudent(new Student("Edgar","205967845","",false,"0528886345","Eilat"));
        addStudent(new Student("Felix","205967846","",false,"0528886346","Frankfurt"));
        addStudent(new Student("Gabriel","205967847","",false,"0528886347","Givatayim"));
        addStudent(new Student("Henry","205967848","",false,"0528886348","Haifa"));
        addStudent(new Student("Isaac","205967849","",false,"0528886349","Istanbul"));

    }

    List<Student> data = new LinkedList<>();
    public List<Student> getAllStudents(){
        return data;
    }

    public void addStudent(Student st){
        data.add(st);
    }
}