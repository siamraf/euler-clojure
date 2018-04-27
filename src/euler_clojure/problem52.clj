(ns euler-clojure.problem52)

(defn permute-failures
  [& xs]
  (let [x (frequencies (str (first xs)))
        successes (take-while #(= x (frequencies (str %))) xs)]
    (if (= (count xs) (count successes))
      nil
      successes)))

(defn permutes?
  [& xs]
  (apply = (map #(frequencies (str %)) xs)))

(defn naive-solve
  [n]
  (first (drop-while #(not (apply permutes? (map * (repeat n %) (range 1 (inc n))))) (map inc (range)))))

(defn solve
  [n]
  (loop [failures #{}
         x 1]
    (if (failures x)
      (recur failures (inc x))
      (let [xs (map * (repeat n x) (range 1 (inc n)))
            failed-permutes (apply permute-failures xs)]
        (if failed-permutes
          (recur (into failures failed-permutes) (inc x))
          x)))))
