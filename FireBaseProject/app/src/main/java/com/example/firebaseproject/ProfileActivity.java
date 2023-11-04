package com.example.firebaseproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ProfileActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private DatabaseReference dbRef;
    private FirebaseUser fbUser;
    private StorageReference stgRef;
    private FirebaseStorage storage;

    private EditText edtTxt2;
    private EditText edtTxt3;
    private EditText edtTxt4;
    private EditText edtTxt6;
    private Button btnModf;
    private Button btnSave;
    private Button btnImage;
    private Button btnConfig;
    private ImageView imgVw;

    private Uri userUri;

    private ProgressDialog prgDlg;

    private MyUser regUser;

    private String dataName, dataSurname, dataEmail, dataPhone;

    public void onStart() {
        super.onStart();
        if (fbUser == null){
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        edtTxt2 = (EditText) findViewById(R.id.edtTxt2);
        edtTxt3 = (EditText) findViewById(R.id.edtTxt3);
        edtTxt4 = (EditText) findViewById(R.id.edtTxt4);
        edtTxt6 = (EditText) findViewById(R.id.edtTxt6);
        btnModf = (Button) findViewById(R.id.btnModf);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnConfig = (Button) findViewById(R.id.btnConfig);
        btnImage = (Button) findViewById(R.id.btnImages);
        imgVw = (ImageView) findViewById(R.id.imgVw);

        prgDlg = new ProgressDialog(this);

        // Deshabilita los campos de texto y botones
        activateButtons(false);

        // Método de recuperar datos de pantalla anterior (Deshabilitado)
        // Bundle extras = getIntent().getExtras();
        // regUser = (MyUser) extras.getSerializable("prfUser");

        // Activación de Firebase
        mAuth = FirebaseAuth.getInstance();
        // Activa el usuario de Firebase
        fbUser = mAuth.getCurrentUser();
        // Recupera de Firebase Database los datos del usuario de Firebase
        dbRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        // Activa la base de datos de Firebase
        stgRef = FirebaseStorage.getInstance().getReference();
        storage = FirebaseStorage.getInstance();

        if (fbUser != null) {
            reloadValues();
        }

        btnModf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(true);
            }
        });

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activateButtons(false);

                prgDlg.setMessage("Guardando...");
                prgDlg.show();
                dataName = edtTxt2.getText().toString().trim();
                dataSurname = edtTxt3.getText().toString().trim();
                dataEmail = edtTxt4.getText().toString().trim();
                dataPhone = edtTxt6.getText().toString().trim();
                MyUser newUser = new MyUser(dataName,dataSurname,dataEmail,dataPhone, null);
                guardarFoto(newUser, fbUser.getUid());
            /* dbRef.child(fbUser.getUid()).setValue(newUser).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        prgDlg.dismiss();
                        if(task.isSuccessful()) {
                            Toast.makeText(ProfileActivity.this, "El Usuario se ha actualizado", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(ProfileActivity.this, "Ha habido un error al actualizar el Usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                }); */

            }
        });

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(ProfileActivity.this, ConfigActivity.class);
                startActivity(itn);
            }
        });

        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itn = new Intent(ProfileActivity.this, ImageListActivity.class);
                startActivity(itn);
            }
        });

        imgVw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent();
                galleryIntent.setAction(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, 2);
            }
        });

    }

    private void guardarFoto(MyUser user, String uidd) {
        StorageReference fileRef = stgRef.child(System.currentTimeMillis() + getFileExtension(userUri));
        fileRef.putFile(userUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                fileRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        user.setImageUser(uri.toString());
                        dbRef.child(uidd).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                prgDlg.dismiss();
                                if(task.isSuccessful()){
                                    regUser = user;
                                    Toast.makeText(ProfileActivity.this, "Datos Modificados Correctamente", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(ProfileActivity.this, "Ha habido un error al actualizar el Usuario", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                    }
                });

            }
        });
    }

    private String getFileExtension(Uri mUri){
        ContentResolver cr = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cr.getType(mUri));
    }

    private void reloadValues() {
        // evento de completado de la operación de recuperar los datos del usuario
        dbRef.child(fbUser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                // Recupera los datos del usuario en un POJO creado por nosotros
                regUser = snapshot.getValue(MyUser.class);
                if (regUser != null) {
                    edtTxt2.setText(regUser.getName().toString());
                    edtTxt3.setText(regUser.getSurname().toString());
                    edtTxt4.setText(regUser.getEmail().toString());
                    edtTxt6.setText(regUser.getPhone().toString());

                    Glide.with(ProfileActivity.this.getApplicationContext()).load(regUser.getImageUser()).into(imgVw);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Si se cancela la operación
            }
        });
    }

    private void activateButtons (boolean act) {
        // Activar/Deactivar Campos de texto y botónes
        edtTxt2.setEnabled(act);
        edtTxt3.setEnabled(act);
        edtTxt4.setEnabled(false);
        edtTxt6.setEnabled(act);
        btnModf.setEnabled(!act);
        btnSave.setEnabled(act);
        imgVw.setEnabled(act);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 2 && resultCode == RESULT_OK && data != null){
            userUri = data.getData();
            imgVw.setImageURI(userUri);
        }
    }
}