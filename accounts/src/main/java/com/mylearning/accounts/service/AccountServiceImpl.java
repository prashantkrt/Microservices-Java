package com.mylearning.accounts.service;

import com.mylearning.accounts.constants.ApplicationConstants;
import com.mylearning.accounts.dto.AccountDto;
import com.mylearning.accounts.dto.CustomerDto;
import com.mylearning.accounts.entity.Accounts;
import com.mylearning.accounts.entity.Customer;
import com.mylearning.accounts.exception.CustomerAlreadyExistsException;
import com.mylearning.accounts.exception.ResourceNotFoundException;
import com.mylearning.accounts.mapper.AccountsMapper;
import com.mylearning.accounts.mapper.CustomerMapper;
import com.mylearning.accounts.repository.AccountsRepository;
import com.mylearning.accounts.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;


@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    /**
     * @param customerDto-CustomerDto Object
     */

    @Override
    public void createAccount(CustomerDto customerDto) {
        Optional<Customer> existedCustomer = customerRepository.findByMobileNumberOrEmail(customerDto.getMobileNumber(), customerDto.getEmail());
        if (existedCustomer.isPresent()) {
            throw new CustomerAlreadyExistsException("Customer with the same mobile number or email already exists" + customerDto.getMobileNumber() + " " + customerDto.getEmail());
        }
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
//        customer.setCreatedAt(LocalDateTime.now());
//        customer.setCreatedBy("Anonymous");
        Customer savedCustomer = customerRepository.save(customer);
        accountsRepository.save(createNewAccount(savedCustomer));
    }

    private Accounts createNewAccount(Customer customer) {
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long randomAccNumber = 1000000000L + new Random().nextInt(900000000);
        newAccount.setAccountNumber(randomAccNumber);
        newAccount.setAccountType(ApplicationConstants.SAVINGS);
        newAccount.setBranchAddress(ApplicationConstants.ADDRESS);
//        newAccount.setCreatedAt(LocalDateTime.now());
//        newAccount.setCreatedBy("Anonymous");
        return newAccount;
    }

    /**
     * @param mobileNumber-Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */

    @Override
    public CustomerDto fetchAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString()));
        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountDto(AccountsMapper.mapToAccountDto(account, new AccountDto()));
        return customerDto;
    }

    /**
     * @param customerDto-CustomerDto Object
     * @return boolean indicating if the update of the Account details is successful or not
     */

    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountDto accountDto = customerDto.getAccountDto();
        if (accountDto != null) {
            Accounts account = accountsRepository.findById(accountDto.getAccountNumber()).orElseThrow(() -> new ResourceNotFoundException("Account", "AccountNumber", accountDto.getAccountNumber().toString()));
            AccountsMapper.mapToAccounts(accountDto,account);
            account = accountsRepository.save(account);

            Long customerId = account.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new ResourceNotFoundException("Customer","customerId",customerId.toString()));
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated=true;
        }
        return isUpdated;
    }

    /**
     * @param  mobileNumber-Input mobileNumber
     * @return boolean indicating if the deletion of the Account details is successful or not
     */

    @Override
    public boolean deleteAccount(String mobileNumber) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));
        accountsRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;
    }
}
