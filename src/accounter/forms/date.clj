(ns accounter.forms.date
  (require [clojure.core.match :refer [match]])
  (require [clojure.math.combinatorics :as combo])
  (require [clj-time.core :as t]))

(declare coerce-date)

(defn as-date [s]
  (let [ds (vec (map clojure.edn/read-string (re-seq #"\d+" s)))]
    (match ds
      [a b c] (remove #(= % false) (map coerce-date (combo/permutations ds)))
      :else false)))

(defn- coerce-date [ds]
  (try
    (apply t/date-time ds)
    (catch Exception e false)))
