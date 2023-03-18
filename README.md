# MonkPay v1.0.0

A simple payment gateway application integrated with Instamojo. This document includes all the information required to set up, run and experiment with the application.

- #### Current functionality includes:
  1. Create single/multiple invoices and save to the database.
  2. Three invoices are created and saved to the database by default on application start up.
  2. Fetch single/multiple invoices from the database.
  3. Delete single/multiple invoices from the database.
  4. Generate access token for payment initialisation.
  4. Initiate payment for a particular invoice and save returned details response for corresponding invoice to the database.
  5. Status updated for existing invoices based on status of payments.
  6. Receive webhooks for payment completions, update invoice status and save to the database for future usage.

- #### Upcoming enhancements:
  1. Logging framework.
  2. Explicit exception handling.
  3. Response improvements for error cases.

**Now, let's use MonkPay!**

# Pre-Requisites

- **MongoDB** is MonkPay's choice of database and to ensure the application functions as expected, a mongo server is expected to be running on your local machine.
    [Here](https://www.mongodb.com/docs/manual/administration/install-community/) is a reference which you can use to achieve this. If you do not have Home Brew installed on your machine, you can refer [this](https://brew.sh/) documentation.
- **ngrok** is used to set up a server on your local machine in order to receive webhooks from InstaMojo. It can be installed using Home Brew from [here](https://ngrok.com/download).

# APIs

- ### General

| Endpoint | Method | Description                        |
|----------|--------|------------------------------------|
| /health  | GET    | Application health check endpoint. |

- ### Invoice

| Endpoint         | Method | Description                                                               |
|------------------|--------|---------------------------------------------------------------------------|
| /invoice         | GET    | Fetch all invoices or invoices grouped by payment status.                 |
| /invoice         | POST   | Create a new invoice or multiple invoices based on input count parameter. |
| /invoice         | DELETE | Delete all invoices or a specific invoice based on invoice identifier.    |
| /payment         | POST   | Initiate payment for an invoice based on it's unique identifier.          |
| /webhook/payment | POST   | Receive payment webhooks from Instamojo for a particular invoice.         |

- ### Parameters

| Parameter      | Type    | Mandatory | Description                       |
|----------------|---------|-----------|-----------------------------------|
| payment_status | Enum    | No        | PENDING / INITIATED / COMPLETED.  |
| count          | Integer | No        | Number of invoices to be created. |
| invoice_id     | String  | Yes/No    | Unique identifier for an invoice. |
