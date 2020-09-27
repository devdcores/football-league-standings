package com.football.standings.footballleaguestandings.exception;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;
import lombok.extern.slf4j.Slf4j;

import java.net.SocketTimeoutException;

@Slf4j
public class ClientErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(String s, Response response) {
        if (response.status() == 502) {
            log.warn("Got 502 error, Retrying..");
            throw new RetryableException(response.status(), response.reason(), response.request().httpMethod(), new SocketTimeoutException("502 error"), null, response.request());
        }

        return new RuntimeException("Internal Server Error!! Football Api didn't respond!!");
    }
}
