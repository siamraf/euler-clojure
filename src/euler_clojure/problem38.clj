(ns euler-clojure.problem38)

(defn pandigital?
  [n]
  (and (= 9 (count (str n))) (= #{1 2 3 4 5 6 7 8 9} (set (map #(Character/getNumericValue %) (str n))))))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) (Character/getNumericValue x))) 0 xs))

(defn create-multiple
  [n]
  (list->num (first (drop-while #(< (count %) 9) (reductions concat "" (map (comp str (partial * n) inc) (range)))))))

(defn nines
  [limit]
  (reverse (mapcat (fn [n] (#(range (* 9 %) (* 10 %)) (apply * (repeat n 10)))) (range limit))))

(defn solve
  [n]
  (first (filter #(pandigital? (create-multiple %)) (nines n))))
