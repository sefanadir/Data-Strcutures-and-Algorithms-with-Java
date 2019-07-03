package Homework03;

public class GTUCourseStructure {

    private String semester;
    private String courseCode;
    private String courseTitle;
    private String ECTSCredits;
    private String GTUCredits;
    private String hoursTheoryLaboratory;

    public GTUCourseStructure() {

    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getECTSCredits() {
        return ECTSCredits;
    }

    public void setECTSCredits(String ECTSCredits) {
        this.ECTSCredits = ECTSCredits;
    }

    public String getGTUCredits() {
        return GTUCredits;
    }

    public void setGTUCredits(String GTUCredits) {
        this.GTUCredits = GTUCredits;
    }

    public String getHTL() {
        return hoursTheoryLaboratory;
    }

    public void setHTL(String HTL) {
        this.hoursTheoryLaboratory = HTL;
    }
}