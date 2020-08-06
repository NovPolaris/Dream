package com.puyang.filter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class TransactionFilterTest {
    @Mock
    private HttpServletRequest httpServletRequest;
    @Mock
    private HttpServletResponse httpServletResponse;
    @Mock
    private FilterChain filterChain;

    private TransactionFilter transactionFilter;

    @BeforeEach
    public void setUp() {
        transactionFilter = new TransactionFilter();
    }

    @Test
    public void testCommit() {
        transactionFilter.doFilter(httpServletRequest, httpServletResponse, filterChain);
    }

    @Test
    public void testRollback() throws IOException, ServletException {
        doThrow(new IOException()).when(filterChain).doFilter(httpServletRequest, httpServletResponse);
        Exception expected = assertThrows(RuntimeException.class, () ->
                transactionFilter.doFilter(httpServletRequest, httpServletResponse, filterChain));
        assertNotNull(expected);
    }
}
