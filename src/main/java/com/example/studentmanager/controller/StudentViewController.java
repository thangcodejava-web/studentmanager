package com.example.studentmanager.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.studentmanager.entity.Student;
import com.example.studentmanager.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentViewController {

    @Autowired
    private StudentService studentService;

    // Hiển thị giao diện cơ bản Phần B
    @GetMapping
    public String viewStudentsPage(Model model, @RequestParam(required = false) String keyword) {
        model.addAttribute("students", studentService.search(keyword));
        model.addAttribute("keyword", keyword);
        model.addAttribute("newStudent", new Student());
        return "students";
    }

    // Xử lý thêm mới sinh viên từ Form Phần B
    @PostMapping("/add")
    public String addStudent(@ModelAttribute("newStudent") Student student) {
        studentService.save(student);
        return "redirect:/students";
    }

    // Xử lý xóa sinh viên từ Phần B
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable UUID id) {
        studentService.delete(id);
        return "redirect:/students";
    }

    // Điều hướng mở giao diện AdminLTE Phần C
    @GetMapping("/admin")
    public String viewAdminPage() {
        return "admin_students";
    }
}