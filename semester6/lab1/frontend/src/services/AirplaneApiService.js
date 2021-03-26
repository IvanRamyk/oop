export function getAirports(){
    const requestOptions = {
        method: 'Get',
        headers: {},
    };
    return fetch(`http://localhost:8081/airports`, requestOptions).then(response => response.json())
        .then((responseData) => {
            return responseData;
        })
}

export function getFlights(){
    const requestOptions = {
        method: 'Get',
        headers: {},
    };
    return fetch(`http://localhost:8081/flights`, requestOptions).then(response => response.json())
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
        body: JSON.stringify({ name: name, city: city, country: country })
    };
    fetch('http://localhost:8081/airports', requestOptions).then(response => response.json()).then(data => console.log(data));
}

export function airlineLogin(login, password){

    console.log(login + " " + password + "!");
    console.log({ login: login, password: password });
    const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ login: login, password: password})
    };
    return fetch('http://localhost:8081/login', requestOptions).then(response => response.json()).then((data) => {
        console.log(data);
        return data;
    });
}