(ns accounter.core
  (use [table.core :only [table]])
  (:gen-class))

(def table-style
  {:top ["" "" ""]
   :top-dash ""
   :middle ["[ " " | " " ]"]
   :dash "─"
   :bottom ["" "" ""]
   :bottom-dash ""
   :header-walls ["[ " " | " " ]"]
   :body-walls ["[ " " | " " ]"]})

(defn -main [& args]
  (table [["Category" "Account"] ["credit" "amex"]] :style table-style)
  (System/exit 0))
