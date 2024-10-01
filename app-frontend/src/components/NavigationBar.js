import {Link} from "react-router-dom";
import {Button,Flex} from "antd";
import './NavigationBar.css';
const NavigationBar =() =>{
    return(
        <nav className="navBar">
            <Flex gap="small" wrap>
                <Link to='/Members'><Button type="primary">Members</Button></Link>
                <Link to='/Instructors'><Button type="primary"> Instructors</Button></Link>
                <Link to='/WorkoutClasses'><Button type="primary">WorkoutClasses</Button></Link>
            </Flex>
        </nav>
        

    )
};
export default NavigationBar;