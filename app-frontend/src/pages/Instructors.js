import React, { useState, useEffect } from 'react';
import {deleteInstructor, getAllInstructors} from '../components/HandleRequest';
import { Button} from "antd";
import { useNavigate } from 'react-router-dom';


const Instructors = () => {

    const [instructors, setInstructors] = useState([]); // State to hold members data
    const navigator=useNavigate();
    
    useEffect(() =>{ 
      showInstructors()
    }, [] )
  
    const showInstructors =() =>{
      
        getAllInstructors().then ((response) => {
            setInstructors(response.data);
        }).catch(error =>{
            console.log(error);
    
        })
     
    }
   
    const HandleAdd = () => {
      navigator("/addInstructor")
      
    }
    
  
    const HandleUpdate=(id) =>{
      navigator(`/UpdateInstructor/${id}`);
    }
  
    const HandleDelete=(id) =>{
       console.log("Delete id",id)
       deleteInstructor(id).then((response) => {
        showInstructors()
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
                <th>specialty </th>
                <th>Phone Number</th>
                <th>Action</th>
                </tr>
            </thead>
            <tbody>
              {instructors.map( (i) =>
                (
                  <tr key={i.id}>
                    <td>{i.firstName}</td>
                    <td>{i.lastName}</td>
                    <td>{i.specialty}</td>
                    <td>{i.phoneNumber}</td>
                    <td>< Button type="primary"  onClick={() => HandleUpdate(i.id)}>Update</Button></td>
                    <td>< Button type="primary"  onClick={() => HandleDelete(i.id)}>Delete</Button></td>
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
  
  
    
  export default Instructors;
  
      
  