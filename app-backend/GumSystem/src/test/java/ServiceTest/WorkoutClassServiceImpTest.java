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

import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Model.WorkoutClass;
import com.example.GumSystem.Repository.MemberRepository;
import com.example.GumSystem.Repository.WorkoutClassRepository;
import com.example.GumSystem.Service.MemberServiceImp;
import com.example.GumSystem.Service.WorkoutClassServiceImp;


@ExtendWith(MockitoExtension.class)
class WorkoutClassServiceImpTest {
	
	@Mock
	private WorkoutClassRepository classRepository;
	
	
	@InjectMocks
    private WorkoutClassServiceImp classService; 
	
	private WorkoutClass newWorkoutClassParam;

	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.openMocks(this);  // Initialize the mocks
		 newWorkoutClassParam = new WorkoutClass();
		 newWorkoutClassParam.setClassName("TRX");
		 newWorkoutClassParam.setCostOfClass(20.0);
	}
	@Test
	void testCreateClass() {
		WorkoutClass savedClass = new WorkoutClass();  
		savedClass.setClassName("TRX");
		savedClass.setCostOfClass(20.0);
		
		when(classRepository.save(any( WorkoutClass.class))).thenReturn(savedClass);  
		WorkoutClass result = classService.createClass(newWorkoutClassParam);

		assertNotNull(result);
        assertEquals(newWorkoutClassParam.getClassName(), result.getClassName());
        assertEquals(newWorkoutClassParam.getCostOfClass(),result.getCostOfClass());
	}
	
	@Test
    public void testDeleteClassById() {
        
    	//class exists
        when(classRepository.existsById(1)).thenReturn(true);
        classService.deleteClassById(1);
        
        //class doesn't exist
        when(classRepository.existsById(10)).thenReturn(false);
        classService.deleteClassById(10);

        
    }

	      
	        
	        

}
