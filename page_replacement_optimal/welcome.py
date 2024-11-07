import tkinter as tk

# Function to close the welcome window
def close_welcome():
    welcome_window.destroy()

# Create the main window
welcome_window = tk.Tk()
welcome_window.title("Welcome Screen")  # Set the window title
welcome_window.geometry("400x300")  # Set the window size

# Label for the title of the application
title_label = tk.Label(welcome_window, text="Welcome to Our Application!", font=("Arial", 20, "bold"))
title_label.pack(pady=50)

# Label for a brief description or welcome message
message_label = tk.Label(welcome_window, text="We are glad to have you here.", font=("Arial", 14))
message_label.pack(pady=10)

# Button to close the welcome window
close_button = tk.Button(welcome_window, text="Close", command=close_welcome, font=("Arial", 12))
close_button.pack(pady=20)

# Start the GUI event loop
welcome_window.mainloop()
