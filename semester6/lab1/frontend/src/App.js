import React, { Component } from "react";
import { makeStyles } from '@material-ui/core/styles';

import {
    BrowserRouter as Router,
    Route,
    Switch,
    Link,
    Redirect
} from "react-router-dom";

import {AppBar, Box, Button, Container, IconButton, Toolbar, Typography} from "@material-ui/core";
import MenuIcon from "@material-ui/icons/Menu";
import Airports from "./pages/Airports";

const useStyles = makeStyles((theme) => ({
    root: {
        flexGrow: 1
    },
    title: {
        flexGrow: 1
    },
    menuButton: {
        marginRight:  theme.spacing(1)
    },
    toolbar: theme.mixins.toolbar,

}))

const App = () => {

    const classes = useStyles();
    return (
        <div>
            <AppBar  position="sticky">
                <Container fixed>
                    <Toolbar>
                        <IconButton edge="start" color="inherit"  aria-label="menu"
                        className={classes.menuButton}>
                            <MenuIcon />
                        </IconButton>
                        <Typography variant="h6" className={classes.title}> Airline app</Typography>
                        <Box mr={3}>
                            <Button color="inherit" variant="outlined">Log In</Button>
                        </Box>
                        <Button color="secondary" variant="contained">Sign Up</Button>
                    </Toolbar>
                </Container>

            </AppBar>
            <main>
                <Airports  />
            </main>
        </div>
    );
};

export default App;