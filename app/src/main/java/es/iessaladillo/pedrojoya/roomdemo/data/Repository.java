package es.iessaladillo.pedrojoya.roomdemo.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

public interface Repository {

    LiveData<List<Student>> queryAllStudents();
    void insertStudent(Student student);
    void deleteStudent(Student student);
    LiveData<Student> queryStudent(long studentId);
    void updateStudent(Student student);
}
