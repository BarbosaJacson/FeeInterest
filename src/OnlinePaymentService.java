public interface OnlinePaymentService {
    default double paymentFee(double amount) {
        return 0;
    }

    default double interest(double amount, int months){
        return 0;
    }

}

