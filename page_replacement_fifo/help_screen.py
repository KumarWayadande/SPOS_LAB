import tkinter as tk
from tkinter import messagebox

# Function to handle the "Close" button
def close_help():
    help_window.destroy()

# Create the main window
root = tk.Tk()
root.title("Help Screen")  # Set the window title
root.geometry("400x300")  # Set the window size

# Label for the header
label_header = tk.Label(root, text="App Help Screen", font=("Arial", 18))
label_header.pack(pady=10)

# A brief instruction about the app
label_info = tk.Label(root, text="This app helps you perform various tasks. Below are the instructions:")
label_info.pack(pady=5)

# Text box for detailed help instructions (scrollable)
text_box = tk.Text(root, height=8, width=40, wrap=tk.WORD)
text_box.insert(tk.END, "1. Login to the app using your credentials.\n"
                         "2. Navigate to different sections using the menu.\n"
                         "3. Click on the 'Submit' button to complete tasks.\n"
                         "4. If you encounter any issues, contact support at support@app.com.")
text_box.config(state=tk.DISABLED)  # Make the text box read-only
text_box.pack(pady=10)

# Close button
close_button = tk.Button(root, text="Close", command=close_help)
close_button.pack(pady=10)

# Start the GUI event loop
root.mainloop()
