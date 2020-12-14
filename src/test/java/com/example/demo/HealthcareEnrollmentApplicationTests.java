package com.example.demo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.JSONException;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.jpa.DependentsRepository;
import com.example.demo.jpa.EnrolleesRepository;
import com.example.demo.model.Enrollees;


@RunWith(SpringRunner.class)
@SpringBootTest(classes =HealthcareEnrollmentApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)

@ActiveProfiles("test")
class HealthcareEnrollmentApplicationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;
	
	@MockBean
	private EnrolleesRepository enrolleesRepository;
	@MockBean
	private DependentsRepository dependetRepository;
	
	@LocalServerPort
	private int port;
	
	private String getRootUrl() {
		return "http://localhost:"+port;
	}

	@Test
	void contextLoads() {
	}

	
	
	@Test
    public void get_all_enrollees() throws JSONException {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);

        ResponseEntity<String> response = testRestTemplate.exchange(getRootUrl()+"/healthCare/enrollees", HttpMethod.GET, entity, String.class);
		Assert.assertNotNull(response.getBody());
   

    }
	
	@Test
	public void testAddEnrollees() throws ParseException {
		Enrollees enrollees = new Enrollees();
		//enrollees.setId(001L);
		
				SimpleDateFormat dateformate = new SimpleDateFormat("MM-dd-yyyy");
				Date d = dateformate.parse("10-10-2017");
		enrollees.setBirthdate(d);
		
		enrollees.setName("admin");
		enrollees.setActivationStatus(true);
		enrollees.setPhoneNumber("1234567890");
		
		ResponseEntity<Enrollees> postResponse = testRestTemplate.postForEntity(getRootUrl() + "/healthCare/enrollees", enrollees, Enrollees.class);
		Assert.assertNotNull(postResponse);
		Assert.assertNotNull(postResponse.getBody());
	}


}
