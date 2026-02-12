package net.javaguides.usermanagement.web;

import static org.mockito.Mockito.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class UserServletTest {

    @InjectMocks
    private UserServlet servlet;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher dispatcher;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testDoGet_NewAction() throws Exception {
        // Simuler le chemin "/new"
        when(request.getServletPath()).thenReturn("/new");
        when(request.getRequestDispatcher("user-form.jsp")).thenReturn(dispatcher);

        servlet.doGet(request, response);

        // Vérifier que le dispatcher a bien été appelé vers la bonne page
        verify(dispatcher).forward(request, response);
    }
}