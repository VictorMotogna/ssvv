import controller.DoctorController;
import exceptions.ConsultationException;
import exceptions.PatientException;
import junit.framework.TestCase;
import model.Consultation;
import model.Patient;
import repository.Repository;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class AppTestIntegration extends TestCase {
    private Repository repository;
    private DoctorController controller;

    public void testAddPatient() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/test/java/FilePatientsIntegration.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();

        writer = null;
        try {
            writer = new PrintWriter("src/test/java/FileConsultationsIntegration.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();


        repository = new Repository("src/test/java/FilePatientsIntegration.txt", "src/test/java/FileConsultationsIntegration.txt");

        controller = new DoctorController(repository);

        try {
            controller.addPatient(new Patient("adi", "1960925245056", "adi"));
        } catch (PatientException e) {
            e.printStackTrace();
        }

        int noPatients = controller.getPatientList().size();

        assertEquals(1, noPatients);
    }

    public void testAddConsultation() {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter("src/test/java/FileConsultationsIntegration.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();

        repository = new Repository("src/test/java/FilePatientsIntegration.txt", "src/test/java/FileConsultationsIntegration.txt");

        controller = new DoctorController(repository);

        try {
            List<String> meds = new ArrayList<String>();
            meds.add("meds");
            controller.addConsultation(1 + "", "1960925245056", "diagnostic", meds, "01.01.2001");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }

        int noConsultations = controller.getConsultationList().size();

        assertEquals(1, noConsultations);
    }

    public void testDiseaseDescendingConsultations() {
        repository = new Repository("src/test/java/filePatientsInt.txt", "src/test/java/fileConsultationsInt.txt");
        controller =  new DoctorController(repository);

        List<Patient> patientsWithDisease = new ArrayList<Patient>();
        try {
            patientsWithDisease = controller.getPatientsWithDisease("diagnostic");
        } catch (PatientException e) {
            e.printStackTrace();
        }

        int noPatientsWithDisease = patientsWithDisease.size();

        List<Consultation> consultations = controller.getConsultationList();
        Map<Patient, Integer> patientsWithConsultations = new TreeMap<>();

        for(int i=0; i<noPatientsWithDisease; i++) {
            int noConsultationsForPatient = 0;
            for(Consultation consultation: consultations) {
                if(patientsWithDisease.get(i).getSSN().equals(consultation.getPatientSSN())) {
                    noConsultationsForPatient++;
                }
            }

            patientsWithConsultations.put(patientsWithDisease.get(i), noConsultationsForPatient);
        }

        assertEquals(patientsWithDisease.size(), patientsWithConsultations.size());
    }

    public void testIntegrationFinal() {

        // add patient test

        String filePatients = "testIntegrationPatients.txt";
        String fileConsultations = "testIntegrationConsultations.txt";

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filePatients);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();

        writer = null;
        try {
            writer = new PrintWriter(fileConsultations);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();


        repository = new Repository(filePatients, fileConsultations);

        controller = new DoctorController(repository);

        try {
            controller.addPatient(new Patient("adi", "1960925245056", "adi"));
        } catch (PatientException e) {
            e.printStackTrace();
        }

        int noPatients = controller.getPatientList().size();

        assertEquals(1, noPatients);



        // add consultation test

        try {
            List<String> medicine = new ArrayList<String>();
            medicine.add("medicine");
            controller.addConsultation(1 + "", "1960925245056", "diagnostic", medicine, "01.01.2001");
        } catch (ConsultationException e) {
            e.printStackTrace();
        }

        int noConsultations = controller.getConsultationList().size();

        assertEquals(1, noConsultations);



        // test patients with disease

        int numberOfConsultations = 0;
        try {
            numberOfConsultations = controller.getPatientsWithDisease("diagnostic").size();
        } catch (PatientException e) {
            e.printStackTrace();
        }

        assertEquals(1, numberOfConsultations);
    }

    public void finalIntegrationTest() {
        testAddPatient();
        testAddConsultation();
        testDiseaseDescendingConsultations();
    }
}
