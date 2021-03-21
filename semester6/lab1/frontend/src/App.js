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
import Header from "./components/Header";

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
            <Header />
            <main>
                <Airports  />
            </main>
        </div>
    );
};

export default App;