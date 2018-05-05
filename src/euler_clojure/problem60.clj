(ns euler-clojure.problem60)

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

(defn num->list
  [n]
  (map #(Character/getNumericValue %) (str n)))

(defn list->num
  [xs]
  (reduce (fn [acc x] (+ (* 10 acc) x)) 0 xs))

(defn divisions
  [ps]
  (map #(vector (take % ps) (drop % ps)) (range 1 (count ps))))

(defn prime-pairs
  [p]
  (filter #(and
             (prime? (Integer/parseInt (apply str (reverse %))))
             (prime? (Integer/parseInt (apply str %)))
             (every? prime? %))
          (map #(map list->num %) (divisions (num->list p)))))

(defn retrieve
  [ps target current acc cnt]
  (case cnt
    0 (when (and (every? #(contains? (ps current) %) acc) ((set (ps current)) target)) [(conj acc current)])
    (apply concat
           (keep #(retrieve ps target % (conj acc current) (dec cnt))
                 (filter
                   #(and (every? (fn [x] (contains? (ps x) %)) acc)
                               (not (acc %))
                               (not= % target))
                         (ps current))))))

(defn dootherthing
  [ps target acc cnt]
  (case cnt
    0 [acc]
    (let [children (ps target)]
      (apply concat (keep (fn [child]
        (when (every? #((ps %) child) acc)
          (dootherthing ps child (conj acc child) (dec cnt)))) children)))))

(defn dothing
  [n]
 (into {} (map (fn [[k v]] [k (set v)]) (reduce (fn [acc [p1 p2]] (assoc acc p1 (conj (acc p1) p2))) {} (mapcat prime-pairs (sieve n))))))

(defn solve
  [limit n]
  (let [ps (dothing limit)
        ps (into {} (filter (fn [[k v]] (>= (count v) n)) ps))]
    (prn "churn churn churn.")
    (set (map set (apply concat (keep #(dootherthing ps % [] n) (keys ps)))))))
