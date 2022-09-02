import React from 'react';

function DashboardHeader() {

  // STATE WHICH WE WILL USE TO TOGGLE THE MENU ON HAMBURGER BUTTON PRESS
  //const [toggleMenu, setToggleMenu] = useState(false);

  return (
    <nav className="flex items-center justify-between justify-around flex-wrap bg-red-500 p-2 text-white">
      <div className="left">
        <a id="home" href="" className="pr-4">Home</a>
        <a id="pantry" href="pantry">Pantry</a>
      </div>
      <h1 className="">Foodimizer</h1>
      <div className="right">
        <a id="search" className="pr-4">Search</a>
        <a id="menu">Menu</a>
      </div>
    </nav>
  );
}

export default DashboardHeader;
