package projects.splitwise.service;

import java.util.*;

import projects.splitwise.model.*;

public class DebtSimplificationService {
    public void simplify(Group group) {
        group.getLock().lock();
        try {
            List<User> members = group.getMembers();
            Map<User, BalanceSheet> balanceSheets = group.getBalanceSheets();

            Map<User,Double> netBalances = new HashMap<>();

            for (User lender : members) {
                double totalPendingAmount = 0;
                Map<User, Double> balances = balanceSheets.get(lender).getBalances();
                for(double amount : balances.values()){
                    totalPendingAmount += amount;
                }
                netBalances.put(lender, totalPendingAmount);
                balanceSheets.get(lender).clear();
            }


            PriorityQueue<User> lenders = new PriorityQueue<>((a,b) -> Double.compare(netBalances.get(a), netBalances.get(b)));
            PriorityQueue<User> borrowers = new PriorityQueue<>((a,b) -> Double.compare(netBalances.get(b), netBalances.get(a)));

            for(User user: netBalances.keySet()){
                if(netBalances.get(user) > 0){
                    lenders.offer(user);
                } else if(netBalances.get(user) < 0){
                    borrowers.offer(user);
                }
            }

            while(!lenders.isEmpty() && !borrowers.isEmpty()){
                User lender = lenders.poll();
                User borrower = borrowers.poll();

                double lendedAmount = netBalances.get(lender);
                double borrowedAmount = netBalances.get(borrower);

                double settledAmount = Math.min(lendedAmount, -borrowedAmount);

                balanceSheets.get(lender).addBalance(borrower, settledAmount);
                balanceSheets.get(borrower).addBalance(lender, -settledAmount);

                netBalances.put(lender, lendedAmount-settledAmount);
                netBalances.put(borrower, borrowedAmount+settledAmount);

                if(netBalances.get(lender) > 0){
                    lenders.offer(lender);
                }
                if(netBalances.get(borrower) < 0){
                    borrowers.offer(borrower);
                }

            }
        } finally {
            group.getLock().unlock();
        }
    }
}
