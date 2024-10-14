package lk.ijse.spring_pos_backend.controller;

import jakarta.validation.Valid;
import lk.ijse.spring_pos_backend.dto.CustomerDTO;
import lk.ijse.spring_pos_backend.exception.DataPersistFailedException;
import lk.ijse.spring_pos_backend.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {
    @Autowired
    private final CustomerService customerService;

    static Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> saveCustomer(@Valid @RequestBody CustomerDTO customer) {
        if (customer == null) {
            return ResponseEntity.badRequest().build();
        } else {
            try {
                customerService.saveCustomer(customer);
                logger.info("Customer saved : " + customer);
                return ResponseEntity.created(null).build();
            } catch (DataPersistFailedException e) {
                return ResponseEntity.badRequest().build();
            } catch (Exception e) {
                logger.error(e.getMessage());
                return ResponseEntity.internalServerError().build();
            }
        }
    }
}