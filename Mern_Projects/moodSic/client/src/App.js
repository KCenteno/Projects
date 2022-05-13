import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import React, { useState } from 'react';
import { Switch, Route } from "react-router-dom";
import SignIn from './components/SignIn';
import Dashboard from './components/Dashboard';
import GenreChoices from './components/GenreChoices';
import MoodChoices from './components/MoodChoices';



function App() {

  return (
    <div className="App">
      <Switch>

        <Route exact path="/">
          <SignIn/>
        </Route>

        <Route exact path="/dashboard">
            <Dashboard/>
        </Route>

        <Route exact path="/genres">
          <GenreChoices/>
        </Route>

        <Route exact path="/moods">
          <MoodChoices/>
        </Route>

      </Switch>
    </div>
  );
}

export default App;
