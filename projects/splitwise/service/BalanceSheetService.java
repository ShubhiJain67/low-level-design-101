package projects.splitwise.service;

import java.util.List;

import projects.splitwise.model.*;

public class BalanceSheetService {
    public void updateBalances(Group group, User lender, List<Split> splits){
        BalanceSheet lenderBalanceSheet = group.getUserBalanceSheet(lender);
        double amountPaidByLender = 0;
        for (Split split : splits) {
            amountPaidByLender += split.getAmount();
            User borrower = split.getUser();
            BalanceSheet borrowerBalanceSheet = group.getUserBalanceSheet(borrower);
            double borrowedAmount = split.getAmount();
            borrowerBalanceSheet.addExpense(borrowedAmount);
            if(!borrower.equals(lender)){
                borrowerBalanceSheet.addBalance(lender, -borrowedAmount);
                lenderBalanceSheet.addBalance(borrower, borrowedAmount);
            }
        }
        lenderBalanceSheet.addPaid(amountPaidByLender);

    }
}
