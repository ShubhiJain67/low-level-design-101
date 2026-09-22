package projects.splitwise.repository;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import projects.splitwise.model.Expense;

public class InMemoryExpenseRepository implements IExpenseRepository {
    private final Map<String, Expense> expenses;

    public InMemoryExpenseRepository() {
        this.expenses = new ConcurrentHashMap<>();
    }

    @Override
    public Expense findById(String id) {
        return this.expenses.get(id);
    }

    @Override
    public void save(Expense expense) {
        this.expenses.put(expense.getId(), expense);
    }
}
