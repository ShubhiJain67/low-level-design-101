package projects.splitwise;

import java.util.*;
import projects.splitwise.enums.SplitType;
import projects.splitwise.model.*;
import projects.splitwise.repository.*;
import projects.splitwise.service.*;

public class Main {
    private static GroupService groupService;
    public static void main(String[] args) {
        start();
        User shubhi = new User("Shubhi");
        User prateek = new User("Prateek");
        User akansha = new User("Akansha");
        User rishabh = new User("Rishabh");

        List<User> members = new ArrayList<>(List.of(shubhi, prateek, akansha, rishabh));
        String shimlaGroupId = groupService.createGroup("Shimla", members);


        groupService.addExpense(
            shimlaGroupId,
            "Flight",
            50000,
            akansha,
            members,
            SplitType.EQUAL,
            null
        );
        groupService.addExpense(
            shimlaGroupId,
            "Hotel",
            60000,
            prateek,
            members,
            SplitType.EQUAL,
            null
        );
        groupService.addExpense(
            shimlaGroupId,
            "Food",
            10000,
            prateek,
            members,
            SplitType.EQUAL,
            null
        );
        groupService.addExpense(
            shimlaGroupId,
            "Activities",
            100000,
            shubhi,
            members,
            SplitType.EQUAL,
            null
        );
        System.err.println("Before Simplifying Debts\n");
        groupService.displayBalances(shimlaGroupId);
        groupService.simplifyDebts(shimlaGroupId);
        System.err.println("\n\nAfter Simplifying Debts\n");
        groupService.displayBalances(shimlaGroupId);

        groupService.recordPayment(shimlaGroupId, rishabh, shubhi, 50000);
        System.err.println("\n\nAfter Rishabh pays Shubhi 5000\n");
        groupService.displayBalances(shimlaGroupId);
    }

    private static void start() {
        IGroupRepository groupRepo = new InMemoryGroupRepository();
        IExpenseRepository expenseRepo = new InMemoryExpenseRepository();
        IPaymentRepository paymentRepo = new InMemoryPaymentRepository();
        BalanceSheetService balanceSheetService = new BalanceSheetService();

        DebtSimplificationService simplifier = new DebtSimplificationService();
        ExpenseService expenseService = new ExpenseService(expenseRepo, balanceSheetService);
        PaymentService paymentService = new PaymentService(paymentRepo);

        groupService = new GroupService(groupRepo, expenseService, simplifier, paymentService);
    }
}
