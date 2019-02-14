package es.iessaladillo.pedrojoya.roomdemo.ui.main;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.roomdemo.data.Repository;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

public class MainActivityViewModel extends ViewModel {

    private final Repository repository;
    private final LiveData<List<Student>> students;

    public MainActivityViewModel(Repository repository) {
        this.repository = repository;
        students = repository.queryAllStudents();
    }

    LiveData<List<Student>> getStudents() {
        return students;
    }

    void deleteStudent(Student student) {
        repository.deleteStudent(student);
    }
}
