(defproject accounter "0.1.0-SNAPSHOT"
  :description "Command-line accounting program."
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"],
                 [org.clojure/core.match "0.3.0-alpha4"],
                 [org.clojure/math.combinatorics "0.1.1"],
                 [clojure-csv/clojure-csv "2.0.1"],
                 [clj-time "0.11.0"],
                 [table "0.5.0"],
                 [com.velisco/tagged "0.4.1"]]
  :main ^:skip-aot accounter.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
