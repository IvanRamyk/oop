import React from "react";
import Info from './components/info';
import Form from './components/form';
import Flight from './components/Flight';
import Airports from './components/airports'

class Airport {
  constructor(id, name, city, country) {
    this.id = id;
    this.name = name;
    this.city = city;
    this.country = country;
  }
}

class App extends React.Component {


	state = {
		airports: []
	}

  gettingFlight = async () => {
    fetch(`http://localhost:8081/airports`)
        .then(res => res.json())
        .then((data) => {
          this.setState({ airports: data });

    	  console.log(data);
        })
        .catch(console.log)

  }

  constructor(props) {
          super(props);
   	this.gettingFlight();
  }

  render() {
    return (
      <div	>
      <Info />
      <Form />
      <Flight />
      <Airports airports={this.state.airports} />
      </div>
    );
  }
}

export default App;
