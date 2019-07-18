package com.example.listactivitydetail;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvCategory;
    private ArrayList<Food> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(FoodData.getListData());

        showRecycleList();

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
                        .signOut(MainActivity.this)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
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

        Intent detail = new Intent(MainActivity.this, DetailActivity.class);
        detail.putExtra(DetailActivity.EXTRA_NAME,  food.getName());
        detail.putExtra(DetailActivity.EXTRA_CONTENT, food.getContent());
        detail.putExtra(DetailActivity.EXTRA_PHOTO, food.getPhoto());
        detail.putExtra(DetailActivity.EXTRA_PRICE, food.getPrice());
        detail.putExtra(DetailActivity.EXTRA_ADDRESS, food.getAddress());

        startActivity(detail);
    }
}
