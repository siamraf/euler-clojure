(ns euler-clojure.problem3)

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (if (keep #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6)) true false)))))

(defn prime-factors
  [n]
  (cond
    (= 0 (mod n 2)) (conj (prime-factors (quot n 2)) 2)
    :else (loop [i 3 n' n factors []]
      (cond
        (= 0 (mod n' i)) (recur i (quot n' i) (conj factors i))
        (>= i (int (Math/sqrt n'))) (cond-> factors (is-prime n') (conj n'))
        :else (recur (+ i 2) n' factors)))))

(defn solve
  [n]
  (apply max (prime-factors n)))

