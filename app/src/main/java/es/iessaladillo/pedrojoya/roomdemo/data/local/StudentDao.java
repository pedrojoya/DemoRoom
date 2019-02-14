package es.iessaladillo.pedrojoya.roomdemo.data.local;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

@Dao
public interface StudentDao {

    @Query("SELECT * FROM students")
    LiveData<List<Student>> queryAllStudents();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertStudent(Student student);

    @Delete
    void deleteStudent(Student student);

    @Query("SELECT * FROM students WHERE id = :studentId")
    LiveData<Student> queryStudent(long studentId);

    @Update(onConflict = OnConflictStrategy.IGNORE)
    void updateStudent(Student student);
}
