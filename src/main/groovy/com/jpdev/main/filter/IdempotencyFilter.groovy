package com.jpdev.main.filter

import com.jpdev.main.repository.IdempotencyRepository
import org.slf4j.Logger;
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
@Order(1)
public class IdempotencyFilter implements Filter {

    @Autowired
    private final IdempotencyRepository idempotencyRepository

    private final static Logger LOG = LoggerFactory.getLogger(IdempotencyFilter.class)

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        LOG.info("Initializing filter :{}", this)
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        if (!before(req, response as HttpServletResponse)) return

        chain.doFilter(request, response);

        LOG.info("Committing Transaction for req :{}", req.getRequestURI())
    }

    @Override
    public void destroy() {
        LOG.warn("Destructing filter :{}", this)
    }

    private Boolean before(final HttpServletRequest request, final HttpServletResponse response) {
        String idempotencyKey = request.getHeader("idempotency-key")
        if (!idempotencyKey) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value())
            return false
        }

        // TODO: save and encrypt
        if (idempotencyRepository.findById(idempotencyKey)) {
            response.setStatus(HttpStatus.CONFLICT.value())
            return false
        }


        return true
    }
}