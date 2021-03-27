
import React from 'react'
import {
    Box,
    Button,
    ButtonGroup,
    Card,
    CardContent,
    Container,
    Grid,
    Typography
} from "@material-ui/core";

import {makeStyles} from "@material-ui/core/styles";
import AddFlightFormDialog from "./AddFlightFormDialog";
import {getAirports} from "../services/AirplaneApiService";

const useStyles = makeStyles((theme) => ({
    cardButtonGroup: {
        marginRight:  theme.spacing(1),
        marginBottom:  theme.spacing(2),
        marginLeft:  theme.spacing(1.7)
    },

    infoItem: {
        marginBottom:  theme.spacing(3),
        marginTop:  theme.spacing(3),
        marginLeft:  theme.spacing(2),
        marginRight:  theme.spacing(2),
    },

    airportCard: {
        backgroundColor: "#E5F3FF",
        marginBottom: theme.spacing(6),
    }


}))

const zeroPad = (num, places) => String(num).padStart(places, '0')

const FlightsList = ({ flights, airports }) => {
    const classes = useStyles();
    console.log(flights);
    console.log("!");
    return (
        <Container fixed>
            <Grid direction="row" justify="space-between" alignItems="center" container>
                <Grid item className={classes.infoItem}><Typography variant={"h4"} > Flights: </Typography></Grid>
                <Grid item className={classes.infoItem}><AddFlightFormDialog airports={airports}/></Grid>

            </Grid>
            {flights.map((flight) => (
                <Card className={classes.airportCard}>
                    <CardContent>
                        <Grid direction={"row"} container justify={"space-between"}>
                            <Grid item alignItems>
                                <Typography variant="h4" color={"primary"}>{"Airport " + flight.from.name}</Typography>
                                <Typography variant="subtitle1" color={"primary"}>{flight.from.city + ", " + flight.from.country}</Typography>
                            </Grid>
                            <Grid item>
                                <Typography variant="h4" color={"primary"} align={"center"}>
                                    {zeroPad(flight.dateTime.time.hour, 2)
                                + ":" + zeroPad(flight.dateTime.time.minute, 2)}</Typography>

                                <Typography variant="h6" color={"primary"} align={"center"}>{
                                    flight.dateTime.date.day.toString()
                                    + '/' +
                                    flight.dateTime.date.month.toString()
                                    + '/' +
                                    flight.dateTime.date.year.toString()
                                }</Typography>
                            </Grid>
                            <Grid item >
                                <Typography variant="h4" color={"primary"} align={"right"}>{"Airport " + flight.to.name}</Typography>
                                <Typography variant="subtitle1" color={"primary"} align={"right"}>{flight.to.city + ", " + flight.to.country}</Typography>
                            </Grid>
                        </Grid>

                    </CardContent>
                    <Box justifyContent="flex-end" width={"100%"}>
                        <ButtonGroup color="primary" className={classes.cardButtonGroup} variant={"contained"}>
                            <Button>Edit</Button>
                            <Button>Delete</Button>
                        </ButtonGroup>
                    </Box>
                </Card>
            ))}



        </Container>
    );
};

export default FlightsList