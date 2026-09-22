package projects.splitwise.repository;

import projects.splitwise.model.Expense;

public interface IExpenseRepository {
    Expense findById(String id);
    void save(Expense group);
}
