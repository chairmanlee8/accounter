Initial Specification
=====================

CLI-based interactive knowledge system to accomplish the following:

* Import transactions from account logs, automatically discover (via asking) new accounts and de-duplicate (with prompting).

* Parse and understand receipts & invoices, whether they are in image format or PDF, and match them with transactions from account logs.

* Produce reports of monthly spending broken down by category. Categories may be at the transaction level or at the invoice line item level; error bars in the generated report should reflect the statistical uncertainty due to mis-categorization. For example, transactions at Jewel-Osco with no receipts may be categorized as 80% groceries and 20% food by the system, based on a model generated from Jewel-Osco transactions that did have receipts and were categorized at the line item level.

Web service which powers a mobile gamification portal to drive compliance/participation rate for the human input aspects of the knowledge system:

* Guess the statement closing time and send reminder to upload new transaction logs to the system.

* Ask for receipts for transactions which require them.

* Gamify the categorization process, where the goal is to improve the error score by adding categorizations.

Next Steps
==========

* Analyze income side as well, not just expense side. Match sides for double-entry accounting. Especially useful for split transactions (transactions balanced by immediate cash/Venmo, or delayed cash such as rebates).

* Other account types such as loans; cashflow analysis when matched up with income; goal-based solver should suggest even-cashflow strategies.

* Project categories (special funding); for example, `computer repair` could be a category, or vacation trips (transient categories); virtual categories (for amortization or self-finance situations). Which categories should add up to 100%?

* Non-cash asset tracking; automatic depreciation; calculate liquidity risk, insurance coverage; stocks and other investments.

* Tax projections.
