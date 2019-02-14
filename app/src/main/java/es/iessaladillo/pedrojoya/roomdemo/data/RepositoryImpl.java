package es.iessaladillo.pedrojoya.roomdemo.data;

import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;
import es.iessaladillo.pedrojoya.roomdemo.data.local.StudentDao;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

public class RepositoryImpl implements Repository {

    private final StudentDao studentDao;

    public RepositoryImpl(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    @Override
    public LiveData<List<Student>> queryAllStudents() {
        return studentDao.queryAllStudents();
    }

    @Override
    public LiveData<Student> queryStudent(long studentId) {
        return studentDao.queryStudent(studentId);
    }

    @Override
    public void insertStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.insertStudent(student));
    }

    @Override
    public void updateStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.updateStudent(student));
    }

    @Override
    public void deleteStudent(Student student) {
        AsyncTask.THREAD_POOL_EXECUTOR.execute(() -> studentDao.deleteStudent(student));
    }

}
