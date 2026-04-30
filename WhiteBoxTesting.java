import java.util.Date;

public class WhiteBoxTesting {
    public static void main(String[] args) {
        System.out.println("--- White Box Testing (15 Cases covering 5 Paths) ---\n");
        System.out.println("Target Method: applyForInternship()");
        System.out.println("Cyclomatic Complexity V(G) = 5\n");
        System.out.println("Note: Because V(G)=5, there are only 5 unique paths through the code.");
        System.out.println("These 15 test cases comprehensively cover all conditions within those 5 paths.\n");

        ApplyInternshipController controller = new ApplyInternshipController();
        
        Date today = new Date();
        Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
        Internship validInternship = new Internship("INT_WB", today, tomorrow);

        // ==========================================
        // PATH 1: Mandatory Field Check Fails
        // ==========================================
        System.out.println("--- Path 1 Variations (Mandatory Fields) ---");
        
        // WB_01: Missing Student Object
        System.out.println("WB_01 | Path 1: Null Student Object");
        controller.applyForInternship(null, validInternship, "resume.pdf", "cover.txt");
        
        // WB_02: Missing Student ID
        System.out.println("WB_02 | Path 1: Empty Student ID");
        controller.applyForInternship(new Student("", 0), validInternship, "resume.pdf", "cover.txt");
        
        // WB_03: Missing Internship Object
        System.out.println("WB_03 | Path 1: Null Internship Object");
        controller.applyForInternship(new Student("S1", 0), null, "resume.pdf", "cover.txt");

        // WB_04: Missing Internship ID
        System.out.println("WB_04 | Path 1: Empty Internship ID");
        controller.applyForInternship(new Student("S1", 0), new Internship("", today, tomorrow), "resume.pdf", "cover.txt");

        // WB_05: Missing Resume
        System.out.println("WB_05 | Path 1: Empty Resume");
        controller.applyForInternship(new Student("S1", 0), validInternship, "", "cover.txt");

        // WB_06: Missing Cover Letter
        System.out.println("WB_06 | Path 1: Null Cover Letter");
        controller.applyForInternship(new Student("S1", 0), validInternship, "resume.pdf", null);
        System.out.println();


        // ==========================================
        // PATH 2: Application Count Check Fails
        // ==========================================
        System.out.println("--- Path 2 Variations (Application Limit) ---");

        // WB_07: Count exactly 5
        System.out.println("WB_07 | Path 2: Count is exactly 5");
        controller.applyForInternship(new Student("S_L1", 5), validInternship, "resume.pdf", "cover.txt");

        // WB_08: Count is 6 (over limit)
        System.out.println("WB_08 | Path 2: Count is 6");
        controller.applyForInternship(new Student("S_L2", 6), validInternship, "resume.pdf", "cover.txt");
        System.out.println();


        // ==========================================
        // PATH 3: Date Validation Fails
        // ==========================================
        System.out.println("--- Path 3 Variations (Date Rules) ---");

        // WB_09: Start > End
        System.out.println("WB_09 | Path 3: Start Date after End Date");
        controller.applyForInternship(new Student("S_D1", 0), new Internship("I_D1", tomorrow, today), "resume.pdf", "cover.txt");

        // WB_10: Start Date is Null
        System.out.println("WB_10 | Path 3: Start Date is Null");
        controller.applyForInternship(new Student("S_D2", 0), new Internship("I_D2", null, tomorrow), "resume.pdf", "cover.txt");

        // WB_11: End Date is Null
        System.out.println("WB_11 | Path 3: End Date is Null");
        controller.applyForInternship(new Student("S_D3", 0), new Internship("I_D3", today, null), "resume.pdf", "cover.txt");
        System.out.println();


        // ==========================================
        // PATH 4: Duplicate Application Fails
        // ==========================================
        System.out.println("--- Path 4 Variations (Duplicates) ---");

        Student dupStudent = new Student("S_DUP", 0);
        // Setup initial application (This executes Path 5 to setup Path 4)
        controller.applyForInternship(dupStudent, validInternship, "resume.pdf", "cover.txt");

        // WB_12: Exact Duplicate
        System.out.print("WB_12 | Path 4: Exact Duplicate Attempt -> ");
        controller.applyForInternship(dupStudent, validInternship, "resume.pdf", "cover.txt");

        // WB_13: Duplicate with different resume (Should still fail due to same IDs)
        System.out.print("WB_13 | Path 4: Duplicate with new resume -> ");
        controller.applyForInternship(dupStudent, validInternship, "new_resume.pdf", "cover.txt");
        System.out.println();


        // ==========================================
        // PATH 5: Successful Execution
        // ==========================================
        System.out.println("--- Path 5 Variations (Success) ---");

        // WB_14: Standard Success
        System.out.println("WB_14 | Path 5: Standard valid application");
        controller.applyForInternship(new Student("S_SUC1", 0), new Internship("INT_SUC1", today, tomorrow), "resume.pdf", "cover.txt");

        // WB_15: Success at limit boundary (Count = 4)
        System.out.println("WB_15 | Path 5: Success at boundary (Count = 4)");
        controller.applyForInternship(new Student("S_SUC2", 4), new Internship("INT_SUC2", today, tomorrow), "resume.pdf", "cover.txt");
        System.out.println();
    }
}
