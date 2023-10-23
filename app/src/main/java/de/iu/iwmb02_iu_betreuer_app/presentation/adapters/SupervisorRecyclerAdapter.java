package de.iu.iwmb02_iu_betreuer_app.presentation.adapters;

import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

import de.iu.iwmb02_iu_betreuer_app.R;
import de.iu.iwmb02_iu_betreuer_app.data.dao.FirebaseStorageDao;
import de.iu.iwmb02_iu_betreuer_app.model.Supervisor;
import de.iu.iwmb02_iu_betreuer_app.util.Callback;

public class SupervisorRecyclerAdapter extends FirestoreRecyclerAdapter<Supervisor, SupervisorRecyclerAdapter.SupervisorViewHolder> {

    private  final FirebaseStorageDao firebaseStorageDao;
    public SupervisorRecyclerAdapter(@NonNull FirestoreRecyclerOptions<Supervisor> options) {
        super(options);
        firebaseStorageDao = FirebaseStorageDao.getInstance();
    }

    @NonNull
    @Override
    public SupervisorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supervisor,parent,false);
        return new SupervisorViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull SupervisorViewHolder holder, int position, @NonNull Supervisor supervisor) {
        holder.txtSupervisorName.setText(supervisor.getFullName());
        System.out.println(supervisor.getProfilePictureUrl());

        firebaseStorageDao.downloadImage(supervisor.getProfilePictureUrl(), new Callback<Bitmap>() {
            @Override
            public void onCallback(Bitmap bmp) {
                holder.imgSupervisorProfilePicture.setImageBitmap(bmp);
            }
        });
    }

    public class SupervisorViewHolder extends RecyclerView.ViewHolder{
        private final TextView txtSupervisorName;
        private final ImageView imgSupervisorProfilePicture;

        public SupervisorViewHolder(@NonNull View itemView) {
            super(itemView);
            txtSupervisorName = itemView.findViewById(R.id.supervisorNameTextView);
            imgSupervisorProfilePicture = itemView.findViewById(R.id.supervisorImageView);
        }
    }
}
