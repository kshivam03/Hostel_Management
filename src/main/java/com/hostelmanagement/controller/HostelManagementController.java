package com.hostelmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hostelmanagement.model.Admin;
import com.hostelmanagement.model.Payment;
import com.hostelmanagement.model.Room;
import com.hostelmanagement.model.Staff;
import com.hostelmanagement.model.Student;
import com.hostelmanagement.repository.PaymentRepository;
import com.hostelmanagement.repository.RoomRepository;
import com.hostelmanagement.repository.StudentRepository;
import com.hostelmanagement.service.AdminService;
import com.hostelmanagement.service.PaymentService;
import com.hostelmanagement.service.RoomService;
import com.hostelmanagement.service.StaffService;
import com.hostelmanagement.service.StudentService;

@Controller
public class HostelManagementController {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private StaffService staffService;
	
	@Autowired
	private StudentService studentService;
	
	@Autowired
	private AdminService adminService;
	
	@Autowired
	private PaymentService paymentService;
	
	@Autowired
    private RoomRepository roomRepository;

    @Autowired
    private StudentRepository studentRepository;
    
    @Autowired
    private PaymentRepository paymentRepository;
   
	
	@GetMapping("/")
	public String showLoginPage() {
		return "index";
	}
	
	@GetMapping("/home")
	public String home(ModelMap studentModel) {
		return "home";
	}
	 
	@PostMapping("/login")
	public String login(@ModelAttribute(name="loginForm") Admin admin, Model m) {
		String uname = admin.getUsername();
	    String pass = admin.getPassword();
	  	  
	    Admin temp = adminService.getAdminById(uname);
	  
	    if(temp!=null && pass.equals(temp.getPassword())) {
	        return "redirect:/home";
	    }
	  
	    m.addAttribute("error", "Incorrect Username & Password");
	    
	    return "index";
	}
	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/viewRoom")
	public String viewRoom(Model model) {
		model.addAttribute("listRooms", roomService.getAllRooms());
		return "viewRoom";
	}
	
	@GetMapping("/addRoom")
	public String addRoom(Model model) {
		Room room=new Room();
		model.addAttribute("room", room);
		return "addRoom";
	}
	
	@PostMapping("/saveRoom")
	public String saveRoom(@ModelAttribute("room") Room room) {
		String type = room.getRoom_type();
		
		if(type.equals("Normal (Non Ac)"))
		{
			room.setRoom_capacity(3);
			room.setRoom_vacancy(3);
		}
		else if(type.equals("Deluxe (Ac)"))
		{
			room.setRoom_capacity(2);
			room.setRoom_vacancy(2);
		}
		else if(type.equals("Private (Ac)"))
		{
			room.setRoom_capacity(1);
			room.setRoom_vacancy(1);
		}
		else
		{
			room.setRoom_capacity(1);
			room.setRoom_vacancy(1);
		}
		
		roomService.saveRoom(room);
		return "redirect:/viewRoom";
	}
	
	@GetMapping("/deleteRoom/{room_no}")
	public String deleteRoom(@PathVariable (value="room_no") long room_no) {
		Room room = roomService.getRoomById(room_no);
		
		if(room.getRoom_capacity()==room.getRoom_vacancy())
		{
			this.roomService.deleteRoomById(room_no);
		}
		
		return "redirect:/viewRoom";
	}
	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/viewStaff")
	public String viewStaff(Model model) {
		model.addAttribute("listStaffs", staffService.getAllStaffs());
		return "viewStaff";
	}
	
	@GetMapping("/addStaff")
	public String addStaff(Model model) {
		Staff staff=new Staff();
		model.addAttribute("staff", staff);
		return "addStaff";
	}
	
	@PostMapping("/saveStaff")
	public String saveStaff(@ModelAttribute("staff") Staff staff) {
		staffService.saveStaff(staff);
		return "redirect:/viewStaff";
	}
	
	@GetMapping("/updateStaff/{staff_id}")
	public String updateStaff(@PathVariable (value="staff_id") long staff_id, Model model) {
		Staff staff=staffService.getStaffById(staff_id);
		model.addAttribute("staff", staff);
		return "updateStaff";
	}
	
	@GetMapping("/deleteStaff/{staff_id}")
	public String deleteStaff(@PathVariable (value="staff_id") long staff_id) {
		this.staffService.deleteStaffById(staff_id);
		return "redirect:/viewStaff";
	}
	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
	
	@GetMapping("/viewStudent")
	public String viewStudent(Model model) {
		return findPaginated(1, model);
	}
	
	@GetMapping("/addStudent")
	public String addStudent(Model model) {
		Student student=new Student();
		model.addAttribute("student", student);
		
		List<Room> allRooms = roomService.getAllRooms();
		List<Room> listRooms = new ArrayList();
		
		for(Room room : allRooms)
		{
			if(room.getRoom_vacancy() != 0)
			{
				listRooms.add(room);
			}
		}
			
		model.addAttribute("listRooms", listRooms);
		return "addStudent";
	}
	
	@PostMapping("/saveStudent")
	public String saveStudent(@ModelAttribute("student") Student student) {
		long room_no = student.getRoom().getRoom_no();
		Room room = roomService.getRoomById(room_no);
		int room_vacancy = room.getRoom_vacancy();
		
		if(room_vacancy>0)
		{
			room.setRoom_vacancy(room_vacancy-1);
			roomService.saveRoom(room);
;			studentService.saveStudent(student);
		}
		
		return "redirect:/viewStudent";
	}
	
	@PostMapping("/saveUpdatedStudent/{room_no}")
	public String saveUpdatedStudent(@ModelAttribute("student") Student student, @PathVariable (value="room_no") long room_no) {
		Room room1 = roomService.getRoomById(room_no);
		Room room2 = roomService.getRoomById(student.getRoom().getRoom_no());
		
		int room_vacancy1 = room1.getRoom_vacancy(); 
		int room_vacancy2 = room2.getRoom_vacancy(); 

		room1.setRoom_vacancy(room_vacancy1+1);
		room2.setRoom_vacancy(room_vacancy2-1);
		roomService.saveRoom(room1);
		roomService.saveRoom(room2);
		studentService.saveStudent(student);
		
		return "redirect:/viewStudent";
	}
	
	@GetMapping("/updateStudent/{student_id}")
	public String updateStudent(@PathVariable (value="student_id") long student_id, Model model) {
		Student student=studentService.getStudentById(student_id);
		model.addAttribute("student", student);
		
		List<Room> allRooms = roomService.getAllRooms();
		List<Room> listRooms = new ArrayList();
		
		for(Room room : allRooms)
		{
			if(room.getRoom_vacancy() != 0)
			{
				listRooms.add(room);
			}
		}
			
		model.addAttribute("listRooms", listRooms);
		
		return "updateStudent";
	}
	
	@GetMapping("/deleteStudent/{student_id}")
	public String deleteStudent(@PathVariable (value="student_id") long student_id) {
		Student student=studentService.getStudentById(student_id);
		
		List<Payment> payment = student.getPayment();
		
		if(payment!=null)
		{
			long room_no = student.getRoom().getRoom_no();
			Room room = roomService.getRoomById(room_no);
			int room_vacancy = room.getRoom_vacancy();
			room.setRoom_vacancy(room_vacancy+1);
			roomService.saveRoom(room);
			this.studentService.deleteStudentById(student_id);
		}
		
		return "redirect:/viewStudent";
	}
	
	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------

	
	@GetMapping("/viewPayment")
	public String viewPayment(Model model) {
		model.addAttribute("listPayments", paymentService.getAllPayments());
		return "viewPayment";
	}
	
	@GetMapping("/addPayment")
	public String addPayment(Model model) {
		Payment payment=new Payment();
		model.addAttribute("payment", payment);
		
		List<Student> allStudents = studentService.getAllStudents();
		List<Student> listStudents = new ArrayList();
		
		for(Student student : allStudents)
		{
			if(student.getRemaining_amount() != 0)
			{
				listStudents.add(student);
			}
		}
			
		model.addAttribute("listStudents", listStudents);
					
		return "addPayment";
	}
	
	@PostMapping("/savePayment")
	public String savePayment(@ModelAttribute("payment") Payment payment) {
		Student student = payment.getStudent();
		int remaining_amount = student.getRemaining_amount();
		
		if(remaining_amount >= payment.getAmount())
		{
			paymentService.savePayment(payment);
			student.setRemaining_amount(remaining_amount-payment.getAmount());
			studentService.saveStudent(student);
		}
		
		return "redirect:/viewPayment";
	}
	
	@GetMapping("/deletePayment/{payment_id}")
	public String deletePayment(@PathVariable (value="payment_id") long payment_id) {
		Payment payment = paymentService.getPaymentById(payment_id);
		Student student = payment.getStudent();
		int remaining_amount = student.getRemaining_amount();
		
		student.setRemaining_amount(remaining_amount+payment.getAmount());
		studentService.saveStudent(student);
		
		this.paymentService.deletePaymentById(payment_id);
		
		return "redirect:/viewPayment";
	}
	
	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------	
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
// -----------------------------------------------------------------------------------------------------------------------------------
	
	
	@GetMapping("/showRoomDetails/{room_no}")
    public String showRoomDetails(@PathVariable("room_no") long room_no, Model model) {
        Room room = roomRepository.findById(room_no).orElseThrow(() -> new IllegalArgumentException("Invalid room number: " + room_no));
        model.addAttribute("listRooms", room);
        model.addAttribute("listStudents", studentRepository.findByRoom(room));
        return "showRoomDetails";
    }
	
	@GetMapping("/showPaymentDetails/{student_id}")
    public String showPaymentDetails(@PathVariable("student_id") long student_id, Model model) {
        Student student = studentRepository.findById(student_id).orElseThrow(() -> new IllegalArgumentException("Invalid student id: " + student_id));
        model.addAttribute("listStudents", student);
        model.addAttribute("listPayments", paymentRepository.findByStudent(student));
        return "showPaymentDetails";
    }
	
	@GetMapping("/logout")
    public String logout() {
        return "redirect:/";
    }
	

	
	@GetMapping("/viewStudent/page/{pageNo}")
	public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
	    int pageSize = 4;

	    Page < Student > page = studentService.findPaginated(pageNo, pageSize);
	    List < Student > listStudent = page.getContent();

	    model.addAttribute("currentPage", pageNo);
	    model.addAttribute("totalPages", page.getTotalPages());
	    model.addAttribute("totalItems", page.getTotalElements());
	    model.addAttribute("listStudents", listStudent);
	    return "viewStudent";
	}
}

