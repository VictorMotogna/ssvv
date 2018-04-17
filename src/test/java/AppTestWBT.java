import controller.DoctorController;
import exceptions.PatientException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import repository.Repository;

public class AppTestWBT extends TestCase {

    private Repository repository;
    private DoctorController controller;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTestWBT(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTestWBT.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testAppWBT() {
        assertTrue(true);
    }

    public void testAllStatementsPatients() {
        repository = new Repository("src/test/java/FilePatientsAllStatements.txt", "src/test/java/FileConsultationsAllStatements.txt");
        controller =  new DoctorController(repository);

        int numberOfPatients = controller.getPatientList().size();

        assertEquals(3, numberOfPatients);
        System.out.println();
    }

    public void testAllStatementsConsultations() {
        repository = new Repository("src/test/java/FilePatientsAllStatements.txt", "src/test/java/FileConsultationsAllStatements.txt");
        controller =  new DoctorController(repository);

        int numberOfConsultations = controller.getConsultationList().size();

        assertEquals(4, numberOfConsultations);
        System.out.println();
    }

    public void testPatientsWithDiseaseLoop1() {
        repository = new Repository("src/test/java/FilePatientsLoop1.txt", "src/test/java/FileConsultationsLoop1.txt");
        controller =  new DoctorController(repository);

        int numberOfConsultations = 0;
        try {
            numberOfConsultations = controller.getPatientsWithDisease("sore throat").size();
        } catch (PatientException e) {
            e.printStackTrace();
        }

        assertEquals(1, numberOfConsultations);
        System.out.println();
    }

    public void testPatientsWithDiseaseLoop2() {
        repository = new Repository("src/test/java/FilePatientsLoop2.txt", "src/test/java/FileConsultationsLoop2.txt");
        controller =  new DoctorController(repository);

        int numberOfConsultations = 0;
        try {
            numberOfConsultations = controller.getPatientsWithDisease("sore throat").size();
        } catch (PatientException e) {
            e.printStackTrace();
        }

        assertEquals(2, numberOfConsultations);
        System.out.println();
    }

    public void testAllPatientsPath0() {
        repository = new Repository("src/test/java/FilePatientsAllPath0.txt", "src/test/java/FileConsultationsAllPath0.txt");
        controller =  new DoctorController(repository);

        int numberOfPatients = controller.getPatientList().size();

        assertEquals(0, numberOfPatients);
        System.out.println();
    }

    public void testAllConsultationsPath0() {
        repository = new Repository("src/test/java/FilePatientsAllPath0.txt", "src/test/java/FileConsultationsAllPath0.txt");
        controller =  new DoctorController(repository);

        int numberOfConsultations = controller.getConsultationList().size();

        assertEquals(0, numberOfConsultations);
        System.out.println();
    }

    public void testPatientsDiseaseTrue() {
        repository = new Repository("src/test/java/FilePatientsTrue.txt", "src/test/java/FileConsultationsTrue.txt");
        controller =  new DoctorController(repository);

        int numberOfConsultations = 0;
        try {
            numberOfConsultations = controller.getPatientsWithDisease("sore throat").size();
        } catch (PatientException e) {
            e.printStackTrace();
        }

        boolean result = (numberOfConsultations == 1);

        assertEquals(true, result);
        System.out.println();
    }
}
