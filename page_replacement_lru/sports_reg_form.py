import tkinter as tk
from tkinter import messagebox

# Function to handle form submission
def submit_form():
    name = name_entry.get()
    age = age_entry.get()
    gender = gender_var.get()
    sport = sport_listbox.get(sport_listbox.curselection())
    contact = contact_entry.get()

    # Validate if fields are filled
    if not name or not age or not contact:
        messagebox.showwarning("Input Error", "Please fill in all the required fields.")
    else:
        messagebox.showinfo("Registration Successful", f"Registration Successful for {name}!\nSport: {sport}\nContact: {contact}")
        # You can extend this to store data or process it further
        clear_form()

# Function to clear the form
def clear_form():
    name_entry.delete(0, tk.END)
    age_entry.delete(0, tk.END)
    contact_entry.delete(0, tk.END)
    gender_var.set(None)
    sport_listbox.selection_clear(0, tk.END)

# Create the main window
root = tk.Tk()
root.title("Sports Academy Registration Form")
root.geometry("400x500")

# Label for the form title
title_label = tk.Label(root, text="Sports Academy Registration", font=("Arial", 16, "bold"))
title_label.pack(pady=10)

# Name field
name_label = tk.Label(root, text="Name:", font=("Arial", 12))
name_label.pack(pady=5)
name_entry = tk.Entry(root, font=("Arial", 12))
name_entry.pack(pady=5)

# Age field
age_label = tk.Label(root, text="Age:", font=("Arial", 12))
age_label.pack(pady=5)
age_entry = tk.Entry(root, font=("Arial", 12))
age_entry.pack(pady=5)

# Gender field (Radio buttons)
gender_label = tk.Label(root, text="Gender:", font=("Arial", 12))
gender_label.pack(pady=5)
gender_var = tk.StringVar()
gender_var.set(None)  # No gender selected by default
gender_male = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male", font=("Arial", 12))
gender_female = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female", font=("Arial", 12))
gender_male.pack(pady=5)
gender_female.pack(pady=5)

# Sport selection (Listbox)
sport_label = tk.Label(root, text="Select Sport:", font=("Arial", 12))
sport_label.pack(pady=5)
sport_listbox = tk.Listbox(root, font=("Arial", 12), height=4, selectmode=tk.SINGLE)
sports = ["Football", "Basketball", "Tennis", "Cricket", "Badminton", "Swimming"]
for sport in sports:
    sport_listbox.insert(tk.END, sport)
sport_listbox.pack(pady=5)

# Contact number field
contact_label = tk.Label(root, text="Contact Number:", font=("Arial", 12))
contact_label.pack(pady=5)
contact_entry = tk.Entry(root, font=("Arial", 12))
contact_entry.pack(pady=5)

# Submit and Clear buttons
submit_button = tk.Button(root, text="Submit", font=("Arial", 12), command=submit_form)
submit_button.pack(pady=10)

clear_button = tk.Button(root, text="Clear", font=("Arial", 12), command=clear_form)
clear_button.pack(pady=5)

# Run the main loop
root.mainloop()
