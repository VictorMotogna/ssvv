package ssvvlab2;

import ssvvlab2.repository.Repository;
import ssvvlab2.ui.DoctorUI;
import ssvvlab2.controller.DoctorController;

public class App {

    public static void main(String[] args) {
        String patients = "src/main/FilePatients.txt";
        String consultations = "src/main/FileConsultations.txt";
        Repository repo = new Repository(patients, consultations);
        DoctorController ctrl = new DoctorController(repo);

        DoctorUI console = new DoctorUI(ctrl);
        console.Run();
    }
}
