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
import org.springframework.boot.test.context.SpringBootTest;

import com.example.GumSystem.Model.Member;
import com.example.GumSystem.Repository.MemberRepository;
import com.example.GumSystem.Service.MemberServiceImp;


@ExtendWith(MockitoExtension.class)

class MemberServiceImpTest {
	@Mock
	private MemberRepository memberRepository;
	
	@InjectMocks
    private MemberServiceImp memberService;  

	private Member newMemberParam;
	
	//for the update method
	private Member existingMember;
    private Member updatedMember;
	
	@BeforeEach
	public void setUp() {
		 MockitoAnnotations.openMocks(this);  // Initialize the mocks
	        newMemberParam = new Member();
	        newMemberParam.setFirstName("John");
	        newMemberParam.setLastName("John");
	        newMemberParam.setPhoneNumber(1234567890);
	        newMemberParam.setYearOfBirth(1990);
	        newMemberParam.setDateOfRegistration(2020);
	        
	        
	        existingMember = new Member();
	        existingMember.setId(3);
	        existingMember.setFirstName("Mary");
	        existingMember.setLastName("Adams");
	        existingMember.setPhoneNumber(1234567890);
	        existingMember.setYearOfBirth(1990);
	        existingMember.setDateOfRegistration(2021);
	        
	        
	        
	     /*Initialize updated member after update
	      * changed Last Name from John to Mary
	      */
	        updatedMember = new Member();
	        updatedMember.setFirstName("Mary");
	        updatedMember.setLastName("Jonan");
	        updatedMember.setPhoneNumber(1234567890);
	        updatedMember.setYearOfBirth(1990);
	        updatedMember.setDateOfRegistration(2021);
	    }
   
	
	
	@Test
	void testCreateMember() {
		// Arrange
        Member savedMember = new Member();  
        savedMember.setFirstName("John");
        savedMember.setLastName("John");
        savedMember.setPhoneNumber(1234567890);
        savedMember.setYearOfBirth(1990);
        savedMember.setDateOfRegistration(2020);

        when(memberRepository.save(any(Member.class))).thenReturn(savedMember);  
        Member result = memberService.createMember(newMemberParam);

        
        assertNotNull(result);
        assertEquals(newMemberParam.getFirstName(), result.getFirstName());
        assertEquals(newMemberParam.getLastName(), result.getLastName());
        assertEquals(newMemberParam.getPhoneNumber(), result.getPhoneNumber());
        assertEquals(newMemberParam.getYearOfBirth(), result.getYearOfBirth());
        assertEquals(newMemberParam.getDateOfRegistration(), result.getDateOfRegistration());

	}
	
	@Test
    public void testUpdateMember() {
        // Arrange: Set up the mock behavior
        when(memberRepository.existsById(3)).thenReturn(true);  
        when(memberRepository.findById(3)).thenReturn(existingMember);  
        when(memberRepository.save(existingMember)).thenReturn(existingMember);  

        // Act: Call the method to be tested
        Member result = memberService.updateMember(3, updatedMember);

        // Assert: Check if the result has updated values
        assertNotNull(result);
        assertEquals("Mary", result.getFirstName());
        assertEquals("Jonan", result.getLastName());
        assertEquals(1234567890, result.getPhoneNumber());
        assertEquals(1990, result.getYearOfBirth());
        assertEquals(updatedMember.getDateOfRegistration(), result.getDateOfRegistration());

     
    }

    @Test
    public void testUpdateMember_MemberNotFound() {
        when(memberRepository.existsById(2)).thenReturn(false);  

        
        Member result = memberService.updateMember(2, updatedMember);

       
        assertNull(result);

       
    }
    
    @Test
    public void testDeleteMemberById() {
        
    	//member exists
        when(memberRepository.existsById(3)).thenReturn(true);
        memberService.deleteMemberById(3);
        
        //member doesn't exist
        when(memberRepository.existsById(10)).thenReturn(false);
        memberService.deleteMemberById(10);

        
    }
    @Test
    public void testFindMemberById() {
        when(memberRepository.findById(3)).thenReturn(existingMember);

        // Act: Call the method to be tested
        Member result = memberService.findMemberById(3);

        // Assert: Verify that the member returned is the one we expect
        assertNotNull(result);
        assertEquals(existingMember.getFirstName(), result.getFirstName());
        assertEquals(existingMember.getLastName(), result.getLastName());
    }
    
    
    
}
	


