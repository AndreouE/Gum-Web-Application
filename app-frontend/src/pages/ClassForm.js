import React, { useState} from 'react';
import { Button,  Form, Input } from 'antd';
import {createClass} from '../components/HandleRequest';




const onFinish = (values) => {
  console.log('Success:', values);
};
const onFinishFailed = (errorInfo) => {
  console.log('Failed:', errorInfo);
};


const ClassForm = () =>{
    const [className,setClassName]=useState(" ")
    const [costOfClass,setCostOfClass]=useState(0)

    const HandleSubmit =(event) =>{
        event.preventDefault()
        const converted_costOfClass=parseInt(costOfClass)

        const newClass={className,converted_costOfClass}
        console.log("new ME",newClass)

        createClass(newClass).then((response) =>{
            console.log("response",response.data)
         })
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
      label="ClassName"
      name="Class Name"
      value={className}
      onChange={(event)=>setClassName(event.target.value)}

      rules={[
        {
          required: true,
          message: 'Please enter Class Name ',
        },
      ]}
    >
      <Input />
    </Form.Item>

    <Form.Item
      label="Cost of class"
      name="Cost of class"
      value={costOfClass}
      onChange={(event)=>setCostOfClass(event.target.value)}
      rules={[
        {
          required: true,
          message: 'Please enter cost of class!',
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
export default ClassForm;