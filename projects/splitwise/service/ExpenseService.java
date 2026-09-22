package projects.splitwise.service;

import java.util.List;
import java.util.Map;
import projects.splitwise.enums.*;
import projects.splitwise.factory.SplitStrategyFactory;
import projects.splitwise.model.*;
import projects.splitwise.repository.IExpenseRepository;
import projects.splitwise.strategy.ISplitStrategy;

public class ExpenseService {
    private final IExpenseRepository repository;
    private final BalanceSheetService balanceSheetService;

    public ExpenseService(IExpenseRepository repository, BalanceSheetService balanceSheetService) {
        this.repository = repository;
        this.balanceSheetService = balanceSheetService;
    }

    public String addExpense(Group group, String name, double amount, User lender, List<User> borrowers, SplitType splitType, Map<User, Double> meta){
        group.getLock().lock();
        try {
            ISplitStrategy strategy = SplitStrategyFactory.getSplitStrategy(splitType);
            List<Split> splits = strategy.split(amount, borrowers, meta);
            Expense expense = new Expense(name, amount, lender, borrowers, splitType, splits);
            repository.save(expense);
            group.addExpenses(expense);
            balanceSheetService.updateBalances(group, lender, splits);
            return expense.getId();
        } finally {
            group.getLock().unlock();
        }
    }
}
