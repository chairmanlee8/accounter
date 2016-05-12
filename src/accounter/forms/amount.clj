(ns accounter.forms.amount)

(defn as-cents
  "Recognize string as an amount and convert to a cents figure."
  [s]
  (->> (re-seq #"\d+" s)
       (clojure.string/join)
       (clojure.edn/read-string)))
