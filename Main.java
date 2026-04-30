import java.util.Date;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Internship Portal Testing (15 Cases) ---\n");

        ApplyInternshipController controller = new ApplyInternshipController();

        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));

        Internship validInternship = new Internship("INT_VALID", today, tomorrow);
        Internship invalidDateInternship = new Internship("INT_INVALID_DATE", tomorrow, today);
        Internship boundaryDateInternship = new Internship("INT_BOUNDARY", today, today);

        // TC_01: Valid Application (Count = 1) -> Student has 0, applies for 1st
        Student s1 = new Student("S_01", 0);
        System.out.println("TC_01 | Valid Application (Count 1)");
        controller.applyForInternship(s1, validInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_02: Valid Application (Count = 4) -> Student has 3, applies for 4th
        Student s2 = new Student("S_02", 3);
        System.out.println("TC_02 | Valid Application (Count 4)");
        controller.applyForInternship(s2, new Internship("INT_2", today, tomorrow), "resume.pdf", "cover.txt");
        System.out.println();

        // TC_03: Valid Application Boundary (Count = 5) -> Student has 4, applies for 5th
        Student s3 = new Student("S_03", 4);
        System.out.println("TC_03 | Valid Application Boundary (Count 5)");
        controller.applyForInternship(s3, new Internship("INT_3", today, tomorrow), "resume.pdf", "cover.txt");
        System.out.println();

        // TC_04: Invalid Limit (Count = 6) -> Student already has 5, applies for 6th
        Student s4 = new Student("S_04", 5);
        System.out.println("TC_04 | Invalid Limit (Count 6)");
        controller.applyForInternship(s4, validInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_05: Boundary Case (Count = 0) -> Student has 0 applications
        Student s5 = new Student("S_05", 0);
        System.out.println("TC_05 | Boundary Case (Count 0)");
        controller.applyForInternship(s5, new Internship("INT_4", today, tomorrow), "resume.pdf", "cover.txt");
        System.out.println();

        // TC_06: Missing Student ID
        Student s6 = new Student("", 0);
        System.out.println("TC_06 | Missing Student ID");
        controller.applyForInternship(s6, validInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_07: Missing Internship ID
        Student s7 = new Student("S_07", 0);
        Internship noIdInternship = new Internship(null, today, tomorrow);
        System.out.println("TC_07 | Missing Internship ID");
        controller.applyForInternship(s7, noIdInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_08: Missing Resume
        System.out.println("TC_08 | Missing Resume");
        controller.applyForInternship(new Student("S_08", 0), validInternship, "", "cover.txt");
        System.out.println();

        // TC_09: Missing Cover Letter
        System.out.println("TC_09 | Missing Cover Letter");
        controller.applyForInternship(new Student("S_09", 0), validInternship, "resume.pdf", null);
        System.out.println();

        // TC_10: Valid Date Range (Start < End)
        System.out.println("TC_10 | Valid Date Range");
        controller.applyForInternship(new Student("S_10", 0), validInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_11: Boundary Date Range (Start = End)
        System.out.println("TC_11 | Boundary Date Range (Start = End)");
        controller.applyForInternship(new Student("S_11", 0), boundaryDateInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_12: Invalid Date Range (Start > End)
        System.out.println("TC_12 | Invalid Date Range (Start > End)");
        controller.applyForInternship(new Student("S_12", 0), invalidDateInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_13: Invalid Date Format (represented as null)
        System.out.println("TC_13 | Invalid Date Format");
        Internship invalidFormatInternship = new Internship("INT_INVALID_FMT", null, null);
        controller.applyForInternship(new Student("S_13", 0), invalidFormatInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_14: Duplicate Application
        System.out.println("TC_14 | Duplicate Application");
        Student dupStudent = new Student("S_14", 0);
        // 1st time
        controller.applyForInternship(dupStudent, validInternship, "resume.pdf", "cover.txt");
        // 2nd time
        System.out.print("        Second attempt: ");
        controller.applyForInternship(dupStudent, validInternship, "resume.pdf", "cover.txt");
        System.out.println();

        // TC_15: Extra Valid Case
        System.out.println("TC_15 | Extra Valid Case");
        controller.applyForInternship(new Student("S_15", 0), new Internship("INT_EXTRA", today, tomorrow), "resume.pdf", "cover.txt");
        System.out.println();
    }
}
