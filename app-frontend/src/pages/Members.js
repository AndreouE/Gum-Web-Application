import React, { useState, useEffect } from 'react';
import {deleteMember, getAllMembers} from '../components/HandleRequest';
import { Button} from "antd";
import { useNavigate } from 'react-router-dom';


const Members = () => {

  const [members, setMembers] = useState([]); // State to hold members data
  const navigator=useNavigate();
  
  useEffect(() =>{ 
    showMembers()
  }, [] )

  const showMembers =() =>{
    
      getAllMembers().then ((response) => {
          setMembers(response.data);
      }).catch(error =>{
          console.log(error);
  
      })
   
  }
 
  

  const HandleAdd = () => {
    navigator("/addMember")
    
  }
  

  const HandleUpdate=(id) =>{
    navigator(`/Update/${id}`);
  }

  const HandleDelete=(id) =>{
     console.log("Delete id",id)
     deleteMember(id).then((response) => {
      showMembers()
     }).catch(error =>{
      console.error(error);
     })
  }

  
  return (
    <>
      <div className="list">
        <table >
          <thead>
            <tr >
              <th>FirstName</th>
              <th>LastName</th>
              <th>Registration </th>
              <th>Birth Year</th>
              <th>Phone Number</th>
              <th>Action</th>
              </tr>
          </thead>
          <tbody>
            {members.map( (member) =>
              (
                <tr key={member.id}>
                  <td>{member.firstName}</td>
                  <td>{member.lastName}</td>
                  <td>{member.dateOfRegistration}</td>
                  <td>{member.yearOfBirth}</td>
                  <td>{member.phoneNumber}</td>
                  <td>< Button type="primary"  onClick={() => HandleUpdate(member.id)}>Update</Button></td>
                  <td>< Button type="primary"  onClick={() => HandleDelete(member.id)}>Delete</Button></td>
                </tr>
              )
            )}
          </tbody>
        </table>
        <>< Button type="primary"  onClick={() => HandleAdd()}>Add</Button></>
      </div>
      
    </>
    
  
   
  );
};


  
export default Members;

    
