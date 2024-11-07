import tkinter as tk

# Create the main window
root = tk.Tk()
root.title("Login Window")
root.geometry("300x200")

# Create a label for the login title
label_title = tk.Label(root, text="Login", font=("Arial", 20))
label_title.pack(pady=10)

# Frame for input fields
frame_inputs = tk.Frame(root)
frame_inputs.pack(pady=10)

# Label and text entry for username
label_username = tk.Label(frame_inputs, text="Username:", font=("Arial", 12))
label_username.grid(row=0, column=0, padx=5, pady=5)

entry_username = tk.Entry(frame_inputs, width=25)
entry_username.grid(row=0, column=1, padx=5, pady=5)

# Label and text entry for password
label_password = tk.Label(frame_inputs, text="Password:", font=("Arial", 12))
label_password.grid(row=1, column=0, padx=5, pady=5)

entry_password = tk.Entry(frame_inputs, show="*", width=25)
entry_password.grid(row=1, column=1, padx=5, pady=5)

# Frame for buttons
frame_buttons = tk.Frame(root)
frame_buttons.pack(pady=10)

# Login button
button_login = tk.Button(frame_buttons, text="Login", width=10)
button_login.grid(row=0, column=0, padx=5, pady=5)

# Reset button
button_reset = tk.Button(frame_buttons, text="Reset", width=10)
button_reset.grid(row=0, column=1, padx=5, pady=5)

# Run the main loop
root.mainloop()
