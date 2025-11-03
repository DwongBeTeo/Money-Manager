package tttn.duong.moneyManager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tttn.duong.moneyManager.dto.ExpenseDTO;
import tttn.duong.moneyManager.dto.IncomeDTO;
import tttn.duong.moneyManager.dto.RecentTransactionDTO;
import tttn.duong.moneyManager.entity.ProfileEntity;

import java.math.RoundingMode;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Stream.concat;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final IncomeService incomeService;
    private final ExpenseService expenseService;
    private final ProfileService profileService;

    public Map<String,Object> getDashboardData() {
        ProfileEntity profile = profileService.getCurrentProfile();
        Map<String,Object> returnValue = new LinkedHashMap<>();
        List<IncomeDTO> lastestIncomes = incomeService.getLastest5IncomesForCurrentUser();
        List<ExpenseDTO> lastestExpenses = expenseService.getLastest5ExpensesForCurrentUser();


        List<RecentTransactionDTO> recentTransactions = concat(lastestIncomes.stream().map(income ->
                RecentTransactionDTO.builder()
                        .id(income.getId())
                        .profileId(profile.getId())
                        .icon(income.getIcon())
                        .name(income.getName())
                        .amount(income.getAmount())
                        .date(income.getDate())
                        .createdAt(income.getCreatedAt())
                        .updatedAt(income.getUpdatedAt())
                        .type("income")
                        .build()), lastestExpenses.stream().map(expense ->
                                        RecentTransactionDTO.builder()
                                                .id(expense.getId())
                                                .profileId(profile.getId())
                                                .icon(expense.getIcon())
                                                .name(expense.getName())
                                                .amount(expense.getAmount())
                                                .date(expense.getDate())
                                                .createdAt(expense.getCreatedAt())
                                                .updatedAt(expense.getUpdatedAt())
                                                .type("expense")
                                                .build()))
                .sorted((a,b) -> {
                    int cmp = b.getDate().compareTo(a.getDate());
                    if(cmp == 0 && a.getCreatedAt() != null && b.getCreatedAt() != null){
                        return b.getCreatedAt().compareTo(a.getCreatedAt());
                    }
                    return cmp;
                }).collect(Collectors.toList());
        returnValue.put("totalBalance",
                incomeService.getTotalIncomesForCurrentUser()
                        .subtract(expenseService.getTotalExpenseForCurrentUser()));
        returnValue.put("totalIncome", incomeService.getTotalIncomesForCurrentUser());
        returnValue.put("totalExpense", expenseService.getTotalExpenseForCurrentUser());
        returnValue.put("recent5Expenses", lastestExpenses);
        returnValue.put("recent5Incomes", lastestIncomes);
        returnValue.put("recentTransaction",recentTransactions);
        return returnValue;
    }
}
