package com.example.listactivitydetail;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Auth extends AppCompatActivity {

    private static final int MY_REQUEST_CODE = 12345;
    List<AuthUI.IdpConfig> providers;

    private RecyclerView rvCategory;
    private ArrayList<Food> list = new ArrayList<>();


//    Button btn_sign_out;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        btn_sign_out = findViewById(R.id.btn_sign_out);
//        btn_sign_out.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //Logout
//                AuthUI.getInstance()
//                        .signOut(Auth.this)
//                        .addOnCompleteListener(new OnCompleteListener<Void>() {
//                            @Override
//                            public void onComplete(@NonNull Task<Void> task) {
//                                btn_sign_out.setEnabled(false);
//                                showSignInOptions();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                    @Override
//                    public void onFailure(@NonNull Exception e) {
//                        Toast.makeText(Auth.this, ""+e.getMessage(),Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

        providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build()
        );

        showSignInOptions();

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(FoodData.getListData());

        showRecycleList();
    }

    private void showSignInOptions() {
        startActivityForResult(
                AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(),MY_REQUEST_CODE
        );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == MY_REQUEST_CODE){
            IdpResponse response = IdpResponse.fromResultIntent(data);
            if(resultCode == RESULT_OK){

                //Get User
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                //Show Email On Toast
                Toast.makeText(this,""+user.getEmail(), Toast.LENGTH_SHORT).show();

//                //setButton Sign Out
//                btn_sign_out.setEnabled(true);
            }
            else {
                Toast.makeText(this, ""+response.getError().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        setMode(item.getItemId());
        return super.onOptionsItemSelected(item);
    }

    private void setMode(int selectedMode) {
        switch (selectedMode){
            case R.id.action_home:
                showRecycleList();
                break;

            case R.id.action_signout:
                AuthUI.getInstance()
                        .signOut(Auth.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                showSignInOptions();
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Auth.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
    }

    public void showRecycleList(){
        rvCategory.setLayoutManager(new LinearLayoutManager(this));
        ListFoodAdapter listFoodAdapter = new ListFoodAdapter(list);
        rvCategory.setAdapter(listFoodAdapter);

        listFoodAdapter.setOnItemClickedCallback(new ListFoodAdapter.onItemClickCallback() {
            @Override
            public void OnItemClicked(Food food) {
                showSelectedItem(food);
            }
        });

    }

    private void showSelectedItem(Food food) {

        Intent detail = new Intent(this, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRA_NAME,  food.getName());
        detail.putExtra(DetailActivity.EXTRA_CONTENT, food.getContent());
        detail.putExtra(DetailActivity.EXTRA_PHOTO, food.getPhoto());
        detail.putExtra(DetailActivity.EXTRA_PRICE, food.getPrice());
        detail.putExtra(DetailActivity.EXTRA_ADDRESS, food.getAddress());

        startActivity(detail);
    }
}

