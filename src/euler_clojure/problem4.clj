(ns euler-clojure.problem4)

(defn is-palandrome
  [x]
  (let [xs (seq (str x))]
    (= xs (reverse xs))))

(defn solve
  [n]
  (let [values (reverse (range (int (Math/pow 10 (dec n))) (int (Math/pow 10 n))))]
    (apply max (filter is-palandrome (mapcat #(map (partial * %) values) values)))))
