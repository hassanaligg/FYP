package com.example.rescue1122.adapters;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.rescue1122.Alert;
import com.example.rescue1122.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.DatabaseRegistrar;
import com.google.firebase.database.FirebaseDatabase;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.ViewHolder;

import java.util.HashMap;
import java.util.Map;

public class ListAdapter extends FirebaseRecyclerAdapter<Alert, ListAdapter.ListViewHolder> {

    Context context;
    public ListAdapter(@NonNull FirebaseRecyclerOptions<Alert> options, Context context) {
        super(options);
        this.context=context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ListViewHolder holder, final int position, @NonNull final Alert model) {
        holder.name.setText(model.getFullName());
        holder.username.setText(model.getUserName());
        holder.cnic.setText(model.getCnic());
        holder.password.setText(model.getPassword());

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference()
                        .child("Worker")
                        .child(getRef(position).getKey())
                        .removeValue() //or .setValue(null)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Toast.makeText(context, "Successfully Deleted Worker", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DialogPlus dialog = DialogPlus.newDialog(context)
                        .setGravity(Gravity.CENTER)
                        .setMargin(50,0,50,0)
                        .setContentHolder(new ViewHolder(R.layout.editworker))
                        .setExpanded(false)
                        .create();

                View HolderView = (LinearLayout)dialog.getHolderView();

                final EditText name=HolderView.findViewById(R.id.name);
                final EditText username=HolderView.findViewById(R.id.username);
                final EditText cnic=HolderView.findViewById(R.id.cnic);
                final EditText password=HolderView.findViewById(R.id.passwor);

                name.setText(model.getFullName());
                username.setText(model.getUserName());
                cnic.setText(model.getCnic());
                password.setText(model.getPassword());

                Button update=HolderView.findViewById(R.id.update);
                update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {


                        deleteNode(getRef(position).getKey());
                        String child=username.getText().toString();
                        DatabaseReference myRef=FirebaseDatabase.getInstance().getReference("Worker").child(child);
                        myRef.child("FullName").setValue(name.getText().toString());
                        myRef.child("UserName").setValue(username.getText().toString());
                        myRef.child("Cnic").setValue(cnic.getText().toString());
                        myRef.child("Password").setValue(password.getText().toString());
                        dialog.dismiss();

                        Toast.makeText(context, username.getText().toString()+" Updated Successfully !", Toast.LENGTH_SHORT).show();

                    }
                });
                dialog.show();
            }
        });
    }

    private void deleteNode(String key) {
        FirebaseDatabase.getInstance().getReference()
                .child("Worker")
                .child(key)
                .removeValue() //or .setValue(null)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(context, "Please Wait...", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workerlist, parent, false);

        return new ListViewHolder(view);
    }

    class ListViewHolder extends RecyclerView.ViewHolder{

        TextView name,username,cnic,password;
        ImageView edit,delete;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            username=itemView.findViewById(R.id.username);
            cnic=itemView.findViewById(R.id.cnic);
            password=itemView.findViewById(R.id.passwor);
            edit=itemView.findViewById(R.id.edit);
            delete=itemView.findViewById(R.id.delete);
        }
    }
}
