# rule-mgmt-system
### Transaction Processing System - Rule Management Module

Interview Problem Statement
Background
You are tasked with designing a rule management module for a transaction processing system. This system needs to evaluate financial transactions against configurable rules to make decisions (such as flagging suspicious transactions, applying discounts, or routing transactions to different processors).

Problem Description

Design a flexible rule management system that can:

1. Define rules consisting of multiple criteria
2. Evaluate transactions against these rules
3. Support complex logical combinations of criteria
4. Allow easy creation and modification of rules

Key Requirements

Rule ->* Criteria("transaction amount > $100" and/or MCC='walmart')

Rules: A rule is a named entity that determines whether a transaction meets certain conditions.

Criteria: Each rule consists of one or more criteria. A criterion is a specific condition that can be evaluated against a transaction (e.g., "transaction amount > $100").

Operations: Rules need to combine multiple criteria using logical operations (AND, OR, etc.).

Flexibility: The system should allow for easy creation of new criteria types without modifying existing code.

Performance: Rule evaluation should be efficient, as it may need to process thousands of transactions per second.

Example Scenarios

Scenario 1: Fraud Detection
1. A bank needs to flag potentially fraudulent transactions based on rules like:

2. Flag if transaction amount > $5,000 AND transaction country differs from card's home country
3. Flag if transaction occurs within 5 minutes of another transaction in a different geographic location

Scenario 2: Discount Application
1. An e-commerce platform needs to apply discounts based on rules like:

2. Apply 10% discount if customer is a premium member AND purchase amount > $200
3. Apply 5% discount if purchase contains items from category "Electronics" OR if it's the customer's birthday

Scenario 3: Transaction Routing

1. A payment processor needs to route transactions to different processing systems based on rules like:

2. Route to high-priority processor if transaction amount > $10,000 OR merchant is in "VIP" category
3. Route to specialized processor if merchant category code = "Airlines" AND transaction currency is not USD

Sample Transaction Data

Consider transactions with attributes like:

{
"id": "tx123456",
"amount": 1250.00,
"currency": "USD",
"merchantId": "merch_89012",
"merchantCategoryCode": "5812", // Restaurants
"timestamp": "2023-05-15T14:30:45Z",
"cardId": "card_12345",
"countryCode": "US"
}
