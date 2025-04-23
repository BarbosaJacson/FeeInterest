import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


        System.out.println("Entre os dados do contrato:");
        System.out.print("Numero:");
        int numberContract = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Data (dd/MM/yyyy):  ");
        LocalDate date;
        try {date = LocalDate.parse(scanner.nextLine(), formatter);
        } catch (DateTimeParseException e) {System.out.println("Data inválida. Use o formato DD/MM/YYYY.");
            return;
        }
        System.out.print("Valor do contrato:  ");
        double value = scanner.nextDouble();
        System.out.print("Entre com o numero de parcelas:  ");
        int number = scanner.nextInt();
        Contract contract = new Contract(number, date, value);
        ContractService service = new ContractService(new PayPal());
        service.processContract(contract, number);
        System.out.println("\nParcelas:");
        for (Installment installment : contract.getInstallments()) {
            System.out.println(installment);
        }


        scanner.close();



    }
}