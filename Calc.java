import java.util.Scanner;

public class Calc {

    static final int TOTAL_SEMESTERS = 6;
    static final int SUBJECTS_PER_SEM = 5;
    static final float CGPA_DIVISOR = 9.5f;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        printTitle();

        String studentName = inputString(sc, "Enter Student Name");
        String sapId = inputString(sc, "Enter SAP ID");
        int semester = inputSemester(sc);

        String[][] subjects = loadSubjects();
        float[] marks = new float[SUBJECTS_PER_SEM];

        printSemesterHeader(semester);

        for (int i = 0; i < SUBJECTS_PER_SEM; i++) {
            marks[i] = inputMarks(sc, subjects[semester - 1][i]);
        }

        float average = calculateAverage(marks);
        float cgpa = average / CGPA_DIVISOR;

        String performance = performanceLabel(average);
        String remark = performanceRemark(average);

        int bestIndex = bestSubjectIndex(marks);
        int weakIndex = weakestSubjectIndex(marks);

        printStudentInfo(studentName, sapId, semester);
        printMarks(subjects[semester - 1], marks);
        printStatistics(average, cgpa, performance, remark);
        printInsights(subjects[semester - 1], marks, bestIndex, weakIndex);
        printGradeChart(subjects[semester - 1], marks);
        printEnd();

        sc.close();
    }

    static void printTitle() {
        System.out.println("=================================================");
        System.out.println("        ULTIMATE B.TECH CSE CGPA CALCULATOR");
        System.out.println("=================================================");
    }

    static String inputString(Scanner sc, String label) {
        System.out.print(label + ": ");
        return sc.nextLine();
    }

    static int inputSemester(Scanner sc) {
        int sem;
        while (true) {
            System.out.print("Enter Current Semester (1-6): ");
            if (sc.hasNextInt()) {
                sem = sc.nextInt();
                sc.nextLine();
                if (sem >= 1 && sem <= 6) return sem;
            } else {
                sc.nextLine();
            }
        }
    }

    static float inputMarks(Scanner sc, String subject) {
        float m;
        while (true) {
            System.out.print("Enter percentage for " + subject + ": ");
            if (sc.hasNextFloat()) {
                m = sc.nextFloat();
                sc.nextLine();
                if (m >= 0 && m <= 100) return m;
            } else {
                sc.nextLine();
            }
        }
    }

    static float calculateAverage(float[] arr) {
        float sum = 0;
        for (float v : arr) sum += v;
        return sum / arr.length;
    }

    static String performanceLabel(float avg) {
        if (avg >= 85) return "EXCELLENT";
        if (avg >= 70) return "VERY GOOD";
        if (avg >= 60) return "GOOD";
        if (avg >= 50) return "AVERAGE";
        return "BELOW AVERAGE";
    }

    static String performanceRemark(float avg) {
        if (avg >= 85) return "Top-tier academic performance";
        if (avg >= 70) return "Strong and consistent progress";
        if (avg >= 60) return "Stable performance with scope to improve";
        if (avg >= 50) return "Needs increased discipline and focus";
        return "Immediate academic attention required";
    }

    static int bestSubjectIndex(float[] marks) {
        int idx = 0;
        for (int i = 1; i < marks.length; i++)
            if (marks[i] > marks[idx]) idx = i;
        return idx;
    }

    static int weakestSubjectIndex(float[] marks) {
        int idx = 0;
        for (int i = 1; i < marks.length; i++)
            if (marks[i] < marks[idx]) idx = i;
        return idx;
    }

    static String grade(float m) {
        if (m >= 90) return "O";
        if (m >= 80) return "A+";
        if (m >= 70) return "A";
        if (m >= 60) return "B+";
        if (m >= 50) return "B";
        return "C";
    }

    static void printSemesterHeader(int sem) {
        System.out.println("-----------------------------------------");
        System.out.println("Semester " + sem + " Subjects");
        System.out.println("-----------------------------------------");
    }

    static void printStudentInfo(String name, String sap, int sem) {
        System.out.println("\nSTUDENT PROFILE");
        System.out.println("-----------------------------------------");
        System.out.println("Name      : " + name);
        System.out.println("SAP ID    : " + sap);
        System.out.println("Semester  : " + sem);
        System.out.println("-----------------------------------------");
    }

    static void printMarks(String[] subjects, float[] marks) {
        System.out.println("\nSUBJECT PERFORMANCE");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-35s %6.2f %%\n", subjects[i], marks[i]);
        }
        System.out.println("-----------------------------------------");
    }

    static void printStatistics(float avg, float cgpa, String perf, String remark) {
        System.out.println("\nACADEMIC SUMMARY");
        System.out.println("-----------------------------------------");
        System.out.printf("Average Percentage : %.2f %%\n", avg);
        System.out.printf("CGPA               : %.2f\n", cgpa);
        System.out.println("Performance Level  : " + perf);
        System.out.println("Remark             : " + remark);
        System.out.println("-----------------------------------------");
    }

    static void printInsights(String[] subjects, float[] marks, int best, int weak) {
        System.out.println("\nINSIGHTS");
        System.out.println("-----------------------------------------");
        System.out.println("Strongest Subject  : " + subjects[best] + " (" + marks[best] + "%)");
        System.out.println("Weakest Subject    : " + subjects[weak] + " (" + marks[weak] + "%)");
        System.out.println("-----------------------------------------");
    }

    static void printGradeChart(String[] subjects, float[] marks) {
        System.out.println("\nGRADE DISTRIBUTION");
        System.out.println("-----------------------------------------");
        for (int i = 0; i < subjects.length; i++) {
            System.out.printf("%-35s Grade: %s\n", subjects[i], grade(marks[i]));
        }
        System.out.println("-----------------------------------------");
    }

    static void printEnd() {
        System.out.println("\n=================================================");
        System.out.println("        END OF SEMESTER ANALYSIS REPORT");
        System.out.println("=================================================");
    }

    static String[][] loadSubjects() {
        return new String[][] {
            {
                "Programming in C",
                "Engineering Mathematics I",
                "Physics",
                "Problem Solving",
                "Environmental Studies"
            },
            {
                "Data Structures",
                "Engineering Mathematics II",
                "Digital Electronics",
                "Python Programming",
                "Critical Thinking"
            },
            {
                "Database Management Systems",
                "Operating Systems",
                "Discrete Mathematics",
                "AIML Fundamentals",
                "Design and Analysis of Algorithms"
            },
            {
                "Object Oriented Programming",
                "Computer Networks",
                "Software Engineering",
                "Linear Algebra",
                "Exploratory Course"
            },
            {
                "Compiler Design",
                "Cryptography and Network Security",
                "Formal Languages",
                "Probability and Statistics",
                "Research Methodology"
            },
            {
                "Statistics and Data Analysis",
                "Leadership and Teamwork",
                "Minor Project",
                "Program Elective",
                "Program Elective Lab"
            }
        };
    }
}
