import pandas as pd

print("*** ENTER BUDGET ***")

try:
    monthly_income = float(input("Enter your total monthly income ($): "))
    food_spent = float(input("Enter your monthly Food spending ($): "))
    utility_spent = float(input("Enter your monthly Utilities spending ($): "))
    entertainment_spent = float(input("Enter your monthly Entertainment spending ($): "))
    transport_spent = float(input("Enter your monthly Transport spending ($): "))
    
    data = [
        {"Date": "2026-10-01", "Description": "Monthly Income", "Amount": monthly_income, "Category": "Income"},
        {"Date": "2026-10-02", "Description": "Food", "Amount": -food_spent, "Category": "Food"},
        {"Date": "2026-10-03", "Description": "Utilities & Bills", "Amount": -utility_spent, "Category": "Utilities"},
        {"Date": "2026-10-04", "Description": "Entertainment & Fun", "Amount": -entertainment_spent, "Category": "Entertainment"},
        {"Date": "2026-10-05", "Description": "Transport & Gas", "Amount": -transport_spent, "Category": "Transport"}
    ]

    df = pd.DataFrame(data)
    df.to_csv("clean_ledger.csv", index=False)
    
    print("\nYassss Girl! Your custom budget has been saved! Run -javac BudgetManager.java- then run -java BudgetManager-!")

except ValueError:
    print("Ooopsies Babe! Please enter numbers only (e.g., 2500 or 45.50).")