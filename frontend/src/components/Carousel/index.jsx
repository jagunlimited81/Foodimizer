import React from 'react';
import 'tw-elements';

function Carousel() {

  // STATE WHICH WE WILL USE TO TOGGLE THE MENU ON HAMBURGER BUTTON PRESS
  //const [toggleMenu, setToggleMenu] = useState(false);

  return (
    <div id="carouselExampleCaptions" className="carousel slide relative" data-bs-ride="carousel">
      <div className="carousel-indicators absolute right-0 bottom-0 left-0 flex justify-center p-0 mb-4">
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="0"
          className="active"
          aria-current="true"
          aria-label="Slide 1"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="1"
          aria-label="Slide 2"
        ></button>
        <button
          type="button"
          data-bs-target="#carouselExampleCaptions"
          data-bs-slide-to="2"
          aria-label="Slide 3"
        ></button>
      </div>
      <div className="carousel-inner relative w-full overflow-hidden">
        <div className="carousel-item active relative float-left w-full h-96">
          <img
            src="https://external-content.duckduckgo.com/iu/?u=https%3A%2F%2Flubbockimpact.com%2F_2018%2Fwp-content%2Fuploads%2F2017%2F11%2Fpantry1.jpg&f=1&nofb=1"
            className="block w-full "
            alt="..."
          />
          <div className="carousel-caption hidden md:block absolute text-center">
            <h5 className="text-2xl text-white font-bold ">What&aposs in your fridge, pantry, bar?</h5>
            <p>We provide cooking inspiration.</p>
          </div>
        </div>
        <div className="carousel-item relative float-left w-full h-96">
          <img
            src="https://3nao9w26pylg2kxfi61opwp4-wpengine.netdna-ssl.com/wp-content/uploads/2017/03/480740127.jpg"
            className="block w-full "
            alt="..."
          />
          <div className="carousel-caption hidden md:block absolute text-center">
            <h5 className="text-xl">Build recipies using ingredients on hand</h5>
            <p>Plug in what you have, and let us help you plan your meal in the most convenient way possible!</p>
          </div>
        </div>
        <div className="carousel-item relative float-left w-full h-96">
          <img
            src="https://sleeklens.com/wp-content/uploads/2016/08/DSC06051.jpg"
            className="block w-full "
            alt="..."
          />
          <div className="carousel-caption hidden md:block absolute text-center">
            <h5 className="text-xl">Save Recipies cusoomized to your food profile</h5>
            <p>Food allergies? Hate mustard or olives? Vegan? We&aposve got you covered.</p>
          </div>
        </div>
      </div>
      <button
        className="carousel-control-prev absolute top-0 bottom-0 flex items-center justify-center p-0 text-center border-0 hover:outline-none hover:no-underline focus:outline-none focus:no-underline left-0"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="prev"
      >
        <span className="carousel-control-prev-icon inline-block bg-no-repeat" aria-hidden="true"></span>
        <span className="visually-hidden">Previous</span>
      </button>
      <button
        className="carousel-control-next absolute top-0 bottom-0 flex items-center justify-center p-0 text-center border-0 hover:outline-none hover:no-underline focus:outline-none focus:no-underline right-0"
        type="button"
        data-bs-target="#carouselExampleCaptions"
        data-bs-slide="next"
      >
        <span className="carousel-control-next-icon inline-block bg-no-repeat" aria-hidden="true"></span>
        <span className="visually-hidden">Next</span>
      </button>
    </div>
  );
}

export default Carousel;
