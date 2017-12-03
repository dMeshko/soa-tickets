package finki.ukim.mk.soatickets.controllers;

import finki.ukim.mk.soatickets.business.services.IInvoiceService;
import finki.ukim.mk.soatickets.business.services.IUsersService;
import finki.ukim.mk.soatickets.business.view.models.tickets.InvoiceViewModel;
import finki.ukim.mk.soatickets.business.view.models.user.UserViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

/**
 * Created by DarkoM on 03.12.2017.
 */

@RestController
@RequestMapping(value = "/api/invoices", produces = MediaType.APPLICATION_JSON_VALUE)
public class InvoicesController {
    @Autowired
    private IInvoiceService invoiceService;

    @Autowired
    private IUsersService usersService;

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('admin')")
    public List<InvoiceViewModel> getAllInvoices(Principal principal, @PathVariable Long id) throws Exception {
        return invoiceService.getAllForUser(id);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @PreAuthorize("hasAnyRole('user')")
    public List<InvoiceViewModel> getMyInvoices(Principal principal) throws Exception {
        UserViewModel currentUser = usersService.findByEmail(principal.getName());
        return invoiceService.getAllForUser(currentUser.getId());
    }
}
