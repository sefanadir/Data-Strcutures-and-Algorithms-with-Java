package Homework03;

/**
 *
 * @author sefanadir
 */
public class SemesterCourse {

    int semester;
    String courseTitle;

    public SemesterCourse() {

    }

    public SemesterCourse(int semester, String courseTitle) {
        this.semester = semester;
        this.courseTitle = courseTitle;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }
}