(ns euler-clojure.problem14)

(defn get-chain
  [start]
  (case start
    1 [1]
    (cond
      (even? start) (cons start (get-chain (/ start 2)))
      (odd? start)  (cons start (get-chain (+ 1 (* 3 start)))))))

(defn solve
  [n]
  (first (apply max-key second (map #(vector % (count (get-chain %))) (range 1 n)))))
