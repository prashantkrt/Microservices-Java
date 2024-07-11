package com.mylearning.accounts.service;

import com.mylearning.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     * @param  customerDto-CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     * @param  mobileNumber-Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */

    public CustomerDto fetchAccount(String mobileNumber);


    /**
     * @param  customerDto-CustomerDto Object
     * @return boolean indicating if the update of the Account details is successful or not
     */

    public boolean updateAccount(CustomerDto customerDto);

    /**
     * @param  mobileNumber-Input mobileNumber
     * @return boolean indicating if the deletion of the Account details is successful or not
     */

    boolean deleteAccount(String mobileNumber);


}
