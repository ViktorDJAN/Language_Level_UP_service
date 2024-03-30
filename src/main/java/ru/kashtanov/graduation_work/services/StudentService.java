package ru.kashtanov.graduation_work.services;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.kashtanov.graduation_work.models.Message;
import ru.kashtanov.graduation_work.models.MessageSenderReceiver;
import ru.kashtanov.graduation_work.repositories.MessageSenderReceiverRepository;
import ru.kashtanov.graduation_work.models.Student;
import ru.kashtanov.graduation_work.secutiry.PasswordConfig;
import ru.kashtanov.graduation_work.repositories.StudentRepository;
import ru.kashtanov.graduation_work.repositories.RoleRepository;


import javax.persistence.EntityNotFoundException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

/**
 * StudentService provides peculiar functions due to its role
 */
@Service
public class StudentService {
    private final RoleRepository roleRepository;
    private final PasswordConfig passwordEncoder;
    private final StudentRepository studentRepository;
    private final MessageService messageService;
    private final MessageSenderReceiverRepository messageSenderReceiverRepository;


    public StudentService(RoleRepository roleRepository,
                          PasswordConfig passwordEncoder,
                          StudentRepository studentRepository,
                          MessageService messageService,
                          MessageSenderReceiverRepository messageSenderReceiverRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.studentRepository = studentRepository;
        this.messageService = messageService;

        this.messageSenderReceiverRepository = messageSenderReceiverRepository;
    }

    public List<Student> getFullStudentList() {
        return studentRepository.findAll();
    }


    public Student getCertainStudentById(Integer studentId) {
        return studentRepository.findById(studentId).orElse(null);
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findById(student.getId());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("The student with such login is already created! " + student.getUsername());
        }
        studentRepository.save(student);
    }

    public void deleteCertainStudent(Integer studentId) {
        boolean studentExists = studentRepository.existsById(studentId);
        if (!studentExists) {
            throw new IllegalStateException("The pointed student does not exist with id = " + studentId);
        }
        studentRepository.deleteById(studentId);
    }

    public List<Student> getAllStudentsByTutorUserName(String tutorName) {
        System.out.println(studentRepository.findAllStudentsByUserName(tutorName));
        return studentRepository.findAllStudentsByUserName(tutorName);

    }

    public Student getStudentByUsername(String username) {
        return studentRepository.findStudentByUserName(username).orElse(null);
    }

    public void sendNewMessageToTutor(Message message, Student student) {
        Long time = System.currentTimeMillis();
        Timestamp timestamp = new java.sql.Timestamp(time);
        message.setTimestamp(timestamp);
        messageService.addNewMessageToDb(message);

        MessageSenderReceiver msr = new MessageSenderReceiver();
        msr.setMessage(message);
        msr.setSender_id(student.getId());
        msr.setReceiver_id(student.getTutor().getId());
        messageSenderReceiverRepository.save(msr);

    }

    public Student findAuthenticatedStudent() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Student student = new Student();
        if (auth.isAuthenticated()) {
            student = studentRepository.findStudentByUserName(auth.getName()).orElse(null);
        }
        return student;
    }

    public List<Student> getAllStudentsWithoutTutor() {
        System.out.println(studentRepository.findAllStudentsWithoutTutor());
        return studentRepository.findAllStudentsWithoutTutor();
    }

    public Student getStudentWithoutTutorByName(String studentFirstName) {
        return studentRepository.findCertainStudentWithoutTutorByName(studentFirstName).orElse(null);
    }

    public Student register(String firstname, String secondName, String username, String password) {
        if (!username.isEmpty() && password != null) {
            Student student = new Student();
            student.setFirstName(firstname);
            student.setSecondName(secondName);
            student.setUsername(username);
            student.setPassword(passwordEncoder.passwordEncoder().encode(password));
            student.setRole(roleRepository.findByRoleName("STUDENT").orElse(null));
            return studentRepository.save(student);
        } else {
            return null;
        }
    }

    public Student getCertainStudentByFirstName(String firstName) {
        return studentRepository.findStudentByUserFirstName(firstName)
                .orElseThrow(() -> new EntityNotFoundException("There is no such entity"));
    }

    public boolean hasStudentTutor() {
        Student student = findAuthenticatedStudent();
        if (student.getTutor() != null) {
            return true;
        } else {
            return false;
        }
    }


}
