import React from 'react';
import './App.css';
import {BrowserRouter, Routes, Route} from 'react-router-dom';
import Home from './pages/home';
import Pantry from './pages/pantry';

const App = () => {
  return (
    <div className="App bg-white">
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/pantry" element={<Pantry />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
};

export default App;
