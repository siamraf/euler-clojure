(ns euler-clojure.problem16)

(defn solve
  [pow]
  (apply + (map #(Character/getNumericValue %) (str (apply * (repeat pow 2N))))))
