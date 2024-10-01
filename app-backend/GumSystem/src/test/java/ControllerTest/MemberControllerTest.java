package ControllerTest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.mockito.Mockito.when;


import org.springframework.http.MediaType;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.GumSystem.GumSystemApplication;
import com.example.GumSystem.Controller.MemberController;
import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Service.MemberServiceImp;
import com.fasterxml.jackson.databind.ObjectMapper;




@ContextConfiguration(classes = {GumSystemApplication.class})
@WebMvcTest(controllers=MemberController.class)
@AutoConfigureMockMvc(addFilters=false)
@ExtendWith(MockitoExtension.class)
class MemberControllerTest {
	
	@MockBean
    private MemberServiceImp memberService;

    @InjectMocks
    private MemberController memberController;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper; // For converting objects to JSON
	
    @BeforeEach
    public void setUp() {
        // Initialize MockMvc
    	MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(memberController).build();
        objectMapper = new ObjectMapper();
    }
    
    @Test
    public void testCreateMember() throws Exception {
        // Arrange: Create a new member and set the expected behavior
        Member newMember = new Member();
        newMember.setFirstName("Dennis");
        newMember.setLastName("Do");
        newMember.setPhoneNumber(1234567890);
        newMember.setYearOfBirth(1995);
        newMember.setDateOfRegistration(2023);

        Member savedMember = new Member("Dennis", "Do",  1995, 2023,1234567890);

        when(memberService.createMember(any(Member.class))).thenReturn(savedMember);

        // Act & Assert: Perform the POST request and expect the correct response
        mockMvc.perform(post("/members/create")
        		.contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newMember))) // Convert member to JSON
                .andExpect(status().isOk())
                
                .andExpect(jsonPath("$.firstName").value("Dennis"))
                .andExpect(jsonPath("$.lastName").value("Do"));
    }
    
    @Test
    public void testGetMemberById_ReturnsMember() throws Exception {
        
        int memberId = 1;
        Member member = new Member();
        member.setId(memberId);
        member.setFirstName("Markus");
        member.setLastName("Doe");
        member.setPhoneNumber(1234567890);
        member.setYearOfBirth(1998);
        member.setDateOfRegistration(2014);
        
        // Mock the service to return the member
        when(memberService.findMemberById(memberId)).thenReturn(member);
        
        // When & Then
        mockMvc.perform(get("/members/" + memberId)
                .contentType(MediaType.APPLICATION_JSON)) // Optional for GET, but good practice
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.firstName").value("Markus")) 
                .andExpect(jsonPath("$.lastName").value("Doe"))
        		.andExpect(jsonPath("$.dateOfRegistration").value(2014))
        		.andExpect(jsonPath("$.yearOfBirth").value(1998))
        		.andExpect(jsonPath("$.phoneNumber").value(1234567890));
    }
    
    @Test
    public void testUpdateMember() throws Exception {
        // Given
        int memberId = 1;
        Member updatedMember = new Member();
        updatedMember.setFirstName("Marilen");
        updatedMember.setLastName("Sturt");
        updatedMember.setPhoneNumber(1234567890);
        updatedMember.setYearOfBirth(1998);
        updatedMember.setDateOfRegistration(2014);
        
        Member returnedMember = new Member();
        returnedMember.setId(memberId);
        returnedMember.setFirstName("Maria");
        returnedMember.setLastName("Dons");
        returnedMember.setPhoneNumber(1234567890);
        returnedMember.setYearOfBirth(1998);
        returnedMember.setDateOfRegistration(2014);

        // Mock the service to return the updated member
        when(memberService.updateMember(eq(memberId), any(Member.class))).thenReturn(returnedMember);

        // When & Then
        mockMvc.perform(put("/members/" + memberId)
                .contentType(MediaType.APPLICATION_JSON) 
                .content("{\"firstName\":\"Maria\",\"lastName\":\"Dons\"}")) 
                .andExpect(status().isOk()) // Expect 200 OK status
                .andExpect(jsonPath("$.firstName").value("Maria")) 
                .andExpect(jsonPath("$.lastName").value("Dons")); 

       
    }
    
    
    
    @Test
    public void testDeleteMember() throws Exception {
        // Given
        int memberId = 1;

        
        doNothing().when(memberService).deleteMemberById(memberId);

        // When & Then
        mockMvc.perform(delete("/members/" + memberId)
                .contentType(MediaType.APPLICATION_JSON)) 
                .andExpect(status().isNoContent()); // Expect 204 No Content status

        
    }
    



}
