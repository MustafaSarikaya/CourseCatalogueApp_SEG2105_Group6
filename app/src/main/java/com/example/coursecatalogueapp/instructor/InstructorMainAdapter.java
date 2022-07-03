package com.example.coursecatalogueapp.instructor;

import static com.example.coursecatalogueapp.instructor.Instructor_MyCourses.mycourses;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.coursecatalogueapp.R;
import com.example.coursecatalogueapp.modules.Course;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class InstructorMainAdapter extends ArrayAdapter<Course> {
    private Activity context;
    List<Course> mycourses;
    List<Course> courses;

    private FirebaseFirestore firestore;
    private static CollectionReference courseReference, courseReference2;



    public InstructorMainAdapter(Activity context,List<Course> courses,FirebaseFirestore firestore, CollectionReference courseReference) {
        super(context, R.layout.instructor_course_row, courses);
        this.context = context;
        this.courses=courses;
        this.firestore = firestore;
        this.courseReference = courseReference;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View listViewItem = layoutInflater.inflate(R.layout.instructor_course_row, null, true);

        TextView courseName = listViewItem.findViewById(R.id.courseName);
        TextView courseCode = listViewItem.findViewById(R.id.courseCode);

        firestore = FirebaseFirestore.getInstance();
        courseReference = firestore.collection("mycourses");
        courseReference2= firestore.collection("courses");

        ImageView addButton = listViewItem.findViewById(R.id.plusbutton);
        Course course = courses.get(position);



          courseName.setText(course.getCourseName());
          courseCode.setText(course.getCourseCode());
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String name = courseName.getText().toString();
                String code = courseCode.getText().toString();
                String id = course.getId();
                String instructorName = InstructorMainActivity.getuserName();
                Course course = new Course(name, code, id, instructorName);



                Toast.makeText(getContext(), "Course added to your schedule!", Toast.LENGTH_SHORT).show();
                courseReference.add(course);
                courseReference2.document(course.getId()).delete();





            }
        });


        return listViewItem;
    }



}
