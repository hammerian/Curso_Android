package com.example.taskeando;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    public ArrayList <String> categories;
    public ArrayList <Taskita> taskitas;

    private Spinner spnrType;
    private RecyclerView rclTask;

    private Button btnRecipe;
    private ImageView imgView1;

    private TaskAdapter rcpAdapter;

    private DatabaseReference dbRefCateg;
    private DatabaseReference dbRefTasks;
    private static final int CAMERA_REQUEST_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        spnrType = (Spinner) findViewById(R.id.spnr);
        rclTask = (RecyclerView) findViewById(R.id.rccl1);
        btnRecipe = (Button) findViewById(R.id.btnRecipe);

        dbRefCateg = FirebaseDatabase.getInstance().getReference().child("categories");

        // Initiate Firebase Database connection
        dbRefTasks = FirebaseDatabase.getInstance().getReference().child("tasks");

     /*
     // Load data from scratch
        taskitas = new ArrayList<Taskita>();
        Uri myUri = null;
        taskitas.add(new Taskita("Pop-Up", "Crear una app, que muestre un Pop-Up al pulsar un botón.", "Práctica", false, myUri));
        taskitas.add(new Taskita("Lista de contactos", "Ejemplo de App donde tenemos un listado de contactos.", "Ejemplo", false,myUri));
        taskitas.add(new Taskita("Crear un formulario", "Realizar un XML Drawable de un formulario para crear un contacto.", "Ejercicio", false,myUri));
        taskitas.add(new Taskita("Guardar Usuarios de Firebase", "Creación de un usuario de Firebase y guardado de sus datos.", "Teoría", false,myUri));
     // Save array in Firebase
        dbRefTasks.setValue(newListData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(ListActivity.this, "El listado no se ha guardado", Toast.LENGTH_SHORT).show();
                }
            }
        }); */

        // Recycler view initiation
        rclTask.setLayoutManager(new LinearLayoutManager(this));
        rcpAdapter= new TaskAdapter(taskitas);
        rclTask.setAdapter(rcpAdapter);
        rclTask.setHasFixedSize(false);
        //rcpAdapter.notifyDataSetChanged();
        // rclTask.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        setUpItemTouchHelper();

        ValueEventListener pepe = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categories = new ArrayList<>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String categ = ds.getValue().toString();
                    categories.add(categ);
                }
                changeType(categories, spnrType);
                //Do what you need to do with your list
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Don't ignore errors!
            }
        };
        dbRefCateg.addListenerForSingleValueEvent(valueEventListener);

        ValueEventListener taskitaListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Taskita> newListData = new ArrayList<Taskita>();
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    Taskita tsk = (Taskita) ds.getValue(Taskita.class);
                    newListData.add(tsk);
                }
                taskitas = newListData;
                updateList(taskitas);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //Don't ignore errors!
            }
        };
        dbRefTasks.addListenerForSingleValueEvent(taskitaListener);

        spnrType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0){
                    ArrayList<Taskita> rcpLst = new ArrayList<Taskita>(taskitas);
                    rcpAdapter.applyFilter(rcpLst);
                } else {
                    String str = spnrType.getSelectedItem().toString();
                    ArrayList<Taskita> tskLst = new ArrayList<Taskita>();
                    for (int i = 0; i < taskitas.size();i++) {
                        Taskita rc = taskitas.get(i);
                        if (rc.getTaskType().toString().equals(str)) {
                            tskLst.add(rc);
                        }
                    }
                    rcpAdapter.applyFilter(tskLst);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btnRecipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onButtonShowPopupWindowClick(v);
            }
        });

    }

    private void updateList (ArrayList<Taskita> myNewList) {
        rcpAdapter = new TaskAdapter(myNewList);
        rclTask.setAdapter(rcpAdapter);
        rcpAdapter.notifyDataSetChanged();
    }

    private void changeType (ArrayList<String> typeList, Spinner tlSpnr) {
        ArrayAdapter<String> musicAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, typeList);
        musicAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tlSpnr.setAdapter(musicAdapter);
    }

    private void setUpItemTouchHelper() {
        // https://github.com/nemanja-kovacevic/recycler-view-swipe-to-deletes
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {

            // we want to cache these and not allocate anything repeatedly in the onChildDraw method
            Drawable background;
            Drawable xMark;
            int xMarkMargin;
            boolean initiated;

            private void init() {
                background = new ColorDrawable(Color.RED);
                xMark = ContextCompat.getDrawable(ListActivity.this, R.drawable.ic_clear_24);
                xMark.setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP);
                xMarkMargin = (int) ListActivity.this.getResources().getDimension(R.dimen.ic_clear_margin);
                initiated = true;
            }

            // not important, we don't want drag & drop
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                xMark.setVisible(false,false);
                return false;
            }

            @Override
            public int getSwipeDirs(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
                int position = viewHolder.getAdapterPosition();
                TaskAdapter testAdapter = (TaskAdapter) recyclerView.getAdapter();
                return super.getSwipeDirs(recyclerView, viewHolder);
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int swipedPosition = viewHolder.getAdapterPosition();
                TaskAdapter adapter = (TaskAdapter) rclTask.getAdapter();
                adapter.remove(swipedPosition);
                taskitas = adapter.getRecipeData();
                saveInFirebase(taskitas);
                adapter.notifyDataSetChanged();
                initiated = false;
                xMark.setVisible(false,false);
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
                View itemView = viewHolder.itemView;

                // Victor: Created safe clear of Delete button that previously don't dismiss :( --{
                boolean isCanceled = (dX == 0f) && !isCurrentlyActive;
                if (isCanceled) {
                    xMark.setVisible(false,true);
                    super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
                    return;
                }
                // }--s

                // not sure why, but this method get's called for viewholder that are already swiped away
                if (viewHolder.getAdapterPosition() == -1) {
                    // not interested in those
                    return;
                }

                if (!initiated) {
                    init();
                }

                // draw red background
                background.setBounds(itemView.getRight() + (int) dX, itemView.getTop(), itemView.getRight(), itemView.getBottom());
                background.draw(c);

                // draw x mark
                int itemHeight = itemView.getBottom() - itemView.getTop();
                int intrinsicWidth = xMark.getIntrinsicWidth();
                int intrinsicHeight = xMark.getIntrinsicWidth();

                int xMarkLeft = itemView.getRight() - xMarkMargin - intrinsicWidth;
                int xMarkRight = itemView.getRight() - xMarkMargin;
                int xMarkTop = itemView.getTop() + (itemHeight - intrinsicHeight)/2;
                int xMarkBottom = xMarkTop + intrinsicHeight;
                xMark.setBounds(xMarkLeft, xMarkTop, xMarkRight, xMarkBottom);

                xMark.draw(c);

                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }

        };
        ItemTouchHelper mItemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        mItemTouchHelper.attachToRecyclerView(rclTask);
    }

    private void clearCanvas(Canvas c, Float left, Float top, Float right, Float bottom) {
        Paint pnt = new Paint();
        c.drawRect(left, top, right, bottom, pnt);
    }

    public void onButtonShowPopupWindowClick(View view) {
        // Open PopupView to create a new Recipe Object
        LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = layoutInflater.inflate(R.layout.task_form, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;

        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, true);

        // Initiate Popup elements
        EditText edtTxt11 = (EditText) popupView.findViewById(R.id.edtTxtName);
        EditText edtTxt12 = (EditText) popupView.findViewById(R.id.edtTxtDesc);
        Spinner spnCat = (Spinner) popupView.findViewById(R.id.spnRecipe);
        imgView1 = (ImageView) popupView.findViewById(R.id.imgView1) ;
        Button btn2 = popupView.findViewById(R.id.btnRcp);
        Button btnPic = popupView.findViewById(R.id.btnPic);

        changeType(categories, spnCat); // TODO: Cambiar categorías
        // Launch PopupView
        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        // Dismiss PopupView
        popupView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                popupWindow.dismiss();
                return true;
            }
        });

        // Add Image button action
        btnPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkSelfPermission(android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{android.Manifest.permission.CAMERA}, CAMERA_REQUEST_CODE);
                } else {
                 // openCamera(); // TODO: Abrir cámara
                }
            }
        });

        // Save recipe button action
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Recollection of Form Fields
                String category = spnCat.getSelectedItem().toString();
                String recipeName = edtTxt11.getText().toString().trim();
                String recipeDesc = edtTxt12.getText().toString().trim();

                if (testForm(category,recipeName,recipeDesc)) {
                    Taskita newRecipe = new Taskita();
                 /* if (imageV == null) {
                        newRecipe = new Taskita(recipeName, recipeDesc, category, "" + R.drawable.recipe2, false);
                    } else {
                        newRecipe = new Taskita (recipeName, recipeDesc, category,imageV,false);
                    } */
                    newRecipe = new Taskita (recipeName, recipeDesc, category,false, null);
                    taskitas.add(newRecipe);
                    rcpAdapter.applyFilter(taskitas);
                    spnCat.setSelection(0);
                    rcpAdapter.notifyDataSetChanged();
                    saveInFirebase(taskitas);
                    Toast.makeText(ListActivity.this, "Registro agregado", Toast.LENGTH_SHORT).show();
                    popupWindow.dismiss();
                }
            }
        });
    }

    public void saveInFirebase(ArrayList <Taskita> newListData) {
        // Save array in Firebase
        dbRefTasks.setValue(newListData).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {

                } else {
                    Toast.makeText(ListActivity.this, "El listado no se ha guardado", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean testForm(String category, String recipeName, String recipeDesc) {
        if(category.equals("--Seleccione Una Categoría--")){
            Toast.makeText(ListActivity.this, "Debes seleccionar una categoría para continuar", Toast.LENGTH_SHORT).show();
            return false;
        }else if (recipeName.isEmpty()) {
            Toast.makeText(ListActivity.this, "Debes escribir un nombre para continuar", Toast.LENGTH_SHORT).show();
            return false;
        }else if (recipeDesc.isEmpty()) {
            Toast.makeText(ListActivity.this, "Debes escribir una descripción para continuar", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}