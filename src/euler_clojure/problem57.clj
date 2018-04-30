(ns euler-clojure.problem57)

(defn iter-frac
  [n]
  (case n
    0 2
    (+ 2 (/ 1 (iter-frac (dec n))))))

(def mem-frac (memoize iter-frac))

(defn sqrt2n
  [n]
  (+ 1 (/ 1 (mem-frac n))))

(defn solve
  [limit]
  (count
    (filter
      #(> (count (str (numerator %))) (count (str (denominator %))))
      (map sqrt2n (range limit)))))
