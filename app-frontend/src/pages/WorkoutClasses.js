import React, { useState, useEffect } from 'react';
import { getAllClasses,deleteClass } from '../components/HandleRequest';
import { useNavigate } from 'react-router-dom';
import { Button} from "antd";


const WorkoutClasses =()=>{
    const [classes, setClasses] = useState([]); 
    const navigator=useNavigate();

    useEffect(() =>{ 
        showClass()
      }, [] )

    const showClass =() =>{
    
        getAllClasses().then ((response) => {
            setClasses(response.data);
        }).catch(error =>{
            console.log(error);
    
        })
     
    }
    const HandleAddClass = () => {
        navigator("/addClass")
        
    }

    const HandleDelete=(id) =>{
        console.log("Delete id",id)
        deleteClass(id).then((response) => {
         showClass()
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
                  <th>ClassName</th>
                  <th>CostOfClass</th>
                  <th>Action</th>
                  </tr>
              </thead>
              <tbody>
                {classes.map((c) =>
                  (
                    <tr key={c.id}>
                      <td>{c.className}</td>
                      <td>{c.costOfClass}</td>
                      <td>< Button type="primary"  onClick={() => HandleDelete(c.id)}>Delete</Button></td>
                    </tr>
                  )
                )}
              </tbody>
            </table>
            <>< Button type="primary"  onClick={() => HandleAddClass()}>Add</Button></>
          </div>
          
        </>
        
      
       
      );
    };
    


export default WorkoutClasses;