package Homework03;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

/**
 *
 * @author sefanadir
 */
public class Part1 {

    private final GTUCourseStructure[] gtuCourseStructure;
    private final LinkedList<GTUCourseStructure> gtuCourses;
    private static final String GTU_COURSE_CSV = "gtu_courses.csv";

    public Part1() throws Exception {
        gtuCourses = new LinkedList();
        gtuCourseStructure = new GTUCourseStructure[countFileRows(GTU_COURSE_CSV)];
        for (int i = 0; i < gtuCourseStructure.length; ++i) {
            gtuCourseStructure[i] = new GTUCourseStructure();
        }
        readGTUCoursesCSVFile();
        String[] a = new String[6];
        a = getByCode("CSE 4XX");
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
            System.out.println("");
        }
        System.out.println("----------");
        String[] b = listSemesterCourses(2);
        for (int i = 0; i < b.length; i++) {
            System.out.print(b[i] + " ");
            System.out.println("");
        }
        System.out.println("----------");
        String[] c = getByRange(13, 18);
        for (int i = 0; i < c.length; i++) {
            System.out.print(c[i] + " ");
            System.out.println("");
        }
    }

    /**
     * Bu method parametreden String olarak aldığı ders koduna göre bu koda
     * karşılık gelen ders isimleri belirler.
     *
     * @param courseCode ders kodu
     * @return Belirlenen ders isimleri String arrayi olarak return edelir.
     * @throws Exception Eğer ders kodu sistemde bulunmuyorsa exception
     * fırlatır.
     */
    public String[] getByCode(String courseCode) throws Exception {
        String[] coursesTitle;
        int sameCourseCode = 0, index = 0;
        for (int i = 0; i < gtuCourses.size(); ++i) {
            if (gtuCourses.get(i).getCourseCode().equals(courseCode)) {
                ++sameCourseCode;
            }
        }
        if (sameCourseCode != 0) {
            coursesTitle = new String[sameCourseCode];
            for (int i = 0; i < gtuCourses.size(); ++i) {
                if (gtuCourses.get(i).getCourseCode().equals(courseCode)) {
                    coursesTitle[index] = gtuCourses.get(i).getCourseTitle();
                    ++index;
                }
            }
            return coursesTitle;
        } else {
            throw new Exception("Invalid course code");
        }
    }

    /**
     * Bu method parametreden aldığı semester yılına göre o dönem olan tüm
     * derslerin isimlerini belirler.
     *
     * @param semester dönem yılı
     * @return Belirlenen tüm dersler String arrayi olarak return eder
     * @throws Exception Eğer sistemde olmayan bir semester numarası gelirse
     * exception fırlatılır
     */
    public String[] listSemesterCourses(int semester) throws Exception {
        String[] coursesTitle;
        String semesterYear = semester + "";
        int sameSemester = 0, index = 0;
        for (int i = 0; i < gtuCourses.size(); ++i) {
            if (gtuCourses.get(i).getSemester().equals(semesterYear)) {
                ++sameSemester;
            }
        }
        if (sameSemester != 0) {
            coursesTitle = new String[sameSemester];
            for (int i = 0; i < gtuCourses.size(); ++i) {
                if (gtuCourses.get(i).getSemester().equals(semesterYear)) {
                    coursesTitle[index] = gtuCourses.get(i).getCourseTitle();
                    ++index;
                }
            }
            return coursesTitle;
        } else {
            throw new Exception("Invalid semester year");
        }
    }

    /**
     * Bu method belirtilen başlangıç ve bitiş noktaları arasındaki dersleri
     * belirler. Arama yapılırken bitiş noktası hesaba ( [3, 9) etc. ) katılmaz.
     *
     * @param start_index başlangıc noktası
     * @param last_index bitis noktası
     * @return Belirlenen dersler String arrayi olarak return edilir.
     * @throws Exception Belirtilen indeksler hatalı ise exception fırlatılır.
     */
    public String[] getByRange(int start_index, int last_index) throws Exception {
        int index = 0;
        String[] coursesTitle;
        if (start_index >= 0 && start_index <= gtuCourses.size()
                && last_index >= 0 && last_index <= gtuCourses.size()) {
            if (start_index > last_index) {
                int temp_index = last_index;
                last_index = start_index;
                start_index = temp_index;
            }
            int size = Math.abs(start_index - last_index);
            coursesTitle = new String[size];
            for (int i = start_index; i < last_index; ++i) {
                coursesTitle[index] = gtuCourses.get(i).getCourseTitle();
                ++index;
            }
            return coursesTitle;
        } else {
            throw new Exception("Invalid index");
        }
    }

    /**
     * Bu method dosyadan bilgileri satır satır okuyarak split methodu ile parse
     * eder ve gtuCourses yapısına yerleştirir.
     *
     * @return Dosyanın başarılı şekilde parse edilmesi sonucu true değerini
     * döndürür.
     */
    private boolean readGTUCoursesCSVFile() {
        int index = 0;
        int countRows = 0;
        String courseLine;
        String[] courseStructure;
        try {
            FileReader fileReader = new FileReader(GTU_COURSE_CSV);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                courseLine = bufferedReader.readLine();
                while ((courseLine = bufferedReader.readLine()) != null) {
                    courseStructure = courseLine.split(";");
                    setGTUCourseStructure(courseStructure, index);
                    gtuCourses.add(this.gtuCourseStructure[index]);
                    ++index;
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + GTU_COURSE_CSV);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + GTU_COURSE_CSV);
        }
        return true;
    }

    /**
     * Bu method parametreden dosyanın adını alır. Dosyayı satır satır okuyarak
     * kaç satırdan oluştuğunu bulur ve return eder.
     *
     * @param fileName .csv dosyasının ismi
     * @return dosyanın satır sayısını return eder.
     */
    private int countFileRows(String fileName) {
        int numberOfRows = 0;
        String courseLine;
        try {
            FileReader fileReader = new FileReader(fileName);
            try (BufferedReader bufferedReader = new BufferedReader(fileReader)) {
                courseLine = bufferedReader.readLine();
                while ((courseLine = bufferedReader.readLine()) != null) {
                    ++numberOfRows;
                }
                bufferedReader.close();
                return numberOfRows;
            }
        } catch (FileNotFoundException ex) {
            System.out.println("Unable to open file: " + fileName);
        } catch (IOException ex) {
            System.out.println("Error reading file: " + fileName);
        }
        return numberOfRows;
    }

    /**
     * Bu method dosyadan okunan satırın parse edildikten sonra parça parça
     * GTUCourseStructure[ ] gtuCourseStructure dizine yerleştirilmesini sağlar.
     *
     * @param courseStructure derlere ait bilgilerin bulundugu array
     * @param index bilgi koyulacak indeks
     * @return Yerleştirme işlemi başarılı ise 1 return eder.
     */
    private int setGTUCourseStructure(String[] courseStructure, int index) {
        gtuCourseStructure[index].setSemester(courseStructure[0]);
        gtuCourseStructure[index].setCourseCode(courseStructure[1]);
        gtuCourseStructure[index].setCourseTitle(courseStructure[2]);
        gtuCourseStructure[index].setECTSCredits(courseStructure[3]);
        gtuCourseStructure[index].setGTUCredits(courseStructure[4]);
        gtuCourseStructure[index].setHTL(courseStructure[5]);
        return 1;
    }

    public LinkedList<GTUCourseStructure> getLinkedList() {
        return gtuCourses;
    }

    public GTUCourseStructure[] getCourseStructure() {
        return gtuCourseStructure;
    }
}