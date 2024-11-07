import tkinter as tk
from tkinter import messagebox

# Dummy Data for Authentication (username and password)
users_db = {"user1": "password123", "user2": "mypassword"}

# Function to handle login
def login():
    username = username_entry.get()
    password = password_entry.get()

    # Check if user exists and password matches
    if username in users_db and users_db[username] == password:
        login_screen.destroy()
        create_dashboard_screen(username)
    else:
        messagebox.showerror("Login Error", "Invalid Username or Password")

# Function to create the Dashboard screen after successful login
def create_dashboard_screen(username):
    dashboard_screen = tk.Tk()
    dashboard_screen.title("Banking Dashboard")
    dashboard_screen.geometry("600x400")

    # Welcome Label
    tk.Label(dashboard_screen, text=f"Welcome, {username}!", font=("Arial", 16)).pack(pady=20)

    # Balance Button
    tk.Button(dashboard_screen, text="Check Balance", width=30, command=lambda: show_balance(dashboard_screen, username)).pack(pady=10)

    # Fund Transfer Button
    tk.Button(dashboard_screen, text="Transfer Funds", width=30, command=lambda: fund_transfer(dashboard_screen, username)).pack(pady=10)

    # Account Details Button
    tk.Button(dashboard_screen, text="Account Details", width=30, command=lambda: show_account_details(dashboard_screen, username)).pack(pady=10)

    # Exit Button
    tk.Button(dashboard_screen, text="Logout", width=30, command=dashboard_screen.quit).pack(pady=20)

    dashboard_screen.mainloop()

# Function to display the Balance
def show_balance(dashboard_screen, username):
    messagebox.showinfo("Balance", "Your current balance is: $5000")

# Function to create the Fund Transfer screen
def fund_transfer(dashboard_screen, username):
    transfer_screen = tk.Toplevel(dashboard_screen)
    transfer_screen.title("Fund Transfer")
    transfer_screen.geometry("400x300")

    # Amount Label and Entry
    tk.Label(transfer_screen, text="Amount to Transfer:").pack(pady=10)
    amount_entry = tk.Entry(transfer_screen)
    amount_entry.pack(pady=10)

    # Recipient Label and Entry
    tk.Label(transfer_screen, text="Recipient Account:").pack(pady=10)
    recipient_entry = tk.Entry(transfer_screen)
    recipient_entry.pack(pady=10)

    # Transfer Button
    tk.Button(transfer_screen, text="Transfer", width=20, command=lambda: complete_transfer(amount_entry.get(), recipient_entry.get())).pack(pady=20)

# Function to simulate the fund transfer
def complete_transfer(amount, recipient):
    try:
        amount = float(amount)
        if amount <= 0:
            raise ValueError("Amount must be greater than zero.")
        # Simulating transfer logic
        messagebox.showinfo("Transfer", f"Successfully transferred ${amount} to account {recipient}.")
    except ValueError as e:
        messagebox.showerror("Error", str(e))

# Function to show account details
def show_account_details(dashboard_screen, username):
    account_screen = tk.Toplevel(dashboard_screen)
    account_screen.title("Account Details")
    account_screen.geometry("400x300")

    # Account Information
    tk.Label(account_screen, text=f"Account holder: {username}", font=("Arial", 12)).pack(pady=20)
    tk.Label(account_screen, text="Account Number: 123456789", font=("Arial", 12)).pack(pady=10)
    tk.Label(account_screen, text="Account Type: Savings", font=("Arial", 12)).pack(pady=10)
    tk.Label(account_screen, text="Balance: $5000", font=("Arial", 12)).pack(pady=10)

    # Exit Button
    tk.Button(account_screen, text="Close", command=account_screen.quit).pack(pady=20)

# Function to create the login screen
def create_login_screen():
    global username_entry, password_entry, login_screen

    login_screen = tk.Tk()
    login_screen.title("Banking Login")
    login_screen.geometry("400x300")

    # Login Form
    tk.Label(login_screen, text="Username:", font=("Arial", 12)).pack(pady=5)
    username_entry = tk.Entry(login_screen)
    username_entry.pack(pady=5)

    tk.Label(login_screen, text="Password:", font=("Arial", 12)).pack(pady=5)
    password_entry = tk.Entry(login_screen, show="*")
    password_entry.pack(pady=5)

    # Login Button
    tk.Button(login_screen, text="Login", width=20, command=login).pack(pady=20)

    login_screen.mainloop()

# Main entry point to the program
if __name__ == "__main__":
    create_login_screen()
