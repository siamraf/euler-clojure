(ns euler-clojure.problem50)

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

(def primes (sieve 1000000))

(def store (atom {}))

(defn consecutive-prime-sum
  ([primes target] (consecutive-prime-sum primes target []))
  ([primes target acc]
   (let [acc' (conj acc (first primes))
         psum (apply + acc')]
     (cond
       (> psum target) (if (empty? acc) nil (recur primes target (rest acc)))
       (< psum target) (recur (rest primes) target acc')
       (= psum target) acc'))))

(defn solve
  [limit]
  (let [primes  (sieve limit)]
    (apply max-key #(count (consecutive-prime-sum primes %)) primes)))
