package com.hospital.models;

import java.time.LocalDate;

public class Patient {

    public enum PatientType {
        INPATIENT,
        OUTPATIENT,
        MATERNITY
    }

    // Common fields
    private String name;
    private int age;
    private String gender;
    private String status;
    private String bloodType;
    private String phone;
    private String email;
    private PatientType type;

    // Inpatient-specific fields
    private String ward;
    private String bedNumber;
    private LocalDate admissionDate;
    private String doctorAssigned;

    // Outpatient-specific fields
    private LocalDate visitDate;
    private String consultationType;
    private LocalDate nextAppointment;

    // Maternity-specific fields
    private LocalDate deliveryDate;
    private String obstetrician;
    private String pregnancyStage;
    private String notes;

    // -------------------------
    // Constructors
    // -------------------------

    public Patient() {}

    /** Convenience constructor for common fields */
    public Patient(String name, int age, String gender, String status,
                   String bloodType, String phone, String email, PatientType type) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.status = status;
        this.bloodType = bloodType;
        this.phone = phone;
        this.email = email;
        this.type = type;
    }

    // -------------------------
    // Getters & Setters
    // -------------------------

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public PatientType getType() { return type; }
    public void setType(PatientType type) { this.type = type; }

    // Inpatient
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }

    public String getBedNumber() { return bedNumber; }
    public void setBedNumber(String bedNumber) { this.bedNumber = bedNumber; }

    public LocalDate getAdmissionDate() { return admissionDate; }
    public void setAdmissionDate(LocalDate admissionDate) { this.admissionDate = admissionDate; }

    public String getDoctorAssigned() { return doctorAssigned; }
    public void setDoctorAssigned(String doctorAssigned) { this.doctorAssigned = doctorAssigned; }

    // Outpatient
    public LocalDate getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDate visitDate) { this.visitDate = visitDate; }

    public String getConsultationType() { return consultationType; }
    public void setConsultationType(String consultationType) { this.consultationType = consultationType; }

    public LocalDate getNextAppointment() { return nextAppointment; }
    public void setNextAppointment(LocalDate nextAppointment) { this.nextAppointment = nextAppointment; }

    // Maternity
    public LocalDate getDeliveryDate() { return deliveryDate; }
    public void setDeliveryDate(LocalDate deliveryDate) { this.deliveryDate = deliveryDate; }

    public String getObstetrician() { return obstetrician; }
    public void setObstetrician(String obstetrician) { this.obstetrician = obstetrician; }

    public String getPregnancyStage() { return pregnancyStage; }
    public void setPregnancyStage(String pregnancyStage) { this.pregnancyStage = pregnancyStage; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    @Override
    public String toString() {
        return "Patient{name='" + name + "', type=" + type + "}";
    }
}
