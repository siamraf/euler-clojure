(ns euler-clojure.problem34)

(defn fac
  [n]
  (apply * (range 1 (inc n))))

(defn curious?
  [n]
  (= n (apply + (map #(fac %) (map #(Character/getNumericValue %) (str n))))))

(defn solve
  []
  (apply + (filter curious? (range 3 2540160))))
