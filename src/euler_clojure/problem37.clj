(ns euler-clojure.problem37)

(defn prime?
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

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

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn truncate-left
  [x]
  (let [xs (map #(Character/getNumericValue %) (str x))]
    (map #(list->num (drop % xs)) (range (count xs)))))

(defn truncate-right
  [x]
  (let [xs (map #(Character/getNumericValue %) (str x))]
    (map #(list->num (take % xs)) (range 1 (inc (count xs))))))

(defn solve
  []
  (apply + (take 11 (filter #(every? prime? (concat (truncate-left %) (truncate-right %))) (drop 4 (sieve 10000000))))))
