package es.iessaladillo.pedrojoya.roomdemo.ui.main;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.roomdemo.R;
import es.iessaladillo.pedrojoya.roomdemo.data.RepositoryImpl;
import es.iessaladillo.pedrojoya.roomdemo.data.local.AppDatabase;
import es.iessaladillo.pedrojoya.roomdemo.data.local.StudentDao;
import es.iessaladillo.pedrojoya.roomdemo.ui.student.StudentActivity;

public class MainActivity extends AppCompatActivity {

    private MainActivityViewModel viewModel;
    private MainActivityAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StudentDao studentDao = AppDatabase.getInstance(this).studentDao();
        RepositoryImpl repository = new RepositoryImpl(studentDao);
        viewModel =
            ViewModelProviders.of(this, new MainActivityViewModelFactory(repository)).get(MainActivityViewModel.class);
        setupViews();
        viewModel.getStudents().observe(this, students -> listAdapter.submitList(students));
    }

    private void setupViews() {
        RecyclerView lstStudents = ActivityCompat.requireViewById(this, R.id.lstStudents);
        listAdapter = new MainActivityAdapter();
        lstStudents.setHasFixedSize(true);
        lstStudents.setLayoutManager(new LinearLayoutManager(this));
        lstStudents.setItemAnimator(new DefaultItemAnimator());
        lstStudents.setAdapter(listAdapter);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(
            new ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT) {

                // Cuando se detecta un gesto drag & drop.
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView,
                    @NonNull RecyclerView.ViewHolder viewHolder,
                    @NonNull RecyclerView.ViewHolder target) {
                    return false;
                }

                // Cuando se detecta un gesto swipe to dismiss.
                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    // Se elimina el elemento.
                    viewModel.deleteStudent(listAdapter.getItem(viewHolder.getAdapterPosition()));
                }
            });
        // Se enlaza con el RecyclerView.
        itemTouchHelper.attachToRecyclerView(lstStudents);
        FloatingActionButton fab = ActivityCompat.requireViewById(this, R.id.fab);
        fab.setOnClickListener(v -> navigateToAddStudent());
    }

    private void navigateToAddStudent() {
        StudentActivity.start(this);
    }

}
