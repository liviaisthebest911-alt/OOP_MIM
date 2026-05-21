package Part7.HospitalService;

/**
 * Exception khi bệnh nhân đã xuất viện
 */
public class PatientDischargedException extends Exception {
    public PatientDischargedException(String message) {
        super(message);
    }
}