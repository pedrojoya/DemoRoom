package es.iessaladillo.pedrojoya.roomdemo.ui.student;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import es.iessaladillo.pedrojoya.roomdemo.data.Repository;
import es.iessaladillo.pedrojoya.roomdemo.ui.main.MainActivityViewModel;

public class StudentViewModelFactory implements ViewModelProvider.Factory {

    private final Repository repository;
    private final long studentId;

    public StudentViewModelFactory(Repository repository, long studentId) {
        this.repository = repository;
        this.studentId = studentId;
    }

    @SuppressWarnings("unchecked")
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new StudentViewModel(repository, studentId);
    }
}
