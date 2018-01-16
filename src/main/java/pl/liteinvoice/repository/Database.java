package pl.liteinvoice.repository;

import pl.liteinvoice.model.business.Invoice;

import java.util.List;

public interface Database {

  void saveInvoice(Invoice invoice);

  List<Invoice> getInvoices();

  boolean removeInvoice(int invoiceId);

  boolean replaceInvoice(int invoiceId, Invoice invoice);
}
