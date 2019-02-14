package es.iessaladillo.pedrojoya.roomdemo.ui.main;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;
import es.iessaladillo.pedrojoya.roomdemo.R;
import es.iessaladillo.pedrojoya.roomdemo.data.local.entity.Student;
import es.iessaladillo.pedrojoya.roomdemo.ui.student.StudentActivity;

public class MainActivityAdapter extends ListAdapter<Student, MainActivityAdapter.ViewHolder> {

    protected MainActivityAdapter() {
        super(new DiffUtil.ItemCallback<Student>() {
            @Override
            public boolean areItemsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return oldItem.getId() == newItem.getId();
            }

            @Override
            public boolean areContentsTheSame(@NonNull Student oldItem, @NonNull Student newItem) {
                return TextUtils.equals(oldItem.getName(), newItem.getName()) &&
                    oldItem.getAge() == newItem.getAge();
            }
        });
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_main_item,
            parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    @Override
    public Student getItem(int position) {
        return super.getItem(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView lblName;
        private final TextView lblAge;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            lblName = ViewCompat.requireViewById(itemView, R.id.lblName);
            lblAge = ViewCompat.requireViewById(itemView, R.id.lblAge);
            itemView.setOnClickListener(v -> navigateToStudent(getItem(getAdapterPosition())));
        }

        private void navigateToStudent(Student student) {
            StudentActivity.start(itemView.getContext(), student.getId());
        }

        void bind(Student student) {
            lblName.setText(student.getName());
            lblAge.setText(String.valueOf(student.getAge()));
        }
    }
}
