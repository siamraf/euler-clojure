(ns euler-clojure.problem5)

(defn predicate
  [x]
  (empty? (filter (partial not= 0) (map #(mod x %) [9 11 13 14 15 16 17 18 19 20]))))

(defn solve
  [n]
  (first (drop-while (complement predicate) (iterate (partial + n) n))))
