import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import controller.DoctorController;
import exceptions.PatientException;
import repository.Repository;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {

    private Repository rep;
    private DoctorController ctrl;

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {
        assertTrue(true);
    }

    /**
     * Testing patient number
     */
    public void test_PatientsNumber() {
        rep = new Repository("src/test/java/FilePatientsTests.txt", "src/test/java/FileConsultationsTests.txt");

        ctrl = new DoctorController(rep);
        int noPatients = ctrl.getPatientList().size();

        assertEquals(1, noPatients);
    }

    /**
     * Testing patients with disease sore throat number
     */
    public void test_PatientsWithDiseaseNumber() {
        rep = new Repository("src/test/java/FilePatientsTests.txt", "src/test/java/FileConsultationsTests.txt");

        ctrl = new DoctorController(rep);
        int noPatients = 0;
        try {
            noPatients = ctrl.getPatientsWithDisease("sore throat").size();
        } catch (PatientException e) {
            e.printStackTrace();
        }

        assertEquals(1, noPatients);
    }
}
