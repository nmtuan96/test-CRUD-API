package com.example.demo.controller;

import com.example.demo.entities.Student;
import com.example.demo.service.ServiceStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController

@CrossOrigin("*")
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private ServiceStudent studentService;

    @GetMapping()
    public ResponseEntity<Iterable<Student>> listAll(){
        return new ResponseEntity<>(studentService.findAll(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Student> createNewStudent(@RequestBody Student student){
        studentService.save(student);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Student> editStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentService.findById(id);
        student.setId(id);
        if(optionalStudent.isPresent()) return new ResponseEntity<>(studentService.save(student),HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Student> getOneStudent(@PathVariable Long id, @RequestBody Student student){
        Optional<Student> optionalStudent = studentService.findById(id);
        student.setId(id);
        if(optionalStudent.isPresent()) return new ResponseEntity<>(student,HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id){
        studentService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
