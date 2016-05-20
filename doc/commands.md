# Create account

    account create <identifier> <category>
    account create amex credit

    account list

    [ Category | Account ]
    [ -------- | ------- ]
    [ credit   | amex    ]

# Import transactions

    account import <file>

    Guessing the following table format:

    [ Transaction Post Date | Amount | Description ]
    [ --------------------- | ------ | ----------- ]
    [ sample data... ]

    Continue? [y/n]
    Entering tagging mode for x transactions...

    4/16/2016  9.99  FOOBAR : default guessed tags :: add ^remove (^@ to remove all)
    ...

# Generate monthly report

    account report spending --show-accounts
    account report spending <month/year>

    MTD (month-to-date)

    [ Tag    | amex  | citi  | chasebcp | Total  ]
    [ ------ | ----- | ----- | -------- | ------ ]
    [ food   | 45.79 | 12.24 | 34.67    | 124.67 ]

    Previous month


    account configure report spending
