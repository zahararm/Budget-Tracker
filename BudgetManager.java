import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class BudgetManager {
    public static void main(String[] args) {
        String csvFile = "clean_ledger.csv";
        String line;
        
        double totalIncome = 0.0;
        double totalExpenses = 0.0;
        double food = 0.0, utilities = 0.0, entertainment = 0.0, transport = 0.0;

        System.out.println("Analyzing your custom budget...\n");

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String header = br.readLine(); 

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                
                if (values.length >= 4) {
                    try {
                        double amount = Double.parseDouble(values[2]);
                        String category = values[3].trim();

                        if (amount > 0) {
                            totalIncome += amount;
                        } else {
                            double expenseVal = Math.abs(amount);
                            totalExpenses += expenseVal;

                            if (category.equalsIgnoreCase("Food")) food += expenseVal;
                            else if (category.equalsIgnoreCase("Utilities")) utilities += expenseVal;
                            else if (category.equalsIgnoreCase("Entertainment")) entertainment += expenseVal;
                            else if (category.equalsIgnoreCase("Transport")) transport += expenseVal;
                        }
                    } catch (NumberFormatException e) { 
                        
                    }
                }
            }

            double netSavings = totalIncome - totalExpenses;
            double savingsRate = (totalIncome > 0) ? (netSavings / totalIncome) * 100 : 0;

            System.out.println("-----------------------------------------");
            System.out.println("       YOUR PERSONAL BUDGET REPORT       ");
            System.out.println("-----------------------------------------");
            System.out.println("Total Income:       +$" + String.format("%.2f", totalIncome));
            System.out.println("Total Expenses:     -$" + String.format("%.2f", totalExpenses));
            System.out.println("-----------------------------------------");
            System.out.println("Net Monthly Savings: $" + String.format("%.2f", netSavings));
            System.out.println("Savings Rate:        " + String.format("%.1f", savingsRate) + "%");
            System.out.println("-----------------------------------------");
            
            System.out.println("\nEXPENSE BREAKDOWN");
            System.out.println("Food:          $" + String.format("%.2f", food));
            System.out.println("Utilities:     $" + String.format("%.2f", utilities));
            System.out.println("Entertainment: $" + String.format("%.2f", entertainment));
            System.out.println("Transport:     $" + String.format("%.2f", transport));

            
            System.out.println("\nBUDGET MANAGER FEEDBACK");
            if (netSavings < 0) {
                System.out.println("Woah Woah Woahh Girl... You are spending more than you earn! You need to cut back.");
            } else if (savingsRate < 20) {
                System.out.println("Just letting you know, you're saving " + String.format("%.1f", savingsRate) + "%. Lets get that savings rate closer to 20% for better financial health.");
            } else {
                System.out.println("Yayyyy great job! You have a healthy savings rate above 20%. Keep it up!");
            }

        } catch (IOException e) {
            System.out.println("Error reading ledger file: " + e.getMessage());
        }
    }
}