package Homework03;

public class Main {

    public static void main(String[] args) throws Exception {
        try {
            Part1 a = new Part1();

            Part2<String> b = new Part2<>();

            for (int i = 0; i < a.getLinkedList().size(); ++i) {
                b.add(a.getLinkedList().get(i).getCourseTitle());
            }

            System.out.println("size1:" + b.size());
            b.disable(a.getCourseStructure()[2].getCourseTitle());
            System.out.println("size2:" + b.size());

            b.disable(a.getCourseStructure()[3].getCourseTitle());
            System.out.println("size3:" + b.size());

            b.enable(a.getCourseStructure()[2].getCourseTitle());
            b.enable(a.getCourseStructure()[3].getCourseTitle());
            b.showDisabled();
            System.out.println("size4:" + b.size());

            Part3 object = new Part3();
            SemesterCourse a_1 = new SemesterCourse(1, "CSE 102");
            SemesterCourse a_2 = new SemesterCourse(3, "MAT 110");
            SemesterCourse a_3 = new SemesterCourse(4, "BIL 204");
            SemesterCourse a_4 = new SemesterCourse(1, "LAB 147");
            SemesterCourse a_5 = new SemesterCourse(3, "FIZ 144");
            SemesterCourse a_6 = new SemesterCourse(1, "ROR 456");
            object.add(a_1);
            object.add(a_2);
            object.add(a_3);
            object.add(a_4);
            object.add(a_5);
            object.add(a_6);
            System.out.println(object);

            System.out.println("1--->" + object.getCircleSize(1));
            System.out.println("2--->" + object.getCircle(1, 1).getCourseTitle());
            System.out.println(object.get(2).getCourseTitle());
            System.out.println("-------------------");
            object.remove(3);
            System.out.println("3--->" + object.getCircleSize(1));
            System.out.println("4--->" + object.getCircle(1, 1).getCourseTitle());

        } catch (Exception e) {
            System.err.println("Exception caught: " + e);
        }
    }
}
