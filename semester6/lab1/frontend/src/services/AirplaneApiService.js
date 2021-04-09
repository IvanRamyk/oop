export function getAirports(){
    const requestOptions = {
        method: 'Get',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/airports`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function deleteAirport(id){
    const requestOptions = {
        method: 'Delete',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/airports/${id}`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function deleteFlight(id){
    const requestOptions = {
        method: 'Delete',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/flights/${id}`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function deleteEmployee(id){
    const requestOptions = {
        method: 'Delete',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/employees/${id}`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function deleteEmployeeFromFlight(employeeId, flightId){
    console.log(employeeId + " " + flightId + "!");
    const requestOptions = {
        method: 'Delete',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ employeeId: employeeId, flightId: flightId}),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/flight_employees', requestOptions).then(response => response.json()).then(data => console.log(data));
}

export function getFlights(){
    const requestOptions = {
        method: 'Get',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/flights`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function getEmployees(){
    const requestOptions = {
        method: 'Get',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/employees`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function getFlightEmployees(flightId){
    const requestOptions = {
        method: 'Get',
        headers: {},
        //credentials: 'include',
    };
    return fetch(`http://localhost:8081/employees?flightId=${flightId}`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}


export function postAirport(name, city, country){
    console.log(name + " " + city + " " + country + "!");
    console.log({ name: name, city: city, country: country });
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ name: name, city: city, country: country }),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/airports', requestOptions).then(response => response.json()).then(data => console.log(data));


}


export function putAirport(id, name, city, country){
    console.log(name + " " + city + " " + country + "!");
    console.log({ name: name, city: city, country: country });
    const requestOptions = {
        method: 'Put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id:id, name: name, city: city, country: country }),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/airports', requestOptions).then(response => response.json()).then(data => console.log(data));


}

export function putEmployee(id, name, position){
    const requestOptions = {
        method: 'Put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id, fullName: name, position: position}),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/employees', requestOptions).then(response => response.json()).then(data => console.log(data));
}


export function postEmployee(name, position){
    console.log(name + " " + position + "!");
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ fullName: name, position: position}),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/employees', requestOptions).then(response => response.json()).then(data => console.log(data));
}


export function postEmployeeForFlight(employeeId, flightId){
    console.log(employeeId + " " + flightId + "!");
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ employeeId: employeeId, flightId: flightId}),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/flight_employees', requestOptions).then(response => response.json()).then(data => console.log(data));
}

export function postFlight(datetime, from, to){
    console.log(datetime + " " + from + " " + to + "!");
    console.log({ datetime: datetime.getTime(), from: from, to: to });
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ datetime: datetime.getTime(), from: from, to: to }),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/flights', requestOptions).then(response => response.json()).then(data => console.log(data));
}

export function putFlight(id, datetime, from, to){
    console.log(datetime + " " + from + " " + to + "!");
    console.log({ datetime: datetime.getTime(), from: from, to: to });
    const requestOptions = {
        method: 'Put',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ id: id, datetime: datetime.getTime(), from: from, to: to }),
        //credentials: 'include',
    };
    fetch('http://localhost:8081/flights', requestOptions).then(response => response.json()).then(data => console.log(data));
}

export function airlineLogin(login, password){

    console.log(login + " " + password + "!");
    console.log({ login: login, password: password });
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ login: login, password: password}),
        //credentials: 'include',
    };
    return fetch('http://localhost:8081/login', requestOptions).then(response => response.json()).then((data) => {
        console.log(data);
        return data;
    });
}

export function airlineLogout(){

    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        //credentials: 'include',
    };
    return fetch('http://localhost:8081/logout', requestOptions).then(response => response.json()).then((data) => {
        console.log(data);
        return data;
    });
}
