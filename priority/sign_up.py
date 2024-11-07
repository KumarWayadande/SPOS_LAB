import tkinter as tk

# Create a new window
window = tk.Tk()
window.title("Sign-Up Form")

# Set the window size
window.geometry("400x400")

# Create a label for the title
title_label = tk.Label(window, text="Sign-Up", font=("Arial", 20))
title_label.pack(pady=10)

# Username Label and Entry
username_label = tk.Label(window, text="Username:", font=("Arial", 12))
username_label.pack(pady=5)
username_entry = tk.Entry(window, width=30)
username_entry.pack(pady=5)

# Email Label and Entry
email_label = tk.Label(window, text="Email:", font=("Arial", 12))
email_label.pack(pady=5)
email_entry = tk.Entry(window, width=30)
email_entry.pack(pady=5)

# Password Label and Entry
password_label = tk.Label(window, text="Password:", font=("Arial", 12))
password_label.pack(pady=5)
password_entry = tk.Entry(window, show="*", width=30)
password_entry.pack(pady=5)

# Confirm Password Label and Entry
confirm_password_label = tk.Label(window, text="Confirm Password:", font=("Arial", 12))
confirm_password_label.pack(pady=5)
confirm_password_entry = tk.Entry(window, show="*", width=30)
confirm_password_entry.pack(pady=5)

# Sign-Up Button
signup_button = tk.Button(window, text="Sign Up", width=15, font=("Arial", 12))
signup_button.pack(pady=20)

# Cancel Button
cancel_button = tk.Button(window, text="Cancel", width=15, font=("Arial", 12))
cancel_button.pack(pady=10)

# Start the Tkinter loop
window.mainloop()
