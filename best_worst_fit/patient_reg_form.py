import tkinter as tk
from tkinter import messagebox

# Create the main window
root = tk.Tk()
root.title("Patient Registration Form")
root.geometry("500x600")

# Title Label
title_label = tk.Label(root, text="Patient Registration Form", font=("Helvetica", 18))
title_label.pack(pady=20)

# Patient's Full Name
name_label = tk.Label(root, text="Full Name:")
name_label.pack(pady=5)
name_entry = tk.Entry(root, width=40)
name_entry.pack(pady=5)

# Patient's Age
age_label = tk.Label(root, text="Age:")
age_label.pack(pady=5)
age_entry = tk.Entry(root, width=40)
age_entry.pack(pady=5)

# Gender Selection
gender_label = tk.Label(root, text="Gender:")
gender_label.pack(pady=5)

gender_var = tk.StringVar()
gender_var.set("Male")

male_radio = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male")
male_radio.pack()
female_radio = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female")
female_radio.pack()
other_radio = tk.Radiobutton(root, text="Other", variable=gender_var, value="Other")
other_radio.pack()

# Patient's Address
address_label = tk.Label(root, text="Address:")
address_label.pack(pady=5)
address_entry = tk.Entry(root, width=40)
address_entry.pack(pady=5)

# Contact Number
contact_label = tk.Label(root, text="Contact Number:")
contact_label.pack(pady=5)
contact_entry = tk.Entry(root, width=40)
contact_entry.pack(pady=5)

# Email ID
email_label = tk.Label(root, text="Email ID:")
email_label.pack(pady=5)
email_entry = tk.Entry(root, width=40)
email_entry.pack(pady=5)

# Medical History Checkboxes
medical_label = tk.Label(root, text="Medical History (Check all that apply):")
medical_label.pack(pady=10)

# Conditions Checkboxes
diabetes_check = tk.Checkbutton(root, text="Diabetes")
hypertension_check = tk.Checkbutton(root, text="Hypertension")
heart_disease_check = tk.Checkbutton(root, text="Heart Disease")
asthma_check = tk.Checkbutton(root, text="Asthma")
other_condition_check = tk.Checkbutton(root, text="Other (Specify)")

# Display checkboxes
diabetes_check.pack()
hypertension_check.pack()
heart_disease_check.pack()
asthma_check.pack()
other_condition_check.pack()

# Patient's Emergency Contact Name
emergency_label = tk.Label(root, text="Emergency Contact Name:")
emergency_label.pack(pady=5)
emergency_entry = tk.Entry(root, width=40)
emergency_entry.pack(pady=5)

# Emergency Contact Number
emergency_contact_label = tk.Label(root, text="Emergency Contact Number:")
emergency_contact_label.pack(pady=5)
emergency_contact_entry = tk.Entry(root, width=40)
emergency_contact_entry.pack(pady=5)

# Submit Button (without event handling)
submit_button = tk.Button(root, text="Submit", width=20, height=2)
submit_button.pack(pady=20)

# Footer
footer_label = tk.Label(root, text="Powered by Hospital Management System", font=("Helvetica", 8))
footer_label.pack(side="bottom", pady=10)

# Run the application
root.mainloop()
