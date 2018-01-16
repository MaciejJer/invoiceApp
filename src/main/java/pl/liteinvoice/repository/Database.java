package pl.liteinvoice.repository;

import org.springframework.stereotype.Service;
import pl.liteinvoice.model.business.Invoice;

import java.util.List;

@Service
public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  boolean removeInvoice(int invoiceId);

  boolean replaceInvoice(int invoiceId, Invoice invoice);
}
