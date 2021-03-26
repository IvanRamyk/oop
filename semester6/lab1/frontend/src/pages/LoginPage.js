import React from "react";
import AirportList from "../components/airports_list";
import {Box, Container, createMuiTheme, Grid, MuiThemeProvider, Paper} from "@material-ui/core";
import {getAirports} from "../services/AirplaneApiService";
import LoginForm from "../components/LoginForm";



const themeDark = createMuiTheme({
    palette: {
        background: {
            default: "#222222",
        }

    },
});

class LoginPage extends React.Component {


    render() {
        return (
            <MuiThemeProvider theme = { themeDark }>
                <Grid
                    container
                    spacing={0}
                    direction="column"
                    alignItems="center"
                    justify="center"
                    style={{ minHeight: '100vh' }}
                    >

                    <Grid item xs={3}>
                    <LoginForm />
                    </Grid>

                </Grid>
            </MuiThemeProvider>
        );
    }
}


export default LoginPage;
