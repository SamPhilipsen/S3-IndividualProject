import React from "react"
import ReactDOM from "react-dom"
import { BrowserRouter as Router } from "react-router-dom";

//stylesheet
import "./App.css"

//component file
import MainSite from "./components/MainSite"

ReactDOM.render(
    <React.StrictMode>
        <Router>
            <MainSite/>
        </Router>
    </React.StrictMode>,
    document.getElementById("root")
);