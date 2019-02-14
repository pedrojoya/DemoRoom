package es.iessaladillo.pedrojoya.roomdemo.ui.student;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import es.iessaladillo.pedrojoya.roomdemo.R;
import es.iessaladillo.pedrojoya.roomdemo.data.Repository;
import es.iessaladillo.pedrojoya.roomdemo.data.RepositoryImpl;
import es.iessaladillo.pedrojoya.roomdemo.data.local.AppDatabase;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;

public class StudentActivity extends AppCompatActivity {

    private static final String EXTRA_STUDENT_ID = "EXTRA_ID";

    private EditText txtName;
    private EditText txtAge;
    private Repository repository;
    private long studentId;
    private StudentViewModel viewModel;

    public static void start(Context context) {
        context.startActivity(new Intent(context, StudentActivity.class));
    }

    public static void start(Context context, long studentId) {
        context.startActivity(new Intent(context, StudentActivity.class).putExtra(EXTRA_STUDENT_ID, studentId));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        obtainArguments();
        repository = new RepositoryImpl(AppDatabase.getInstance(this).studentDao());
        viewModel = ViewModelProviders.of(this, new StudentViewModelFactory(repository,
            studentId)).get(StudentViewModel.class);
        setupViews();
        if (studentId > 0) {
            observeStudent();
        }
    }

    private void observeStudent() {
        viewModel.getStudent().observe(this, student -> showStudent(student));
    }

    private void showStudent(Student student) {
        txtName.setText(student.getName());
        txtAge.setText(String.valueOf(student.getAge()));
    }

    private void obtainArguments() {
        studentId = getIntent().getLongExtra(EXTRA_STUDENT_ID, 0);
    }

    private void setupViews() {
        txtName = ActivityCompat.requireViewById(this, R.id.txtName);
        txtAge = ActivityCompat.requireViewById(this, R.id.txtAge);
        FloatingActionButton fab = ActivityCompat.requireViewById(this, R.id.fab);
        fab.setOnClickListener(v -> {
            if (studentId == 0) {
                Student student = new Student(0, txtName.getText().toString(),
                    Integer.valueOf(txtAge.getText().toString()));
                insertStudent(student);
            } else {
                Student student = new Student(studentId, txtName.getText().toString(),
                    Integer.valueOf(txtAge.getText().toString()));
                updateStudent(student);
            }
        });
    }

    private void updateStudent(Student student) {
        viewModel.updateStudent(student);
        finish();
    }

    private void insertStudent(Student student) {
        viewModel.insertStudent(student);
        finish();
    }

}
