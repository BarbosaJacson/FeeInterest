import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;

public class ContractService {
    private OnlinePaymentService onlinePaymentService;
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ContractService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
            }

    public OnlinePaymentService getOnlinePaymentService() {
        return onlinePaymentService;
    }

    public void setOnlinePaymentService(OnlinePaymentService onlinePaymentService) {
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, int months) {
        double valueBase = contract.getTotalValue() / months;

        for (int i = 0; i < months; i++) {
            LocalDate dueDate = contract.getDate().plusMonths(i + 1);
            double interest = onlinePaymentService.interest(valueBase, i + 1);
            double amountWithInterest = valueBase + interest;
            double fee = onlinePaymentService.paymentFee(amountWithInterest);
            double totalAmount = amountWithInterest + fee;
            Installment parcela = new Installment(dueDate, totalAmount);
            contract.getInstallments().add(parcela);

        }
    }
}
