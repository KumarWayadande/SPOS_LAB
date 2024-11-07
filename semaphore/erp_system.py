import tkinter as tk

# Function to create the login screen
def create_login_screen():
    login_screen = tk.Tk()
    login_screen.title("Login Screen")
    login_screen.geometry("400x300")

    # Title Label
    tk.Label(login_screen, text="Welcome to ERP System", font=("Arial", 16)).pack(pady=20)

    # Username Label and Entry
    tk.Label(login_screen, text="Username:").pack(pady=5)
    username_entry = tk.Entry(login_screen)
    username_entry.pack(pady=5)

    # Password Label and Entry
    tk.Label(login_screen, text="Password:").pack(pady=5)
    password_entry = tk.Entry(login_screen, show="*")
    password_entry.pack(pady=5)

    # Login Button
    login_button = tk.Button(login_screen, text="Login", width=20)
    login_button.pack(pady=20)

    # Create the "Login Screen" window
    login_screen.mainloop()

# Function to create the dashboard screen
def create_dashboard_screen():
    dashboard_screen = tk.Tk()
    dashboard_screen.title("Dashboard")
    dashboard_screen.geometry("600x400")

    # Title Label
    tk.Label(dashboard_screen, text="ERP Dashboard", font=("Arial", 16)).pack(pady=20)

    # Dashboard Buttons for modules
    tk.Button(dashboard_screen, text="Inventory Management", width=20).pack(pady=10)
    tk.Button(dashboard_screen, text="Sales Orders", width=20).pack(pady=10)
    tk.Button(dashboard_screen, text="Reports & Analytics", width=20).pack(pady=10)

    # Create the "Dashboard" window
    dashboard_screen.mainloop()

# Function to create the inventory management screen
def create_inventory_screen():
    inventory_screen = tk.Tk()
    inventory_screen.title("Inventory Management")
    inventory_screen.geometry("600x400")

    # Title Label
    tk.Label(inventory_screen, text="Inventory Management", font=("Arial", 16)).pack(pady=20)

    # Inventory Listbox
    inventory_list = tk.Listbox(inventory_screen, height=10, width=40)
    inventory_list.pack(pady=20)

    # Add some dummy inventory items
    inventory_list.insert(tk.END, "Item 1: Product A - 50 units")
    inventory_list.insert(tk.END, "Item 2: Product B - 30 units")
    inventory_list.insert(tk.END, "Item 3: Product C - 100 units")

    # Buttons to add or remove items (no event handling)
    tk.Button(inventory_screen, text="Add New Item", width=20).pack(pady=5)
    tk.Button(inventory_screen, text="Remove Item", width=20).pack(pady=5)

    # Create the "Inventory Management" window
    inventory_screen.mainloop()

# Function to create the sales order management screen
def create_sales_screen():
    sales_screen = tk.Tk()
    sales_screen.title("Sales Order Management")
    sales_screen.geometry("600x400")

    # Title Label
    tk.Label(sales_screen, text="Sales Order Management", font=("Arial", 16)).pack(pady=20)

    # Sales Order Listbox
    sales_order_list = tk.Listbox(sales_screen, height=10, width=40)
    sales_order_list.pack(pady=20)

    # Add some dummy sales orders
    sales_order_list.insert(tk.END, "Order 1: Customer A - 2 units of Product A")
    sales_order_list.insert(tk.END, "Order 2: Customer B - 5 units of Product C")
    sales_order_list.insert(tk.END, "Order 3: Customer C - 3 units of Product B")

    # Buttons to manage orders (no event handling)
    tk.Button(sales_screen, text="Add New Order", width=20).pack(pady=5)
    tk.Button(sales_screen, text="Process Order", width=20).pack(pady=5)

    # Create the "Sales Order Management" window
    sales_screen.mainloop()

# Function to create the reporting/analytics screen
def create_reports_screen():
    reports_screen = tk.Tk()
    reports_screen.title("Reports & Analytics")
    reports_screen.geometry("600x400")

    # Title Label
    tk.Label(reports_screen, text="Reports & Analytics", font=("Arial", 16)).pack(pady=20)

    # Example of report data
    report_data = "Total Sales: $5000\nInventory Value: $2000\nTop Selling Product: Product A"

    # Display report data
    tk.Label(reports_screen, text=report_data, font=("Arial", 12)).pack(pady=20)

    # Buttons to view different reports (no event handling)
    tk.Button(reports_screen, text="View Sales Report", width=20).pack(pady=5)
    tk.Button(reports_screen, text="View Inventory Report", width=20).pack(pady=5)

    # Create the "Reports & Analytics" window
    reports_screen.mainloop()

# Main function to simulate the process
def main():
    # Create the Login Screen
    create_login_screen()
    create_reports_screen()
    create_dashboard_screen()
    create_inventory_screen()
    create_sales_screen()
    

    # After login (for testing purposes, we can comment out the login screen creation and jump to the dashboard):
    # create_dashboard_screen()

# Run the main function
if __name__ == "__main__":
    main()
