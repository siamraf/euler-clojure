(ns euler-clojure.problem40)

(defn frac
  ([] (frac 0))
  ([n] (lazy-seq (concat (str n) (frac (inc n))))))

(defn solve
  [xs]
  (apply * (map #(Character/getNumericValue (first (drop % (frac)))) xs)))
