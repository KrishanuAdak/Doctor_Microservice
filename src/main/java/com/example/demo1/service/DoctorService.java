package com.example.demo1.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.FileSystemResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

//import com.example.demo1.config.CustomUserDetails;
import com.example.demo1.model.Doctor;
//import com.example.demo1.model.EmailSender;
import com.example.demo1.repo.DoctorRepo;

//import jakarta.mail.MessagingException;
//import jakarta.mail.internet.MimeMessage;



@Service
public class DoctorService {
	@Autowired
	private DoctorRepo repo;
	
//	@Autowired
//	private PasswordEncoder encoder; 
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}")
	private String fromEmail;
	
	@Autowired
	private KafkaTemplate<String,Object> kafkatemplate;
	
	public boolean isExistsDoctor(int id) {
		return this.repo.existsById(id);
	}
	
	public int getId(String name) {
		return this.repo.getIdFromLoginByUsername(name);
	}

	
	
//	public void sendEmail(EmailSender email)
//	{
//		MimeMessage mime=mailSender.createMimeMessage();
//		try {
//		
//			MimeMessageHelper messageHelper=new MimeMessageHelper(mime,true);
//			messageHelper.setFrom(fromEmail);
//			messageHelper.setTo(email.sendTo);
//			messageHelper.setSubject("Login Success");
//			messageHelper.setText("Login Successfully done!!!");
//
//			mailSender.send(mime); 
//			System.out.println("Mail Sent");			
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//	}
	
	public Doctor registerCheck(Doctor d) {
		Doctor dd=d;
		Random rand=new Random();
		int id=rand.nextInt(999999)+1;
		dd.setId(id);
//		dd.setPassword(encoder.encode(d.getPassword()));
		dd.setPhone_number(d.getPhone_number());
		dd.setRegistrationNumber(d.getRegistrationNumber());
//		dd.setApproval_Status("PENDING");
		dd.setCreation_date(LocalDateTime.now());
	
		Doctor x=this.repo.save(dd);
		return x;
		
		
	}
	
	
//	@SuppressWarnings("null")
//	public Doctor saveDoctor(String name,String email,String phone_number,String pasword,String RegistrationNumber,MultipartFile file,String specialization,String filename) throws Exception
//	{
//		String d=this.repo.findByEmail(email);
//		if(d!=null)
//		{ 
//			throw new Exception("Use Another Email");
//		}
//	    Doctor dc=new Doctor(); 
//		Random rand=new Random();
//		int x=rand.nextInt(900000)+1;	
//		dc.setId(x);
////		dc.setEmail(email);
////		dc.setPassword(encoder.encode(pasword));
//		dc.setPhone_number(phone_number);
//		dc.setDoctor_name(name);
//		dc.setRegistrationNumber(RegistrationNumber);
//		dc.setApproval_Status("PENDING");
//		dc.setCreation_date(LocalDateTime.now());
//		dc.setLock_version(false);
////		dc.setRole("DOCTOR");
////		dc.setSpecialty(specialization);
//		if(file.getBytes()==null) {
//			throw new Exception("File is required");
//		}
//		dc.setRegistrationFile(file.getBytes());
//		Tika tik=new Tika();
//		String fileType=tik.detect(file.getBytes()); 
//		dc.setFileName(filename);
//		dc.setFileType(fileType);
//		Doctor d1=this.repo.save(dc);
////		this.kafkatemplate.send("doctor-approval-status",d1);
//		return d1;
		
//		dc.setId(x);
//		dc.setDoctor_name(doctor.getDoctor_name());
//		dc.setEmail(doctor.getEmail());
//		dc.setPassword(encoder.encode(doctor.getPassword()));
//		dc.setPhone_number(doctor.getPhone_number());
//		dc.setSpecialty(doctor.getSpecialty());
//		dc.setRegistrationNumber(doctor.getRegistrationNumber());
//		dc.setRegistrationFile(doctor.getRegistrationFile());
//		dc.setRole("DOCTOR");
//		dc.setCreation_date(LocalDateTime.now());
//		dc.setLock_version(false);
//		dc.setApproval_Status("PENDING");
//		Doctor s=this.repo.save(dc);
//		kafkatemplate.send("new-doctor",s); 
//		MimeMessage mime=mailSender.createMimeMessage();
//		try {
//			MimeMessageHelper messageHelper=new MimeMessageHelper(mime,true);
//			messageHelper.setFrom(fromEmail);
//			messageHelper.setTo(doctor.getEmail());
//			messageHelper.setSubject("Login Success");
//			messageHelper.setText("Hey "+doctor.getDoctor_name()+" Your Login  Successfully done!!!");
//			mailSender.send(mime); 
//			System.out.println("Mail Sent");			
//		} catch (MessagingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}	
//	System.out.println(emaikl);
//		return s;
//	}

	public List<Doctor> getAllDoctors(){
//	(Pageable pageable)
	
		List<Doctor> lists= this.repo.findAll();
		if(lists.isEmpty())
		{
			return null;
		}
		return lists;
		
	}
	
	
	//Redis Implement here
	@Cacheable(value="doctors",key="#id")
	public Doctor getById(int id)
	{		
		return this.repo.findById(id).orElseThrow(()->new RuntimeException("Doctor id not found"));
	}
	
	
	//Redis
	@CachePut(value="doctors",key="#id")
	public Doctor updateById(Doctor d,int id)
	{
		Doctor s=d;
//		s.setEmail(d.getEmail());
//		s.setPassword(d.getPassword());
		s.setCreation_date(d.getCreation_date());
		Doctor x=this.repo.save(s);
		System.out.println(x);
		return x;		
	}
//	  public static Long getLoggedInUserId() {
//	        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//	        if (authentication != null && authentication.getPrincipal() instanceof CustomUserDetails) {
//	            return (long) ((CustomUserDetails) authentication.getPrincipal()).getId();
//	        }
//	        return null;
//	    }
	  @CacheEvict(value="doctors",key="#id")
	  public void delete(int id)
	  {
		  this.repo.deleteById(id);
		  
	  }
	  public void updateApprovalStatus(int id,String status)
	  {
		  Doctor d=this.repo.findById(id).orElseThrow(() -> new RuntimeException("Doctor not found"));
		
			 this.repo.updateApprovalStatusByAdmin(status, id);		 
		  
	      
	  }
	  public List<Doctor> getBySearch(String name,String email)
	  {
		  List<Doctor> doctor=this.repo.searchByDoctor(name,email);
		  if(doctor==null)
		  {
			  return null;
		  }
		  return doctor;
	  }
	

	
	
}
