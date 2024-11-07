import tkinter as tk
from tkinter import ttk

# Create the main window
root = tk.Tk()
root.title("Shopping App Prototype")
root.geometry("800x600")

# Create the title for the app
title_label = tk.Label(root, text="Welcome to ShopEasy", font=("Arial", 24), fg="blue")
title_label.pack(pady=10)

# Frame for the product categories
frame_categories = tk.Frame(root)
frame_categories.pack(side=tk.LEFT, fill=tk.Y, padx=20, pady=10)

# Add category label
label_categories = tk.Label(frame_categories, text="Product Categories", font=("Arial", 16))
label_categories.pack(anchor="w")

# Add categories as buttons (No event handling for this prototype)
categories = ["Electronics", "Fashion", "Home & Furniture", "Books", "Beauty Products", "Sports"]
for category in categories:
    category_button = tk.Button(frame_categories, text=category, width=20)
    category_button.pack(pady=5)

# Frame for the product list
frame_products = tk.Frame(root)
frame_products.pack(side=tk.LEFT, fill=tk.BOTH, expand=True, padx=20, pady=10)

# Add product list label
label_products = tk.Label(frame_products, text="Products", font=("Arial", 16))
label_products.pack(anchor="w")

# Create a product list (prototype without actual data)
product_names = [
    "Smartphone - $499", "Laptop - $899", "T-shirt - $19", 
    "Bookshelf - $79", "Running Shoes - $59", "Lipstick - $15"
]

# Add a listbox to display products
listbox_products = tk.Listbox(frame_products, height=15, width=50)
for product in product_names:
    listbox_products.insert(tk.END, product)
listbox_products.pack(pady=10)

# Frame for the shopping cart
frame_cart = tk.Frame(root)
frame_cart.pack(side=tk.RIGHT, fill=tk.Y, padx=20, pady=10)

# Add cart label
label_cart = tk.Label(frame_cart, text="Shopping Cart", font=("Arial", 16))
label_cart.pack(anchor="w")

# Create a cart listbox (Empty in prototype)
listbox_cart = tk.Listbox(frame_cart, height=10, width=30)
listbox_cart.pack(pady=10)

# Add a total price label
label_total = tk.Label(frame_cart, text="Total: $0.00", font=("Arial", 14))
label_total.pack(anchor="w", pady=5)

# Add buttons for cart actions
button_add_to_cart = tk.Button(frame_cart, text="Add to Cart", width=20)
button_add_to_cart.pack(pady=5)

button_remove_from_cart = tk.Button(frame_cart, text="Remove from Cart", width=20)
button_remove_from_cart.pack(pady=5)

button_checkout = tk.Button(frame_cart, text="Checkout", width=20, bg="green", fg="white")
button_checkout.pack(pady=10)

# Main loop to run the app
root.mainloop()
