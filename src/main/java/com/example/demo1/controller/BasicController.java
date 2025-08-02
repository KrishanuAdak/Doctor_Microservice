package com.example.demo1.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.file.Files;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo1.Exception.ResourceNotFoundException;
import com.example.demo1.Exception.UnauthorizedUser;
import com.example.demo1.config.BlacklistToken;
import com.example.demo1.impl.UserDetailsImpl;
import com.example.demo1.model.Available_Doctor_Now;
import com.example.demo1.model.Available_Doctor_Respponse;
import com.example.demo1.model.Doctor;

import com.example.demo1.repo.DoctorRepo;
import com.example.demo1.service.Avaible_Now_service;
//import com.example.demo1.openfeign.OpenFiegn;
import com.example.demo1.service.DoctorService;
import com.example.demo1.service.LoginRecordsService;
import com.example.demo1.util.JwtUtil;
import jakarta.validation.Valid;
import jakarta.servlet.http.HttpServletRequest;






@RestController
@RequestMapping("/doctor")

@CrossOrigin(origins = "http://localhost:4200")
public class BasicController {


	@Autowired
	private DoctorService service;
	
	@Autowired
	private Avaible_Now_service service1;
		
	@Autowired
	private DoctorRepo repo;
	
	@Autowired
	private BlacklistToken token;
	
	@Autowired
	private LoginRecordsService loginRecordsService;
	
	@Autowired
	private DoctorRepo doctorRepo;
	
	


	
	
		
	@GetMapping("/home")
	public ResponseEntity<?> home( @RequestHeader("X-User-Id") String user_id,
	        @RequestHeader("X-Role") String role)
	{
		if(!role.equalsIgnoreCase("doctor")) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You dont have access to view");
		}
	return ResponseEntity.status(HttpStatus.OK).body("welcome to Doctor portal,God");
		
	}
	
	@PostMapping("/register")
	public Doctor registerDoctor(@RequestBody Doctor d){
		return this.service.registerCheck(d);
		
	}
	
	@PostMapping("/register/basic-details")
	public ResponseEntity<?> saveBasicDetails(@RequestHeader("X-User-Id") String user_id,
	        @RequestHeader("X-Role") String role,@Valid @RequestBody Doctor doctor, BindingResult bindingResults){
				if(doctor!=null && user_id!=null) {
					Doctor doc=new Doctor();
					doc.setDoctor_name(doctor.getDoctor_name());
					doc.setDoctor_id(Integer.parseInt(user_id));
					doc.setRegistrationNumber(doctor.getRegistrationNumber());
					doc.setPhone_number(doctor.getPhone_number());
					doc.setLock_version(false);
					doc.setCreation_date(LocalDateTime.now());					
					Doctor d=doc;
					Doctor x=this.doctorRepo.save(d);
					return ResponseEntity.status(HttpStatus.CREATED).body(x);
				}
				return ResponseEntity.badRequest().build();
		
	}


	@GetMapping("/doctors/list")
	public ResponseEntity<?> getAll(
//			@RequestParam(defaultValue="0") int page,
//			                   @RequestParam(defaultValue="4") int size,
			
			                   @RequestHeader("X-User-Id") String userid,
			       	        @RequestHeader("X-Role") String role
                            )
//			                   @RequestParam(defaultValue="doctor_name") String[] sortBy)
	{
//		Sort sortOrder=Sort.by(Sort.Direction.fromString(sortBy[1]),sortBy[0]);
//		Pageable lists=PageRequest.of(page, size);
		List<Doctor> lists=this.service.getAllDoctors();
		System.out.println(role);
		List<Doctor> li=this.service.getAllDoctors();
	    if (!role.equalsIgnoreCase("admin") &&  !role.equalsIgnoreCase("patient")) {
			return ResponseEntity.status(HttpStatus.OK).body(li);
	   
	    }
	    else {
	        return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Access denied");
	    }
	} 


	@GetMapping("/existsById/{id}")
	public boolean isExistDoctor(@PathVariable("id") int id) {
		return this.service.isExistsDoctor(id);		
	}
	@DeleteMapping("/{id}")
	public void delete(@RequestParam("id") int id) {
		this.service.delete(id);
	}
	
	
	@PutMapping("/doctors/{id}")
	public Doctor updateById(@RequestBody Doctor doctor,@PathVariable("id") int id)
	{		
		Doctor s=this.service.updateById(doctor, id);
		return s;
		
	}
	@PutMapping("/update/status/{id}")
	public void updateStatus(@PathVariable("id") int id,@RequestParam("status") String status)
	{
		this.service.updateApprovalStatus(id,status); 
	}
	 
	
	@GetMapping("/available-doctors")
	public List<Available_Doctor_Respponse> getAvailableDoctors()
	{
		List<Available_Doctor_Respponse> li= this.service1.availableDoctors();
		if(li.size()==0)
		{
			throw new ResourceNotFoundException("OOPS!! NO DOCTORS AVAILABLE NOW!! \n"
					+ "we will let you know once any doctor available.");
		}
		return li;
	}
	
	
	@PostMapping("/add-available")
	public Available_Doctor_Now create(@RequestBody Available_Doctor_Now d)
	{
		return this.service1.add(d);
		
	}
//	@PostMapping("/logout")
//  public ResponseEntity<?> logout(@RequestHeader("Authorization") String header,HttpServletRequest request){
//		String records_check_header=request.getHeader("Authorization");
//		String jwt_token="";
//		if(records_check_header!=null && records_check_header.startsWith("Bearer ")) {
//			jwt_token=records_check_header.substring(7);
//			
//		}
//		String name=jwtUtil.extractEmail(jwt_token);
//	   int id=this.repo.getIdFromLoginByUsername(name);
////	   this.loginRecordsService.saveOrUpdateLoginDetails(id);
//	  String to=header.replace("Bearer ", "");
//	  long expireTime=jwtUtil.getExpiration(to).getTime()- System.currentTimeMillis();
//	  token.blacklistToken(to, expireTime);
//	  return ResponseEntity.ok("Hey Buddy! logout successfully");
//	  
//	  
//  }
	

//	@PostMapping("/login")
//	public ResponseEntity<String> signup(@RequestBody Doctor doctor)
//	{
////	    System.out.println(doctor.getEmail());
//		
//		String status_check=this.repo.checkApprovalStatus(doctor.getDoctor_name());
//		if(!status_check.equalsIgnoreCase("APPROVED")) {
//			throw new UnauthorizedUser("Your approval status is not approved!");
//			
//		}
//	      try{
//	            manager.authenticate(
//	                    new UsernamePasswordAuthenticationToken(doctor.getDoctor_name(), doctor.getPassword()));
//	            UserDetails user=userdetails.loadUserByUsername(doctor.getDoctor_name());
//	            String jwt =jwtUtil.generateToken(user);
////	            int id=this.repo.getIdFromLoginByUsername(user.getUsername());
//	            if(jwtUtil.isValidateToken(jwt)) {
////	            	this.loginRecordsService.saveOrUpdateLoginDetails(id);
//	                return new ResponseEntity<>(jwt, HttpStatus.OK);
//	            	
//	            }
//	 
//	        }catch (Exception e){
//	        	e.printStackTrace();
////	            log.error("Exception occurred while createAuthenticationToken ", e);
//	            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
//	        }
//		return new ResponseEntity<>("Login failed", HttpStatus.INTERNAL_SERVER_ERROR);
//	}

//	@GetMapping("/admin/search")
//	public List<Doctor> getByDoctorSearch(@RequestParam ("name")  String name,@RequestParam("email") String email)
//	{
//		System.out.println("Name"+name);
//		return this.service.getBySearch(name,email);
//	}
//	@GetMapping("/{id}/download")
//	public ResponseEntity<byte[]> download(@PathVariable("id") int id){
//		 System.out.print("Download id: "+id);
////		 int idx=Integer.parseInt(id);
//		Doctor d=this.service.getById(id);
//		if(d.getRegistrationFile()==null || id==0 || id<0) {
//			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
//		}
//		   String fileType = "application/octet-stream"; // Default binary file type
//		    try {
//		        fileType = Files.probeContentType(Paths.get(d.getFileName()));
//		        System.out.println(d.getFileName());
//		    } catch (Exception e) {      
//		        e.printStackTrace();
//		    }
//		    return ResponseEntity.ok()
//		            .contentType(MediaType.parseMediaType(fileType))
//		            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + d.getFileName() + "\"")
//		            .body(d.getRegistrationFile());		
//	}
//	
//	@GetMapping("/image/{file_name}")
//	public ResponseEntity<Resource> getImage(@PathVariable("file_name")  String filename) {
//	    try {
//	        Path filePath = Paths.get("add/doctor").resolve(filename).normalize();
//	        Resource resource = new UrlResource(filePath.toUri());
//
//	        if (resource.exists()) {
//	            return ResponseEntity.ok()
//	                    .contentType(MediaType.IMAGE_JPEG) // Change type if needed
//	                    .body(resource);
//	        } else {
//	            return ResponseEntity.notFound().build();
//	        }
//	    } catch (MalformedURLException e) {
//	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//	    }
//	}
//	@GetMapping("/{id}/preview")
//	public ResponseEntity<byte[]> preview(@PathVariable("id") int id) {
//	    Doctor d = service.getById(id);
//
//	    if (d == null || d.getRegistrationFile() == null) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
//	    }
//
//	    String fileType = "application/octet-stream";
//	    try {
//	        // Guess MIME type from filename
//	        fileType = Files.probeContentType(Paths.get(d.getFileName()));
//	    } catch (Exception e) {
//	        e.printStackTrace();
//	    }
//
//	    return ResponseEntity.ok()
//	            .contentType(MediaType.parseMediaType(fileType))
//	            .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + d.getFileName() + "\"")
//	            .body(d.getRegistrationFile());
//	}

	


	
	
	

}
