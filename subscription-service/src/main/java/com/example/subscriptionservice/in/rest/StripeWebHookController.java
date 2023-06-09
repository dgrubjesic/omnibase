package com.example.subscriptionservice.in.rest;

import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stripe")
@RequiredArgsConstructor
@Slf4j
public class StripeWebHookController {


    @PostMapping
    public void webhook(@RequestBody Event event) {
        log.info(event.toString());
    }
}
