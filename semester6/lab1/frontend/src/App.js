import React from "react";
import {
    BrowserRouter as Router,
    Switch,
    Route,
} from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import Airports from "./pages/Airports";
import Flights from "./pages/Flights";
import Employees from "./pages/Employees";

export default function App() {
    return (
        <Router>
            <div>
                <Switch>
                    <Route path="/login">
                        <LoginPage />
                    </Route>
                    <Route path="/admin">
                        <Flights />
                    </Route>
                    <Route path="/airports">
                        <Airports />
                    </Route>
                    <Route path="/employees">
                        <Employees />
                    </Route>
                    <Route path="/">
                        <Home />
                    </Route>
                </Switch>
            </div>
        </Router>
    );
}

function Home() {
    return <h2>Home</h2>;
}

