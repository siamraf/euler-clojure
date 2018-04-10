(ns euler-clojure.problem28)

(defn solve
  [n]
  (apply + (take (inc (* 4 (quot (dec n) 2))) (reductions + 1 (mapcat (comp #(repeat 4 %) (partial * 2) inc) (range))))))
