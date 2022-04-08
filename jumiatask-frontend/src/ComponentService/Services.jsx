import axios from 'axios';

const url ="http://localhost:8086/customers"

class Service{
 

    getCustomers(){
        return axios.get(url);
    }

    getCustomersByCountry(country){
        return axios.get(url + '/' + country);
    }

    getCustomersWithCoutryCode(countryCode){
        return axios.post(url + '/' + countryCode);
    }

    getCustomersByCountryAndValidity(country, validity){
        return axios.get(url + '/' + country + '/' + validity);
    }
 

}

export default new Service()