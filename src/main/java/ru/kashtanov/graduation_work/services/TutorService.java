package ru.kashtanov.graduation_work.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;
import ru.kashtanov.graduation_work.repositories.MessageSenderReceiverRepository;
import ru.kashtanov.graduation_work.models.Tutor;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.repositories.StudentRepository;
import ru.kashtanov.graduation_work.dto.TutorForStudentDto;
import ru.kashtanov.graduation_work.repositories.TutorRepository;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TutorService {
    private final StudentRepository studentRepository;
    private final TutorRepository tutorRepository;
    private final MessageService messageService;
    private final RoleService roleService;
    private final MessageSenderReceiverRepository messageSenderReceiverRepository;

    @Autowired
    public TutorService(StudentRepository studentRepository,
                        TutorRepository tutorRepository,
                        MessageService messageService,
                        RoleService roleService,
                        MessageSenderReceiverRepository messageSenderReceiverRepository) {
        this.studentRepository = studentRepository;
        this.tutorRepository = tutorRepository;
        this.messageService = messageService;
        this.roleService = roleService;
        this.messageSenderReceiverRepository = messageSenderReceiverRepository;
    }

    public List<Tutor> getFullTutorList() {
        return tutorRepository.findAll();
    }

    public Tutor getCertainTutorById(Integer tutorId) {
        return tutorRepository.findById(tutorId).orElse(null);
    }

    public Tutor getCertainTutorByUsername(String username) {
        return tutorRepository.findTutorByUserName(username).orElse(null);
    }

    public Tutor getCertainTutorByFirstName(String firstName) {
        return tutorRepository.findTutorByFirstName(firstName).orElse(null);
    }

    public void sendNewMessageToStudent(Message message, Tutor tutor, Integer studentId) {
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new java.sql.Timestamp(time);
        message.setTimestamp(timestamp);
        messageService.addNewMessageToDb(message);

        MessageSenderReceiver msr = new MessageSenderReceiver();

        msr.setMessage(message);
        msr.setSender_id(tutor.getId());
        msr.setReceiver_id(studentId);
        messageSenderReceiverRepository.saveAndFlush(msr);

    }

    public Tutor findAuthenticatedTutor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Tutor tutor = new Tutor();
        if (auth.isAuthenticated()) {
            tutor = tutorRepository.findTutorByUserName(auth.getName()).orElse(null);

        }
        return tutor;
    }

    public void addTutorToDataBase(Tutor tutor) {
        tutor.setRole(roleService.getRoleByName("TUTOR"));
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        tutor.setPassword(encoder.encode(tutor.getPassword()));
        tutorRepository.save(tutor);
    }

    public void update(Integer tutorId, Tutor tutorForUpdating) {
        Tutor updatedTutor = tutorRepository.findById(tutorId).orElse(null);
        updatedTutor.setFirstName(tutorForUpdating.getFirstName());
        updatedTutor.setSecondName(tutorForUpdating.getSecondName());
        updatedTutor.setPhone(tutorForUpdating.getPhone());
        updatedTutor.setEmail(tutorForUpdating.getEmail());
        tutorRepository.save(updatedTutor);

    }

    public boolean assignTutorToStudent(TutorForStudentDto tutorForStudentDto) {
        Tutor tutor = tutorRepository.findTutorByFirstName(tutorForStudentDto.getTutorName()).orElse(null);
        Student student = studentRepository.findCertainStudentWithoutTutorByName(tutorForStudentDto
                .getStudentName()).orElse(null);

        if (tutor != null && student != null) {
            student.setTutor(tutor);
            tutor.getStudentList().add(student);
            studentRepository.save(student);
            tutorRepository.save(tutor);
            return true;
        }
        return false;

    }

    public void deleteTutorById(Integer tutorId) {
        tutorRepository.delete(getCertainTutorById(tutorId));
    }


}
