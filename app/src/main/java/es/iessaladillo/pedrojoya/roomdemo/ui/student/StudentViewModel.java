package es.iessaladillo.pedrojoya.roomdemo.ui.student;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import es.iessaladillo.pedrojoya.roomdemo.data.Repository;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

public class StudentViewModel extends ViewModel {

    private final long studentId;
    private final Repository repository;
    private LiveData<Student> student;

    public StudentViewModel(Repository repository, long studentId) {
        this.repository = repository;
        this.studentId = studentId;
        if (studentId > 0) {
            student = repository.queryStudent(studentId);
        }
    }

    public LiveData<Student> getStudent() {
        return student;
    }

    public void insertStudent(Student student) {
        repository.insertStudent(student);
    }

    public void updateStudent(Student student) {
        repository.updateStudent(student);
    }
}
