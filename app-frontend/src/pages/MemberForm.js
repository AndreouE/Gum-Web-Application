import React, { useState, useEffect } from 'react';
import { Button,  Form, Input } from 'antd';
import {createMember,getMemberById, updateMember} from '../components/HandleRequest';
import { useParams } from 'react-router-dom';



const onFinish = (values) => {
  console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};




const MemberForm = () =>{
    const [firstNameState,setFirstNameState]=useState(" ")
    const [lastNameState,setLastNameState]=useState(" ")
    const [yearOfBirthState,setYearOfBirthState]=useState(0)
    const [dateOfRegistrationState,setDateOfRegistrationState]=useState(0)
    const [phoneNumberState,setPhoneNumberState]=useState(0)
    
    
    const {id}=useParams()

    useEffect( () =>{
        if(id){
            getMemberById(id).then((response) => {
                setFirstNameState(response.data.firstName)
                setLastNameState(response.data.lastName)
                setYearOfBirthState(response.data.yearOfBirth)
                setDateOfRegistrationState(response.data.dateOfRegistration)
                setPhoneNumberState(response.data.phoneNumber)
                
            }).catch(error => console.error(error))
        }
       },[id])
    
    

    const HandleSubmit =(event) =>{
        event.preventDefault()
        
        const converted_dateOfRegistrationState=parseInt(dateOfRegistrationState)
        const converted_yearOfBirthState=parseInt(yearOfBirthState)
        const converted_phoneNumberState=parseInt(phoneNumberState)
       
        const newMember={firstNameState,lastNameState,converted_yearOfBirthState,converted_dateOfRegistrationState,converted_phoneNumberState}
        console.log("new ME",newMember)
        
        if(id){
            
            updateMember(id,newMember).then((response) =>{
                console.log(response.data)
                debugger
            }).catch(error => {
                console.error(error)
            })    
            

        }
        else{
            createMember(newMember).then((response) =>{
                    
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
      value={firstNameState}
      onChange={(event)=>setFirstNameState(event.target.value)}

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
      value={lastNameState}
      onChange={(event)=>setLastNameState(event.target.value)}
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
      label="yearOfBirth"
      name="yearOfBirth"
      value={yearOfBirthState}
      onChange={(event)=>setYearOfBirthState(event.target.value)}
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
      label="dateOfRegistration"
      name="dateOfRegistration"
      value={dateOfRegistrationState}
      onChange={(event)=>setDateOfRegistrationState(event.target.value)}
      rules={[
        {
          required: true,
          message: 'Please enter date Of Registration',
        },
      ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="phoneNumber"
      name="phoneNumber"
      value={phoneNumberState}
      onChange={(event)=>setPhoneNumberState(event.target.value)}
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
    
export default MemberForm;