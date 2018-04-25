(ns euler-clojure.problem48)

(defn solve
  [n]
  (apply str (reverse (take 10 (reverse (str (apply + (map (comp #(apply * (repeat % %)) bigint) (range 1 (inc n))))))))))
