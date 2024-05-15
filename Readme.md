# Database Schema for E-commerce Platform by Thanmai

# You need a mysql server running on your machine at port 3306
## Entities and Relationships

### 1. Customers

- **Table Name**: `customers`
- **Description**: Stores information about customers.
- **Primary Key**:
    - `id`: A unique identifier for each customer.
- **Columns**:
    - `name`: The name of the customer.
    - `email`: The customer's email address, unique across the table.

### 2. Products

- **Table Name**: `products`
- **Description**: Contains details about the products available for purchase.
- **Primary Key**:
    - `id`: A unique identifier for each product.
- **Columns**:
    - `name`: Name of the product.
    - `category`: Category of the product (e.g., Electronics, Apparel).
    - `price`: Price of the product.
    - `description`: A description of the product.

### 3. Orders

- **Table Name**: `orders`
- **Description**: Records details of customer orders.
- **Primary Key**:
    - `id`: A unique identifier for each order.
- **Foreign Keys**:
    - `customer_id`: References `id` in the `customers` table.
    - `product_id`: References `id` in the `products` table.
- **Columns**:
    - `quantity`: Number of units of the product ordered.
    - `order_date`: The date and time when the order was placed.

### 4. Ratings

- **Table Name**: `ratings`
- **Description**: Stores ratings given by customers to products.
- **Primary Key**:
    - `id`: A unique identifier for each rating.
- **Foreign Keys**:
    - `product_id`: References `id` in the `products` table.
- **Columns**:
    - `score`: Numerical rating given to a product.

## Relationships Overview

- **Customers and Orders**:
    - One-to-Many: A customer can place multiple orders but each order is linked to one customer.
- **Products and Orders**:
    - One-to-Many: A product can be part of multiple orders, each order contains one or more products.
- **Products and Ratings**:
    - One-to-Many: A product can have multiple ratings but each rating is associated with one product.

