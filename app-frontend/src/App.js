import { BrowserRouter as Router, Route, Routes } from 'react-router-dom'
import Home from './pages/Home'

import Members from './pages/Members'
import MemberForm from './pages/MemberForm'
import WorkoutClasses from './pages/WorkoutClasses'
import Instructors from './pages/Instructors'
import InstructorForm from './pages/InstructorForm'
import NavigationBar from './components/NavigationBar'
import './App.css'
import ClassForm from './pages/ClassForm'

function App() {
  return (
    
    <Router>
      <NavigationBar />
      <Routes>
        <Route path="/" element={<Home />} ></Route>
       
        <Route path="/Members" element={<Members />} ></Route>
        <Route path="/addMember" element={<MemberForm/>} ></Route>
        <Route path="/Update/:id" element={<MemberForm />} ></Route>

        <Route path="/Instructors" element={<Instructors />}></Route>
        <Route path="/addInstructor" element={<InstructorForm/>} ></Route>
        <Route path="/UpdateInstructor/:id" element={<InstructorForm />} ></Route>

        <Route path="/WorkoutClasses" element={<WorkoutClasses />} ></Route>
        <Route path="/addClass" element={<ClassForm/>} ></Route>

      </Routes>
    </Router>
  );
}

export default App;
