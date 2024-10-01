package ServiceTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.GumSystem.Model.Instructor;
import com.example.GumSystem.Repository.InstructorRepository;
import com.example.GumSystem.Service.InstructorServiceImp;




@ExtendWith(MockitoExtension.class)
class InstructorServiceImpTest {
	@Mock
	private InstructorRepository instructorRepository;
	
	@InjectMocks
    private InstructorServiceImp instructorService;  

	private Instructor newInstructorParam;
	
	//for the update method
	private Instructor existingInstructor;
    private Instructor updatedInstructor;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.openMocks(this);  // Initialize the mocks
		 newInstructorParam = new Instructor();
		 newInstructorParam.setFirstName("Carl");
		 newInstructorParam.setLastName("Bob");
		 newInstructorParam.setPhoneNumber(1234567890);
		 newInstructorParam.setSpecialty("pilates");
	        
		 existingInstructor = new Instructor();
		 existingInstructor.setId(5);
		 existingInstructor.setFirstName("Hillary");
		 existingInstructor.setLastName("Adams");
		 existingInstructor.setPhoneNumber(1234567890);
		 existingInstructor.setSpecialty("crossfit");
	        
	     /*Initialize updated member after update
	      * changed  FirstName and Last Name 
	      */
		 updatedInstructor = new Instructor();
		 updatedInstructor.setFirstName("Mary");
		 updatedInstructor.setLastName("Jonan");
		 updatedInstructor.setPhoneNumber(1234567890);
		 updatedInstructor.setSpecialty("crossfit");
	    }
   
	
	
	@Test
	void testCreateInstructor() {
		// Arrange
		Instructor savedInstructor = new Instructor();  
		savedInstructor.setFirstName("Carl");
		savedInstructor.setLastName("Bob");
		savedInstructor.setPhoneNumber(1234567890);
		savedInstructor.setSpecialty("pilates");

        when(instructorRepository.save(any(Instructor.class))).thenReturn(savedInstructor);  
        Instructor result = instructorService.createInstructor(newInstructorParam);

        
        assertNotNull(result);
        assertEquals(newInstructorParam.getFirstName(), result.getFirstName());
        assertEquals(newInstructorParam.getLastName(), result.getLastName());
        assertEquals(newInstructorParam.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(newInstructorParam.getSpecialty(), result.getSpecialty());
        
	}
	
	@Test
    public void testUpdateInstructor() {
        // Arrange: Set up the mock behavior
        when(instructorRepository.existsById(5)).thenReturn(true);  
        when(instructorRepository.findById(5)).thenReturn(existingInstructor);  
        when(instructorRepository.save(existingInstructor)).thenReturn(existingInstructor);  

        // Act: Call the method to be tested
        Instructor  result = instructorService.updateInstructor(5,updatedInstructor);

        // Assert: Check if the result has updated values
        assertNotNull(result);
        assertEquals("Mary", result.getFirstName());
        assertEquals("Jonan", result.getLastName());
        assertEquals(1234567890, result.getPhoneNumber());
        assertEquals("crossfit", result.getSpecialty());
        
    }

    @Test
    public void testUpdateInstructor_InstructorNotFound() {
        when(instructorRepository.existsById(25)).thenReturn(false);  

        
        Instructor  result = instructorService.updateInstructor(25,updatedInstructor);

       
        assertNull(result);

       
    }
    
    @Test
    public void testDeleteInstructorById() {
        
    	//member exists
        when(instructorRepository.existsById(5)).thenReturn(true);
        instructorService.deleteInstructorById(5);
        
        //member doesn't exist
        when(instructorRepository.existsById(10)).thenReturn(false);
        instructorService.deleteInstructorById(10);

        
    }
    @Test
    public void testFindInstructorById() {
        when(instructorRepository.findById(5)).thenReturn(existingInstructor);

        // Act: Call the method to be tested
        Instructor result = instructorService.findInstructorById(5);

        // Assert: Verify that the member returned is the one we expect
        assertNotNull(result);
        assertEquals(existingInstructor.getFirstName(), result.getFirstName());
        assertEquals(existingInstructor.getLastName(), result.getLastName());
    }
    
    
    
}
	
