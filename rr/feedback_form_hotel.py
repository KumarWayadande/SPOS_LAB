import tkinter as tk

# Create the main window
root = tk.Tk()
root.title("Feedback Form")

# Set the window size
root.geometry("400x400")

# Add a label for the title of the form
title_label = tk.Label(root, text="Feedback Form", font=("Helvetica", 16))
title_label.pack(pady=10)

# Add a label and text entry for Name
name_label = tk.Label(root, text="Name:")
name_label.pack(pady=5)
name_entry = tk.Entry(root, width=30)
name_entry.pack(pady=5)

# Add a label and text entry for Email
email_label = tk.Label(root, text="Email:")
email_label.pack(pady=5)
email_entry = tk.Entry(root, width=30)
email_entry.pack(pady=5)

# Add a label for feedback type
feedback_type_label = tk.Label(root, text="Type of Feedback:")
feedback_type_label.pack(pady=5)

# Add radio buttons for feedback type
feedback_var = tk.StringVar()
feedback_var.set("Positive")  # Default selection

positive_radio = tk.Radiobutton(root, text="Positive", variable=feedback_var, value="Positive")
positive_radio.pack()
negative_radio = tk.Radiobutton(root, text="Negative", variable=feedback_var, value="Negative")
negative_radio.pack()

# Add a label for feedback comments
comments_label = tk.Label(root, text="Your Comments:")
comments_label.pack(pady=5)

# Add a text widget for comments
comments_text = tk.Text(root, width=30, height=5)
comments_text.pack(pady=5)

# Add a label for rating
rating_label = tk.Label(root, text="Rate your experience (1-5):")
rating_label.pack(pady=5)

# Add scale widget for rating
rating_scale = tk.Scale(root, from_=1, to=5, orient="horizontal")
rating_scale.pack(pady=5)

# Add a label for submission
submit_label = tk.Label(root, text="Click Submit to send your feedback")
submit_label.pack(pady=10)

# Dummy Submit Button (No Event Handling)
submit_button = tk.Button(root, text="Submit")
submit_button.pack(pady=10)

# Run the application
root.mainloop()
