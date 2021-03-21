import React from "react";
import AirportList from "../components/airports_list";
import AirportsPageInfo from "../components/AirportPageInfo";
import {Box, Container, Grid, Paper} from "@material-ui/core";




class Airports extends React.Component {


  state = {
    airports: [
        {
            "name": "Boryspil",
            "city": "Kyiv",
            "country": "Ukraine"
        }
    ]
  }

  gettingFlight = async () => {
    /*fetch(`http://localhost:8081/airports`)
        .then(res => res.json())
        .then((data) => {
          this.setState({ airports: data });

          console.log(data);
        })
        .catch(console.log)*/
      this.setState({
          airports: [
              {
                  "name": "A",
                  "city": "A city",
                  "country": "Ukraine"
              }
          ]
      });

  }

  constructor(props) {
    super(props);
    //this.gettingFlight();
  }

  render() {
    return (
        <Box>
            <Container fixed>
                <Grid container>
                    <Grid item md={6}></Grid>
                </Grid>
                <AirportList airports={this.state.airports} />
            </Container>
        </Box>
    );
  }
}


export default Airports;
