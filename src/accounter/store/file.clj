(ns accounter.store.file
  (require [miner.tagged :as tag])
  (require [accounter.store.memory]))

(defmethod print-method accounter.store.memory.Accounter [this w] (tag/pr-tagged-record-on this w))
(defmethod print-method accounter.store.memory.Account [this w] (tag/pr-tagged-record-on this w))
(defmethod print-method accounter.store.memory.Transaction [this w] (tag/pr-tagged-record-on this w))

(defn save-state
  "Save application state to state.edn"
  [atr]
  (spit "run/state.edn" (pr-str atr)))

(defn load-state
  "Load application state from state.edn"
  []
  (tag/read-string (slurp "run/state.edn")))
