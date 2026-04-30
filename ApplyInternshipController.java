import java.util.ArrayList;
import java.util.List;

public class ApplyInternshipController {
    public List<Application> existingApplications = new ArrayList<>();

    public boolean applyForInternship(Student student, Internship internship, String resume, String coverLetter) {
        // 1. Mandatory Fields Validation
        if (student == null || student.studentID == null || student.studentID.isEmpty() ||
            internship == null || internship.internshipID == null || internship.internshipID.isEmpty() ||
            resume == null || resume.isEmpty() || 
            coverLetter == null || coverLetter.isEmpty()) {
            System.out.println("Error: All fields are mandatory.");
            return false;
        }

        // 2. Application Count Check
        if (student.applicationCount >= 5) {
            System.out.println("Error: Maximum 5 simultaneous applications allowed.");
            return false;
        }

        // 3. Date Validation
        if (internship.startDate != null && internship.endDate != null) {
            if (internship.startDate.after(internship.endDate)) {
                System.out.println("Error: Start date must be less than or equal to end date.");
                return false;
            }
        } else {
            System.out.println("Error: Invalid date provided.");
            return false;
        }

        // 4. Duplicate Application Check
        for (Application app : existingApplications) {
            if (app.studentID.equals(student.studentID) && app.internshipID.equals(internship.internshipID)) {
                System.out.println("Error: Duplicate application not allowed.");
                return false;
            }
        }

        // 5. Successful Application
        Application newApp = new Application(student.studentID, internship.internshipID, resume, coverLetter);
        existingApplications.add(newApp);
        student.applicationCount++;
        System.out.println("Success: Application submitted successfully.");
        return true;
    }
}
