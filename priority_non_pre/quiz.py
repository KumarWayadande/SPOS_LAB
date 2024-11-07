import tkinter as tk
from tkinter import messagebox

class QuizApp:
    def __init__(self, root):
        self.root = root
        self.root.title("Online Quiz")
        self.root.geometry("400x400")

        # List of questions and answers
        self.questions = [
            "What is the capital of France?",
            "Which is the largest planet in our Solar System?",
            "Who wrote 'Hamlet'?",
        ]

        # Options for each question
        self.options = [
            ["Paris", "London", "Rome", "Berlin"],
            ["Earth", "Mars", "Jupiter", "Venus"],
            ["Charles Dickens", "William Shakespeare", "Mark Twain", "Jane Austen"],
        ]

        # Correct answers for each question
        self.answers = [0, 2, 1]  # Index of correct answers (zero-based)

        self.current_question = 0  # Track the current question
        self.selected_option = tk.IntVar()  # Track selected option

        self.score = 0  # Track the score

        self.create_widgets()

    def create_widgets(self):
        # Display question
        self.question_label = tk.Label(self.root, text=self.questions[self.current_question], font=("Arial", 14))
        self.question_label.pack(pady=20)

        # Display options using checkboxes (radio buttons)
        self.option_buttons = []
        for i in range(4):
            radio_btn = tk.Radiobutton(
                self.root, text=self.options[self.current_question][i],
                variable=self.selected_option, value=i, font=("Arial", 12)
            )
            radio_btn.pack(pady=5)
            self.option_buttons.append(radio_btn)

        # Submit button
        self.submit_button = tk.Button(self.root, text="Submit", command=self.next_question, font=("Arial", 14))
        self.submit_button.pack(pady=20)

    def next_question(self):
        # Check if the selected answer is correct
        if self.selected_option.get() == self.answers[self.current_question]:
            self.score += 1

        # Move to the next question or finish
        self.current_question += 1
        if self.current_question >= len(self.questions):
            self.show_result()
        else:
            self.update_question()

    def update_question(self):
        # Update the question and options
        self.question_label.config(text=self.questions[self.current_question])
        for i, btn in enumerate(self.option_buttons):
            btn.config(text=self.options[self.current_question][i])
        self.selected_option.set(-1)  # Deselect options

    def show_result(self):
        # Display the result in a message box
        result_text = f"Quiz finished!\nYour score: {self.score}/{len(self.questions)}"
        messagebox.showinfo("Quiz Result", result_text)
        self.root.quit()  # Close the quiz window

# Main program
if __name__ == "__main__":
    root = tk.Tk()
    app = QuizApp(root)
    root.mainloop()
