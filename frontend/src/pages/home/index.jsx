import React from 'react';
import SiteHeader from "../../components/SiteHeader";
import SiteFooter from "../../components/SiteFooter";
import SearchBox from '../../components/SearchBox/SearchBox';

const Home = () => {
     return (<>
          <SiteHeader />
          <SearchBox />
          <SiteFooter />
     </>
     )
}

export default Home;