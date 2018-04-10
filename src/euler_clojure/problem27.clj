(ns euler-clojure.problem27)

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (if (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))) false true)))))

(defn solve
  [limit]
  (apply max-key first
         (for [a (range (- limit) limit)
               b (range (- limit) (inc limit))]
           [(count (take-while is-prime (map #(+ (* % %) (* a %) b) (range)))) [a b]])))
