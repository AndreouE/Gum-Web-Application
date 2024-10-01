import axios from 'axios';

// URL for  Spring Boot backend
const API_URL = 'http://localhost:8081';


export const createMember = (data) => {
     console.log("Data in axios",data)
     return axios.post(`${API_URL}/members/create`,{
      firstName:data.firstNameState,
      dateOfRegistration:data.converted_dateOfRegistrationState,
      phoneNumber:data.converted_phoneNumberState,
      yearOfBirth:data.converted_yearOfBirthState,
      lastName:data.lastNameState
    })
  
}

export const createInstructor = (data) => {
  console.log("Data in axios",data)
  return axios.post(`${API_URL}/instructors/createInstructor`,{
   firstName:data.firstName,
   lastName:data.lastName,
   specialty:data.specialty,
   phoneNumber:data.converted_phoneNumber, 
 })

}

export const createClass = (data) => {
  console.log("Data in axios",data)
  return axios.post(`${API_URL}/WorkoutClasses/create`,{

    className:data.className,
    costOfClass:data.converted_costOfClass,
  
   
 })

}



export const getAllMembers = () => axios.get(`${API_URL}/members/Allmembers`);
export const getAllInstructors = () => axios.get(`${API_URL}/instructors/AllInstructors`);
export const getAllClasses = () =>axios.get(`${API_URL}/WorkoutClasses/AllClasses`);


export const getMemberById = (id) => {
  console.log("inside get")
  return axios.get(`${API_URL}/members/${id}`);
};

export const getInstructorById = (id) => {
  console.log("inside get")
  return axios.get(`${API_URL}/instructors/${id}`);
};

export const updateMember = (id, memberData) => {
  console.log("inside update")
  return axios.put(`${API_URL}/members/${id}`,{
    dateOfRegistration:memberData.converted_dateOfRegistrationState,
    phoneNumber:memberData.converted_phoneNumberState,
    yearOfBirth:memberData.converted_yearOfBirthState,
    firstName:memberData.firstNameState,
    lastName:memberData.lastNameState
  });
};

export const updateInstructor = (id, instructorData) => {
  console.log("inside update")
  return axios.put(`${API_URL}/instructors/${id}`,{
    firstName:instructorData.firstName,
    lastName:instructorData.lastName,
    specialty:instructorData.specialty,
    phoneNumber:instructorData.converted_phoneNumber,
    
    
  });
};


export const deleteMember = (id) => {
  return axios.delete(`${API_URL}/members/${id}`);
};

export const deleteInstructor = (id) => {
  return axios.delete(`${API_URL}/instructors/${id}`);
};

export const deleteClass = (id) => {
  return axios.delete(`${API_URL}/WorkoutClasses/${id}`);
};