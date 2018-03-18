(ns euler-clojure.problem7)

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

(def solve (nth (sieve 200000) 10000))
