(ns euler-clojure.problem58)

(defn prime?
  [n]
  (if (<= n 1)
    false
    (if (<= n 3)
      true
      (if (or (= 0 (mod n 2)) (= 0 (mod n 3)))
        false
        (nil? (seq (filter #(or (= 0 (mod n %)) (= 0 (mod n (+ % 2)))) (range 5 (inc (int (Math/sqrt n))) 6))))))))

(defn diagonals
  []
  (reductions + 1 (mapcat (comp #(repeat 4 %) (partial * 2) inc) (range))))

(defn prime-counts
  []
  (drop 1
        (reductions
          (fn [[ps nps lastn] n]
            (if (prime? n) [(inc ps) nps n] [ps (inc nps) n]) )
          [0 0 0]
          (diagonals))))

(defn prime-ratios
  []
  (map (fn [[ps nps n] [_ _ n']] [(/ ps (+ ps nps)) (dec (- n' n))]) (prime-counts) (rest (prime-counts))))

(defn solve
  [lim]
  (second (first (drop-while #(>= (first %) lim) (rest (prime-ratios))))))
