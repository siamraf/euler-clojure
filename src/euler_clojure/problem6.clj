(ns euler-clojure.problem6)

(defn solve
  [n]
  (let [xs (range 1 (inc n))]
    (Math/abs (- (reduce + (map #(* % %) xs)) (#(* % %) (reduce + xs))))))
