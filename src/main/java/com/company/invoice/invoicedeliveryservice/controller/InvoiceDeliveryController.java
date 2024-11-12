package com.company.invoice.invoicedeliveryservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
public class InvoiceDeliveryController {

    private static final Random random = new Random();

    @GetMapping("/status")
    public ResponseEntity<String> getServiceStatus() {
        // Get a random HTTP status code from the list of common ones
        HttpStatus randomStatus = getRandomHttpStatus();

        // Map the status to an appropriate message
        String message = getMessageForStatus(randomStatus);

        // Return the response with the selected status and message
        return ResponseEntity.status(randomStatus)
                             .body(message);
    }

    // This method randomly selects an HTTP status from common ones
    private HttpStatus getRandomHttpStatus() {
        // Array of common HTTP status codes
        HttpStatus[] statuses = {
            HttpStatus.OK,                     // 200 OK
            HttpStatus.BAD_REQUEST,            // 400 Bad Request
            HttpStatus.NOT_FOUND,              // 404 Not Found
            HttpStatus.INTERNAL_SERVER_ERROR, // 500 Internal Server Error
            HttpStatus.FORBIDDEN,              // 403 Forbidden
            HttpStatus.UNAUTHORIZED,           // 401 Unauthorized
            HttpStatus.SERVICE_UNAVAILABLE,    // 503 Service Unavailable
            HttpStatus.CREATED                 // 201 Created
        };

        // Get a random index in the array
        int randomIndex = random.nextInt(statuses.length);

        // Return the random status code
        return statuses[randomIndex];
    }

    // This method returns an appropriate message for each status code
    private String getMessageForStatus(HttpStatus status) {
        switch (status) {
            case OK:
                return "Everything is working fine!";
            case BAD_REQUEST:
                return "The request is invalid or malformed.";
            case NOT_FOUND:
                return "The requested resource could not be found.";
            case INTERNAL_SERVER_ERROR:
                return "The server encountered an unexpected condition.";
            case FORBIDDEN:
                return "You don't have permission to access the resource.";
            case UNAUTHORIZED:
                return "You need to log in to access the resource.";
            case SERVICE_UNAVAILABLE:
                return "The service is currently unavailable, please try again later.";
            case CREATED:
                return "The resource was successfully created.";
            default:
                return "An unknown error occurred.";
        }
    }
}