(ns euler-clojure.problem56)

(defn digit-sum
  [a b]
  (apply + (map #(Character/getNumericValue %) (str (apply * (repeat b (bigint a)))))))

(defn solve
  []
  (apply max
         (for [a (range 1 100)
               b (range 1 100)]
           (digit-sum a b))))
