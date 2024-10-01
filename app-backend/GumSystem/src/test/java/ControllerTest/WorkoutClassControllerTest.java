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
import com.example.GumSystem.Controller.MemberController;
import com.example.GumSystem.Controller.WorkoutClassController;
import com.example.GumSystem.Model.Instructor;
import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Model.WorkoutClass;
import com.example.GumSystem.Service.InstructorServiceImp;
import com.example.GumSystem.Service.MemberServiceImp;
import com.example.GumSystem.Service.WorkoutClassServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.*;

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
@WebMvcTest(controllers=WorkoutClassController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
class WorkoutClassControllerTest {
	@MockBean
    private WorkoutClassServiceImp classService;

    @InjectMocks
    private WorkoutClassController classController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON
	
    @BeforeEach
    public void setUp() {
        // Initialize MockMvc
    	MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(classController).build();
        objectMapper = new ObjectMapper();
    }
    
    @Test
    public void testCreateClass() throws Exception {
        
    	WorkoutClass newClass = new WorkoutClass();
    	newClass.setClassName("pilates");
    	newClass.setCostOfClass(35.0);
      
    	WorkoutClass savedClass = new WorkoutClass(35.0,"pilates");

        when(classService.createClass(any(WorkoutClass.class))).thenReturn(savedClass);

        // Act & Assert: Perform the POST request and expect the correct response
        mockMvc.perform(post("/WorkoutClasses/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newClass))) 
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.className").value("pilates"))
                .andExpect(jsonPath("$.costOfClass").value(35.0));
               
                
    }
    
    
    @Test
    public void testDeleteClass() throws Exception {
        
        int classId = 1;
        doNothing().when(classService).deleteClassById(classId);

        // When & Then
        mockMvc.perform(delete("/WorkoutClasses/" + classId)
                .contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isNoContent()); // Expect 204 No Content status

        
    }
	

}
