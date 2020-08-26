package com.example.rescue1122.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rescue1122.Alert;
import com.example.rescue1122.mapactivity.AlertDetailActivity;
import com.example.rescue1122.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

public class WorkerAlertAdapter extends RecyclerView.Adapter<WorkerAlertAdapter.WorkerAlertViewHolder> {


    Context context;
    List<Alert> userAlertList;
    private FirebaseAuth mAuth;
    private DatabaseReference userRef;

    public WorkerAlertAdapter(Context context, List<Alert> userAlertList) {
        this.userAlertList = userAlertList;
        this.context = context;
    }

    @NonNull
    @Override
    public WorkerAlertAdapter.WorkerAlertViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.post, viewGroup, false);

        mAuth = FirebaseAuth.getInstance();

        return new WorkerAlertAdapter.WorkerAlertViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final WorkerAlertViewHolder AlertViewHolder, final int i) {

        //   String messageSenderId = mAuth.getCurrentUser().getUid();
        final Alert Alerts = userAlertList.get(i);

        AlertViewHolder.latitud.setText(Alerts.getLat());
        AlertViewHolder.logitud.setText(Alerts.getLon());
        AlertViewHolder.status.setText(Alerts.getStatus());
        AlertViewHolder.title.setText(Alerts.getTitle());
        AlertViewHolder.servicetype.setText(Alerts.getServicetype());
        //AlertViewHolder.amount_text.setText(Alerts.getAlert_amount());

        AlertViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String lat = userAlertList.get(i).getLat();
                String lon = userAlertList.get(i).getLon();
                String status = userAlertList.get(i).getStatus();
                Intent intent = new Intent(context, AlertDetailActivity.class);
                intent.putExtra("Latitude", lat);
                intent.putExtra("Longitude", lon);
                intent.putExtra("Status", status);
                intent.putExtra("From", "Worker");

                intent.putExtra("id",userAlertList.get(i).getId());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return userAlertList.size();
    }


    public class WorkerAlertViewHolder extends RecyclerView.ViewHolder {
        public TextView logitud,latitud,status,title,servicetype;

        public WorkerAlertViewHolder(View itemView) {
            super(itemView);

            logitud = itemView.findViewById(R.id.longi);
            latitud = itemView.findViewById(R.id.lati);
            status = itemView.findViewById(R.id.status);
            title = itemView.findViewById(R.id.alertmsg);
            servicetype = itemView.findViewById(R.id.servicetype);



        }
    }
}