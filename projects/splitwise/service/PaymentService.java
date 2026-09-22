package projects.splitwise.service;

import projects.splitwise.model.*;
import projects.splitwise.repository.IPaymentRepository;

public class PaymentService {
    private final IPaymentRepository repository;

    public PaymentService(IPaymentRepository repository) {
        this.repository = repository;
    }

    public String recordPayment(Group group, User payer, User payee, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Payment amount must be positive");
        }
        if (payer.equals(payee)) {
            throw new IllegalArgumentException("Payer and payee cannot be the same person");
        }

        group.getLock().lock();
        try {
            BalanceSheet payerSheet = group.getUserBalanceSheet(payer);
            BalanceSheet payeeSheet = group.getUserBalanceSheet(payee);

            payerSheet.addBalance(payee, amount);
            payeeSheet.addBalance(payer, -amount);

            Payment payment = new Payment(payer, payee, amount);
            repository.save(payment);
            return payment.getId();
        } finally {
            group.getLock().unlock();
        }
    }
}
