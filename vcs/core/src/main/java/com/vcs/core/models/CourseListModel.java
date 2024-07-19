package com.vcs.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Model(adaptables = Resource.class)
public class CourseListModel {

    @ValueMapValue
    private String title;

    @ChildResource(name = "courses")
    private List<Resource> courses;

    private List<Course> courseList = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public List<Course> getCourses() {
        return courseList;
    }

    @PostConstruct
    protected void init() {
        if (courses != null) {
            for (Resource course : courses) {
                String courseName = course.getValueMap().get("courseName", String.class);
                String faculty = course.getValueMap().get("faculty", String.class);
                String date = course.getValueMap().get("date", String.class);
                String time = course.getValueMap().get("time", String.class);
                String courseContent = course.getValueMap().get("courseContent", String.class);
                String demoLink = course.getValueMap().get("demoLink", String.class);

                courseList.add(new Course(courseName, faculty, date, time, courseContent, demoLink));
            }
        }
    }

    public static class Course {
        private String courseName;
        private String faculty;
        private String date;
        private String time;
        private String courseContent;
        private String demoLink;

        public Course(String courseName, String faculty, String date, String time, String courseContent, String demoLink) {
            this.courseName = courseName;
            this.faculty = faculty;
            this.date = date;
            this.time = time;
            this.courseContent = courseContent;
            this.demoLink = demoLink;
        }

        public String getCourseName() {
            return courseName;
        }

        public String getFaculty() {
            return faculty;
        }

        public String getDate() {
            return date;
        }

        public String getTime() {
            return time;
        }

        public String getCourseContent() {
            return courseContent;
        }

        public String getDemoLink() {
            return demoLink;
        }
    }
}
