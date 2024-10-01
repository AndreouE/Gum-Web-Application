package ControllerTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.example.GumSystem.GumSystemApplication;
import com.example.GumSystem.Controller.InstructorController;

import com.example.GumSystem.Model.Instructor;

import com.example.GumSystem.Service.InstructorServiceImp;

import com.fasterxml.jackson.databind.ObjectMapper;



import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


@ContextConfiguration(classes = {GumSystemApplication.class})
@WebMvcTest(controllers=InstructorController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
class InstructorControllerTest {

	@MockBean
    private InstructorServiceImp instructorService;

    @InjectMocks
    private InstructorController instructorController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON
	
    @BeforeEach
    public void setUp() {
        // Initialize MockMvc
    	MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
        objectMapper = new ObjectMapper();
    }
	
    @Test
    public void testCreateInstructor() throws Exception {
        
        Instructor newInstructor = new Instructor();
        newInstructor.setFirstName("Marios");
        newInstructor.setLastName("Andrews");
        newInstructor.setPhoneNumber(1234567890);
        newInstructor.setSpecialty("TRX");
      

        Instructor savedInstructor = new Instructor("Marios", "Andrews","TRX",1234567890);

        when(instructorService.createInstructor(any(Instructor.class))).thenReturn(savedInstructor);

        // Act & Assert: Perform the POST request and expect the correct response
        mockMvc.perform(post("/instructors/createInstructor")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newInstructor))) // Convert member to JSON
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.firstName").value("Marios"))
                .andExpect(jsonPath("$.lastName").value("Andrews"))
                .andExpect(jsonPath("$.phoneNumber").value(1234567890))
                .andExpect(jsonPath("$.specialty").value("TRX"));
                
    }
    
    
    @Test
    public void testUpdateInstructor() throws Exception {
        
        int instructorId = 1;
        Instructor updatedInstructor = new Instructor();
        updatedInstructor.setFirstName("Marilen");
        updatedInstructor.setLastName("Sturt");
        updatedInstructor.setPhoneNumber(1234567890);
        updatedInstructor.setSpecialty("pilates");
        
        
        Instructor returnedInstructor = new Instructor();
        returnedInstructor.setId(instructorId);
        returnedInstructor.setFirstName("Maria");
        returnedInstructor.setLastName("Dons");
        returnedInstructor.setPhoneNumber(1234567890);
        returnedInstructor.setSpecialty("pilates");
        

       
        when(instructorService.updateInstructor(eq(instructorId), any(Instructor.class))).thenReturn(returnedInstructor);

        // When & Then
        mockMvc.perform(put("/instructors/" + instructorId)
                .contentType(MediaType.APPLICATION_JSON) 
                .content("{\"firstName\":\"Maria\",\"lastName\":\"Dons\"}")) 
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.firstName").value("Maria")) 
                .andExpect(jsonPath("$.lastName").value("Dons")); 

       
    }
    
    
    
    @Test
    public void testDeleteInstructor() throws Exception {
        
        int instructorId = 1;
        doNothing().when(instructorService).deleteInstructorById(instructorId);

        // When & Then
        mockMvc.perform(delete("/instructors/" + instructorId)
                .contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isNoContent()); // Expect 204 No Content status

        
    }
    

	

}
