(ns euler-clojure.problem46)

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

(defn is-prime
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(defn composite-numbers
  []
  (filter #(and (not (is-prime %)) (odd? %)) (drop 2 (range))))

(defn fits?
  [n p]
  (let [square (int (Math/sqrt (/ (- n p) 2)))]
    (= n (+ p (* 2 square square)))))

(defn solve
  []
  (first (filter #(not (some (partial fits? %) (sieve %))) (composite-numbers))))
