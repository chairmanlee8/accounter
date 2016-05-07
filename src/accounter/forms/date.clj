(ns accounter.forms.date
  (require [clojure.core.match :refer [match]])
  (require [clojure.math.combinatorics :as combo])
  (require [clj-time.core :as t]))

(declare as-dates)
(declare as-date)
(declare coerce-date)

;; takes a list of strings containing exactly 3 numeric components and returns the most likely conversion
;; to date. this is computed by using as-date to generate permutations and selecting the permutation number
;; which maximizes valid dates...amongs equals, use the following sequence preferences
;; mm dd yyyy -- [2 3 1] -- index 3
;; yyyy mm dd -- [1 2 3] -- index 0
;; dd mm yyyy -- [3 2 1] -- index 5
;;
;; selection order 3 0 5 1 2 4

(defn as-dates [xs]
  (let [ps (apply mapv vector (vec (map #(vec (as-date %)) xs)))
        sel [3 0 5 1 2 4]]
    (let [counts (map #(count (remove false? %)) ps)]
      

;; takes a string containing exactly 3 numeric components and produces a 6-vector of all possible permutations
;; coerced to date (or false if date components were invalid)

(defn as-date [s]
  (let [ds (vec (map-indexed (fn [idx itm] [idx (clojure.edn/read-string itm)]) (re-seq #"\d+" s)))]
    (match ds
      [a b c] (map coerce-date (map second (combo/permutations ds)))
      :else false)))

;; ds - array of yyyy mm dd
;;               1    2  3

(defn- coerce-date [ds]
  (try
    (apply t/date-time ds)
    (catch Exception e false)))
