(ns accounter.store.memory)

(defrecord Accounter [username accounts])
(defrecord Account [category identifier transactions])
(defrecord Transaction [post-date amount description tags])

(defn add-account
  "Add an account to an accounting file."
  [atr account]
  (update atr :accounts conj account))

(defn add-transaction
  "Add a transaction to an account."
  [account txn]
  (update account :transactions conj txn))
