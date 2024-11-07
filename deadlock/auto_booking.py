import tkinter as tk
from tkinter import messagebox

# Create the main window
root = tk.Tk()
root.title("Cab/Auto Booking App")
root.geometry("400x400")

# Define a function to display the booking details (for visual feedback, not event handling)
def show_details():
    name = name_entry.get()
    pick_up = pick_up_entry.get()
    destination = destination_entry.get()
    vehicle = vehicle_var.get()

    messagebox.showinfo("Booking Details", f"Name: {name}\nPick-Up: {pick_up}\nDestination: {destination}\nVehicle Type: {vehicle}")

# Label and Entry for Name
name_label = tk.Label(root, text="Enter your Name:")
name_label.pack(pady=10)

name_entry = tk.Entry(root)
name_entry.pack(pady=5)

# Label and Entry for Pick-Up Location
pick_up_label = tk.Label(root, text="Pick-Up Location:")
pick_up_label.pack(pady=10)

pick_up_entry = tk.Entry(root)
pick_up_entry.pack(pady=5)

# Label and Entry for Destination
destination_label = tk.Label(root, text="Destination:")
destination_label.pack(pady=10)

destination_entry = tk.Entry(root)
destination_entry.pack(pady=5)

# Label for Vehicle Type Selection
vehicle_label = tk.Label(root, text="Select Vehicle Type:")
vehicle_label.pack(pady=10)

# Vehicle Type Selection using RadioButtons
vehicle_var = tk.StringVar(value="Cab")  # Default value is "Cab"
cab_radio = tk.Radiobutton(root, text="Cab", variable=vehicle_var, value="Cab")
cab_radio.pack()

auto_radio = tk.Radiobutton(root, text="Auto", variable=vehicle_var, value="Auto")
auto_radio.pack()

# Button to show the booking details
show_button = tk.Button(root, text="Show Booking Details", command=show_details)
show_button.pack(pady=20)

# Run the main loop to display the window
root.mainloop()
