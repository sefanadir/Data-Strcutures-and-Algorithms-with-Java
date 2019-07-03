package Homework03;

/**
 *
 * @author sefanadir
 */
public class Part3 {

    private int size;
    private int[] circleSize;
    private Node startList;
    private Node endList;
    private Node[] circleEndList;
    private Node[] circleStartList;
    private final static int SEMESTER_NUMBER = 8;

    public Part3() {
        size = 0;
        endList = null;
        startList = null;
        circleSize = new int[SEMESTER_NUMBER];
        circleEndList = new Node[SEMESTER_NUMBER];
        circleStartList = new Node[SEMESTER_NUMBER];
        initializeArray();
    }

    class Node {

        private SemesterCourse data;
        private Node next;
        private Node circleNext;

        public Node() {
            next = null;
            circleNext = null;
        }

        public Node(SemesterCourse data) {
            next = null;
            circleNext = null;
            this.data = data;
        }

        public SemesterCourse getData() {
            return data;
        }

        public void setData(SemesterCourse data) {
            this.data = data;
        }

        public Node next() {
            return next;
        }

        public Node nextInSemester() {
            return circleNext;
        }
    }

    /**
     * Listenın uzunlugunu verır
     *
     * @return sıze'ı return eder
     */
    public int size() {
        return size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    /**
     * Circle yapıların sızeını verır
     *
     * @param index cırcle ındeksı
     * @return cırcle sıze'ı return eder
     */
    public int getCircleSize(int index) {
        return circleSize[index - 1];
    }

    private void setCircleSize(int index, int circleSize) {
        this.circleSize[index - 1] = circleSize;
    }

    /**
     * Indekse gore o konumdakı elemanı return eder
     *
     * @param index elemanın konumu
     * @return indekstekı eleman
     */
    public SemesterCourse get(int index) {
        if (index >= 0 && index < size()) {
            Node findElement = startList;
            for (int i = 0; i < index; ++i) {
                findElement = findElement.next();
            }
            return findElement.getData();
        }
        return null;
    }

    /**
     * cırcle yapısından next elemanını verır
     *
     * @param semester semester nosu
     * @param index konum degerı
     * @return bır sonrakı cırcle elemanını return eder.
     */
    public SemesterCourse getCircle(int semester, int index) {
        if (circleSize[semester - 1] >= 0 && index < circleSize[semester - 1]) {

            Node findElement = startList;
            for (int i = 0; i < size(); ++i) {
                if (findElement.getData().getSemester() == semester) {
                    break;
                }
                findElement = findElement.next();

            }
            for (int i = 0; i < index; ++i) {
                findElement = findElement.nextInSemester();
            }
            return findElement.getData();
        }
        return null;
    }

    /**
     * Listeye eleman eklenmek ıstedıgınde kullanılır.
     *
     * @param data eklenecek olan eleman
     * @return eklenen elemanı return eder.
     */
    public SemesterCourse add(SemesterCourse data) {
        Node newNode = new Node(data);
        if (startList == null) {
            startList = newNode;
            endList = startList;
        } else {
            endList.next = newNode;
            endList = newNode;
        }
        setSize(size() + 1);

        int index = newNode.getData().getSemester();
        if (circleStartList[index - 1] == null) {
            circleStartList[index - 1] = newNode;
            circleEndList[index - 1] = newNode;
            newNode.circleNext = circleStartList[index - 1];
        } else {
            circleEndList[index - 1].circleNext = newNode;
            circleEndList[index - 1] = newNode;
            circleEndList[index - 1].circleNext = circleStartList[index - 1];
        }
        setCircleSize(index, getCircleSize(index) + 1);
        return newNode.getData();
    }

    /**
     * Listeden eleman cıkarmak ıcın kullanılır.
     *
     * @param index remove edılecek olan indeks
     * @return remove edılen elemanı return eder.
     */
    public SemesterCourse remove(int index) {
        if (size() == 0) {
            System.out.println("cannot remove from an empty list.");
            return null;
        } else if (index >= 0 && index <= size()) {
            Node currentNode = startList;
            for (int i = 0; i < index - 1; ++i) {
                currentNode = currentNode.next();
            }
            currentNode.next = currentNode.next.next;
            setSize(size() - 1);

            Node findElement = startList;
            int semester = get(index).getSemester();
            for (int i = 0; i < size(); ++i) {
                if (findElement.getData().getSemester() == semester) {
                    break;
                }
                findElement = findElement.next();
            }
            Node endNode = findElement;
            do {
                if (endNode.circleNext.getData() == get(index)) {
                    Node newNode = endNode.circleNext;
                    endNode = newNode.circleNext;
                    if (newNode == findElement) {
                        findElement = findElement.circleNext;
                    }
                    newNode = null;
                    break;
                }
                endNode = endNode.circleNext;
            } while (endNode != startList);
            setCircleSize(semester, getCircleSize(semester) - 1);
            return get(index);
        } else {
            return null;
        }
    }

    /**
     * Elemanın listede icerigınde olup olmadıgına bakar
     *
     * @param data kontrol edelıcek eleman
     * @return eleman listede varsa true eder.
     */
    public boolean contains(SemesterCourse data) {
        if (size() == 0) {
            return false;
        } else {
            Node checkNode = startList;
            for (; checkNode != null; checkNode = checkNode.next()) {
                if (checkNode.getData().equals(data)) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * Listeyi print eder.
     *
     * @return bos return eder
     */
    @Override
    public String toString() {
        System.out.print("[");

        int i = 0;
        Node current = startList;

        while (current != null) {
            System.out.print(current.getData().getCourseTitle() + (i < size - 1 ? ", " : ""));
            current = current.next();
            ++i;
        }
        System.out.println("]");
        return "";
    }

    /**
     * Liste bos mu dolu mu dıye kontrol eder.
     *
     * @return liste bossa true dolu ise false return eder.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * olusturulan arraylerı doldurur.
     */
    private void initializeArray() {
        for (int i = 0; i < SEMESTER_NUMBER; ++i) {
            circleEndList[i] = new Node();
            circleStartList[i] = new Node();
        }
        for (int i = 0; i < SEMESTER_NUMBER; ++i) {
            circleEndList[i] = null;
            circleStartList[i] = null;
        }
    }
}