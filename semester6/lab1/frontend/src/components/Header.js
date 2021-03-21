import React, { Component } from "react";
import { makeStyles } from '@material-ui/core/styles';

import {AppBar, Box, Button, Container, IconButton, Toolbar, Typography} from "@material-ui/core";
import MenuIcon from "@material-ui/icons/Menu";

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

const Header = () => {

    const classes = useStyles();
    return (
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

        </AppBar>);
};

export default Header;