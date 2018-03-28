import controller.DoctorController;
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

        int numberOfPacients = controller.getPatientList().size();

        assertEquals(3, numberOfPacients);
        System.out.println();
    }
}