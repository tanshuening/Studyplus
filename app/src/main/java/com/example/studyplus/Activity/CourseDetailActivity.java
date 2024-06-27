package com.example.studyplus.Activity;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.studyplus.Adapter.VideoAdapter;
import com.example.studyplus.R;

import java.util.ArrayList;
import java.util.List;

public class CourseDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);

        String courseName = getIntent().getStringExtra("courseName");
        String courseKey = getIntent().getStringExtra("courseKey");

        TextView courseTitle = findViewById(R.id.courseTitle);
        TextView courseDescription = findViewById(R.id.courseDescription);
        RecyclerView videoRecyclerView = findViewById(R.id.videoRecyclerView);

        courseTitle.setText(courseName);
        courseDescription.setText("Videos and resources about " + courseName);

        List<String> videoUrls = getVideoUrls(courseKey);
        VideoAdapter videoAdapter = new VideoAdapter(videoUrls, this);
        videoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        videoRecyclerView.setAdapter(videoAdapter);
    }

    private List<String> getVideoUrls(String courseKey) {
        List<String> urls = new ArrayList<>();
        switch (courseKey) {
            case "react":
                urls.add("https://www.youtube.com/watch?v=dGcsHMXbSOA");
                urls.add("https://www.youtube.com/watch?v=Ke90Tje7VS0");
                break;
            case "html":
                urls.add("https://www.youtube.com/watch?v=pQN-pnXPaVg");
                urls.add("https://www.youtube.com/watch?v=UB1O30fR-EE");
                break;
            case "css":
                urls.add("https://www.youtube.com/watch?v=yfoY53QXEnI");
                urls.add("https://www.youtube.com/watch?v=1Rs2ND1ryYc");
                break;
            case "sql":
                urls.add("https://www.youtube.com/watch?v=HXV3zeQKqGY");
                urls.add("https://www.youtube.com/watch?v=7S_tz1z_5bA");
                break;
            case "nosql":
                urls.add("https://www.youtube.com/watch?v=ztHopE5Wnpc");
                urls.add("https://www.youtube.com/watch?v=0r2D32jxMEc");
                break;
            case "mongodb":
                urls.add("https://www.youtube.com/watch?v=pWbMrx5rVBE");
                urls.add("https://www.youtube.com/watch?v=oSIv-E60NiU");
                break;
            case "java":
                urls.add("https://www.youtube.com/watch?v=grEKMHGYyns");
                urls.add("https://www.youtube.com/watch?v=eIrMbAQSU34");
                break;
            case "python":
                urls.add("https://www.youtube.com/watch?v=rfscVS0vtbw");
                urls.add("https://www.youtube.com/watch?v=_uQrJ0TkZlc");
                break;
            case "cpp":
                urls.add("https://www.youtube.com/watch?v=vLnPwxZdW4Y");
                urls.add("https://www.youtube.com/watch?v=mUQZ1qmKlLY");
                break;
            default:
                break;
        }
        return urls;
    }
}
