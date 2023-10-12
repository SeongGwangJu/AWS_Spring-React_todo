import { Route, Routes } from 'react-router-dom';
import './App.css';
import RootLayout from './components/Layouts/RootLayout/RootLayout';
import Home from './pages/Home/Home';
import Signup from './pages/Signup/Signup';
import Signin from './pages/Signin/Signin';
import AuthRouter from './AuthRouter/AuthRouter';

function App() {
  return (
    <>
    <RootLayout>
      <Routes>
        <Route path='/' element={ <AuthRouter element={ <Home />} />} />
        <Route path='/accounts/signup' element={<Signup />} />
        <Route path='/accounts/login' element={<Signin />} />
      </Routes>
    </RootLayout>
    </>

  );
}

export default App;
