package projects.splitwise.service;

import java.util.*;
import projects.splitwise.enums.SplitType;
import projects.splitwise.model.*;
import projects.splitwise.repository.IGroupRepository;

public class GroupService {
    private final IGroupRepository repository;
    private final ExpenseService expenseService;
    private final DebtSimplificationService simplifier;
    private final PaymentService paymentService;

    public GroupService(IGroupRepository repository, ExpenseService expenseService, DebtSimplificationService simplifier, PaymentService paymentService) {
        this.repository = repository;
        this.expenseService = expenseService;
        this.simplifier = simplifier;
        this.paymentService = paymentService;
    }

    public String createGroup(String name, List<User> members){
        Group group = new Group(name);
        for (User member : members) {
            group.addMember(member);
        }
        repository.save(group);
        return group.getId();
    }

    public void addMember(String id, User user) throws IllegalArgumentException{
        Group group = repository.findById(id);
        if(group == null){
            throw new IllegalArgumentException("Did not find any grop with the id - " + id);
        }
        group.addMember(user);
    }

    private Group getGroupById(String id){
        return repository.findById(id);
    }

    public void addExpense(String groupId, String name, double amount, User lender, List<User> borrowers, SplitType splitType, Map<User, Double> meta){
        expenseService.addExpense(getGroupById(groupId), name, amount, lender,borrowers, splitType, meta);
    }

    public void simplifyDebts(String groupId){
        this.simplifier.simplify(getGroupById(groupId));
    }

    public String recordPayment(String groupId, User payer, User payee, double amount){
        return this.paymentService.recordPayment(getGroupById(groupId), payer, payee, amount);
    }

    public void displayBalances(String groupId){
        Group group = getGroupById(groupId);
        boolean anyDebt = false;
        for (User user : group.getMembers()) {
            BalanceSheet balanceSheet = group.getUserBalanceSheet(user);
            for (Map.Entry<User, Double> entry : balanceSheet.getBalances().entrySet()) {
                if (entry.getValue() > 0) {
                    anyDebt = true;
                    System.out.printf("%s owes %s: %.2f%n", entry.getKey().getName(), user.getName(), entry.getValue());
                }
            }
        }
        if (!anyDebt) {
            System.out.println("All settled up -- no outstanding balances.");
        }
    }
}
