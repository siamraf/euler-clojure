(ns euler-clojure.problem45)

(defn tri
  [n]
  (/ (* n (inc n)) 2))

(defn pent
  [n]
  (/ (* n (dec (* 3 n))) 2))

(defn hex
  [n]
  (* n (dec (* 2 n))))

(defn solve
  ([] (apply solve [1 1 1]))
  ([tn pn hn]
   (let [tv (tri tn)
         pv (pent pn)
         hv (hex hn)]
     (cond
       (= tv pv hv) (cons tv (apply solve (map inc [tn pn hn])))
       (and (<= tv pv) (<= tv hv)) (recur (inc tn) pn hn)
       (and (<= pv tv) (<= pv hv)) (recur tn (inc pn) hn)
       :else (recur tn pn (inc hn))))))
