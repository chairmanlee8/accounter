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
;; mm dd yyyy -- [2 3 1] -- index 4
;; yyyy mm dd -- [1 2 3] -- index 0
;; dd mm yyyy -- [3 2 1] -- index 5
;;
;; selection order 4 0 5 1 2 3

(defn as-dates [xs]
  (let [ps (vec (map (comp vec as-date) xs))
        cs (apply mapv vector ps)
        sel [4 0 5 1 2 3]
        counts (map #(count (remove false? %)) cs)]
    (nth cs (best-of-order > sel counts))))


;; given an ordering select the best pred of xs in that order
;; returns the index in xs of the result

(defn best-of-order [pred order xs]
  (->> (map vector order (range) xs)
       (sort-by first)
       (reduce #(if (pred (nth %2 2) (nth %1 2)) %2 %1))
       (second)))


;; takes a string containing exactly 3 numeric components and produces a 6-vector of all possible permutations
;; coerced to date (or false if date components were invalid)

(defn as-date [s]
  (let [ds (map clojure.edn/read-string (re-seq #"\d+" s))
        tagged-ds (vec (map vector (range) ds))]
    (match tagged-ds
      [a b c] (map #(coerce-date (map second %1)) (combo/permutations tagged-ds))
      :else (repeat 6 false))))


;; ds - array of yyyy mm dd
;;               0    1  2
;; 0 1 2 -- ymd == abc -> abc == ymd
;; 0 2 1 -- ydm == abc -> acb == ymd
;; ...
;; 2 0 1 -- mdy == abc -> cab == ymd
;; 2 1 0 -- dmy == abc -> cba == ymd

(defn- coerce-date [ds]
  (try
    (apply t/date-time ds)
    (catch Exception e false)))
