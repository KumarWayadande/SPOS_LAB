import tkinter as tk
from tkinter import messagebox

# Function to be called when the submit button is clicked
def submit_form():
    name = entry_name.get()
    gender = gender_var.get()
    courses = []
    if course_var1.get():
        courses.append("Math")
    if course_var2.get():
        courses.append("Science")
    if course_var3.get():
        courses.append("English")
    selected_hobbies = listbox_hobbies.curselection()
    hobbies = [listbox_hobbies.get(i) for i in selected_hobbies]

    if not name or not gender:
        messagebox.showwarning("Input Error", "Please fill out all fields.")
        return
    
    # Display the collected information
    info = f"Name: {name}\nGender: {gender}\nCourses: {', '.join(courses)}\nHobbies: {', '.join(hobbies)}"
    messagebox.showinfo("Student Registration", info)

# Create the main window
root = tk.Tk()
root.title("Student Registration Form")
root.geometry("400x400")

# Labels and Text fields for student name
label_name = tk.Label(root, text="Student Name:")
label_name.grid(row=0, column=0, padx=10, pady=10)

entry_name = tk.Entry(root, width=30)
entry_name.grid(row=0, column=1, padx=10, pady=10)

# Gender selection using Radiobutton
label_gender = tk.Label(root, text="Gender:")
label_gender.grid(row=1, column=0, padx=10, pady=10)

gender_var = tk.StringVar()

radio_male = tk.Radiobutton(root, text="Male", variable=gender_var, value="Male")
radio_female = tk.Radiobutton(root, text="Female", variable=gender_var, value="Female")
radio_other = tk.Radiobutton(root, text="Other", variable=gender_var, value="Other")

radio_male.grid(row=1, column=1, sticky="w", padx=10)
radio_female.grid(row=1, column=1, padx=10)
radio_other.grid(row=1, column=1, sticky="e", padx=10)

# Checkboxes for course selection
label_courses = tk.Label(root, text="Select Courses:")
label_courses.grid(row=2, column=0, padx=10, pady=10)

course_var1 = tk.IntVar()
course_var2 = tk.IntVar()
course_var3 = tk.IntVar()

check_math = tk.Checkbutton(root, text="Math", variable=course_var1)
check_science = tk.Checkbutton(root, text="Science", variable=course_var2)
check_english = tk.Checkbutton(root, text="English", variable=course_var3)

check_math.grid(row=2, column=1, sticky="w", padx=10)
check_science.grid(row=2, column=1, padx=10)
check_english.grid(row=2, column=1, sticky="e", padx=10)

# Listbox for selecting hobbies
label_hobbies = tk.Label(root, text="Select Hobbies:")
label_hobbies.grid(row=3, column=0, padx=10, pady=10)

listbox_hobbies = tk.Listbox(root, selectmode="multiple", height=5)
listbox_hobbies.grid(row=3, column=1, padx=10, pady=10)

# Add items to the hobbies listbox
hobbies_list = ["Reading", "Sports", "Music", "Gaming", "Traveling"]
for hobby in hobbies_list:
    listbox_hobbies.insert(tk.END, hobby)

# Submit button
button_submit = tk.Button(root, text="Submit", command=submit_form)
button_submit.grid(row=4, column=0, columnspan=2, pady=20)

# Start the GUI loop
root.mainloop()
