import React from "react";
import {Box, Container} from "@material-ui/core";
import {getFlights} from "../services/AirplaneApiService";
import Header from "../components/Header";
import FlightsList from "../components/FlightsList";




class Flights extends React.Component {


    state = {
        flights: []
    }

    gettingFlight = async () => {
        let flights = await getFlights()
        this.setState({
            flights: flights
        });

    }

    constructor(props) {
        super(props);
        this.gettingFlight();
    }

    render() {
        console.log(this.state.flights);
        return (
            <Box>
                <Header />
                <Container fixed>
                    <FlightsList flights={this.state.flights} />
                </Container>
            </Box>
        );
    }
}


export default Flights;
