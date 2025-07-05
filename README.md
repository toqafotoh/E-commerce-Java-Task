# E-commerce-Java-Task

## Project Overview

This project represents a simple e-commerce system that demonstrates clear application of Object-Oriented Programming (OOP) principles. The system allows defining products with different behaviors such as expiration and shipping requirements, as well as basic operations like adding products to a cart and performing checkout.

---

## Class Diagram

 

---

## Why the OOP Design is Structured This Way

* The Product class uses Composition to associate behaviors like expiration and shipping, keeping the product logic clean and separated.
* Interfaces (ExpirationPolicy and ShippingPolicy) allow defining flexible rules for expiration and shipping, making the system easy to extend.
* This structure enables adding new types of products or behaviors without changing existing classes, following core OOP principles.
* Classes are divided logically:

  * model package for core entities like Product, Cart, Customer
  * policy package for shipping and expiration logic
  * service package for operations like checkout and shipping
  * exception package for handling expected errors clearly

The overall design ensures the system is organized, easy to maintain, and follows standard OOP best practices.

 
