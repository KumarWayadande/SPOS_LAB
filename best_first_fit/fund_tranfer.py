import tkinter as tk
from tkinter import messagebox

# Create the main window
root = tk.Tk()
root.title("Fund Transfer Screen")

# Set window size
root.geometry("400x400")

# Title label
title_label = tk.Label(root, text="Fund Transfer", font=("Helvetica", 18))
title_label.pack(pady=20)

# Sender Account Number
sender_account_label = tk.Label(root, text="Sender Account Number:")
sender_account_label.pack(pady=5)
sender_account_entry = tk.Entry(root, width=30)
sender_account_entry.pack(pady=5)

# Receiver Account Number
receiver_account_label = tk.Label(root, text="Receiver Account Number:")
receiver_account_label.pack(pady=5)
receiver_account_entry = tk.Entry(root, width=30)
receiver_account_entry.pack(pady=5)

# Transfer Amount
amount_label = tk.Label(root, text="Amount to Transfer:")
amount_label.pack(pady=5)
amount_entry = tk.Entry(root, width=30)
amount_entry.pack(pady=5)

# Transaction Date (Static Example)
transaction_date_label = tk.Label(root, text="Transaction Date:")
transaction_date_label.pack(pady=5)
transaction_date_value = tk.Label(root, text="2024-11-07")
transaction_date_value.pack(pady=5)

# Transaction Type (Radio Buttons)
transaction_type_label = tk.Label(root, text="Transaction Type:")
transaction_type_label.pack(pady=5)
transaction_type = tk.StringVar()
transaction_type.set("Transfer")

deposit_radio = tk.Radiobutton(root, text="Deposit", variable=transaction_type, value="Deposit")
deposit_radio.pack()
transfer_radio = tk.Radiobutton(root, text="Transfer", variable=transaction_type, value="Transfer")
transfer_radio.pack()

# Submit Button (No Event Handling)
submit_button = tk.Button(root, text="Submit", width=20, height=2)
submit_button.pack(pady=20)

# Footer
footer_label = tk.Label(root, text="Powered by Fund Management System", font=("Helvetica", 8))
footer_label.pack(side="bottom", pady=10)

# Run the application
root.mainloop()
