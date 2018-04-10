(ns euler-clojure.problem29)

(defn solve
  [n]
  (count (into #{} (for [a (range 2 (inc n)) b (range 2 (inc n))] (Math/pow a b)))))
