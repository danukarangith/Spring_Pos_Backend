package lk.ijse.spring_pos_backend.service.impl;

import lk.ijse.spring_pos_backend.dao.CustomerDAO;
import lk.ijse.spring_pos_backend.dto.CustomerDTO;
import lk.ijse.spring_pos_backend.entity.CustomerEntity;
import lk.ijse.spring_pos_backend.service.CustomerService;
import lk.ijse.spring_pos_backend.util.DateTimeUtil;
import lk.ijse.spring_pos_backend.util.MappingUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomerServiceIMPL implements CustomerService {
    @Autowired
    private final CustomerDAO customerDAO;

    @Autowired
    private final MappingUtil mappingUtil;

    @Override
    public String saveCustomer(CustomerDTO customerDTO) {
        customerDTO.setCustomerId(generateCustomerID());
        customerDTO.setName(customerDTO.getFirstName() + " " + customerDTO.getLastName());
        customerDTO.setLastUpdatedAt(DateTimeUtil.getCurrentDateTime());
        CustomerEntity customerEntity = mappingUtil.convertToCustomerEntity(customerDTO);
        customerDAO.save(customerEntity);
        System.out.println("Customer saved : " + customerEntity);
        return "Customer saved successfully";
    }
}