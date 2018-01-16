package pl.liteinvoice.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import pl.liteinvoice.model.business.Invoice;

import java.util.List;


@Repository
@Transactional
@Service
@ConditionalOnProperty(name = "pl.liteinvoice.repository", havingValue = "sqlDatabase")
public class DatabaseImpl implements Database {

  @PersistenceContext
  private EntityManager entityManager;

  @Override
  public void saveInvoice(Invoice invoice) {
    entityManager.persist(invoice);
  }

  @Override
  public List<Invoice> getInvoices() {
    Query query = entityManager.createNativeQuery("SELECT * FROM invoice;", Invoice.class);
    return query.getResultList();
  }

  @Override
  public boolean removeInvoice(int invoiceId) {
    Invoice invoiceToRemove = entityManager.getReference(Invoice.class, invoiceId);
    if (invoiceToRemove != null) {
      entityManager.remove(invoiceToRemove);
      return true;
    }
    return false;
  }

  @Override
  public boolean replaceInvoice(int invoiceId, Invoice invoice) {
    TypedQuery<Invoice> query = entityManager
        .createQuery("SELECT i FROM Invoice i WHERE i.invoiceId =?1", Invoice.class);
    Invoice invoiceToReplace = query.setParameter(1, invoiceId).getSingleResult();
    if (invoiceToReplace != null) {
      invoice.setInvoiceId(invoiceId);
      entityManager.merge(invoice);
      return true;
    }
    return false;
  }
}
