package com.example.projectcrud;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private FloatingActionButton btnAdd;
    private RecyclerView recyclerView;
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private List<User> list = new ArrayList<>();
    private UserAdapter userAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        btnAdd = findViewById(R.id.btnAdd);
        userAdapter = new UserAdapter(getApplicationContext(), list);

        userAdapter.setDialog(new userAdapter.Dialog() {
            @Override
            public void onClick(int pos) {
                final CharSequence[] dialogitem = {"Edit", "Delete"}:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("Pilih Aksi");
                dialog.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0: // Edit
                                Intent intent = new Intent(MainActivity.this, EditActivity.class);
                                intent.putExtra("id", list.get(pos).getId());
                                intent.putExtra("nama", list.get(pos).getNama());
                                intent.putExtra("kelas", list.get(pos).getKelas());
                                intent.putExtra("absen", list.get(pos).getAbsen());
                                startActivity(intent);
                                break;
                            case 1: // Delete
                                deleteUser(list.get(pos).getId());
                                break:

                        }
                    }
                });
                dialog.show();
            }
        });

        recyclerView.LayoutManager LayoutManager = LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.ItemDecoration decoration = new DividerItemDecoration(getApplicationContext(), DeviderItemDecoration.VERTICAL);
        recyclerView.setLayoutManager(LayoutManager);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setAdapter(userAdapter);

        btnAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditActivity.class)
            startActivity(intent);
        });
    }

        @Override
        protected void  onStart() {
        super.onStart();
        getData();
    }

    private void getData() {

        db.collection("users")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (e != null)
                    }
                })}
