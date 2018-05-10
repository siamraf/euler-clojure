(ns euler-clojure.problem63)

(defn nth-pow-len
  [n x]
  (count (str (apply * (repeat n (bigint x))))))

(defn solve
  []
  (loop [n 1
         sum 0]
    (let [valid (take-while #(= (nth-pow-len n %) n) (drop-while #(< (nth-pow-len n %) n) (map inc (range))))]
      (if (seq valid)
        (recur (inc n) (+ sum (count valid)))
        sum))))
