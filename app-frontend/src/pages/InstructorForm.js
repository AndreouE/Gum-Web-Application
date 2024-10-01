import React, { useState, useEffect } from 'react';
import { Button, Form, Input } from 'antd';
import {createInstructor,getInstructorById, updateInstructor} from '../components/HandleRequest';
import { useParams } from 'react-router-dom';



const onFinish = (values) => {
  console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};




const InstructorForm = () =>{
    const [firstName,setFirstName]=useState(" ")
    const [lastName,setLastName]=useState(" ")
    const [specialty,setSpecialty]=useState("")
    const [phoneNumber,setPhoneNumber]=useState(0)
    
    
    const {id}=useParams()

    useEffect( () =>{
        if(id){
            getInstructorById(id).then((response) => {
                setFirstName(response.data.firstName)
                setLastName(response.data.lastName)
                setSpecialty(response.data.specialty)
                setPhoneNumber(response.data.phoneNumber)
                
            }).catch(error => console.error(error))
        }
       },[id])
    
    

    const HandleSubmit =(event) =>{
        event.preventDefault()
       
        const converted_phoneNumber=parseInt(phoneNumber)
        const newInstructor={firstName,lastName,specialty,converted_phoneNumber}
       
        
        if(id){
            updateInstructor(id,newInstructor).then((response) =>{
                console.log(response.data)
            }).catch(error => {
                console.error(error)
            })    
        }
        else{
            createInstructor(newInstructor).then((response) =>{      
                console.log(response.data)
             })
        }
    }

    return(
        <Form
    name="basic"
    labelCol={{
      span: 8,
    }}
    wrapperCol={{
      span: 16,
    }}
    style={{
      maxWidth: 600,
    }}
    initialValues={{
      remember: true,
    }}
    onFinish={onFinish}
    onFinishFailed={onFinishFailed}
    autoComplete="off"
  >
    <Form.Item
      label="FirstName"
      name="Fisrt Name"
      value={firstName}
      onChange={(event)=>setFirstName(event.target.value)}

      rules={[
        {
          required: true,
          message: 'Please enter First Name ',
        },
      ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="LastName"
      name="Last Name"
      value={lastName}
      onChange={(event)=>setLastName(event.target.value)}
      rules={[
        {
          required: true,
          message: 'Please enter Last Name!',
        },
      ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="specialty"
      name="specialty"
      value={specialty}
      onChange={(event)=>setSpecialty(event.target.value)}
      rules={[
        {
          required: true,
          message: 'Please enter year Of Birth!',
        },
      ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="phoneNumber"
      name="phoneNumber"
      value={phoneNumber}
      onChange={(event)=>setPhoneNumber(event.target.value)}
      rules={[
        {
          required: true,
          message: 'Please enter phoneNumber',
        },
      ]}
    >
      <Input />
    </Form.Item>

    

    <Form.Item
      wrapperCol={{
        offset: 8,
        span: 16,
      }}
    >
      <Button type="primary" htmlType="submit" onClick={HandleSubmit}>
        Submit
      </Button>
    </Form.Item>
  </Form>
    )

    
}
    
export default InstructorForm;