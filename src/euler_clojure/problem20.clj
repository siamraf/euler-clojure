(ns euler-clojure.problem20)

(defn solve
  [n]
  (apply + (map #(Character/getNumericValue %) (str (apply * (map bigint (range 1 (inc n))))))))
