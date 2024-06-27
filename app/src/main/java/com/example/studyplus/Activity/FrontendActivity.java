package com.example.studyplus.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyplus.Adapter.CoursesAdapter;
import com.example.studyplus.Domain.CoursesDomain;
import com.example.studyplus.R;

import java.util.ArrayList;

public class FrontendActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterCourceList;
    private RecyclerView recyclerViewCourse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_courses_list);

        initRecyclerView();
    }

    private void initRecyclerView() {
        ArrayList<CoursesDomain> items = new ArrayList<>();
        items.add(new CoursesDomain("React", "react"));
        items.add(new CoursesDomain("HTML", "html"));
        items.add(new CoursesDomain("CSS", "css"));

        recyclerViewCourse = findViewById(R.id.view);
        recyclerViewCourse.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        adapterCourceList = new CoursesAdapter(items, course -> {
            Intent intent = new Intent(FrontendActivity.this, CourseDetailActivity.class);
            intent.putExtra("courseName", course.getTitle());
            intent.putExtra("courseKey", course.getPicPath());
            startActivity(intent);
        });
        recyclerViewCourse.setAdapter(adapterCourceList);
    }
}
