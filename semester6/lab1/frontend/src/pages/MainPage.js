import React from "react";
import { useHistory } from 'react-router-dom';
function MainPage(){
    const history = useHistory();

    const goToAirports= () =>{
        let path = '/airports';
        history.push(path);
    }

        return (
            <div >
                <button onClick={goToAirports}> Маршрути</button>
            </div>
        );

}

export default MainPage;
