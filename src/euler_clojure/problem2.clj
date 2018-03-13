(ns euler-clojure.problem2)

(def fib  (cons 1  (cons 2  (lazy-seq  (map + fib  (rest  fib))))))

(defn solve
  [limit]
  (reduce + (filter even? (take-while #(<= % limit) fib))))

