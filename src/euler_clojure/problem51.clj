(ns euler-clojure.problem51)

(defn sieve
  [n]
  (let [xs (vec (range 2 (inc n)))
        ys (range 2 (inc (int (Math/sqrt n))))]
    (remove nil?
      (reduce
        (fn [acc y]
          (if (acc (- y 2))
            (reduce
              (fn [acc' y'] (assoc acc' (- y' 2) nil))
              acc
              (range (* y y) (inc n) y))
            acc))
        xs
        ys))))

; locate 'family' primes
; go backwards

(defn solve
  [limit]
  )
