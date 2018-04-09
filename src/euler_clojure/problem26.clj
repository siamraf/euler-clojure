(ns euler-clojure.problem26)

; only works for initial p = 1
(defn divide
  ([p n] (divide p n [] {}))
  ([p n acc seen]
  (let [seen-sequence (seen [p n])]
    (if seen-sequence
      [(seq seen-sequence) (drop (count seen-sequence) acc)]
      (case p
        0 acc
        (let [p' (* 10 p)
            q (quot p' n)
            r (rem p' n)]
          (case r
            0 [(conj acc q) ()]
            (recur r n (conj acc q) (assoc seen [p n] acc)))))))))

(defn solve
  [n]
  (let [rec-cycles (into {} (map #(vector (count (second (divide 1 %))) %) (range 2 n)))]
    (rec-cycles (apply max (keys rec-cycles)))))
