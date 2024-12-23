package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.Entity.Accounts;
import com.eazybytes.accounts.Entity.Customer;
import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoansDto;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomersService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomersService {
    private AccountsRepository accountsRepository;
    private CustomerRepository customerRepository;

    private CardsFeignClient cardsFeignClient;
    private LoansFeignClient loansFeignClient;

    private class LoansFallback implements LoansFeignClient {
        @Override
        public ResponseEntity<LoansDto> fetchLoanDetails(String correlationId, String mobileNumber) {
            return ResponseEntity.ok(new LoansDto());
        }
    }

    private class CardsFallback implements CardsFeignClient {
        @Override
        public ResponseEntity<CardsDto> fetchCardDetails(String correlationId, String mobileNumber) {
            return ResponseEntity.ok(null);
        }
    }

    @Override
    public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
        Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );

        CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));

        LoansFeignClient loansFallbackClient = new LoansFallback();
        CardsFeignClient cardsFallbackClient = new CardsFallback();

        try {
            ResponseEntity<LoansDto> loansDtoResponseEntity = loansFeignClient.fetchLoanDetails(correlationId, mobileNumber);
            if (loansDtoResponseEntity != null && loansDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
                customerDetailsDto.setLoansDto(loansDtoResponseEntity.getBody());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при вызове LoansFeignClient: " + e.getMessage());
            customerDetailsDto.setLoansDto(loansFallbackClient.fetchLoanDetails(correlationId, mobileNumber).getBody());
        }
        try {
            ResponseEntity<CardsDto> cardsDtoResponseEntity = cardsFeignClient.fetchCardDetails(correlationId, mobileNumber);
            if (cardsDtoResponseEntity != null && cardsDtoResponseEntity.getStatusCode().is2xxSuccessful()) {
                customerDetailsDto.setCardsDto(cardsDtoResponseEntity.getBody());
            }
        } catch (Exception e) {
            System.err.println("Ошибка при вызове CardsFeignClient: " + e.getMessage());
            customerDetailsDto.setCardsDto(cardsFallbackClient.fetchCardDetails(correlationId, mobileNumber).getBody());
        }
        return customerDetailsDto;
    }
}