(ns euler-clojure.problem35)

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

(defn rotations
  [p]
  (let [orig (seq (str p))
        size (count orig)
        orig' (concat orig orig)]
    (map #(Integer/parseInt (apply str (take size (drop % orig')))) (range size))))

(defn circular?
  [primes p]
  (every? #(contains? primes %) (rotations p)))

(defn solve
  [limit]
  (let [primes (set (sieve limit))]
    (count (filter #(circular? primes %) primes))))
