## Problem Statement

Design a Payment Gateway system that supports:
### Multiple payment methods
- payment processing
- payment status tracking
- extensible architecture
- Requirements
- Functional Requirements

### User should be able to:
- create payment
- choose payment method
- process payment
- view payment status
- Payment Methods

### Support:
- UPI
- Credit Card
- Debit Card
- Wallet
- Net Banking
- Payment Status

### Possible states:
- CREATED
- PROCESSING
- COMPLETED
- FAILED
- REFUNDED


### Non-Functional Requirements
- extensible
- loosely coupled
- scalable
- thread-safe discussion
- easy to add new payment methods

## Main Components

| Component          | Responsibility              |
| ------------------ | --------------------------- |
| PaymentService     | Main orchestration          |
| PaymentStrategy    | Payment processing behavior |
| PaymentFactory     | Strategy creation           |
| Payment            | Payment entity              |
| PaymentStatus      | Payment state               |
| PaymentRepository  | Store payments              |
| TransactionManager | Transaction tracking        |


## Design Patterns Used
| Pattern   | Usage                       |
| --------- | --------------------------- |
| Strategy  | payment methods             |
| Factory   | create payment strategies   |
| State     | payment status              |
| Singleton | gateway instance (optional) |
| Observer  | notifications/webhooks      |
