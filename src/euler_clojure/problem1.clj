(ns euler-clojure.problem1)

(defn solve
 [n]
 (apply + (filter #(some (partial = 0) [(mod % 3) (mod % 5)]) (range 0 n))))
