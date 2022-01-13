package com.clinicanasnuvens.controller;

import com.clinicanasnuvens.model.Patient;
import com.clinicanasnuvens.repository.PatientRepository;
import com.clinicanasnuvens.service.PatientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;
    private final PatientRepository patientRepository;

    public PatientController(PatientService patientService, PatientRepository patientRepository) {
        this.patientService = patientService;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/patient/index")
    public List<Patient> index() {
        return patientService.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patient findById(@PathVariable("id") Long id) {
        return patientService.findById(id);
    }

    @PostMapping("/patient/save")
    public Patient save(@RequestBody Patient patient) {
        return patientService.save(patient);
    }

    @PutMapping("/patient/{id}")
    public Patient update(@RequestBody Patient newPatient, @PathVariable("id") Long id) {

        return patientRepository.findById(id)
                .map(patient -> {
                    patient.setName(newPatient.getName());
                    patient.setSex(newPatient.getSex());
                    patient.setBirthDate(newPatient.getBirthDate());
                    patient.setPhoneNumber(newPatient.getPhoneNumber());
                    patient.setCpf(newPatient.getCpf());

                    return patientService.save(patient);
                })
                .orElseGet(() -> {
                    newPatient.setId(id);
                    return patientService.save(newPatient);
                });
    }

    @DeleteMapping("/patient/delete/{id}")
    public Patient delete(@PathVariable("id") Long id) {
        Patient patient = patientService.findById(id);
        patientService.delete(patient);
        return patient;
    }
}
