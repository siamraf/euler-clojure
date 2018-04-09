(ns euler-clojure.problem25)

(def fib  (cons 1N  (cons 1N  (lazy-seq  (map + fib  (rest  fib))))))

(defn solve
  [n]
  (let [fibs-with-index (map vector fib (map inc (range)))]
    (second (first (drop-while #(< (count (str (first %))) n) fibs-with-index)))))
