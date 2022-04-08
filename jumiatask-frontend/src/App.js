import { useState, useEffect } from 'react';
import './App.css';
import Table from 'react-bootstrap/Table';
import Container from 'react-bootstrap/Container';
import Service from './ComponentService/Services'; 


function App() { 
  const [customers, setCustomers] = useState([]);
  const [countrySelectedOption, setCountrySelectedOption] = useState('')
  const [stateSelectedOption, setStateSelectedOption] = useState('')
  const [countrycodeSelectedOption, setCountrycodeSelectedOption] = useState('')
 

  const getUsers = () =>{
    Service.getCustomers()
        .then((res) => {
          setCustomers(res.data);
        })
        .catch((err) => console.log(err))
  }


  const handleStateSelect = (e) =>{
    setStateSelectedOption(e.target.value);    
  }


  const handleCountrySelect = (e) =>{
    setCountrySelectedOption(e.target.value);
  }

  const handleCountryCodeSelect = (e) =>{
    setCountrycodeSelectedOption(e.target.value);
  }


  const getCustomersByCountry = (countrySelectedOption) =>{
    Service.getCustomersByCountry(countrySelectedOption)
      .then((res) => {
        setCustomers(res.data);
      })
      .catch((err) => console.log(err));
  }
 
  const getCustomersByCountryCode = (handleCountryCodeSelect) =>{
    handleCountryCodeSelect = "237"

    Service.getCustomersWithCoutryCode(handleCountryCodeSelect)
       .then((res) => {
         setCustomers(res.data);
       })
       .catch((err) => console.log(err));
 
    
    } 
 
 const getCustomersByCountryAndValidity = (countrySelectedOption, stateSelectedOption) =>{
   Service.getCustomersByCountryAndValidity(countrySelectedOption, stateSelectedOption)
    .then(res => setCustomers(res.data))
    .catch(err => console.log(err));
 }

  const getUsersFilter = () =>{

    if (!countrySelectedOption && !stateSelectedOption && !countrycodeSelectedOption) {
       
      getUsers();
    
    } else if (countrySelectedOption && !stateSelectedOption && !countrycodeSelectedOption) {
     
      getCustomersByCountry(countrySelectedOption);
    
    } else if (countrycodeSelectedOption && !stateSelectedOption && !countrySelectedOption) {
      
      getCustomersByCountryCode(countrycodeSelectedOption);
    
    } else getCustomersByCountryAndValidity(countrySelectedOption, stateSelectedOption);
  }

  const resetFilter = () =>{
    window.location.reload(true)
  }

  useEffect(() => {
 
    getUsersFilter();

  }, [countrySelectedOption, stateSelectedOption])
  

  return (
    <div className="App">
     

      <Container>

      <h1 className='text-center my-5'>Countries and Phone Numbers</h1>

      <h5>Filter By:</h5>
      <div className='row'>
 
        <div className='col-lg-3'>
          <label>Country</label>
          <select className='form-select my-4 bg-dark text-white col-lg-6' onChange={handleCountrySelect}>
            <option value={''}>Select Country</option>
            <option value={"morocco"}>Morocco</option>
            <option value={"Uganda"}>Uganda</option>
            <option value={"Mozambique"}>Mozambique</option>
            <option value={"Cameroon"}>Cameroon</option>
            <option value={"Ethiopia"}>Ethiopia</option> 
          </select>
        </div> 


        <div className='col-lg-3'>
        <label>State</label>
          <select className='form-select my-4 bg-dark text-white col-lg-6' onChange={handleStateSelect}>
            <option value={''}>Select State</option>
            <option value={"valid"}>Valid</option>
            <option value={"invalid"}>Invalid</option>
          </select>
        </div>

   

        <div className='col-lg-6 d-flex justify-content-end align-items-center'>
            <button className='btn btn-primary' onClick={resetFilter}>RESET</button>
        </div>

      </div>
      
          <Table responsive striped bordered hover variant="dark" className='my-4'>
            <thead>
              <tr>
                <th>Name</th>
                <th>Phone</th>
                <th>Country</th>
                <th>Country Code</th>
                <th>State</th>
              </tr>
            </thead>
            <tbody>
              { customers.map((customer) =>(
                <tr key={customer.id}>
                  <td>{customer.name}</td>
                  <td>{customer.phone}</td>
                  <td>{customer.country}</td>
                  <td>{customer.countryCode}</td>
                  <td>{customer.state}</td>
                </tr>
              ))}

            </tbody>
        </Table>

      </Container>



    </div>
  );
}

export default App;
