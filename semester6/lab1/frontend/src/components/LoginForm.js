import React from 'react';
import { Paper, withStyles, Grid, TextField, Button, FormControlLabel, Checkbox } from '@material-ui/core';
import { Face, Fingerprint } from '@material-ui/icons'
import {airlineLogin, postAirport} from "../services/AirplaneApiService";
import {useHistory} from "react-router-dom";
const styles = theme => ({
    margin: {
        margin: theme.spacing(2),
    },
    padding: {
        padding: theme.spacing(1)
    }
});

function LoginTab() {

    let login = '';
    let password = '';
    const history = useHistory();

    const handleLogin = (event) => {
        login = event.target.value;
    }

    const handlePassword = (event) => {
        password = event.target.value;
    }

    const submitForm = async () => {
        let data = await airlineLogin(login, password);
        if (data != null) {
            console.log(data["role"]);
            if (data["role"] === "admin") {
                console.log("pushing..");
                history.push("/admin");
            }
        }
        ;
    }

    //const {classes} = styles;
    return (
        <Paper className={styles.padding}>
            <div className={styles.margin}>
                <Grid container spacing={8} alignItems="flex-end">
                    <Grid item>
                        <Face/>
                    </Grid>
                    <Grid item md={true} sm={true} xs={true}>
                        <TextField id="username" label="Username" type="email" fullWidth autoFocus required
                                   onChange={handleLogin}/>
                    </Grid>
                </Grid>
                <Grid container spacing={8} alignItems="flex-end">
                    <Grid item>
                        <Fingerprint/>
                    </Grid>
                    <Grid item md={true} sm={true} xs={true}>
                        <TextField id="username" label="Password" type="password" fullWidth required
                                   onChange={handlePassword}/>
                    </Grid>
                </Grid>
                <Grid container justify="center" style={{marginTop: '50px'}}>
                    <Button variant="outlined" color="primary" onClick={submitForm}
                            style={{textTransform: "none"}}>Login</Button>
                </Grid>
            </div>
        </Paper>
    );
}

export default withStyles(styles)(LoginTab);