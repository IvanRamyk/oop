import React from "react";
import AirportList from "../components/airports_list";
import {Box, Container, Grid, Paper} from "@material-ui/core";
import {getAirports} from "../services/AirplaneApiService";




class Airports extends React.Component {


  state = {
    airports: []
  }

  gettingFlight = async () => {
      let airs = await getAirports()
      this.setState({
          airports: airs
      });

  }

  constructor(props) {
    super(props);
    this.gettingFlight();
  }

  render() {
    console.log(this.state.airports);
    return (
        <Box>
            <Container fixed>
                <AirportList airports={this.state.airports} />
            </Container>
        </Box>
    );
  }
}


export default Airports;
